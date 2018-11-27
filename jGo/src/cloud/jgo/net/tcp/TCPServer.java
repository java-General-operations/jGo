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
import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.JTextComponent;

import cloud.jgo.£;
import cloud.jgo.net.Manageable;
import cloud.jgo.net.Server;
import cloud.jgo.net.ServerTimer;
import cloud.jgo.net.ServerType;
import cloud.jgo.net.ServerTypes;
import cloud.jgo.net.config.Configuration;
import cloud.jgo.net.config.ServerConfiguration;
import cloud.jgo.net.factorys.ServersFactory;
import cloud.jgo.net.handlers.Handler;
import cloud.jgo.utils.command.RemoteCommand;
/**
 * 
 * @author Martire91<br>
 * This class represents a TCP server,
 * and is an implementation of {@link Server}
 */
public abstract class TCPServer implements Server,Manageable,Iterable<Handler>{
	private List<RemoteCommand> serverCommands = new ArrayList<>(); // i comandi impostati dal server per controllare il client
	protected List<Handler> handlers = new ArrayList<>();
	private String nameServer = null;
	public static int DEFAULT_PORT = 3333 ;
	private boolean inListen = false ;
	private Closable closable = null ;
	private int countSockets = 0 ;
	private ServerTimer serverTimer = null ;
	private TCPServerConfiguration configuration2 = new TCPServerConfiguration();
	private Object readFrom = null;
	private Object writeFrom = null ;
	protected DefaultSocket acceptedConnection = null ;
	private boolean closed = false ;
	private String textOfAcceptedSocket = null ; 
	private int maximumSockets = 0 ;
	private Object object = null;
	// metodo che imposta il server come auto run 
	private boolean inTheAcceptancePhase = false ;
	private boolean oneConnectionAtATime = false;
	/**
	 * This method returns the handler through the id
	 * @param id_session session id
	 * @return the handler
	 */
	public Handler getHandlerByID(String id_session){
		Iterator<Handler>iterator = iterator();
		Handler h = null ;
		while (iterator.hasNext()) {
			Handler handler = (Handler) iterator.next();
			if(handler.idSession().equals(id_session)){
				h = handler ;
				break ;
			}
		}
		return h ;
	}
	
	/**
	 * This method sets an object
	 * @param object the object
	 */
	public void setObject(Object object){
	this.object = object ;	
	}
	
	/**
	 * This method sets a server as autorun
	 * @param serverExecutable the executable
	 * @param valueNameRegistry the register value that you want to associate
	 * @return true if all right
	 */
	public static boolean setAutoRunServer(cloud.jgo.io.File serverExecutable,String valueNameRegistry){
		return £.setAutoRunFile(serverExecutable, valueNameRegistry);	
	}
	
	@Override
	public Object getObject() {
		// TODO Auto-generated method stub
		return this.object  ;
	}
	
	
	public boolean isInTheAcceptancePhase() {
		return inTheAcceptancePhase;
	}
	
	/**
	 * This method is useful when extending this class.
	 * Set the model type you will be working on at a later time.
	 * @param handler the model type you want to use
	 */
	protected void setFutureModel(Handler handler){
		if (handler instanceof TCPHandlerConnection) {
			this.futureModel = handler ;
		}
		else{
			try {
				throw new NotSupportedModelException();
			} catch (NotSupportedModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	// metodo che si occupa di aggiornare la configurazione 
	
	/**
	 * Recharge the configuration
	 */
	public void reloadConfiguration(){
		this.configure(getConfiguration());
	}
	
	@Override
	public boolean isClosed() {
		// TODO Auto-generated method stub
		return server.isClosed();
	}
	
	
	/**
	 * set up one connection at a time
	 * @param flag the flag
	 */
	public void setOneConnectionAtATime(boolean flag){
		this.oneConnectionAtATime  = flag ;
	}
	
	/**
	 * Returns true if the server is set to one connection at a time
	 * @return the flag
	 */
	public boolean isOneConnectionAtATime() {
		return this.oneConnectionAtATime;
	}
	@Override
	public void configure(Configuration configuration) {
		// TODO Auto-generated method stub
		this.configuration2 = (TCPServerConfiguration) configuration ;
		if(this.configuration2.containsKey(TCPServerConfiguration.MULTI_CONNECTIONS.getKey())){
			setMultiConnections(this.configuration2.getConfig(TCPServerConfiguration.MULTI_CONNECTIONS));
		}
		if (this.configuration2.containsKey(TCPServerConfiguration.LPORT.getKey())) {
			setLocalPort(this.configuration2.getConfig(ServerConfiguration.LPORT));
		}
		if (this.configuration2.containsKey(TCPServerConfiguration.SERVER_NAME.getKey())) {
			setServerName(this.configuration2.getConfig(TCPServerConfiguration.SERVER_NAME));
		}
		if (this.configuration2.containsKey(TCPServerConfiguration.ACCEPTED_SOCKET.getKey())) {
			setLocalPort(this.configuration2.getConfig(TCPServerConfiguration.ACCEPTED_SOCKET));
		}
		if (this.configuration2.containsKey(TCPServerConfiguration.MAXIMUM_SOCKETS.getKey())) {
			this.maximumSockets = (this.configuration2.getConfig(TCPServerConfiguration.MAXIMUM_SOCKETS));
		}
		if (this.configuration2.containsKey(TCPServerConfiguration.HANDLER_MODEL.getKey())) {
			setModel(this.configuration2.getConfig(TCPServerConfiguration.HANDLER_MODEL));
		}
	}
	
	@Override
	public Handler getModel() {
		// TODO Auto-generated method stub
		return this.model ;
	}
	
	
	@Override
	public void setModel(Handler handler) {
		if(handler instanceof TCPHandlerConnection){
			// TODO Auto-generated method stub
			this.model = handler ;
			this.configuration2.put(TCPServerConfiguration.HANDLER_MODEL,this.model);
		}
		else{
			try {
				throw new NotSupportedModelException();
			} catch (NotSupportedModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method adds the commands in server
	 * @param commands The commands you want to add
	 */
	public void addServerCommand(RemoteCommand...commands){
		RemoteCommand[]commandsArray = commands;
		for (int i = 0; i < commandsArray.length; i++) {
			serverCommands.add(commandsArray[i]);
		}
	}
	
	/**
	 * This command controls the command from input and returns a remote command
	 * @param command input command
	 * @param commands list commands
	 * @return the remote command
	 */
	public RemoteCommand checkCommand(String command, List<RemoteCommand> commands) {
		return (RemoteCommand) RemoteCommand.getCommand(command, commands)	;
	}
	
	@Override
	public boolean isConfigurated() {
		
		if(this.getConfiguration()!=null){
			
			// ora qui devo controllare se il numero delle impostazioni settate è maggiore di 0
			
			if(this.getConfiguration().size()>0){
				
				// ultimo controllo
				// controllo se la porta è stata settata
				
				if(this.getConfiguration().getConfig(ServerConfiguration.LPORT)!=null){
					// qui potrei essere ancora più pignolo
					// perchè dovrei verificare se la porta rientra
					// nel range di porte accessibili ,ma no problem 
					// quello lo faremo nella prossima versione
					return true ;
				}
				else{
					return false ;
				}
				
			}
			else{
				return false ;
			}
		}
		else{
			return false ;
		}
	}
	
	/**
	 * Factory method
	 * @param configuration the configuration with which the server is started
	 * @return the tcp server
	 * @throws SocketException 1 exception
	 */
	public static TCPServer creates(TCPServerConfiguration configuration) throws SocketException{
		ServersFactory factory = ServersFactory.getInstance();
		return (TCPServer) factory.createServer(configuration);
	}
	
	
	
	@Override
	public String toString() {
		/*
		 
		JGO Auto-generated method stub
		Author : £ wasp91 £
		Date 23 nov 2017
		
		*/
	
		if(this.nameServer!=null){
			
			try {
				return "* Server Name :"+this.nameServer+"\n"+
					   "* Server Type :"+TYPE_SERVER.TYPE+"\n"+
					   "* LHOST       :"+InetAddress.getLocalHost().toString()+"\n"+
					   "* LPORT		  :"+getServer().getLocalPort();
			} catch (UnknownHostException e) {
				/*
				 
				JGO Auto-generated catch block
				Author : £ wasp91 £
				Date 23 nov 2017
				
				*/
				return null ;
			}
			
			
		}
		else{
			try {
				return 
					   "* Server Type :"+TYPE_SERVER.TYPE+"\n"+
					   "* LHOST       :"+InetAddress.getLocalHost().toString()+"\n"+
					   "* LPORT		  :"+getServer().getLocalPort();
			} catch (UnknownHostException e) {
				return null ;
			}
		}
	}
	
	/**
	 * returns the sockets number
	 * @return the sockets number
	 */
	public int getCountSockets() {
		return countSockets;
	}
	
	/**
	 * Returns the text that is shown when accepting a connection
	 * @return the text
	 */
	public String getTextOfAcceptedSocket(){
		return this.textOfAcceptedSocket ;
	}

	/**
	 * This method sets the text that is shown when accepting a connection
	 * @param output the text
	 */
	public void setTextOfAcceptedSocket(String output) {
		this.textOfAcceptedSocket = output ;
		this.configuration2.put(TCPServerConfiguration.ACCEPTED_SOCKET,textOfAcceptedSocket);
	}
	@Override
	public TCPServerConfiguration getConfiguration() {
		// TODO Auto-generated method stub
		return this.configuration2;
	}
	/**
	 * This method sets reading source
	 * @param source reading source
	 */
	public void setReadFrom(Object source){
	   this.readFrom = source ;
	   
	}
	
	/**
	 * This method gets the reading source
	 * @return reading source
	 */
	public Object getReadFrom() {
		return this.readFrom;
	}
	
	
	/**
	 * Read a text from the reading source
	 * @param text the text
	 * @throws IOException 1 exception
	 * @throws NoReadingSourceException 2 exception
	 */
	public void read(String text) throws IOException, NoReadingSourceException{
		if(readFrom instanceof JTextComponent){
			   ((JTextComponent)readFrom).setText(text);
		   }
		   else if(readFrom instanceof PrintStream){
			   ((PrintStream)readFrom).println(text);
		   }
			 
		   else if(readFrom instanceof OutputStream){
			   ((OutputStream)readFrom).write(text.getBytes()); 
		   }
		   else if(readFrom instanceof Writer){
			   ((BufferedWriter)readFrom).write(text);
			   ((BufferedWriter)readFrom).newLine();
		   }
		   else if(readFrom instanceof JTextPane){
			   ((JTextPane)readFrom).setText(((JTextPane)readFrom).getText()+"\n"+text);
			   ((JTextPane)readFrom).setCaretPosition(((JTextPane)readFrom).getDocument().getLength()-1);
			   ((JTextPane)readFrom).revalidate();
			   ((JTextPane)readFrom).repaint();
		   }
		   else if(readFrom instanceof JTextArea){
			   ((JTextArea)readFrom).setText(((JTextArea)readFrom).getText()+"\n"+text);
			   ((JTextArea)readFrom).setCaretPosition(((JTextArea)readFrom).getDocument().getLength()-1);
			   ((JTextArea)readFrom).revalidate();
			   ((JTextArea)readFrom).repaint();
		   }
		   else{
			   
			   // qui scatta l'eccezzione
			   
				throw new NoReadingSourceException();
			
		   }
		
		
	}
	
	
	@Override
	public void startServer() {
		/*
		 
		JGO Auto-generated method stub
		Author : £ wasp91 £
		Date 22 nov 2017
		
		*/
		new Thread(this).start();
	}
	
	/**This method sets writing source
	 * @param source source
	 */
	public void setWriteFrom(Object source){
		
		 this.writeFrom = source ;  
	}
	
	/**
	 * This method gets the writing source
	 * @return writing source
	 */
	public Object getWriteFrom() {
		return writeFrom;
	}
	
	/**
	 * This method writes the text on the writing source
	 * @return the text
	 * @throws IOException 1 exception
	 */
	public String write() throws IOException{
		String text = null ;
		if(writeFrom instanceof Scanner){
			text =((Scanner)writeFrom).nextLine();
		}
		else if(writeFrom instanceof JTextComponent){
			text = ((JTextComponent)writeFrom).getText();
		}
		else if(writeFrom instanceof InputStream){
			text =((Scanner)writeFrom).nextLine();
		}
		return text ;
	}
	protected Handler model = null;
	protected Handler futureModel=null;
	
	/**
	 * If  true it means that the server allows multi-connection
	 * @return the flag
	 */
	public boolean isMultiConnections() {
		return multiConnections;
	}
	/**
	 * Sets the closing operation
	 * @param closable the closing object
	 */
	public void setClosable(Closable closable) {
		this.closable = closable;
	}
	@Override
	public String[] getInfoServerSystem() throws UnknownHostException {
		List<String>list = new ArrayList<>();
		if(getServerName()!=null){
			list.add("Server Name :"+getServerName());
		}
		list.add("Server System :"+System.getProperty("os.name"));
		list.add("Server System Version :"+System.getProperty("os.version"));
		list.add("Server System arch :"+System.getProperty("os.arch"));
		list.add("Server host :"+InetAddress.getLocalHost().toString());
		list.add("Server Username :"+System.getProperty("user.name"));
		list.add("Server Path :"+System.getProperty("user.dir"));
		list.add("Server java version :"+System.getProperty("java.version"));
		Iterator<String>itera = list.iterator();
		int listSize = list.size();
		String[]info = new String[listSize];
		int count = 0 ;
		while(itera.hasNext()){
			String line = itera.next();
			info[count]=line;
			count++ ;
		}
		return info ;
	}
	
	@Override
	public String getServerName() {
		// TODO Auto-generated method stub
		return this.nameServer ;
	}

    /**
     * This method sets the server name
     * @param nameServer server name
     */
	public void setServerName(String nameServer) {
		this.nameServer = nameServer;
		this.configuration2.put(ServerConfiguration.SERVER_NAME,this.nameServer);
	}

	// qui dochiaro il tipo del server
	public static ServerType TYPE_SERVER = ServerTypes.TYPE_TCP ;
    /**
     * 
     * @return the server type
     */
	protected ServerType getType() {
		return TYPE_SERVER ;
	}
	
    /**
     * This method sets the server for the multi connections
     * @param multiConnections the flag
     */
	public void setMultiConnections(boolean multiConnections) {
		this.multiConnections = multiConnections;
		this.configuration2.put(TCPServerConfiguration.MULTI_CONNECTIONS, this.multiConnections);
	}
	private DefaultServerSocket server = null;
	private int localPort;
	// inoltre un tcpserver ha un variabile che decide se è multi connessione o meno
	protected DefaultServerSocket getServer() {
		return this.server;
	}
	@Override
	public void setLocalPort(int localPort) {
		this.localPort = localPort ;
	    this.configuration2.put(ServerConfiguration.LPORT,this.localPort);
	}
	/**
	 * forces the server program to close
	 */
	public void forceShutdownServerProgram(){
	   System.exit(0);
	}
	private boolean multiConnections = false ;
	
	public TCPServer() {
		this.server = null ;
	}
	@Override
	public int getLocalPort() {
		// TODO Auto-generated method stub
		return this.localPort ;
	}
	
	
	
	 /**
	  * This method accepts a connection
	  * @return the accepted socket 
	  * @throws IOException 1 exception
	  * @throws NoReadingSourceException 2 exception :the read source has not been set
	  */
	 public DefaultSocket acceptRequest() throws IOException, NoReadingSourceException{
		// per prima cosa controllo se non  accetta connessioni
		if(this.getConfiguration().containsKey(TCPServerConfiguration.MAXIMUM_SOCKETS)){
			// è stato impostato un numero massimo di sockets
			if(this.countSockets<((Integer)this.configuration2.getConfig(TCPServerConfiguration.MAXIMUM_SOCKETS))){
				
				inTheAcceptancePhase = true ;
				this.acceptedConnection = this.getServer().accept();
				inTheAcceptancePhase = false ;
				
				if(!isMultiConnections()){
					if(!getServer().isClosed()){
						
						
						getServer().close();
						
					}
				}
				this.countSockets++ ;
				if(this.textOfAcceptedSocket!=null){
					read(this.textOfAcceptedSocket);
				}
				if(((Boolean)getConfiguration().getConfig(TCPServerConfiguration.DEFAULT_PRINT_FOR_ACCEPTANCE_SOCKET))){
					read(this.countSockets+") New Connection from "+this.acceptedConnection.getInetAddress().toString());
				}
				return this.acceptedConnection ;	
			}
			else{
				/*
				 * 
				 * Spiega perchè ci può stare questa eccezzione qui
				 * 
				 */
				try {
					throw new MaximumSocketsException();
				} catch (MaximumSocketsException e) {
					/*
					 
					JGO Auto-generated catch block
					Author : £ wasp91 £
					Date 05 feb 2018
					
					*/
					e.printStackTrace();
				}
				return null ;
			}
		}
		else{
			// qui si procede ugualmente
			inTheAcceptancePhase = true ;
			this.acceptedConnection = this.getServer().accept();
			inTheAcceptancePhase = false ;
			if(!isMultiConnections()){
				if(!getServer().isClosed()){
				getServer().close();		
				}
			}
			this.countSockets++ ;
			if(this.textOfAcceptedSocket!=null){
				read(this.textOfAcceptedSocket);
			}
			if((Boolean)getConfiguration().getConfig(TCPServerConfiguration.DEFAULT_PRINT_FOR_ACCEPTANCE_SOCKET)){
				read(this.countSockets+") New Connection from "+this.acceptedConnection.getInetAddress().toString());	
			}
			return this.acceptedConnection ;	
		}
	}
	
	
	 protected void acceptRequestsFromClients(Handler handler){
		while(true){
			
			// accept the socket
			try {
				this.acceptedConnection = acceptRequest();
				try {
					((TCPHandlerConnection)handler).setSocket(this.acceptedConnection);
					((TCPHandlerConnection)handler).setServer(this);
					impl(handler);
					handlers.add(((TCPHandlerConnection)handler));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoReadingSourceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
		}
	}
	
	 protected void acceptRequestFromClient(Handler handler){
			try {
				this.acceptedConnection = acceptRequest();
				 handlers.add(handler);
				try {
					try {
						((TCPHandlerConnection)handler).setSocket(this.acceptedConnection);
						((TCPHandlerConnection)handler).setServer(this);
						impl(handler);
						handlers.add(handler);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoReadingSourceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	
	
	/**
	 * this method must be implemented by the user when extending this class
	 * @param handler current handler
	 */
	protected abstract void impl(Handler handler);

	protected void acceptRequestsFromClientsWithModel() throws IOException, CloneNotSupportedException, NoReadingSourceException, InstantiationException, IllegalAccessException{
		while(true){
			this.acceptedConnection = this.acceptRequest();
			if(this.acceptedConnection!=null){
							// Ora qui devo ricavare che tipo di handler è stato impostato
							
							Class classeModel = model.getClass();
							TCPHandlerConnection newInstance = (TCPHandlerConnection) classeModel.newInstance();
							// okok adesso uso questo handler per gestire la socket corrente
							newInstance.setSocket(this.acceptedConnection);
							// imposto il server
							try {
								newInstance.setServer(this);
								handlers.add(newInstance);
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							// attivo l'handler 
						  Thread th = newInstance.startSession();
							
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
	
	
	
	@Override
	public void run() {
		
		// allora controllo definitivo
		
		// si controlla se 
		// c'è il modello impostato o il modello futuro impostato
		// bene,possiamo prenderci il rischio di avviare il server,
		// in caso contrario,non possiamo prederci il rischio perchè
		// l'errore che ci sarà nella console non farà caèire all'utente cosa è successo
		// quindi preveniamo noi.
		
		
		if (this.model!=null || this.futureModel!=null) {
			
			
			// ora qui ulteriore controllo importante
			// verificare che sia impostato o il model o il future model
			if (this.model!=null) {
				
				if (isMultiConnections()) {
					// multi connessione con modello
					try {
						acceptRequestsFromClientsWithModel();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoReadingSourceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					// singola connessione con modello
					try {
						acceptRequestWithModel();
					} catch (HeadlessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (AWTException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoReadingSourceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			else if(this.futureModel!=null){
				if (isMultiConnections()) {
					// multi connessione estendendo la classe
					acceptRequestsFromClients(this.futureModel);
				}
				else{
					// singola connessione estendendo la classe
					acceptRequestFromClient(this.futureModel);
				}
			}
			else{
				//System.err.println("Sono stati impostati tutti i due i tipi di modelli #");
			}
			
		}
		else{
			
			// qui di sicuro dobbiamo lanciare l'eccezzione
			try {
				throw new ModelAbsenceException();
			} catch (ModelAbsenceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		    }
			}
			
	protected void acceptRequestWithModel() throws IOException, CloneNotSupportedException, HeadlessException, ClassNotFoundException, AWTException, InstantiationException, IllegalAccessException, NoReadingSourceException {
		this.acceptedConnection = acceptRequest();
					Class classeModel = model.getClass();
					TCPHandlerConnection newInstance = (TCPHandlerConnection) classeModel.newInstance();
					
					// okok adesso uso questo handler per gestire la socket corrente
					newInstance.setSocket(this.acceptedConnection);
					
					// imposto il server 
					
					newInstance.setServer(this);
					handlers.add(newInstance);
					// attivo l'handler 
					try {
						newInstance.manage();
					} catch (InterruptedException e) {
						/*
						 
						JGO Auto-generated catch block
						Author : £ wasp91 £
						Date 22 nov 2017
						
						*/
						e.printStackTrace();
					}	    
					}
	
	
	@Override
	public void closeServer() {
		// TODO Auto-generated method stub
		inListen = false ;
		try {
			getServer().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// da ridefinire
	}

	
	// quando mettiamo il server in ascolto viene settato il
	

	@Override
	public void listen(int localPort, String positiveListen, String negativeListen) throws NoReadingSourceException, NegativeListeningException {
        this.localPort = localPort ;
		boolean occupied = isOccupied("localhost",this.localPort);
		if(occupied){
			// scatta l'eccezzione
			throw new NegativeListeningException();
		}
		else{
			// mettiamo il nostro bel server in attesa 
			try {
				this.server = new DefaultServerSocket(this.localPort);
				this.server.setClosable(this.closable);
				read(positiveListen);
				this.inListen = true ;
				if((Integer)this.getConfiguration().getConfig(TCPServerConfiguration.TIMER)>0){
					// qui parte il timer
					serverTimer = new ServerTimer(this,getConfiguration().getConfig(TCPServerConfiguration.TIMER));
					serverTimer.startTimer();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				try {
					read(negativeListen);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void listen() throws NegativeListeningException {
		boolean occupied = false ;
		try {
			occupied = isOccupied(InetAddress.getLocalHost().getHostAddress(),this.localPort);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(occupied){
			throw new NegativeListeningException();
		}
		else{
			if (localPort==0) {
				localPort = DEFAULT_PORT ;
			}
			try {
				this.server = new DefaultServerSocket(localPort);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.server.setClosable(this.closable);
			this.inListen = true ;
			// qui si controlla se è stato settato il timer
			if((Integer)getConfiguration().getConfig(TCPServerConfiguration.TIMER)>0){
				// qui parte il timer
				serverTimer = new ServerTimer(this,getConfiguration().getConfig(TCPServerConfiguration.TIMER));
				serverTimer.startTimer();
			}
		}	
	}
	
	@Override
	public void listen(String LHOST, int LPORT) throws NegativeListeningException{
		this.localPort = LPORT;
		this.server = null ;
		boolean occupied = isOccupied(LHOST, LPORT);
		if(occupied){
			throw new NegativeListeningException();
		}
		else{
			try {
				this.server = new DefaultServerSocket();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.server.setClosable(this.closable);
			try {
				this.server.bind(new InetSocketAddress(LHOST, LPORT));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.inListen = true ;
			// qui si controlla se è stato settato il timer
			if((Integer)getConfiguration().getConfig(TCPServerConfiguration.TIMER)>0){
				// qui parte il timer
				serverTimer = new ServerTimer(this,getConfiguration().getConfig(TCPServerConfiguration.TIMER));
				serverTimer.startTimer();
			}
		}
	}
	
	
	
	
	
	@Override
	public boolean isOccupied(String address,int serverPort) {
		 try (Socket ignored = new Socket(address, serverPort)) {
		        return true ;
		    } catch (IOException ignored) {
		        return false ;
		    }
	}
	@Override
	public boolean isInListen() {
		// TODO Auto-generated method stub
		return this.inListen ;
	}

	/**
	 * Returns the server commands
	 * @return the server commands list
	 */
	public List<RemoteCommand> getServerCommands() {
		return this.serverCommands ;
	}

	@Override
	public Iterator<Handler> iterator() {
		// TODO Auto-generated method stub
		return this.handlers.iterator();
	}	
}
