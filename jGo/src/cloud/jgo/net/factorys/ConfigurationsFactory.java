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
package cloud.jgo.net.factorys;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import cloud.jgo.net.Configuration;
import cloud.jgo.net.tcp.TCPServerConfiguration;

public class ConfigurationsFactory {

	
	// contains method factory
	
	
	public static Configuration getConfiguration(java.io.File from) throws FileNotFoundException, IOException{
		
		TCPServerConfiguration configuration = new TCPServerConfiguration();
		
		configuration = (TCPServerConfiguration) configuration.loadConfiguration(new FileInputStream(from));
		
		return configuration ;
	}
	
	
}
