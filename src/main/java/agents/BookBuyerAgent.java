package agents;

import jade.core.Agent;

public class BookBuyerAgent extends Agent {
    protected void setup() {
        // Printout a welcome message
//        System.out.println("Hello! Buyer-agent "+getAID().getName()+" is ready.");
        System.out.println("hello");
    }

    public static void main(String[] args) {
        BookBuyerAgent agent = new BookBuyerAgent();
        agent.setup();
//        agent.setup();
    }
}
