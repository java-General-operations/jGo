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
package cloud.jgo.net.handlers;
/**
 * 
 * @author Martire91<br>
 * This interface represents the handler.
 * (Product)
 */
public interface Handler extends Runnable{
	/**
	 * 
	 * @return the session id
	 */
	public abstract String idSession();
	/**
	 * This method sets the session id
	 * @param id_session the session id
	 */
	public abstract void setIdSession(String id_session);
	/**
	 * This method starts the session
	 * @return the session thread
	 */
	public abstract Thread startSession(); // avvia il thread e ci restituisce lo stesso
}
