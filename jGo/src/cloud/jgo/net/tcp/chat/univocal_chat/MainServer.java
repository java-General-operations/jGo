package cloud.jgo.net.tcp.chat.univocal_chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import cloud.jgo.net.tcp.NegativeListeningException;
import cloud.jgo.net.tcp.NoReadingSourceException;
import cloud.jgo.net.tcp.TCPHandlerConnection;
import cloud.jgo.net.tcp.chat.Mexg;

public class MainServer {
public static void main(String[] args) throws IOException {
	
	ChatServer server = new ChatServer();
	server.setReadFrom(System.out);
	server.setWriteFrom(new Scanner(System.in));
	server.setUsername("Marco");
	try {
		try {
			server.listen(3333, "Server in ascolto","Server inattivo");
		} catch (NegativeListeningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (NoReadingSourceException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	// imposto le sorgenti
	
	try {
		server.acceptRequest();
	} catch (NoReadingSourceException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

}
}
