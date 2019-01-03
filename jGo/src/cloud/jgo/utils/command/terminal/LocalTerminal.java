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
package cloud.jgo.utils.command.terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import cloud.jgo.£;
import cloud.jgo.utils.command.Command;
import cloud.jgo.utils.command.HelpCommands;
import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.execution.Execution;

/**
 * 
 * @author Martire91<br>
 *         This class represents the local terminal
 */
public class LocalTerminal extends Terminal implements Iterable<Entry<String, LocalCommand>> {
	protected Map<String, LocalCommand> commands = new HashMap<String, LocalCommand>();
	private List<LocalCommand> commandsList = null;
	protected HelpCommands helpCommands = new HelpCommands();
	protected String generalHelpValue = "help";
	private int countCommands = 0;
	private String name ;
	public void setName(String name) {
		this.name = name;
	}
	public LocalTerminal() {
		// private constructor
	}

	/**
	 * This method counts the terminal commands
	 * 
	 * @return the terminal commands number
	 */

	public int getCountCommands() {
		return this.commands.size();
	}

	/**
	 * This method creates a help command
	 */
	public void useGeneralHelp() {
		LocalCommand helpCommand = new LocalCommand(generalHelpValue, "Show General Help commands", new Execution() {

			@Override
			public Object exec() {
				getHelpCommands().print();
				return getHelpCommands();
			}
		});
		// aggiungo il comando
		addCommand(helpCommand);
	}

	/**
	 * This method adds the command into the terminal
	 * 
	 * @param command
	 *            the command
	 * @return true if the insertion has occurred
	 */
	public boolean addCommand(Command command) {
		LocalCommand command_ = this.commands.putIfAbsent(command.getCommand(), (LocalCommand) command);// sappiamo che
																										// return
																										// l'oggetto che
																										// trova già
																										// presente
		if (command_ == null) {
			this.helpCommands.add(((LocalCommand) command).getHelpCommand());
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method adds the commands into the terminal
	 * 
	 * @param commands
	 *            the commands
	 */
	public void addCommands(Command... commands) {
		Command[] commands_ = commands;
		for (int i = 0; i < commands_.length; i++) {
			addCommand(commands_[i]);
		}
	}

	/**
	 * This method returns the command
	 * 
	 * @param command
	 *            the input command
	 * @return the command
	 */
	public LocalCommand getCommand(String command) {
		if (this.commands.containsKey(command)) {
			return commands.get(command);
		} else {
			return null;
		}
	}

	/**
	 * This method replaces an old command with new command
	 * 
	 * @param oldCmd
	 *            the old command
	 * @param newCmd
	 *            the new command
	 * @return the result of the replacement
	 */
	public boolean replaceCommand(LocalCommand oldCmd, LocalCommand newCmd) {
		LocalCommand result = this.commands.replace(oldCmd.getCommand(), newCmd);
		System.out.println(result);
		return true;
	}

	/**
	 * This method returns the command
	 * 
	 * @param index
	 *            the command index
	 * @return the command
	 */
	public LocalCommand getCommand(int index) {
		if (index <= commands.values().toArray().length - 1 && index > -1) {
			return (LocalCommand) commands.values().toArray()[index];
		} else {
			return null;
		}
	}

	/**
	 * This method returns the terminal help
	 * 
	 * @return the terminal help
	 */

	public HelpCommands getHelpCommands() {
		return this.helpCommands;
	}

	/**
	 * This method returns the list commands
	 * 
	 * @return the list commands
	 */
	public List<LocalCommand> getCommands() {

		this.commandsList = new ArrayList<LocalCommand>();
		Iterator<Entry<String, LocalCommand>> iterator = iterator();
		while (iterator.hasNext()) {
			Map.Entry<java.lang.String, cloud.jgo.utils.command.LocalCommand> entry = (Map.Entry<java.lang.String, cloud.jgo.utils.command.LocalCommand>) iterator
					.next();
			this.commandsList.add(entry.getValue());
		}
		return this.commandsList;
	}

	@Override
	public Iterator<Entry<String, LocalCommand>> iterator() {
		return this.commands.entrySet().iterator();
	}

	protected final void useCommandResponseData(ArrayList<Object> data) {
		Iterator<Object> iterator = data.iterator();
		while (iterator.hasNext()) {
			Object object = iterator.next();
			if (object != null) {
				if (object instanceof String) {
					System.out.println(((String) object));
				} else if (object instanceof StringBuffer) {
					System.out.println(((StringBuffer) object).toString());
				} else {
					// è un oggetto di un altro tipo
					useCommandResponseObjectData(object);
				}
			}
		}
	}

	/**
	 * This method must be redefined to use the return object
	 * 
	 * @param obj
	 *            the returned object
	 */
	protected void useCommandResponseObjectData(Object obj) {
		// da implementare
	}

	@Override
	protected void implOpen() {
		String inputCommand = £.nextLine();
		if (inputCommand.equals(getExitCommand())) {
			close();
		} else {
			ArrayList<Object> infoCommand = LocalCommand.executeInputCommand(inputCommand, getCommands());
			if (infoCommand != null) {
				useCommandResponseData(infoCommand);
			}
		}
	}

	@Override
	public Object command(String command) throws IOException, InterruptedException {
		return LocalCommand.executeInputCommand(command, getCommands());
	}

	/**
	 * This method removes a command
	 * 
	 * @param command
	 *            the command
	 * @return true if the command has been deleted
	 */
	public boolean remove(String command) {
		LocalCommand cmd = commands.remove(command);
		if (cmd != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public LocalCommand getExitCommand() {
		// TODO Auto-generated method stub
		return (LocalCommand) exitCommand;
	}

	@Override
	public void setExitCommand(String exitCommand) {
		this.exitCommand = new LocalCommand(exitCommand, "Closes the program and uninstall the components");
		((LocalCommand) this.exitCommand).setExecution(new Execution() {

			@Override
			public Object exec() {
				close();
				return null;
			}
		});
		addCommand((LocalCommand) this.exitCommand);
	}

	@Override
	public String getCommandRequest() {
		// TODO Auto-generated method stub
		String value = "£_:";
		if (getName()!=null) {
			if (!getName().equals("")) {
				value = ""+getName()+"_:";
			}
		}
		return value ;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name ;
	}

}
