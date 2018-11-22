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
import java.util.Dictionary;
import java.util.HashMap;
import java.util.logging.Logger;



public interface Configuration2{
	public final static String PORT = "jgo.net.port";
	public final static String TIMER = "jgo.net.timeout";
		/**
		 * 
		 * @return all configurations in the form of a string
		 */
		public abstract StringBuffer AllConfigurations(); // stampa tutte le configurazioni
		/**
		 * This method returns the server logger
		 * @return the server logger
		 */
		public abstract Logger getLogger();
		/**
		 * 
		 * @return the settings number
		 */
		public abstract int getSettingsCounter();
}
