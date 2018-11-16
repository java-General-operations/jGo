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

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

import cloud.jgo.Encrypts;
import cloud.jgo.net.tcp.TCPServerConfiguration;
import cloud.jgo.net.tcp.TCPServerTypes;
/**
 * 
 * @author Martire91<br>
 * Login tcp server configuration
 */
public class TCPLoginServerConfiguration extends TCPServerConfiguration{
	private String password,username = null ;
	private int attempts = TCPLoginServer.DEFAULT_ATTEMPTS ;
	public final static String KEY_PASSW = "jgo.net.server.passw";
	public final static String KEY_USER = "jgo.net.server.user";
	public final static String KEY_ATTEMPTS = "jgo.net.server.attempts";
	public final static String KEY_AES_KEY = "jgo.net.server.aes_key";
	private Key key=null;
	static{
		SERVER_TYPE = TCPServerTypes.TYPE_LOGIN ;
	}
	/**
	 * This method sets the server password
	 * @param password server password
	 */
	public void setPassword(String password) {
		this.password = password ;
		if(!this.getProps().containsKey(KEY_PASSW)){
			this.getProps().put(KEY_PASSW,this.password);
			counterSettings++ ;
		}
		else{
			this.getProps().replace(KEY_PASSW,this.password);
		}
	}
	/**
	 * This method sets the server username
	 * @param username server username
	 */
	public void setUsername(String username){
		this.username = username ;
		if(!this.getProps().containsKey(KEY_USER)){
			this.getProps().put(KEY_USER,this.username);
			counterSettings++ ;
		}
		else{
			this.getProps().replace(KEY_USER,this.username);
		}
	}
	
	/**
	 * This method sets the AES key
	 * @param keyText the key text
	 */
	public void AES_key(String keyText){
		this.key = new SecretKeySpec(keyText.getBytes(),Encrypts.ALGORITHM);
		if (!this.getProps().contains(KEY_AES_KEY)) {
			this.getProps().put(KEY_AES_KEY,keyText);
			counterSettings++ ;
		}
		else{
			this.getProps().replace(KEY_AES_KEY,keyText);
		}
	}
	
	/**
	 * This method returns the AES key
	 * @return AES key
	 */
	public Key AES_key(){
		return this.key ;
	}
	

	/**
	 * This method returns the server password
	 * @return the server password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * This method returns the server username
	 * @return server username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * This method returns the login attempts
	 * @return the login attempts
	 */
	public int getAttempts() {
		return attempts;
	}


	/**
	 * This method sets the login attempts
	 * @param attempts the login attempts
	 */
	public void setAttempts(int attempts) {
		this.attempts = attempts;
		if(!this.getProps().containsKey(KEY_ATTEMPTS)){
			this.getProps().put(KEY_ATTEMPTS,String.valueOf(attempts));
			counterSettings++ ;
		}
		else{
			this.getProps().replace(KEY_ATTEMPTS,String.valueOf(attempts));
		}
	}
	
}
