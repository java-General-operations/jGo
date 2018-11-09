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
package cloud.jgo.net;
import cloud.jgo.utils.command.Parameter;
import cloud.jgo.utils.command.RemoteCommand;
/**
 * 
 * @author Martire91<br>
 * This interface contains the methods for remote control
 */
public interface Control {
	/**
	 * This method performs a remote command
	 * @param commns the remote command
	 * @param params the command parameters
	 * @return true if the command has been sent
	 */
	public abstract boolean enterRemoteCommand(RemoteCommand commns,Parameter...params);
	/**
	 * This method performs a remote command
	 * @param commnd the remote command
	 * @param parameters the command parameters
	 * @return true if the command has been sent
	 */
	public abstract boolean enterRemoteCommand(RemoteCommand commnd,String...parameters); // questo per un comando con parametri ma senza values input
    
	/**
	 * This method performs a remote command
	 * @param commnd the remote command
	 * @param param the command parameter
	 * @param inputValue the parameter input value
	 * @return true if the command has been sent
	 */
	public abstract boolean enter_cmd(RemoteCommand commnd,String param,String inputValue);
}
