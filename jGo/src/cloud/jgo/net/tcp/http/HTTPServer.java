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
import java.io.IOException;
import java.net.SocketException;

import javax.activation.MimeTypeParseException;

import cloud.jgo.£;
import cloud.jgo.io.File;
import cloud.jgo.net.ServerType;
import cloud.jgo.net.config.Configuration;
import cloud.jgo.net.config.HTTPServerConfiguration;
import cloud.jgo.net.config.TCPServerConfiguration;
import cloud.jgo.net.factorys.ServersFactory;
import cloud.jgo.net.handlers.Handler;
import cloud.jgo.net.tcp.NoReadingSourceException;
import cloud.jgo.net.tcp.NotSupportedModelException;
import cloud.jgo.net.tcp.TCPServer;
import cloud.jgo.net.tcp.TCPServerTypes;
/**
 * @author Martire91<br>
 * This class represents a simple http server,
 * and is a subclass of {@link TCPServer}
 */
public class HTTPServer extends TCPServer{
	private String rootFolder = £.getHomeDirectory().getPath();
	private String nameRootFolder = null ;
	public final static String HTTP_VERSION = "HTTP/1.0";
	private static String decoder_type = "UTF-8"; // default value
	private HTTPServerConfiguration configuration2 = new HTTPServerConfiguration();
	public static String getDecoder_type() {
		return decoder_type;
	}

	static{
		DEFAULT_PORT = 80 ;
		TYPE_SERVER = TCPServerTypes.TYPE_HTTP ;
	}
	
	@Override
	protected ServerType getType() {
		// TODO Auto-generated method stub
		return new TCPServerTypes().TYPE_HTTP ;
	}
	@Override
	public HTTPServerConfiguration getConfiguration2() {
		// TODO Auto-generated method stub
		return (HTTPServerConfiguration) super.getConfiguration2();
	}
	@Override
	protected void acceptRequestsFromClientsWithModel() throws IOException, CloneNotSupportedException,
			NoReadingSourceException, InstantiationException, IllegalAccessException {

		boolean loopInfinity = true ;
		while(loopInfinity){
			
			this.acceptedConnection = this.acceptRequest(); // qui diciamo che se la socket restituita è diversa da null si agisce
			if(this.acceptedConnection!=null){			
							// Ora qui devo ricavare che tipo di handler è stato impostato
							
							Class classeModel = getModel().getClass();
							Handler newInstance = null ;
							
							newInstance =  (Handler) classeModel.newInstance();
							if(this instanceof HTTPServer){
								if(((HTTPServer)this).getRootFolder()!=null){
								
								try {
									((cloud.jgo.net.tcp.http.HTTPHandlerConnection)newInstance).setSocket(this.acceptedConnection,this.rootFolder);
								} catch (MimeTypeParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								((cloud.jgo.net.tcp.http.HTTPHandlerConnection)newInstance).setServer(this);
								handlers.add(newInstance);
									
								}
							}
							// attivo l'handler 
							Thread th=newInstance.startSession();
							if (isOneConnectionAtATime()) {
							try {
								th.join();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							}
			}
			else{
				/*
				 * JGO NON SO SE DA PROBLEMI EVENTUALMENTE TOGLIERE TUTTO QUESTO CODICE DELL'ELSE
				 */
				
				/*
				 * Qui vuol dire che non si accettano più sockets e quindi chiudiamo il server
				 */
					
					this.closeServer();
					
					// e qui chiudiamo il metodo usando return ,tanto ormai sono gli handlers threads a pensare a gestire le connessioni
					
					return ;
			}
		   
		}
		
	}
	public HTTPServer() {
	}
	@Override
	protected void acceptRequestWithModel() throws InstantiationException, IllegalAccessException{
		this.acceptedConnection  = null ;
		try {
			this.acceptedConnection = this.acceptRequest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoReadingSourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // qui diciamo che se la socket restituita è diversa da null si agisce
		if(this.acceptedConnection!=null){			
						// Ora qui devo ricavare che tipo di handler è stato impostato
						
						Class classeModel = model.getClass();
					    Handler newInstance = (Handler) classeModel.newInstance();
						
						if(this instanceof HTTPServer){
							if(((HTTPServer)this).getRootFolder()!=null){
								
									// do per scontato
									try {
										try {
											((cloud.jgo.net.tcp.http.HTTPHandlerConnection)newInstance).setSocket(this.acceptedConnection, this.rootFolder);
										} catch (MimeTypeParseException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									((cloud.jgo.net.tcp.http.HTTPHandlerConnection)newInstance).setServer(this);
									handlers.add(newInstance);
								
							}
							
						}
						// attivo l'handler 
						newInstance.startSession();
		}
		else{
			/*
			 * JGO NON SO SE DA PROBLEMI EVENTUALMENTE TOGLIERE TUTTO QUESTO CODICE DELL'ELSE
			 */
			
			this.closeServer();
				
				// e qui chiudiamo il metodo usando return ,tanto ormai sono gli handlers threads a pensare a gestire le connessioni
				
				return ;
		}
	}
	@Override
	public void configure(Configuration configuration) {
		super.configure(configuration);
		this.configuration2 = configuration2 ;
		if (this.configuration2.containsKey(HTTPServerConfiguration.ROOT_FOLDER)) {
			setRootFolder(this.configuration2.getConfig(HTTPServerConfiguration.ROOT_FOLDER));
		}
		if (this.configuration2.containsKey(HTTPServerConfiguration.HANDLER_MODEL)) {
			setModel(this.configuration2.getConfig(HTTPServerConfiguration.HANDLER_MODEL));
		}
	}
	@Override
	public void reloadConfiguration() {
		configure(this.configuration2);
	}
	
	@Override
	public void setServerName(String nameServer) {
		// TODO Auto-generated method stub
		super.setServerName(nameServer);
		this.configuration2.put(HTTPServerConfiguration.SERVER_NAME,getServerName());
	}
	
	
	@Override
	public void setLocalPort(int localPort) {
		// TODO Auto-generated method stub
		super.setLocalPort(localPort);
		this.configuration2.put(HTTPServerConfiguration.LPORT,getLocalPort());
	}
	
	@Override
	public void setTextOfAcceptedSocket(String output) {
		// TODO Auto-generated method stub
		super.setTextOfAcceptedSocket(output);
		this.configuration2.put(HTTPServerConfiguration.ACCEPTED_SOCKET,getTextOfAcceptedSocket());
	}

	@Override
	public void setMultiConnections(boolean multiConnections) {
		// TODO Auto-generated method stub
		super.setMultiConnections(multiConnections);
		this.configuration2.put(HTTPServerConfiguration.MULTI_CONNECTIONS,isMultiConnections());
	}

	@Override
	public void setModel(Handler handler) {
		if(handler instanceof HTTPHandlerConnection){
			this.model =  handler ;
			this.configuration2.put(HTTPServerConfiguration.HANDLER_MODEL,this.model);
		}
		else{
			this.model = null ;
			try {
				throw new NotSupportedModelException();
			} catch (NotSupportedModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * This method sets the decoder type
	 * @param decoder_type the decoder type
	 */
	public static void setDecoder_type(String decoder_type) {
		HTTPServer.decoder_type = decoder_type;
	}
	public HTTPServer(String rootFolder) {
		// TODO Auto-generated constructor stub
		this.rootFolder = rootFolder ;
		if(new File(rootFolder).exists() && new File(rootFolder).isDirectory()){
		// va bene
			this.nameRootFolder = new File(rootFolder).getName();
			// casomai cancellare da qui
			getConfiguration2().put(HTTPServerConfiguration.ROOT_FOLDER,this.rootFolder);
		}
	}
	// qui ridefiniamo l'override del metodi isConfigurated 
	// in base alle esigenze di questo server
	@Override
	public boolean isConfigurated() {
		boolean tcpConfigurated = super.isConfigurated();
		if(tcpConfigurated == false){
			return false ;
		}
		else{
			if (this.configuration2.contains(HTTPServerConfiguration.ROOT_FOLDER)&&
				new File(this.configuration2.getConfig(HTTPServerConfiguration.ROOT_FOLDER)).exists()&&
				new File(this.configuration2.getConfig(HTTPServerConfiguration.ROOT_FOLDER)).isDirectory()) {
				return true ;
			}
			else{
				return false ;
			}
		}
	}
	/**
	 * Method factory
	 * @param configuration the server configuration
	 * @return the http server
	 * @throws SocketException 1 exception
	 */
	public static HTTPServer creates(HTTPServerConfiguration configuration) throws SocketException{
		ServersFactory factory = ServersFactory.getInstance();
		return (HTTPServer)factory.createServer(configuration);
	}
	
	/**
	 * This method sets the root folder
	 * @param rootFolder the root folder
	 */
	public void setRootFolder(String rootFolder) {
		 this.rootFolder = rootFolder ;
		if(new File(rootFolder).exists() && new File(rootFolder).isDirectory()){
				this.nameRootFolder = new File(rootFolder).getName();
				this.configuration2.put(HTTPServerConfiguration.ROOT_FOLDER,this.rootFolder);
			}
		else{
			System.err.println("Root folder is not exist #");
		}
	}
	/**
	 * This method returns the root folder name
	 * @return root folder name
	 */
	public String getNameRootFolder() {
		return nameRootFolder;
	}

	/**
	 * This method returns the root folder
	 * @return root folder
	 */
	public String getRootFolder() {
		return this.rootFolder;
	}
	
	@Override
	public void impl(Handler handler) {
		// TODO Auto-generated method stub
		System.out.println("Connection from "+((HTTPHandlerConnection)handler).getSocket().getRemoteSocketAddress().toString());
        handler.startSession();
	}
}
