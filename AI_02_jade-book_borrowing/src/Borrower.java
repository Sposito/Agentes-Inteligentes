import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class Borrower extends Agent {
	
	float frequency = 3f;     
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
	
	private class SenderBehaviour extends CyclicBehaviour{

		@Override
		public void action() {
			
			AID librarian = new AID();
			librarian.setLocalName("LibrarianAgent");
			
			ACLMessage message = new ACLMessage(ACLMessage.INFORM);
			message.setContent("Hey fellow!");
			message.addReceiver(librarian);
			myAgent.send(message);
			
			
			block((int)(frequency * 1000));
			
			System.out.println("Message sent");
		}
		
	}


}

