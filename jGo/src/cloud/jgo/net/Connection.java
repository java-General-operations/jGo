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

/**
 * 
 * @author Martire91<br>
 * This class this class represents a connection
 */
public interface Connection {
    /**
     * This method sends an object to the other side of the connection
     * @param object the object you want to send
     */
	public abstract void send(Object object);
	
	/**
	 * This method receives an object on the other side of the connection
	 * @return the received object
	 */
	public abstract Object receive();
	/**
	 * Indicates if the connection was successful
	 * @return true if it is established
	 */
	public abstract boolean successfully();
	/**
	 * This method closes the connection
	 */
	public abstract void closeConnection();
	
	/**
	 * Indicates if the outlet is connected or not
	 * @return true if connected
	 */
	public abstract boolean isSocketConnected();
	
}
