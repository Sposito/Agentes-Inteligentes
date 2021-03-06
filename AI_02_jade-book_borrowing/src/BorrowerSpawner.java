import jade.core.Agent;
import jade.core.Runtime;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.Behaviour;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;

import java.util.UUID;

public class BorrowerSpawner extends Agent {
    @Override
    protected void setup(){
        super.setup();
        Behaviour loop = new TickerBehaviour( this, 1000 )
        {
            protected void onTick() {
                System.out.println("New Borrower Spawned");
                try {
                    AgentController ag = myAgent.getContainerController().createNewAgent("BorrowerAgent_" + UUID.randomUUID() , "Borrower", new Object[]{});
                    ag.start();
                }

                catch (Exception e){

                }
            }
        };

        addBehaviour( loop );
        System.out.println("Spawner agent initialized.");
    }

    @Override
    protected void takeDown(){
        super.takeDown();
        System.out.println("Borrower agent terminated.");
    }


}
