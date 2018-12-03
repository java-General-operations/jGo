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

import java.util.List;

import cloud.jgo.utils.command.execution.Executable;
import cloud.jgo.utils.command.execution.Execution;
import cloud.jgo.utils.command.terminal.phase.Phase;
/**
 * @author Martire91<br>
 * This class represents a terminal command
 */
public interface Command extends Executable{
	/**
	 * This method returns the command
	 * @return the command
	 */
	public abstract String getCommand();
	/**
	 * This method counts the number of command parameters
	 * @return the parameters number
	 */
    public abstract int countParameters();
    /**
     * This method returns the command execution
     * @return the command execution
     */
	public abstract Execution getExecution();
	/**
	 * This method returns the command help
	 * @return the command help
	 */
	public abstract String getHelp(); // spiega il comando
	
	/**
	 * This method returns the command effect
	 * @return the command effect
	 */
	public abstract String getEffect(); // comunica l'effetto del comando
	
	/**
	 * This method adds a parameter into the command
	 * @param param the parameter
	 * @param help the parameter help
	 * @return the parameter
	 */
	public abstract Parameter addParam(String param,String help);
	/**
	 * This method returns the parameter
	 * @param param the parameter text
	 * @return the parameter
	 */
	public abstract Parameter param(String param);
	/**
	 * This method returns the parameter to the position index
	 * @param index the parameter index
	 * @return the parameter
	 */
	public abstract Parameter param(int index);
	/**
	 * Check if it's a parameter
	 * @param param the parameter text
	 * @return the flag
	 */
	public abstract boolean isParameter(String param);
	/**
	 * This method removes all the parameters
	 */
	public abstract void removeAllParameters();
	/**
	 * This method removes a parameter
	 * @param param a parameter
	 * @return true if the parameter is removed
	 */
	public abstract boolean removeParam(String param);
	/**
	 * This method removes a parameter
	 * @param index the parameter index
	 * @return true if the parameter is removed
	 */
	public abstract boolean removeParam(int index);
	/**
	 * This method replaces a parameter
	 * @param param the old parameter
	 * @param newValue the new parameter
	 * @return true if the parameter is replaced
	 */
	public abstract boolean replace(String param,Parameter newValue);
	/**
	 * This method executes a parameter
	 * @param param the parameter
	 * @return the object returned from execution
	 */
	public abstract Object executeParam(String param);
	/**
	 * This method executes a parameter
	 * @param param the parameter
	 * @param inputValue parameter input value
	 * @return the object returned from execution
	 */
	public abstract Object executeParam(String param,String inputValue);
	/**
	 * This method sorts the parameters list
	 * @return the ordered list
	 */
	public abstract List<Parameter> sortParameters(); // se ci sono params li ordina
	/**
	 * This method checks if there are parameters in the command
	 * @return the flag
	 */
	public abstract boolean hasParameters();
	/**
	 * This method returns the command parameters
	 * @return the command parameters
	 */
	public abstract Parameter[]params();
	/**
	 * This method returns the command phase
	 * @return the command phase
	 */
	public abstract Phase getBelongsTo();
	/**
	 * This method checks if exist a command phase
	 * @return the flag
	 */
	public abstract boolean hasAPhase();
	// version 1.0.7
	public abstract void merge(Parameter parameter);
}
