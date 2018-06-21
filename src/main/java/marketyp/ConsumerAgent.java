package marketyp;

import com.sun.org.apache.xerces.internal.impl.dv.dtd.ENTITYDatatypeValidator;
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

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import static java.lang.Thread.sleep;
import static marketyp.Env.getResponseMessage;
import static marketyp.Env.getServices;
import static marketyp.Env.printMessage;
import static marketyp.Env.printSentMessage;
import static marketyp.Env.timeout;

public class ConsumerAgent extends BaseAgent{
    private static final Logger LOGGER = Logger.getLogger(DriverAgent.class.getName());

    DFAgentDescription[] services;

    private void extractArgumentsAndServices() {
        try {
            Object[] args = getArguments();
            if (args != null && args.length > 0) {
                home = Integer.parseInt(args[0].toString());
                offerPrice = Integer.parseInt(args[1].toString());
            }
            LOGGER.log(Level.SEVERE, "Initialize " + getLocalName() + " with parameters " + home + " " +
                    offerPrice);
            services = getServices(this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }




//    private void tryToBuy() {
//        try {
//            waitForAccept = true;
//            System.out.println(this.getLocalName() + ": SENT message PROPOSE " +  home + " " +  offerPrice);
//            for(DFAgentDescription dfd : services){
//                sendOfferToDefiniteService(dfd);
//            }
//            addBehaviour(new WakerBehaviour(this, timeout) {
//                @Override
//                protected void onWake() {
//                    ACLMessage msg = myAgent.receive();
//                    if (msg != null) {
//                        if (msg.getPerformative() == ACLMessage.ACCEPT_PROPOSAL) {
//                            printMessage(myAgent, msg);
//                            LOGGER.log(Level.INFO, getLocalName() + ": get ACCEPT from " + msg.getSender().getLocalName());
//                            needToBuy = false;
//                            ACLMessage acceptMessage = getResponseMessage(msg, ACLMessage.CONFIRM);
//                            printSentMessage(myAgent, msg.getSender().getLocalName(), acceptMessage);
//                            myAgent.send(acceptMessage);
//                            Env.INSTANCE.decreaseBuyers();
//                        }
//                    }
//                    waitForAccept = false;
//                    offerPrice +=  needToBuy ? 50 : 0;
//                }
//            });
//        }catch (Exception e){e.printStackTrace();}
//    }


    @Override
    protected void setup() {
        extractArgumentsAndServices();
        Env.increaseAgent();
        addBehaviour(new TickerBehaviour(this, timeout) {
            @Override
            protected void onTick() {
                if(needToBuy && !waitForAccept) {
                    Env.tryToBuy((BaseAgent)myAgent, services);
                }
            }
        });

    }


}
