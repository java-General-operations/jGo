package test;

import javax.swing.text.DefaultHighlighter;

import cloud.jgo.net.config.ServerConfiguration;
import cloud.jgo.net.factorys.ServersFactory;
import cloud.jgo.net.tcp.NegativeListeningException;
import cloud.jgo.net.tcp.TCPServerConfiguration;
import cloud.jgo.net.tcp.TCPServerTypes;
import cloud.jgo.net.tcp.http.DefaultHTTPServer;
import cloud.jgo.net.tcp.http.HTTPServer;
import cloud.jgo.net.tcp.http.HTTPServerConfiguration;

public class HTTPServerTest {
public static void main(String[] args) {
	
	
	HTTPServer server = new DefaultHTTPServer();
	
	server.getConfiguration().put(ServerConfiguration.LPORT,80);
	server.getConfiguration().put(ServerConfiguration.SERVER_NAME,"mio server");
	server.getConfiguration().put(TCPServerConfiguration.MULTI_CONNECTIONS,true);
	server.getConfiguration().put(HTTPServerConfiguration.ROOT_FOLDER,"C:\\test");
	server.getConfiguration().put(TCPServerConfiguration.HANDLER_MODEL,new HTTPServerHandlerTest());
	
	server.reloadConfiguration();
	
	try {
		server.listen();
		System.out.println("http server in attesa di richieste ...");
	} catch (NegativeListeningException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	server.startServer();
}
}
