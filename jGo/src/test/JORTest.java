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
	
	// JOR completato
	JORServer server = £.createJORServer();
	Persona persona = new Persona("Marco", "Mariuss", 27);
	Persona persona2 = new Persona("Giovanni", "Mariuus", 30);
	Persona persona3 = new Persona("Dario", "Zozzo", 27);
	Persona persona4 = new Persona("Roberto", "Bene", 28);
	
	
	// configuro il server 
	
	server.getConfiguration().setLport(80);
	server.getConfiguration().setMultiConnections(true);
	server.getConfiguration().setServerName("Mio server");
	server.getConfiguration().setModel(new JORHandlerTest());
	server.getConfiguration().setRootFolder(System.getProperty("user.home")+"\\Desktop\\test");
	server.addToMatrix(new Persona[]{persona,persona2,persona3,persona4});
	
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
