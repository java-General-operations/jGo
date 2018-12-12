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
package cloud.jgo.net.tcp;

/**
 * 
 * @author Martire91<br>
 *         This exception occurs if the handler has not been set up
 *
 */
public class ModelAbsenceException extends Exception {

	public ModelAbsenceException() {
		// TODO Auto-generated constructor stub
		super(" - No model set");
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Exception in " + getStackTrace()[0].getClassName() + "" + getMessage() + " : \n"
				+ "You need to set up a template for the server #. " + getClass().getName();
	}
}
