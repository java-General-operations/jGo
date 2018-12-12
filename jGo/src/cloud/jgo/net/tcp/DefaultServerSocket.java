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

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;

import cloud.jgo.net.Server.Closable;

public class DefaultServerSocket extends ServerSocket {

	private Closable closable = null;

	public void setClosable(Closable closable) {
		this.closable = closable;
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		super.close();

		if (closable != null) {
			closable.closeServer();
		}
	}

	public DefaultServerSocket(int lport) throws IOException {
		super(lport);
	}

	public DefaultServerSocket(int arg0, int arg1) throws IOException {
		super(arg0, arg1);
		/*
		 * 
		 * JGO Auto-generated constructor stub Author : £ wasp91 £ Date 05 feb 2018
		 * 
		 */
	}

	public DefaultServerSocket() throws IOException {
		super();
		/*
		 * 
		 * JGO Auto-generated constructor stub Author : £ wasp91 £ Date 05 feb 2018
		 * 
		 */
	}

	@Override
	public DefaultSocket accept() throws IOException {
		if (isClosed()) {
			new SocketException("The server is closed #");
			return null;
		}
		if (!isBound()) {
			new SocketException("The server is not bound yet");
			return null;
		} else {
			DefaultSocket socket = new DefaultSocket();

			implAccept(socket);

			// inserisco la socket nella lista

			return socket;
		}

	}

}
