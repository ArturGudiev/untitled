package marketyp;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;
import java.util.stream.Stream;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import static java.lang.Math.min;
import static marketyp.FloydWarshall.*;
import static marketyp.FloydWarshall.getDistance;

public class Env {
    public static final Env INSTANCE = new Env();
    public volatile ArrayList<Integer> pathes = new ArrayList<Integer>();
    public volatile int buyerAgent = 0;
    public boolean SOLVED = false;
    public static int timeout = 100;
    public static int getLengthOfPath(List<Integer> path) {
        int sum = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            sum += getDistance(path.get(i), path.get(i + 1));
        }
        return sum;
    }

    public static void printToFile(String str) {
        try
        {
            String filename= "C:\\Programming\\Java\\untitled\\src\\main\\java\\marketyp\\final.txt";
            FileWriter fw = new FileWriter(filename,true); //the true will append the new data
            fw.write(str + "\n");//appends the string to the file
            fw.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }

    }
    public static void main(String[] args) {
        FloydWarshall.main(null);
        List<Integer> path1 = getPath(1, 11);
//        System.out.println(path1);

    }

    public static ACLMessage getAcceptMessage(ACLMessage msg) {
        ACLMessage ans = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
        ans.addReceiver(new AID(msg.getSender().getLocalName(), AID.ISLOCALNAME));
        ans.setLanguage("English");
        ans.setOntology("Connection");
        return ans;
    }

    public static void printMessage(Agent agent, ACLMessage msg) {
        String content = msg.getContent() ;
        content = content == null ? "" : content;
        System.out.println(agent.getLocalName() + ": GOT message "+perf(msg)+" from " + msg.getSender().getLocalName() + " " + content);
    }

    public static String perf(ACLMessage msg){
        return ACLMessage.getAllPerformativeNames()[msg.getPerformative()];
    }

    public static void printSentMessage(Agent agent, String agentName, ACLMessage msg) {
        System.out.println(agent.getLocalName() +  ": SENT message "+ perf(msg) +" to " + agentName + " " + msg.getContent());
    }

    public static double getDistFromPathToPoint(List<Integer> path, int point) {
        return getDistFromPathToPoint(path, point, 0, path.size());
    }

    public static double getDistFromPathToPoint(List<Integer> path, int point, int startIndex, int lastIndex) {
        double ans = Double.POSITIVE_INFINITY;
        for (int i = startIndex; i < lastIndex; i++) {
            int u = path.get(i);
            double d = 2 * getDistance(u, point);
            if (i != path.size() - 1) {
                int v = path.get(i + 1);
                double d2 = getDistance(u, point) + getDistance(point, v);
                ans = min(min(d, d2), ans);
            } else {
                ans = min(d, ans);
            }
        }
        return ans;
    }

    public static void addPointToPath(List<Integer> path, int point) {
        addPointToPath(path, point, 0, path.size());
    }

    public static void addPointToPath(List<Integer> path, int point, int startIndex, int lastIndex) {
//        System.out.println("path = [" + path + "], point = [" + point + "], startIndex = [" + startIndex + "], lastIndex = [" + lastIndex + "]");
        double val = Double.POSITIVE_INFINITY;
        int index = startIndex;
        boolean self = false;
        for (int i = startIndex; i < lastIndex; i++) {
            int u = path.get(i);
            double dist_self = 2 * getDistance(u, point);
            if (i != path.size() - 1) {
                int v = path.get(i + 1);
                double dist_trans = getDistance(u, point) + getDistance(point, v);
                if (val <= min(dist_self, dist_trans)) continue;
                //min(d,d2) >= ans
                val = min(dist_self, dist_trans);
                index = i;
                self = dist_self < dist_trans;
            } else if (dist_self < val) {
                index = i;
                self = true;
                val = dist_self;
            }
        }
        //adding the path
        if (self) {
            int u = path.get(index);
            ArrayList<Integer> path1 = getPath(u, point);
            ArrayList<Integer> path2 = getPath(point, u);
            path1.remove(0);
            path2.remove(0);
//            path2.remove(path2.size() - 1);
            path1.addAll(path2);
            path.addAll(index + 1, path1);
        } else {
            int u = path.get(index);
            int v = path.get(index + 1);
            ArrayList<Integer> path1 = getPath(u, point);
            ArrayList<Integer> path2 = getPath(point, v);
            path1.remove(0);
            path2.remove(0);
//            path2.remove(path2.size()-1);
            path1.addAll(path2);
            path.addAll(index + 1, path1);
        }
        return;
    }

    public void decreaseBuyers() {
        buyerAgent--;
        if(buyerAgent > 0)LOGGER.info("DECREASE: " + buyerAgent);
        if(buyerAgent == 0){
            SOLVED = true;
        }
        System.out.println("=============== \n \n");
    }

    public static boolean hasSameSender(ACLMessage msg, ACLMessage msgNew) {
        return msgNew.getSender().getLocalName().equals(msg.getSender().getLocalName());
    }

    public void increaseAgent() {
        buyerAgent++;
        LOGGER.info("INCREASE: " + buyerAgent);

    }
}

class MyFormatter extends SimpleFormatter {
    /* (non-Javadoc)
     * @see java.util.logging.Formatter#format(java.util.logging.LogRecord)
     */
    @Override
    public String format(LogRecord record) {
        StringBuilder sb = new StringBuilder();
        sb.append(record.getMessage()).append('\n');
        return sb.toString();
    }
}

class FloydWarshall {
    static double[][] dist;
    static int[][] next;
    private static int stock = 11;


    public static void main(String[] args) {
        int[][] weights = {{1, 3, 2}, {2, 1, 4}, {2, 3, 3}, {3, 4, 2}, {4, 2, 1}, {4, 5, 4}, {5, 6, 2}, {5, 7, 2}, {3, 8, 5},
                {8, 9, 3}, {9, 10, 1}, {9, 11, 1}, {10, 7, 2}, {11, 7, 2}};
        int numVertices = 11;
        int[][] finalWeights = Arrays.stream(weights)
                .flatMap(arr -> Stream.of(arr, new int[]{arr[1], arr[0], arr[2]}))
                .toArray(int[][]::new);

        floydWarshall(finalWeights, numVertices);
    }

    static void floydWarshall(int[][] weights, int numVertices) {

        dist = new double[numVertices][numVertices];
        for (double[] row : dist)
            Arrays.fill(row, Double.POSITIVE_INFINITY);

        for (int[] w : weights)
            dist[w[0] - 1][w[1] - 1] = w[2];

        next = new int[numVertices][numVertices];
        for (int i = 0; i < next.length; i++) {
            for (int j = 0; j < next.length; j++)
                if (i != j)
                    next[i][j] = j + 1;
        }

        for (int k = 0; k < numVertices; k++)
            for (int i = 0; i < numVertices; i++)
                for (int j = 0; j < numVertices; j++)
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }

        try {
            printResult();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//        printDefiniteResult(1,4);
//        System.out.println(getDistance(1,4));
    }

    static int getStock(){
        return stock;
    }
    static ArrayList<Integer> getPath(int u, int v) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(u);
        do {
            u = next[u - 1][v - 1];
            list.add(u);
        } while (u != v);
        return list;
    }

    static double getDistance(int u, int v) {
        return dist[u - 1][v - 1];
    }

    static void printResult() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("C:\\Programming\\Java\\untitled\\src\\main\\java\\marketyp\\pathes.txt", "UTF-8");
        writer.println("pair     dist    path");
        for (int i = 0; i < next.length; i++) {
            for (int j = 0; j < next.length; j++) {
                if (i != j) {
                    int u = i + 1;
                    int v = j + 1;
                    String path = String.format("%d -> %d    %2d     %s", u, v, (int) dist[i][j], u);
                    do {
                        u = next[u - 1][v - 1];
                        path += " -> " + u;
                    } while (u != v);
                    writer.println(path);
                }
            }
        }
        writer.close();
    }
}
