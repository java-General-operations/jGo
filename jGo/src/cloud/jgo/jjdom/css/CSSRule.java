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
package cloud.jgo.jjdom.css;

import java.util.Hashtable;

/**
 * 
 * @author Martire91<br>
 *         This class represents a css rule
 */
public abstract class CSSRule extends Hashtable<CSSPropertyType, String> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method returns the selection
	 * 
	 * @return the selection
	 */
	public abstract String getSelection();

	/**
	 * This method returns the possible comment
	 * 
	 * @return the possible comment
	 */
	public abstract String getComment();
}
