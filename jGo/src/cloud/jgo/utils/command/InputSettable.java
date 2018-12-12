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
package cloud.jgo.utils.command;

// version 1.0.7
/**
 * 
 * @author Martire91
 * The class that extends this interface will<br>
 * have methods to exploit and set values from input.<br>
 * Valid mechanism with commands and parameters.
 *
 */
public interface InputSettable{
	/**
	 * This method gets the input value
	 * @return the input value
	 */
	public abstract String getInputValue();
	/**
	 * This method sets the input value
	 * @param inputValue the input value
	 */
	public abstract void setInputValue(String inputValue);
	/**
	 * Indicates if the value from input is exploitable or not
	 * @return true if it is exploitable
	 */
	public abstract boolean hasInputValueExploitable();
	/**
	 * Sets the exploitation of the value from input
	 * @param exploitable the flag
	 */
	public abstract void setInputValueExploitable(boolean exploitable);
}
