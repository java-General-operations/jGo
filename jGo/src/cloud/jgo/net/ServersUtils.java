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
package cloud.jgo.net;
import cloud.jgo.net.config.ServerConfiguration;
import cloud.jgo.net.handlers.Handler;
import cloud.jgo.net.tcp.TCPHandlerConnection;
import cloud.jgo.net.tcp.TCPServer;
import cloud.jgo.net.tcp.http.HTTPHandlerConnection;
import cloud.jgo.net.tcp.http.HTTPServer;
import cloud.jgo.net.tcp.login.TCPLoginHandlerConnection;
import cloud.jgo.net.tcp.login.TCPLoginServer;
import cloud.jgo.net.tcp.login.TCPLoginServerConfiguration;
/**
 * @author Martire91
 * This is a simple utility class for servers
 */
public final class ServersUtils {
	private ServersUtils() {}
	public static boolean compatible(ServerConfiguration config,Server server){
		// prendo la configurazione del server
		ServerConfiguration currentConfig = (ServerConfiguration) server.getConfiguration();
		if (config.getClass().equals(currentConfig.getClass())) {
			return true ;
		}
		else{
		return false ;
		}
	}
	public static boolean compatible(Handler handler,Server server){
		boolean compatible = false ;
		if (TCPServer.class.isInstance(server)) {
			if (TCPHandlerConnection.class.isInstance(handler)) {
				compatible = true ;
			}
		}
		else if(HTTPServer.class.isInstance(server)){
			if (HTTPHandlerConnection.class.isInstance(handler)) {
				compatible = true ;
			}
		}
		else if(TCPLoginServer.class.isInstance(server)){
			if (TCPLoginHandlerConnection.class.isInstance(handler)) {
				compatible = true ;
			}
		}
		return compatible ;
	}
}
