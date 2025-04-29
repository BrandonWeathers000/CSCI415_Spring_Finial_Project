// USCA ACSC415

import java.io.*;
import java.net.*;

public class echoserver {
	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = null; // Descriptor of server
		Socket clientSocket = null; // Descriptor of client connection
		PrintWriter out = null; // Write to socket
		BufferedReader in = null; // Read from socket -- not used here

		// Create a Socket
		int port = 4343;
		try {
			serverSocket = new ServerSocket(port);
			// Keep waiting for client connection
			while(true){
				String playerOneResponse = "?";
				String playerTwoResponse = "?";
				for(int i = 0; i < 2; i++){
					// Start a client connection
					try{
						clientSocket = serverSocket.accept();
					}catch(IOException e){
						System.out.println("Accept failed:" + port);
						System.exit(-1);
					}

					// Get IO Stream
					out = new PrintWriter(clientSocket.getOutputStream(), true);
					System.out.println("Accept connection from" + clientSocket.getRemoteSocketAddress());
					in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

					// Read data from the client and send it back
					String s;
					s = in.readLine();
					System.out.println("Message from " + s);
					out.println("You sent <" + s + ">");

					// Determining user response
					if(i == 0){
						playerOneResponse = s.substring(s.length() - 1, s.length());
						System.out.println("Player one's response is: " + playerOneResponse);
					}else if(i == 1){
						playerTwoResponse = s.substring(s.length() - 1, s.length());
						System.out.println("Player two's response is: " + playerTwoResponse);

						if(playerOneResponse.equals(playerTwoResponse)){
							System.out.println("It's a tie...");
						}else if(playerOneResponse.equals("R") && playerTwoResponse.equals("S")){
							System.out.println("The one who threw rock wins!");
						}else if(playerOneResponse.equals("R") && playerTwoResponse.equals("P")){
							System.out.println("The one who threw paper wins!");
						}else if(playerOneResponse.equals("P") && playerTwoResponse.equals("R")){
							System.out.println("The one who threw paper wins!");
						}else if(playerOneResponse.equals("P") && playerTwoResponse.equals("S")){
							System.out.println("The one who threw scissors wins!");
						}else if(playerOneResponse.equals("S") && playerTwoResponse.equals("P")){
							System.out.println("The one who threw scissors wins!");
						}else if(playerOneResponse.equals("S") && playerTwoResponse.equals("R")){
							System.out.println("The one who threw rock wins!");
						}else{
							System.out.println("Something went wrong.");
						}
					}

					// Close client connection
					out.close();
					in.close();
					clientSocket.close(); 
				}
			}

		}catch(IOException e){
			System.out.println("Could not listen on port: "+ port);
			System.exit(-1);
		}

		// Close the server connection , this statement will never be reached
		// serverSocket.close();
	}
}
