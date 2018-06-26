package marketyp;

import jade.Boot;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.lang.Math.min;
import static java.lang.Thread.sleep;
//import static marketyp.Env.getDistToPointFromPath;
import static marketyp.Env.*;
import static marketyp.FloydWarshall.*;

public class DriverAgent extends BaseConsumerAgent {
    private static final Logger LOGGER = Logger.getLogger(DriverAgent.class.getName());

    double coef;
    List<Integer> path = new ArrayList<>();
    List<String> consumers = new ArrayList<String>();
    public boolean printLast = false;

    @Override
    protected void setup() {

        extractArguments(getArguments());
        registerService();
        driverCheckIn();

        if (needToBuy) {
            addBehaviour(getConsumerBehaviour());
        }

        //get messages and answer
        addBehaviour(new CyclicBehaviour(this) {
            @Override
            public void action() {
                ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.PROPOSE));
                if (msg != null) {
                    String cid = msg.getConversationId();
                    Iterator<Integer> iter = Arrays.stream(msg.getContent().split(" "))
                            .map(e -> Integer.valueOf(e))
                            .iterator();
                    int clientHome = iter.next(), theOfferPrice = iter.next();

                    SequentialBehaviour seq = new SequentialBehaviour();
                    seq.addSubBehaviour(getAnswerOnProposeBehaviour(msg, cid, clientHome, theOfferPrice));
                    seq.addSubBehaviour(getConfirmReceiverBehaviour(cid, msg.getSender().getLocalName(), clientHome));
                    addBehaviour(seq);
                } else {
                    block();
                }
            }

            public Behaviour getAnswerOnProposeBehaviour(ACLMessage msg, String cid, int clientHome, int theOfferPrice) {
                return new OneShotBehaviour(myAgent) {
                    @Override
                    public void action() {
                        String clientName = msg.getSender().getLocalName();
                        printMessage(msg);
                        double dist = getDistFromPathToPoint(clientHome);
                        boolean hasLoops = deliversList.contains(clientName);

                        if (theOfferPrice >= coef * dist && !hasLoops) {
                            ACLMessage acceptMessage = getResponseMessage(msg, ACLMessage.ACCEPT_PROPOSAL);
                            printSentMessage(clientName, acceptMessage);
                            acceptMessage.setConversationId(cid);

                            ArrayList<String> clonedDeliverList = cloneList(deliversList);
                            clonedDeliverList.add(myAgent.getLocalName());
                            acceptMessage.setContent(
                                    clonedDeliverList.stream().
                                            map(Object::toString).
                                            collect(Collectors.joining(" ")).toString());

                            myAgent.send(acceptMessage);
                        }
                        LOGGER.log(Level.INFO, myAgent.getLocalName() + ": " + msg.getContent() + " from "
                                + msg.getSender().getLocalName() + " myPath:" + makePathString(path));
                    }
                };

            }
        });
        addBehaviour(new CyclicBehaviour(this) {
            @Override
            public void action() {
                ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
                if (msg != null && !printLast) {
                    printLast = true;
                    double delta = (-getDistance(path.get(0), path.get(path.size() - 1)) + getLengthOfPath(path));
                    System.out.println(getLocalName() + " for Stat: " + makePathString(path) + " Delta: "
                            + delta +
                            " " + consumers);

                    ACLMessage ans = new ACLMessage(ACLMessage.AGREE);
                    ans.addReceiver(new AID("Stat", AID.ISLOCALNAME));
                    ans.setLanguage("English");
                    ans.setOntology("Connection");
                    ans.setContent(String.valueOf((int) delta));
                    send(ans);
                } else {
                    block();
                }
            }
        });
        //print last
//        addBehaviour(new TickerBehaviour(this, 2) {
//            @Override
//            protected void onTick() {
//                if (!printLast && Env.INSTANCE.SOLVED) {
//                    try {
//                        sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    LOGGER.log(Level.INFO, getLocalName() + ": END ");
//                    try {
//                        sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(getLocalName() + ": " + makePathString(path) + " Delta: "
//                            + (-getDistance(path.get(0), path.get(path.size() - 1)) + getLengthOfPath(path)) +
//                    " " + consumers);
//                    printLast = true;
//                }
//            }
//        });
    }


    private MyReceiverBehaviour getConfirmReceiverBehaviour(String cid, String consumerAgent, int consumerHome) {
        MessageTemplate template = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.CONFIRM),
                MessageTemplate.MatchConversationId(cid)
        );
        return new MyReceiverBehaviour(this, timeout, template) {
            public void handle(ACLMessage msg) {
                if (msg != null) {
                    addPointToPath(consumerHome);
                    consumers.add(consumerAgent);
                    coef *= 3;
                    printMessage(msg);
                }
            }
        };
    }

    private void extractArguments(Object[] args) {
        if (args != null && args.length > 0) {
            needToBuy = false;
            home = Integer.parseInt(args[0].toString());
            int sellerJob = Integer.parseInt(args[1].toString());
            coef = Double.parseDouble(args[2].toString());
            if (args.length > 3) {
                offerPrice = Integer.parseInt(args[3].toString());
                needToBuy = true;
                Env.increaseConsumers();
                checkIn();
//                System.out.println(getLocalName() + ":::::: INCREASE CONSUMERS " + consumerAgentsNumber);
            }
            path.addAll(getPath(home, sellerJob));
            LOGGER.log(Level.FINE, getLocalName() + ": sell with coef " + coef);
            System.out.println(getLocalName() + ": COEF " + coef + " " + (offerPrice != -1 ? " OFFER PRICE " + offerPrice : ""));
        }
    }

    private void registerService() {
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

    public static <T> ArrayList<T> cloneList(List<T> list) {
        ArrayList<T> ans = new ArrayList<T>();
        for (int i = 0; i < list.size(); i++) {
            ans.add(list.get(i));
        }
        return ans;
    }

    protected void driverCheckIn() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID("Stat", AID.ISLOCALNAME));
        msg.setLanguage("English");
        msg.setOntology("Connection");
        msg.setContent("driver");
        send(msg);
        return;
    }
//    public static ArrayList<String> cloneList(List<String> list) {
//        ArrayList<String> ans = new ArrayList<>();
//        for (int i = 0; i < list.size(); i++) {
//            ans.add(list.get(i));
//        }
//        return ans;
//    }

    public static void main(String[] args) throws InterruptedException, IOException {
        LOGGER.setLevel(Level.SEVERE);
        FileHandler fh;
        fh = new FileHandler("C:\\Programming\\Java\\untitled\\src\\main\\java\\marketyp\\logs");
        LOGGER.addHandler(fh);
        fh.setFormatter(new MyFormatter());

        Env env = INSTANCE;
        env.main(null);


        Boot.main(("-agents " +
                "Stat:marketyp.StatisticAgent();" +
                "Consumer1:marketyp.ConsumerAgent(9,70);" +
                "Consumer2:marketyp.ConsumerAgent(8,30);" +
                "Consumer3:marketyp.ConsumerAgent(4,100)" +
                "").split(" "));
        sleep(1000);
        Boot.main(("-container " +
                "Driver1:marketyp.DriverAgent(1,2,16,60);" +
                "Driver2:marketyp.DriverAgent(2,3,17);" +
                "Driver3:marketyp.DriverAgent(3,9,18);" +
                "Driver4:marketyp.DriverAgent(4,6,17,70);" +
                "Driver5:marketyp.DriverAgent(5,8,19);" +
                "Driver6:marketyp.DriverAgent(6,5,24);" +

                "").split(" "));

    }
}

