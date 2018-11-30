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

import java.io.Serializable;

import cloud.jgo.jjdom.dom.nodes.HTMLElements;
/**
 * This class represents a css selection
 * @author Martire91<br>
 */
public interface CSSSelection extends Serializable{
		/**
		 * This method returns the number of selected items
		 * @return the number of selected items
		 */
		public abstract int getCountSelectedItems();
		public abstract String getSelectionCriterion();
		public abstract HTMLElements getSelectedItems();
		public enum Type{ID,CLASS,TAG_NAME}
}
