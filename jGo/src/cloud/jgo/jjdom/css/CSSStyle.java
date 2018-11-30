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

import java.util.ArrayList;

import cloud.jgo.jjdom.dom.nodes.HTMLDocument;
/**
 * @author Martire91<br>
 * This class represents the css style
 */
public abstract class CSSStyle extends ArrayList<CSSRule>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public abstract CSSStyle addRules(CSSRule...rules);
	public abstract int countRules();
	public abstract HTMLDocument getDocument(); // documento a cui lo stile si riferisce
}
