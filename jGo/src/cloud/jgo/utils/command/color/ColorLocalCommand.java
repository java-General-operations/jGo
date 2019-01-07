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
package cloud.jgo.utils.command.color;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.fusesource.jansi.Ansi.Color;
import cloud.jgo.j£;
import cloud.jgo.utils.ColorString;
import cloud.jgo.utils.command.DefaultParameter;
import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.Parameter;
import cloud.jgo.utils.command.annotations.Command;
import cloud.jgo.utils.command.annotations.InvalidClassException;
import cloud.jgo.utils.command.LocalCommand.HelpCommand;
import cloud.jgo.utils.command.execution.Execution;
import cloud.jgo.utils.command.terminal.TerminalColors;

public class ColorLocalCommand extends LocalCommand {
	// version 1.0.9 :
	/**
	 * This method prints a report of all the fields of the shared object, then
	 * tells us which variables have been set and which are not, all this happens
	 * through reflection.
	 * 
	 * @param sharedObject
	 *            the shared object
	 * @param fieldNameColor
	 *            field name color
	 * @param fieldValueColor
	 *            field value color
	 * @return the shared object configuration
	 * @throws IllegalArgumentException
	 *             1 exception
	 * @throws IllegalAccessException
	 *             2 exception
	 */
	public static String toString(Object sharedObject, Color fieldNameColor, Color fieldValueColor)
			throws IllegalArgumentException, IllegalAccessException {
		if (sharedObject.getClass().isAnnotationPresent(Command.class)) {
			ColorString string = new ColorString();
			string.append("-----------------------------------------------------------------------------------\n");
			string.append(" " + sharedObject.getClass().getSimpleName()).append(" ~ Configuration\n",Color.BLUE);
			string.append("-----------------------------------------------------------------------------------\n");
			Class<?> clazz = sharedObject.getClass();
			Field[] fields = clazz.getDeclaredFields();
			int count = 0;
			for (Field field : fields) {
				field.setAccessible(true);
				if (field.isAnnotationPresent(cloud.jgo.utils.command.annotations.Parameter.class)) {
					String fieldName = field.getName();
					Object fieldValue = field.get(sharedObject);
					if (count == 3) {
						// si va a capo
						count = 0;
						string.append("\n\n");
					}
					string.append("* " + fieldName, fieldNameColor).append("=", Color.WHITE)
								.append(fieldValue + "", fieldValueColor).append("  ");
				}
				count++;
			}
			return string.toString() + "\n";
		}
		else {
			try {
				throw new InvalidClassException();
			} catch (InvalidClassException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null ;
		}
	}

	private ColorHelpCommand helpCommand = new ColorHelpCommand();

	public ColorLocalCommand(String command, String help) {
		super(command, help);
		// ricarico l'help colorato
		this.helpCommand.reload(this);
	}

	public ColorLocalCommand(String command, String help, Execution execution) {
		// TODO Auto-generated constructor stub
		super(command, help, execution);
		// ricarico l'help colorato
		this.helpCommand.reload(this);
	}

	@Override
	public ColorHelpCommand getHelpCommand() {
		// TODO Auto-generated method stub
		return this.helpCommand;
	}

	public static class ColorHelpCommand extends HelpCommand {
		public void reload(LocalCommand command) {
			this.command = command;
			this.buffer = new StringBuffer();
			buffer.append("===================================================================================\n");
			if (this.command.getBelongsTo() != null) {
				buffer.append("HELP Of " + "\"" + j£.colors(this.command.getCommand(), TerminalColors.COMMAND_COLOR)
						+ "\" - Phase :"
						+ j£.colors(this.command.getBelongsTo().phaseName(), TerminalColors.PHASE_COLOR) + "\n");
			} else {
				buffer.append("HELP Of " + "\"" + j£.colors(this.command.getCommand(), TerminalColors.COMMAND_COLOR)
						+ "\" - Phase :" + j£.colors("absent", Color.DEFAULT) + "\n");
			}
			buffer.append("===================================================================================\n");
			// qui devo prendere tutti i parameters
			Collection<Parameter> collection = command.getStructure().values();
			List<Parameter> orderParameters = command.sortParameters();
			// qui ci sarà la descrizione del comando root
			buffer.append(this.command.getHelp().toUpperCase() + "   / has input value ="
					+ j£.colors(this.command.hasInputValueExploitable() + "", Color.GREEN) + "\n\n");
			if (orderParameters != null) {
				buffer.append("* Parameters :" + orderParameters + " :\n\n");
				if (this.command.hasParameters()) {
					// ci sono parametri
					// quindi qui devo prendere i params
					Iterator<Parameter> iterator = orderParameters.iterator();
					while (iterator.hasNext()) {
						Parameter param = iterator.next();
						buffer.append(j£.colors(param.getParam(), TerminalColors.PARAMETER_COLOR) + "="
								+ param.getParameterHelp() + "  / has input value ="
								+ j£.colors(param.hasInputValueExploitable() + "", Color.GREEN) + "\n");
					}
				}
			} else {
				buffer.append("* Parameters :" + collection + " :\n\n");
				if (this.command.hasParameters()) {
					// ci sono parametri
					// quindi qui devo prendere i params
					Iterator<Entry<String, Parameter>> iterator = command.iterator();
					while (iterator.hasNext()) {
						Map.Entry<java.lang.String, cloud.jgo.utils.command.Parameter> entry = (Map.Entry<java.lang.String, cloud.jgo.utils.command.Parameter>) iterator
								.next();
						Parameter param = entry.getValue();
						buffer.append(j£.colors(param.getParam(), TerminalColors.PARAMETER_COLOR) + "="
								+ param.getParameterHelp() + "  / has input value ="
								+ j£.colors(param.hasInputValueExploitable() + "", Color.GREEN) + "\n");
					}
				}
			}
		}
	}

}
