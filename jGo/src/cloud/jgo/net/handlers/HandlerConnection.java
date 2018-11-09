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
package cloud.jgo.net.handlers;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;

import cloud.jgo.net.Connection;
import cloud.jgo.net.Server;
/**
 * 
 * @author Martire91<br>
 * This class represents the connection handler,
 * and is an implementation of {@link Connection} and of {@link Handler}
 * (Product)
 */
public abstract class HandlerConnection implements Connection,Handler{

	/**
	 * This method manages the connection. (Server/side)
	 * @throws ClassNotFoundException 1 exception
	 * @throws IOException 2 exception
	 * @throws InterruptedException 3 exception
	 * @throws HeadlessException 4 exception
	 * @throws AWTException 5 exception
	 */
	public abstract void manage() throws ClassNotFoundException, IOException, InterruptedException, HeadlessException, AWTException;
	private String idSession = null ;
	
	@Override
	public String idSession() {
		// TODO Auto-generated method stub
		return this.idSession ;
	}
	
	@Override
	public void setIdSession(String id_session) {
		// TODO Auto-generated method stub
		this.idSession = id_session ;
	}

	@Override
	public Thread startSession() {
		Thread th = null ;
		th = new Thread(this);
		th.start();
		return th ;
	}
	


	@Override
	public void run() {
		// TODO Auto-generated method stub
	try {
		try {
			try {
				manage();
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	}
}
