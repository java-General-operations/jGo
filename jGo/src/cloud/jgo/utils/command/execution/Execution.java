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
package cloud.jgo.utils.command.execution;

import java.io.Serializable;
/**
 * 
 * @author Martire91<br>
 * This class represents the execution
 */
public abstract class Execution implements Serializable {
	protected static final long serialVersionUID = 1L;
	/**
	 * This method starts the execution
	 * @return the object returned from execution
	 */
	public abstract Object exec(); 
}
