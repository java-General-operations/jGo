package test;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import cloud.jgo.£;
import cloud.jgo.net.tcp.NegativeListeningException;
import cloud.jgo.net.tcp.http.HTTPServerConfiguration;
import cloud.jgo.net.tcp.http.jor.JORServer;

public class JORTest {
public static void main(String[] args) {
	
	// risolto bug : bisogna dare un array tipizzato
	
	JORServer server = £.createJORServer();
	Account a = new Account("wasp91", "wasp91dayno");
	Account a1 = new Account("marco91", "marco91dayno");
	
	
	// configuro il server 
	
	server.getConfiguration().setLport(80);
	server.getConfiguration().setMultiConnections(true);
	server.getConfiguration().setServerName("Mio server");
	server.getConfiguration().setModel(new JORHandlerTest());
	server.getConfiguration().setRootFolder(System.getProperty("user.home")+"\\Desktop\\test");
	server.addToMatrix(new Account[]{a,a1});
	
	System.out.println("Modalità array #");
	
	
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
