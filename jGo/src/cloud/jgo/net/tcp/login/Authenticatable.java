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
 *         this interface provides the methods for the authentication
 *
 */
public interface Authenticatable {

	/**
	 * Methods that must be implemented
	 */
	/**
	 * This method manages the case in which access is granted
	 */
	public abstract void doAccessGranted();

	/**
	 * This method manages the case in which access is failed
	 */
	public abstract void doAccessFailed();

	/**
	 * This method executes the logout
	 */
	public abstract void logout();

	/**
	 * Check if the user is logged in
	 * 
	 * @return true if the user is logged
	 */
	public abstract boolean isLogged();

	/**
	 * The login attempts
	 * 
	 * @return login attempts
	 */
	public abstract int getAttempts();
}
