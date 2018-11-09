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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
/**
 * 
 * @author Martire91<br>
 * This class allows you to configure a server
 *
 */
import java.util.logging.Logger;
public interface Configuration {
	public final static String KEY_ADDRESS = "jgo.net.localhost";
	public final static String KEY_LPORT = "jgo.net.lport";
	public final static String KEY_SERVER_NAME = "jgo.net.server.name";
	public final static String KEY_SERVER_TYPE = "jgo.net.server.type";
	public final static String KEY_TIMER = "jgo.net.timeout";
	/**
	 * This method sets a timer for the server
	 * @param sec the seconds
	 */
	public abstract void setTimer(int sec);
	/**
	 * This method saves this configuration to a file
	 * @param fos the outputstream
	 * @param comment the comment
	 */
	public abstract void saveConfiguration(FileOutputStream fos,String comment);
	/**
	 * This method saves this configuration to a xml file
	 * @param fos the outputstream
	 * @param comment the comment
	 */
	public abstract void saveXMLConfiguration(FileOutputStream fos,String comment);
	/**
	 * This method configures the localhost
	 */
	public abstract void setLocalhost();
	/**
	 * 
	 * @return the localhost
	 */
	public abstract InetAddress getLocalHost();
	/**
	 * This method sets the server name
	 * @param nameServer the server name
	 */
	public abstract void setServerName(String nameServer);
	/**
	 * This method returns the server name
	 * @return server name
	 */
	public abstract String getServerName();

	/**
	 * This method loads a configuration
	 * @param fis the inputStream
	 * @return the configuration
	 * @throws IOException 1 exception
	 */
	public Configuration loadConfiguration(FileInputStream fis) throws IOException;
	
	/**
	 * This method sets the local_Port
	 * @param lport the local port
	 */
	public abstract void setLport(Integer lport);
	
	/**
	 * 
	 * @return the settings number
	 */
	public abstract int getSettingsCounter(); // questo metodo indica il numero delle impostazioni settate
   
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
	
}
