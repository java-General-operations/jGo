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
package cloud.jgo.net.tcp.http;

import java.io.IOException;

import cloud.jgo.net.tcp.http.headers.Header;
/**
 * 
 * @author Martire91<br>
 * This class contains all the methods that manage the headers
 *
 */
public interface HTTPMessage extends Iterable<Header>{
	/**
	 * This method removes a header by type
	 * @param type the header type
	 */
	public abstract void removeHeader(Header.Type type);
	/**
	 * This method removes a header by index
	 * @param headerIndex header index
	 * @return true returns true if it is deleted
	 * @throws IOException 1 exception
	 */
	public abstract boolean removeHeader(int headerIndex) throws IOException;
	/**
	 * This method removes all headers
	 * @throws IOException 1 exception
	 */
	public abstract void removeAllHeaders() throws IOException;
	/**
	 * This method prints all headers
	 */
	public abstract void printHeaders();
	/**
	 * This method checks if this type of header is present
	 * @param type the header type
	 * @return true if is present
	 */
	public abstract boolean isPresent(Header.Type type);
	/**
	 * This method checks if there are no headers
	 * @return true if there are no headers
	 */
	public abstract boolean isEmptyHeaders();
	/**
	 * This method retrieves a header using the index
	 * @param index header index
	 * @return the header
	 */
	public abstract Header header(int index);
	/**
	 * This method retrieves a header using the type
	 * @param type header type
	 * @return the header
	 */
	public abstract Header header(Header.Type type);	
}
