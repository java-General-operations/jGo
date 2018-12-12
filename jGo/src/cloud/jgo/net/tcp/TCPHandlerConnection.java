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
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.net.Socket;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import cloud.jgo.£;
import cloud.jgo.net.Control;
import cloud.jgo.net.Controllable;
import cloud.jgo.net.handlers.HandlerConnection;
import cloud.jgo.utils.command.Parameter;
import cloud.jgo.utils.command.RemoteCommand;

/**
 * 
 * @author Martire91 Handler TCP
 */
public abstract class TCPHandlerConnection extends HandlerConnection implements Controllable, Control {
	@Override
	public boolean enterRemoteCommand(RemoteCommand commnd, String... parameters) {
		boolean entered = false;
		if (commnd != null) {

			if (parameters.length == 1) {

				if (parameters[0] != null) {
					if (parameters[0].equals("help")) {

						// eseguo l'help del comando

						commnd.getHelpCommand().print();

						return entered = true;
					}
				}

			}
			// new object
			SimpleEntry<RemoteCommand, String[]> entry = new SimpleEntry<RemoteCommand, String[]>(commnd, parameters);

			// invio questo oggetto

			try {
				output.writeObject(entry);
			} catch (IOException e) {
				/*
				 * 
				 * JGO Auto-generated catch block Author : £ wasp91 £ Date 13 gen 2018
				 * 
				 */
				e.printStackTrace();
			}
			try {
				output.flush();
			} catch (IOException e) {
				/*
				 * 
				 * JGO Auto-generated catch block Author : £ wasp91 £ Date 13 gen 2018
				 * 
				 */
				e.printStackTrace();
			}
			entered = true;

		}
		return entered;
	}

	/**
	 * Spiegare differenze con il metodo statico della classe LocalCommand,
	 * differenze :
	 * 
	 * 1 differenza : Questo metodo non restituisce i valori get impostati
	 * precedentemente su un oggetto 2 differenza : Non restituisce il metodo
	 * toString 3 restituisce un array di oggetti che vanno poi distinti nel lato
	 * opposto a cui si inviano
	 */
	@Override
	public ArrayList<Object> executeRemoteCommand() {
		final ArrayList<Object> objects = new ArrayList<>();
		Entry<RemoteCommand, Object> entry = null;
		final ArrayList<String> comunications = new ArrayList<>();
		RemoteCommand command = null;
		entry = (Entry<RemoteCommand, Object>) this.receive();
		if (entry != null) {

			// qui verifico se stiamo parlando come secondo param del simple entry di un
			// array di params stringhe o proprio params

			if (entry.getValue() instanceof String[]) {
				// qui eseguiamo il comando
				// con i suoi params

				String[] parameters = (String[]) entry.getValue();
				command = entry.getKey();

				// per prima verifichiamo se il comando di per sè ha una esecuzione

				if (command.hasAnExecution()) {
					// eseguo il comando
					objects.add(command.execute());
				}

				// qui eseguo i params

				if (parameters != null) {
					if (parameters.length > 0) {

						// qui devo pensare a eseguire i params
						for (int i = 0; i < parameters.length; i++) {
							Parameter param = command.param(parameters[i]);
							if (param != null) {
								// e se il param non ha un valore da input sfruttabile
								// eseguo il param

								objects.add(param.execute());

							}
						}

					}
				}
			} else if (entry.getValue() instanceof Parameter[]) {

				// quindi primo passo ottengo i params
				Parameter[] params = (Parameter[]) entry.getValue();
				command = entry.getKey();

				// verifico se il comando ha una esecuzione

				if (command.hasAnExecution()) {
					objects.add(command.execute());
				}

				// qui eseguo i params

				if (params != null) {
					if (params.length > 0) {

						// qui in tanto faccio iterare i params per poterli eseguire

						for (int i = 0; i < params.length; i++) {

							// allora qui controllo se il param in questione non ha un valore da input
							// sfruttabile e allora lo eseguo

							objects.add(params[i].execute());

						}

					}
				}

			}
		}
		return objects;
	}

	public boolean enterRemoteCommand(RemoteCommand cmd, Parameter... params) {
		boolean entered = false;
		if (cmd != null) {

			// quindi qui non devo fare altro che mandare un simpleEntry al client con il
			// comando e l'array di params
			SimpleEntry<RemoteCommand, Parameter[]> entry = new SimpleEntry<RemoteCommand, Parameter[]>(cmd, params);

			send(entry);
			entered = true;
		}
		return entered;
	}

	@Override
	public String idSession() {
		return "tcp_" + £.generateStringRandom(45);
	}

	@Override
	public boolean enter_cmd(RemoteCommand commnd, String param, String inputValue) {
		boolean entered = false;

		if (commnd != null) {

			// faccio il controllo dell'help
			if (param != null && inputValue == null) {

				if (param.equals("help")) {

					// eseguo l'help del comando

					commnd.getHelpCommand().print();

					return entered = true;
				}

			}

			// invio comando
			try {
				output.writeObject(commnd);
			} catch (IOException e) {
				/*
				 * 
				 * JGO Auto-generated catch block Author : £ wasp91 £ Date 13 gen 2018
				 * 
				 */
				e.printStackTrace();
			}
			try {
				output.writeObject(param);
			} catch (IOException e) {
				/*
				 * 
				 * JGO Auto-generated catch block Author : £ wasp91 £ Date 13 gen 2018
				 * 
				 */
				e.printStackTrace();
			}
			try {
				output.writeObject(inputValue);
			} catch (IOException e) {
				/*
				 * 
				 * JGO Auto-generated catch block Author : £ wasp91 £ Date 13 gen 2018
				 * 
				 */
				e.printStackTrace();
			}
			try {
				output.flush();
				entered = true;
			} catch (IOException e) {
				/*
				 * 
				 * JGO Auto-generated catch block Author : £ wasp91 £ Date 13 gen 2018
				 * 
				 */
				e.printStackTrace();
			}

		}
		return entered;
	}

	@Override
	public ArrayList<Object> execute_cmd() {
		final ArrayList<Object> objects = new ArrayList<>();
		String parameter = null, inputValue = null;
		// prendo un comando
		RemoteCommand getCommand = null;
		try {
			getCommand = (RemoteCommand) input.readObject();
		} catch (ClassNotFoundException e) {
			/*
			 * 
			 * JGO Auto-generated catch block Author : £ wasp91 £ Date 13 gen 2018
			 * 
			 */
			e.printStackTrace();
		} catch (IOException e) {
			/*
			 * 
			 * JGO Auto-generated catch block Author : £ wasp91 £ Date 13 gen 2018
			 * 
			 */
			e.printStackTrace();
		}
		if (getCommand != null) {

			// qui eseguo il comando se ha una esecuzione

			if (getCommand.hasAnExecution()) {
				Object obj = getCommand.execute();
				if (obj != null) {
					objects.add(obj);
				}
			}

			// si continua da qui

			try {
				parameter = (String) input.readObject();
				inputValue = (String) input.readObject();
			} catch (ClassNotFoundException e) {
				/*
				 * 
				 * JGO Auto-generated catch block Author : £ wasp91 £ Date 13 gen 2018
				 * 
				 */
				e.printStackTrace();
			} catch (IOException e) {
				/*
				 * 
				 * JGO Auto-generated catch block Author : £ wasp91 £ Date 13 gen 2018
				 * 
				 */
				e.printStackTrace();
			}

			// qui controllo se il parametro è diverso da null in tanto

			if (parameter != null) {

				Parameter param = getCommand.param(parameter);

				if (param != null) {

					// verifico se il comando ha un valore da input

					if (param.hasInputValueExploitable()) {

						// qui verifico se c'è appunto un valore da input

						if (inputValue != null) {

							param.setInputValue(inputValue);

							Object obj = param.execute();

							if (obj != null) {
								objects.add(obj);
							}
						} else {

							// se non c'è un valore da input
							// allora si fa il controllo se c'è di mezzo un oggetto condiviso
							if (((RemoteCommand) param.getParent()).getSharedObject() != null) {
								// ottengo la classe dell'oggetto condiviso

								Class<?> classObj = ((RemoteCommand) param.getParent()).getSharedObject().getClass();

								Field field = null;
								try {
									field = classObj.getDeclaredField(param.getOnlyParam());
									field.setAccessible(true);
								} catch (NoSuchFieldException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (SecurityException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								if (field != null) {

									// okok voglio il valore di questa variabile sull'oggetto condiviso
									Object obj = null;

									try {
										obj = field.get(((RemoteCommand) param.getParent()).getSharedObject());
									} catch (IllegalArgumentException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (IllegalAccessException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									if (obj != null) {
										// inserisco il valore inserito nell'arraylist
										objects.add(param.getOnlyParam() + " = " + obj);
									} else {
										objects.add("Error #2 in param :" + param);
									}

								} else {
									// comunico che c'è stato un errore
									objects.add("Error #1 in param :" + param);
								}
							} else {
								objects.add(
										"The parameter (" + param.getOnlyParam() + ") requires a value from input #");
							}

						}

					}

					else {
						// non ha un valore da input
						// per cui controllo se noi lo abbiamo ricevuto

						if (inputValue != null) {

							// qui comunico che questo comando non ha un valore da input
							objects.add(
									"The parameter (" + param.getOnlyParam() + ") has not a exploitable input value #");

						} else {
							Object obj = param.execute();
							if (obj != null) {
								objects.add(obj);
							}
						}

					}

				}

			}

		}
		return objects;
	}

	private List<cloud.jgo.utils.command.RemoteCommand> clientCommands = new ArrayList<>();

	public boolean isSocketConnected() {
		return socket.isConnected();
	}

	private boolean handlerClosed = false;
	private boolean socketConnected = false;

	public boolean isHandlerClosed() {
		return handlerClosed;
	}

	// qui vengono ridefiniti i metodi send e receive
	@Override
	public void send(Object object) {
		try {
			output.writeObject(object);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			output.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<cloud.jgo.utils.command.RemoteCommand> getClientCommands() {
		return this.clientCommands;
	}

	public void setClientCommands(List<cloud.jgo.utils.command.RemoteCommand> commands) {
		this.clientCommands = commands;
	}

	@Override
	public Object receive() {

		Object obj = null;
		try {
			obj = this.input.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	private Socket socket;
	private ObjectInputStream input = null;
	private ObjectOutputStream output = null;

	// bisogna impostare il server per sapere quali sono i suoi sorgenti di lettura
	// e scrittura
	protected TCPServer server = null;

	public void setServer(TCPServer server) throws ClassNotFoundException, IOException {
		this.server = server;
		// casomai cancellare da qui fino a @

		// ricevo la lista dei comandi impostati dal client
		List<cloud.jgo.utils.command.RemoteCommand> clientCommands = (List<cloud.jgo.utils.command.RemoteCommand>) receive();

		// invio la lista dei comandi impostati dal server

		send(this.server.getServerCommands());

		// se la lista dei comandi ricevuti dal client è valida setto la mia lista dei
		// comandi
		// cioè i local comandi del server setto
		this.clientCommands = clientCommands;
		// @
	}

	public TCPServer getServer() {

		return server;
	}

	// faccio due costruttori ,uno che prende la socket e l'altro no

	public TCPHandlerConnection(Socket socket) throws IOException {
		this.socket = socket;
		this.output = new ObjectOutputStream(this.socket.getOutputStream());
		this.input = new ObjectInputStream(this.socket.getInputStream());
	}

	public TCPHandlerConnection(ObjectOutputStream out, ObjectInputStream in) throws IOException {
		this.input = in;
		this.output = out;
	}

	public TCPHandlerConnection() {
		this.socket = null;
	}

	public Socket getSocket() {
		return this.socket;
	}

	public void setSocket(Socket socket) throws IOException {
		this.socket = socket;
		this.output = new ObjectOutputStream(this.socket.getOutputStream());
		this.output.flush();
		this.input = new ObjectInputStream(this.socket.getInputStream());
	}

	@Override
	public void run() {
		try {
			try {
				manage();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean successfully() {
		// TODO Auto-generated method stub
		return this.socket.isConnected();
	}

	@Override
	public void closeConnection() {
		try {
			this.socket.close();
			this.input.close();
			this.output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.handlerClosed = true; // qui il flag diventa true
	}

}
