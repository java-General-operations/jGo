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

import cloud.jgo.jjdom.css.CSSRule;
import cloud.jgo.jjdom.css.CSSStyle;
import cloud.jgo.jjdom.dom.nodes.HTMLDocument;
// tramite tag style
public class CSSDefaultStyle extends CSSStyle{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HTMLDocument document=null;

	public CSSDefaultStyle(HTMLDocument document) {
		// TODO Auto-generated constructor stub
		this.document = document ;
	}
	public CSSDefaultStyle(){}
	public void setDocument(HTMLDocument document) {
		this.document = document;
	}
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n");
		for (int i = 0; i < this.size(); i++) {
			buffer.append(get(i)+"\n");
		}
		return buffer.toString();
	}

	@Override
	public CSSStyle addRules(CSSRule... rules) {
		for (int i = 0; i < rules.length; i++) {
			add(rules[i]);
		}
		return this ;
	}

	@Override
	public int countRules() {
		// TODO Auto-generated method stub
		return this.size() ;
	}

	@Override
	public HTMLDocument getDocument() {
		// TODO Auto-generated method stub
		return this.document ;
	}
	
}
