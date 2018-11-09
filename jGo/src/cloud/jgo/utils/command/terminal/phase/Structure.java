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
import cloud.jgo.utils.command.Command;
/**
 * @author Martire91<br>
 * This class represents the phase structuring
 */
public interface Structure {
	/**
	 * This method moves on to the next phase
	 * @return the next phase
	 */
	public abstract Phase nextPhase();
	/**
	 * This method goes to the previous phase
	 * @return the previous phase
	 */
	public abstract Phase previousPhase();
	/**
	 * This method changes the phase
	 * @param phaseValue the new phase value
	 * @return the new phase
	 */
	public abstract Phase changePhase(int phaseValue);
	/**
	 * This method changes the phase
	 * @param phaseName the new phase name
	 * @return the new phase
	 */
	public abstract Phase changePhase(String phaseName);
	/**
	 * This method changes the phase
	 * @param phase the new phase
	 * @return the new phase
	 */
	public abstract Phase changePhase(Phase phase);
	/**
	 * This method returns to the initial phase
	 */
	public abstract void reset(); // fa ripartire dalla fase iniziale
	/**
	 * This method counts the phases
	 * @return the phases number
	 */
	public abstract int countPhases();
	/**
	 * This method returns the phase
	 * @param phaseName the phase name
	 * @return the phase
	 */
	public abstract Phase phase(final String phaseName);
	/**
	 * This method returns the phase
	 * @param value the phase value
	 * @return the phase
	 */
	public abstract Phase phase(final int value);
	/**
	 * This method returns the reset command
	 * @return the reset command
	 */
	public abstract Command resetCommand();
	/**
	 * This method sets the reset command
	 * @param command the reset command
	 */
	public abstract void setResetCommand(String command);
	/**
	 * This method removes a phase
	 * @param value the phase value
	 * @return true if the phase has been eliminated
	 */
	public abstract boolean removePhase(final int value);
}
