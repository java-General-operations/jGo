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
 * This interface is the opposite of Control
 */
public interface Controllable {

	/**
	 * This method executes the command received remotely
	 * @return the object that returns the command
	 */
	public abstract Object executeRemoteCommand(); 
	/**
	 * This method executes the command received remotely
	 * @return the object that returns the command
	 */
	public abstract Object execute_cmd();
	
	
	
	
}
