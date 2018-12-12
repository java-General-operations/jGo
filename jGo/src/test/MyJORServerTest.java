package test;

import java.util.ArrayList;
import java.util.List;

import cloud.jgo.net.config.Configuration.ConfigurationKey;
import cloud.jgo.net.config.ServerConfiguration;
import cloud.jgo.net.tcp.NegativeListeningException;
import cloud.jgo.net.tcp.TCPServerConfiguration;
import cloud.jgo.net.tcp.http.HTTPServerConfiguration;
import cloud.jgo.net.tcp.http.jor.JORServer;

public class MyJORServerTest {
	public static void main(String[] args) {

		JORServer server = new JORServer();

		// configuro il server

		server.setMultiConnections(true);
		server.setLocalPort(80);
		server.setRootFolder("C:\\test");
		server.setModel(new MyJORServerHandlerTest());

		// creo gli oggetti da rappresentare :

		Person person1 = new Person("Peter", "Galb", 40);
		Person person2 = new Person("Lucas", "Galb", 45);
		Person person3 = new Person("Jonh", "Galb", 50);
		
		List<Person>peopleList = new ArrayList<>();
		peopleList.add(person1);
		peopleList.add(person2);
		peopleList.add(person3);
		
		server.addToMatrix(peopleList);
		
		// metto il server in attesa 
		
		try {
			server.listen();
			System.out.println("Server in attesa ...");
		} catch (NegativeListeningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		server.startServer();

	}
}
