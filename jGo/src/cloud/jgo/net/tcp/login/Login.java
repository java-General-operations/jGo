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
package cloud.jgo.net.tcp.login;

/**
 * 
 * @author Martire91<br>
 *         This is login interface
 *
 */
public interface Login {
	/**
	 * The login attempts
	 * 
	 * @return login attempts
	 */
	public abstract int getAttempts();

	/**
	 * This method is the login
	 * 
	 * @param user
	 *            server user
	 * @param passw
	 *            server password
	 * @return the login result
	 */
	public abstract boolean login(String user, String passw);
}
