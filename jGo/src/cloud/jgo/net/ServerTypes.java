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
 * <p>This class collects all types of servers</p>
 *
 */
public class ServerTypes {

	
	// qui tutte una serie di costanti 
	
	public final static ServerType TYPE_TCP = new ServerType("TCP", 0);
	/**
	 * <p style='color:red'>Still not valid</p>
	 */
	public final static ServerType TYPE_UDP = new ServerType("UDP", 1);
	
	
	public ServerType getServerType(String proto){
		ServerType type = null ;
		if(proto.equalsIgnoreCase(TYPE_TCP.TYPE)){
			
			type = TYPE_TCP ;
		}
		else if(proto.equalsIgnoreCase(TYPE_UDP.TYPE)){
			type = TYPE_UDP ;
		}
		return type ;	
	};
	
	
	
}
