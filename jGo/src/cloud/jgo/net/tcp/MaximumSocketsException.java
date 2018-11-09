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
 * This exception occurs when the maximum number of acceptable sockets is reached
 *
 */
public class MaximumSocketsException extends Exception{

	
	public MaximumSocketsException() {
		// TODO Auto-generated constructor stub
		super(" - Reached maximum number");
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Exception in "+getStackTrace()[0].getClassName()+""
				+ getMessage()+" : \n"+"The maximum number of acceptable sockets has been reached # \n"+getClass().getName();
	}
	
	
	
	
}
