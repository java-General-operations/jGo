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
import cloud.jgo.net.config.Configuration;
/**
 * 
 * @author Martire91<br>
 * This class is the creator for the servers
 *
 */
public abstract class ServerFactory {

	
	/**
	 * This method creates a server instance
	 * @param type the server type
	 * @param localPort the local port
	 * @return the server
	 */
	public abstract Server createServer(int type,int localPort);
	/**
	 * This method creates a server instance
	 * @param configuration the server configuration
	 * @return the server
	 * @throws SocketException 1 exception
	 */
	public abstract Server createServer(Configuration configuration) throws SocketException;
	
}
