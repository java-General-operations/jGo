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

import java.io.Serializable;
import java.util.Map;

import cloud.jgo.jjdom.Home;
import cloud.jgo.jjdom.dom.Manipulable;
import cloud.jgo.jjdom.dom.nodes.Element;
import cloud.jgo.jjdom.dom.nodes.Node;

/**
 * 
 * @author Martire91<br>
 * @see Node
 * @see Manipulable This interface represents the concept of html element
 *
 */
public interface HTMLElement extends Element, Home {
	/**
	 * This method returns the element type
	 * 
	 * @return the element type
	 */
	public abstract HTMLElementType getType();

	/**
	 * This method returns the element id
	 * 
	 * @return the element id
	 */
	public abstract String getId();

	/**
	 * This method sets the element id
	 * 
	 * @param idElement
	 *            the element id
	 * @return the element on which the method was invoked
	 */
	public abstract HTMLElement setId(String idElement);

	/**
	 * The Html Space
	 */
	public final static String HTML_SPACE = "&nbsp;";

	// metodi per il css
	/**
	 * This method appends the br tag
	 * 
	 * @return the element on which the method was invoked
	 */
	public abstract HTMLElement appendBR();

	/**
	 * This method adds a class
	 * 
	 * @param className
	 *            the class name
	 * @return the element on which the method was invoked
	 */
	public abstract HTMLElement addClass(String className);

	/**
	 * This method adds the classes
	 * 
	 * @see HTMLElement#addClass(String)
	 * @param classes
	 *            the classes
	 * @return the element on which the method was invoked
	 */
	public abstract HTMLElement addClasses(String... classes);

	/**
	 * This method removes a class
	 * 
	 * @param className
	 *            the class name
	 * @return the element on which the method was invoked
	 */
	public abstract HTMLElement removeClass(String className);

	/**
	 * This method removes the classes
	 * 
	 * @param classes
	 *            the classes
	 * @return the element on which the method was invoked
	 */
	public abstract HTMLElement removeClasses(String... classes);

	/**
	 * This method adds a css property
	 * 
	 * @param cssProps
	 *            css property
	 * @return the element on which the method was invoked
	 */
	public abstract HTMLElement addCssProps(String... cssProps); // css inline
	// version 1.0.7

	public abstract HTMLElement addCssProp(final String cssProp);

	// version-1.0.7
	/**
	 * 
	 * This method gets a css property value
	 * 
	 * @param onlyProp
	 *            the css property name
	 * @return the css property value
	 */
	public abstract String getCssPropValue(String onlyProp);

	// version-1.0.7
	/**
	 * 
	 * This method gets a css property
	 * 
	 * @param onlyProp
	 *            the css property name
	 * @return the css property
	 */
	public abstract String getCssProp(String onlyProp);

	// version 1.0.7
	/**
	 * This method verifies whether the element contains a css property or not.
	 * 
	 * @param onlyProp
	 *            the css property name
	 * @return true if the element has the css property
	 */
	public abstract boolean hasCssProp(String onlyProp);

	// version 1.0.7 : lavorano solo a livello dom
	/**
	 * This method hides the element in the web page
	 * 
	 * @return the element on which the method was called
	 */
	public abstract HTMLElement hide();

	// version 1.0.7
	/**
	 * This method shows the element in the web page
	 * 
	 * @return the element on which the method was called
	 */
	public abstract HTMLElement show();

	/**
	 * 
	 * @author Martire91<br>
	 *         This class represents the element type
	 */
	static class HTMLElementType implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private boolean hasClosingTag = true; // default value
		private String tagName = null;
		private boolean thereCanBeMore = false; // default value

		// metodo pre creare nuovi tipi
		/**
		 * This method creates a new type
		 * 
		 * @param tagName
		 *            the tag name
		 * @return the type
		 */
		public static HTMLElementType newType(String tagName) {
			HTMLElementType type = new HTMLElementType(tagName);
			return type;
		}

		/**
		 * This method sets if the type provides a closing tag
		 * 
		 * @param flag
		 *            the flag
		 */
		public void setClosingTag(boolean flag) {
			this.hasClosingTag = flag;
		}

		/**
		 * This method sets if of this type, there can be more than one
		 * 
		 * @param flag
		 *            the flag
		 */
		public void setThereCanBeMore(boolean flag) {
			this.thereCanBeMore = flag;
		}

		private HTMLElementType(String tagName, boolean hasClosingTag, boolean thereCanBeMore) {
			this.tagName = tagName;
			this.hasClosingTag = hasClosingTag;
			this.thereCanBeMore = thereCanBeMore;
		}

		private HTMLElementType(String tagName) {
			this.tagName = tagName;
		}

		public final static HTMLElementType HTML = new HTMLElementType("html", true, false);
		public final static HTMLElementType HEAD = new HTMLElementType("head", true, false);
		public final static HTMLElementType TITLE = new HTMLElementType("title", true, false);
		public final static HTMLElementType SCRIPT = new HTMLElementType("script", true, true);
		public final static HTMLElementType LINK = new HTMLElementType("link", false, true);
		public final static HTMLElementType BODY = new HTMLElementType("body", true, false);
		public final static HTMLElementType DIV = new HTMLElementType("div", true, true);
		public final static HTMLElementType TABLE = new HTMLElementType("table", true, true);
		public final static HTMLElementType TH = new HTMLElementType("th", true, true);
		public final static HTMLElementType TR = new HTMLElementType("tr", true, true);
		public final static HTMLElementType TD = new HTMLElementType("td", true, true);
		public final static HTMLElementType TBODY = new HTMLElementType("tbody", true, true);
		public final static HTMLElementType THEAD = new HTMLElementType("thead", true, true);
		public final static HTMLElementType TFOOT = new HTMLElementType("tfoot", true, true);
		public final static HTMLElementType STRONG = new HTMLElementType("strong", true, true);
		public final static HTMLElementType IMG = new HTMLElementType("img", false, true);
		public final static HTMLElementType H1 = new HTMLElementType("h1", true, true);
		public final static HTMLElementType H2 = new HTMLElementType("h2", true, true);
		public final static HTMLElementType H3 = new HTMLElementType("h3", true, true);
		public final static HTMLElementType H4 = new HTMLElementType("h4", true, true);
		public final static HTMLElementType H5 = new HTMLElementType("h4", true, true);
		public final static HTMLElementType H6 = new HTMLElementType("h4", true, true);
		public final static HTMLElementType P = new HTMLElementType("p", true, true);
		public final static HTMLElementType A = new HTMLElementType("a", true, true);
		public final static HTMLElementType BR = new HTMLElementType("br", false, true);
		public final static HTMLElementType UL = new HTMLElementType("ul", true, true);
		public final static HTMLElementType OL = new HTMLElementType("ol", true, true);
		public final static HTMLElementType LI = new HTMLElementType("li", true, true);
		public final static HTMLElementType SPAN = new HTMLElementType("span", true, true);
		public final static HTMLElementType EM = new HTMLElementType("em", true, true);
		public final static HTMLElementType FORM = new HTMLElementType("form", true, true);
		public final static HTMLElementType INPUT = new HTMLElementType("input", false, true);
		public final static HTMLElementType LABEL = new HTMLElementType("label", true, true);
		public final static HTMLElementType OPTION = new HTMLElementType("option", true, true);
		public final static HTMLElementType SELECT = new HTMLElementType("select", true, true);
		public final static HTMLElementType TIME = new HTMLElementType("time", true, true);
		public final static HTMLElementType VIDEO = new HTMLElementType("video", true, true);
		public final static HTMLElementType META = new HTMLElementType("meta", false, true);
		public final static HTMLElementType STYLE = new HTMLElementType("style", true, false); // provvisorio
		public final static HTMLElementType CODE = new HTMLElementType("code", true, true);
		public final static HTMLElementType BUTTON = new HTMLElementType("button", true, true);
		public final static HTMLElementType ARTICLE = new HTMLElementType("article", true, true);
		public final static HTMLElementType ASIDE = new HTMLElementType("aside", true, true);
		public final static HTMLElementType CANVAS = new HTMLElementType("canvas", true, true);
		public final static HTMLElementType CAPTION = new HTMLElementType("caption", true, true);
		public final static HTMLElementType COL = new HTMLElementType("col", false, true);
		public final static HTMLElementType COL_GROUP = new HTMLElementType("colgroup", true, true);
		public final static HTMLElementType[] availableTypes = { HTML, HEAD, TITLE, SCRIPT, LINK, BODY, DIV, TABLE, TH,
				TR, TD, TBODY, THEAD, TFOOT, STRONG, IMG, H1, H2, H3, H4, P, A, BR, UL, OL, LI, SPAN, EM, FORM, INPUT,
				LABEL, OPTION, SELECT, TIME, VIDEO, META, STYLE, CODE, BUTTON, ARTICLE, ASIDE, CANVAS, CAPTION, COL,
				COL_GROUP };

		public static HTMLElementType parse(String type) {
			HTMLElementType typ = null;
			for (int i = 0; i < availableTypes.length; i++) {
				if (type.equals(availableTypes[i].toString())) {
					typ = availableTypes[i];
					break;
				}
			}
			return typ;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.tagName;
		}

		@Override
		public boolean equals(Object obj) {
			HTMLElementType cast = (HTMLElementType) obj;
			if (this.tagName.equals(cast.tagName) && this.hasClosingTag == cast.hasClosingTag
					&& this.thereCanBeMore == cast.thereCanBeMore) {
				return true;
			} else {
				return false;
			}
		}

		/**
		 * This method indicates whether the type provides a closing tag
		 * 
		 * @return the flag
		 */
		public boolean hasClosingTag() {
			return hasClosingTag;
		}

		/**
		 * This method indicates if of this type, there can be more than one
		 * 
		 * @return the flag
		 */
		public boolean thereCanBeMore() {
			return thereCanBeMore;
		}

	}

}
