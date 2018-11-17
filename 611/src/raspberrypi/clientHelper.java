package raspberrypi;
import java.io.*;
import java.net.*;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;

public class clientHelper  implements Runnable {
	
	public static GpioPinDigitalOutput triggerPin ;
	public  static GpioPinDigitalInput    echoPin ;
	
	final static GpioController gpio = GpioFactory.getInstance();
	
	public void run() {
		System.out.println("clientHelper run");
	
		triggerPin =  gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04); // Trigger pin as OUTPUT
		echoPin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_05,PinPullResistance.PULL_DOWN); // Echo pin as INPUT
		int serverPort = 9999;
		InetAddress host = null;
		

		
		
		
		
		while(true){
			
			
			/*try {
				host = InetAddress.getByName("localhost");
			} catch (UnknownHostException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} 
			   Socket sock = null;
		       try {
				sock = new Socket(host,serverPort);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} */
			
			try {
		   // System.out.println("initial status ");
			//System.out.println(ledController.getLedStatus());
						
			//Thread.sleep(2000);
			triggerPin.high(); // Make trigger pin HIGH
			Thread.sleep((long) 0.01);// Delay for 10 microseconds
			triggerPin.low(); 
		
			while(echoPin.isLow()){ 
				
			}
			long startTime= System.nanoTime(); 
			while(echoPin.isHigh()){ 
				
			}
			long endTime= System.nanoTime(); 
			double distance = (((endTime-startTime)/1e3)/2) / 29.1 ;
		
			//System.out.println("Distance :"+((((endTime-startTime)/1e3)/2) / 29.1) +" cm"); 
			//send status to hub 
			/*
			byte[] ipAddr = new byte[] { (byte) 127, (byte) 0, (byte)0, (byte) 1 };
            InetAddress hos = InetAddress.getByAddress(ipAddr); 
			Socket sock = new Socket(hos,9999); 
			
			
		*/	
		    
			
		
            if(Main.globalOverride=="0")
            {
            	System.out.println("I AM THE KING");
            	if(distance > 50 && distance <=120)
    			{
    				//switch on  green light 
    				if(ledController.greenpin.isLow())
    				{
    					ledController.greenpin.toggle();
    				}
    				
    				if(ledController.redpin.isHigh())
    				{
    					ledController.redpin.toggle();
    				}
    				
    				
    				if(ledController.yellowpin.isHigh())
    				{
    					ledController.yellowpin.toggle();
    				}
    				
    			
    				
    			}//end if >=170
    			else if(distance <= 50 && distance >15)
    			{
    				//yellow
    				if(ledController.greenpin.isHigh())
    				{
    					ledController.greenpin.toggle();
    				}
    				
    				if(ledController.redpin.isHigh())
    				{
    					ledController.redpin.toggle();
    				}
    				
    				
    				if(ledController.yellowpin.isLow())
    				{
    					ledController.yellowpin.toggle();
    				}
    				
    				
    				//System.out.println(ledController.getLedStatus());
    				
    				
    			}//end if 200
    			
    			
    			else if(distance >= 2 && distance <= 15)
    			{
    				//switch on  red light 
    				if(ledController.greenpin.isHigh())
    				{
    					ledController.greenpin.toggle();
    				}
    				
    				if(ledController.redpin.isLow())
    				{
    					ledController.redpin.toggle();
    				}
    				
    				
    				if(ledController.yellowpin.isHigh())
    				{
    					ledController.yellowpin.toggle();
    				}
    				
    				
    				
    				
    				
    			}//end if 200
    			else
    			{
    				//switch off all led 
    				if(ledController.greenpin.isHigh())
    				{
    					ledController.greenpin.toggle();
    				}
    				
    				if(ledController.redpin.isHigh())
    				{
    					ledController.redpin.toggle();
    				}
    				
    				
    				if(ledController.yellowpin.isHigh())
    				{
    					ledController.yellowpin.toggle();
    				}
    				
    			}
            	
            }
            else
            {
            	System.out.println("Do Nothing");
            }
			
			
			
			  String ledStatus = ledController.getLedStatus();
			  String macaddress = PiSystemInformation.getMacAddress() ;
			   StringBuilder sb = new StringBuilder() ;
	      	   sb.append("pi1");
	      	   sb.append("#");
	      	   sb.append("updates");
	      	   sb.append("#");
	      	   sb.append(macaddress);  //mac value
	      	   sb.append("#");
	      	   sb.append(ledStatus);
	      	   sb.append("#");
               sb.append("sensor");
               sb.append("#");
               sb.append(String.valueOf(distance)); //ultrasonic sensor max value 
               byte[] ipAddr = new byte[] { (byte) 192, (byte) 168, (byte)1, (byte) 187 };
               InetAddress hos = InetAddress.getByAddress(ipAddr); 
   			
   			    Socket sock = new Socket(hos,50000); 
	        	ObjectOutputStream outputStream  = new ObjectOutputStream(sock.getOutputStream());
	            outputStream.writeObject(sb.toString()); 
	            outputStream.flush();
	            outputStream.close();
	            sock.close();
            
			
			Thread.sleep(5000);
			
		} catch (Exception e) {
			System.out.println(e);
			}
		}
     
	}//end run 
	


}
