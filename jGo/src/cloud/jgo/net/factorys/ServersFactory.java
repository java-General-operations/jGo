/**
 * JGO - A pure Java library,
 * its purpose is to make life easier for the programmer.
 *
 * J - Java
 * G - General
 * O - Operations
 *
 * URL Software : https://www.jgo.cloud/
 * URL Documentation : https://www.jgo.cloud/docs/
 *
 * Copyright © 2018 - Marco Martire (www.jgo.cloud)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the MIT License.
 *
 * You may obtain a copy of License at :
 * https://www.jgo.cloud/LICENSE.txt
 *
 * To collaborate on this project, you need to do it from the software site.
 * 
 */
package cloud.jgo.net.factorys;
import java.net.SocketException;

import cloud.jgo.net.Server;
import cloud.jgo.net.ServerTypes;
import cloud.jgo.net.config.Configuration;
import cloud.jgo.net.config.HTTPServerConfiguration;
import cloud.jgo.net.config.TCPLoginServerConfiguration;
import cloud.jgo.net.config.TCPServerConfiguration;
import cloud.jgo.net.tcp.DefaultTCPServer;
import cloud.jgo.net.tcp.TCPServerTypes;
import cloud.jgo.net.tcp.http.HTTPServer;
import cloud.jgo.net.tcp.login.TCPLoginServer;
/**
 * 
 * @author Martire91<br>
 *  This class contains the factory method for the servers
 */
public class ServersFactory extends ServerFactory{

	private static ServersFactory factory = null ;
	
	private ServersFactory() {
		// nothing
	}
	public static ServersFactory getInstance(){
		if(factory == null){
			factory = new ServersFactory();
		}
		return factory ;
	}
	@Override
	public Server createServer(int type, int localPort) {
      Server server = null ;
		if(type==ServerTypes.TYPE_TCP.VALUE){
			
			// qui si deve creare un server TCP
			server = new DefaultTCPServer();
			server.setLocalPort(localPort);
		}
		else if(type == TCPServerTypes.TYPE_HTTP.VALUE){
			server = new HTTPServer(); // restituisco un server da configurare
			server.setLocalPort(localPort);
		}
		else if(type == TCPServerTypes.TYPE_LOGIN.VALUE){
			server = new TCPLoginServer();
			server.setLocalPort(localPort);
		}
		return server ;
	}

	@Override
	public Server createServer(Configuration configuration) throws SocketException {
		Server server = null ; 
		if(HTTPServerConfiguration.class.isInstance(configuration)){
			server = new HTTPServer();
		}
		// qui poi se creero altri server mettere qui i rispettivi else if
		
		else if(TCPServerConfiguration.class.isInstance(configuration)&&!TCPLoginServerConfiguration.class.isInstance(configuration)){
			server = new DefaultTCPServer();
		}
		else if(TCPLoginServerConfiguration.class.isInstance(configuration)){
			
			server = new TCPLoginServer();
		}
		// qui configuro il server 
		if(server!=null){
			server.configure(configuration);	
		}
		return server ;
	}


}
