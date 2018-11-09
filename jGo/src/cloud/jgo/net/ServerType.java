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
 * This class is the abstract server type
 *
 */
final public class ServerType {
	public String TYPE ;
	public int VALUE ;
	public ServerType(String type, int value) {
		this.TYPE = type;
		this.VALUE = value;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.TYPE ;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		ServerType type = (ServerType)obj ;
		boolean result=false ;
		if(type.TYPE.equalsIgnoreCase(this.TYPE)&&type.VALUE==this.VALUE){
			result = true ;
		}
		return result ;
	}
	
}
