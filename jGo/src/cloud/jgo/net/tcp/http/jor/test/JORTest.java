package cloud.jgo.net.tcp.http.jor.test;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import cloud.jgo.£;
import cloud.jgo.net.tcp.NegativeListeningException;
import cloud.jgo.net.tcp.http.HTTPServerConfiguration;
import cloud.jgo.net.tcp.http.jor.JORServer;

public class JORTest {
public static void main(String[] args) {
	
	// creo il server JOR
	
	// trovato bug, quando impostiamo dei settaggi sulla configurazione del server
	// eppoi lanciamo il metodo reloadConfiguration, risolvere e dopo
	// aggiungere funzionalità JOR
	
	JORServer server = £.createJORServer();
	
	// configuro il server 
	
	server.getConfiguration().setLport(80);
	server.getConfiguration().setMultiConnections(true);
	server.getConfiguration().setAcceptedSocket("Ciao come stai ?");
	server.getConfiguration().setServerName("Mio server");
	server.getConfiguration().setModel(new JORHandlerTest());
	server.getConfiguration().setMaximumSockets(10);
	server.getConfiguration().setRootFolder("C:\\test");
	server.getConfiguration().setTimer(10);
	
	
	server.reloadConfiguration();
	
	StringBuffer buffer = server.getConfiguration().AllConfigurations();
	System.out.println(buffer.toString());
}
}
