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
	
	JORServer server = £.createJorServer();
	
	HTTPServerConfiguration conf = new HTTPServerConfiguration();
	conf.setMultiConnections(true);
	conf.setLport(80);
	conf.setRootFolder("C:\\test");
	conf.setModel(new JORHandlerTest());
	
	try {
		server.configure(conf);
	} catch (SocketException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	System.out.println("Configurazione corretta del server @");
	
}
}
