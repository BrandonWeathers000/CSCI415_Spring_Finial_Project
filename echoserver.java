
/*****
 **  
 **  USCA ACSC415
 **
 */

import java.io.*;
import java.net.*;

public class echoserver {

	public static void main(String[] args) throws Exception {

		ServerSocket serverSocket = null; // descriptor of server
		Socket clientSocket = null; // descriptor of client connection
		PrintWriter out = null; // write to socket
		BufferedReader in = null; // read from socket -- not used here

		/* create a socket */
		int port = 4343;
		try {
			serverSocket = new ServerSocket(port);

			/* keep waiting for client connection */
			while (true) {

				/* start a client connection */
				try {
					clientSocket = serverSocket.accept();
				} catch (IOException e) {
					System.out.println("Accept failed:" + port);
					System.exit(-1);
				}

				/* Get IO Stream */
				out = new PrintWriter(clientSocket.getOutputStream(), true);
				System.out.println("Accept connection from" + clientSocket.getRemoteSocketAddress());
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

				/* read data from the client and send it back */
				String s;
				s = in.readLine();
				System.out.println("Message from user:" + s);
				out.println("You sent <" + s + ">");

				/* close client connection */
				out.close();
				in.close();
				clientSocket.close(); 

			}

		} catch (IOException e) {
			System.out.println("Could not listen on port: "+ port);
			System.exit(-1);
		}

		/* close the server connection , this statement will never be reached */
		// serverSocket.close();
	}
}
