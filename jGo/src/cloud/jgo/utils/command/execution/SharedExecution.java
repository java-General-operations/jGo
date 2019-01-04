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
package cloud.jgo.utils.command.execution;

import java.util.ArrayList;
import java.util.List;

import cloud.jgo.utils.command.Sharer;
/**
 * 
 * @author Martire91
 * This class represents a shared execution
 *
 */
public abstract class SharedExecution extends Execution{
	
	protected abstract Object sharedExec(Sharer sharer);
	
	private Sharer currentSharer = null ;
	
	public void setCurrentSharer(Sharer currentSharer) {
		this.currentSharer = currentSharer;
	}
	
	public Sharer getCurrentSharer() {
		return this.currentSharer;
	}

	// non si usa questo metodo, ma il nuovo
	@Override
	public Object exec() {
		return sharedExec(getCurrentSharer());
	}
}
