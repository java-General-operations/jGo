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
import cloud.jgo.utils.command.execution.SharedExecution;
import cloud.jgo.utils.command.execution.Executable.When;

/**
 * 
 * @author Martire91<br>
 *         This class represents a parameter
 */
public abstract class Parameter
		implements cloud.jgo.utils.command.execution.Executable, Comparable<Parameter>, InputSettable, Sharer {
	private static final long serialVersionUID = 1L;
	private Execution execution = null;
	private Command parent = null;
	public static final String SEPARATOR = "-";
	private When when=When.ALWAYS;
	private String param;
	private String help;
	private String onlyParam;
	private String inputValue = null;
	private boolean inputValueExploitable = false;
	//version 1.0.9
	protected boolean shared = false ;
	public boolean shared() {return this.shared;}
	public String getParam() {
		return param;
	}
	public boolean useThread = false;
	// questo metodo serve a condividere un oggetto

	/**
	 * This method returns the parameter input value
	 * 
	 * @return the parameter input value
	 */
	@Override
	public String getInputValue() {
		return inputValue;
	}

	@Override
	public boolean equals(Object obj) {
		Parameter p = (Parameter) obj;
		if (this.getOnlyParam().equals(p.getOnlyParam())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method sets the parameter input value
	 * 
	 * @param inputValue
	 *            the parameter input value
	 */
	@Override
	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}

	/**
	 * this method sets the value from exploitable input
	 * 
	 * @param exploitable
	 *            the flag
	 */
	@Override
	public void setInputValueExploitable(boolean exploitable) {
		this.inputValueExploitable = exploitable;
		// qui ricarico l'help del comando
		((LocalCommand) getParent()).getHelpCommand().reload((LocalCommand) getParent());
	}

	/**
	 * This method checks if the parameter has the input value exploitable
	 * 
	 * @return the flag
	 */
	@Override
	public boolean hasInputValueExploitable() {
		return this.inputValueExploitable;
	}

	/**
	 * This method returns the parameter help
	 * 
	 * @return the parameter help
	 */

	public String getParameterHelp() {
		return this.help;
	}

	public Parameter(String param, String help) {
		this.onlyParam = param;
		this.param = SEPARATOR + this.onlyParam;
		this.help = help;
	}

	/**
	 * This method returns the parameter only
	 * 
	 * @return the parameter only
	 */

	public String getOnlyParam() {
		return onlyParam;
	}
	
	// 1.0.9
	/**
	 * This method sets the help
	 * @param help the help
	 */
	public void setHelp(String help) {
		this.help = help;
	}

	public Parameter(String param, String help, Execution execution) {
		this.onlyParam = param;
		this.param = SEPARATOR + this.onlyParam;
		this.help = help;
		this.execution = execution;
	}

	@Override
	public String toString() {
		return this.param;
	}
	public void setExecution(Execution execution) {
		this.execution = execution;
	}
	@Override
	public Object execute() {
		Object execute = null ;
		if (hasAnExecution()) {
			if (useThread) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						if (getExecution()instanceof SharedExecution) {
							((SharedExecution)getExecution()).setCurrentSharer(Parameter.this);
						}
						switch (when) {
						case ALWAYS:getExecution().exec();
						break; // poi qui aggiornare, quando ci saranno nuovi "quando"
						}
					}
				}).start();
			}
			else {
				if (getExecution()instanceof SharedExecution) {
					((SharedExecution)getExecution()).setCurrentSharer(this);
				}
			execute = getExecution().exec();
			}
		} 
		return execute ;
	}

	/**
	 * This method returns the parameter execution
	 * 
	 * @return the parameter execution
	 */

	public Execution getExecution() {
		return this.execution;
	}

	/**
	 * This method sets the parameter parent
	 * 
	 * @param parent
	 *            the parameter parent
	 */
	public void setParent(Command parent) {
		this.parent = parent;
		// qui ricarico l'help del comando
		((LocalCommand) this.parent).getHelpCommand().reload((LocalCommand) this.parent);
	}
	
	/**
	 * This method returns the parameter parent
	 * 
	 * @return the parameter parent
	 */

	public Command getParent() {
		return this.parent;
	}

	@Override

	public boolean hasAnExecution() {
		if (this.execution != null) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public Type getSharerType() {
		// TODO Auto-generated method stub
		return Type.PARAMETER ;
	}
	@Override
	public When getHypothesis() {
		// TODO Auto-generated method stub
		return this.when ;
	}

	@Override
	public void validExecution(When w) {
		// TODO Auto-generated method stub
		this.when = w ;
	}
}