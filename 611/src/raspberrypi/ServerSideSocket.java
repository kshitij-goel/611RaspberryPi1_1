package raspberrypi;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class ServerSideSocket {
	public static void run() {
	try {
		int serverPort = 9999;
		ServerSocket serverSocket = new ServerSocket(serverPort);
		//serverSocket.setSoTimeout(10000); 
		Socket server =null ;
		Scanner in = new Scanner(System.in);
		while(true) {
			
			System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "..."); 
			
			try {
			server = serverSocket.accept();  // client connection 
            }
            catch (Exception e)
            {

                System.out.println("error in connection  " + e);
            }
			

		
			
			System.out.println("Just connected to " + server.getRemoteSocketAddress()); 
			
			PrintWriter out =  new PrintWriter(server.getOutputStream(),true);
			BufferedReader inBuffer = new BufferedReader(new InputStreamReader(server.getInputStream()));
            
			
            String readLine = in.nextLine();
            System.out.println("readLine   " + readLine);
            // if ((readLine = in.readLine()) != null) {
          if (readLine != null) { 
        	  
        	  String[] commands =  readLine.split("#");
        	  
            if(commands[0].equalsIgnoreCase("request") || commands[0].equalsIgnoreCase("test"))
            {
            	
            		//ask for reading from client and return it 
            		//for now displaying it on console 
            	    StringBuilder msg  = new StringBuilder();
                    msg.append("getStatus") ;
                    msg.append("#");
                    msg.append(serverSocket.getLocalPort());
                   
                    System.out.println("sending  request  command "+msg.toString());

                    out.println(msg.toString());
                    out.flush();
                    System.out.println("waiting for status from client ");
                    String status = inBuffer.readLine();
                    if (status != null) {

                       //send back to hub 
                       // PrintWriter out1 = new PrintWriter(server.getOutputStream(), true);
                       // out1.println(p);
                    	
                       //for now displaying on console
                    	System.out.println("ledstatus    " + status);


                    } else {

                    }

            		
            	//} if read sensor 
            	
            }else if(commands[0].equalsIgnoreCase("override"))
            {
            	
            	
            		//ask for reading from client and return it 
            		//for now displaying it on console 
            		System.out.println("override led lights " +readLine);
            	
                  
                    out.println(readLine);
                    out.flush();
                    
                    String responseForOverride = inBuffer.readLine();
                    if (responseForOverride != null) {

                       //send back to hub 
                       // PrintWriter out1 = new PrintWriter(server.getOutputStream(), true);
                       // out1.println(p);
                    	
                       //for now displaying on console
                    	System.out.println("responseForOverride    " + responseForOverride);


                    } else {

                    }
            	
        //    } //if1
            
            
            
          } // uncomment
          
            
          }
			//String line = fromClient.readLine();
			//System.out.println("Server received: " + line); 
			//toClient.println("Thank you for connecting to " + server.getLocalSocketAddress() + "\nGoodbye!"); 
		}
	}
	catch(UnknownHostException ex) {
		ex.printStackTrace();
	}
	catch(IOException e){
		e.printStackTrace();
	}
  }
	
 /* public static void main(String[] args) {
		//ServerSideSocket srv = new ServerSideSocket();
		//srv.run();
  }*/
}