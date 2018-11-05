package raspberrypi;

import com.pi4j.io.gpio.*;
public class ledController {
	public static GpioPinDigitalOutput redpin; 
	public static GpioPinDigitalOutput yellowpin ; 
	public static GpioPinDigitalOutput greenpin; 
	
	
   public  ledController()
     {
      //do initialization work	 
	  /* if(redpin==null)
	   {
		   GpioController gController = GpioFactory.getInstance();
		               redpin=   gController.provisionDigitalOutputPin(RaspiPin.GPIO_23,"redLed",
		            		   PinState.LOW);
	   }*/
   	  
   	  
     }
   public static String  getLedStatus()
   {
	   String status=null ;
	   PinState redstate = redpin.getState();
	   PinState yellowstate = yellowpin.getState();
	   PinState greenstate = greenpin.getState();
	   
	   System.out.println("red   "+redstate);
	   System.out.println("yellow    "+yellowstate);
	   System.out.println("green    "+greenstate);
	   
	   if(redpin.isLow())
	   {
		   status="red#0";
	   }
	   else
	   {
		   status="red#1";
	   }
	   if(yellowpin.isLow())
	   {
		   status+="yellow#0";
	   }
	   else
	   {
		   status+="yellow#1";
	   }
	   
	   if(greenpin.isLow())
	   {
		   status+="green#0";
	   }
	   else
	   {
		   status+="green#1";
	   }
	   
	  return status ; 
	   
	   
   }
   public static void toggleLed(GpioPinDigitalOutput r)
   {
	 /*  GpioController gController = GpioFactory.getInstance();
   redpin=   gController.provisionDigitalOutputPin(RaspiPin.GPIO_23,"redLed",
		   PinState.LOW);*/
	   r.toggle();
	   System.out.println("led toggled"+r );
   }
   
   public static PinState[] getPinState()
   {
	
	   
	   
	   return PinState.values();
   }
   
   
   public static void setPinvalues(String line) 
   { //  0         1  2    3   4       5
	   //override#red#0#yellow#0#green#0 
	   String[] status = line.split("#");
	   
	   System.out.println(status[4]);
	   
	   if(status[4].equalsIgnoreCase("1") && yellowpin.isHigh())
	   {
		   System.out.println(" yellow :retain high state");
		   
	
	   }
	   else if(status[4].equalsIgnoreCase("0") && yellowpin.isLow())
	   {
		   System.out.println("yellow :retain low state");
	   }
	   else
	   {
		   
		   yellowpin.toggle();
		   
		   System.out.println("yellow :toggle pin state ");
	   }
	
	   
	   
	   
	   if(status[2].equalsIgnoreCase("1") && redpin.isHigh())
	   {
		   System.out.println("redpin :retain high state");
		   
	
	   }
	   else if(status[2].equalsIgnoreCase("0") && redpin.isLow())
	   {
		   System.out.println("redpin : retain low state");
	   }
	   else
	   {
		   
		   redpin.toggle();
		   
		   System.out.println("redpin: toggle pin state ");
	   }
	   
	
	   if(status[6].equalsIgnoreCase("1") && greenpin.isHigh())
	   {
		   System.out.println("green: retain high state");
		   
	
	   }
	   else if(status[6].equalsIgnoreCase("0") && greenpin.isLow())
	   {
		   System.out.println("green: retain low state");
	   }
	   else
	   {
		   
		   greenpin.toggle();
		   
		   System.out.println("green : toggle pin state ");
	   }
	
	  

	   
   }
   
	public static void main(String[] args) {
		// TODO Auto-generated method stub
    
	}

}
