package marketyp;

import jade.Boot;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
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
import static marketyp.Env.getDistFromPathToPoint;
//import static marketyp.Env.getDistToPointFromPath;
import static marketyp.FloydWarshall.*;

public class SellerAgent extends Agent {
    private static final Logger LOGGER = Logger.getLogger(SellerAgent.class.getName());
    double coef;
    int sellerHome;
    int sellerJob;
    List<Integer> path = new ArrayList<>();
    public boolean printLast = false;

    @Override
    protected void setup() {

        Object[] args = getArguments();
        if (args != null && args.length > 0) {
            sellerHome = Integer.parseInt(args[0].toString());
            sellerJob = Integer.parseInt(args[1].toString());
            coef = Double.parseDouble(args[2].toString());
//            path.add(sellerHome);
//            path.add(sellerJob);
            path.addAll(getPath(sellerHome, sellerJob));
            LOGGER.log(Level.FINE, getLocalName() + ": sell with coef " + coef);
            System.out.println(getLocalName() + ": seller with params coef " + coef);
        }

        // Register the service
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

        addBehaviour(new CyclicBehaviour(this) {

            public void  answer(ACLMessage msg) {
                Iterator<Integer> iter = Arrays.stream(msg.getContent().split(" "))
                        .map(e -> Integer.valueOf(e))
                        .iterator();
                int clientHome = iter.next(), clientStock = FloydWarshall.getStock(), offerPrice = iter.next();
                double dist = getDistFromPathesToPoints(clientHome, clientStock);

                ACLMessage ans;
                if (offerPrice < coef * dist) {
                    ans = new ACLMessage(ACLMessage.REJECT_PROPOSAL);
                } else {
                    ans = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
                    addPointsToPath(clientHome, clientStock);
                    coef *= 3;
                }

                ans.addReceiver(new AID(msg.getSender().getLocalName(), AID.ISLOCALNAME));
                ans.setLanguage("English");
                ans.setOntology("Connection");
                myAgent.send(ans);
            }

            @Override
            public void action() {
                ACLMessage msg = myAgent.receive();
                if (msg != null) {
                    answer(msg);
                    LOGGER.log(Level.INFO, myAgent.getLocalName() + ": " + msg.getContent() + " from "
                            + msg.getSender().getLocalName() + " myPath:" + makePathString(path));

                } else {
                    block();
                }
            }
        });
        //print last
        addBehaviour(new TickerBehaviour(this, 2) {
            @Override
            protected void onTick() {
                if(!printLast && Env.INSTANCE.SOLVED) {

                    LOGGER.log(Level.INFO, getLocalName() + ": END " );
                    System.out.println(getLocalName() + ": " + makePathString(path) + " ("
                            + FloydWarshall.getDistance(path.get(0), path.get(path.size()-1)) + ", "
                    + Env.getLengthOfPath(path) + ")");
                    printLast = true;
                }
            }
        });
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

    private void addPointsToPath(int home, int stock) {

        int homeIndex = path.indexOf(home);
        int stockIndex = path.indexOf(stock);
        if (homeIndex == -1 && stockIndex == -1) {
//            LOGGER.log(Level.SEVERE, getLocalName() + "addPointsToPath1");
            addPointToPath(path, stock);
            int iStock = path.indexOf(stock);
            addPointToPath(path, home, iStock, path.size());
        } else if (stockIndex != -1 && homeIndex == -1) {
//            LOGGER.log(Level.SEVERE, getLocalName() + "addPointsToPath2");

            int iStock = path.indexOf(stock);
            addPointToPath(path, home, iStock, path.size());
        } else if (
                (stockIndex == -1 && homeIndex != -1)
                        ||
                        (stockIndex != -1 && homeIndex != -1 && homeIndex < stockIndex)
                ) {

//            LOGGER.log(Level.SEVERE, getLocalName() + "addPointsToPath3");
            addPointToPath(path, home, 0, homeIndex);
        }
        return;

    }


    private double getDistFromPathesToPoints(int home, int stock) {
        ArrayList<Integer> clonePath = cloneList(path);
        double dist = 0;
        int homeIndex = clonePath.indexOf(home);
        int stockIndex = clonePath.indexOf(stock);
        if (homeIndex == -1 && stockIndex == -1) {
            dist = getDistFromPathToPoint(clonePath, stock, 0, clonePath.size());
            addPointToPath(clonePath, stock);
            int iStock = clonePath.indexOf(stock);
            dist += getDistFromPathToPoint(clonePath, home, iStock, clonePath.size());
        } else if (stockIndex != -1 && homeIndex == -1) {
            int iStock = clonePath.indexOf(stock);
            dist += getDistFromPathToPoint(clonePath, home, iStock, clonePath.size());
        } else if (
                (stockIndex == -1 && homeIndex != -1)
                        ||
                        (stockIndex != -1 && homeIndex != -1 && homeIndex < stockIndex)
                ) {
            dist += getDistFromPathToPoint(clonePath, home, 0, homeIndex);
        } else {
            dist = 0;
        }

        return dist;
    }


    public static ArrayList<Integer> cloneList(List<Integer> list) {
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ans.add(list.get(i));
        }
        return ans;
    }


    public static void main(String[] args) throws InterruptedException, IOException {
        LOGGER.setLevel(Level.INFO);
        FileHandler fh;
        fh = new FileHandler("C:\\Programming\\Java\\untitled\\src\\main\\java\\marketyp\\logs");
        LOGGER.addHandler(fh);
        fh.setFormatter(new MyFormatter());

        Env env = INSTANCE;
        env.main(null);

        Boot.main(("-agents " +
                "Seller1:marketyp.SellerAgent(1,2,16);" +
                "Seller2:marketyp.SellerAgent(2,3,17);" +
                "Seller3:marketyp.SellerAgent(3,9,18);" +
                "Seller4:marketyp.SellerAgent(4,6,17);" +
                "Seller5:marketyp.SellerAgent(5,8,19);" +
                "Seller6:marketyp.SellerAgent(6,5,24);").split(" "));
//        sleep(1000);
        Boot.main(("-container " +
                "Buyer1:marketyp.BuyerAgent(9,70);" +
                "Buyer2:marketyp.BuyerAgent(11,30);" +
                "Buyer3:marketyp.BuyerAgent(4,100)").split(" "));
    }
}

