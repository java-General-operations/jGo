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
package cloud.jgo.utils.command.terminal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import cloud.jgo.Home;
import cloud.jgo.£;
import cloud.jgo.utils.command.LocalCommand;
import cloud.jgo.utils.command.terminal.phase.LocalPhaseTerminal;

/**
 * 
 * @author Martire91<br>
 *         This class represents the terminal
 */
public abstract class Terminal extends Home {
	public enum Type {
		WINDOWS, LINUX
	}

	/**
	 * This method returns the text with which the command is requested
	 * 
	 * @return the text
	 */
	public abstract String getCommandRequest();

	/**
	 * This method must be implemented to redefine the start of the terminal
	 */
	protected abstract void implOpen(); // this method is performed within the open method cycle

	/**
	 * This method executes the command
	 * 
	 * @param command
	 *            the command
	 * @return the returned object
	 * @throws IOException
	 *             1 exception
	 * @throws InterruptedException
	 *             2 exception
	 */
	public abstract Object command(String command) throws IOException, InterruptedException;

	protected Runtime runtime = null;
	protected Process process = null;
	private OutputStream output;
	private InputStream input;
	protected Object exitCommand = null;
	protected boolean stop = true;

	/**
	 * This method returns the exit command
	 * 
	 * @return the exit command
	 */
	public abstract Object getExitCommand();

	/**
	 * This method sets the exit command
	 * 
	 * @param command
	 *            the exit command
	 */
	public abstract void setExitCommand(String command);

	/**
	 * This method starts the terminal
	 */
	final public void open() {
		if (this instanceof WinTerminal || this instanceof LinuxTerminal) {

			if (WinTerminal.class.isInstance(this)) {
				this.runtime = Runtime.getRuntime();
				try {
					this.process = this.runtime.exec("C:\\Windows\\System32\\cmd.exe");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				// is LINUX
				this.runtime = Runtime.getRuntime();
				try {
					this.process = this.runtime.exec("/bin/bash");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// qui avvio i threads input / error
			new Thread(new SyncPipe(this.process.getErrorStream(), System.err)).start();
			new Thread(new SyncPipe(this.process.getInputStream(), System.out)).start();
		} else if (this instanceof LocalPhaseTerminal) {
			((LocalPhaseTerminal) this).orients(); // orientiamo il terminale prima che parte il ciclo, cosi sa da quale
													// oggetto fase deve partire
		}
		// qui avvio - £_()
		LocalCommand.setInputHelpExploitable(true);
		this.stop = false;
		while (this.stop != true) {
			if (getCommandRequest() != null) {
				£._O(getCommandRequest());
			}
			implOpen();
		}
		System.out.println("Terminal is close #");
	}

	/**
	 * This method closes the terminal
	 */
	public void close() {
		this.stop = true;
	}

}
