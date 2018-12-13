package test;

import cloud.jgo.net.tcp.NegativeListeningException;
import cloud.jgo.net.tcp.http.jor.JORServer;

public class MyJORServerTest {
public static void main(String[] args) {
	
	
	JORServer server = new JORServer();
	server.setRootFolder("C:\\test");
	server.setLocalPort(80);
	server.setMultiConnections(true);
	server.setModel(new MyHandler());
	
	// aggiungo gli oggetti da rappresentare :
	
	Book book1,book2,book3 ;
	book1 = new Book(10,"Stieg Lars",20.0,"Millen");
	book2 = new Book(40,"Wasp91",20,"Il paese degli uccelli");
	book3 = new Book(70, "Daynos",35,"Good life");
	
	server.addToMatrix(new Book[]{book1,book2,book3});
	
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
