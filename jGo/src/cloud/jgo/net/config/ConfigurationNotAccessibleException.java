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
package cloud.jgo.net.config;
/**
 * 
 * @author Martire91
 *	This exception occurs when you do not have permission to make a certain configuration
 */
public class ConfigurationNotAccessibleException extends Exception {
	public ConfigurationNotAccessibleException() {
		// TODO Auto-generated constructor stub
		super(" - Configuration Not accessible #");
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Exception in "+getStackTrace()[0].getClassName()+""
				+ getMessage()+" : \n"+"You do not have permission to set up this configuration."
						+ " These configurations are made by\nthe library it self >\n"+getClass().getName();
	}
}
