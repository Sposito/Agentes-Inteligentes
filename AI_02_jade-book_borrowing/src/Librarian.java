import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Arrays;  
import java.util.List;  
import java.util.ArrayList; 

public class Librarian extends Agent {
	
	String[] availableBooksArray = new String[]{"The Lusiads", "Dom Casmurro", "Vidas Secas", "The Hour of the Star", "The Book of Disquiet"};
	List<String> availableBooks;
	
	@Override
	protected void setup(){
		availableBooks = Arrays.asList(availableBooksArray);
		super.setup();
		this.addBehaviour(new ReceiverBehaviour() );
		System.out.println("Librarian agent ready to listen.");
		
	}
	
	@Override
	protected void takeDown(){
		super.takeDown();
		System.out.println("Librarian agent gone for good.");
	}


	
	private class ReceiverBehaviour extends CyclicBehaviour{
		
		@Override
		public void action(){
			ACLMessage msg = receive();
			
			if (msg != null){
				String librarianLine = "Librarian: ";
				String messageContent = msg.getContent();
				if (availableBooks.contains(messageContent) ){
					librarianLine += "Book " + messageContent + " just borrowed.";
				}
				
				else{
					librarianLine += "For gosh sake! We don't have this kind of book here!!";
				}
				
				System.out.println(librarianLine);
				
				
			}
		}
	
		
	}
}