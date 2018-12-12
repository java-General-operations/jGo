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
package cloud.jgo.jjdom.css.concrete;

import java.util.Iterator;
import java.util.Map;

import cloud.jgo.jjdom.css.CSSPropertyType;
import cloud.jgo.jjdom.css.CSSRule;

/**
 * 
 * @author Martire91<br>
 * @see CSSRule
 */
public class CSSDefaultRule extends CSSRule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String selection = null;
	private String comment = null;

	public CSSDefaultRule(String selection, String comment) {
		// TODO Auto-generated constructor stub
		this.selection = selection;
		this.comment = comment;
	}

	public CSSDefaultRule(String selection) {
		// TODO Auto-generated constructor stub
		this.selection = selection;
	}

	@Override
	public String getSelection() {
		// TODO Auto-generated method stub
		return this.selection;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer buffer = new StringBuffer();
		if (this.comment != null) {
			buffer.append("/*" + "\n\n");
			buffer.append(this.comment + "\n\n");
			buffer.append("*/" + "\n");
		}
		buffer.append(selection + "{" + "\n");
		Iterator<Map.Entry<CSSPropertyType, String>> iterator = entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<cloud.jgo.jjdom.css.CSSPropertyType, java.lang.String> entry = (Map.Entry<cloud.jgo.jjdom.css.CSSPropertyType, java.lang.String>) iterator
					.next();
			String cssType = entry.getKey().name().toLowerCase();
			String cssValue = entry.getValue();
			buffer.append(cssType + ": " + cssValue + ";\n");
		}
		buffer.append("}");
		return buffer.toString();
	}

	@Override
	public String getComment() {
		// TODO Auto-generated method stub
		return this.comment;
	}

}
