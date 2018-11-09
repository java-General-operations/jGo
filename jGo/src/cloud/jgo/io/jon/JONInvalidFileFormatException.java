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
package cloud.jgo.io.jon;
/**
 * 
 * @author Martire91<br>
 * This exception occurs when trying to save a jon file with another format.
 *
 */
public class JONInvalidFileFormatException extends Exception{
	private static final long serialVersionUID = 1L;
	private String format;
	public JONInvalidFileFormatException(String format) {
		// TODO Auto-generated constructor stub
		super(" - JON format absence #");
		this.format = format ;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Exception in "+getStackTrace()[0].getClassName()+""
				+ getMessage()+" :\n"+"Format '"+this.format+"' is not valid "+getClass().getName();
	}
}
