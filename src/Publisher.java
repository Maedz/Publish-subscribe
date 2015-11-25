// SimpleClient.java: A simple client program.
import java.net.*;
import java.io.*;

public class Publisher {
	
	/*HELLO MATHIAS THIS IS TEST, IS WORK?*/
	/*HELLO ALEX THIS IS TEST,IS WORK?*/
	
	public static void read(DataInputStream dis) throws IOException {
		// Get an input file handle from the socket and read the input
		String st = new String (dis.readUTF());
		System.out.println(st);
		// When done, just close the connection and exit
	}
	
	public static void send(DataOutputStream dos) throws IOException {
		// Get a communication stream associated with the socket
		// Send a string!

		dos.writeUTF("");
		// Close the connection, but not the server socket
	}
	
	public static void main(String args[]) throws IOException {
		// Open your connection to a server, at port 1254
		Socket s1 = new Socket("localhost",1254);
		
		// Outgoing information uses this object
		OutputStream s1Out = s1.getOutputStream();
		DataOutputStream dos = new DataOutputStream (s1Out);
		
		// Ingoing information uses this object
		InputStream s1In = s1.getInputStream();
		DataInputStream dis = new DataInputStream(s1In);
		
		read(dis);
		send(dos);
		s1.close();
	}

}