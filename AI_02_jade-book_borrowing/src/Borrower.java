import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

import java.util.concurrent.ThreadLocalRandom;

public class Borrower extends Agent {
	
	//float frequency = 3f;
	String[] chooseableBooks = new String[]{"The Lusiads", "Dom Casmurro",
										   "Vidas Secas", "The Hour of the Star", 
										   "The Book of Disquiet", "Blindness", 
										   "The Trilogy of the Ferries" };

	
	@Override
	protected void setup(){
		super.setup();
		this.addBehaviour(new SenderBehaviour());
		this.addBehaviour(new DeathBehaviour());
		System.out.println("Borrower agent initialized.");
	}
	
	@Override
	protected void takeDown(){
		super.takeDown();
		System.out.println("Borrower agent terminated.");
	}
	
	String chooseRandomBook(){
		int index = ThreadLocalRandom.current().nextInt(0, chooseableBooks.length);
		return chooseableBooks[index];
	}
	private class DeathBehaviour extends CyclicBehaviour{
        @Override
        public void action(){

            ACLMessage msg = receive();

            if (msg != null){
				System.out.println(9);
               boolean hasTheBook = msg.getPerformative() == 4;
				//boolean hasTheBook = msg.getContent() == "TRUE";
                if (hasTheBook){
                    System.out.println("Agent will be removed in 10.");
					try {
						java.lang.Thread.sleep(10000);
						ACLMessage devolutionMsg = new ACLMessage(ACLMessage.INFORM);
						devolutionMsg.setContent(msg.getContent());
						devolutionMsg.addReceiver(msg.getSender());
						myAgent.send(devolutionMsg);



					}
					catch (Exception e){
						e.printStackTrace();
					}


                    myAgent.doDelete();
                    System.out.println("Agent removed from system.");
                }

                else{
					myAgent.doDelete();
                    System.out.println("Agent removed from system.");
                }
            }
        }
	}
	private class SenderBehaviour extends SimpleBehaviour{

		@Override
		public void action() {
			
			AID librarian = new AID();
			librarian.setLocalName("LibrarianAgent");
			
			ACLMessage message = new ACLMessage(ACLMessage.QUERY_IF);
			message.setContent( chooseRandomBook() );
			message.addReceiver(librarian);
			myAgent.send(message);
			
			System.out.println("Borrower: By any chance do you have the book ' " + message.getContent() + " ' ?");

		}
		@Override
		public boolean done(){
		    return true;
        }
		
	}


}

