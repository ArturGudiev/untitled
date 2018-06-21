package marketyp;

import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;
import static marketyp.Env.checkForAcceptMessage;
import static marketyp.Env.consumerAgentsNumber;
import static marketyp.Env.timeout;
import static marketyp.Env.tryToBuy;

public class ConsumerAgent extends BaseConsumerAgent {
    private static final Logger LOGGER = Logger.getLogger(DriverAgent.class.getName());


    private void extractArgumentsAndServices() {
        try {
            Object[] args = getArguments();
            if (args != null && args.length > 0) {
                home = Integer.parseInt(args[0].toString());
                offerPrice = Integer.parseInt(args[1].toString());
            }
            LOGGER.log(Level.SEVERE, "Initialize " + getLocalName() + " with parameters " + home + " " +
                    offerPrice);
//            services = getServices(this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void setup() {
        extractArgumentsAndServices();
        Env.increaseConsumers();
//        System.out.println(getLocalName() + ":::::: INCREASE CONSUMERS " + consumerAgentsNumber);
        addBehaviour(new TickerBehaviour(this, timeout) {
            @Override
            protected void onTick() {
                if(needToBuy && !waitForAccept) {
                    tryToBuy((BaseConsumerAgent)myAgent);
                }
            }
        });

        //get messages and answer
        addBehaviour(new CyclicBehaviour(this) {

            @Override
            public void action() {
                ACLMessage msg = myAgent.receive();
                if (msg != null) {
                    // get ACCEPT
                    checkForAcceptMessage((BaseConsumerAgent) myAgent, msg);
                }
            }

        });
    }


}
