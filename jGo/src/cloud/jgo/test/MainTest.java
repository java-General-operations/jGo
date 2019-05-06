package cloud.jgo.test;

import cloud.jgo.£;
import cloud.jgo.io.File;
import cloud.jgo.net.Server;
import cloud.jgo.net.factorys.ServersFactory;
import cloud.jgo.net.tcp.NegativeListeningException;
import cloud.jgo.net.tcp.TCPServer;
import cloud.jgo.net.tcp.TCPServerTypes;
import cloud.jgo.net.tcp.http.HTTPServer;
import cloud.jgo.net.tcp.http.jor.JORServer;

/**
 * @author Martire91 - <https://github.com/wasp91>
 * @version 1.0.0
 */
public class MainTest {
public static void main(String[] args) {
	
	
	JORServer server = new JORServer();
	server.setMultiConnections(true);
	server.setRootFolder("C:\\test");
	server.setServerName("my jor server");
	server.setLocalPort(80);
	server.setModel(null);
	// mi creo gli oggetti da condividere 
	Magazzino primo = new Magazzino("conad",1500);
	Magazzino secondo = new Magazzino("despar", 2500);
	server.addToMatrix(new Magazzino[] {primo,secondo});
	
	// serializzo l'oggetto
	
	
	
}
}
