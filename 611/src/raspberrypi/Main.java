package raspberrypi;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("start program");
		Scanner inn = new Scanner(System.in) ;
		String s = inn.nextLine();
		
		if(s.equalsIgnoreCase("server"))
		{
			//ServerSideSocket srv = new ServerSideSocket();
			ServerSideSocket.run();
		}
		else
		{
			
			ClientSocket.run();
		}

	}

}
