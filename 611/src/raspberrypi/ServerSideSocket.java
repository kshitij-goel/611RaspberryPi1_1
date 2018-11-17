package raspberrypi;

import java.net.*;
import java.util.Scanner;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.platform.PlatformManager;
import com.pi4j.system.NetworkInfo;
import com.pi4j.system.SystemInfo;

import java.io.*;

public class ServerSideSocket {
	
	
	public static void run() {
		
		
		  ServerSideSocket.initialize();
		

		
	try {
		
		int serverPort = 9999;
		ServerSocket serverSocket = new ServerSocket(serverPort);
		
		//serverSocket.setSoTimeout(10000); 
		Socket server =null ;
		Scanner in = new Scanner(System.in);
		
		while(true) {
			
			System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "..."); 
			
			
			try {
			
		    System.out.println("waiting to connect");		
			server = serverSocket.accept(); 
		
            }
            catch (Exception e)
            {

                System.out.println("error in connection  " + e);
            }
			

		
			
			System.out.println("Just connected to " + server.getRemoteSocketAddress()); 
			
		   //PrintWriter out =  new PrintWriter(server.getOutputStream(),true);
			 //BufferedReader inBuffer = new BufferedReader(new InputStreamReader(server.getInputStream()));
			// ObjectOutputStream outputStream = new ObjectOutputStream(server.getOutputStream());  
			// String readLine = inBuffer.readLine();
			
			 ObjectInputStream inputStream = new ObjectInputStream(server.getInputStream());
			
           
            String readLine="";
			try {
				
				readLine = (String) inputStream.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
           
			System.out.println("readLine   " + readLine);
			
			
            // if ((readLine = in.readLine()) != null) {
          if (readLine != null) { 
        	  
        	  String[] commands =  readLine.split("#");
        /*	  
            if(commands[0].equalsIgnoreCase("request") || commands[0].equalsIgnoreCase("test"))
            {
            	  ///gather status 
            	
    			   System.out.println("getting leds pinstate");
    		       String ledStatus = ledController.getLedStatus();
    			
    			
    			    System.out.println("sending status  message to server ");
    			
            	
            	    String msg  = "sendStatus"+"#"+ledStatus ;
                   
                    System.out.println("sending  status report to hub  "+msg);
                    
                    byte[] ipAddr = new byte[] { (byte) 192, (byte) 168, (byte)1, (byte) 187 };
                    InetAddress hos = InetAddress.getByAddress(ipAddr); 
        			Socket sock = new Socket(hos,50000); 
        			
        		    ObjectOutputStream outputStream  = new ObjectOutputStream(sock.getOutputStream());
                    outputStream.writeObject(msg); 
                    outputStream.flush();
                    outputStream.close();
                    
              
            }else */if(commands[0].equalsIgnoreCase("initial"))
		            {
		            	
                
                //initial - mac , hardware id #output #led#numbr of led #input #sensor # min value #max value 
            	  
            	   StringBuilder sb = new StringBuilder() ;
            	   sb.append("pi1");
            	   sb.append("#");
            	   sb.append("initial");
            	   sb.append("#");
            	   
            	   String macaddress = PiSystemInformation.getMacAddress() ;
            	   sb.append(macaddress);  //mac value
            	   sb.append("#");
            	  
            	   String hardwareId= PiSystemInformation.getHardwareID() ;
            	   sb.append(hardwareId);
            	   
            	   sb.append("#");
            	   sb.append("out");
            	   sb.append("#");
            	   sb.append("led");
            	   sb.append("#");
            	   sb.append("3");
            	   sb.append("#");
            	   sb.append("in");
            	   sb.append("#");
                   sb.append("UltraSonic Sensor");
                   sb.append("#");
                   sb.append("2"); //ultasonic min value 
                   sb.append("#");
                   sb.append("400"); //ultrasonic sensor max value 
                   sb.append("#");
                   sb.append("cm");
                   
                   System.out.println("sending INITIAL response to hub " + sb.toString());
                   
                   byte[] ipAddr = new byte[] { (byte) 192, (byte) 168, (byte)1, (byte) 187 };
                   InetAddress hos = InetAddress.getByAddress(ipAddr); 
       			   Socket sock = new Socket(hos,50000); 
       			
       		       ObjectOutputStream outputStream  = new ObjectOutputStream(sock.getOutputStream());
                   outputStream.writeObject(sb.toString()); 
                   outputStream.flush();
                   outputStream.close();
                   
                   
		            	
		            }
		            
            else if(commands[0].equalsIgnoreCase("request"))
            	 // 0     1  2        3  4    5   6  7  8     9    10    11   12  13   14    15  16
            {   //request # overried  #  0/1  #  red #  0/1   #   yellow  #  0/1  #   green  #  0/1
            	
            	//    0        1   2  3  4  5     6  7    8
            	//request#override#0#red#0#yellow#0#green#0
            	      //case one :  override =yes 
            	      //case two :  override = no 
            	
            		    System.out.println(" request override  " +readLine);
            	          if(commands[2].equals("1"))
            	          {
            	        	  Main.globalOverride="1" ;
            	          }
            	          else
            	          {
            	        	  Main.globalOverride="0" ; 
            	          }
            		    //change led status 
            		    ledController.setPinvalues(readLine);
                        System.out.println("led Values changed ");
        			
                       /* byte[] ipAddr = new byte[] { (byte) 192, (byte) 168, (byte)1, (byte) 187 };
                        InetAddress hos = InetAddress.getByAddress(ipAddr); 
            			
            			Socket sock = new Socket(hos,50000); 
            			
            		    ObjectOutputStream outputStream1  = new ObjectOutputStream(sock.getOutputStream());
                      
            		    outputStream1.writeObject("successsss"); 
                        outputStream1.flush();
                        outputStream1.close();*/

            
            
            
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
	
	public static void initialize()
	{
		   GpioController gController = GpioFactory.getInstance();
			  
		    ledController.redpin=   gController.provisionDigitalOutputPin(RaspiPin.GPIO_24,"redLed",
		 		   PinState.LOW);
		    
		    ledController.yellowpin=   gController.provisionDigitalOutputPin(RaspiPin.GPIO_23,"redLed",
		    		   PinState.LOW);
		    
		     ledController.greenpin =   gController.provisionDigitalOutputPin(RaspiPin.GPIO_25,"redLed",
		    		   PinState.LOW);
	}
}