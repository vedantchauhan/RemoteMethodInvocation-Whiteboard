
package au.edu.unimelb.distributedsystems.whiteboard.client.app;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client 
{
    //public static PaintApplication paint; //= new PaintApplication();
    public static ClientLoginGUI login;
    
    public static void main(String[] args) 
    {
    	// Default values for host and port number.
    			String host = "localhost";
    			int portNo = 3000;
    			IWhiteboardCommunication iWhiteboardCommunication = null;
    			
    			if(args.length > 0){
    				host = args[0];
    				portNo = Integer.parseInt(args[1]);
    	        }
    			
    			try {
    			Registry registry = LocateRegistry.getRegistry(host, portNo);
    			iWhiteboardCommunication = (IWhiteboardCommunication) registry.lookup("Whiteboard");
    	        System.out.println("Found server");

    	        //paint = new PaintApplication();
    	        login = new ClientLoginGUI();
    	        login.setVisible(true);
    			}catch(Exception e) {
    				e.printStackTrace();
    			}
        
    }
}
