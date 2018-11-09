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
package cloud.jgo.net.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import cloud.jgo.net.Connection;
import cloud.jgo.net.factorys.ConnectionFactory;
/**
 * 
 * @author Martire91<br>
 * Factory class for tcp connections
 *
 */
public class TCPFactoryConnection implements ConnectionFactory{
	private Socket socket = null ;
	private Connection connection = null ;
	public TCPFactoryConnection() {
		// TODO Auto-generated constructor stub
		this.socket = new Socket();
	}
	@Override
	public Connection getConnection(InetSocketAddress remoteAddress) throws IOException {
		
		try {
			this.socket.connect(remoteAddress);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// molto bello qui gli passiamo l'esito della connessione che verrà
		// memorizzato all'interno della variabile successfully dell'interfaccia Connection
		try {
			this.connection = new TCPConnection(this.socket,this.socket.isConnected());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.connection ;
	}
	
	
}
