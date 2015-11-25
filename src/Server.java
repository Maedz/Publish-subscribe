// SimpleServer.java: A simple server program.
import java.net.*;
import java.io.*;

public class Server {
	
	public static void send(String Output, DataOutputStream dos) throws IOException {
		// Get a communication stream associated with the socket
		// Send a string!z
		dos.writeUTF(Output);
	}
	
	public static void read(DataInputStream dis) throws IOException {
		// Get an input file handle from the socket and read the input
		String st = new String (dis.readUTF());
		System.out.println(st);
	}
	
	public static void main(String args[]) throws IOException {
		
		// Register service on port 1254
		ServerSocket s = new ServerSocket(1254);
				
		Socket s1;
		while(true)
		{
			s1 = s.accept(); // Wait and accept a connection
			
			// Outgoing information uses this object
			OutputStream s1Out = s1.getOutputStream();
			DataOutputStream dos = new DataOutputStream (s1Out);
					
			// Ingoing information uses this object
			InputStream s1In = s1.getInputStream();
			DataInputStream dis = new DataInputStream(s1In);
			
			send("Connection Running...\n\nWhat do you want to do?\n\n1.Add\n\n2Subscribe\n\n", dos);
			read(dis);
			s1.close();
		}
		
	}
	
	
	
}