package marketyp;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.util.leap.Iterator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;
import static marketyp.Env.getResponseMessage;
import static marketyp.Env.printMessage;
import static marketyp.Env.printSentMessage;
import static marketyp.Env.timeout;

public class ConsumerAgent extends Agent {
    private static final Logger LOGGER = Logger.getLogger(DriverAgent.class.getName());
    int offerPrice = -1;
    boolean needToBuy = true;
    boolean waitForAnswer = false;

    DFAgentDescription[] services;
    int home;
    private DFAgentDescription[] getServices() throws FIPAException {
        DFAgentDescription template = new DFAgentDescription();
        ServiceDescription templateSd = new ServiceDescription();
        templateSd.setType("market");
        template.addServices(templateSd);

        SearchConstraints sc = new SearchConstraints();
        // We want to receive 10 results at most
        sc.setMaxResults(new Long(100));

        DFAgentDescription[] results = DFService.search(this, template, sc);
        List<DFAgentDescription> l = Arrays.asList(results);
        Collections.shuffle(l);
        return l.toArray(results);
    }

    private void extractArgumentsAndServices() {
        try {
            Object[] args = getArguments();
            if (args != null && args.length > 0) {
                home = Integer.parseInt(args[0].toString());
                offerPrice = Integer.parseInt(args[1].toString());
            }
            LOGGER.log(Level.SEVERE, "Initialize " + getLocalName() + " with parameters " + home + " " +
                    offerPrice);
            services = getServices();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void sendOffer(String serviceName) {
        ACLMessage msg = new ACLMessage(ACLMessage.PROPOSE);
        msg.addReceiver(new AID(serviceName, AID.ISLOCALNAME));
        msg.setLanguage("Engilsh");
        msg.setOntology("market-ontology");
        msg.setContent(home + " " + offerPrice );
        this.send(msg);
//        printSentMessage(this, serviceName, msg);
    }


    private void tryToBuy() {
        try {
            waitForAnswer = true;
            System.out.println(this.getLocalName() + ": SENT message PROPOSE " +  home + " " +  offerPrice);
            for(DFAgentDescription dfd : services){
                sendOfferToDefiniteService(dfd);
            }
            addBehaviour(new WakerBehaviour(this, timeout) {
                @Override
                protected void onWake() {
                    ACLMessage msg = myAgent.receive();
                    if (msg != null) {
                        if (msg.getPerformative() == ACLMessage.ACCEPT_PROPOSAL) {
                            printMessage(myAgent, msg);
                            LOGGER.log(Level.INFO, getLocalName() + ": get ACCEPT from " + msg.getSender().getLocalName());
                            needToBuy = false;
                            ACLMessage acceptMessage = getResponseMessage(msg, ACLMessage.CONFIRM);
                            printSentMessage(myAgent, msg.getSender().getLocalName(), acceptMessage);
                            myAgent.send(acceptMessage);
                            Env.INSTANCE.decreaseBuyers();
                        }
                    }
                    waitForAnswer = false;
                    offerPrice +=  needToBuy ? 50 : 0;
                }
            });


        }catch (Exception e){e.printStackTrace();}
    }

    private void sendOfferToDefiniteService(DFAgentDescription dfd) {
        Iterator it = dfd.getAllServices();
        ServiceDescription sd = (ServiceDescription) it.next();
        if (sd.getType().equals("market")) {
            sendOffer(sd.getName());
        }
    }

    private void incServiceIndex() {
    }

    @Override
    protected void setup() {
        extractArgumentsAndServices();
        Env.increaseAgent();
        addBehaviour(new TickerBehaviour(this, timeout) {
            @Override
            protected void onTick() {
                if(needToBuy && !waitForAnswer) {
                    tryToBuy();
                }
            }
        });

//        addBehaviour(new CyclicBehaviour(this) {
//
//            @Override
//            public void action() {
//                System.out.println("GETTING MESSAGES");
//                ACLMessage msg = myAgent.receive();
//                if (msg != null) {
//                    System.out.println(myAgent.getLocalName() + ": " + ACLMessage.getPerformative(msg.getPerformative()) + " from " + msg.getSender().getLocalName());
//                    System.out.println(msg.getContent());
//                }else{
//                    block();
//                }
//            }
//        });
    }


}
