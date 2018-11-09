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

import cloud.jgo.jjdom.dom.HTMLNode;
/**
 * 
 * @author Martire91<br>
 * This class represents a css selector
 *
 */
public interface CSSSelector extends Serializable{
	/**
	 * This method selects and then gets the css selection
	 * @param selection the selection
	 * @return the css selection
	 */
	public abstract CSSSelection select(String selection); // metodo che va chiamato esplicitamente ma va anche ridefinito eventualmente
	/**
	 * This method selects and then gets the css selection
	 * @param element the elements selection 1
	 * @param combiner the css combiner
	 * @param element2 the elements selection 2
	 * @return the css selection
	 */
	public abstract CSSSelection select(String element,String combiner,String element2);
	/**
	 * @see CSSSelector#select(String)
	 * @param selections the selections
	 * @return the css selection
	 */
	public abstract CSSSelection[] select(String...selections);
	/**
	 * This method returns the context node
	 * @return the context node
	 */
	public abstract HTMLNode getRootContext(); // il suo valore di default sarà il documento
	/**
	 * This method sets the context node
	 * @param rootContext the new context node
	 */
	public abstract void setRootContext(HTMLNode rootContext);
	/**
	 * This recursive method is redefined if you want
	 * to customize a css selector
	 * @param context the context
	 * @param index the array index
	 * @param selection the css selection
	 */
	public abstract void recursiveSupport(Object context,int index,CSSSelection selection);
	
	/*
	 * prende tutti gli elementi diversi
	 */
	public final static String DIFFERENT_OPERATOR = "!="  ;
	/*
	 * prende tutti gli elementi che iniziano con questa parola
	 */
	public final static String STARTS_WITH_OPERATOR = "^=";
	/*
	 * prende tutti gli elementi che contengono la parola
	 */
	public final static String CONTAINS_OPERATOR = "*="   ;
	/*
	 * prende tutti gli elementi diversi
	 */
	public final static String ENDS_WITH_OPERATOR = "$="  ;
	
	/*
	 * Questo combinatore prende i soli figli : se per esempio abbiamo nel body, tre parags tra cui uno è in un div, escluderà appunto quest'ultimo e prenderà gli altri due
	 */
	public final static String CHILDREN_COMBINER = ">";
	/*
	 * Questo combinatore concatena i fratelli - ?
	 */
	public final static String ADJACENT_BROTHERS_COMBINER = "+";
	public final static String GENERAL_BROTHERS_COMBINER = "~";
	
	/**
	 * The global selector
	 */
	public final static String GLOBAL_SELECTOR = "*";
	
}
