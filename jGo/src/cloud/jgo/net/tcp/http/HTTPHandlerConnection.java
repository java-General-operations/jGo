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
package cloud.jgo.net.tcp.http;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.activation.MimeTypeParseException;

import cloud.jgo.£;
import cloud.jgo.io.File;
import cloud.jgo.net.handlers.Handler;
import cloud.jgo.net.tcp.DefaultSocket;
public abstract class HTTPHandlerConnection implements Handler{
	@Retention(value = RetentionPolicy.RUNTIME)
	@Target(value = { ElementType.METHOD })
	public @interface £_HTTPServer{
		String value();
		String value_1();
	}
	private HTTPRequest request = null ;
	private HTTPResponse response = null ;
	private static boolean notifyConnections = false ;
	// indico quali sono gli stream dedicati
	private DataInputStream in = null ;
	private DataOutputStream out = null ;
	private DefaultSocket socket;
	private String rootFolder ;
	private String infoRequest = null ;
	private HTTPServer server  = null ;
	/**
	 * This method prints the connections in the system.out.println
	 * @param flag if true prints the connections
	 */
	public static void setNotifyConnections(boolean flag){
		
		notifyConnections = flag ;
		
	}
	/**
	 * this method returns true if connection notification is set
	 * @return true if connection notification is set
	 */
	public static boolean isNotifyConnections(){
		
		return notifyConnections ;
		
	}
	/**
	 * This method returns the socket
	 * @return the socket
	 */
	public DefaultSocket getSocket() {
		return this.socket;
	}
	
	private String idSession = null ;
	
	
	@Override
	public void setIdSession(String idSession) {
		this.idSession = idSession;
	}

    private int serverPort = 0 ;
    /**
     * This method returns server port
     * @return the server port
     */
	public int getPort(){
		return this.serverPort ;
	}
	
	@Override
	public Thread startSession() {
		Thread th = null ;
		th = new Thread(this);
		th.start();
		return th ;
	}
	
	/**
	 * This method returns the root folder
	 * @return the root folder
	 */
	public String getRootFolder() {
		return rootFolder;
	}
	
	/**
	 * This method returns the root folder
	 * @return the server root folder
	 */
	public File getServerRootFolder() {
		return serverRootFolder;
	}
	
	private File serverRootFolder = null ;
	
	/**
	 * This method should not be used explicitly
	 * @param server the server
	 */
	public void setServer(HTTPServer server) {
		this.server = server;
	}
	
	/**
	 * This method returns the server
	 * @return the server
	 */
	public HTTPServer getServer() {
		return this.server;
	}
	
	@Override
	public String idSession() {
		return "http_"+£.generateStringRandom(45);
	}

	@£_HTTPServer(value = "request", value_1 = "response")
	public abstract void manage(HTTPRequest request,HTTPResponse response) throws IOException, ClassNotFoundException;
	
	// costruttore che sarà implementato
	public HTTPHandlerConnection(DefaultSocket sck,String rootFolder) throws IOException, MimeTypeParseException {
		// TODO Auto-generated constructor stub
		this.rootFolder = rootFolder ;
		this.serverRootFolder =new File(this.rootFolder);
		this.socket = sck ;
		this.in = new DataInputStream(this.socket.getInputStream());
		this.out = new DataOutputStream(this.socket.getOutputStream());
		// test leggo le informazioni qui
		BufferedReader reader = new BufferedReader(new InputStreamReader(this.in));
		String leggi ;
		StringBuffer buffer = new StringBuffer();
		
		// come primo elemento inserisco l'ip del client
		
		buffer.append("Connection from :"+this.socket.getInetAddress().toString()+"\n");
		
		while ((leggi = reader.readLine())!=null) {
//			System.out.println(leggi);
			buffer.append(leggi+"\n");
			if(leggi.isEmpty()){
				break ;
			}
		}
	// abbiamo ottenuto le informazioni della richiesta con cui costruiremo 
		// la richiesta oggetto
		
		infoRequest = buffer.toString();
		// quindi qui inizializzo l'oggetto request
		this.request = new HTTPRequest(infoRequest,this.rootFolder,this.getPort(),notifyConnections);
		// e qui devo inizializzare anche la risposta
		// per adesso ci passiamo la richiesta e basta
		this.response = new HTTPResponse(this.request,this.out);
		
	}
	public HTTPHandlerConnection() {
		this.socket = null ;
		this.in = null ;
		this.out = null ;
	}
	
	/**
	 * This method should not be used explicitly
	 * @param sck the socket
	 * @param rootFolder the root folder
	 * @throws IOException 1 exception
	 * @throws MimeTypeParseException 2 exception 
	 */
	public void setSocket(DefaultSocket sck,String rootFolder) throws IOException, MimeTypeParseException{
		// TODO Auto-generated constructor stub
				this.rootFolder = rootFolder ;
				this.serverRootFolder = new File(this.rootFolder);
				this.socket = sck ;
				this.in = new DataInputStream(this.socket.getInputStream());
				this.out = new DataOutputStream(this.socket.getOutputStream());
				
				
				// test leggo le informazioni qui
				BufferedReader reader = new BufferedReader(new InputStreamReader(this.in));
				String leggi ;
				StringBuffer buffer = new StringBuffer();
				
				// come primo elemento inserisco l'ip del client
				
				buffer.append("Connection from :"+this.socket.getInetAddress().toString()+"\n");
				
				while ((leggi = reader.readLine())!=null) {
//					System.out.println(leggi);
					buffer.append(leggi+"\n");
					if(leggi.isEmpty()){
						break ;
					}
				}
			// abbiamo ottenuto le informazioni della richiesta con cui costruiremo 
				// la richiesta oggetto
				
				infoRequest = buffer.toString();
				
				
				// quindi qui inizializzo l'oggetto request
				this.request = new HTTPRequest(infoRequest,this.rootFolder,this.getPort(),notifyConnections);
				
				// e qui devo inizializzare anche la risposta
				// per adesso ci passiamo la richiesta e basta
				this.response = new HTTPResponse(this.request,this.out);
	}
	
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			manage(this.request,this.response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
