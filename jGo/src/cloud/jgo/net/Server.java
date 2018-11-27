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
import java.net.SocketException;
import java.net.UnknownHostException;

import cloud.jgo.net.config.Configuration;
import cloud.jgo.net.tcp.NegativeListeningException;
import cloud.jgo.net.tcp.NoReadingSourceException;
/**
 * 
 * @author Martire91<br>
 * This class is an abstract server
 */
public interface Server extends Runnable{

	/**
	 * Let the server listen
	 * @param localPort the port on which you want to put the server listening
	 * @param positiveListen the text string in case the server can listen
	 * @param negativeListen the text string in case the server can not listen
	 * @throws NoReadingSourceException 1 exception : if you do not set up a read source, this exception is generated
	 * @throws NegativeListeningException 2 exception : it is generated if listening is not successful
	 * @throws IOException 3 exception
	 */
	public abstract void listen(int localPort,String positiveListen,String negativeListen) throws IOException, NoReadingSourceException, NegativeListeningException;
	/**
	 * Let the server listen
	 * @param LHOST the localhost
	 * @param LPORT the local port
	 * @throws NegativeListeningException : it is generated if listening is not successful
	 * @throws IOException 2 excpetion
	 */
	public abstract void listen(String LHOST,int LPORT) throws NegativeListeningException, IOException;
	/**
	 * Let the server listen
	 * @throws UnknownHostException 1 exception : it occurs if the host is unknown
	 * @throws NegativeListeningException 2 exception : it is generated if listening is not successful
	 */
	public abstract void listen() throws UnknownHostException, IOException, NegativeListeningException; // questo metodo usa i dati di default riferiti al computer locale
	/**
	 * Returns the local port
	 * @return the local port
	 */
	public abstract int getLocalPort();
	/**
	 * This method sets the local port
	 * @param localPort the port you want to use
	 */
	public abstract void setLocalPort(int localPort);
	/**
	 * This method returns information on the server
	 * @return the info
	 * @throws UnknownHostException 1 exception :it occurs if the host is unknown
	 */
	public abstract String[]getInfoServerSystem() throws UnknownHostException;
	/**
	 * 
	 * @return true if the server is in listen
	 */
	public abstract boolean isInListen();
	/**
	 * 
	 * @return true if the server is close
	 */
	public abstract boolean isClosed();
	/**
	 * This method closes the server
	 */
	public abstract void closeServer();
	/**
	 * This method starts the server
	 */
	public abstract void startServer();
	/**
	 * 
	 * @return true if the server is configurated
	 */
	public abstract boolean isConfigurated();
	/**
	 * Returns an object that can be used in connections
	 * @return the object
	 */
	public abstract Object getObject(); // questo metodo mi serve per impostare un oggetto è usarlo poi nell'handler
	/**
	 * This method configures the server
	 * @param configuration the server configuration
	 * @throws SocketException 1 exception
	 */
	public abstract void configure(Configuration configuration);
	/**
	 * This method returns the server configuration
	 * @return the server configuration
	 */
	public abstract Configuration getConfiguration2();
	/**
	 * This method sets the reading source
	 * @param source the reading source
	 */
	public abstract void setReadFrom(Object source);
	/**
	 * This method sets the writing source
	 * @param source the writing source
	 * @throws IOException 1 exception
	 */
	public abstract void setWriteFrom(Object source) throws IOException;
	/**
	 * This method check if the door is busy
	 * @param address the localhost
	 * @param serverPort the local port
	 * @return true if the port is occupied
	 */
	public abstract boolean isOccupied(String address,int serverPort);
	
	/**
	 * This method returns the server name
	 * @return the server name
	 */
	public abstract String getServerName();
	/**
	 * 
	 * @author Martire91
	 *
	 */
	public interface Closable {
		public abstract void closeServer() throws IOException;
	}
}
