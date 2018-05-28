package marketyp;

import jade.Boot;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.util.Formatter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static java.lang.Thread.sleep;
import static marketyp.Env.INSTANCE;

public class SellerAgent extends Agent {
    private static final Logger LOGGER = Logger.getLogger(SellerAgent.class.getName());
    int price = -1;
    double coef;

    @Override
    protected void setup() {

        Object[] args = getArguments();
        if (args != null && args.length > 0) {
//            price = Integer.parseInt(args[0].toString());
            coef = Double.parseDouble(args[0].toString());
            LOGGER.log(Level.FINE, getLocalName() + ": sell with price " + price);
//            System.out.println(getLocalName() + ": sell with price " + price);
        }

        // Register the service
        System.out.println(getLocalName() + ": registering service of type \"market\" with price " + price);
        try {
            DFAgentDescription dfd = new DFAgentDescription();
            dfd.setName(getAID());
            ServiceDescription sd = new ServiceDescription();
            sd.setName(getLocalName());
            sd.setType("market");
            // Agents that want to use this service need to "know" the weather-forecast-ontology
            sd.addOntologies("market-ontology");
            // Agents that want to use this service need to "speak" the FIPA-SL language
            sd.addLanguages(FIPANames.ContentLanguage.FIPA_SL);
            dfd.addServices(sd);

            DFService.register(this, dfd);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }

        addBehaviour(new CyclicBehaviour(this) {

            public void answer(int offerPrice, String sender) {
                ACLMessage ans;
                if (offerPrice < coef * INSTANCE.pathes.get(0)) {
                    ans = new ACLMessage(ACLMessage.REJECT_PROPOSAL);
                } else {
                    ans = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
                }
                ans.addReceiver(new AID(sender, AID.ISLOCALNAME));
                ans.setLanguage("English");
                ans.setOntology("Connection");
                myAgent.send(ans);
//                System.out.println("sent to " + sender + " " + ACLMessage.getPerformative(ans.getPerformative()));
            }

            @Override
            public void action() {
                ACLMessage msg = myAgent.receive();
                if (msg != null) {
                    String content = msg.getContent();
                       LOGGER.log(Level.INFO, myAgent.getLocalName() + ": " + msg.getContent() + " from " + msg.getSender().getLocalName());
                    int offerPrice = Integer.valueOf(msg.getContent());
                    answer(offerPrice, msg.getSender().getLocalName());

                        ACLMessage ans = new ACLMessage(ACLMessage.INFORM);
//                        ans.addReceiver(new AID(msg.getSender().getLocalName(), AID.ISLOCALNAME));
//                        ans.addReceiver(new AID(msg.getSender().getLocalName(), AID.ISLOCALNAME));
//                        ans.setLanguage("English");
//                        ans.setOntology("Connection");
                        ans.setContent("pong");
                        myAgent.send(ans);

                }else {
                    block();
                }
            }
        });
    }

    public static void main(String[] args) throws InterruptedException, IOException {
//        LOGGER.info("Logger Name: " + LOGGER.getName());

//        Handler consoleHandler = new ConsoleHandler();
//        LOGGER.addHandler(consoleHandler);
//        consoleHandler.setLevel(Level.INFO);
        LOGGER.setLevel(Level.INFO);
//        LOGGER.setLevel(Level.OFF);
        FileHandler fh;
        fh = new FileHandler("C:\\Programming\\Java\\untitled\\src\\main\\java\\marketyp\\logs");
        LOGGER.addHandler(fh);
        fh.setFormatter(new MyFormatter());
//        LOGGER.config("Configuration done.");
//        LOGGER.log(Level.FINE, "Finer logged");
//        LOGGER.finer("Finest example on LOGGER handler completed.");
//        LOGGER.log(Level.INFO, "Hello World!");
//        LOGGER.setLevel(Level.ALL);

//        System.out.println(l);
//        System.out.println(Arrays.toString(l.toArray()));;
//        System.out.println(Arrays.toString(l.toArray()));;
//        System.out.println(Arrays.toString(a));

        Env env = INSTANCE;

        env.pathes.add(50);
        env.pathes.add(10);
        env.pathes.add(20);
        env.pathes.add(30);
        env.pathes.add(40);

        Boot.main(("-agents Seller1:marketyp.SellerAgent(100);Seller2:marketyp.SellerAgent(1.25);" +
                "Seller3:marketyp.SellerAgent(1.5);" +
                "Seller4:marketyp.SellerAgent(2.7);" +
                "Seller5:marketyp.SellerAgent(3.4);" +
                "Seller6:marketyp.SellerAgent(1.8);").split(" "));
        sleep(1000);
        Boot.main(("-container Buyer1:marketyp.BuyerAgent(70);" +
                "Buyer2:marketyp.BuyerAgent(30)").split(" "));
    }
}

