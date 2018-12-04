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
package cloud.jgo.jjdom.dom.nodes.html;
import java.util.List;
import java.util.Set;

import cloud.jgo.jjdom.Home;
import cloud.jgo.jjdom.css.CSSRule;
import cloud.jgo.jjdom.css.CSSStyle;
import cloud.jgo.jjdom.dom.Manipulable;
import cloud.jgo.jjdom.dom.nodes.Document;
import cloud.jgo.jjdom.dom.nodes.Element;
import cloud.jgo.jjdom.dom.nodes.Elements;
import cloud.jgo.jjdom.dom.nodes.html.HTMLElement.HTMLElementType;
/**
 * 
 * @author Martire91<br>
 * This interface represents an html document
 */
public interface HTMLDocument extends Document,Home{
	/**
	 * This method creates a button
	 * @param btnText the button text
	 * @param btnId the button id
	 * @return the button
	 */
	public abstract HTMLElement createButton(String btnText,String btnId);
	/**
	 * This method creates a link
	 * @param href the link url
	 * @param text the link text
	 * @return the link element
	 */
	public abstract HTMLElement createLink(String href,String text);
	/**
	 * This method creates a default element
	 * @param type the element type
	 * @return the element
	 */
	public abstract HTMLElement createElement(HTMLElement.HTMLElementType type);
	/**
	 * This method creates a default element
	 * @param type the element type
	 * @param textElement the element text
	 * @return the element
	 */
	public abstract HTMLElement createElement(HTMLElement.HTMLElementType type, String textElement);
	/**
	 * This method creates an image link
	 * @param srcImage the image url
	 * @param href the link url
	 * @return the image link
	 */
	public abstract HTMLElement createImageLink(String srcImage,String href);
	/**
	 * This method creates a div element
	 * @param divId the div id
	 * @return the div element
	 */
	public abstract HTMLElement createDiv(String divId);
	/**
	 * This method creates a div element
	 * @param divClass the div class
	 * @param divId the div id
	 * @return the div element
	 */
	public abstract HTMLElement createDiv(String divClass,String divId);
	/**
	 * This method changes the background color
	 * @param color the background color
	 * @return the document on which the method was invoked
	 */
	public abstract HTMLDocument bgColor(String color);
	/**
	 * This method changes the background color
	 * @param color the foreground color
	 * @return the document on which the method was invoked
	 */
	public abstract HTMLDocument fgColor(String color);
	/**
	 * This method returns the javascript source
	 * @return the javascript source
	 */
	public abstract StringBuffer jsSource();
	/**
	 * This method sets the javascript source
	 * @param buffer the javascript source
	 * @return the document on which the method was invoked
	 */
	public abstract HTMLDocument setJsSource(StringBuffer buffer);
	/**
	 * This method returns the script tag containing the javascript source
	 * @return the script element
	 */
	public abstract HTMLElement getJsSourceTag();
	/**
	 * This method sets the script element for the javascript source
	 * @param scriptTag the script element
	 * @return the document on which the method was invoked
	 */
	public abstract HTMLDocument setJsSourceTag(HTMLElement scriptTag);
	/**
	 * This method basically indicates if the jquery: ready method has been called
	 * @return true if the ready method has been invoked
	 */
	public abstract boolean isReady();
	/**
	 * This method returns the jquery path
	 * @return the jquery path
	 */
	public abstract String jqueryPath();
	/**
	 * This method sets the jquery path
	 * @param jqueryPath the jquery path
	 * @return the document on which the method was invoked
	 */
	public abstract HTMLDocument setJqueryPath(String jqueryPath);
	/**
	 * This method creates a menu element
	 * @param idMenu the menu id
	 * @param items the menu elements
	 * @return the menu
	 */
	public abstract HTMLElement createMenu(String idMenu,String...items);
	/**
	 * This method creates a form element
	 * @param action the "action" attribute
	 * @param method the "method" attribute
	 * @return the form
	 */
	public abstract HTMLElement createForm(String action,String method);
	/**
	 * This method creates the input element
	 * @param inputType the input type element
	 * @param name the input name
	 * @param value the input value
	 * @return the input element
	 */
	public abstract HTMLElement createInput(String inputType,String name,String value);
	/**
	 * This method gets the elements by type
	 * @param type the elements type
	 * @return the found elements
	 */
	public abstract Elements getElementsByType(HTMLElement.HTMLElementType type); // restituisce il primo elemento che si trova di quel tipo
	/**
	 * This method returns the meta tag
	 * @return the meta tag element
	 */
	public abstract HTMLElement getMetaTag(); // restituisce il tag del meta
	/**
	 * This method returns the body element
	 * @return the body element
	 */
	public abstract HTMLElement getBody();
	/**
	 * This method returns the style tag
	 * @return the style tag
	 */
	public abstract HTMLElement getStyleTag(); // entra in gioco quando chiamiamo il metodo setminimalTags() però non viene aggiunto, oppure quando l'utente inserisce il tag style viene collegato con la variabile appunto
	/**
	 * This method returns the css style
	 * @return the css style
	 */
	public abstract CSSStyle getStyleSheet();
	/**
	 * This method creates a css rule
	 * @param selection the selection
	 * @param comment the possible comment
	 * @return the css rule
	 */
	public abstract CSSRule createRule(String selection,String comment); // crea una regola 
	/**
	 * This method creates a css style
	 * @return the css style
	 */
	public abstract CSSStyle createStyle(); // crea uno stile che è un insieme di regole css
	/**
	 * This method sets the css style
	 * @param style the css style
	 * @return the document on which the method was invoked
	 */
	public abstract HTMLDocument setStyleSheet(CSSStyle style);
	/**
	 * This method returns the head element
	 * @return the head element
	 */
	public abstract HTMLElement getHead();
	/**
	 * This method returns the title element
	 * @return the title element
	 */
	public abstract HTMLElement getTitle();
	/**
	 * This method is equivalent to the javascript method: document.write();
	 * @param text the text
	 * @return the document on which the method was invoked
	 */
	public abstract HTMLDocument write(String text);
	/**
	 * This method sets the minimal tags
	 * @return the document on which the method was invoked
	 */
	public abstract HTMLDocument setMinimalTags();
	/**
	 * This method sets the doctype tag
	 * @param flag the flag
	 * @return the document on which the method was invoked
	 */
	public abstract HTMLDocument useDoctype(boolean flag);
	/**
	 * This method prints the javascript source
	 * @return the document on which the method was invoked
	 */
	public abstract HTMLDocument printJsSource();
	/**
	 * This method returns true if the doctype element is present
	 * @return true if the doctype element is present
	 */
	public abstract boolean doctypeIsPresent();
	public abstract HTMLDocument addClass(String className,HTMLElement...elements);
}
