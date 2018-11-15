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
// version 1.0.5
package cloud.jgo;
import java.util.Hashtable;
import java.util.Properties;
/**
 * 
 * @author Martire91
 * This class is a collection of smtp hosts
 * Facilitates the use of jGo methods to send emails.
 */
public class SMTPHosts extends Hashtable<String,java.util.Map.Entry<String,Integer>>{
	
	private static SMTPHosts instance = null ;
	// mi costruisco un costruttore privato, cosi non faccio creare instanze di questa classe
	private SMTPHosts() {
		// qui dentro mi creo tutti gli host
		
		
		
	}
	
	public static SMTPHosts getInstance(){
		if (instance==null) {
			instance = new SMTPHosts();
		}
		return instance;
	}
}
