package marketyp;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.util.leap.Iterator;
import javafx.util.Pair;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    public static volatile int consumerAgentsNumber = 0;
    public boolean SOLVED = false;
    public static int timeout = 100;
    public static int getLengthOfPath(List<Integer> path) {
        int sum = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            sum += getDistance(path.get(i), path.get(i + 1));
        }
        return sum;
    }

    public static ArrayList<Pair<String, String>> orders = new ArrayList<Pair<String, String>>();
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

    public static ACLMessage getResponseMessage(ACLMessage msg, int messageType) {
        ACLMessage ans = new ACLMessage(messageType);
        ans.addReceiver(new AID(msg.getSender().getLocalName(), AID.ISLOCALNAME));
        ans.setLanguage("English");
        ans.setOntology("Connection");
        return ans;
    }

    public static void printMessage(Agent agent, ACLMessage msg) {
        String content = msg.getContent() ;
        content = content == null ? "" : content;
//        System.out.println(agent.getLocalName() + ": GOT message "+perf(msg)+" from " + msg.getSender().getLocalName() + " " + content);
    }

    public static String perf(ACLMessage msg){
        return ACLMessage.getAllPerformativeNames()[msg.getPerformative()];
    }

    public static void printSentMessage(Agent agent, String agentName, ACLMessage msg) {
        String content = msg.getContent() == null ? "" : msg.getContent();
        System.out.println(agent.getLocalName() +  ": SENT message "+ perf(msg) +" to " + agentName + " " + content);
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


    public static DFAgentDescription[] getServices(Agent agent) {
        try{
            DFAgentDescription template = new DFAgentDescription();

        ServiceDescription templateSd = new ServiceDescription();
        templateSd.setType("market");
        template.addServices(templateSd);

        SearchConstraints sc = new SearchConstraints();
        // We want to receive 10 results at most
        sc.setMaxResults(new Long(100));

        DFAgentDescription[] results = DFService.search(agent, template, sc);
        List<DFAgentDescription> l = Arrays.asList(results);
        Collections.shuffle(l);

        return l.toArray(results);
        }catch (Exception e){
            return null;
        }
    }

    public static boolean checkForAcceptMessage(BaseConsumerAgent agent, ACLMessage msg){
        if(msg == null){
            return false;
        }
        if (agent.needToBuy && agent.waitForAccept && msg.getPerformative() == ACLMessage.ACCEPT_PROPOSAL) {
            printMessage(agent, msg);
            LOGGER.log(Level.INFO, agent.getLocalName() + ": get ACCEPT from " + msg.getSender().getLocalName());
            agent.needToBuy = false;
            ACLMessage confirmMessage = getResponseMessage(msg, ACLMessage.CONFIRM);
            printSentMessage(agent, msg.getSender().getLocalName(), confirmMessage);
            agent.send(confirmMessage);
            Env.INSTANCE.decreaseBuyers();
            return true;
        }
        return false;
    }
    private static void sendOfferToDefiniteService(DFAgentDescription dfd, BaseConsumerAgent agent) {
        Iterator it = dfd.getAllServices();
        ServiceDescription sd = (ServiceDescription) it.next();
        if (sd.getType().equals("market")) {
            sendOffer(sd.getName(), agent);
        }
    }


    private static void sendOffer(String serviceName, BaseConsumerAgent agent) {
        ACLMessage msg = new ACLMessage(ACLMessage.PROPOSE);
        msg.addReceiver(new AID(serviceName, AID.ISLOCALNAME));
        msg.setLanguage("Engilsh");
        msg.setOntology("market-ontology");
        msg.setContent(agent.home + " " + agent.offerPrice );
        agent.send(msg);
//        printSentMessage(this, serviceName, msg);
    }

    public static void tryToBuy(BaseConsumerAgent agent) {
        DFAgentDescription[] services = getServices(agent);
        try {
            agent.waitForAccept = true;
            System.out.println(agent.getLocalName() + ": SENT message PROPOSE " +  agent.home + " " +  agent.offerPrice);
            for(DFAgentDescription dfd : services){
                sendOfferToDefiniteService(dfd, agent);
            }
            // remove listening and increase offer
            agent.addBehaviour(new WakerBehaviour(agent, timeout) {
                @Override
                protected void onWake() {
                    agent.waitForAccept = false;
                    if(agent.needToBuy){
                        agent.offerPrice +=  agent.needToBuy ? 50 : 0;
                    }
                }
            });
        }catch (Exception e){e.printStackTrace();}
    }


    public void decreaseBuyers() {
        consumerAgentsNumber--;
        if(consumerAgentsNumber > 0)LOGGER.info("DECREASE: " + consumerAgentsNumber);
        if(consumerAgentsNumber == 0){
            SOLVED = true;
            System.out.println("orders = " + orders);
        }
//        System.out.println("=============== \n \n");
    }

    public static String makePathString(List<Integer> path) {
        String ans = "";
        int last = -1;
        for (int i = 0; i < path.size(); i++) {
            int element = path.get(i);
            if (ans != "" && element != last) {
                ans += " -> ";
            }
            if (element != last) {
                ans += element;
                last = element;
            }
        }

        return ans;
    }


    public static boolean hasSameSender(ACLMessage msg, ACLMessage msgNew) {
        return msgNew.getSender().getLocalName().equals(msg.getSender().getLocalName());
    }

    public static void increaseConsumers() {
        consumerAgentsNumber++;
        System.out.println("consumerAgentsNumber = " + consumerAgentsNumber);
        LOGGER.info("INCREASE: " + consumerAgentsNumber);

    }

    public static void addOrder(String driver, String consumer) {
        orders.add(new Pair<String, String>(driver, consumer));
    }

    public static boolean checkForLoop(String driver, String consumer) {
        if(containsElement(consumer, driver)){
            return true;
        }
        return false;
    }

    private static boolean containsElement(String parent, String element) {
        if (parent.equals(element)) {
            return true;
        }
        for (int i = 0; i < orders.size(); i++) {
            Pair<String, String> order = orders.get(i);
            if(parent.equals(order.getKey()) && containsElement(order.getValue(), element)){
                return true;
            }
        }
        return false;
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
