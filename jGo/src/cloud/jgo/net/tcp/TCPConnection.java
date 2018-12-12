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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.net.Socket;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import cloud.jgo.net.Connection;
import cloud.jgo.net.Control;
import cloud.jgo.net.Controllable;
import cloud.jgo.utils.command.Parameter;
import cloud.jgo.utils.command.RemoteCommand;

/**
 * 
 * @author Martire91<br>
 *         (Concrete)
 *
 */
public class TCPConnection implements Connection, Controllable, Control {

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
				getOut().writeObject(entry);
			} catch (IOException e) {
				/*
				 * 
				 * JGO Auto-generated catch block Author : £ wasp91 £ Date 13 gen 2018
				 * 
				 */
				e.printStackTrace();
			}
			try {
				getOut().flush();
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
				getOut().writeObject(commnd);
			} catch (IOException e) {
				/*
				 * 
				 * JGO Auto-generated catch block Author : £ wasp91 £ Date 13 gen 2018
				 * 
				 */
				e.printStackTrace();
			}
			try {
				getOut().writeObject(param);
			} catch (IOException e) {
				/*
				 * 
				 * JGO Auto-generated catch block Author : £ wasp91 £ Date 13 gen 2018
				 * 
				 */
				e.printStackTrace();
			}
			try {
				getOut().writeObject(inputValue);
			} catch (IOException e) {
				/*
				 * 
				 * JGO Auto-generated catch block Author : £ wasp91 £ Date 13 gen 2018
				 * 
				 */
				e.printStackTrace();
			}
			try {
				getOut().flush();
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
			getCommand = (RemoteCommand) getIn().readObject();
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
				parameter = (String) getIn().readObject();
				inputValue = (String) getIn().readObject();
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

	private Socket socket = null;
	private boolean successfully = false;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	private TCPClient client = null;
	private boolean socketConnected = false;
	private boolean connectionClosed = false;

	public boolean isConnectionClosed() {

		return this.connectionClosed;
	}

	@Override
	public boolean isSocketConnected() {
		return this.socket.isConnected();
	}

	public ObjectOutputStream getOut() {
		return out;
	}

	public ObjectInputStream getIn() {
		return in;
	}

	public TCPConnection(Socket socket, boolean successfully, TCPClient client)
			throws IOException, ClassNotFoundException {
		// TODO Auto-generated constructor stub
		this.client = client;
		this.socket = socket;
		this.successfully = successfully;
		if (this.successfully) {
			this.out = new ObjectOutputStream(this.socket.getOutputStream());
			this.in = new ObjectInputStream(this.socket.getInputStream());
			// casomai eliminare da qui a @
			// invio la lista dei comandi al server
			this.out.writeObject(client.getClientCommands());
			// ricevo la lista dei comandi impostati dal server
			List<cloud.jgo.utils.command.RemoteCommand> serverCommands = (List<cloud.jgo.utils.command.RemoteCommand>) this.in
					.readObject();
			client.setServerCommands(serverCommands);
			// @
		}
	}

	// si può usare anche dal server(da chiarire)
	public TCPConnection(Socket socket, boolean successfully) throws IOException, ClassNotFoundException {
		// TODO Auto-generated constructor stub
		this.socket = socket;
		this.successfully = successfully;
		if (this.successfully) {
			this.out = new ObjectOutputStream(this.socket.getOutputStream());
			this.in = new ObjectInputStream(this.socket.getInputStream());
		}
	}

	@Override
	public void send(Object object) {
		try {
			this.out.writeObject(object);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Object receive() {
		// TODO Auto-generated method stub
		Object object = null;
		try {
			object = this.in.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}

	@Override
	public boolean successfully() {
		// TODO Auto-generated method stub
		return this.successfully;
	}

	@Override
	public void closeConnection() {
		try {
			this.socket.close();
			this.out.close();
			this.in.close();
			this.connectionClosed = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Spiegare differenze con il metodo statico della classe LocalCommand,
	 * differenze :
	 * 
	 * 1 differenza : Questo metodo non restituisce i valori get impostati
	 * precedentemente su un oggetto 2 differenza : Non restituisce il metodo
	 * toString 3 restituisce un array di oggetti che vanno poi distinti nel lato
	 * opposto a cui si inviano 4 non invia comunicazioni se tipo non abbiamo dato
	 * il value input e il param lo richiedesse e non a comunicazioni se tipo
	 * mettiamo un value input e il param non lo richiedesse
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

							if (params[i].hasAnExecution()) {
								objects.add(params[i].execute());
							}

						}

					}
				}

			}
		}
		return objects;
	}
}
