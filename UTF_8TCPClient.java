import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class UTF_8TCPClient{
	public static void main(String[] args) throws Exception{
		// String host = "172.17.156.200";
		String host = "172.17.158.11";
		int port = 4343;

		try{
		// Construct socket and input/output stream
		Socket mySocket = new Socket(host, port);
		Scanner inFrom = new Scanner(mySocket.getInputStream());
		DataOutputStream outTo = new DataOutputStream(mySocket.getOutputStream());

		// Gaming
		Scanner ob = new Scanner(System.in);
		System.out.println("Welcome to rock paper scissors!");
		System.out.println("-------------------------------");
		System.out.print("What is your username: ");
		String userName	= ob.nextLine();
		System.out.println("Wait for the count of three, then enter either R, P, or S.");
		System.out.println("When you are ready to continue press ENTER.");
		ob.nextLine();

		System.out.println("Here we go...");
		Thread.sleep(1000);
		System.out.println("3");
		Thread.sleep(1000);
		System.out.println("2");
		Thread.sleep(1000);
		System.out.println("1");
		Scanner inFromUser = new Scanner(System.in);
		String message = inFromUser.nextLine();
		outTo.writeBytes("(" + userName + ")" + " I throw ");
		outTo.writeBytes(message + "\r\n");
		outTo.flush();

		// System.out.println();
		// System.out.println("Do you want to play again? [y/n]");
		// String message = inFromUser.nextLine();

		// Read
		// String response = inFrom.nextLine();
		// System.out.println("Server: " + response);

		// Close Everything
		inFromUser.close();
		inFrom.close();
		outTo.close();
		mySocket.close();

		}catch(IOException e){
			System.out.print("ERROR: ");
			System.err.println(e.getMessage());
		}
	}
}
