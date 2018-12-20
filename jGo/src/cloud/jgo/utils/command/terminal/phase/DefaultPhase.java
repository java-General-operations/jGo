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
import java.util.ArrayList;
import java.util.List;
import cloud.jgo.utils.command.Command;
import cloud.jgo.utils.command.LocalCommand;
/**
 * 
 * @author Martire91<br>
 *         This class represents a concrete phase
 */
public class DefaultPhase implements Phase {

	/**
	 * this is a concrete product
	 */
	private static final long serialVersionUID = 12L;
	private Rule accessibilityRule = null;
	private Rule satisfiabilityRule = null;
	private String phaseName = null;
	private int value;
	private boolean accessible = true;
	private boolean satisfied = true;
	private String description = null;

	/**
	 * This method returns the satisfiability Rule
	 * 
	 * @return the satisfiability Rule
	 */
	public Rule getSatisfiabilityRule() {
		return this.satisfiabilityRule;
	}

	public DefaultPhase(String phaseType, int value) {
		this.phaseName = phaseType;
		this.value = value;
	}

	private List<Command> commands = new ArrayList<>();

	/**
	 * This method adds a command into the phase
	 * 
	 * @param command
	 *            the command
	 */
	public void addCommand(Command command) {
		((LocalCommand) command).setBelongsTo(this);
		((LocalCommand) command).getHelpCommand().reload(((LocalCommand) command)); // aggiorno l'help del comando
		this.commands.add(command);
	}
	/**
	 * This method adds the commands into the phase
	 * 
	 * @param commands
	 *            the commands
	 */
	public void addCommands(Command... commands) {
		// TODO Auto-generated method stub
		for (int i = 0; i < commands.length; i++) {
			addCommand(commands[i]);
		}
	}

	@Override
	public boolean equals(Object obj) {
		DefaultPhase phase = (DefaultPhase) obj;
		if (value == phase.value && phaseName.equalsIgnoreCase(phase.phaseName())) {

			// qui possiamo sempificarci la vita perchè in un terminale non possono esistere
			// due fasi con nome uguale e valore, per via della struttura
			return true;

		} else {
			return false;
		}
	}

	@Override
	public String phaseName() {
		// TODO Auto-generated method stub
		return this.phaseName;
	}

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return this.value;
	}

	/**
	 * This method returns the accessibility Rule
	 * 
	 * @return the accessibility Rule
	 */
	public Rule getAccessibilityRule() {
		return this.accessibilityRule;
	}

	@Override
	public int getCountCommands() {
		// TODO Auto-generated method stub
		return this.commands.size();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.phaseName + "/" + this.value;
	}

	@Override
	public boolean isAccessible() {
		if (this.accessibilityRule != null) {
			return this.accessible = this.accessibilityRule.verification(); // qui chiamo il metodo
		} else {
			return this.accessible;
		}
	}

	@Override
	public void accessibleThrough(Rule rule) {
		this.accessibilityRule = rule;
	}

	@Override
	public List<Command> getCommands() {
		// TODO Auto-generated method stub
		return this.commands;
	}

	@Override
	public boolean isSatisfied() {
		if (this.satisfiabilityRule != null) {
			return this.satisfied = this.satisfiabilityRule.verification(); // qui chiamo il metodo
		} else {
			return this.satisfied;
		}
	}

	@Override
	public void satisfiableThrough(Rule rule) {
		// TODO Auto-generated method stub
		this.satisfiabilityRule = rule;
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return this.description;
	}

	/**
	 * This method sets the phase description
	 * 
	 * @param description
	 *            the description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
