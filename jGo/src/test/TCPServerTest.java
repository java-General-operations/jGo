package test;

import java.util.Scanner;

import cloud.jgo.net.ServerTypes;
import cloud.jgo.net.config.ServerConfiguration;
import cloud.jgo.net.factorys.ServersFactory;
import cloud.jgo.net.tcp.NegativeListeningException;
import cloud.jgo.net.tcp.TCPServer;
import cloud.jgo.net.tcp.TCPServerConfiguration;

public class TCPServerTest {
public static void main(String[] args) {
	
	
	
	TCPServer server = (TCPServer) ServersFactory.getInstance().createServer(ServerTypes.TYPE_TCP.VALUE,3332);
	
	// configuro il server 
	
	server.getConfiguration().put(ServerConfiguration.SERVER_NAME,"mio server");
	server.getConfiguration().put(TCPServerConfiguration.MULTI_CONNECTIONS,true);
	server.getConfiguration().put(TCPServerConfiguration.HANDLER_MODEL,new TCPserverHandlerTest());
	
	
	
	server.reloadConfiguration();
	
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
