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
package cloud.jgo.jjdom.dom;

import cloud.jgo.jjdom.dom.nodes.Element;
import cloud.jgo.jjdom.dom.nodes.Elements;
import cloud.jgo.jjdom.dom.nodes.html.HTMLElement;

/**
 * 
 * @author Martire91<br>
 * This interface expresses the concept of manipulable html object
 * 
 */
public interface Manipulable {
	/**
	 * This method gets the element for id
	 * @param elementId the element id
	 * @return the found element
	 */
	public abstract Element getElementById(String elementId);
	/**
	 * This method gets the elements by tag
	 * @param tagName the tag name
	 * @return the found elements
	 */
	public abstract Elements getElementsByTag(String tagName);
	// version 1.0.7
	/**
	 * Obtains the elements, whose "name"<br>
	 * attribute corresponds with the one passed as a parameter.
	 * @param name the name
	 * @return the list of items found
	 */
	public abstract Elements getElementsByName(String name);
	/**
	 * This method gets the elements by class
	 * @param className the class name
	 * @return the found elements
	 */
	public abstract Elements getElementsByClassName(String className);
	/**
	 * This method gets the elements by attribute
	 * @param attribute the attribute
	 * @return the found elements
	 */
	public abstract Elements getElementsByAttribute(String attribute); // questo metodo raccoglie tutti gli elementi che hanno questo attributo
	/**
	 * This method gets the elements by attribute value
	 * @param value the attribute value
	 * @return the found elements
	 */
	public abstract Elements getElementsByAttributeValue(String value);
	/**
	 * This method gets the elements by attribute value
	 * @param attr the attribute
	 * @param val the attribute value
	 * @return the found elements
	 */
	public abstract Elements getElementsByAttributeValue(String attr,String val);
	// questo metodo praticamente, ricerca gli elementi che hanno questo attributo però con un valore diverso di quello specificato
	/**
	 * This method gets elements by different attribute value
	 * @param attr the attribute
	 * @param val the attribute value
	 * @return the found elements
	 */
	public abstract Elements getElementsByDifferentAttributeValue(String attr,String val);
	/**
	 * This method gets the elements that start with the attribute value
	 * @param attr the attribute
	 * @param val the attribute value
	 * @return the found elements
	 */
	public abstract Elements getElementsThatStartWithAttributevalue(String attr,String val);
	/**
	 * This method gets the elements that end with the attribute value
	 * @param attr the attribute
	 * @param val the attribute value
	 * @return the found elements
	 */
	public abstract Elements getElementsThatEndWithAttributeValue(String attr,String val);
	/**
	 * This method gets the elements that contain the attribute value
	 * @param attr the attribute
	 * @param val the attribute value
	 * @return the found elements
	 */
	public abstract Elements getElementsThatContainTheAttributeValue(String attr,String val);
	// metodi diversi ma utili
	/**
	 * This method gets the direct children by tag
	 * @param tagName the tag name
	 * @return the found elements
	 */
	public abstract Elements getDirectChildrenByTag(String tagName);
	// fratelli adiacenti significa posto vicino
	// cioè se in un div h2,h3,strong,h3 e selezioniamo
	// h2 + h3 : mi deve restituire il primo h3 e basta
	// poichè dopo il primo c'è uno strong che blocca la situazione
	// ma se vogliamo prendere i fratelli generali, useremo l'ultimo
	// combinatore 
	/**
	 * This method gets the adiacent siblings by tag
	 * @param tagName the tag name
	 * @return the found elements
	 */
	public abstract Elements getAdiacentSiblingsByTag(String tagName);
	/**
	 * This method gets the general siblings by tag
	 * @param tagName the tag name
	 * @return the found elements
	 */
	public abstract Elements getGeneralSiblingsByTag(String tagName);
}
