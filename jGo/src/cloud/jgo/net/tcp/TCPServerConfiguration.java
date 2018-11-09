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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

import cloud.jgo.net.Configuration;
import cloud.jgo.net.Server;
import cloud.jgo.net.ServerType;
import cloud.jgo.net.ServerTypes;
import cloud.jgo.net.handlers.Handler;
import cloud.jgo.net.handlers.HandlerConnection;
/**
 * 
 * @author Martire91<br>
 * TCP server configuration,
 * and is an implementation of {@link Server}
 *
 */
public class TCPServerConfiguration implements Configuration{
	public final static String KEY_MULTI_CONNECTIONS = "jgo.net.multi_connections";
	public final static String KEY_ACCEPTED_SOCKET = "jgo.net.accepted_socket";
	public final static String KEY_MAXIMUM_SOCKETS = "jgo.net.maximum.sockets";
	public final static String KEY_SERVER_MODEL = "jgo.net.server.model";
	// informazioni configurazione server pc
	public final static String KEY_OS_NAME = "jgo.net.server.os.name";
	public final static String KEY_OS_VERSION = "jgo.net.server.os.version";
	public final static String KEY_OS_ARCH = "jgo.net.server.os.arch";
	public final static String KEY_OS_USERNAME = "jgo.net.server.os.user.name";
	public final static String KEY_SERVER_PATH = "jgo.net.server.path";
	public final static String KEY_OS_JAVA_VERSION = "jgo.net.server.os.java.version";
	public final static String KEY_BLOCKING_ACCEPTANCE = "jgo.net.server.blocking_acceptance";
	public final static String KEY_DEFAULT_PRINT_FOR_SOCKET_ACCEPTANCE = "jgo.net.default_print_for_acceptance_socket";
	public static ServerType SERVER_TYPE = ServerTypes.TYPE_TCP ;
	protected int counterSettings = 0 ;
	private String osName,osVersion,osArch,osUsername,serverPath,javaVersion = null ;
	private boolean defaultPrintforSocketAcceptance = false ; // default value
	private Logger logger = null ;
	
	/**
	 * Includes the java version in configuration
	 */
	public void setJavaVersion(){
		this.javaVersion = System.getProperty("java.version");
		if(!this.props.containsKey(KEY_OS_JAVA_VERSION)){
			this.props.put(KEY_OS_JAVA_VERSION,this.javaVersion);
			this.counterSettings++ ;
		}
		else{
			// se è gia contenuta la chiave cambiamo solo il valore
		 this.props.replace(KEY_OS_JAVA_VERSION,this.javaVersion);
		 // ho sostituito il valore della chiave contenuta
		 // test se funziona
		}
	}
	
	public TCPServerConfiguration() {
		// auto imposto il logger 
		this.logger = Logger.getLogger(TCPServer.class.getName());
		// okok abbiamo impostato il logger, poi sarà compito dell'utente personalizzarlo
	}

	/**
	 * 
	 * @param defaultPrintforSocketAcceptance if true sets a default text for accepting connections
	 */
	public void setDefaultPrintforSocketAcceptance(boolean defaultPrintforSocketAcceptance) {
		this.defaultPrintforSocketAcceptance = defaultPrintforSocketAcceptance;
		if(!this.props.containsKey(KEY_DEFAULT_PRINT_FOR_SOCKET_ACCEPTANCE)){
			this.props.put(KEY_DEFAULT_PRINT_FOR_SOCKET_ACCEPTANCE,this.defaultPrintforSocketAcceptance);
			this.counterSettings++ ;
		}
		else{
		 this.props.replace(KEY_DEFAULT_PRINT_FOR_SOCKET_ACCEPTANCE,this.defaultPrintforSocketAcceptance);
		}
	}
	/**
	 * 
	 * @return the text that is shown when accepting connections
	 */
	public boolean isDefaultPrintforSocketAcceptance() {
		return this.defaultPrintforSocketAcceptance;
	}
	
	/**
	 * This method sets the server path
	 * @param server_path server path
	 */
	public void setServerPath(String server_path){
		
		this.serverPath = server_path ;
		if(!this.props.containsKey(KEY_SERVER_PATH)){
			this.props.put(KEY_SERVER_PATH,this.serverPath);
			this.counterSettings++ ;
		}
		else{
			// se è gia contenuta la chiave cambiamo solo il valore
		 this.props.replace(KEY_SERVER_PATH,this.serverPath);
		 // ho sostituito il valore della chiave contenuta
		 // test se funziona
		}
		
	}
	
	
	/**
	 * Includes the username in configuration
	 */
	public void setOsUsername(){
		this.osUsername = System.getProperty("user.name");
		if(!this.props.containsKey(KEY_OS_USERNAME)){
			this.props.put(KEY_OS_USERNAME,this.osUsername);
			this.counterSettings++ ;
		}
		else{
			// se è gia contenuta la chiave cambiamo solo il valore
		 this.props.replace(KEY_OS_USERNAME,this.osUsername);
		 // ho sostituito il valore della chiave contenuta
		 // test se funziona
		}
	}	
	
	/**
	 * Includes the architecture type of the operating system
	 */
	public void setOsArch(){
		
		this.osArch = System.getProperty("os.arch");
		if(!this.props.containsKey(KEY_OS_ARCH)){
			this.props.put(KEY_OS_ARCH,this.osArch);
			this.counterSettings++ ;
		}
		else{
			// se è gia contenuta la chiave cambiamo solo il valore
		 this.props.replace(KEY_OS_ARCH,this.osArch);
		 // ho sostituito il valore della chiave contenuta
		 // test se funziona
		}
		
	}
	
	
	/**
	 * Includes operating system version in configuration
	 */
	public void setOsVersion(){
		
		this.osVersion = System.getProperty("os.version");
		if(!this.props.containsKey(KEY_OS_VERSION)){
			this.props.put(KEY_OS_VERSION,this.osVersion);
			this.counterSettings++ ;
		}
		else{
			// se è gia contenuta la chiave cambiamo solo il valore
		 this.props.replace(KEY_OS_VERSION,this.osVersion);
		 // ho sostituito il valore della chiave contenuta
		 // test se funziona
		}
		
	}

	/**
	 * Includes the operating system name in configuration
	 */
	public void setOsName(){
		this.osName = System.getProperty("os.name");
		if(!this.props.containsKey(KEY_OS_NAME)){
			this.props.put(KEY_OS_NAME,this.osName);
			this.counterSettings++ ;
		}
		else{
			// se è gia contenuta la chiave cambiamo solo il valore
		 this.props.replace(KEY_OS_NAME,this.osName);
		 // ho sostituito il valore della chiave contenuta
		 // test se funziona
		}
	}

	protected Handler model = null ;
	private InetAddress serverAddress = null ;
	private ServerType serverType = null ;
	
	/**
	 * Includes the handler in configuration
	 * @param handler the handler
	 */
	public void setModel(Handler handler) {
		if(handler instanceof HandlerConnection){
			this.model =(HandlerConnection) handler ;
			if(!this.props.containsKey(KEY_SERVER_MODEL)){
				this.props.put(KEY_SERVER_MODEL,this.model.getClass().getSimpleName());
				this.counterSettings++ ;
			}
			else{
				// se è gia contenuta la chiave cambiamo solo il valore
			 this.props.replace(KEY_SERVER_MODEL,this.model.getClass().getSimpleName());
			 // ho sostituito il valore della chiave contenuta
			 // test se funziona
			}
		}
	}
	/**
	 * 
	 * @return the handler
	 */
	public Handler getModel() {
		return this.model;
	}
	
	public static String getKeyMaximumSockets() {
		return KEY_MAXIMUM_SOCKETS;
	}
	private Properties props = new Properties();
	public Properties getProps() {
		return props;
	}
	private int timer = 0 ;
	/**
	 * 
	 * @return the timer value
	 */
	public int getTimer() {
		return this.timer;
	}

	/**
	 * The text that is shown when accepting a socket
	 * @return the text
	 */
	public String getAcceptedSocket() {
		return acceptedSocket;
	}

	/**
	 * This method sets The text that is shown when accepting a socket
	 * @param acceptedSocket the text
	 */
	public void setAcceptedSocket(String acceptedSocket) {
		this.acceptedSocket = acceptedSocket;
		if(!this.props.containsKey(KEY_ACCEPTED_SOCKET)){
			this.props.put(KEY_ACCEPTED_SOCKET,this.acceptedSocket);
			this.counterSettings++ ;
		}
		else{
			// se è gia contenuta la chiave cambiamo solo il valore
		 this.props.replace(KEY_ACCEPTED_SOCKET,this.acceptedSocket);
		 // ho sostituito il valore della chiave contenuta
		 // test se funziona
		}
		
	}

	
	/**
	 * The maximum number of acceptable sockets
	 * @return the maximum sockets
	 */
	public Integer getMaximumSockets() {
		return maximumSockets;
	}

	/**
	 * This method sets the maximum number of acceptable sockets
	 * @param maximumSockets the maximum number
	 */
	public void setMaximumSockets(Integer maximumSockets) {
		this.maximumSockets = maximumSockets;
		if(!this.props.containsKey(KEY_MAXIMUM_SOCKETS)){
			this.props.put(KEY_MAXIMUM_SOCKETS,String.valueOf(this.maximumSockets));
			this.counterSettings++ ;
		}
		else{
			this.props.replace(KEY_MAXIMUM_SOCKETS,String.valueOf(this.maximumSockets));
		}
		
	}

	/**
	 * 
	 * @return true if the server is set up for multi-connection
	 */
	public boolean isMultiConnections() {
		return multiConnections;
	}

	/**
	 * This method sets the multiple connection
	 * @param multiConnections the flag
	 */
	public void setMultiConnections(boolean multiConnections) {
		this.multiConnections = multiConnections;
		if(!this.props.containsKey(KEY_MULTI_CONNECTIONS)){
			this.props.put(KEY_MULTI_CONNECTIONS,String.valueOf(this.multiConnections));
			this.counterSettings++ ;
		}
		else{
			this.props.replace(KEY_MULTI_CONNECTIONS,String.valueOf(this.multiConnections));
		}
		
	}

	/**
	 * This method returns the local port
	 * @return local port
	 */
	public Integer getLport() {
		return lport;
	}

	@Override
	public void setLport(Integer lport) {
		this.lport = lport;
		if(!this.props.containsKey(KEY_LPORT)){
			this.props.put(KEY_LPORT, String.valueOf(lport));
			this.counterSettings++ ;
		}
		else{
			this.props.replace(KEY_LPORT,String.valueOf(this.lport));
		}
		
	}
	
	public String getProperty(String key){
		
		
		return this.props.getProperty(key);
		
	}

	

	private String acceptedSocket = null;
	private Integer maximumSockets = null ;
	private boolean multiConnections = false ;
	private Integer lport = null ;
	private String nameServer=null;
	
	


	@Override
	public void setTimer(int sec) {
		// TODO Auto-generated method stub
		timer = sec ;
		if(!this.props.containsKey(KEY_TIMER)){
			this.props.put(KEY_TIMER,timer);
			this.counterSettings++ ;
		}
		else{
			this.props.replace(KEY_TIMER, timer);
		}
		
	}

	

	@Override
	public void setServerName(String nameServer) {
	
		this.nameServer = nameServer ;
		if(!this.props.containsKey(KEY_SERVER_NAME)){
			this.props.put(KEY_SERVER_NAME, nameServer);
			this.counterSettings++ ;
		}
		else{
			this.props.replace(KEY_SERVER_NAME, nameServer);
		}
		
	}

	@Override
	public String getServerName() {
		// TODO Auto-generated method stub
		return this.nameServer ;
	}

	@Override
	public Configuration loadConfiguration(FileInputStream fis) throws IOException{
		
		this.props.clear(); // cancello tutto le proprietà
		this.props.load(fis);
		fis.close();
		
		
		// a questo punto devo ricavare l'oggetto
		TCPServerConfiguration configuration = new TCPServerConfiguration();
		
		if(this.props.containsKey(KEY_ACCEPTED_SOCKET)){
			configuration.setAcceptedSocket(this.props.getProperty(KEY_ACCEPTED_SOCKET));
		}
		if(this.props.containsKey(KEY_ADDRESS)){
			configuration.setLocalhost();
		}
		if(this.props.containsKey(KEY_MAXIMUM_SOCKETS)){
			configuration.setMaximumSockets(Integer.parseInt(this.props.getProperty(KEY_MAXIMUM_SOCKETS)));
			
		}
		if(this.props.containsKey(KEY_LPORT)){
			configuration.setLport(Integer.parseInt(this.props.getProperty(KEY_LPORT)));
		}
		if(this.props.containsKey(KEY_MULTI_CONNECTIONS)){
			if(this.props.getProperty(KEY_MULTI_CONNECTIONS).equals("true")){
				configuration.setMultiConnections(true);
			}
			else{
				configuration.setMultiConnections(false);
			}
		}
		if(this.props.containsKey(KEY_SERVER_NAME)){
			configuration.setServerName(this.props.getProperty(KEY_SERVER_NAME));
		}
		if(this.props.containsKey(KEY_TIMER)){
			configuration.setTimer(Integer.parseInt(this.props.getProperty(KEY_TIMER)));
		}
		
		return configuration ;
		
	}

	@Override
	public void saveConfiguration(FileOutputStream fos, String comment) {
		this.props.save(fos, comment);
	}

	@Override
	public void setLocalhost(){
		try {
			serverAddress = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (serverAddress!=null) {
			if(!this.props.containsKey(KEY_ADDRESS)){
				this.props.put(KEY_ADDRESS,serverAddress);
				this.counterSettings++ ;
			}
			else{
				this.props.replace(KEY_ADDRESS,serverAddress);
			}
		}
	}
	@Override
	public InetAddress getLocalHost() {
		return this.serverAddress ;
	}

	
	@Override
	public void saveXMLConfiguration(FileOutputStream fos, String comment){
		try {
			this.props.storeToXML(fos, comment);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @return the OS name
	 */
	public String getOsName() {
		return osName;
	}

	/**
	 * 
	 * @return the OS version
	 */
	public String getOsVersion() {
		return osVersion;
	}

	/**
	 * 
	 * @return the OS architecture
	 */
	public String getOsArch() {
		return osArch;
	}

	/**
	 * 
	 * @return the OS username
	 */
	public String getOsUsername() {
		return osUsername;
	}

	/**
	 * 
	 * @return the server path
	 */
	public String getServerPath() {
		return serverPath;
	}

	/**
	 * 
	 * @return the java version
	 */
	public String getJavaVersion() {
		return javaVersion;
	}

	@Override
	public int getSettingsCounter() {
		return this.counterSettings;
	}

	@Override
	public StringBuffer AllConfigurations() {
		Set<Entry<Object, Object>> settings = this.props.entrySet();
		StringBuffer buffer = new StringBuffer();
		Iterator<Entry<Object, Object>> itera = settings.iterator();
		while (itera.hasNext()) {
			Map.Entry<java.lang.Object, java.lang.Object> entry = (Map.Entry<java.lang.Object, java.lang.Object>) itera
					.next();
			String key = (String) entry.getKey();
			Object setting = entry.getValue();
			buffer.append(key+"="+setting+"\r\n");
		}
		return buffer;
	}

	@Override
	public Logger getLogger() {
		// TODO Auto-generated method stub
		return this.logger;
	}
}
