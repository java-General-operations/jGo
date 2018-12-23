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
package cloud.jgo.utils.command;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import cloud.jgo.j£;
import cloud.jgo.£;
import cloud.jgo.utils.command.execution.Execution;
import cloud.jgo.utils.command.execution.SharedExecution;
import cloud.jgo.utils.command.terminal.Terminal;
import cloud.jgo.utils.command.terminal.phase.Phase;

/**
 * 
 * @author Martire91<br>
 *         This class represents a local command from a terminal
 */
public class LocalCommand implements Command, Iterable<Entry<String, Parameter>>, Shareable {
	private static final long serialVersionUID = 1L;
	private Execution execution = null;
	protected String help = null;
	private String effect = null;
	private LocalCommand.HelpCommand helpCommand = new LocalCommand.HelpCommand();
	protected String command = null;
	private Object sharedObject = null;
	private static String helpValue = "help";
	private String inputValue = null;
	private boolean inputValueExploitable = false;
	private static boolean inputHelpExploitable = false;
	private boolean merged = false;
	private Phase belongsTo = null;

	public LocalCommand(String command, String help) {
		// quando inizializzo il costruttore
		this.help = help;
		this.command = command;
		// setto l'help
		this.helpCommand.reload(this);
	}

	public LocalCommand(String command, String help, Execution execution) {
		this.command = command;
		this.help = help;
		this.helpCommand.reload(this);
		this.execution = execution;
	}

	public void setBelongsTo(Phase belongsTo) {
		this.belongsTo = belongsTo;
	}

	@Override

	public Phase getBelongsTo() {
		return this.belongsTo;
	}

	// qui ci sarà una struttura dati che reggerà il gioco
	protected Map<String, Parameter> structure = new HashMap<String, Parameter>();

	public Map<String, Parameter> getStructure() {
		return structure;
	}

	public boolean useThread = false;

	// version 1.0.9
	public void setCommand(String command) {
		this.command = command;
	}

	@Override
	public String toString() {
		return this.command;
	}

	@Override
	public boolean hasAPhase() {
		if (getBelongsTo() != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean equals(Object obj) {
		boolean equals = false;
		Command command = (Command) obj;
		int total = 0;
		int count = 0;
		if (this.getCommand().equals(command.getCommand())) {
			if (this.countParameters() == command.countParameters()) {
				equals = true;
			}
		}
		return equals;
	}

	/**
	 * This method reset the structure
	 */
	public void clearStructure() {
		this.structure.clear();
	}

	/**
	 * This method divides the command for the parameters
	 * 
	 * @param inputCommand
	 *            the input command
	 * @return the parameters list
	 */
	public static List<String> splitForParameters(String inputCommand) {

		// in tanto qui è importante prendere il comando ,prima di tutto
		String onlyCommand = null;
		List<String> parameters = new ArrayList<>();

		// splitto per spazio

		String[] splitForSpace = inputCommand.split(" ");

		// controllo che ci siano params

		if (splitForSpace.length > 1) {
			// controllo che la prima riga ,cioè il solo comando non sia vuoto
			if (!splitForSpace[0].isEmpty()) {
				onlyCommand = splitForSpace[0];

				// si continua da qui
				// ora devo prendere dalla lunghezza del comando ,quello che rimane

				String rest = inputCommand.substring(onlyCommand.length()).trim();

				// okok ora splitto il resto per il simbolo del param

				String[] splitForParameters = rest.split("-");

				// qui faccio iterare i params per correggerli
				for (int i = 0; i < splitForParameters.length; i++) {

					// qui devo controllare se il param in questione ha un valore da input
					String param = splitForParameters[i];

					if (!param.isEmpty()) {
						splitForSpace = param.split(" ");
						// quindi se lo split è uno vuol dire che è senza input value
						// invece se è maggiore di 1 vuol dire che ha valore da input
						if (splitForSpace.length > 1) { // c'è il valore da input,quindi prendo il primo elemento
							param = splitForSpace[0];
						}
						// aggiungo il param alla lista
						parameters.add(param);
					}
				}

			}
		}
		return parameters;
	}

	/**
	 * This method divides the command for the parameters
	 * 
	 * @param inputCommand
	 *            the input command
	 * @return the parameters list
	 */
	public static String[] splitForParameters_2(String inputCommand) {

		// in tanto qui è importante prendere il comando ,prima di tutto
		String onlyCommand = null;
		List<String> parameters = new ArrayList<>();
		String[] params = null;
		// splitto per spazio

		String[] splitForSpace = inputCommand.split(" ");

		// controllo che ci siano params

		if (splitForSpace.length > 1) {
			// controllo che la prima riga ,cioè il solo comando non sia vuoto
			if (!splitForSpace[0].isEmpty()) {
				onlyCommand = splitForSpace[0];

				// si continua da qui
				// ora devo prendere dalla lunghezza del comando ,quello che rimane

				String rest = inputCommand.substring(onlyCommand.length()).trim();

				// okok ora splitto il resto per il simbolo del param

				String[] splitForParameters = rest.split("-");

				// qui faccio iterare i params per correggerli
				for (int i = 0; i < splitForParameters.length; i++) {

					// qui devo controllare se il param in questione ha un valore da input
					String param = splitForParameters[i];

					if (!param.isEmpty()) {
						splitForSpace = param.split(" ");
						// quindi se lo split è uno vuol dire che è senza input value
						// invece se è maggiore di 1 vuol dire che ha valore da input
						if (splitForSpace.length > 1) { // c'è il valore da input,quindi prendo il primo elemento
							param = splitForSpace[0];
						}
						// aggiungo il param alla lista
						parameters.add(param);
					}
				}

				// qui si controlla se la lista ha un size accettabile

				if (parameters.size() > 0) {
					params = new String[parameters.size()];
					for (int i = 0; i < parameters.size(); i++) {
						params[i] = parameters.get(i);
					}
				}

			}
		}
		return params;
	}

	/**
	 * This method sets the help command from input
	 * 
	 * @param exploitable
	 *            the flag
	 */
	public static void setInputHelpExploitable(boolean exploitable) {
		inputHelpExploitable = exploitable;
	}

	/**
	 * This method checks if the command has the exploitable help command
	 * 
	 * @return the flag
	 */

	public boolean hasInputHelpExploitable() {
		return inputHelpExploitable;
	}

	/**
	 * 
	 * @author Martire91<br>
	 *         This class is the command help
	 */
	public static class HelpCommand implements Serializable, Comparable<HelpCommand> {
		protected LocalCommand command = null;
		protected StringBuffer buffer = new StringBuffer();
		private final static long serialVersionUID = 1L;

		/**
		 * This method prints the help command
		 */
		public void print() {

			System.out.println(buffer.toString());
		}

		@Override
		public String toString() {
			/*
			 * 
			 * JGO Auto-generated method stub Author : £ wasp91 £ Date 06 gen 2018
			 * 
			 */
			return this.buffer.toString();
		}

		/**
		 * This command reloads the help command
		 * 
		 * @param command
		 *            the command
		 */
		public void reload(LocalCommand command) {
			this.command = command;
			this.buffer = new StringBuffer();
			buffer.append(
					"===================================================================================\n");
			buffer.append("HELP Of " + "\"" + this.command.command + "\" - Phase :"
					+ ((LocalCommand) this.command).getBelongsTo() + "\n");
			buffer.append(
					"===================================================================================\n");

			// qui devo prendere tutti i parameters
			Collection<Parameter> collection = command.structure.values();
			// qui ci sarà la descrizione del comando root
			buffer.append(this.command.getHelp().toUpperCase() + "\n\n");
			buffer.append("* Parameters :" + collection + " :\n\n");

			if (this.command.hasParameters()) {

				// ci sono parametri

				// quindi qui devo prendere i params
				Iterator<Entry<String, Parameter>> iterator = command.iterator();
				List<Parameter> params = new ArrayList<>();
				while (iterator.hasNext()) {
					Map.Entry<java.lang.String, cloud.jgo.utils.command.Parameter> entry = (Map.Entry<java.lang.String, cloud.jgo.utils.command.Parameter>) iterator
							.next();
					Parameter param = entry.getValue();
					buffer.append(param.getParam() + "=" + param.getParameterHelp() + "  / has input value ="
							+ param.hasInputValueExploitable() + "\n");
				}
			}
		}

		@Override
		public int compareTo(HelpCommand arg0) {
			return this.command.command.compareTo(arg0.command.command);
		}

	}

	@Override
	public void setExecution(Execution execution) {
		this.execution = execution;
	}

	@Override
	public Object execute() {
		Object execute = null;
		if (hasAnExecution()) {
			if (useThread == true) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						if (getExecution()instanceof SharedExecution) {
							((SharedExecution)getExecution()).setCurrentSharer(LocalCommand.this);
						}
					}
				}).start();
			} else {
				if (getExecution()instanceof SharedExecution) {
					((SharedExecution)getExecution()).setCurrentSharer(this);
				}
				execute = getExecution().exec();
			}
			return execute;
		} else {
			return execute;
		}
	}

	@Override

	public Execution getExecution() {
		return this.execution;
	}

	@Override

	public String getHelp() {
		return this.help;
	}

	@Override

	public String getEffect() {
		return this.effect;
	}

	@Override

	public boolean hasAnExecution() {
		if (this.execution != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Parameter addParam(String param, String help) {
		Parameter param_ = null;
		if (!isParameter(param)) {

			param_ = new DefaultParameter(param, help);

			// qui setto il parent del parametro
			param_.setParent(this);

			// aggiungo il param alla struttura dati

			this.structure.put(param, param_);

			getHelpCommand().reload(this);
		}
		return param_;
	}

	@Override
	public Parameter param(String param) {
		if (this.structure.containsKey(param)) {
			return this.structure.get(param);
		} else {
			return null;
		}
	}

	@Override
	public Parameter param(int index) {
		Iterator<Entry<String, Parameter>> iterator = this.iterator();
		Parameter param = null;
		while (iterator.hasNext()) {

			Entry<String, Parameter> entry = iterator.next();

			if (£.value() == index) {
				param = entry.getValue();

				break;
			}

			£.increment();
		}
		return param;
	}

	/**
	 * This method returns the command help
	 * 
	 * @return the command help
	 */
	public HelpCommand getHelpCommand() {
		return this.helpCommand;
	}

	@Override
	public boolean isParameter(String param) {
		boolean isParameter = false;
		Iterator<Entry<String, Parameter>> iterator = this.iterator();
		while (iterator.hasNext()) {
			Map.Entry<java.lang.String, cloud.jgo.utils.command.Parameter> entry = (Map.Entry<java.lang.String, cloud.jgo.utils.command.Parameter>) iterator
					.next();
			String key = entry.getKey();
			if (param.equals(key)) {
				isParameter = true;
				break;
			}
		}
		return isParameter;
	}

	@Override
	public Iterator<Entry<String, Parameter>> iterator() {
		return this.structure.entrySet().iterator();
	}

	@Override
	public void removeAllParameters() {

		this.structure = new HashMap<String, Parameter>();

		getHelpCommand().reload(this);

	}

	@Override
	public boolean removeParam(String param) {
		if (countParameters() > 0) {
			Parameter param_ = this.structure.remove(param);

			if (param_ != null) {
				getHelpCommand().reload(this);
				return true;

			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override

	public int countParameters() {

		return this.structure.size();

	}

	@Override
	public boolean removeParam(int index) {
		Parameter param = param(index);
		if (param != null) {
			return removeParam(param.getOnlyParam());
		} else {
			return false;
		}

	}

	@Override
	public boolean replace(String param, Parameter newValue) {
		if (countParameters() > 0) {

			// qui individuo il param
			Parameter param_ = param(param);
			if (param_ != null) {

				boolean flag = this.structure.replace(param, param_, newValue);

				if (flag == true) {
					getHelpCommand().reload(this);
				}
				return flag;
			} else {
				return false;
			}

		} else {
			return false;
		}
	}

	@Override

	public String getCommand() {
		return this.command;
	}

	@Override
	public Object executeParam(String param) {

		// qui in tanto verifico se il param è un vero param del comando
		if (isParameter(param)) {

			// ottengo il param
			Parameter get = this.param(param);

			return get.execute();
		} else {
			return null;
		}
	}

	@Override
	public Object executeParam(String param, String inputValue) {

		// qui in tanto verifico se il param è un vero param del comando
		if (isParameter(param)) {

			// ottengo il param
			Parameter get = this.param(param);

			get.setInputValue(inputValue);

			return get.execute();
		} else {
			return null;
		}

	}

	@SuppressWarnings("unused")
	private static class PrivateEntry {

		private String param;
		private String value;

		public PrivateEntry(String param, String value) {
			this.param = param;
			this.value = value;
		}

		public String getParam() {
			return param;
		}

		public void setParam(String param) {
			this.param = param;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}

	/**
	 * Questo metodo va spiegato bene : lavora con la stampa di sistema mette a
	 * disposizione una struttura di get and set per le variabili degli oggetti
	 * condivisi su cui si lavora. per settare per esempio la var nome di persona mi
	 * basterà fare :
	 * 
	 * persona -nome Gioacchino
	 * 
	 * Per recuperare il nome poi faccio
	 * 
	 * persona -nome Questo discorso, attenzione è solo per le variabili degli
	 * oggetti condivisi poichè il metodo quando riceve un comando che vuole
	 * accedere a una var,recupera l'oggetto condiviso di quel comando e su di esso
	 * tenta di recuperare quella variabile, il valore della variabile viene
	 * stampato anche nel caso in cui sia null, appunto per avvisare l'utente di
	 * questo valore nullo
	 *
	 */
	// a questo metodo importantissimo ho tolto lo static
	// quindi casomai da problems vedere un attimino o aggiungere lo static al
	// metodo
	// okok qui iniziano i metodi complessi
	// spiegare cosa ritorna questo metodo
	private static Object objectReturn = null;

	/**
	 * This method executes the command and its possible parameters.
	 * 
	 * @param inputCommand
	 *            the input command
	 * @param commands
	 *            the commands list
	 * @return the returned object from execution
	 */
	public static ArrayList<Object> executeInputCommand(String inputCommand, List<? extends Command> commands) {
		final ArrayList<Object> commandReturnList = new ArrayList<>();
		objectReturn = null;
		// prima cosa da fare prendere il comando
		LocalCommand getCommand = null;
		String[] split = inputCommand.split(" ");

		// prendo il comando padre
		String command = null;
		if (!split[0].isEmpty()) {
			command = split[0];
			// controllo se c'è qualcosa dopo il comando
			if (split.length > 1) {
				// c'è qualcosa dopo il comando
				// quindi devo prendere quello che c'è dopo il comando
				int start = command.length();
				String rest = inputCommand.substring(start).trim();
				// okok ora qui devo controllare se il resto è uguale all'helps
				// oppure splittarlo per parameters
				if (rest.equals(LocalCommand.helpValue)) {
					// qui eseguo l'help
					// qui devo prendere il comando
					for (int i = 0; i < commands.size(); i++) {
						if (command.equals(commands.get(i).getCommand())) {
							getCommand = (LocalCommand) commands.get(i);
							break; // qui posso uscire perchè il comando è senza params e lo abbiamo trovato
						}
					}
					// eseguo l'help solo se questo è sfruttabile
					if (getCommand.hasInputHelpExploitable()) {
						getCommand.getHelpCommand().print();
					}

				} else if (!rest.contains(Parameter.SEPARATOR)) {
					// quindi qui ha validità solo se è richiesto un valore da input dal comando
					// e gli viene fornito, perchè se non gli venisse fornito non si entrerebbe quià
					// dentro
					// qui dovrebbe entrare
					// se si da un comando con
					// valore di input, non si possono aggiungere parametri
					// quindi si presume che ci siano pochi comandi con valore da input

					// 1 passo : prendo il comando
					for (int i = 0; i < commands.size(); i++) {
						if (command.equals(commands.get(i).getCommand())) {
							getCommand = (LocalCommand) commands.get(i);
							break; // qui posso uscire perchè il comando è senza params e lo abbiamo trovato
						}
					}
					if (getCommand != null) {
						// 2 passo : verifico se il comando ha un valore da input
						if (getCommand.hasInputValueExploitable()) {
							// controllo se di fatto c'è un valore da input
							getCommand.setInputValue(rest);
								// eseguo il comando
								objectReturn = getCommand.execute();
							if (objectReturn != null) {
								commandReturnList.add(objectReturn);
								objectReturn = commandReturnList;
							}
						}
					}
				} else {

					// devo qui prendere i parameters
					String[] splitForParameters = rest.split(Parameter.SEPARATOR);
					List<PrivateEntry> entries = new ArrayList<LocalCommand.PrivateEntry>();
					String param, paramValue = null;
					for (int i = 0; i < splitForParameters.length; i++) {
						paramValue = null;
						param = null;
						if (!(splitForParameters[i]).isEmpty()) {

							param = splitForParameters[i].split(" ")[0];

							if (splitForParameters[i].contains(" ")) {

								// c'è il valore

								paramValue = splitForParameters[i].substring(param.length()).trim();
							}
							if (paramValue != null) {
								if (paramValue.isEmpty()) {
									paramValue = null;
								}
							}
							// qui prendo l'entry
							PrivateEntry entry = new PrivateEntry(param, paramValue);
							entries.add(entry);
						}

					}
					// ora qui dobbiamo in tanto far iterare la lista di entries
					if (entries.size() > 0) {
						// qui per prima cosa devo individuare il comando
						for (int i = 0; i < commands.size(); i++) {
							if (command.equals(commands.get(i).getCommand())) {
								getCommand = (LocalCommand) commands.get(i);
								break; // qui posso uscire perchè il comando è senza params e lo abbiamo trovato
							}
						}

						if (getCommand != null) {
							/***
							 * @author MARTIRE91 : QUI è IMPORTANTE SE UN GIORNO VOGLIAMO ESEGUIRE UN
							 *         COMANDO CHE HA UNA ESECUZIONE E ANCHE DEI PARAMS E QUI CHE DOBBIAMO
							 *         ESEGUIRLO,PERO C'è SOLO UN PROBLEMA POI E' DA TENERE PRESENTE CHE SE
							 *         PER ESEMPIO DIAMO UN COMANDO SIMILE : person -toString, lui
							 *         reinizializza di nuovo la persona e quindi ci stampa valori nulli per
							 *         il momento, l'esecuzione del comando ha valore solo se quest'ultimo
							 *         non ha params,cosa che prima o poi dovremo correggere.
							 */
							// itero la lista di entries
							for (int i = 0; i < entries.size(); i++) {
								PrivateEntry entry = entries.get(i);

								// okok qui per prima cosa verifico che il param corrente dell'entry
								// sia effettivamente un param del comando ottenuto

								if (getCommand.isParameter(entry.param)) {

									// ottengo il param vero e proprio per poi controllare se ha un valore da input
									// sfruttabile
									Parameter getParameter = getCommand.param(entry.param);

									if (getParameter != null) {

										// controllo se questo param ha un valore da input struttabile

										if (getParameter.hasInputValueExploitable()) {

											// qui devo controllare se l'entry ha il valore del parametro
											if (entry.value != null) {
												// setto il valore sfruttabile
												getParameter.setInputValue(entry.value);
												// eseguo il parametro
												objectReturn = getParameter.execute();
												if (objectReturn != null) {
													commandReturnList.add(objectReturn);

													// diciamo che in questo punto del metodo
													// il nostro oggetto di restituzione
													// diventa l'arraylist
													objectReturn = commandReturnList;
												}
											} else {

												// l'entry non ha un valore del parametro
												// eppure ci vuole ,quindi qui è un errore

												// okok ecco la zona di codice che ci interessa
												// qui in tanto è fattibile solo se si sta lavorando
												// con un oggetto condiviso
												// quindi per prima cosa verifico che ci sia
												// un oggetto condiviso

												LocalCommand parent = (LocalCommand) getParameter.getParent();
												Object obj = parent.getSharedObject();
												if (obj != null) {

													/*
													 * Gestire con la RIFLESSIONE :)
													 */

													// prendo il nome del parametro

													String onlyParam = getParameter.getOnlyParam();

													// ora vado a esaminare questo oggetto condiviso

													Field field = null;

													try {
														field = obj.getClass().getDeclaredField(onlyParam);
														field.setAccessible(true);
													} catch (NoSuchFieldException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													} catch (SecurityException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}

													if (field != null) {

														// okok si è trovato
														// questa variabile
														// ora dobbiamo controllare se ha un valore
														Object valueObj = null;
														try {
															valueObj = field.get(obj);
														} catch (IllegalArgumentException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														} catch (IllegalAccessException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														}

														// qua sia che è null oppure che abbia un valore
														// noi stampiamo tale valore

														if (valueObj != null) {
															objectReturn = onlyParam + " = " + valueObj;
															// System.out.println(objectReturn);
															commandReturnList.add(objectReturn);
															objectReturn = commandReturnList;
														} else {
															// qui stampo il valore nullo del valore della var
															// e in più la stampa che ricorda che il param necessita
															// di un valore

															objectReturn = onlyParam + " = " + valueObj
																	+ "\nThis parameter requires a value";
															// System.out.println(objectReturn);
															commandReturnList.add(objectReturn);
															objectReturn = commandReturnList;
														}

													}

												} else {
													// qui vuol dire che non c'è un oggetto condiviso
													objectReturn = "This parameter requires a value";
													commandReturnList.add(objectReturn);
													objectReturn = commandReturnList;
												}

											}

										} else {

											// qui significa che il parametro non ha un valore da input sfruttabile

											// allora se non ha un valore sfruttabile da input
											// bisogna per prima cosa verificare se l'apposito entry ha il value
											// in tal caso lo diamo come errore,quindi non succede niente
											// perchè l'utente ha sbagliato a scrivere il comando
											if (entry.value != null) {
												objectReturn = "The parameter (" + getParameter
														+ ") has not a exploitable input value #";
												// System.out.println(objectReturn);
												commandReturnList.add(objectReturn);
												objectReturn = commandReturnList;
											} else {
												// eseguo il parametro
												objectReturn = getParameter.execute();
												commandReturnList.add(objectReturn);
												objectReturn = commandReturnList;
											}
										}
									}
								}
							}
						}
					}
				}
			} else if (split.length == 1) {
				// non c'è niente dopo il comando,quindi qui posso eseguirlo tranquillamente
				for (int i = 0; i < commands.size(); i++) {
					if (command.equals(commands.get(i).getCommand())) {
						getCommand = (LocalCommand) commands.get(i);
						break; // qui posso uscire perchè il comando è senza params e lo abbiamo trovato
					}
				}
				if (getCommand != null) {
					objectReturn = getCommand.execute();
					commandReturnList.add(objectReturn);
					objectReturn = commandReturnList;
				}
			}
		}
		return (ArrayList<Object>) objectReturn;
	}

	private static LocalCommand getCommand = null;

	/**
	 * This method returns the command starting from the input command
	 * 
	 * @param inputCommand
	 *            the input command
	 * @param commands
	 *            the commands list
	 * @return the command
	 */
	public static LocalCommand getCommand(String inputCommand, List<? extends Command> commands) {
		String[] split = inputCommand.split(" ");
		getCommand = null;
		// prendo il comando padre
		String command = null;
		if (!split[0].isEmpty()) {
			command = split[0];
			// controllo se c'è qualcosa dopo il comando
			if (split.length > 1) {
				// c'è qualcosa dopo il comando
				// quindi devo prendere quello che c'è dopo il comando
				int start = command.length();
				String rest = inputCommand.substring(start).trim();
				// okok ora qui devo controllare se il resto è uguale all'helps
				// oppure splittarlo per parameters

				if (rest.equals(LocalCommand.helpValue)) {
					// qui eseguo l'help
					// qui devo prendere il comando
					for (int i = 0; i < commands.size(); i++) {
						if (command.equals(commands.get(i).getCommand())) {
							getCommand = (LocalCommand) commands.get(i);
							break; // qui posso uscire perchè il comando è senza params e lo abbiamo trovato
						}
					}
					// // eseguo l'help solo se questo è sfruttabile
					// if(getCommand.hasInputHelpExploitable()){
					// getCommand.getHelpCommand().print();
					// }
				} else if (!rest.contains(Parameter.SEPARATOR)) {
					for (int i = 0; i < commands.size(); i++) {
						if (command.equals(commands.get(i).getCommand())) {
							getCommand = (LocalCommand) commands.get(i);
							break; // qui posso uscire perchè il comando è senza params e lo abbiamo trovato
						}
					}
					if (getCommand != null) {
						// solo se il comando non ha un valore da input
						// setto getCommand a null
						if (!getCommand.hasInputValueExploitable()) {
							getCommand = null;
						}
					}
				} else {

					// devo qui prendere i parameters
					String[] splitForParameters = rest.split(Parameter.SEPARATOR);
					List<PrivateEntry> entries = new ArrayList<LocalCommand.PrivateEntry>();
					String param, paramValue = null;
					for (int i = 0; i < splitForParameters.length; i++) {
						paramValue = null;
						param = null;
						if (!(splitForParameters[i]).isEmpty()) {

							param = splitForParameters[i].split(" ")[0];

							if (splitForParameters[i].contains(" ")) {

								// c'è il valore

								paramValue = splitForParameters[i].substring(param.length()).trim();
							}
							if (paramValue != null) {
								if (paramValue.isEmpty()) {
									paramValue = null;
								}
							}
							// qui prendo l'entry
							PrivateEntry entry = new PrivateEntry(param, paramValue);
							entries.add(entry);
						}

					}
					// ora qui dobbiamo in tanto far iterare la lista di entries
					if (entries.size() > 0) {
						// qui per prima cosa devo individuare il comando
						for (int i = 0; i < commands.size(); i++) {
							if (command.equals(commands.get(i).getCommand())) {
								getCommand = (LocalCommand) commands.get(i);
								break; // qui posso uscire perchè il comando è senza params e lo abbiamo trovato
							}
						}
						if (getCommand != null) {

							boolean thereIsError = false;
							// itero la lista di entries
							for (int i = 0; i < entries.size(); i++) {
								PrivateEntry entry = entries.get(i);

								// okok qui per prima cosa verifico che il param corrente dell'entry
								// sia effettivamente un param del comando ottenuto

								if (getCommand.isParameter(entry.param)) {

									// ottengo il param vero e proprio per poi controllare se ha un valore da input
									// sfruttabile
									Parameter getParameter = getCommand.param(entry.param);

									if (getParameter != null) {

										// controllo se questo param ha un valore da input struttabile

										if (getParameter.hasInputValueExploitable()) {

											// qui devo controllare se l'entry ha il valore del parametro
											if (entry.value != null) {
												// setto il valore sfruttabile

												getParameter.setInputValue(entry.value);
											}
											// else{
											//
											// if (getCommand.getSharedObject()==null) {
											// // l'entry non ha un valore del parametro
											// // eppure ci vuole ,quindi qui è un errore
											// thereIsError = true ;
											// }
											// }
											//
										}
										// else{
										//
										// // qui significa che il parametro non ha un valore da input sfruttabile
										//
										// // allora se non ha un valore sfruttabile da input
										// // bisogna per prima cosa verificare se l'apposito entry ha il value
										// // in tal caso lo diamo come errore,quindi non succede niente
										// // perchè l'utente ha sbagliato a scrivere il comando
										// if(entry.value!=null){
										//
										// thereIsError = true ;
										// }
										//
										//
										// }
									}

								} else {
									thereIsError = true;
								}

							}
							if (thereIsError) {
								getCommand = null;
							}

						}

					}

				}

			}

			else if (split.length == 1) {
				// non c'è niente dopo il comando,quindi qui posso eseguirlo tranquillamente
				for (int i = 0; i < commands.size(); i++) {
					if (command.equals(commands.get(i).getCommand())) {
						getCommand = (LocalCommand) commands.get(i);
						break; // qui posso uscire perchè il comando è senza params e lo abbiamo trovato
					}
				}

			}
		}
		return getCommand;

	}

	@Override
	public List<Parameter> sortParameters() {
		List<Parameter> params = null;
		if (hasParameters()) { // ordina solo se ci sono params

			params = new ArrayList<>();
			Iterator<Entry<String, Parameter>> itera = this.iterator();
			while (itera.hasNext()) {
				Map.Entry<java.lang.String, cloud.jgo.utils.command.Parameter> entry = (Map.Entry<java.lang.String, cloud.jgo.utils.command.Parameter>) itera
						.next();
				Parameter param = entry.getValue();
				params.add(param);
			}

			// qui abbiamo la lista riempita

			Collections.sort(params);

		}
		return params;
	}

	@Override

	public boolean hasParameters() {
		if (this.countParameters() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override

	public <T> T getSharedObject() {
		// TODO Auto-generated method stub
		return (T) this.sharedObject;
	}

	@Override
	public <T> void shareObject(T sharedObject) {
		// TODO Auto-generated method stub
		this.sharedObject = sharedObject;
		if (this.sharedObject != null) {
			// solo se il param non lo ha già lo aggiungo
			if (!isParameter("to_string")) {

				// se toString non è un param lo aggiungi

				final Parameter pToString = this.addParam("to_string",
						"This parameter shows the toString method of the shared object");
				pToString.setExecution(new Execution() {

					@Override
					public Object exec() {

						return sharedObject.toString();

					}
				});
			} else {

				// se invece to_string è un parametro senza eliminarlo
				// mi basta sostituirne l'esecuzione
				// quindi ottengo il param
				Parameter param_toString = this.param("to_string");

				param_toString.setExecution(new Execution() {

					@Override
					public Object exec() {

						return sharedObject.toString();
					}
				});
			}
		} else {
			// qui entra se l'oggetto condiviso è null
			// cancello il parametro perchè non vi è più l'oggetto condiviso
			if (isParameter("to_string")) {
				removeParam("to_string");
			}
		}
	}

	@Override
	public Parameter[] params() {
		if (structure.values().toArray().length > 0) {
			Object[] objects = structure.values().toArray();
			Parameter[] params = new Parameter[objects.length];
			for (int i = 0; i < objects.length; i++) {
				params[i] = (Parameter) objects[i];
			}
			return params;
		} else {
			return null;
		}
	}

	@Override
	public String getInputValue() {
		// TODO Auto-generated method stub
		return this.inputValue;
	}

	@Override
	public void setInputValue(String inputValue) {
		// TODO Auto-generated method stub
		this.inputValue = inputValue;
	}

	@Override
	public boolean hasInputValueExploitable() {
		// TODO Auto-generated method stub
		return this.inputValueExploitable;
	}

	@Override
	public void setInputValueExploitable(boolean exploitable) {
		// TODO Auto-generated method stub
		this.inputValueExploitable = exploitable;
		getHelpCommand().reload(this);
	}

	
	@Override
	public Parameter shareParameter(Parameter parameter) {
		Parameter p = addParam(parameter.getOnlyParam(),parameter.getParameterHelp());
		// adesso vado a prendere le info + importanti del parametro che ho ricevuto
		p.setInputValueExploitable(parameter.hasInputValueExploitable());
		p.shared = true ;
		return p ;
	}

	@Override
	public List<Parameter> getSharedParameters() {
		List<Parameter>params = sortParameters();
		List<Parameter>sharedParams = new ArrayList<>();
		for (int i = 0; i < params.size(); i++) {
			if (params.get(i).shared) {
				sharedParams.add(params.get(i));
			}
		}
		return sharedParams ;
	}
	
	public List<Parameter>getUnSharedParameters(){
		List<Parameter>params = sortParameters();
		List<Parameter>unSharedParams = new ArrayList<>();
		for (int i = 0; i < params.size(); i++) {
			if (!params.get(i).shared) {
				unSharedParams.add(params.get(i));
			}
		}
		return unSharedParams ;
	}

	@Override
	public Type getSharerType() {
		// TODO Auto-generated method stub
		return Type.COMMAND;
	}

	@Override
	public void shareItEntirely(Parameter parameter, SharedExecution execution) {
		Parameter p = addParam(parameter.getOnlyParam(),parameter.getParameterHelp());
		// adesso vado a prendere le info + importanti del parametro che ho ricevuto
		p.setInputValueExploitable(parameter.hasInputValueExploitable());
		p.setExecution(execution);
		p.shared = true ;
	}

	@Override
	public void shareItEntirely(Parameter parameter) {
		shareItEntirely(parameter, (SharedExecution) parameter.getExecution());
	}
	
	
}