import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

import java.util.concurrent.ThreadLocalRandom;

public class Borrower extends Agent {
	
	float frequency = 3f;
	String[] chooseableBooks = new String[]{"The Lusiads", "Dom Casmurro",
										   "Vidas Secas", "The Hour of the Star", 
										   "The Book of Disquiet", "Blindness", 
										   "The Trilogy of the Ferries" };
	
	@Override
	protected void setup(){
		super.setup();
		this.addBehaviour(new SenderBehaviour());
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
	
	private class SenderBehaviour extends CyclicBehaviour{

		@Override
		public void action() {
			
			AID librarian = new AID();
			librarian.setLocalName("LibrarianAgent");
			
			ACLMessage message = new ACLMessage(ACLMessage.INFORM);
			message.setContent( chooseRandomBook() );
			message.addReceiver(librarian);
			myAgent.send(message);
			
			System.out.println("Borrower: By any chance do you have the book ' " + message.getContent() + " ' ?");
			block((int)(frequency * 1000));
			
			
		}
		
	}


}

