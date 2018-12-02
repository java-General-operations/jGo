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
package cloud.jgo.jjdom.jquery;
import cloud.jgo.£;
import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.dom.nodes.html.HTMLElement;
import cloud.jgo.jjdom.dom.nodes.html.NodeList;
import cloud.jgo.jjdom.jquery.Event.EventType;

/**
 * 
 * @author Martire91<br>
 * This interface contains the jquery methods.<br>
 * consult the jquery <a href='https://api.jquery.com/'>documentation</a><br> 
 * to cover these methods
 *
 */
public interface jQuerySupport {
	public abstract String attr(String attributeName);
	public abstract JjDom attr(String attrName,String attrValue);
	public abstract JjDom after(final String content);
	public abstract JjDom before(final String content);
	// append method : inserisce il contenuto specificato come ultimo figlio di ciascun elemento nella raccolta jQuery
	public abstract int length();
	public abstract JjDom append(String content);
	public abstract NodeList children();
	public abstract JjDom delay(final int duration);
	public abstract JjDom val();
	public abstract JjDom val(String value);
	public abstract JjDom addClass(String className);
	public abstract JjDom removeClass(String className);
	public abstract JjDom toggleClass(String className);
	public abstract void ready(final jQueryfunction handler); // è void perchè non si considera un metodo, a cui si vuole concatenare qualche altra chiamata di metodi, ma i metodi vanno poi scritti al suo interno
	public abstract JjDom show(final String jqEffect);
	public abstract JjDom show(final int millisec);
	public abstract JjDom show(final String jqEffect,jQueryfunction callBack);
	public abstract JjDom show(final int millisec,jQueryfunction callBack);
	public abstract JjDom hide(final String jqEffect);
	public abstract JjDom hide(final int millisec);
	public abstract JjDom hide(final String jqEffect,jQueryfunction callBack);
	public abstract JjDom hide(final int millisec,jQueryfunction callBack);
	public abstract JjDom slideUp(final String jqEffect);
	public abstract JjDom slideUp(final int millisec);
	public abstract JjDom slideUp(final String jqEffect,jQueryfunction callBack);
	public abstract JjDom slideUp(final int millisec,jQueryfunction callBack);
	public abstract JjDom slideDown(final String jqEffect);
	public abstract JjDom slideDown(final int millisec);
	public abstract JjDom slideDown(final String jqEffect,jQueryfunction callBack);
	public abstract JjDom slideDown(final int millisec,jQueryfunction callBack);
	public abstract JjDom slideToggle(final String jqEffect);
	public abstract JjDom slideToggle(final int millisec);
	public abstract JjDom slidetoggle(final String jqEffect,jQueryfunction callBack);
	public abstract JjDom slideToggle(final int millisec,jQueryfunction callBack);
	public abstract JjDom fadeOut(final String jqEfect);
	public abstract JjDom fadeOut(final int millisec);
	public abstract JjDom fadeOut(final String jqEffect,jQueryfunction callBack);
	public abstract JjDom fadeOut(final int millisec,jQueryfunction callBack);
	public abstract JjDom fadeIn(final String jqEfect);
	public abstract JjDom fadeIn(final int millisec);
	public abstract JjDom fadeIn(final String jqEffect,jQueryfunction callBack);
	public abstract JjDom fadeIn(final int millisec,jQueryfunction callBack);
	public abstract JjDom fadeToggle(final String jqEffect);
	public abstract JjDom fadeToggle(final int millisec);
	public abstract JjDom fadeToggle(final String jqEffect,jQueryfunction callBack);
	public abstract JjDom fadeToggle(final int millisec,jQueryfunction callBack);
    public abstract JjDom css(String cssProp,String cssValue);
    public abstract HTMLElement get(int index);
    public abstract String html();
    public abstract JjDom html(String htmlString);
    public abstract JjDom next(); 
    public abstract JjDom prev();
    public abstract String text();
    public abstract JjDom text(String text);
    public abstract JjDom bind(final String eventType,jQueryfunction handler);
    public abstract JjDom bind(final EventType eventType,jQueryfunction handler);
    public abstract JjDom on(final String eventType,jQueryfunction handler);
    public abstract JjDom on(final EventType eventType,jQueryfunction handler);
    public abstract JjDom off();// elimina tutti gli eventi della selezione
    public abstract JjDom off(final String eventType);// elimina solo gli eventi specificati
    public abstract JjDom off(final EventType eventType);
    public abstract JjDom hover(jQueryfunction handlerIn,jQueryfunction handlerOut);
    public abstract JjDom mouseenter(jQueryfunction handler); // quando entra nell'elemento
    public abstract JjDom mouseleave(jQueryfunction handler); // quando lascia l'elemento
    public abstract JjDom mouseover(jQueryfunction handler); // quando entra nell'elemento
    public abstract JjDom mouseout(jQueryfunction handler); // quando lascia un elemento
    public abstract JjDom submit();
    public abstract JjDom submit(jQueryfunction handler); // solo per i form
    public abstract JjDom click(jQueryfunction handler);
    public abstract JjDom dblclick(jQueryfunction handler);
    public abstract JjDom toggle(final String jqEffect);
    public abstract JjDom toggle(final jQueryEffect jqEffect);
    public abstract JjDom toggle(final String jqEffect,jQueryfunction callBack);
    public abstract JjDom toggle(final jQueryEffect jqEffect,jQueryfunction callBack);
    public abstract JjDom toggle(final int millisec);
    public abstract JjDom toggle(final int millisec,jQueryfunction callBack);
    public abstract String prop(String propertyName); // lavora sul primo elemento
    public abstract JjDom prop(String propertyName,String value);
    public abstract JjDom prepend(String content);
    public abstract JjDom removeAttr(final String attributeName);
    public abstract HTMLElement first();
    public abstract HTMLElement last();
    public abstract JjDom find(String selection); // this method works with the dom
    public enum jQueryEffect {
    	SLOW,FAST
    }
}
