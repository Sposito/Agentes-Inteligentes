import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.ContainerController;
import jade.wrapper.AgentController;

class MainContainer {
	
	Runtime runtime;
	ContainerController container;
	
	public static void main(String[] args) {
		MainContainer mainContainer = new MainContainer();
		
		mainContainer.initMainContainerInPlatform("localhost", "9898", "MainContainer");
		mainContainer.startAgentInPlatform("BorrowerSpanerAgent", "BorrowerSpawner");
		mainContainer.startAgentInPlatform("LibrarianAgent", "Librarian");
	}
	
	public void initMainContainerInPlatform(String host, String port, String containerName){
		this.runtime = Runtime.instance();
		
		//Profile creation
		Profile profile = new ProfileImpl();
		profile.setParameter(Profile.CONTAINER_NAME, containerName);
		profile.setParameter(Profile.MAIN_HOST, host);
		profile.setParameter(Profile.MAIN_PORT, port);
		profile.setParameter(Profile.MAIN, "true");
		profile.setParameter(Profile.GUI, "true");
		
		//Create main container
		this.container = runtime.createMainContainer(profile);
		runtime.setCloseVM(true);
		
			
	}
	
	public void startAgentInPlatform(String name, String classpath){
	    try {
	       AgentController agentController = container.createNewAgent(name, classpath,  new Object[0]);
	       agentController.start();
	    } 
	    
	    catch (Exception e) {
	       e.printStackTrace();
	       System.out.println("Erro em" + name + " " + classpath );
	    }
	 }
	
		
	
}
