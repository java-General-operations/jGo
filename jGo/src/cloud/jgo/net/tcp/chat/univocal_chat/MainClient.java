package cloud.jgo.net.tcp.chat.univocal_chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class MainClient {
public static void main(String[] args) throws IOException, ClassNotFoundException {
	
	/*
	 * la stessa cosa vale per il client ,il discorso del modello
	 */
	
	
	ChatClient client = new ChatClient();
	System.out.println("Connessione in corso ...");
	client.setUsername("Salvatore");
	client.setReadFrom(System.out);
	client.setWriteFrom(new Scanner(System.in));
	client.connect("localhost",3333);
	System.out.println("Connessione stabilita con il server");
	client.communicates(client.getConnection());
	

	
	
	
	
	
	
}
}
