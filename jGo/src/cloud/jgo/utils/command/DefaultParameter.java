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

import cloud.jgo.utils.command.execution.Execution;

public class DefaultParameter extends Parameter {

	public DefaultParameter(String param, String help) {
		super(param, help);
		/*
		 * 
		 * JGO Auto-generated constructor stub Author : £ wasp91 £ Date 06 gen 2018
		 * 
		 */
	}

	public DefaultParameter(String param, String help, Execution execution) {
		super(param, help, execution);
		/*
		 * 
		 * JGO Auto-generated constructor stub Author : £ wasp91 £ Date 06 gen 2018
		 * 
		 */
	}

	@Override
	public int compareTo(Parameter o) {

		return this.getOnlyParam().compareTo(o.getOnlyParam());

	}

}
