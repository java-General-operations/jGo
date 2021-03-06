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
 * Copyright � 2018 - Marco Martire (www.jgo.cloud)
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

import cloud.jgo.utils.command.execution.Execution;
import cloud.jgo.utils.command.execution.SharedExecution;

/**
 * 
 * @author Martire91<br>
 *         Interface for sharing an object between commands
 */
public interface Shareable {
	/**
	 * This method returns the shared object
	 * 
	 * @param <T>
	 *            the type
	 * @return the shared object
	 */
	public abstract <T> T getSharedObject();

	/**
	 * This method shares an object
	 * 
	 * @param sharedObject
	 *            the object
	 * @param <T>
	 *            the type
	 */
	public abstract <T> void shareObject(T sharedObject);
	
	// version 1.0.9
	/**
	 * This method shares a parameter
	 * @param parameter the parameter
	 * @return the shared parameter
	 */
	public abstract Parameter shareParameter(Parameter parameter);
	/**
	 * This method returns the shared parameters
	 * @return the shared parameters
	 */
	public abstract List<Parameter> getSharedParameters();
	/**
	 * This method fully shares a parameter
	 * @param parameter the parameter
	 * @param execution the shared execution
	 */
	public abstract void shareItEntirely(Parameter parameter,SharedExecution execution);
	// in questo metodo si da per scontato che il parametro abbia gi� una esecuzione
	// condividibile
	/**
	 * This method fully shares a parameter
	 * @param parameter the parameter
	 */
	public abstract void shareItEntirely(Parameter parameter);
	
}
