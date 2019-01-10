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
/**
 * 
 * @author Martire91<br>
 * This class is the phases factory
 */
public class PhasesFactory {
	/**
	 * This method is a factory method.
	 * @param phaseName the phase name
	 * @param value the phase value
	 * @return the phase
	 */
	public static Phase create(final String phaseName,final int value){
		// ora l'unico tipo che restituisco è un localPhase
		
		return new LocalPhaseFactory().newInstance(phaseName, value);
	}
}
