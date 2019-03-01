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

import cloud.jgo.utils.command.Sharer;
import cloud.jgo.utils.command.Sharer.Type;
import cloud.jgo.utils.command.execution.SharedExecution;

/**
 * @author Martire91 - <https://github.com/wasp91>
 * @version 1.0.9
 * This execution boasts a possible particularity,
 * when the phase is executed, it can become the current one
 */
public abstract class PhaseExecution extends SharedExecution{

	private static final long serialVersionUID = 1L;
	private LocalPhaseTerminal phaseTerminal;
	private boolean asCurrentAfterExecution;
	
	public PhaseExecution(LocalPhaseTerminal t, boolean setAsCurrentAfterExecution) {
		// TODO Auto-generated constructor stub
		this.phaseTerminal = t ;
		this.asCurrentAfterExecution  = setAsCurrentAfterExecution ;
	}
	
	public PhaseExecution(LocalPhaseTerminal t) {
		// TODO Auto-generated constructor stub
		this.phaseTerminal = t ;
		this.asCurrentAfterExecution = false ;
	}
	
	/**
	 * @param asCurrentAfterExecution the asCurrentAfterExecution to set
	 */
	public void setAsCurrentAfterExecution(boolean asCurrentAfterExecution) {
		this.asCurrentAfterExecution = asCurrentAfterExecution;
	}
	
	/**
	 * @return the asCurrentAfterExecution
	 */
	public boolean isAsCurrentAfterExecution() {
		return asCurrentAfterExecution;
	}
	
	/**
	 * @return the phaseTerminal
	 */
	public LocalPhaseTerminal getPhaseTerminal() {
		return phaseTerminal;
	}
	
	/* (non-Javadoc)
	 * @see cloud.jgo.utils.command.execution.SharedExecution#sharedExec(cloud.jgo.utils.command.Sharer)
	 */
	@Override
	protected Object sharedExec(Sharer sharer) {
		if (sharer!=null) {
			if (sharer.getSharerType().equals(Type.PHASE)) {
				Object result = sharedExec(((Phase)sharer));
				if (isAsCurrentAfterExecution()) {
					this.phaseTerminal.currentPhase = ((Phase)sharer);	
				}
			}
		}
		return null ;
	}
	
	/**
	 * This method must be implemented to fish the phases.
	 * @param phase the phase
	 * @return the result of phase execution
	 */
	protected abstract Object sharedExec(Phase phase);
}
