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

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import cloud.jgo.net.Connection;
import cloud.jgo.net.Server;
import cloud.jgo.net.ServerTypes;
import cloud.jgo.net.tcp.DefaultTCPServer;
import cloud.jgo.net.tcp.NoReadingSourceException;
import cloud.jgo.net.tcp.TCPConnection;
import cloud.jgo.net.tcp.TCPFactoryConnection;
/**
 * 
 * @author Martire91<br>
 * This class contains the factory method for the connections
 *
 */
public class ConnectionsFactory {

	
	public final static int TYPE_TCP = 0 ;
	public final static int TYPE_UDP = 1 ;
	
	
	public static Connection getConnection(InetSocketAddress remoteAddress,int typeConnection) throws IOException{
		TCPConnection connection = null ;
		
		if(typeConnection == TYPE_TCP){
		connection = (TCPConnection) new TCPFactoryConnection().getConnection(remoteAddress);
		}
		else if(typeConnection == TYPE_UDP){
		// e qui ,quando poi costruiremo la sezione dell'udp
			// poi devo continuare qui e fare la stessa cosa
			/*
			 * COMPLETARE QUI NEL CASO IN CUI SI è SCELTO COME TIPO L'UDP
			 */
		}
		return connection ;
	}
	
	
	public static Connection getConnection(Server server,int typeServer) throws IOException, NoReadingSourceException{
		Connection connection = null ;
		if(typeServer == ServerTypes.TYPE_TCP.VALUE){
			DefaultTCPServer server_ =(DefaultTCPServer)server ;
			Socket socket = server_.acceptRequest();
			try {
				connection = new TCPConnection(socket, true);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(typeServer  == ServerTypes.TYPE_TCP.VALUE){
			
		}
	
	return connection ;
	}
	
	
	
	
	
	
	
}
