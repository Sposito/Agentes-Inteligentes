import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Arrays;  
import java.util.List;  
//import java.util.ArrayList;

public class Librarian extends Agent {
	
	String[] availableBooksArray = new String[]{"The Lusiads", "Dom Casmurro", "Vidas Secas", "The Hour of the Star", "The Book of Disquiet"};
	List<String> availableBooks;
	Boolean[] nonRentedBooks;
	@Override
	protected void setup(){
		availableBooks = Arrays.asList(availableBooksArray);
		nonRentedBooks = new Boolean[availableBooks.size()];

		for (int i = 0; i < nonRentedBooks.length; i++) {
			nonRentedBooks[i] = true;
		}

		super.setup();
		this.addBehaviour(new ReceiverBehaviour() );
		System.out.println("Librarian agent ready to listen.");
		
	}

	@Override
	protected void takeDown(){
		super.takeDown();
		System.out.println("Librarian agent gone for good.");
	}

	void SetBookAvailability(String bookName, Boolean available){
		int i = availableBooks.indexOf(bookName);
		nonRentedBooks[i] = available;
	}

	Boolean hasBook(String book){
		int i = availableBooks.indexOf(book);
		return availableBooks.contains(book) && nonRentedBooks[i];
	}


	
	private class ReceiverBehaviour extends CyclicBehaviour{
		
		@Override
		public void action(){
			ACLMessage msg = receive();
			
			if (msg != null){

				if(msg.getPerformative() == ACLMessage.QUERY_IF) {
					String librarianLine = "Librarian: ";
					String messageContent = msg.getContent();
					Boolean hasBook = hasBook(messageContent);
					if (hasBook){

						SetBookAvailability(messageContent, false);

						librarianLine += "Book " + messageContent + " just borrowed.";
					} else {
						librarianLine += "For gosh sake! We don't have this kind of book here!!";
					}
					sendBookMessage(msg.getSender(), hasBook, msg.getContent());
					System.out.println(librarianLine);
				}

				else if(msg.getPerformative() == ACLMessage.INFORM){
					SetBookAvailability(msg.getContent(), true);
					System.out.println("Librarian just received the book " + msg.getContent());
				}
				
			}
		}

		void sendBookMessage(jade.core.AID borrower, boolean hasBook, String bookName){
            ACLMessage message = new ACLMessage(hasBook? ACLMessage.CONFIRM : ACLMessage.DISCONFIRM);
            message.setContent(hasBook ? bookName : "FALSE");
            message.addReceiver(borrower);
            myAgent.send(message);
        }
	
		
	}
}