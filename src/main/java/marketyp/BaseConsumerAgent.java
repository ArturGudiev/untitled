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
import sun.management.resources.agent;

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

    protected static int cidCnt = 0;
    String cidBase ;

    String genCID()
    {
        if (cidBase==null) {
            cidBase = getLocalName() + hashCode() +
                    System.currentTimeMillis()%10000 + "_";
        }
        return  cidBase + (cidCnt++);
    }

    public void printMessage(ACLMessage msg) {
        String content = msg.getContent() ;
        content = content == null ? "" : content;
        System.out.println(getLocalName() + ": GOT message "+perf(msg)+" from " + msg.getSender().getLocalName() + " " + content + " " + msg.getConversationId());
    }

    public void printSentMessage(String agentName, ACLMessage msg) {
        String content = msg.getContent() == null ? "" : msg.getContent();
        System.out.println(this.getLocalName() +  ": SENT message "+ perf(msg) +" to " + agentName + " " + content);
    }

    public static String perf(ACLMessage msg){
        return ACLMessage.getAllPerformativeNames()[msg.getPerformative()];
    }

    public static ACLMessage getResponseMessage(ACLMessage msg, int messageType) {
        ACLMessage ans = new ACLMessage(messageType);
        ans.addReceiver(new AID(msg.getSender().getLocalName(), AID.ISLOCALNAME));
        ans.setLanguage("English");
        ans.setOntology("Connection");
        return ans;
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

    public boolean checkForAcceptMessage(ACLMessage msg){
        if(msg == null){
            return false;
        }
        if (this.needToBuy && this.waitForAccept && msg.getPerformative() == ACLMessage.ACCEPT_PROPOSAL) {
            printMessage(msg);
            LOGGER.log(Level.INFO, this.getLocalName() + ": get ACCEPT from " + msg.getSender().getLocalName());
            this.needToBuy = false;
            ACLMessage confirmMessage = getResponseMessage(msg, ACLMessage.CONFIRM);
            printSentMessage(msg.getSender().getLocalName(), confirmMessage);
            this.send(confirmMessage);
            Env.INSTANCE.decreaseBuyers();
            return true;
        }
        return false;
    }

    private void sendOfferToDefiniteService(DFAgentDescription dfd) {
        Iterator it = dfd.getAllServices();
        ServiceDescription sd = (ServiceDescription) it.next();
        if (sd.getType().equals("market")) {
            sendOffer(sd.getName(), genCID());
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
//        printSentMessage(this, serviceName, msg);
    }

    public void tryToBuy() {
        DFAgentDescription[] services = getServices(this);
        try {
            waitForAccept = true;
            System.out.println(this.getLocalName() + ": SENT message PROPOSE " +  this.home + " " +  this.offerPrice);
            for(DFAgentDescription dfd : services){
                sendOfferToDefiniteService(dfd);
            }
            // remove listening and increase offer
            this.addBehaviour(new WakerBehaviour(this, timeout) {
                @Override
                protected void onWake() {
                    waitForAccept = false;
                    if(needToBuy){
                        offerPrice +=  needToBuy ? 50 : 0;
                    }
                }
            });
        }catch (Exception e){e.printStackTrace();}
    }


}
