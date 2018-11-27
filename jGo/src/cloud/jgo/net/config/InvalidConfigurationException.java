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
 * This exception occurs when the configuration you are trying to make is incorrect.
 */
public class InvalidConfigurationException extends Exception{
	private String value;
	public InvalidConfigurationException(String value) {
		// TODO Auto-generated constructor stub
		super(" - Invalid configuration #");
		this.value = value ;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Exception in "+getStackTrace()[0].getClassName()+""
				+ getMessage()+" : \n"+"This configuration has not been recognized ("+value+"),\n"
				+ "make sure that the type of configuration but also the value of the same is correct. >\n"+getClass().getName();
	}
}
