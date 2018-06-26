package marketyp;

import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import static java.lang.Thread.sleep;
import static marketyp.Env.consumerAgentsNumber;
import static marketyp.Env.timeout;

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void setup() {
        extractArgumentsAndServices();
        Env.increaseConsumers();
        System.out.println("CONSUMER-CHECKIN");
        checkIn();

        addBehaviour(getConsumerBehaviour());
    }




}
