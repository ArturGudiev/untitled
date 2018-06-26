package marketyp;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class StatisticAgent extends Agent {

    int number = 0;
    int delta = 0;
    boolean finished = false;
    HashMap<String, String> map = new HashMap<String, String>();
    List<String> drivers = new ArrayList<String>();
    int driversNum = 0;


    @Override
    protected void setup() {
        System.out.println("=========STATISTIC AGENT");
        //get messages and answer
        addBehaviour(new CyclicBehaviour(this) {
            @Override
            public void action() {
                ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
                if (msg != null) {
                    if ("driver".equals(msg.getContent())) {
                        drivers.add(msg.getSender().getLocalName());
                        System.out.println("driver checkin " + drivers.size() + " " + drivers);
                    } else {
                        number++;
                    }
                    System.out.println("-----CHECKIN " + msg.getSender().getLocalName() + " " + number);
                } else {
                    block();
                }
            }
        });

        addBehaviour(new CyclicBehaviour(this) {
            @Override
            public void action() {
                ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.AGREE));
                if (msg != null) {
                        driversNum++;
                    delta += Integer.parseInt(msg.getContent());
                        if(driversNum == drivers.size()){
                            System.out.println("Final delta : " + delta);
                        }
                }
                else{
                    block();
                }

            }
        });

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.CANCEL));
                if (msg != null) {
                        {
                        number--;
                        System.out.println("-----CHECKOUT " + msg.getSender().getLocalName() + " " + number);
                        String senderName = msg.getSender().getLocalName();
                        String str = msg.getContent();
                        map.put(senderName, str);
                        if (number == 0) {
                            finished = true;
                            addBehaviour(new WakerBehaviour(myAgent, 2000) {
                                @Override
                                protected void onWake() {
                                    sendRequestToAllDrivers();
                                }
                            });
                        }
                    }
                } else {
                    block();
                }
            }
        });
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.CONFIRM));
                if (msg != null) {
                    int d = Integer.parseInt(msg.getContent());
                    delta += d;
                } else {
                    block();
                }
            }
        });

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
                if (msg != null) {
                    ACLMessage ans = BaseConsumerAgent.getResponseMessage(msg, ACLMessage.REQUEST);
//                    ans.setContent();
                } else {
                    block();
                }
            }
        });
    }

    private void sendRequestToAllDrivers() {
        for (String driver : drivers){
            ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
            msg.addReceiver(new AID(driver, AID.ISLOCALNAME));
            msg.setLanguage("English");
            msg.setOntology("Connection");
            send(msg);
        }
    }
}
