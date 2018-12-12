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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import cloud.jgo.utils.command.LocalCommand.HelpCommand;

/**
 * 
 * @author Martire91<br>
 *         This class is the general help. Useful for terminals.
 */
public class HelpCommands implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<HelpCommand> helpCommands = new ArrayList<HelpCommand>();

	/**
	 * This method adds the command help
	 * 
	 * @param help
	 *            the command help
	 */
	public void add(HelpCommand help) {
		this.helpCommands.add(help);
	}

	/**
	 * This method counts the helps
	 * 
	 * @return the helps number
	 */
	public int getCountHelps() {

		return this.helpCommands.size();
	}

	/**
	 * This method adds the helps
	 * 
	 * @param args
	 *            the helps
	 */
	public void add(HelpCommand... args) {
		HelpCommand[] array = args;
		for (int i = 0; i < array.length; i++) {
			this.helpCommands.add(array[i]);
		}
	}

	/**
	 * This method adds the helps
	 * 
	 * @param array
	 *            the helps
	 */
	public void addArray(HelpCommand[] array) {
		for (int i = 0; i < array.length; i++) {
			this.helpCommands.add(array[i]);
		}
	}

	/**
	 * This method adds the helps
	 * 
	 * @param commands
	 *            the helps
	 */
	public void add(List<LocalCommand> commands) {

		// gli passiamo una lista specifica di localCommand perchè è il primo elemento
		// che comincia ad avere
		// un oggetto dedicato helpCommand
		Iterator<LocalCommand> iterator = commands.iterator();
		while (iterator.hasNext()) {
			LocalCommand localCommand = (LocalCommand) iterator.next();
			LocalCommand.HelpCommand helpCommand = localCommand.getHelpCommand();
			helpCommands.add(helpCommand);
		}
	}

	/**
	 * This method prints the general help
	 */
	public void print() {
		System.out.println(this);
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < this.helpCommands.size(); i++) {
			buffer.append(this.helpCommands.get(i) + "\n");
		}
		return buffer.toString();
	}

	/**
	 * This method sorts the general help
	 */
	public void sort() {
		Collections.sort(this.helpCommands);
	}

}
