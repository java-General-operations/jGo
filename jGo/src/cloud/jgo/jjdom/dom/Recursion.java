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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;

import javax.lang.model.element.ElementKind;
import javax.swing.JOptionPane;

import cloud.jgo.£;
import cloud.jgo.io.File;
import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.dom.nodes.Comment;
import cloud.jgo.jjdom.dom.nodes.Element;
import cloud.jgo.jjdom.dom.nodes.Elements;
import cloud.jgo.jjdom.dom.nodes.Node;
import cloud.jgo.jjdom.dom.nodes.Node.NodeType;
import cloud.jgo.jjdom.dom.nodes.NodeList;
import cloud.jgo.jjdom.dom.nodes.html.HTMLComment;
import cloud.jgo.jjdom.dom.nodes.html.HTMLDefaultElement;
import cloud.jgo.jjdom.dom.nodes.html.HTMLDocument;
import cloud.jgo.jjdom.dom.nodes.html.HTMLElement;
import cloud.jgo.jjdom.dom.nodes.html.HTMLElement.HTMLElementType;
import cloud.jgo.jjdom.dom.nodes.xml.XMLDocument;
import cloud.jgo.jjdom.dom.nodes.xml.XMLElement;

/**
 * N:B: Devo risolvere una questione e se io gli passo per esempio conoscendo
 * questa classe non il nodo root, per esempio se chiamassi il metodi che mi
 * ottiene gli elementi per classe e l'elemento nodo che gli passo, ha anche
 * questa classe, allo stato attuale non me lo controlla questi metodi perchè si
 * da per scontato che si passa il root node quindi poi ultimare
 */
// questa qui poi diventerà una classe interna
public abstract class Recursion {
	public static void examines_html(Node node, StringBuffer htmlCode) {
		// for doctype from here to @
		String key = null;
		if (node instanceof Element) {
			// qui che sappiamo che si tratta di un elemento html
			// gestiamo gli attributi
			if (((Element) node).hasAttributes()) {
				// System.out.println("L'elemento "+node.getNodeName()+" ha i seguenti attributi
				// :");
				Map<String, String> attributes = ((Element) node).getAttributes();
				Iterator<Entry<String, String>> iterator = attributes.entrySet().iterator();
				while (iterator.hasNext()) {
					Map.Entry<java.lang.String, java.lang.String> entry = (Map.Entry<java.lang.String, java.lang.String>) iterator
							.next();
					key = entry.getKey();
					// casomai eliminare da qui a @
					if (((HTMLDefaultElement) node).getStartTag().contains(" " + key + "='" + entry.getValue() + "'")) {
						((HTMLDefaultElement) node).setStartTag(((HTMLDefaultElement) node).getStartTag()
								.replace(" " + key + "='" + entry.getValue() + "'", ""));
					}
					// @ - in tanto
					((HTMLDefaultElement) node).setStartTag(((HTMLDefaultElement) node).getStartTag().replace(">", ""));
					((HTMLDefaultElement) node).setStartTag(((HTMLDefaultElement) node).getStartTag() + " " + key + "='"
							+ entry.getValue() + "'" + ">");
					// System.out.println(key+":"+entry.getValue());
				}
			}
			if (((HTMLElement) node).getType().equals(HTMLElementType.HTML)) {
				// qui ottengo il documento del nodo
				HTMLDocument doc = (HTMLDocument) ((Element) node).getDocument();
				if (doc.doctypeIsPresent()) {
					// inserisco il doctype
					htmlCode.append(£.colors("<",Element.tag_color)+"!DOCTYPE html"+£.colors(">",Element.tag_color) + "\n");
				}
			}
		}
		// @
		if (node.getTextContent() != null) {
			if (node.getNodeType().equals(NodeType.ELEMENT)) {
//				htmlCode.append(((HTMLDefaultElement) node).getStartTag()); - questa era
				String colorStartTag = £.colors("<",Element.tag_color)+((HTMLDefaultElement) node).getStartTag().replace("<","").replace(">","")+£.colors(">",Element.tag_color);
				htmlCode.append(colorStartTag);
				
			} else if (node.getNodeType().equals(NodeType.COMMENT)) {
				//htmlCode.append(((HTMLComment) node).getStartTag()); - questa era
				String colorStartTag = £.colors("<",Element.tag_color)+((HTMLComment) node).getStartTag().replace("<","").replace(">","")+£.colors(">",Element.tag_color);
				htmlCode.append(colorStartTag);
			}
			htmlCode.append(node.getTextContent());
		} else {
			if (node instanceof Element) {
//				htmlCode.append(((HTMLDefaultElement) node).getStartTag() + "\n"); - questa era
				String colorStartTag = £.colors("<",Element.tag_color)+((HTMLDefaultElement) node).getStartTag().replace("<","").replace(">","")+£.colors(">",Element.tag_color)+ "\n";
				htmlCode.append(colorStartTag);
			}
		}

		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			examines_html(node.getChildNodes().item(i), htmlCode);
		}

		// chiudo il tag
		if (node instanceof Element) {
			if (((HTMLElement) node).getType() != null) {
				// solo se ha il tag di chisura lo inseriamo nel codice html
				if (((HTMLElement) node).getType().hasClosingTag()) {
					//htmlCode.append(((HTMLDefaultElement) node).getEndTag() + "\n"); - era questo
					String colorEndTag = £.colors("</",Element.tag_color)+((HTMLDefaultElement) node).getEndTag().replace("</","").replace(">","")+£.colors(">",Element.tag_color)+ "\n";
					htmlCode.append(colorEndTag);
				}
			} else {
				// di sicuro se il nodo non ha un tipo di riferimento
				// chiudiamo in maniera standart : con il tag di chiusura
				//htmlCode.append(((HTMLDefaultElement) node).getEndTag() + "\n"); - era questo
				String colorEndTag = £.colors("</",Element.tag_color)+((HTMLDefaultElement) node).getEndTag().replace("</","").replace(">","")+£.colors(">",Element.tag_color)+ "\n";
				htmlCode.append(colorEndTag);
			}
		} else {

			// qui invece significa che non è un elemento html
			// quindi deve essere per forza un commento, almeno per il momento

			// magari per sicurezza:controllo che sia cosi

			if (node instanceof HTMLComment) {
				//htmlCode.append(((HTMLComment) node).getEndTag() + "\n"); - era questo
				String colorEndTag = £.colors("<",Element.tag_color)+((HTMLComment) node).getEndTag().replace("<","").replace(">","")+£.colors(">",Element.tag_color);
				htmlCode.append(colorEndTag);
			}
		}
	}

	public static void examines_xml(Node node, StringBuffer xmlCode, String charset) {
		// for doctype from here to @
		if (node instanceof XMLDocument) {
			xmlCode.append(
					"<?xml version=" + £.escp(XMLDocument.XML_VERSION) + " encoding=" + £.escp(charset) + "?>\n");
		}
		String key = null;
		if (node instanceof Element) {
			// qui che sappiamo che si tratta di un elemento html
			// gestiamo gli attributi
			if (((Element) node).hasAttributes()) {
				// System.out.println("L'elemento "+node.getNodeName()+" ha i seguenti attributi
				// :");
				Map<String, String> attributes = ((Element) node).getAttributes();
				Iterator<Entry<String, String>> iterator = attributes.entrySet().iterator();
				while (iterator.hasNext()) {
					Map.Entry<java.lang.String, java.lang.String> entry = (Map.Entry<java.lang.String, java.lang.String>) iterator
							.next();
					key = entry.getKey();
					// casomai eliminare da qui a @
					if (((XMLElement) node).getStartTag().contains(" " + key + "='" + entry.getValue() + "'")) {
						((XMLElement) node).setStartTag(((XMLElement) node).getStartTag()
								.replace(" " + key + "='" + entry.getValue() + "'", ""));
					}
					// @ - in tanto
					((XMLElement) node).setStartTag(((XMLElement) node).getStartTag().replace(">", ""));
					((XMLElement) node).setStartTag(
							((XMLElement) node).getStartTag() + " " + key + "='" + entry.getValue() + "'" + ">");
					// System.out.println(key+":"+entry.getValue());
				}
			}
		}
		// @
		if (node.getTextContent() != null) {
			if (node.getNodeType().equals(NodeType.ELEMENT)) {
				xmlCode.append(((XMLElement) node).getStartTag());
			} else if (node.getNodeType().equals(NodeType.COMMENT)) {
				xmlCode.append(((HTMLComment) node).getStartTag());
			}
			xmlCode.append(node.getTextContent());
		} else {
			if (node instanceof Element) {
				xmlCode.append(((XMLElement) node).getStartTag() + "\n");
			}
		}
		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			examines_xml(node.getChildNodes().item(i), xmlCode, charset);
		}
		// chiudo il tag
		if (node instanceof Element) {
			// di sicuro se il nodo non ha un tipo di riferimento
			// chiudiamo in maniera standart : con il tag di chiusura
			xmlCode.append(((XMLElement) node).getEndTag() + "\n");
		} else {
			// qui invece significa che non è un elemento html
			// quindi deve essere per forza un commento, almeno per il momento
			// magari per sicurezza:controllo che sia cosi
			if (node instanceof Comment) {
				xmlCode.append(((HTMLComment) node).getEndTag() + "\n");
			}
		}
	}

	// what : text | html
	private static StringBuffer buffer = new StringBuffer();

	public static String getTexts(Elements elements) {
		final String OPER = "text";
		for (int i = 0; i < elements.size(); i++) {
			collects(OPER, elements.get(i));
		}
		StringBuffer newBuffer = buffer;
		buffer = new StringBuffer();
		return newBuffer.toString();
	}

	public static String getHtml(Element element) {
		final String OPER = "html";
		collects(OPER, element);
		StringBuffer newBuffer = buffer;
		buffer = new StringBuffer();
		return newBuffer.toString();
	}

	private static String collects(String what, Node node) {
		switch (what) {
		case "text":
			if (node.getTextContent() != null) {
				buffer.append(node.getTextContent() + "\n");
			}
			// controllo se il nodo ha figli
			if (node.hasChildNodes()) {
				for (int i = 0; i < node.getChildNodes().getLength(); i++) {
					collects(what, node.getChildNodes().item(i));
				}
			}
			break;
		case "html":
			buffer.append(node.getMarkup());

			// aggiornare qui

			if (node.hasChildNodes()) {
				for (int i = 0; i < node.getChildNodes().getLength(); i++) {
					collects(what, node.getChildNodes().item(i));
				}
			}
			break;
		}
		return buffer.toString();
	}

	private static Element found = null;
	// continuare da qui, facendo un metodo ricorsivo che mi fa ottenere
	// un elemento tramite id

	public static void helpForId(String idElement, Node node) {
		NodeList listNodes = node.getChildNodes();
		for (int i = 0; i < listNodes.getLength(); i++) {
			Node currentNode = listNodes.item(i);
			if (currentNode instanceof Element) {
				if (((Element) currentNode).hasAttributes()) {
					// okok l'elemento ha attributi
					String idValue = ((Element) currentNode).getAttributeValue("id");
					if (idValue != null) {
						// qui verifico se è lo stesso id
						if (idValue.equals(idElement)) {
							found = (Element) currentNode;
							break;
						}
					}
				}
			}
			if (!(currentNode instanceof HTMLComment)) {
				// se non è una instanza del commento
				// recursive
				helpForId(idElement, currentNode);
			}
		}
	}

	public static Element examinesForId(String idElement, Node node) {
		helpForId(idElement, node);
		Element element = found;
		found = null;
		return element;
	}

	/*
	 * CLASS fino a @
	 */

	private static Elements classListNodes = new Elements();

	public static Elements examinesForClass(String className, Node node) {

		// chiamo il metodo di supporto

		helpForClass(className, node);

		// qui poi aggiornare con una costante apposita

		Elements elements = classListNodes;

		// reinizializzo di nuovo la variabile statica

		classListNodes = new Elements();

		// restituisco la copia della lista

		return elements;

	}

	private static void helpForClass(String className, Node node) {
		NodeList listNodes = node.getChildNodes();
		for (int i = 0; i < listNodes.getLength(); i++) {
			Node currentNode = listNodes.item(i);
			if (currentNode instanceof Element) {
				if (((Element) currentNode).hasAttributes()) {
					// okok l'elemento ha attributi
					String classValue = ((Element) currentNode).getAttributeValue("class");
					if (classValue != null) {
						if (classValue.equals(className)) {
							// quindi carico i nodi con la classe nella lista
							classListNodes.add((Element) currentNode);
						}
					}
				}
			}
			// ricorsività
			helpForClass(className, currentNode);
		}
	}

	/*
	 * @
	 */

	/*
	 * TAG fino a @
	 */

	private static Elements tagListNodes = new Elements();

	public static Elements examinesForTag(String tagName, Node node) {

		// chiamo il metodo di supporto

		helpForTag(tagName, node);

		// qui poi aggiornare con una costante apposita

		Elements elements = tagListNodes;

		// reinizializzo di nuovo la variabile statica

		tagListNodes = new Elements();

		// restituisco la copia della lista

		return elements;

	}

	private static void helpForTag(String tagName, Node node) {
		NodeList listNodes = node.getChildNodes();
		for (int i = 0; i < listNodes.getLength(); i++) {
			Node currentNode = listNodes.item(i);
			if (currentNode.getNodeName().equals(tagName)) {
				tagListNodes.add((Element) currentNode);
			}
			// ricorsività
			helpForTag(tagName, currentNode);
		}
	}

	/*
	 * @
	 */

	/*
	 * Name fino a @
	 */

	private static Elements nameListNodes = new Elements();

	public static Elements examinesForName(String name, Node node) {
		// chiamo il metodo di supporto

		helpForName(name, node);

		// qui poi aggiornare con una costante apposita

		Elements elements = nameListNodes;

		// reinizializzo di nuovo la variabile statica

		nameListNodes = new Elements();

		// restituisco la copia della lista

		return elements;

	}

	private static void helpForName(String name, Node node) {
		NodeList listNodes = node.getChildNodes();
		for (int i = 0; i < listNodes.getLength(); i++) {
			Node currentNode = listNodes.item(i);
			if (currentNode.getNodeType().equals(NodeType.ELEMENT)) {
				Element element = (Element) currentNode;
				if (element.isPresent("name")) {
					String currentName = element.getAttributeValue("name");
					if (name.equals(currentName)) {
						nameListNodes.add(element);
					}
				}
			}
			// ricorsività
			helpForName(name, currentNode);
		}
	}

	/*
	 * @
	 */
	/*
	 * TAG fino a @
	 */
	private static Elements typeListNodes = new Elements();

	public static Elements examinesForType(HTMLElementType type, Node node) {

		// chiamo il metodo di supporto

		helpForType(type, node);

		// qui poi aggiornare con una costante apposita

		Elements elements = typeListNodes;

		// reinizializzo di nuovo la variabile statica

		typeListNodes = new Elements();

		// restituisco la copia della lista

		return elements;

	}

	private static void helpForType(HTMLElementType type, Node node) {
		NodeList listNodes = node.getChildNodes();
		for (int i = 0; i < listNodes.getLength(); i++) {
			Node currentNode = listNodes.item(i);
			if (currentNode instanceof Element) {
				if ((((HTMLElement) currentNode).getType().toString()).equals(type.toString())) {
					typeListNodes.add((Element) currentNode);
				}
			}
			// ricorsività
			helpForType(type, currentNode);
		}
	}

	/*
	 * @
	 */

	/**
	 * @ da qui a @ : path
	 */

	private static StringBuffer path = new StringBuffer();

	public static String examinesForTPath(Node node) {

		helpForPath(node);

		// quindi trasferisco il valore della var statica su un altra variabile

		String finalPath = path.toString();

		// qui faccio lo split

		String[] split = null;

		split = finalPath.split(Pattern.quote(File.separator));

		List<String> list = new ArrayList<>();

		for (int i = split.length; i > 0; i--) {
			if (i > 0) {
				list.add(split[i - 1] + File.separator);
			} else {
				list.add(split[i - 1]);
			}
		}
		StringBuffer bufferINtern = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			bufferINtern.append(list.get(i));
		}

		finalPath = bufferINtern.toString();

		// sappiamo che l'ultimo carattere è un separatore e vogliamo eliminarlo

		path = new StringBuffer();

		return finalPath;

	}

	private static void helpForPath(Node node) {
		path.append(node.getNodeName() + File.separator);
		Node parent = node.getParentNode();
		if (!node.getNodeName().equalsIgnoreCase("html")) { // verifico che il nodo ricevuto non sia il nodo root html
			if (parent != null) {
				// aggiungo al path
				path.append(parent.getNodeName() + File.separator);

				// ricorsività

				helpForPath(parent.getParentNode());
			}
		} else {
			// in caso contrario finisce il metodo
			path.append(parent.getNodeName() + File.separator);
			return;
		}
	}

	/**
	 * @
	 */

	/**
	 * metodi recorsivo per eliminamento nodo dal documento
	 */

	private static Node deleted = null;

	public static Node removeNode(Node node, Node rootNode) {
		deleted = null;
		NodeList listNodes = rootNode.getChildNodes();

		for (int i = 0; i < listNodes.getLength(); i++) {

			Node currentNode = listNodes.item(i);

			if (node.equals(currentNode)) {

				// prendo il genitore

				Node parent = currentNode.getParentNode();

				deleted = parent.removeNode(node);

				break;
			}

			// se arriva qui vuol dire che ancora non si è trovato il nodo
			// quindi richiamo il metodo

			removeNode(node, currentNode); // questa volta però come root nodo ci passo il nodo corrente che sta
											// iterando

		}
		return deleted;
	}

	/**
	 * Metodo ricorsivo per sostituzione child dal documento
	 */
	private static Node replaced = null;

	public static Node replaceChild(Node newNode, Node oldNode, Node rootNode) {
		replaced = null;
		NodeList listNodes = rootNode.getChildNodes();

		for (int i = 0; i < listNodes.getLength(); i++) {

			Node currentNode = listNodes.item(i);

			if (oldNode.equals(currentNode)) {

				// prendo il genitore

				Node parent = currentNode.getParentNode();

				replaced = parent.replaceChild(newNode, oldNode);

				break;
			}

			// se arriva qui vuol dire che ancora non si è trovato il nodo
			// quindi richiamo il metodo

			replaceChild(newNode, oldNode, currentNode); // questa volta però come root nodo ci passo il nodo corrente
															// che sta iterando

		}
		return replaced;
	}

	/*
	 * Metodo ricorsivo per l'ottenimento dei commenti del documento Qui dobbiamo
	 * gestire il fatto che il set di commenti non viene resettato a nessuna parte,
	 * quindi allo stato attuale, viene riempito sempre, senza però mai essere
	 * svuotato, quindi RISOLVERE
	 */
	private static Set<Comment> comments = new HashSet<>();

	public static void resetCommentsSet() {
		comments = new HashSet<>();
	}

	public static Set<Comment> getAllComments(Node rootNode) {
		NodeList listNodes = rootNode.getChildNodes();
		for (int i = 0; i < listNodes.getLength(); i++) {
			Node node = listNodes.item(i);
			if (node instanceof Comment) {
				comments.add((Comment) node);
			} else {
				// se non è un commento, vuol dire che è un nodo
				// e quindi dentro ci posso essere altri commenti
				// per cui richiamo il metodo
				getAllComments(node); // recursive
			}
		}
		return comments;
	}

	private static List<Comment> commentsList = new ArrayList<>();

	public static void resetCommentsList() {
		commentsList = new ArrayList<>();
	}

	public static List<Comment> getListComments(Node rootNode) {
		NodeList listNodes = rootNode.getChildNodes();
		for (int i = 0; i < listNodes.getLength(); i++) {
			Node node = listNodes.item(i);
			if (node instanceof Comment) {
				commentsList.add((Comment) node);
			} else {
				// se non è un commento, vuol dire che è un nodo
				// e quindi dentro ci posso essere altri commenti
				// per cui richiamo il metodo
				getListComments(node); // recursive
			}
		}
		return commentsList;
	}

	/*
	 * For attribute fino a @
	 */

	private static Elements attributeNodes = new Elements();

	public static Elements examinesForAttribute(String attribute, Node node) {

		// chiamo il metodo di supporto

		helpForAttribute(attribute, node);

		// qui poi aggiornare con una costante apposita

		Elements elements = attributeNodes;

		// reinizializzo di nuovo la variabile statica

		attributeNodes = new Elements();

		// restituisco la copia della lista

		return elements;

	}

	private static void helpForAttribute(String attribute, Node node) {
		if (node instanceof Element) {
			if (((Element) node).hasAttributes()) {
				if (((Element) node).isPresent(attribute)) {
					if (!attributeNodes.contains(node)) {
						attributeNodes.add((Element) node);
					}
				}
			}
		}
		NodeList listNodes = node.getChildNodes();
		for (int i = 0; i < listNodes.getLength(); i++) {
			Node currentNode = listNodes.item(i);
			if (currentNode instanceof Element) {
				if (((Element) currentNode).hasAttributes()) {
					if (((Element) currentNode).isPresent(attribute)) {
						attributeNodes.add((Element) currentNode);
					}
				}
			}
			// ricorsività
			helpForAttribute(attribute, currentNode);
		}
	}

	/*
	 * @
	 */

	/*
	 * For attribute fino a @
	 */

	private static Elements attributeValueNodes = new Elements();

	public static Elements examinesForAttributeValue(String value, Node node) {

		// chiamo il metodo di supporto

		helpForAttributeValue(value, node);

		// qui poi aggiornare con una costante apposita

		Elements elements = attributeValueNodes;

		// reinizializzo di nuovo la variabile statica

		attributeValueNodes = new Elements();

		// restituisco la copia della lista

		return elements;

	}

	private static void helpForAttributeValue(String value, Node node) {
		if (node instanceof Element) {
			if (((Element) node).hasAttributes()) {
				Map<String, String> attributes = ((Element) node).getAttributes();
				Iterator<Entry<String, String>> iterator = attributes.entrySet().iterator();
				while (iterator.hasNext()) {
					Map.Entry<java.lang.String, java.lang.String> entry = (Map.Entry<java.lang.String, java.lang.String>) iterator
							.next();
					String attr = entry.getKey();
					String val = entry.getValue();
					if (val.equals(value)) {
						attributeValueNodes.add((Element) node);
					}
				}
			}
		}
		NodeList listNodes = node.getChildNodes();
		for (int i = 0; i < listNodes.getLength(); i++) {
			Node currentNode = listNodes.item(i);
			if (currentNode instanceof Element) {
				if (((Element) currentNode).hasAttributes()) {
					Map<String, String> attributes = ((Element) currentNode).getAttributes();
					Iterator<Entry<String, String>> iterator = attributes.entrySet().iterator();
					while (iterator.hasNext()) {
						Map.Entry<java.lang.String, java.lang.String> entry = (Map.Entry<java.lang.String, java.lang.String>) iterator
								.next();
						String attr = entry.getKey();
						String val = entry.getValue();
						if (val.equals(value)) {
							attributeValueNodes.add((Element) currentNode);
						}
					}
				}
			}
			// ricorsività
			helpForAttributeValue(value, currentNode);
		}
	}

	/*
	 * @
	 */

	/*
	 * Da qui in poi abbiamo il metodo che unisce tutti questi metodi separati
	 * "superficialmente" fino a #
	 */

	private static Elements attributeValueNodes_ = new Elements();

	public static Elements examinesForAttributeValue_(String attribute, String value, Node node, String operator) {
		// chiamo il metodo di supporto
		helpForAttributeValue_(attribute, value, node, operator);

		// qui poi aggiornare con una costante apposita

		Elements elements = attributeValueNodes_;

		// reinizializzo di nuovo la variabile statica

		attributeValueNodes_ = new Elements();

		// restituisco la copia della lista

		return elements;
	}

	private static void check(Node node, String attribute, String value, String operator) {
		if (node instanceof Element) {
			if (((Element) node).hasAttributes()) {
				if (((Element) node).isPresent(attribute)) {
					String val = ((Element) node).getAttributeValue(attribute);
					switch (operator) {
					case "=":
						if (val.equals(value)) {
							// lo abbiamo
							if (!attributeValueNodes_.contains(node)) {
								attributeValueNodes_.add((Element) node);
							}
						}
						break;
					case cloud.jgo.jjdom.css.CSSSelector.DIFFERENT_OPERATOR:
						if (!val.equals(value)) {
							// lo abbiamo
							if (!attributeValueNodes_.contains(node)) {
								attributeValueNodes_.add((Element) node);
							}
						}
						break;
					case cloud.jgo.jjdom.css.CSSSelector.STARTS_WITH_OPERATOR:
						if (val.startsWith(value)) {
							// lo abbiamo
							if (!attributeValueNodes_.contains(node)) {
								attributeValueNodes_.add((Element) node);
							}
						}
						break;
					case cloud.jgo.jjdom.css.CSSSelector.ENDS_WITH_OPERATOR:
						if (val.endsWith(value)) {
							// lo abbiamo
							if (!attributeValueNodes_.contains(node)) {
								attributeValueNodes_.add((Element) node);
							}
						}
						break;
					case cloud.jgo.jjdom.css.CSSSelector.CONTAINS_OPERATOR:
						if (val.contains(value)) {
							// lo abbiamo
							if (!attributeValueNodes_.contains(node)) {
								attributeValueNodes_.add((Element) node);
							}
						}
						break;
					}
				}
			}
		}
	}

	// devo assicurarmi che questo metodo, con questo controllo iniziale
	// va bene o meno, secondo me c'è l rischio che si possono inserire + vlt
	// gli elementi, quindi testare in maniera concentrata
	private static void helpForAttributeValue_(String attribute, String value, Node node, String operator) {
		// controllo la questione attributo sul nodo ricevuto
		check(node, attribute, value, operator);
		// e qui controllo i children
		NodeList listNodes = node.getChildNodes();
		for (int i = 0; i < listNodes.getLength(); i++) {
			Node currentNode = listNodes.item(i);
			check(currentNode, attribute, value, operator);
			// ricorsività
			helpForAttributeValue_(attribute, value, currentNode, operator);
		}
	}

	/*
	 * #
	 */

	/*
	 * Da qui a @ : metodo che ottiene tutti gli elementi del documento
	 */
	private static Elements allElements = new Elements();

	public static Elements getAllElements(Node root) {
		allElements = support(root);
		Elements elements = allElements;
		allElements = new Elements();
		return elements;
	}

	private static Elements support(Node root) {
		// okok inserisco l'elemento nella lista
		if (root instanceof Element) {
			allElements.add((Element) root);
		}
		NodeList list = root.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			support(list.item(i));
		}
		return allElements;
	}
}
