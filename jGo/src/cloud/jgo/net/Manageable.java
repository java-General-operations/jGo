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

import cloud.jgo.net.handlers.Handler;
/**
 * 
 * @author Martire91<br>
 * This interface contains methods for setting up or obtaining a manager
 */
public interface Manageable {
	/**
	 * This method sets the handler
	 * @param handler the manager you want to use
	 */
	public abstract void setModel(Handler handler);
	
	/**
	 * This method gets the handler
	 * @return the handler
	 */
	public abstract Handler getModel();
}
