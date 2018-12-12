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
package cloud.jgo.net.tcp;

import cloud.jgo.net.ServerType;
import cloud.jgo.net.ServerTypes;

/**
 * 
 * @author Martire91<br>
 *         This class contains all TCP server types
 *
 */
public class TCPServerTypes extends ServerTypes {
	/**
	 * <p style='color:red'>
	 * Still not valid
	 * </p>
	 */
	public final static ServerType TYPE_CHAT_TCP = new ServerType("TCP_CHAT", 2);
	/**
	 * <p style='color:red'>
	 * Still not valid
	 * </p>
	 */
	public final static ServerType TYPE_UNIVOCAL_CHAT_TCP = new ServerType("TCP_UNIVOCAL_CHAT", 7);
	/**
	 * <p style='color:red'>
	 * Still not valid
	 * </p>
	 */
	public final static ServerType TYPE_CHAT_HTTP = new ServerType("HTTP_CHAT", 3);
	public final static ServerType TYPE_HTTP = new ServerType("HTTP", 4);
	/**
	 * <p style='color:red'>
	 * Still not valid
	 * </p>
	 */
	public final static ServerType TYPE_FTP = new ServerType("FTP", 5);
	/**
	 * <p style='color:red'>
	 * Still not valid
	 * </p>
	 */
	public final static ServerType TYPE_SSH = new ServerType("SSH", 6);
	public final static ServerType TYPE_LOGIN = new ServerType("TCP-LOGIN", 8);
}
