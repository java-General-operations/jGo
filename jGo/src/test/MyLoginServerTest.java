package test;

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
	
	if (server.isConfigurated()) {
		System.out.println("Server configurato @");
	}
	else {
		System.err.println("Server non configurato #");
	}
}
}
