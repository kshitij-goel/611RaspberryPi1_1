package raspberrypi;
/* Pi4J imports */
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class SensorReading {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello, Raspberry Pi!");
	
       
	}
	
	public static void testRaspberrypi()
	{
		 /* Initialize pi4j */
        final GpioController gpio = GpioFactory.getInstance();
         
        /* Initialize GPIO 0 as an input pin called "MyButton" and set
           it low using the pull-down resistor.
        */
        /*GpioPinDigitalInput myButton =
            gpio.provisionDigitalInputPin(RaspiPin.GPIO_00,
                                            "MyButton",
                                            PinPullResistance.PULL_DOWN);
 
         Read the state (high or low) of the pin. Remember, it should be "low" 
        PinState pinState = myButton.getState();
        System.out.println("GPIO 0 is set to " + pinState.getName());
        System.out.println("all ok") ;*/
        /* Close all open connections. */
      //  gpio.shutdown();

	}

}
