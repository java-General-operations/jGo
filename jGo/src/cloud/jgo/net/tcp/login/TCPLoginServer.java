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

import cloud.jgo.£;
import cloud.jgo.net.ServerType;
import cloud.jgo.net.config.Configuration;
import cloud.jgo.net.factorys.ServersFactory;
import cloud.jgo.net.handlers.Handler;
import cloud.jgo.net.tcp.NotSupportedModelException;
import cloud.jgo.net.tcp.TCPServer;
import cloud.jgo.net.tcp.TCPServerConfiguration;
import cloud.jgo.net.tcp.TCPServerTypes;
import cloud.jgo.utils.Encrypts;

/**
 * 
 * @author Martire91<br>
 *         This class is the login tcp server
 */
public final class TCPLoginServer extends TCPServer implements Login {
	// queste var vengono prima criptate eppoi serializzate
	private String password = null;
	private String username = null;
	public final static int DEFAULT_ATTEMPTS = 5;
	private int attempts = DEFAULT_ATTEMPTS;
	private int copiedValueAttempts = attempts;
	private cloud.jgo.net.tcp.login.TCPLoginServerConfiguration configuration2 = new cloud.jgo.net.tcp.login.TCPLoginServerConfiguration();

	// diamo per scontato che la chiave sia stata impostata
	/**
	 * This method sets the server password
	 * 
	 * @param password
	 *            server password
	 */
	public void setPassword(String password) {
		this.password = password;
		if (getConfiguration().containsKey(cloud.jgo.net.tcp.login.TCPLoginServerConfiguration.AES_KEY.getKey())) {
			this.password = £.AES_e(this.password,
					new SecretKeySpec(((String)getConfiguration().getConfig(cloud.jgo.net.tcp.login.TCPLoginServerConfiguration.AES_KEY)).getBytes(),Encrypts.ALGORITHM));
			this.configuration2.put(cloud.jgo.net.tcp.login.TCPLoginServerConfiguration.PASSW, getPassword());
		} else {
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
			if (this.configuration2.getConfig(TCPLoginServerConfiguration.USER) != null
					&& this.configuration2.getConfig(TCPLoginServerConfiguration.PASSW) != null
					&& this.configuration2.getConfig(TCPLoginServerConfiguration.AES_KEY) != null) {
				configurated = true;
			} else {
				configurated = false;
			}
		}
		return configurated;
	}

	public int getCopiedValueAttempts() {
		return this.copiedValueAttempts;
	}

	@Override
	public cloud.jgo.net.tcp.login.TCPLoginServerConfiguration getConfiguration() {
		// TODO Auto-generated method stub
		return this.configuration2;
	}

	/**
	 * This method sets the server username
	 * 
	 * @param username
	 *            server username
	 */
	public void setUsername(String username) {
		this.username = username;
		if (getConfiguration().containsKey(cloud.jgo.net.tcp.login.TCPLoginServerConfiguration.AES_KEY.getKey())) {
			this.username = £.AES_e(this.username,
					new SecretKeySpec(((String)getConfiguration().getConfig(cloud.jgo.net.tcp.login.TCPLoginServerConfiguration.AES_KEY)).getBytes(),Encrypts.ALGORITHM));
			this.configuration2.put(TCPLoginServerConfiguration.USER, this.username);
		} else {
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
	 * 
	 * @param attempts
	 *            the login attempts
	 */
	public void setAttempts(int attempts) {
		this.attempts = attempts;
		this.copiedValueAttempts = this.attempts;
		this.configuration2.put(TCPLoginServerConfiguration.ATTEMPTS, this.attempts);
	}

	@Override
	public void configure(Configuration configuration) {
		// TODO Auto-generated method stub
		super.configure(configuration);
		this.configuration2 = (cloud.jgo.net.tcp.login.TCPLoginServerConfiguration) configuration;
		if (this.configuration2.containsKey(cloud.jgo.net.tcp.login.TCPLoginServerConfiguration.PASSW.getKey())) {
			setPassword(this.configuration2.getConfig(cloud.jgo.net.tcp.login.TCPLoginServerConfiguration.PASSW));
		}
		if (this.configuration2.containsKey(cloud.jgo.net.tcp.login.TCPLoginServerConfiguration.USER.getKey())) {
			setUsername(this.configuration2.getConfig(cloud.jgo.net.tcp.login.TCPLoginServerConfiguration.USER));
		}
		if (this.configuration2.containsKey(cloud.jgo.net.tcp.login.TCPLoginServerConfiguration.ATTEMPTS.getKey())) {
			setAttempts(this.configuration2.getConfig(cloud.jgo.net.tcp.login.TCPLoginServerConfiguration.ATTEMPTS));
		}
	}

	@Override
	public void reloadConfiguration() {
		// TODO Auto-generated method stub
		configure(this.configuration2);
	}

	/**
	 * This is factory method
	 * 
	 * @param configuration
	 *            the login server configuration
	 * @return the login server
	 * @throws SocketException
	 *             1 exception
	 */
	public static TCPLoginServer creates(cloud.jgo.net.tcp.login.TCPLoginServerConfiguration configuration)
			throws SocketException {

		return (TCPLoginServer) ServersFactory.getInstance().createServer(configuration);
	}

	@Override
	public void setModel(Handler handler) {
		if (handler != null) {
			if (handler instanceof TCPLoginHandlerConnection) {
				this.model = handler;
				getConfiguration().put(cloud.jgo.net.tcp.login.TCPLoginServerConfiguration.HANDLER_MODEL, this.model);
			} else {
				this.model = null;
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
	 * 
	 * @return server username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * This method the server password
	 * 
	 * @return server password
	 */
	public String getPassword() {
		return this.password;
	}

	@Override
	public boolean login(String user, String passw) {
		boolean logged = false;
		String passMem = £.AES_d(this.password,
				new SecretKeySpec(((String)getConfiguration().getConfig(cloud.jgo.net.tcp.login.TCPLoginServerConfiguration.AES_KEY)).getBytes(), Encrypts.ALGORITHM));
		String userMem = £.AES_d(this.username,
				new SecretKeySpec(((String)getConfiguration().getConfig(cloud.jgo.net.tcp.login.TCPLoginServerConfiguration.AES_KEY)).getBytes(), Encrypts.ALGORITHM));
		if (user.equals(userMem) && passw.equals(passMem)) {
			logged = true;
		}
		return logged;
	}

	@Override
	public int getAttempts() {
		// TODO Auto-generated method stub
		return this.attempts;
	}

	@Override
	public ServerType getType() {
		// TODO Auto-generated method stub
		return TCPServerTypes.TYPE_LOGIN;
	}

}
