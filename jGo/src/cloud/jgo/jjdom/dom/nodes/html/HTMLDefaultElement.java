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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JOptionPane;

import cloud.jgo.£;
import cloud.jgo.io.File;
import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.css.CSSSelector;
import cloud.jgo.jjdom.dom.Recursion;
import cloud.jgo.jjdom.dom.nodes.Elements;
import cloud.jgo.jjdom.dom.nodes.Node;
import cloud.jgo.jjdom.dom.nodes.NodeList;

/**
 * 
 * @author Martire91<br>
 * @see HTMLElement This class represents the html default element
 */
public class HTMLDefaultElement implements HTMLElement {
	private static final long serialVersionUID = 12L;
	// il node value e il text content, rappresentano la stessa variabile, poichè
	// sappiamo che sono elementi html
	private NodeList childNodes = null;
	private HTMLDocument document = null;
	private String elementName = null;
	private HTMLElementType type = null;
	private String startTag, endTag = null;
	private String originalStartTag, originalEndTag = null;
	private String textContent = null;
	private Map<String, String> attributes = null;
	private Node parentNode = null;
	private StringBuffer htmlCode = new StringBuffer();
	private JjDom home = null;

	// unico costruttore
	// si deve creare una instanza di questa classe
	public HTMLDefaultElement(String elementName, HTMLDocument document) {
		// TODO Auto-generated constructor stub
		this.home = document.home(); // passo alla home dell'elemento che si sta creando, la home associata al
										// documento :)
		this.elementName = elementName;
		this.startTag = "<" + elementName + ">";
		this.originalStartTag = this.startTag;
		this.endTag = "</" + elementName + ">";
		this.originalEndTag = this.endTag;
		this.childNodes = new NodeList();
		this.attributes = new HashMap<>();
		this.document = document;
	}

	// version 1.0.6
	protected HTMLDefaultElement() {
		// TODO Auto-generated constructor stub

	}

	/**
	 * This method returns the start tag
	 * 
	 * @return the start tag
	 */
	public String getStartTag() {
		return startTag;
	}

	/**
	 * This method sets the start tag
	 * 
	 * @param startTag
	 *            the start tag
	 */
	public void setStartTag(String startTag) {
		this.startTag = startTag;
	}

	/**
	 * This method returns the end tag
	 * 
	 * @return the end tag
	 */
	public String getEndTag() {
		return endTag;
	}

	/**
	 * This method sets the end tag
	 * 
	 * @param endTag
	 *            the end tag
	 */
	public void setEndTag(String endTag) {
		this.endTag = endTag;
	}

	/**
	 * This method sets the element type
	 * 
	 * @param type
	 *            the element type
	 */
	protected void setType(HTMLElementType type) {
		this.type = type;
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
	public String toString() {
		// TODO Auto-generated method stub
		return getNodeName();
	}

	@Override
	public Node appendChild(Node node) {
		if (this.childNodes.contains(node)) {
			this.childNodes.remove(node);
		}
		// aggiungo il nodo

		boolean result = this.childNodes.addNode(node);
		if (result == true) {
			if (node instanceof HTMLElement) {
				((HTMLDefaultElement) node).setParentNode(this);

				// qui faccio i vari controlli per ricavarmi i tags minimali
				if (this.getNodeName().equals("html")) {
					// PARENT = HTML : body, head

					if (node.getNodeName().equals("body")) {
						((HTMLDefaultDocument) getDocument()).setBody((HTMLElement) node);
					} else if (node.getNodeName().equals("head")) {
						((HTMLDefaultDocument) getDocument()).setHead((HTMLElement) node);
					}
				} else if (this.getNodeName().equals("head")) {
					// PARENT = HTML : meta/title
					if (node.getNodeName().equals("meta") && ((HTMLElement) node).isPresent("charset")) {
						// ottengo il nodo meta charset
						((HTMLDefaultDocument) getDocument()).setMetaTag((HTMLElement) node);
					} else if (node.getNodeName().equals("title")) {
						((HTMLDefaultDocument) getDocument()).setTitle((HTMLElement) node);
					} else if (node.getNodeName().equals("style")) {
						((HTMLDefaultDocument) getDocument()).setStyleTag((HTMLElement) node);

						// controllo se il documento ha uno stile
						if (((HTMLDefaultDocument) getDocument()).getStyleSheet() != null) {
							// okok abbiamo uno stile quindi lo inseriamo nel tag appena creato
							((HTMLDefaultDocument) getDocument()).getStyleTag()
									.setTextContent(((HTMLDefaultDocument) getDocument()).getStyleSheet().toString());
						}
					}
				}
			} else if (node instanceof HTMLComment) {
				((HTMLComment) node).setParentNode(this);
			}
			return node;
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
		if (node instanceof HTMLElement) {
			((HTMLDefaultElement) node).setParentNode(this);

			// qui faccio i vari controlli per ricavarmi i tags minimali
			if (this.getNodeName().equals("html")) {
				// PARENT = HTML : body, head

				if (node.getNodeName().equals("body")) {
					((HTMLDefaultDocument) getDocument()).setBody((HTMLElement) node);
				} else if (node.getNodeName().equals("head")) {
					((HTMLDefaultDocument) getDocument()).setHead((HTMLElement) node);
				}
			} else if (this.getNodeName().equals("head")) {
				// PARENT = HTML : meta/title
				if (node.getNodeName().equals("meta") && ((HTMLElement) node).isPresent("charset")) {
					// ottengo il nodo meta charset
					((HTMLDefaultDocument) getDocument()).setMetaTag((HTMLElement) node);
				} else if (node.getNodeName().equals("title")) {
					((HTMLDefaultDocument) getDocument()).setTitle((HTMLElement) node);
				} else if (node.getNodeName().equals("style")) {
					((HTMLDefaultDocument) getDocument()).setStyleTag((HTMLElement) node);

					// controllo se il documento ha uno stile
					if (((HTMLDefaultDocument) getDocument()).getStyleSheet() != null) {
						// okok abbiamo uno stile quindi lo inseriamo nel tag appena creato
						((HTMLDefaultDocument) getDocument()).getStyleTag()
								.setTextContent(((HTMLDefaultDocument) getDocument()).getStyleSheet().toString());
					}
				}
			}
		} else if (node instanceof HTMLComment) {
			((HTMLComment) node).setParentNode(this);
		}
	}

	@Override
	public NodeList getChildNodes() {
		// TODO Auto-generated method stub
		return this.childNodes;
	}

	@Override
	public Node child(int index) {
		// TODO Auto-generated method stub
		return this.childNodes.item(index);
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
	public String getNodeName() {
		// TODO Auto-generated method stub
		return this.elementName;
	}

	@Override
	public NodeType getNodeType() {
		// TODO Auto-generated method stub
		return NodeType.ELEMENT;
	}

	@Override
	public String getNodeValue() {
		// TODO Auto-generated method stub
		return this.textContent;
	}

	@Override
	public Node getParentNode() {
		// TODO Auto-generated method stub
		return this.parentNode;
	}

	@Override
	public Node getPreviousSibling() {
		// mi viene in mente di prendere il padre dell'elemento
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
	public String getTextContent() {
		// TODO Auto-generated method stub
		return this.textContent;
	}

	@Override
	public boolean hasAttributes() {
		if (this.attributes.isEmpty()) {
			return false;
		} else {
			return true;
		}
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

	// N.B.
	// non fare molto affidamento sul nodo che viene restituito
	// poichè si tratti di un metodo ricorsivo, poi magari ultimare
	// anche questo aspetto
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
				if (newNode instanceof HTMLElement) {
					((HTMLDefaultElement) newNode).setParentNode(this);
				} else if (newNode instanceof HTMLComment) {
					((HTMLComment) newNode).setParentNode(this);
				}
				this.childNodes.addFirstNode(newNode);
			} else if (pos > 0) {

				// okok qui il primo passo da fare è :
				// verificare se in quella data posizione esiste già un elemento

				int index = pos - 1;

				Node previousNode = this.childNodes.item(index);

				// quindi qui dobbiamo sostituire questo elemento prima
				if (newNode instanceof HTMLElement) {
					((HTMLDefaultElement) newNode).setParentNode(this);
				} else if (newNode instanceof HTMLComment) {
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
			// okok qui il primo passo da fare è :
			// verificare se in quella data posizione esiste già un elemento

			int index = pos + 1;

			// qui verifico se l'index raggiunto sia minore del lenght attuale della lista
			if (index > this.childNodes.getLength() - 1) {
				// qui mi basta aggiungere
				if (newNode instanceof HTMLElement) {
					((HTMLDefaultElement) newNode).setParentNode(this);
				} else if (newNode instanceof HTMLComment) {
					((HTMLComment) newNode).setParentNode(this);
				}
				this.childNodes.addNode(newNode);
			} else {
				Node followingNode = this.childNodes.item(index);

				// sostituisco
				if (newNode instanceof HTMLElement) {
					((HTMLDefaultElement) newNode).setParentNode(this);
				} else if (newNode instanceof HTMLComment) {
					((HTMLComment) newNode).setParentNode(this);
				}
				followingNode = this.childNodes.setNode(index, newNode);

				// recursive

				insertAfter(followingNode, newNode);
			}
		}

		return newNode;
	}

	// README
	// questo metodo confronta se i nodi sono uguali come tipo
	// esempio se si tratta di due tags div, returns true
	// ovviamente se si passa come parametro un commento, darà false
	// poichè l'oggetto su cui si sta chiamando è un elemento
	@Override
	public boolean isEqualNode(Node node) {
		// 1 passo : verifico se il nodo ricevuto come parametro è anche un elemento
		// in caso non lo sia, restituisco subito false
		boolean flag = false;
		if (node.getNodeType().equals(this.getNodeType())) {
			// a questo punto si va a ricavare il tipo specifico del nodo

			HTMLElementType type = ((HTMLElement) node).getType();

			if (this.getType().equals(type)) {
				flag = true;
			}

		}
		return flag;
	}

	// questo metodo se elimina il nodo lo restituisce
	// altrimenti returns null
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

	// quindi ne deve passare per essere uguale un nodo a un altro

	@Override
	public HTMLElement setNodeValue(String nodeValue) {
		// TODO Auto-generated method stub
		this.textContent = nodeValue;
		return this;
	}

	@Override
	public HTMLElement setTextContent(String textContent) {
		// TODO Auto-generated method stub
		this.textContent = textContent;
		return this;
	}

	@Override
	public HTMLElementType getType() {
		// TODO Auto-generated method stub
		return this.type;
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
	public Map<String, String> getAttributes() {
		return this.attributes;
	}

	@Override
	public HTMLElement setAttribute(String attr, String val) { // qui devo ipotizzare se si inserisce lo stesso attr più
																// volte
		// 1 passo : verifico se si sta aggiungendo una classe
		this.attributes.put(attr, val);
		return this;
	}

	@Override
	public String getAttribute(String attr) {
		// TODO Auto-generated method stub
		return attr + "=" + this.attributes.get(attr);
	}

	@Override
	public String getAttributeValue(String attr) {
		// TODO Auto-generated method stub
		return this.attributes.get(attr);
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
		Recursion.examines_html(this, htmlCode); // provvisorio, poi gli dobbiamo passare il document
		String result = htmlCode.toString();
		// pulisco il buffer code html
		htmlCode = new StringBuffer();
		return result;
	}

	@Override
	public HTMLDocument getDocument() {
		// TODO Auto-generated method stub
		return this.document;
	}

	// con questo metodo di calcola dinamicamente il path del nodo
	@Override
	public String getPath() {
		return Recursion.examinesForTPath(this);
	}

	@Override
	public String getBaseURI() {
		String baseURI = null;
		if (JjDom.documentURL != null) {
			if (£.extractFormatFromFileName(JjDom.documentURL).equals("html")) {
				baseURI = JjDom.documentURL;
			}
		}
		return baseURI;
	}

	@Override
	public JjDom home() {
		// TODO Auto-generated method stub
		return this.home;
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
	public Node printMarkup() {
		£._O(getMarkup());
		return this;
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
	public HTMLElement getElementById(String elementId) {
		return (HTMLElement) Recursion.examinesForId(elementId, this);
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
	public Elements getDirectChildrenByTag(String tagName) {
		Elements elements = new Elements();
		NodeList listNodes = this.childNodes;
		for (int i = 0; i < listNodes.getLength(); i++) {
			if (listNodes.item(i) instanceof HTMLElement) {
				if (listNodes.item(i).getNodeName().equals(tagName)) {
					elements.add((HTMLElement) listNodes.item(i));
				}
			}
		}
		return elements;
	}

	@Override
	public Elements getElementsByClassName(String className) {
		// TODO Auto-generated method stub
		return Recursion.examinesForClass(className, this);
	}

	@Override
	public Elements getElementsByAttribute(String attribute) {
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

	// questo metodo ha ancora il codice vecchio commentato
	// in modo tale che se in futuro vogliamo prenderne nota
	// possiamo farlo
	@Override
	public Elements getElementsByAttributeValue(String attr, String val) {
		// return HTMLRecursion.examinesForAttributeValue_(attr, val, this, "=");
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
		// TODO Auto-generated method stub
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
	public String getId() {
		if (isPresent("id")) {
			return getAttributeValue("id");
		} else {
			return null;
		}
	}

	@Override
	public HTMLElement setId(String idElement) {
		setAttribute("id", idElement);
		return this;
	}

	// qui abbiamo un po cambiato
	// metodo completato
	@Override
	public Elements getAdiacentSiblingsByTag(String tagName) {
		Elements found = new Elements();
		int index = this.getIndex(); // facciamo partire il ciclo dall'indice dell'elemento
		NodeList listNodes = getParentNode().getChildNodes();
		for (int i = (index + 1); i < listNodes.getLength(); i++) {
			if (listNodes.item(i).getNodeName().equals(tagName)) {
				found.add((HTMLElement) listNodes.item(i));
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
				found.add((HTMLElement) listNodes.item(i));
			}
		}
		return found;
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

	// restituisce - 1 se non trova l'indice
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
	public HTMLElement addClass(String className) {
		if (isPresent("class")) {
			// è già presente una classe
			// quindi prendiamo il valore dell'attributo

			String classValue = getAttributeValue("class");

			classValue = classValue + " " + className;

			// sostituisco il valore

			attributes.replace("class", classValue);
		} else {
			// non è presente alcuna classe nell'elemento
			setAttribute("class", className);
		}
		return this;
	}

	@Override
	public HTMLElement addClasses(String... classes) {
		for (String class_ : classes) {
			addClass(class_);
		}
		return this;
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
	public HTMLElement appendBR() {
		appendChild(JjDom.document.createElement(HTMLElementType.BR));
		return this;
	}

	@Override
	public HTMLElement removeAttribute(String attr) {
		String attribute = this.attributes.remove(attr);
		if (attribute != null) {
			return this;
		} else {
			return null;
		}
	}

	@Override
	public HTMLElement removeClass(String className) {
		if (isPresent("class")) {
			String attributeValue = getAttributeValue("class");
			if (attributeValue.contains(className)) {
				attributeValue = attributeValue.replaceAll(className, "").trim();
				// elimino l'attributo
				HTMLElement thisElement = removeAttribute("class");
				// verifico se l'eliminazione dell'attributo è andata a buon fine
				if (thisElement != null) {
					// okok ultimo controllo: se il valore c'è
					// mi ricrei l'attributo class
					if (!attributeValue.isEmpty()) {
						return setAttribute("class", attributeValue);
					} else {
						// qui restituisco l'elemento
						// tanto abbiamo provveduto
						// a eliminare l'attributo, dal momento
						// che non c'èrano + classi al suo interno come valore
						return this;
					}
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

	// da segnalare ...
	@Override
	public HTMLElement removeClasses(String... classes) {
		for (int i = 0; i < classes.length; i++) {
			removeClass(classes[i]);
		}
		return this; // in qualsiasi caso, restituisce l'elemento
	}

	@Override
	public HTMLElement addCssProps(String... cssProps) {
		for (int i = 0; i < cssProps.length; i++) {
			String cssProp = cssProps[i];
			addCssProp(cssProp);
		}
		return this;
	}

	@Override
	public HTMLElement addCssProp(String cssProp) {
		if (isPresent("style")) {
			// prendo il valore attuale css
			String property = cssProp.substring(0, cssProp.indexOf(":")).trim();
			String attrValue = getAttributeValue("style");
			// faccio splittare le proprietà
			boolean notExist = true;
			String[] split = attrValue.split(";");
			for (int i = 0; i < split.length; i++) {
				String prop, value;
				prop = split[i].substring(0, split[i].indexOf(":")).trim();
				value = split[i].substring(split[i].indexOf(":")).replace(":", "").trim();
				if (prop.equals(property)) {
					attrValue = attrValue.replace(split[i] + ";", cssProp);
					setAttribute("style", attrValue);
					notExist = false;
					break;
				}
			}
			if (notExist) {
				return replaceAttributeValue("style", attrValue + cssProp);
			}
			return this;
		} else {
			return setAttribute("style", cssProp);
		}
	}

	@Override
	public HTMLElement replaceAttributeValue(String attr, String newValue) {
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
	public String getCompletePath() {
		String completePath = null;
		if (getBaseURI() != null) {
			completePath = getBaseURI();
		}
		completePath = completePath + "/" + getPath();
		return completePath;
	}

	// version 1.0.7
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
				if (node instanceof HTMLElement) {
					if (nodeName.startsWith("#")) {
						if (((HTMLElement) node).isPresent("id")) {
							if (((HTMLElement) node).getId().equals(nodeName.replace("#", ""))) {
								currentNode = node;
								found = true;
								break;
							}
						}
					} else if (nodeName.startsWith(".")) {
						if (((HTMLElement) node).isPresent("class")) {
							String classAttributeValue = ((HTMLElement) node).getAttributeValue("class");
							String[] classes = classAttributeValue.split(" ");
							for (int k = 0; k < classes.length; k++) {
								if (classes[k].trim().equals(nodeName.replace(".", ""))) {
									currentNode = node;
									found = true;
									break;
								}
							}
						}
					}
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

	@Override
	public HTMLElement hide() {
		return addCssProp("display: none;");
	}

	@Override
	public HTMLElement show() {
		// TODO Auto-generated method stub
		return addCssProp("display: block;");
	}

	@Override
	public String getCssPropValue(String onlyProp) {
		String prop = getCssProp(onlyProp);
		if (prop != null) {
			int index = prop.indexOf(":");
			return prop.substring(index).replace(":", "").trim();
		} else {
			return null;
		}
	}

	@Override
	public String getCssProp(String onlyProp) {
		String prop = null;
		if (isPresent("style")) {
			String attrValue = getAttributeValue("style");
			String[] split = attrValue.split(";");
			for (int i = 0; i < split.length; i++) {
				String _prop_, _value_ = null;
				_prop_ = split[i].substring(0, split[i].indexOf(":")).trim();
				_value_ = split[i].substring(split[i].indexOf(":")).replace(":", "").trim();
				if (_prop_.equals(onlyProp)) {
					prop = split[i];
					break; // trovata
				}
			}
		}
		return prop;
	}

	@Override
	public boolean hasCssProp(String onlyProp) {
		boolean has = false;
		if (isPresent("style")) {
			String attrValue = getAttributeValue("style");
			String[] split = attrValue.split(";");
			for (int i = 0; i < split.length; i++) {
				String _prop_, _value_ = null;
				_prop_ = split[i].substring(0, split[i].indexOf(":")).trim();
				_value_ = split[i].substring(split[i].indexOf(":")).replace(":", "").trim();
				if (_prop_.equals(onlyProp)) {
					has = true;
					break;
				}
			}
		}
		return has;
	}
}
