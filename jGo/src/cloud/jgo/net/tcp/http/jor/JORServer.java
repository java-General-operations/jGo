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
package cloud.jgo.net.tcp.http.jor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

import cloud.jgo.net.tcp.http.HTTPServer;
import cloud.jgo.net.tcp.http.HTTPServerConfiguration;
import cloud.jgo.net.tcp.http.jor.annotations.JOR;

/**
 * @author Martire91
 * @see JOR
 * @see JORHandlerConnection
 * @version 1.0.5 <!--Author : *** Marco Martire *** -->
 *          <h1 style='color: #282828;'>jGo<span style='color:
 *          green;'>.cloud</span>/<a
 *          id='link'href='https://www.jgo.cloud/jor'>JOR</a></h1> <img
 *          id='logo'src='https://www.jgo.cloud/jgo2.png' alt='logo jgo'
 *          width='50px' height='50px'><br>
 *          <strong>Description :</strong><br>
 *          This technology allows you to represent java objects on the web.<br>
 *          Objects are stored in a list, and the list will be passed to the jor
 *          matrix.<br>
 *          Each object therefore has an associated url, obviously
 *          customizable.<br>
 *          How to use it :
 * 
 *          <br>
 *          <ol>
 *          <li>Create a JOR server</li>
 *          <li>Create the objects to represent</li>
 *          <li>Add objects to a list or array.</li>
 *          <li>Create a JOR request handler</li>
 *          <li>Apply the JOR annotation to the class of the object you want to
 *          represent</li>
 *          <li>Implement the methods if necessary:
 *          {@link JORHandlerConnection#html_represents(Object, cloud.jgo.net.tcp.http.HTTPResponse, String)}-{@link JORHandlerConnection#organizesObjectsRootPage(java.util.Map, cloud.jgo.net.tcp.http.HTTPResponse, String)}</li>
 *          </ol>
 *          <br>
 * 			<br>
 *          Here is the url for general documentation :<br>
 *          <a href=
 *          'https://www.jgo.cloud/jor'>https://www.jgo.cloud/jor</a><br>
 * 			<br>
 * 			<br>
 *          <strong>JOR :</strong><br>
 *          <strong>J</strong> - <em>Java</em><br>
 *          <strong>O</strong> - <em>Object</em><br>
 *          <strong>R</strong> - <em>Rappresentation</em><br>
 * 			<br>
 *          This class represents a server with jor technology, is an
 *          implementation of {@link HTTPServer}
 */
public class JORServer extends HTTPServer {
	private static final long serialVersionUID = 1L;
	private ArrayList<Object> matrix = new ArrayList<>();

	/**
	 * This method returns the matrix
	 * 
	 * @return the matrix
	 */
	public ArrayList<Object> getMatrix() {
		return matrix;
	}

	/**
	 * This method adds an object array to matrix
	 * 
	 * @param array
	 *            the object array
	 */
	public void addToMatrix(Object[] array) {
		matrix.add(array);
	}

	/**
	 * This method adds an object list to matrix
	 * 
	 * @param list
	 *            the object list
	 */
	public void addToMatrix(List<? extends Object> list) {
		matrix.add(list);
	}

	/**
	 * This method removes the object array from matrix
	 * 
	 * @param array
	 *            the object array
	 * @return true if the item has been deleted
	 */
	public boolean removeFromMatrix(Object[] array) {
		return matrix.remove(array);
	}

	/**
	 * This method removes the object list from matrix
	 * 
	 * @param list
	 *            the object list
	 * @return true if the item has been deleted
	 */
	public boolean removeFromMatrix(List<Object> list) {
		return matrix.remove(list);
	}

	/**
	 * This method removes all elements from matrix
	 */
	public void removeAllFromMatrix() {
		matrix.clear();
	}

	public static JORServer creates(HTTPServerConfiguration config) {
		JORServer server = new JORServer();
		server.configure(config);
		return server;
	}
}
