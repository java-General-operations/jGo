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

import cloud.jgo.io.File;
import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.dom.nodes.HTMLComment;
import cloud.jgo.jjdom.dom.nodes.HTMLDocument;
import cloud.jgo.jjdom.dom.nodes.HTMLElement;
import cloud.jgo.jjdom.dom.nodes.HTMLElements;
import cloud.jgo.jjdom.dom.nodes.HTMLNodeList;
import cloud.jgo.jjdom.dom.nodes.Node;
import cloud.jgo.jjdom.dom.nodes.HTMLElement.HTMLElementType;
import cloud.jgo.jjdom.dom.nodes.Node.HTMLNodeType;
import cloud.jgo.jjdom.dom.nodes.concrete.HTMLDefaultElement;
/**
 * N:B:
 * Devo risolvere una questione
 * e se io gli passo per esempio conoscendo questa classe
 * non il nodo root, per esempio se chiamassi il metodi che mi ottiene gli elementi per classe
 * e l'elemento nodo che gli passo, ha anche questa classe, allo stato attuale
 * non me lo controlla questi metodi perchè si da per scontato che si passa il root node
 * quindi poi ultimare
 */
// questa qui poi diventerà una classe interna
public abstract class HTMLRecursion {
	public static void examines(Node node,StringBuffer htmlCode){
		// for doctype from here to @
		 String key = null ;
		if (node instanceof HTMLElement) {
			// qui che sappiamo che si tratta di un elemento html
			// gestiamo gli attributi
			if (((HTMLElement)node).hasAttributes()) {
				//System.out.println("L'elemento "+node.getNodeName()+" ha i seguenti attributi :");
				Map<String,String>attributes = ((HTMLElement)node).getAttributes();
				Iterator<Entry<String, String>>iterator = attributes.entrySet().iterator();
				while (iterator.hasNext()) {
					Map.Entry<java.lang.String, java.lang.String> entry = (Map.Entry<java.lang.String, java.lang.String>) iterator
							.next();
					key = entry.getKey();
					// casomai eliminare da qui a @
						if (((HTMLDefaultElement)node).getStartTag().contains(" "+key+"='"+entry.getValue()+"'")) {
							((HTMLDefaultElement)node).setStartTag(((HTMLDefaultElement)node).getStartTag().replace(" "+key+"='"+entry.getValue()+"'",""));
						}
						// @ - in tanto
						((HTMLDefaultElement)node).setStartTag(((HTMLDefaultElement)node).getStartTag().replace(">",""));
						((HTMLDefaultElement)node).setStartTag(((HTMLDefaultElement)node).getStartTag()+
						" "+key+"='"+entry.getValue()+"'"+">");	
						//System.out.println(key+":"+entry.getValue());
				}
			}
			if(((HTMLElement)node).getType().equals(HTMLElementType.HTML)){
				// qui ottengo il documento del nodo
				HTMLDocument doc = ((HTMLElement) node).getDocument();
				if (doc.doctypeIsPresent()) {
					// inserisco il doctype 
					htmlCode.append("<!DOCTYPE html>"+"\n");
				}
			}
		}
		// @
		if(node.getTextContent()!=null){
			if(node.getNodeType().equals(HTMLNodeType.HTML_ELEMENT)){
				 htmlCode.append(((HTMLDefaultElement)node).getStartTag());
			}
			else if(node.getNodeType().equals(HTMLNodeType.HTML_COMMENT)){
				htmlCode.append(((HTMLComment)node).getStartTag());
			}
			htmlCode.append(node.getTextContent());
		}
		else{
			 if (node instanceof HTMLElement) {
				 htmlCode.append(((HTMLDefaultElement)node).getStartTag()+"\n");
			}
		}
	
		 for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			 examines(node.getChildNodes().item(i),htmlCode);
		 }
	 
	 // chiudo il tag
	if (node instanceof HTMLElement) {
		if (((HTMLElement)node).getType()!=null) {
			// solo se ha il tag di chisura lo inseriamo nel codice html
			if (((HTMLElement)node).getType().hasClosingTag()) {
				// per il momento inserisco una @ per capire 
				htmlCode.append(((HTMLDefaultElement)node).getEndTag()+"\n");
			}
		}
		else{
			// di sicuro se il nodo non ha un tipo di riferimento
			// chiudiamo in maniera standart : con il tag di chiusura
			 htmlCode.append(((HTMLDefaultElement)node).getEndTag()+"\n");
		}
	}
	else{
		
		// qui invece significa che non è un elemento html
		// quindi deve essere per forza un commento, almeno per il momento 
		
		// magari per sicurezza:controllo che sia cosi 
		
		if (node instanceof HTMLComment) {
			 htmlCode.append(((HTMLComment)node).getEndTag()+"\n");
		}
	}
	}
	
	// what : text | html
	private static StringBuffer buffer = new StringBuffer();
	public static String getTexts(HTMLElements elements){
		final String OPER = "text";
		for (int i = 0; i < elements.size(); i++) {
			collects(OPER,elements.get(i));
		}
		StringBuffer newBuffer = buffer ;
		buffer = new StringBuffer();
		return newBuffer.toString();
	}
	
	public static String getHtml(HTMLElement element){
		final String OPER = "html";
		collects(OPER,element);
		StringBuffer newBuffer = buffer ;
		buffer = new StringBuffer();
		return newBuffer.toString();
	}
	private static String collects(String what,Node node){
		switch(what){
		case "text":
			if (node.getTextContent()!=null) {
				buffer.append(node.getTextContent()+"\n");
			}
			// controllo se il nodo ha figli
			if (node.hasChildNodes()) {
				for (int i = 0; i < node.getChildNodes().getLength(); i++) {
					collects(what,node.getChildNodes().item(i));
				}
			}
			break ;
		case "html":
			buffer.append(node.getMarkup());
			
			// aggiornare qui
			
			if (node.hasChildNodes()) {
				for (int i = 0; i < node.getChildNodes().getLength(); i++) {
					collects(what,node.getChildNodes().item(i));
				}
			}
			break ;
		}
		return buffer.toString();
	}
	
	
	
	
	
	private static HTMLElement found = null ;
	// continuare da qui, facendo un metodo ricorsivo che mi fa ottenere 
	// un elemento tramite id
	
	public static void helpForId(String idElement,Node node){
		HTMLNodeList listNodes = node.getChildNodes();
		for (int i = 0; i < listNodes.getLength(); i++) {
			Node currentNode = listNodes.item(i);
			if (currentNode instanceof HTMLElement) {
				if(((HTMLElement)currentNode).hasAttributes()){
					// okok l'elemento ha attributi
					String idValue = ((HTMLElement)currentNode).getAttributeValue("id");
					if (idValue!=null) {
						// qui verifico se è lo stesso id 
						if (idValue.equals(idElement)) {
							found = (HTMLElement) currentNode;
							break ;
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
	
	
	public static HTMLElement examinesForId(String idElement,Node node){
		helpForId(idElement, node);
		HTMLElement element = found ;
		found = null ;
		return element ;
	}
	
	/*
	 * CLASS fino a @
	 */
	
	private static HTMLElements classListNodes = new HTMLElements();
	
	public static HTMLElements examinesForClass(String className,Node node){
		
		// chiamo il metodo di supporto
		
		helpForClass(className, node);
		
		// qui poi aggiornare con una costante apposita
		
		HTMLElements elements = classListNodes;
		
		// reinizializzo di nuovo la variabile statica
		
		classListNodes = new HTMLElements();
		
		// restituisco la copia della lista 
		
		return elements ;
		
	}
	
	
	private static void helpForClass(String className,Node node){
		HTMLNodeList listNodes = node.getChildNodes();
		for (int i = 0; i < listNodes.getLength(); i++) {
			Node currentNode = listNodes.item(i);
			if (currentNode instanceof HTMLElement) {
				if(((HTMLElement)currentNode).hasAttributes()){
					// okok l'elemento ha attributi
					String classValue = ((HTMLElement)currentNode).getAttributeValue("class");
					if (classValue!=null) {
						if (classValue.equals(className)) {
							// quindi carico i nodi con la classe nella lista
							classListNodes.add((HTMLElement) currentNode);
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
	
	private static HTMLElements tagListNodes = new HTMLElements();
	
	public static HTMLElements examinesForTag(String tagName,Node node){
		
		// chiamo il metodo di supporto
		
		helpForTag(tagName, node);

		
		// qui poi aggiornare con una costante apposita
		
		HTMLElements elements = tagListNodes;
		
		// reinizializzo di nuovo la variabile statica
		
		tagListNodes = new HTMLElements();
		
		// restituisco la copia della lista 
		
		return elements ;
		
	}
	
	
	private static void helpForTag(String tagName,Node node){
		HTMLNodeList listNodes = node.getChildNodes();
		for (int i = 0; i < listNodes.getLength(); i++) {
			Node currentNode = listNodes.item(i);
			if (currentNode.getNodeName().equals(tagName)) {
				tagListNodes.add((HTMLElement) currentNode);
			}
			// ricorsività
			helpForTag(tagName, currentNode);
		}
	}
	
	/*
	 * @
	 */
	
	
	
	
	
	/*
	 * TAG fino a @
	 */
	
	private static HTMLElements typeListNodes = new HTMLElements();
	
	public static HTMLElements examinesForType(HTMLElementType type,Node node){
		
		// chiamo il metodo di supporto
		
		helpForType(type, node);
		
		
		// qui poi aggiornare con una costante apposita
		
		HTMLElements elements = typeListNodes;
		
		// reinizializzo di nuovo la variabile statica
		
		typeListNodes = new HTMLElements();
		
		// restituisco la copia della lista 
		
		return elements ;
		
	}
	
	
	private static void helpForType(HTMLElementType type,Node node){
		HTMLNodeList listNodes = node.getChildNodes();
		for (int i = 0; i < listNodes.getLength(); i++) {
			Node currentNode = listNodes.item(i);
			if (currentNode instanceof HTMLElement) {
				if ((((HTMLElement)currentNode).getType().toString()).equals(type.toString())) {
					typeListNodes.add((HTMLElement) currentNode);
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
	 * @ da qui a @ :  path
	 */
	
	private static StringBuffer path = new StringBuffer();
	public static String examinesForTPath(Node node){
		
		helpForPath(node);
		
		// quindi trasferisco il valore della var statica su un altra variabile
		
		String finalPath = path.toString();
		
		// qui faccio lo split 
		
		String[]split = null ;
		
		split = finalPath.split(Pattern.quote(File.separator));
		
		List<String>list = new ArrayList<>();
		
		for (int i = split.length; i > 0;i--) {
			if (i>0) {
				list.add(split[i-1]+File.separator);
			}
			else{
				list.add(split[i-1]);
			}
		}
		StringBuffer bufferINtern = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			bufferINtern.append(list.get(i));
		}
		
		finalPath = bufferINtern.toString();
		
		// sappiamo che l'ultimo carattere è un separatore e vogliamo eliminarlo
		
		path = new StringBuffer();
		
		return finalPath ;

	}
	
	private static void helpForPath(Node node){
		path.append(node.getNodeName()+File.separator);
		Node parent = node.getParentNode();
		if (!node.getNodeName().equalsIgnoreCase("html")) { // verifico che il nodo ricevuto non sia il nodo root html
			if (parent!=null) {
				// aggiungo al path 
				path.append(parent.getNodeName()+File.separator);
				
				// ricorsività
				
				helpForPath(parent.getParentNode());
			}	
		}
		else{
			// in caso contrario finisce il metodo
			path.append(parent.getNodeName()+File.separator);
			return ;
		}
	}


/**
 * @
 */
	
	
	/**
	 *  metodi recorsivo per eliminamento nodo dal documento 
	 */
	
	private static Node deleted = null ;

	public static Node removeNode(Node node,Node rootNode){
		deleted = null ;
		HTMLNodeList listNodes = rootNode.getChildNodes();
		
		for (int i = 0; i < listNodes.getLength(); i++) {
			
			Node currentNode = listNodes.item(i);
			
			if (node.equals(currentNode)) {
				
				// prendo il genitore
				
				Node parent = currentNode.getParentNode();
				
				deleted = parent.removeNode(node);
				
				break ;
			}
			
			// se arriva qui vuol dire che ancora non si è trovato il nodo
			// quindi richiamo il metodo 
			
			removeNode(node, currentNode) ; // questa volta però come root nodo ci passo il nodo corrente che sta iterando
			
		}
		return deleted ;
	}
	/**
	 * Metodo ricorsivo per sostituzione child dal documento
	 */
	private static Node replaced = null ;
	
	public static Node replaceChild(Node newNode,Node oldNode,Node rootNode){
		replaced = null ;
		HTMLNodeList listNodes = rootNode.getChildNodes();
		
		for (int i = 0; i < listNodes.getLength(); i++) {
			
			Node currentNode = listNodes.item(i);
			
			if (oldNode.equals(currentNode)) {
				
				// prendo il genitore
				
				Node parent = currentNode.getParentNode();
				
				replaced = parent.replaceChild(newNode, oldNode);
				
				break ;
			}
			
			// se arriva qui vuol dire che ancora non si è trovato il nodo
			// quindi richiamo il metodo 
			
			replaceChild(newNode,oldNode,currentNode) ; // questa volta però come root nodo ci passo il nodo corrente che sta iterando
			
		}
		return replaced ;
	}
	
	
	/*
	 * Metodo ricorsivo per l'ottenimento dei commenti del documento 
	 * Qui dobbiamo gestire il fatto che il set di commenti non viene
	 * resettato a nessuna parte, quindi allo stato attuale, viene 
	 * riempito sempre, senza però mai essere svuotato, quindi RISOLVERE
	 */
	private static Set<HTMLComment> comments = new HashSet<>();
	public static void resetCommentsSet(){
		comments = new HashSet<>();
	}
	public static Set<HTMLComment> getAllComments(Node rootNode){
		HTMLNodeList listNodes = rootNode.getChildNodes();
		for (int i = 0; i < listNodes.getLength(); i++) {
			Node node = listNodes.item(i);
			if (node instanceof HTMLComment) {
				comments.add((HTMLComment) node);
			}
			else{
				// se non è un commento, vuol dire che è un nodo
				// e quindi dentro ci posso essere altri commenti
				// per cui richiamo il metodo
				getAllComments(node); // recursive
			}
		}
		return comments ;
	}
	
	private static List<HTMLComment>commentsList = new ArrayList<>();
	public static void resetCommentsList(){
		commentsList = new ArrayList<>();
	}
	public static List<HTMLComment> getListComments(Node rootNode){
		HTMLNodeList listNodes = rootNode.getChildNodes();
		for (int i = 0; i < listNodes.getLength(); i++) {
			Node node = listNodes.item(i);
			if (node instanceof HTMLComment) {
				commentsList.add((HTMLComment) node);
			}
			else{
				// se non è un commento, vuol dire che è un nodo
				// e quindi dentro ci posso essere altri commenti
				// per cui richiamo il metodo
				getListComments(node); // recursive
			}
		}
		return commentsList ;
	}
	
	/*
	 * For attribute fino a @
	 */
	
	private static HTMLElements attributeNodes = new HTMLElements();
	
	public static HTMLElements examinesForAttribute(String attribute,Node node){
		
		// chiamo il metodo di supporto
		
		helpForAttribute(attribute, node);
		
		// qui poi aggiornare con una costante apposita
		
		HTMLElements elements = attributeNodes;
		
		// reinizializzo di nuovo la variabile statica
		
		attributeNodes = new HTMLElements();
		
		// restituisco la copia della lista 
		
		return elements ;
		
	}
	
	
	private static void helpForAttribute(String attribute,Node node){
		if (node instanceof HTMLElement) {
			if (((HTMLElement)node).hasAttributes()) {
				if (((HTMLElement)node).isPresent(attribute)) {
					if (!attributeNodes.contains(node)) {
						attributeNodes.add((HTMLElement)node);
					}
				}
			}
		}
		HTMLNodeList listNodes = node.getChildNodes();
		for (int i = 0; i < listNodes.getLength(); i++) {
			Node currentNode = listNodes.item(i);
			if (currentNode instanceof HTMLElement) {
				if(((HTMLElement)currentNode).hasAttributes()){
					if (((HTMLElement)currentNode).isPresent(attribute)) {
						attributeNodes.add((HTMLElement) currentNode);
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
	
	private static HTMLElements attributeValueNodes = new HTMLElements();
	
	public static HTMLElements examinesForAttributeValue(String value,Node node){
		
		// chiamo il metodo di supporto
		
		helpForAttributeValue(value, node);
		
		// qui poi aggiornare con una costante apposita
		
		HTMLElements elements = attributeValueNodes;
		
		// reinizializzo di nuovo la variabile statica
		
		attributeValueNodes = new HTMLElements();
		
		// restituisco la copia della lista 
		
		return elements ;
		
	}
	
	
	private static void helpForAttributeValue(String value,Node node){
		if (node instanceof HTMLElement) {
			if (((HTMLElement)node).hasAttributes()) {
				Map<String, String> attributes = ((HTMLElement)node).getAttributes();
				Iterator<Entry<String,String>>iterator = attributes.entrySet().iterator();
				while (iterator.hasNext()) {
					Map.Entry<java.lang.String, java.lang.String> entry = (Map.Entry<java.lang.String, java.lang.String>) iterator
							.next();
					String attr = entry.getKey();
					String val = entry.getValue();
					if (val.equals(value)) {
						attributeValueNodes.add((HTMLElement)node);
					}
				}
			}
		}
		HTMLNodeList listNodes = node.getChildNodes();
		for (int i = 0; i < listNodes.getLength(); i++) {
			Node currentNode = listNodes.item(i);
			if (currentNode instanceof HTMLElement) {
				if(((HTMLElement)currentNode).hasAttributes()){
				Map<String, String> attributes = ((HTMLElement)currentNode).getAttributes();
				Iterator<Entry<String,String>>iterator = attributes.entrySet().iterator();
				while (iterator.hasNext()) {
					Map.Entry<java.lang.String, java.lang.String> entry = (Map.Entry<java.lang.String, java.lang.String>) iterator
							.next();
					String attr = entry.getKey();
					String val = entry.getValue();
					if (val.equals(value)) {
						attributeValueNodes.add((HTMLElement) currentNode);
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
	 * Da qui in poi abbiamo il metodo che unisce tutti questi metodi separati "superficialmente"
	 * fino a #
	 */

	private static HTMLElements attributeValueNodes_ = new HTMLElements();
	
	public static HTMLElements examinesForAttributeValue_(String attribute,String value,Node node,String operator){
		// chiamo il metodo di supporto
		helpForAttributeValue_(attribute,value,node,operator);
		
		// qui poi aggiornare con una costante apposita
		
		HTMLElements elements = attributeValueNodes_;
		
		// reinizializzo di nuovo la variabile statica
		
		attributeValueNodes_ = new HTMLElements();
		
		// restituisco la copia della lista 
		
		return elements ;
	}
	
	private static void check(Node node, String attribute, String value, String operator){
		if (node instanceof HTMLElement) {
			if (((HTMLElement)node).hasAttributes()) {
				if (((HTMLElement)node).isPresent(attribute)) {
					String val = ((HTMLElement)node).getAttributeValue(attribute);
					switch(operator){
					case "=" :
						if (val.equals(value)) {
							// lo abbiamo 
							if (!attributeValueNodes_.contains(node)) {
								attributeValueNodes_.add((HTMLElement)node);
							}
						}
						break ;
					case cloud.jgo.jjdom.css.CSSSelector.DIFFERENT_OPERATOR:
						if (!val.equals(value)) {
							// lo abbiamo 
							if (!attributeValueNodes_.contains(node)) {
								attributeValueNodes_.add((HTMLElement)node);
							}
						}
						break;
					case cloud.jgo.jjdom.css.CSSSelector.STARTS_WITH_OPERATOR:
						if (val.startsWith(value)) {
							// lo abbiamo 
							if (!attributeValueNodes_.contains(node)) {
								attributeValueNodes_.add((HTMLElement)node);
							}
						}
						break ;
					case cloud.jgo.jjdom.css.CSSSelector.ENDS_WITH_OPERATOR:
						if (val.endsWith(value)) {
							// lo abbiamo 
							if (!attributeValueNodes_.contains(node)) {
								attributeValueNodes_.add((HTMLElement)node);
							}
						}
						break ;
					case cloud.jgo.jjdom.css.CSSSelector.CONTAINS_OPERATOR:
						if (val.contains(value)) {
							// lo abbiamo 
							if (!attributeValueNodes_.contains(node)) {
								attributeValueNodes_.add((HTMLElement)node);
							}
						}
						break ;
					}
				}
			}
		}	
	}
	
	// devo assicurarmi che questo metodo, con questo controllo iniziale
	// va bene o meno, secondo me c'è l rischio che si possono inserire + vlt
	// gli elementi, quindi testare in maniera concentrata
	private static void helpForAttributeValue_(String attribute,String value,Node node,String operator){
		// controllo la questione attributo sul nodo ricevuto 
		check(node, attribute, value, operator);
		// e qui controllo i children
		HTMLNodeList listNodes = node.getChildNodes();
		for (int i = 0; i < listNodes.getLength(); i++) {
			Node currentNode = listNodes.item(i);
			check(currentNode, attribute, value, operator);
			// ricorsività
			helpForAttributeValue_(attribute,value,currentNode,operator);
		}
	}
	
	/*
	 * #
	 */
	
	
	
	/*
	 * Da qui a @ : metodo che ottiene tutti gli elementi del documento 
	 */
	private static HTMLElements allElements = new HTMLElements();
	
	public static HTMLElements getAllElements(Node root){
		allElements = support(root);
		HTMLElements elements = allElements ;
		allElements = new HTMLElements() ;
		return elements ;
	}
	
	private static HTMLElements support(Node root){
		// okok inserisco l'elemento nella lista 
		if(root instanceof HTMLElement){
			allElements.add((HTMLElement) root);
		}
		HTMLNodeList list = root.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			support(list.item(i));
		}
		return allElements ;
	}
}
