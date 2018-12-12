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
package cloud.jgo.net.tcp.http.headers;

public class DefaultHeader extends Header {

	protected Object value;
	private String name;

	@Override
	public String getName() {
		/*
		 * 
		 * JGO Auto-generated method stub Author : £ wasp91 £ Date 17 feb 2018
		 * 
		 */
		return this.name;
	}

	@Override
	public Object getValue() {
		/*
		 * 
		 * JGO Auto-generated method stub Author : £ wasp91 £ Date 17 feb 2018
		 * 
		 */
		return this.value;
	}

	@Override
	public void setValue(Object value) {
		/*
		 * 
		 * JGO Auto-generated method stub Author : £ wasp91 £ Date 17 feb 2018
		 * 
		 */
		this.value = value;
	}

	// qui dovremo cercare di farsi che solo defaultHeader può impostare questa
	// variabile
	/**
	 * This method sets header name
	 * 
	 * @param name
	 *            header name
	 */
	public void setName(String name) {
		this.name = name;
	}

}
