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
package cloud.jgo.jjdom.css;
/**
 * This exception occurs when the context is not found
 * @author Martire91<br>
 */
public class ContextNotFoundException extends Exception{
	private String selection;
	public ContextNotFoundException(String selection) {
		// TODO Auto-generated constructor stub
		super(" - Context not found #");
		this.selection = selection ;
	}
	@Override
	public String toString() {
		return "Exception in "+getStackTrace()[0].getClassName()+""
		+ getMessage()+" : \n"+"The '"+this.selection+"' selection had a"
		+ " negative result on the context.\n"+getClass().getName();
	}	
}
