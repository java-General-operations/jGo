package test;

import java.util.Scanner;

import cloud.jgo.Encrypts;
import cloud.jgo.net.config.ServerConfiguration;
import cloud.jgo.net.tcp.NegativeListeningException;
import cloud.jgo.net.tcp.TCPServerConfiguration;
import cloud.jgo.net.tcp.login.TCPLoginServer;
import cloud.jgo.net.tcp.login.TCPLoginServerConfiguration;

public class MyLoginServerTest {
public static void main(String[] args) {
	TCPLoginServer server = new TCPLoginServer();
	// configuro il server 
	server.getConfiguration().put(ServerConfiguration.LPORT,3332);
	server.getConfiguration().put(TCPServerConfiguration.SERVER_NAME,"Mio login server");
	server.getConfiguration().put(TCPServerConfiguration.MULTI_CONNECTIONS,true);
	server.getConfiguration().put(TCPServerConfiguration.HANDLER_MODEL,new MyLoginHandlerTest());
	server.getConfiguration().put(TCPLoginServerConfiguration.USER,"wasp91");
	server.getConfiguration().put(TCPLoginServerConfiguration.PASSW,"wasp91dayno");
	server.getConfiguration().put(TCPLoginServerConfiguration.AES_KEY,Encrypts.TEXT_KEY_DEFAULT);
	server.reloadConfiguration();
	
	// imposto sorgente lettura e scrittura
	server.setReadFrom(System.out);
	server.setWriteFrom(new Scanner(System.in));
	
	try {
		server.listen();
		System.out.println("Server in ascolto ...");
	} catch (NegativeListeningException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	server.startServer();
	
}
}
