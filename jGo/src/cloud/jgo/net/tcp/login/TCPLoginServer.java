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
package cloud.jgo.net.tcp.login;

import java.net.SocketException;
import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

import cloud.jgo.Encrypts;
import cloud.jgo.£;
import cloud.jgo.net.Configuration;
import cloud.jgo.net.ServerType;
import cloud.jgo.net.factorys.ServersFactory;
import cloud.jgo.net.handlers.Handler;
import cloud.jgo.net.tcp.NotSupportedModelException;
import cloud.jgo.net.tcp.TCPServer;
import cloud.jgo.net.tcp.TCPServerTypes;
/**
 * 
 * @author Martire91<br>
 * This class is the login tcp server
 */
public final class TCPLoginServer extends TCPServer implements Login{
	static {
		TYPE_SERVER = TCPServerTypes.TYPE_LOGIN ;
	}
	// queste var vengono prima criptate eppoi serializzate
	private String password = null ;
	private String username = null ;
	public final static int DEFAULT_ATTEMPTS = 5 ;
	private int attempts = DEFAULT_ATTEMPTS ;
	private int copiedValueAttempts = attempts ;
	private TCPLoginServerConfiguration configuration = new TCPLoginServerConfiguration();
	
	// diamo per scontato che la chiave sia stata impostata
	/**
	 * This method sets the server password
	 * @param password server password
	 */
	public void setPassword(String password) {
		this.password = password;
		if(getConfiguration().AES_key()!=null){
			this.password = £.AES_e(this.password,getConfiguration().AES_key());
			this.configuration.setPassword(this.password);
		}
		else{
			// do l'eccezzione dedicata
			try {
				throw new KeyNoSetException();
			} catch (KeyNoSetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public boolean isConfigurated() {
		// TODO Auto-generated method stub
		boolean configurated = super.isConfigurated();
		if (configurated) {
			
			if (this.configuration.getUsername()!=null && this.configuration.getPassword()!=null) {
			    configurated = true ;
			}
			else{
				configurated = false ;
			}
		}
		return configurated ;
	}

	public int getCopiedValueAttempts() {
		return this.copiedValueAttempts;
	}
	/**
	 * This method sets the server username
	 * @param username server username
	 */
	public void setUsername(String username) {
		this.username = username;
		if (getConfiguration().AES_key()!=null) {
			this.username = £.AES_e(this.username,getConfiguration().AES_key());
			this.configuration.setUsername(this.username);
		}
		else{
			// do l'eccezzione
			try {
				throw new KeyNoSetException();
			} catch (KeyNoSetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * This method sets the login attempts
	 * @param attempts the login attempts
	 */
	public void setAttempts(int attempts) {
		this.attempts = attempts;
		this.copiedValueAttempts = this.attempts;
		this.configuration.setAttempts(this.attempts);
	}
	
	@Override
	public TCPLoginServerConfiguration getConfiguration() {
		// TODO Auto-generated method stub
		return this.configuration ;
	}
	
	@Override
	public void configure(Configuration configuration){
		// TODO Auto-generated method stub
		super.configure(configuration);
		this.configuration = (TCPLoginServerConfiguration) configuration;
		if(this.configuration.getPassword()!=null){
			this.setPassword(this.configuration.getPassword()); // e quindi criptiamo la password
		}
		if(this.configuration.getUsername()!=null){
			this.setUsername(this.configuration.getUsername()); // e qui criptiamo la username
		}
		if (this.configuration.getAttempts()>0) {
			this.setAttempts(this.configuration.getAttempts());
		}
	}
	
	@Override
	public void reloadConfiguration() {
		// TODO Auto-generated method stub
		configure(this.configuration);
	}
	
	/**
	 * This is factory method
	 * @param configuration the login server configuration
	 * @return the login server
	 * @throws SocketException 1 exception
	 */
	public static TCPLoginServer creates(TCPLoginServerConfiguration configuration) throws SocketException{
		
		return (TCPLoginServer) ServersFactory.getInstance().createServer(configuration);
	}

	
	@Override
	public void setModel(Handler handler) {
		if (handler!=null) {
			if(handler instanceof TCPLoginHandlerConnection){
				this.model = handler ;
				getConfiguration().setModel(this.model);
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
	}
	
	// questo metodo serve a ben poco in questa classe
	// in quanto la classe non è estendibile
	@Override
	final protected void impl(Handler handler) {
		
		// qui attivo l'handler 
		
		handler.startSession();
		
	}
	
	/**
	 * This method returns the server username
	 * @return server username
	 */
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * This method the server password
	 * @return server password
	 */
	public String getPassword() {
		return this.password;
	}
	@Override
	public boolean login(String user,String passw){
		boolean logged = false ;
	    String passMem = £.AES_d(this.password,getConfiguration().AES_key());
		String userMem = £.AES_d(this.username,getConfiguration().AES_key());
		if(user.equals(userMem)&&passw.equals(passMem)){
			logged = true ;
		}
	    return logged ;	
	}

	@Override
	public int getAttempts() {
		// TODO Auto-generated method stub
		return this.attempts ;
	}

	@Override
	protected ServerType getType() {
		// TODO Auto-generated method stub
		return TCPServerTypes.TYPE_LOGIN ;
	}
	
	
}
