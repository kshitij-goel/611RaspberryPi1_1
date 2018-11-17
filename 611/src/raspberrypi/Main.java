package raspberrypi;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Main {
	public static String globalOverride ="0" ;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("start program");
		Scanner inn = new Scanner(System.in) ;
		String s = inn.nextLine();
	
		if(s.equalsIgnoreCase("server"))
		{
			//ServerSideSocket srv = new ServerSideSocket();
			
			
			    clientHelper myRunnable = new clientHelper();
		        Thread t = new Thread((Runnable) myRunnable);
		        t.start();
		        System.out.println("thread started");
		        ServerSideSocket.run();
	    }
		
		else
		{
			
			ClientSocket.run();
		}

	}




}
