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

import java.io.IOException;
import java.net.InetAddress;
/**
 * 
 * @author Martire91<br>
 * This network class, represents an abstract client.
 *
 */
public abstract class Client extends Thread{
	/**
	 * This method opens a connection with the specified host
	 * @param host the remote host
	 * @param port the remote port
	 * @return true if connected
	 * @throws IOException 1 exception
	 */
	public abstract boolean connect(String host,int port) throws IOException;
	/**
	 * This method opens a connection with the specified host
	 * @return true if connected
	 * @throws IOException 1 exception
	 */
    public abstract boolean connect() throws IOException; // questo metodo si connette a gli indirizzi di default e equilvalente al metodo listen() del server
	/**
	 * This method checks the internet connection
	 * @return true if connected
	 */
    public abstract boolean isConnected();
    /**
     * This method starts the client thread
     */
	public abstract void startClient();
	
	/**
	 * This method checks if a host is reachable
	 * @param host the host
	 * @return true if the host is reachable
	 * @throws IOException 1 exception
	 */
    public static boolean isReachable(String host) throws IOException{
		
		InetAddress address = InetAddress.getByName(host);
		
		if(address.isReachable(4000)){
			return true ;
		}
		
		else{
			return false ;
		}
	}
}
