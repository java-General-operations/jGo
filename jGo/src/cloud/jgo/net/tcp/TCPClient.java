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

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.JTextComponent;

import cloud.jgo.net.Client;
import cloud.jgo.net.Connection;
import cloud.jgo.net.Server;
import cloud.jgo.utils.command.RemoteCommand;

/**
 * 
 * @author Martire91<br>
 *         This class is a tcp client, and is a subclass of {@link Client}
 *
 */
public abstract class TCPClient extends Client {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			try {
				communicates(this.connection);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private List<RemoteCommand> clientCommands = new ArrayList<>();
	private List<RemoteCommand> serverCommands = new ArrayList<>();
	private Proxy proxy = null;

	// Facciamo giusto un costruttore che prende come parametro un proxy

	public Proxy getProxy() {
		return proxy;
	}

	public void setServerCommands(List<RemoteCommand> serverCommands) {
		this.serverCommands = serverCommands;
	}

	/**
	 * This method adds commands in client
	 * 
	 * @param commands
	 *            the client commands
	 */
	public void addClientCommand(RemoteCommand... commands) {
		RemoteCommand[] array = commands;
		for (int i = 0; i < array.length; i++) {
			this.getClientCommands().add(array[i]);
		}
	}

	@Override
	public void startClient() {
		/*
		 * 
		 * JGO Auto-generated method stub Author : £ wasp91 £ Date 22 nov 2017
		 * 
		 */
		this.start();
	}

	/*
	 * spiegazione comandi :
	 * 
	 * * clientCommands : sono i comandi impostati dal client per controllare il
	 * server * serverCommands : sono i comandi impostati dal server per controllare
	 * il client
	 * 
	 */

	/**
	 * Questo metodo controlla i comandi che riceve il client dal server ,come
	 * infatti si nota nel codice che si va a cercare nell'array di comandi
	 * impostati dal server. Ora se trova il comando lo restituisce ma se il comando
	 * ha dei params a quel punto,il metodo cerca di restituire kmq il comando padre
	 * dei comandi con params. Per esempio se io ho un comando registrato tipo cmd
	 * -s che serve ad aprire il cmd ,se io passo questo comando al metodo ,lui non
	 * mi ritorna direttamente il comando cmd -t,ma mi restituisce il comando padre
	 * di quest'ultimio quindi il comando cmd. Poi c'è un altra cosa da sapere su
	 * questo metodo,diciamo che questo metodo è un metodo predisponente a un altro
	 * metodo che è il metodo statico della classe RemoteCommand
	 * :ExecuteInputRemoteCommandWithReturn oppure ExecuteInputRemoteCommand. Ultima
	 * informaizone non meno importante : Se per esempio nel client abbiamo
	 * registrato dei soli comandi senza params allora nel lato server side si può
	 * benissimo utilizzare solo il metodo checkReceivedCommand che retutn il
	 * comando e a quel punto basta eseguire il comando.Incese se nel client abbiamo
	 * registrato una gerarchia di comandi ,quindi ci sono comandi con params a quel
	 * punto nel lato server side ,basta invocare prima il metodo
	 * checkReceivedCommand e dopo il metodo statico :
	 * ExecuteInputRemoteCommandWithReturn oppure ExecuteInputRemoteCommand.Tutto
	 * questo è quello che c'è da sapere.
	 * 
	 * @param command
	 * @return
	 */

	/**
	 * This method controls the command from input and it gives us a reference to it
	 * 
	 * @param command
	 *            the input command
	 * @param commands
	 *            list commands
	 * @return the command
	 */
	public RemoteCommand checkCommand(String command, List<RemoteCommand> commands) {
		return (RemoteCommand) RemoteCommand.getCommand(command, commands);
	}

	/**
	 * This method returns the server commands
	 * 
	 * @return the server commands
	 */
	public List<RemoteCommand> getServerCommands() {
		return serverCommands;
	}

	/**
	 * This method returns the client commands
	 * 
	 * @return the client commands
	 */
	public List<RemoteCommand> getClientCommands() {
		return this.clientCommands;
	}

	/**
	 * This method returns the connection
	 * 
	 * @return the connection
	 */
	public TCPConnection getConnection() {
		return connection;
	}

	public TCPClient(java.net.Proxy proxy) {
		/*
		 * 
		 * JGO Auto-generated constructor stub Author : £ wasp91 £ Date 22 nov 2017
		 * 
		 */
		this.proxy = proxy;
		this.socket = new DefaultSocket(this.proxy);
	}

	public TCPClient() {
		/*
		 * 
		 * JGO Auto-generated constructor stub Author : £ wasp91 £ Date 22 nov 2017
		 * 
		 */
		this.socket = null;
	}

	protected java.net.Socket getSocket() {
		return socket;
	}

	protected DefaultSocket socket = null;
	private java.io.ObjectOutputStream out = null;
	private java.io.ObjectInputStream in = null;
	protected TCPConnection connection = null;
	private Object writeFrom = null;
	private Object readFrom = null;

	/**
	 * This method sets the reading source
	 * 
	 * @param source
	 *            the reading source
	 */
	public void setReadFrom(Object source) {

		this.readFrom = source;

	}

	/**
	 * This method reads the text on the reading source
	 * 
	 * @param text
	 *            the text
	 * @throws IOException
	 *             1 exception
	 * @throws NoReadingSourceException
	 *             2 exception
	 */
	public void read(String text) throws IOException, NoReadingSourceException {
		if (readFrom instanceof JTextComponent) {
			((JTextComponent) readFrom).setText(text);
		} else if (readFrom instanceof PrintStream) {
			((PrintStream) readFrom).println(text);
		}

		else if (readFrom instanceof OutputStream) {
			((OutputStream) readFrom).write(text.getBytes());
		} else if (readFrom instanceof Writer) {
			((BufferedWriter) readFrom).write(text);
			((BufferedWriter) readFrom).newLine();
		} else if (readFrom instanceof JTextPane) {
			((JTextPane) readFrom).setText(((JTextPane) readFrom).getText() + "\n" + text);
			((JTextPane) readFrom).setCaretPosition(((JTextPane) readFrom).getDocument().getLength() - 1);
			((JTextPane) readFrom).revalidate();
			((JTextPane) readFrom).repaint();
		} else if (readFrom instanceof JTextArea) {
			((JTextArea) readFrom).setText(((JTextArea) readFrom).getText() + "\n" + text);
			((JTextArea) readFrom).setCaretPosition(((JTextArea) readFrom).getDocument().getLength() - 1);
			((JTextArea) readFrom).revalidate();
			((JTextArea) readFrom).repaint();
		} else {

			// qui scatta l'eccezzione

			throw new NoReadingSourceException();

		}

	}

	/**
	 * This method sets the writing source.
	 * 
	 * @param source
	 *            the writing source
	 */
	public void setWriteFrom(Object source) {

		this.writeFrom = source;

	}

	/**
	 * This method writes the text on the writing source
	 * 
	 * @return the text
	 * @throws IOException
	 *             1 exception
	 */
	public String write() throws IOException {
		String text = null;
		if (writeFrom instanceof Scanner) {
			text = ((Scanner) writeFrom).nextLine();
		} else if (writeFrom instanceof JTextComponent) {
			text = ((JTextComponent) writeFrom).getText();
		} else if (writeFrom instanceof InputStream) {
			text = ((Scanner) writeFrom).nextLine();
		}
		return text;
	}

	/**
	 * This method manages the connection (client/side)
	 * 
	 * @param connection
	 *            the connection
	 * @throws IOException
	 *             1 exception
	 * @throws ClassNotFoundException
	 *             2 exception
	 */
	public abstract void communicates(Connection connection) throws IOException, ClassNotFoundException;

	@Override
	public boolean connect() throws IOException {
		try {
			this.socket = new DefaultSocket(InetAddress.getLocalHost().getHostAddress(), TCPServer.DEFAULT_PORT);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (this.socket.isConnected()) {
			try {
				this.connection = new TCPConnection(this.socket, this.socket.isConnected(), this);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// qui ricevo la lista dei comandi creati del server
		}
		return this.socket.isConnected();
	}

	@Override
	public boolean connect(String host, int port) throws IOException {
		try {
			this.socket = new DefaultSocket(host, port);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (this.socket.isConnected()) {
			try {
				this.connection = new TCPConnection(this.socket, this.socket.isConnected(), this);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// qui ricevo la lista dei comandi creati del server
		}
		return this.socket.isConnected();
	}

	/**
	 * This method opens a connection with the specified host. This method uses a
	 * Proxy.
	 * 
	 * @param host
	 *            the remote host
	 * @param port
	 *            the remote port
	 * @param proxy
	 *            the proxy (SOCKS)
	 * @return true is connected
	 * @throws IOException
	 *             1 exception
	 */
	public boolean connectWithProxy(String host, int port, Proxy proxy) throws IOException { // qui bisogna che sia
																								// stato impostato il
																								// proxy
		if (proxy != null) {
			try {
				this.socket.connect(new InetSocketAddress(host, port));
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (this.socket.isConnected()) {
				try {
					this.connection = new TCPConnection(this.socket, this.socket.isConnected(), this);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// qui ricevo la lista dei comandi creati del server
			}
			return this.socket.isConnected();
		} else {
			return false;
		}
	}

	@Override
	public boolean isConnected() {
		return this.socket.isConnected();
	}

}
