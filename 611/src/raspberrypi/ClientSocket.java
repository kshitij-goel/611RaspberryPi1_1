package raspberrypi;

import java.io.*;
import java.net.*;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.PinPullResistance;

public class ClientSocket {
//LOCAL EXECUTIONNNNNNNNNNNNN	
	
  public static void run() {

		  
	try {
		int serverPort = 9999;
		InetAddress host = InetAddress.getByName("localhost"); 
		
		System.out.println("Connecting to server on port " + serverPort); 
		
        Socket socket = null;
        while(true) {
        	try {
        		socket = new Socket(host,serverPort); 
        	}
        	catch(Exception e)
        	{
        		
        		
        	}
        	
		System.out.println("Just connected to " + socket.getRemoteSocketAddress()); 
		
		PrintWriter out = 
			new PrintWriter(socket.getOutputStream(),true);
		
		
         
		BufferedReader inBuffer = 
			new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
	
		String line = inBuffer.readLine(); 
		
		System.out.println("line" +line);
		
		
   		
		
		if(line!=null)
		{
		String[] requestFromServer = line.split("#") ; 
		
		if(requestFromServer[0].equalsIgnoreCase("sendStatus"))
		{
			InetAddress hos = InetAddress.getByName("192.168.1.187"); 
			
			Socket sock = new Socket(hos,10000); 
			
		    ObjectOutputStream outputStream = new ObjectOutputStream(sock.getOutputStream());
			/*PrintWriter out1 = 
					new PrintWriter(socket.getOutputStream(),true);*/
			 
			//SensorReading.testRaspberrypi();
			
			//send reading back to server : 
		    outputStream.writeObject("pi1#"+line);
		    outputStream.flush();
			
			out.println("suceesss");
			out.flush();
			
			
		}
		else if(requestFromServer[0].equalsIgnoreCase("override"))
		{
			//overriding control override/red/0/yellow/0/green/0/ 
			
			ledController.setPinvalues(line);
			
			System.out.println("overriding done \n sending success message to server ");
			
			out.println("success");
			out.flush();
			
			
		}else
		{
			System.out.print("unknown request");
		}
		
		}
		
		
		//out.println("Hello from " + socket.getLocalSocketAddress()); 
		
		
	}//while
		//System.out.println("Client received: " + line + " from Server");
		//toServer.close();
		//fromServer.close();
		//socket.close();
	}
	catch(UnknownHostException ex) {
		ex.printStackTrace();
	}
	catch(IOException e){
		e.printStackTrace();
	}
  }

}