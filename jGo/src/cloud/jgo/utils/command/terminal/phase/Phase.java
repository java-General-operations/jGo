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
package cloud.jgo.utils.command.terminal.phase;

import java.io.Serializable;
import java.util.List;

import cloud.jgo.utils.command.Command;

// this is a product
/**
 * 
 * @author Martire91<br>
 *         This interface represents a phase
 */
public interface Phase extends Serializable {
	/**
	 * This method returns the phase name
	 * 
	 * @return the phase name
	 */
	public abstract String phaseName();

	/**
	 * This method returns the phase value
	 * 
	 * @return the phase value
	 */
	public abstract int getValue();

	/**
	 * This method counts the phase commands
	 * 
	 * @return the phase commands number
	 */
	public abstract int getCountCommands();

	/**
	 * This method returns true if the phase is accessible
	 * 
	 * @return the flag
	 */
	public abstract boolean isAccessible();

	/**
	 * This method sets the rule for the accessibility
	 * 
	 * @param rule
	 *            the rule
	 */
	public abstract void accessibleThrough(Rule rule); // con questo metodo impostiamo un codice che se rispetat

	/**
	 * This method returns the phase commands list
	 * 
	 * @return the phase commands list
	 */
	public abstract List<Command> getCommands();

	/**
	 * This method returns true if the phase is satisfied
	 * 
	 * @return the flag
	 */
	public abstract boolean isSatisfied();

	/**
	 * This method sets the rule for the satisfiability
	 * 
	 * @param rule
	 *            the rule
	 */
	public abstract void satisfiableThrough(Rule rule);

	/**
	 * This method returns the phase description
	 * 
	 * @return the phase description
	 */
	public abstract String description();

	/**
	 * This method returns the phase welcome text
	 * 
	 * @return the phase welcome text
	 */
	public abstract String getWelcome();
	//version 2.0.0
	public abstract boolean welcomeHasBeenPrinted();
	/**
	 * This method sets the phase welcome text
	 * 
	 * @param welcomeGreeting
	 *            the phase welcome text
	 */
	public abstract void setWelcome(String welcomeGreeting);
}
