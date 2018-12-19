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
package cloud.jgo.jjdom.dom.nodes.xml;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import cloud.jgo.£;
import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.css.CSSSelector;
import cloud.jgo.jjdom.dom.Manipulable;
import cloud.jgo.jjdom.dom.Recursion;
import cloud.jgo.jjdom.dom.nodes.Comment;
import cloud.jgo.jjdom.dom.nodes.Element;
import cloud.jgo.jjdom.dom.nodes.Elements;
import cloud.jgo.jjdom.dom.nodes.Node;
import cloud.jgo.jjdom.dom.nodes.NodeList;
import cloud.jgo.jjdom.dom.nodes.html.HTMLComment;
import cloud.jgo.jjdom.dom.nodes.html.HTMLDefaultDocument;
import cloud.jgo.jjdom.dom.nodes.html.HTMLDefaultElement;
import cloud.jgo.jjdom.dom.nodes.html.HTMLDocument;
import cloud.jgo.jjdom.dom.nodes.html.HTMLElement;
import cloud.jgo.jjdom.dom.nodes.html.HTMLElement.HTMLElementType;

/**
 * 
 * @author Martire91<br>
 * @see Node
 * @see Manipulable This class represents the concept of XML element
 *
 */
public class XMLElement implements Element {
	private String nodeName = null;
	private NodeList childNodes = null;
	private XMLDocument document = null;
	private String elementName = null;
	private String startTag, endTag = null;
	private String originalStartTag, originalEndTag = null;
	private String textContent = null;
	private Map<String, String> attributes = null;
	private Node parentNode = null;
	private StringBuffer xmlCode = new StringBuffer();

	public XMLElement(String elementName, XMLDocument Document) {
		// TODO Auto-generated constructor stub
		this.nodeName = elementName;
		this.startTag = "<" + elementName + ">";
		this.originalStartTag = this.startTag;
		this.endTag = "</" + elementName + ">";
		this.originalEndTag = this.endTag;
		this.childNodes = new NodeList();
		this.attributes = new HashMap<>();
		this.document = document;
	}

	public String getStartTag() {
		return this.startTag;
	}

	public void setStartTag(String startTag) {
		this.startTag = startTag;
	}

	public String getEndTag() {
		return this.endTag;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nodeName ;
	}
	protected XMLElement() {
	}

	/**
	 * This method sets the parent node
	 * 
	 * @param parentNode
	 *            the parent node
	 */
	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}

	@Override
	public Node appendChild(Node node) {
		if (this.childNodes.contains(node)) {
			this.childNodes.remove(node);
		}
		// aggiungo il nodo

		boolean result = this.childNodes.addNode(node);
		if (result == true) {
			if (node instanceof XMLElement) {
				((XMLElement) node).setParentNode(this);
			} else if (node instanceof Comment) {
				((HTMLComment) node).setParentNode(this);
			}
			return node;
		} else {
			return null;
		}
	}

	@Override
	public Node appendChilds(Node... childs) {
		for (int i = 0; i < childs.length; i++) {
			appendChild(childs[i]);
		}
		return this;
	}

	@Override
	public String getMarkup() {
		Recursion.examines_xml(this, xmlCode, null); // provvisorio, poi gli dobbiamo passare il document
		String result = xmlCode.toString();
		// pulisco il buffer code html
		xmlCode = new StringBuffer();
		return result;
	}

	@Override
	public Node printMarkup() {
		// TODO Auto-generated method stub
		System.out.println(getMarkup());
		return this;
	}

	@Override
	public String getPath() {
		// TODO Auto-generated method stub
		return Recursion.examinesForTPath(this);
	}

	@Override
	public NodeList getChildNodes() {
		// TODO Auto-generated method stub
		return this.childNodes;
	}

	@Override
	public boolean hasThisChild(Node node) {
		boolean flag = false;
		NodeList listNodes = getChildNodes();
		for (int i = 0; i < listNodes.getLength(); i++) {
			if (listNodes.item(i).equals(node)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	@Override
	public Node child(int index) {
		// TODO Auto-generated method stub
		return this.childNodes.item(index);
	}

	@Override
	public Node next() {
		// Ottengo il padre
		Node next = null;
		Node parent = this.parentNode;
		// ottengo i figli del parent
		NodeList list = parent.getChildNodes();
		// faccio iterare i figli del parent in modo tale che
		// individuo l'elemento che si trova dopo
		// l'elemento in questione, ipotizzando che ci sia
		boolean flag = false;
		int pos = 0;
		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			if (node.equals(this)) {
				flag = true;
				pos = i;
				break;
			}
		}
		if (flag) {
			// qui il controllo va bene finora
			// lista lunghezza 5
			// mettiamo che si vuole l'elemento dopo
			// il primo elemento della lista
			// quindi quello è a posizione 0
			// e chiamando il metodo si incrementa
			// quindi non c'è mai il rischio
			// di andare a -1. Perchè si incrementa
			int value = pos + 1;
			if (value <= list.getLength() - 1) {
				next = list.item(value);
			}
		}
		return next;
	}

	@Override
	public Node previous() {
		Node previous = null;
		boolean flag = false;
		int pos = 0;
		// primo passo, prendo il padre
		Node parent = getParentNode();

		// prendo i figli del padre

		NodeList list = parent.getChildNodes();

		// faccio iterare i figli del padre

		for (int i = 0; i < list.getLength(); i++) {
			// okok abbiamo trovato l'elemento
			if (list.item(i).equals(this)) {
				flag = true;
				pos = i;
				break;
			}
		}
		if (flag) {
			// qui dentro verifico se la posizione è corretta per l'array
			int value = pos - 1;
			if (value > -1) {
				// quindi qui se vogliamo prendere
				// l'elemento precedente, sull'ultimo elemento
				// della lista quindi il 4, si decrementa, quindi non c'è
				// mai il rischio di arrivare alla lunghezza
				// massimo si può prendere l'ultimo elemento della lista
				// che potrebbe essere pericoloso, peccato che però il metodo
				// poi decrementa il valore. Kmq testiamolo per bene
				previous = list.item(value);
			}
		}
		return previous;
	}

	@Override
	public int getIndex() {
		Node parent = this.parentNode;
		int index = -1;
		for (int i = 0; i < parent.getChildNodes().getLength(); i++) {
			if (parent.getChildNodes().item(i).equals(this)) {
				index = i;
				break;
			}
		}
		return index;
	}

	@Override
	public Node getFirstChild() {
		// TODO Auto-generated method stub
		return this.childNodes.getFirstItem();
	}

	@Override
	public Node getLastChild() {
		// TODO Auto-generated method stub
		return this.childNodes.getLastItem();
	}

	@Override
	public String getLocalName() {
		// TODO Auto-generated method stub
		return getNodeName();
	}

	@Override
	public NodeList getBrothers() {
		NodeList list = null;
		// individuo il padre del nodo in questione
		Node parent = getParentNode();

		if (parent != null) {
			// ottengo i figli del padre

			NodeList childNodes = parent.getChildNodes();

			// rimuovo dai figli il nodo in questione

			childNodes.remove(this);
			list = childNodes;
		}
		return list;
	}

	@Override
	public Node getNextSibling() {
		Node parent = getParentNode();
		if (parent != null) {
			// qui se ha un padre ci rende la vita più facile
			NodeList listNodes = parent.getChildNodes();
			int pos = 0;
			boolean found = false;
			for (int i = 0; i < listNodes.getLength(); i++) {
				if (listNodes.item(i).equals(this)) {
					pos = i;
					found = true;
					break;
				}
			}
			if (found) {
				pos = pos + 1;
				if (pos < listNodes.getLength()) {
					return listNodes.item(pos);
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public void addFirstChild(Node node) {
		if (this.childNodes.contains(node)) {
			this.childNodes.remove(node);
		}
		// aggiungo il nodo
		this.childNodes.addFirstNode(node);
		if (node instanceof Element) {
			((XMLElement) node).setParentNode(this);
		} else if (node instanceof Comment) {
			((HTMLComment) node).setParentNode(this);
		}
	}

	@Override
	public String getNodeName() {
		// TODO Auto-generated method stub
		return this.nodeName;
	}

	@Override
	public boolean contains(Node node) {
		boolean contains = false;
		NodeList listNodes = this.childNodes;
		for (int i = 0; i < listNodes.getLength(); i++) {
			if (listNodes.item(i).equals(node)) {
				contains = true;
				break;
			}
		}
		return contains;
	}

	@Override
	public String getNodeValue() {
		// TODO Auto-generated method stub
		return this.textContent;
	}

	@Override
	public String getTextContent() {
		// TODO Auto-generated method stub
		return this.textContent;
	}

	@Override
	public Node setNodeValue(String nodeValue) {
		// TODO Auto-generated method stub
		this.textContent = nodeValue;
		return this;
	}

	@Override
	public Node setTextContent(String textContent) {
		// TODO Auto-generated method stub
		this.textContent = textContent;
		return this;
	}

	@Override
	public String getBaseURI() {
		String baseURI = null;
		if (JjDom.documentURL != null) {
			if (£.extractFormatFromFileName(JjDom.documentURL).equals("xml")) {
				baseURI = JjDom.documentURL;
			}
		}
		return baseURI;
	}

	@Override
	public XMLDocument getDocument() {
		// TODO Auto-generated method stub
		return this.document;
	}

	@Override
	public Node getParentNode() {
		// TODO Auto-generated method stub
		return this.parentNode;
	}

	@Override
	public NodeType getNodeType() {
		// TODO Auto-generated method stub
		return NodeType.ELEMENT;
	}

	@Override
	public boolean hasChildNodes() {
		// TODO Auto-generated method stub
		if (this.childNodes.getLength() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Node insertBefore(Node newNode, Node refChild) {
		// questo metodo deve inserire l'elemento newNOde
		// prima di refChild, quindi vediamo i passi da fare :
		// 1 trovare la posizione di refChild
		boolean found = false;
		int pos = 0;
		for (int i = 0; i < this.childNodes.getLength(); i++) {
			if (this.childNodes.item(i).equals(refChild)) {
				found = true;
				pos = i;
				break;
			}
		}
		if (found) {
			if (pos == 0) {
				// qui basta aggiungere l'elemento
				if (newNode instanceof Element) {
					((XMLElement) newNode).setParentNode(this);
				} else if (newNode instanceof Comment) {
					((HTMLComment) newNode).setParentNode(this);
				}
				this.childNodes.addFirstNode(newNode);
			} else if (pos > 0) {
				// okok qui il primo passo da fare è :
				// verificare se in quella data posizione esiste già un elemento
				int index = pos - 1;

				Node previousNode = this.childNodes.item(index);

				// quindi qui dobbiamo sostituire questo elemento prima
				if (newNode instanceof Element) {
					((XMLElement) newNode).setParentNode(this);
				} else if (newNode instanceof Comment) {
					((HTMLComment) newNode).setParentNode(this);
				}
				previousNode = this.childNodes.setNode(index, newNode);
				// essendo che lo abbiamo sostituito, ora dobbiamo permettere
				// che lo preceda per cui richiamo il metodo
				// recursive
				insertBefore(previousNode, newNode);
			}
		}
		return newNode;
	}

	@Override
	public Node insertAfter(Node newNode, Node refChild) {
		// TODO Auto-generated method stub
		boolean found = false;
		int pos = 0;
		for (int i = 0; i < this.childNodes.getLength(); i++) {
			if (this.childNodes.item(i).equals(refChild)) {
				found = true;
				pos = i;
				break;
			}
		}
		if (found) {
			// okok qui il primo passo da fare è :
			// verificare se in quella data posizione esiste già un elemento

			int index = pos + 1;

			// qui verifico se l'index raggiunto sia minore del lenght attuale della lista
			if (index > this.childNodes.getLength() - 1) {
				// qui mi basta aggiungere
				if (newNode instanceof Element) {
					((XMLElement) newNode).setParentNode(this);
				} else if (newNode instanceof Comment) {
					((HTMLComment) newNode).setParentNode(this);
				}
				this.childNodes.addNode(newNode);
			} else {
				Node followingNode = this.childNodes.item(index);
				// sostituisco
				if (newNode instanceof Element) {
					((XMLElement) newNode).setParentNode(this);
				} else if (newNode instanceof Comment) {
					((HTMLComment) newNode).setParentNode(this);
				}
				followingNode = this.childNodes.setNode(index, newNode);
				// recursive
				insertAfter(followingNode, newNode);
			}
		}
		return newNode;
	}

	// da ridefinire ....
	@Override
	public boolean isEqualNode(Node node) {
		boolean flag = false;
		if (node.getNodeType().equals(this.getNodeType())) {
			// a questo punto si va a ricavare il tipo specifico del nodo

			flag = true;
		}
		return flag;
	}

	@Override
	public Node removeNode(Node node) {
		boolean result = this.childNodes.remove(node);
		if (result == true) {
			return node;
		} else {
			return null;
		}
	}

	@Override
	public Node replaceChild(Node newNode, Node oldNode) {
		// primo passo prendo la posizione del nodo vecchio
		boolean found = false;
		int pos = 0;
		for (int i = 0; i < this.childNodes.getLength(); i++) {
			if (oldNode.equals(this.childNodes.item(i))) {
				found = true;
				pos = i;
				break;
			}
		}
		if (found) {
			return this.childNodes.setNode(pos, newNode);
		} else {
			return null;
		}
	}

	@Override
	public Node getNodeByPath(String nodePath) {
		String[] split = nodePath.split("/");
		Node currentNode = this;
		boolean found;
		for (int i = 0; i < split.length; i++) {
			String nodeName = split[i].trim();
			found = false;
			NodeList listNodes = currentNode.getChildNodes();
			for (int j = 0; j < listNodes.getLength(); j++) {
				Node node = listNodes.item(j);
				if (node.getNodeName().equals(nodeName)) {
					currentNode = node;
					found = true;
					break;
				}
			}
			if (!found) {
				// spezziamo la catena
				currentNode = null;
				break; // esco dal controllo del path,poichè non è più ricostruibile
				// in quanto un elemento non è stato trovato
			}
		}
		return currentNode;
	}

	@Override
	public boolean contains(String nodeName) {
		NodeList childNodes = getChildNodes();
		boolean flag = false;
		for (int i = 0; i < childNodes.getLength(); i++) {
			if (nodeName.equals(childNodes.item(i).getNodeName())) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	// testare questi metodi prima del rilascio
	@Override
	public XMLElement getElementById(String elementId) {
		// TODO Auto-generated method stub
		return (XMLElement) Recursion.examinesForId(elementId, this);
	}

	@Override
	public Elements getElementsByTag(String tagName) {
		// TODO Auto-generated method stub
		return Recursion.examinesForTag(tagName, this);
	}

	@Override
	public Elements getElementsByName(String name) {
		// TODO Auto-generated method stub
		return Recursion.examinesForName(name, this);
	}

	@Override
	public Elements getElementsByClassName(String className) {
		// TODO Auto-generated method stub
		return Recursion.examinesForClass(className, this);
	}

	@Override
	public Elements getElementsByAttribute(String attribute) {
		// TODO Auto-generated method stub
		Elements elements = new Elements();
		if (this.hasAttributes()) {
			if (this.isPresent(attribute)) {
				elements.add(this);
			}
		}
		return elements;
	}

	@Override
	public Elements getElementsByAttributeValue(String value) {
		Elements elements = new Elements();
		if (this.hasAttributes()) {
			// prendo tutti gli attributi
			Iterator<Entry<String, String>> iterator = this.attributes.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<java.lang.String, java.lang.String> entry = (Map.Entry<java.lang.String, java.lang.String>) iterator
						.next();
				String val = entry.getValue();
				if (val.equals(value)) {
					elements.add(this);
					break;
				}
			}
		}
		return elements;
	}

	@Override
	public Elements getElementsByAttributeValue(String attr, String val) {
		Elements elements = new Elements();
		if (this.hasAttributes()) {
			if (this.isPresent(attr)) {
				String value = this.getAttributeValue(attr);
				if (value.equals(val)) {
					elements.add(this);
				}
			}
		}
		return elements;
	}

	@Override
	public Elements getElementsByDifferentAttributeValue(String attr, String val) {
		Elements elements = new Elements();
		if (this.hasAttributes()) {
			if (this.isPresent(attr)) {
				String value = this.getAttributeValue(attr);
				if (!value.equals(val)) {
					elements.add(this);
				}
			}
		}
		return elements;
	}

	@Override
	public Elements getElementsThatStartWithAttributevalue(String attr, String val) {
		return Recursion.examinesForAttributeValue_(attr, val, this, CSSSelector.STARTS_WITH_OPERATOR);
	}

	@Override
	public Elements getElementsThatEndWithAttributeValue(String attr, String val) {
		// TODO Auto-generated method stub
		return Recursion.examinesForAttributeValue_(attr, val, this, CSSSelector.ENDS_WITH_OPERATOR);
	}

	@Override
	public Elements getElementsThatContainTheAttributeValue(String attr, String val) {
		// TODO Auto-generated method stub
		return Recursion.examinesForAttributeValue_(attr, val, this, CSSSelector.CONTAINS_OPERATOR);
	}

	@Override
	public Elements getDirectChildrenByTag(String tagName) {
		Elements elements = new Elements();
		NodeList listNodes = this.childNodes;
		for (int i = 0; i < listNodes.getLength(); i++) {
			if (listNodes.item(i) instanceof Element) {
				if (listNodes.item(i).getNodeName().equals(tagName)) {
					elements.add((XMLElement) listNodes.item(i));
				}
			}
		}
		return elements;
	}

	@Override
	public Elements getAdiacentSiblingsByTag(String tagName) {
		Elements found = new Elements();
		int index = this.getIndex(); // facciamo partire il ciclo dall'indice dell'elemento
		NodeList listNodes = getParentNode().getChildNodes();
		for (int i = (index + 1); i < listNodes.getLength(); i++) {
			if (listNodes.item(i).getNodeName().equals(tagName)) {
				found.add((XMLElement) listNodes.item(i));
			} else {
				// nel momento in cui non trovi un successivo, esci dal ciclo
				break;
			}
		}
		return found;
	}

	@Override
	public Elements getGeneralSiblingsByTag(String tagName) {
		Elements found = new Elements();
		int index = this.getIndex(); // facciamo partire il ciclo dall'indice dell'elemento
		NodeList listNodes = getParentNode().getChildNodes();
		for (int i = (index + 1); i < listNodes.getLength(); i++) {
			if (listNodes.item(i).getNodeName().equals(tagName)) {
				found.add((XMLElement) listNodes.item(i));
			}
		}
		return found;
	}

	@Override
	public boolean hasBrothers() {
		if (getBrothers().getLength() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Map<String, String> getAttributes() {
		return this.attributes;
	}

	@Override
	public XMLElement setAttribute(String attr, String val) {
		this.attributes.put(attr, val);
		return this;
	}

	@Override
	public XMLElement replaceAttributeValue(String attr, String newValue) {
		if (isPresent(attr)) {
			String oldString = attributes.replace(attr, newValue);
			if (oldString != null) {
				return this;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public XMLElement removeAttribute(String attr) {
		String attribute = this.attributes.remove(attr);
		if (attribute != null) {
			return this;
		} else {
			return null;
		}
	}

	@Override
	public String getAttribute(String attr) {
		return attr + "=" + this.attributes.get(attr);
	}

	@Override
	public String getAttributeValue(String attr) {
		return this.attributes.get(attr);
	}

	@Override
	public Node getPreviousSibling() {
		Node parent = getParentNode();
		if (parent != null) {
			// qui se ha un padre ci rende la vita più facile
			NodeList listNodes = parent.getChildNodes();
			int pos = 0;
			boolean found = false;
			for (int i = 0; i < listNodes.getLength(); i++) {
				if (listNodes.item(i).equals(this)) {
					pos = i;
					found = true;
					break;
				}
			}
			if (found) {
				pos = pos - 1;
				if (pos > -1) {
					return listNodes.item(pos);
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public boolean isPresent(String attribute) {
		boolean present = false;
		Set<String> attributes = this.attributes.keySet();
		Iterator<String> iterator = attributes.iterator();
		while (iterator.hasNext()) {
			String attr = (String) iterator.next();
			if (attr.equals(attribute)) {
				present = true;
			}
		}
		return present;
	}

	@Override
	public boolean hasAttributes() {
		if (this.attributes.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	// poi bisogna sviluppare il metodo getBaseURI()
	@Override
	public String getCompletePath() {
		String completePath = null;
		if (getBaseURI() != null) {
			completePath = getBaseURI();
		}
		completePath = completePath + "/" + getPath();
		return completePath;
	}

	@Override
	public Node removeChildren() {
		NodeList children = getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node currentNode = children.item(i);
			removeNode(currentNode);
			i--;
		}
		return this;
	}
}
