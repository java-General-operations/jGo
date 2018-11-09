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

public class Performer { //Esecutore
	public static void executeTHeParameters(Parameter...params){
		Parameter[]params_ = params;
		for (int i = 0; i < params_.length; i++) {
			params_[i].execute();
			
		}
	}
	public static void executeTHeCommands(Command...commands){
		for (int i = 0; i < commands.length; i++) {
			commands[i].execute();
		}
	}
}
