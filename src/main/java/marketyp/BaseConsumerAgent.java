package marketyp;


import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.util.leap.Iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import static marketyp.Env.timeout;

public class BaseConsumerAgent extends Agent {
    boolean waitForAccept = false;
    int home;
    int offerPrice = -1;
    boolean needToBuy = true;
    List<String> deliversList = new ArrayList<String>();

    protected static int cidCnt = 0;
    String cidBase;

    String genCID() {
        if (cidBase == null) {
            cidBase = getLocalName() + hashCode() +
                    System.currentTimeMillis() % 10000 + "_";
        }
        return cidBase + (cidCnt++);
    }

    public void printMessage(ACLMessage msg) {
        String content = msg.getContent();
        content = content == null ? "" : content;
        System.out.println(getLocalName() + ": GOT message " + perf(msg) + " from " + msg.getSender().getLocalName() + " " + content + " " + msg.getConversationId());
    }

    public void printSentMessage(String agentName, ACLMessage msg) {
        String content = msg.getContent() == null ? "" : msg.getContent();
        System.out.println(this.getLocalName() + ": SENT message " + perf(msg) + " to " + agentName + " " + content);
    }

    public static String perf(ACLMessage msg) {
        return ACLMessage.getAllPerformativeNames()[msg.getPerformative()];
    }

    public static ACLMessage getResponseMessage(ACLMessage msg, int messageType) {
        ACLMessage ans = new ACLMessage(messageType);
        ans.addReceiver(new AID(msg.getSender().getLocalName(), AID.ISLOCALNAME));
        ans.setConversationId(msg.getConversationId());
        ans.setLanguage("English");
        ans.setOntology("Connection");
        return ans;
    }

    public static DFAgentDescription[] getServices(Agent agent) {
        try {
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
        } catch (Exception e) {
            return null;
        }
    }


    private void sendOfferToDefiniteService(DFAgentDescription dfd, String cid) {
        Iterator it = dfd.getAllServices();
        ServiceDescription sd = (ServiceDescription) it.next();
        if (sd.getType().equals("market")) {
            sendOffer(sd.getName(), cid);
        }
    }

    private void sendOffer(String serviceName, String cid) {
        ACLMessage msg = new ACLMessage(ACLMessage.PROPOSE);
        msg.addReceiver(new AID(serviceName, AID.ISLOCALNAME));
        msg.setLanguage("Engilsh");
        msg.setOntology("market-ontology");
        msg.setContent(home + " " + offerPrice);
        msg.setConversationId(cid);
        this.send(msg);
        printSentMessage(serviceName, msg);
    }

    public void tryToBuy(String cid) {
        DFAgentDescription[] services = getServices(this);
        try {
            System.out.println(this.getLocalName() + ": SENT message PROPOSE " + this.home + " " + this.offerPrice);
            for (DFAgentDescription dfd : services) {
                sendOfferToDefiniteService(dfd, cid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Behaviour getConsumerBehaviour(){
        return new TickerBehaviour(this, timeout) {
            @Override
            protected void onTick() {
                if (!needToBuy) {
                    return;
                }
                String cid = genCID();
                MessageTemplate template = MessageTemplate.and(
                        MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL),
                        MessageTemplate.MatchConversationId(cid)
                );
                SequentialBehaviour seq = new SequentialBehaviour();
                seq.addSubBehaviour(new OneShotBehaviour(myAgent) {
                    @Override
                    public void action() {
                        tryToBuy(cid);
                    }
                });
                seq.addSubBehaviour(new MyReceiverBehaviour(myAgent, timeout, template) {
                    public void handle(ACLMessage msg) {
                        if (msg != null) {
                            printMessage(msg);
                            LOGGER.log(Level.INFO, getLocalName() + ": get ACCEPT from " + msg.getSender().getLocalName());
                            deliversList = new ArrayList<>(Arrays.asList(msg.getContent().split(" ")));
                            needToBuy = false;
                            ACLMessage confirmMessage = getResponseMessage(msg, ACLMessage.CONFIRM);
                            send(confirmMessage);
                            Env.INSTANCE.decreaseBuyers();
                            checkOut();
//                            printSentMessage(msg.getSender().getLocalName(), confirmMessage);
                        } else if (finished) {
                            offerPrice += needToBuy ? 50 : 0;
                        }
                    }
                });
                addBehaviour(seq);
            }
        };
    }

    protected void checkIn() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID("Stat", AID.ISLOCALNAME));
        msg.setLanguage("English");
        msg.setOntology("Connection");
        send(msg);
        return ;
    }

    protected void checkOut() {
        ACLMessage msg = new ACLMessage(ACLMessage.CANCEL);
        msg.addReceiver(new AID("Stat", AID.ISLOCALNAME));
        msg.setLanguage("English");
        msg.setOntology("Connection");
        send(msg);
        return ;
    }

}
