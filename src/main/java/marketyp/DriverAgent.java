package marketyp;

import jade.Boot;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Math.min;
import static java.lang.Thread.sleep;
import static marketyp.Env.INSTANCE;
import static marketyp.Env.addPointToPath;
import static marketyp.Env.getResponseMessage;
import static marketyp.Env.getDistFromPathToPoint;
//import static marketyp.Env.getDistToPointFromPath;
import static marketyp.Env.getLengthOfPath;
import static marketyp.Env.printMessage;
import static marketyp.Env.printSentMessage;
import static marketyp.FloydWarshall.*;

public class DriverAgent extends Agent {
    private static final Logger LOGGER = Logger.getLogger(DriverAgent.class.getName());
    double coef;
    int sellerHome;
    int sellerJob;
    List<Integer> path = new ArrayList<>();
    List<String> consumers = new ArrayList<String>();
    public boolean printLast = false;
    private boolean waitForAnswer = false;
    private String waitAgent;
    private int agentHome;

    @Override
    protected void setup() {

        extractArguments(getArguments());
        registerTheService();
        //get messages and answer
        addBehaviour(new CyclicBehaviour(this) {


            @Override
            public void action() {
                ACLMessage msg = myAgent.receive();
                if (msg != null && msg.getPerformative() == ACLMessage.PROPOSE && !waitForAnswer) {
                    printMessage(myAgent, msg);
                    answer(msg);
                    LOGGER.log(Level.INFO, myAgent.getLocalName() + ": " + msg.getContent() + " from "
                            + msg.getSender().getLocalName() + " myPath:" + makePathString(path));

                } else if (msg != null && waitForAnswer
                        && msg.getPerformative() == ACLMessage.CONFIRM
                        && msg.getSender().getLocalName().equals(waitAgent)) {

                    addPointToPath(agentHome);
                    consumers.add(waitAgent);
                    coef *= 3;
                    printMessage(myAgent, msg);
                } else if (msg != null) {
                    printMessage(myAgent, msg);
                } else {
                    block();
                }
            }

            public void answer(ACLMessage msg) {
                Iterator<Integer> iter = Arrays.stream(msg.getContent().split(" "))
                        .map(e -> Integer.valueOf(e))
                        .iterator();
                int clientHome = iter.next(), offerPrice = iter.next();
                double dist = getDistFromPathToPoint(clientHome);

                if (offerPrice >= coef * dist) {
                    ACLMessage acceptMessage = getResponseMessage(msg, ACLMessage.ACCEPT_PROPOSAL);
                    printSentMessage(myAgent, msg.getSender().getLocalName(), acceptMessage);
                    myAgent.send(acceptMessage);
                    waitForAnswer = true;
                    waitAgent = msg.getSender().getLocalName();
                    agentHome = clientHome;
                    addBehaviour(new WakerBehaviour(myAgent, 1000) {
                        @Override
                        protected void onWake() {

                            waitForAnswer = false;

                        }
                    });


                }
            }


        });

        //print last
        addBehaviour(new TickerBehaviour(this, 2) {
            @Override
            protected void onTick() {
                if (!printLast && Env.INSTANCE.SOLVED) {

                    LOGGER.log(Level.INFO, getLocalName() + ": END ");
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(getLocalName() + ": " + makePathString(path) + " Delta: "
                            + (-getDistance(path.get(0), path.get(path.size() - 1)) + getLengthOfPath(path)) +
                    " " + consumers);
                    printLast = true;
                }
            }
        });

    }


    private void extractArguments(Object[] args) {
        if (args != null && args.length > 0) {
            sellerHome = Integer.parseInt(args[0].toString());
            sellerJob = Integer.parseInt(args[1].toString());
            coef = Double.parseDouble(args[2].toString());

            path.addAll(getPath(sellerHome, sellerJob));
            LOGGER.log(Level.FINE, getLocalName() + ": sell with coef " + coef);
            System.out.println(getLocalName() + ": seller with params coef " + coef);
        }
    }

    private void registerTheService() {
        System.out.println(getLocalName() + ": registering service of type \"market\" with coef " + coef);
        try {
            DFAgentDescription dfd = new DFAgentDescription();
            dfd.setName(getAID());
            ServiceDescription sd = new ServiceDescription();
            sd.setName(getLocalName());
            sd.setType("market");
            sd.addOntologies("market-ontology");
            sd.addLanguages(FIPANames.ContentLanguage.FIPA_SL);
            dfd.addServices(sd);

            DFService.register(this, dfd);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }
    }


    private String makePathString(List<Integer> path) {
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

    private void addPointToPath(int home) {
        System.out.println("Before path: " + path);
        if (path.indexOf(getStock()) == -1) {
            addStock(path);
        }
        int stockIndex = path.indexOf(getStock());

        if (!(path.lastIndexOf(home) != -1 && path.lastIndexOf(home) >= stockIndex)) {

            int min = Integer.MAX_VALUE;
            int index = stockIndex;
            for (int i = stockIndex; i < path.size() - 1; i++) {
                int distanceFromAdding = getDistanceFromAdding(i, home, path);
                if (min > distanceFromAdding) {
                    min = distanceFromAdding;
                    index = i;
                }
            }

            addPathToPointFromIndex(home, path, index);
        }
        System.out.println("After path: " + path);
    }

    private double getDistFromPathToPoint(int home) {
        ArrayList<Integer> clonePath = cloneList(path);

        if (clonePath.indexOf(getStock()) == -1) {
            addStock(clonePath);
        }
        int stockIndex = clonePath.indexOf(getStock());

        int min = Integer.MAX_VALUE;
        int index = stockIndex;
        for (int i = stockIndex; i < clonePath.size() - 1; i++) {
            int distanceFromAdding = getDistanceFromAdding(i, home, clonePath);
            if (min > distanceFromAdding) {
                min = distanceFromAdding;
                index = i;
            }
        }

        addPathToPointFromIndex(home, clonePath, index);
        return getLengthOfPath(clonePath) - getLengthOfPath(path);
    }

    void addPathToPointFromIndex(int home, List<Integer> clonePath, int index) {
        int u = clonePath.get(index);
        int v = clonePath.get(index + 1);
        ArrayList<Integer> path1 = getPath(u, home);
        ArrayList<Integer> path2 = getPath(home, v);
        path2.remove(0);
        path1.addAll(path2);
        clonePath.remove(index);
        clonePath.remove(index);
        clonePath.addAll(index, path1);
    }

    int getDistanceFromAdding(int i, int home, List<Integer> clonePath) {
        int u = clonePath.get(i);
        int v = clonePath.get(i + 1);
        ArrayList<Integer> path1 = getPath(u, home);
        ArrayList<Integer> path2 = getPath(home, v);
        path2.remove(0);
        int l1 = getLengthOfPath(path1);
        int l2 = getLengthOfPath(path2);
        return l1 + l2;
    }

    private void addStock(List<Integer> list) {
        int stockIndex = list.indexOf(getStock());
        if (stockIndex == -1) {
            int u = list.remove(0);
            int v = list.remove(0);
            ArrayList<Integer> pathToStock = getPath(u, getStock());
            ArrayList<Integer> pathFromStockToNext = getPath(getStock(), v);
            pathFromStockToNext.remove(0);
            pathToStock.addAll(pathFromStockToNext);

            list.addAll(0, pathToStock);
        }
    }


    public static ArrayList<Integer> cloneList(List<Integer> list) {
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ans.add(list.get(i));
        }
        return ans;
    }


    public static void main(String[] args) throws InterruptedException, IOException {
        LOGGER.setLevel(Level.SEVERE);
        FileHandler fh;
        fh = new FileHandler("C:\\Programming\\Java\\untitled\\src\\main\\java\\marketyp\\logs");
        LOGGER.addHandler(fh);
        fh.setFormatter(new MyFormatter());

        Env env = INSTANCE;
        env.main(null);

        Boot.main(("-agents " +
                "Driver1:marketyp.DriverAgent(1,2,16);" +
                "Driver2:marketyp.DriverAgent(2,3,17);" +
                "Driver3:marketyp.DriverAgent(3,9,18);" +
                "Driver4:marketyp.DriverAgent(4,6,17);" +
                "Driver5:marketyp.DriverAgent(5,8,19);" +
                "Driver6:marketyp.DriverAgent(6,5,24);"+
                "").split(" "));
        sleep(1000);
        Boot.main(("-container " +
                "Consumer1:marketyp.ConsumerAgent(9,70);" +
                "Consumer2:marketyp.ConsumerAgent(11,30);" +
                "Consumer3:marketyp.ConsumerAgent(4,100)"+
                "").split(" "));
    }
}

