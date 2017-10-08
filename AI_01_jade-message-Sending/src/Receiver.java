import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class Receiver extends Agent {
	@Override
	protected void setup(){
		super.setup();
		this.addBehaviour(new ReceiverBehaviour() );
		System.out.println("Receiver agent ready to listen.");
		
	}
	
	@Override
	protected void takeDown(){
		super.takeDown();
		System.out.println("Receiver agent gone for good.");
	}


	
	private class ReceiverBehaviour extends CyclicBehaviour{
		
		@Override
		public void action(){
			ACLMessage msg = receive();
			
			if (msg != null){
				System.out.println(String.format(
						"Message received from %s, which says: \' %s \'",
						msg.getSender().getName(),
						msg.getContent()
					));
				
			}
		}
	
		
	}
}