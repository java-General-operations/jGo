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
package cloud.jgo.utils;

import org.fusesource.jansi.Ansi.Color;
import org.fusesource.jansi.AnsiConsole;

import cloud.jgo.Home;
import cloud.jgo.j£;
import cloud.jgo.£;

// version 1.0.9
/**
 * 
 * @author Martire91 This class represents a colored string
 *
 */
public class ColorString implements CharSequence {
	private StringBuffer buffer;
	public int length;

	public ColorString(String string, Color color) {
		// TODO Auto-generated constructor stub
		AnsiConsole.systemInstall();
		this.buffer = new StringBuffer();
		this.buffer.append(j£.colors(string, color));
		this.length = this.buffer.length();
	}

	public ColorString() {
		// TODO Auto-generated constructor stub
		AnsiConsole.systemInstall();
		this.buffer = new StringBuffer();
		this.length = this.buffer.length();
	}

	/**
	 * This method appends strings to the colored string
	 * 
	 * @param string
	 *            the string
	 * @param color
	 *            the color
	 * @return the colored string
	 */
	public ColorString append(String string, Color color) {
		this.buffer.append(j£.colors(string, color));
		// aggiorno la lunghezza
		this.length = this.buffer.length();
		return this;
	}

	/**
	 * This method appends characters to the colored string
	 * 
	 * @param charat
	 *            the charat
	 * @param color
	 *            the color
	 * @return the colored string
	 */
	public ColorString append(char charat, Color color) {
		this.buffer.append(j£.colors("" + charat, color));
		this.length = this.buffer.length();
		return this;
	}

	/**
	 * This method appends normal strings to the colored string
	 * 
	 * @param string
	 *            the string
	 * @return the colored string
	 */
	public ColorString append(String string) {
		this.buffer.append(string);
		this.length = this.buffer.length();
		return this;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.buffer.toString();
	}

	/**
	 * This method returns the string value
	 * 
	 * @return string value
	 */
	public String getValue() {
		return this.buffer.toString();
	}

	/**
	 * This method sets the string value
	 * 
	 * @param value
	 *            string value
	 * @param color
	 *            color
	 */
	public void setValue(String value, Color color) {
		// sostituisco l'intero valore del buffer
		this.buffer = new StringBuffer(j£.colors(value, color));
		this.length = this.buffer.length();
	}

	@Override
	public char charAt(int index) {

		return this.buffer.toString().charAt(index);

	}

	@Override
	public int length() {
		// TODO Auto-generated method stub
		return this.buffer.length();
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		// TODO Auto-generated method stub
		return this.buffer.subSequence(start, end);
	}
}
