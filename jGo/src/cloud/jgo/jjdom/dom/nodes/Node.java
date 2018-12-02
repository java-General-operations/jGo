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
package cloud.jgo.jjdom.dom.nodes;
import java.io.Serializable;

import cloud.jgo.jjdom.Home;
import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.dom.nodes.html.HTMLDocument;
/**
 * 
 * @author Martire91<br>
 *	This interface represents the html node
 *
 */
public interface Node extends Serializable,Home{
	/**
	 * This method appends a child node
	 * @param node the child node
	 * @return the node on which the method was invoked
	 */
	public abstract Node appendChild(Node node);
	/**
	 * This method appends a child nodes
	 * @param childs the child nodes
	 * @return the node on which the method was invoked
	 */
	public abstract Node appendChilds(Node...childs);
	/**
	 * This method returns dynamically the document markup
	 * @return the document markup
	 */
	public abstract String getMarkup(); // new method : show code
	/**
	 * This method prints the document markup
	 * @return the node on which the method was invoked
	 */
	public abstract Node printMarkup();
	/**
	 * This method returns the element path
	 * @return the element path
	 */
	public abstract String getPath();
	/**
	 * This method returns the child nodes
	 * @return the child nodes
	 */
	public abstract NodeList getChildNodes();
	/**
	 * This method verifies if the node passed as a parameter,
	 * is part of the child nodes of the node on which the
	 * method has been invoked
	 * @param node the node
	 * @return true if the node is part of the children
	 */
	public abstract boolean hasThisChild(Node node);
	/**
	 * This method returns the node to the index position
	 * @param index the index position
	 * @return the node on which the method was invoked
	 */
	public abstract Node child(int index);
	/**
	 * This method returns the next node to
	 * the one on which the method was invoked
	 * @return the next node
	 */
	public abstract Node next();
	/**
	 * This method returns the previous node to
	 * the one on which the method was invoked
	 * @return the previous node
	 */
	public abstract Node previous();
	/**
	 * This method returns the index node
	 * @return the index node
	 */
	public abstract int getIndex();
	/**
	 * This method returns the first node of child nodes
	 * @return the first node
	 */
	public abstract Node getFirstChild();
	/**
	 * This method returns the last node of child nodes
	 * @return the last node
	 */
	public abstract Node getLastChild();
	/**
	 * This method returns the local name
	 * @return the local name
	 */
	public abstract String getLocalName();
	/**
	 * This method returns the node brothers
	 * @return the node brothers
	 */
	public abstract NodeList getBrothers(); // restituisce i fratelli del nodo, ipotizzando che li abbia
	/**
	 * This method returns the next sibling
	 * @return the next sibling
	 */
	public abstract Node getNextSibling();
	/**
	 * This method adds the first child
	 * @param node the node
	 */
	public abstract void addFirstChild(Node node);
	/**
	 * This method returns the node name
	 * @return the node name
	 */
	public abstract String getNodeName();
	/**
	 * This method checks if the node passed as a parameter
	   it is contained in the children of the node on which
	   the method was invoked
	 * @param node the node
	 * @return true if the node is contained
	 */
	public abstract boolean contains(Node node); // non è ricorsivo, controlla se è contenuto il nodo
	/**
	 * This method returns the node value
	 * @return the node value
	 */
	public abstract String getNodeValue();
	/**
	 * This method returns the node text
	 * @return the node text
	 */
	public abstract String getTextContent();
	/**
	 * This method sets the node value
	 * @param nodeValue the node value
	 * @return the node on which the method was invoked
	 */
	public abstract Node setNodeValue(String nodeValue);
	/**
	 * This method sets the node text
	 * @param textContent the node text
	 * @return the node on which the method was invoked
	 */
	public abstract Node setTextContent(String textContent);
	/**
	 * This method returns the document URL
	 * @return the document URL
	 */
	public abstract String getBaseURI();
	/**
	 * This method returns the document
	 * @return the document
	 */
	public abstract Document getDocument(); // restituisce il documento
	/**
	 * This method returns the parent node
	 * @return the parent node
	 */
	public abstract Node getParentNode();
	/**
	 * This method returns the node type
	 * @return the node type
	 */
	public abstract NodeType getNodeType();
	/**
	 * This method checks if the node on which the method has been invoked has children
	 * @return true if has children
	 */
	public abstract boolean hasChildNodes();
	/**
	 * This method inserts "newNode" immediately before "refChild"
	 * @param newNode the new node
	 * @param refChild the ref child
	 * @return the node on which the method was invoked
	 */
	public abstract Node insertBefore(Node newNode,Node refChild);
	/**
	 * This method inserts "newNode" immediately after "refChild"
	 * @param newNode the new node
	 * @param refChild the ref child
	 * @return the node on which the method was invoked
	 */
	public abstract Node insertAfter(Node newNode,Node refChild);
	/**
	 * This method returns true if the two nodes are of the same type
	 * @param node the node
	 * @return true if the two nodes are of the same type
	 */
	public abstract boolean isEqualNode(Node node);
	/**
	 * This method removes the node
	 * @param node the node to be removed
	 * @return the deleted node
	 */
	public abstract Node removeNode(Node node);
	/**
	 * This method replaces "oldNode" with "newNode"
	 * @param newNode the new node
	 * @param oldNode the old node
	 * @return the old node
	 */
	public abstract Node replaceChild(Node newNode,Node oldNode);
	// from to comment - 1.0.7
	public abstract Node getNodeByPath(String nodePath);
	// version 1.0.7
	public abstract boolean contains(String nodeName);
		/**
		 * 
		 * @author Martire91<br>
		 *	This Enum represents the HTML node type
		 */
		public static enum NodeType{
			ELEMENT,
			DOCUMENT,
			COMMENT
		}
}
