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
import cloud.jgo.£;
import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.dom.concrete.HTMLDefaultDocument;
import cloud.jgo.jjdom.dom.concrete.HTMLDefaultElement;
/**
 * 
 * @author Martire91<br>
 * This class represente the html comment
 */
public class HTMLComment implements HTMLNode{
	/**
	 * 
	 */
	private static final long serialVersionUID = 12L;
	private String startTag,endTag = null ;
	private String textContent = null ;
	private HTMLNode parent = null ;
	private HTMLNodeList childNodes = null ;
	private StringBuffer htmlCode = new StringBuffer();
	private HTMLDocument document = null ;
	private JjDom home = null ;
	
	

	// costruttore della classe 
	
	public HTMLComment(String comment,HTMLDocument document) {
		// TODO Auto-generated constructor stub
		this.startTag = "<!--";
		this.textContent = comment ;
		this.endTag = "-->";
		this.childNodes = new HTMLNodeList();
		this.document = document ;
		this.home = this.document.home();
	}
	
	// restituisce - 1 se non trova l'indice
		@Override
		public int getIndex() {
			HTMLNode parent = getParentNode();
			int index = -1 ;
			for (int i = 0; i < parent.getChildNodes().getLength(); i++) {
				if (parent.getChildNodes().item(i).equals(this)) {
					index = i ;
					break ;
				}
			}
			return index ;
		}

	@Override
	public HTMLNode appendChild(HTMLNode node) {
		if (this.childNodes.contains(node)) {
			this.childNodes.remove(node);
		}
		// imposto il padre 
		
		// qui verifico se si tratta di un elemento o commento
		
		if (node instanceof HTMLElement) {
			((HTMLDefaultElement)node).setParentNode(this);
		}
		else if(node instanceof HTMLComment){
			((HTMLComment)node).setParentNode(this);
		}
		// aggiungo il nodo
		
		boolean result = this.childNodes.addNode(node);
		if (result == true) {
			return  node ;
		}
		else {
			return null ;
		}
	}

	@Override
	public HTMLNode appendChilds(HTMLNode... childs) {
		for (int i = 0; i < childs.length; i++) {
			appendChild(childs[i]);
		}
		return this ;
	}

	@Override
	public String getMarkup() {
		HTMLRecursion.examines(this,htmlCode); // provvisorio, poi gli dobbiamo passare il document
		String result = htmlCode.toString();
		// pulisco il buffer code html
		htmlCode = new StringBuffer();
		return result ;	
	}

	@Override
	public HTMLNodeList getChildNodes() {
		// TODO Auto-generated method stub
		return this.childNodes ;
	}
	
	@Override
	public HTMLNode child(int index) {
		// TODO Auto-generated method stub
		return this.childNodes.item(index);
	}

	@Override
	public HTMLNode getFirstChild() {
		// TODO Auto-generated method stub
		return this.childNodes.getFirstItem();
	}

	@Override
	public HTMLNode getLastChild() {
		// TODO Auto-generated method stub
		return this.childNodes.getLastItem();
	}

	@Override
	public String getLocalName() {
		// TODO Auto-generated method stub
		return this.getNodeName();
	}

	
	@Override
	public HTMLNode getNextSibling() {
		HTMLNode parent = getParentNode();
		if (parent!=null) {
			// qui se ha un padre ci rende la vita più facile 
			HTMLNodeList listNodes = parent.getChildNodes();
			int pos = 0 ;
			boolean found = false ;
			for (int i = 0; i < listNodes.getLength(); i++) {
				if(listNodes.item(i).equals(this)){
					pos = i ;
					found = true ;
					break ;
				}
			}
			if (found) {
				pos = pos+1 ;
				if (pos<listNodes.getLength()) {
					return listNodes.item(pos);
				}
				else{
					return null ;
				}
			}
			else{
				return null ;
			}
		}
		else{
			return null ;
		}
	}

	@Override
	public String getNodeName() {
		// TODO Auto-generated method stub
		return "comment";
	}

	@Override
	public String getBaseURI() {
		// SVILUPPARE QUESTO METODO APPENA HO UN RIFERIMENTO AL FILE HTML IN QUESTIONE
		return JjDom.documentURL;
	}
	@Override
	public HTMLNode getParentNode() {
		// TODO Auto-generated method stub
		return this.parent ;
	}
	
	/**
	 * This method sets the parent node
	 * @param parentNode the parent node
	 */
	public void setParentNode(HTMLNode parentNode){
		this.parent =  parentNode ;
	}

	@Override
	public HTMLNodeType getNodeType() {
		// TODO Auto-generated method stub
		return HTMLNodeType.HTML_COMMENT;
	}

	@Override
	public JjDom home() {
		// TODO Auto-generated method stub
		return this.home ;
	}

	@Override
	public String getNodeValue() {
		// TODO Auto-generated method stub
		return this.textContent ;
	}

	@Override
	public String getTextContent() {
		// TODO Auto-generated method stub
		return this.textContent ;
	}

	@Override
	public boolean hasChildNodes() {
		if (this.childNodes.getLength()>0) {
			return true ;
		}
		else{
			return false ;
		}
	}

	@Override
	public HTMLNode insertBefore(HTMLNode newNode, HTMLNode refChild) {
		boolean found = false ;
		int pos = 0 ;
		for (int i = 0; i < this.childNodes.getLength(); i++) {
			if (this.childNodes.item(i).equals(refChild)) {
				found = true ;
				pos = i ;
				break ;
			}
		}
		if (found) {
			if(pos==0){
				// qui basta aggiungere l'elemento
				if (newNode instanceof HTMLElement) {
					((HTMLDefaultElement)newNode).setParentNode(this);
				}
				else if(newNode instanceof HTMLComment){
					((HTMLComment)newNode).setParentNode(this);
				}
				this.childNodes.addFirstNode(newNode);
			}
			else if(pos > 0){
				
				// okok qui il primo passo da fare è : 
				//verificare se in quella data posizione esiste già un elemento 
				
				int index = pos-1 ;
				
				HTMLNode previousNode = this.childNodes.item(index);
				
				// quindi qui dobbiamo sostituire questo elemento prima
				if (newNode instanceof HTMLElement) {
					((HTMLDefaultElement)newNode).setParentNode(this);
				}
				else if(newNode instanceof HTMLComment){
					((HTMLComment)newNode).setParentNode(this);
				}
				previousNode = this.childNodes.setNode(index,newNode);
				
				// essendo che lo abbiamo sostituito, ora dobbiamo permettere
				// che lo preceda per cui richiamo il metodo
				// recursive 
				
				insertBefore(previousNode, newNode);
			}
		}
		return newNode ;
	}

	@Override
	public HTMLNode insertAfter(HTMLNode newNode, HTMLNode refChild) {
		// questo metodo deve inserire l'elemento newNOde
		// prima di refChild, quindi vediamo i passi da fare :
		// 1 trovare la posizione di refChild 
		boolean found = false ;
		int pos = 0 ;
		for (int i = 0; i < this.childNodes.getLength(); i++) {
			if (this.childNodes.item(i).equals(refChild)) {
				found = true ;
				pos = i ;
				break ;
			}
		}
		if (found) {
				// okok qui il primo passo da fare è : 
				//verificare se in quella data posizione esiste già un elemento 
				
				int index = pos+1 ;
				
				// qui verifico se l'index raggiunto sia minore del lenght attuale della lista
				if (index>this.childNodes.getLength()-1) {
					// qui mi basta aggiungere 
					if (newNode instanceof HTMLElement) {
						((HTMLDefaultElement)newNode).setParentNode(this);
					}
					else if(newNode instanceof HTMLComment){
						((HTMLComment)newNode).setParentNode(this);
					}
					this.childNodes.addNode(newNode);
				}
				else{
					HTMLNode followingNode = this.childNodes.item(index);
					
					// sostituisco 
					if (newNode instanceof HTMLElement) {
						((HTMLDefaultElement)newNode).setParentNode(this);
					}
					else if(newNode instanceof HTMLComment){
						((HTMLComment)newNode).setParentNode(this);
					}
					followingNode = this.childNodes.setNode(index, newNode);
					
					// recursive 
					
					insertAfter(followingNode, newNode);
				}
			}
		
		return newNode ;
	}

	@Override
	public boolean isEqualNode(HTMLNode node) {
		if (node.getNodeType().equals(HTMLNodeType.HTML_COMMENT)) {
			return true ;
		}
		else{
			return false ;
		}
	}

	@Override
	public HTMLNode removeNode(HTMLNode node) {
		boolean result = this.childNodes.remove(node);
		if(result == true){
			return node ;
		}
		else{
			return null;	
		}
	}

	@Override
	public HTMLNode replaceChild(HTMLNode newNode, HTMLNode oldNode) {
		boolean found = false ;
		int pos = 0 ;
		for (int i = 0; i < this.childNodes.getLength(); i++) {
			if (oldNode.equals(this.childNodes.item(i))) {
				found = true ;
				pos = i ;
				break ;
			}
		}
		if (found) {
			return this.childNodes.setNode(pos,newNode);
		}
		else{
			return null ;		
		}
	}
	public String getStartTag() {
		return this.startTag;
	}
	
	public String getEndTag() {
		return this.endTag;
	}

	@Override
	public HTMLDocument getDocument() {
		// TODO Auto-generated method stub
		return this.document ;
	}

	@Override
	public HTMLNode setNodeValue(String nodeValue) {
		// TODO Auto-generated method stub
		this.textContent = nodeValue ;
		return this ;
	}

	@Override
	public HTMLNode setTextContent(String textContent) {
		// TODO Auto-generated method stub
		this.textContent = textContent ;
		return this;
	}

	@Override
	public HTMLNode printMarkup() {
		£._O(getMarkup().trim());
		return this ;
	}

	@Override
	public HTMLNodeList getBrothers() {
		HTMLNodeList list = null ;
		// individuo il padre del nodo in questione 
		HTMLNode parent = getParentNode();
		
		if (parent!=null) {
			// ottengo i figli del padre 
			
			HTMLNodeList childNodes = parent.getChildNodes();
			
			// rimuovo dai figli il nodo in questione 
			
			childNodes.remove(this);
			list = childNodes ;
		}
		return list ;
	}

	@Override
	public boolean contains(HTMLNode node) {
		boolean contains = false ;
		HTMLNodeList listNodes = this.childNodes;
		for (int i = 0; i < listNodes.getLength(); i++) {
			if (listNodes.item(i).equals(node)) {
				contains = true ;
				break ;
			}
		}
		return contains ;
	}

	@Override
	public void addFirstChild(HTMLNode node) {
		if (this.childNodes.contains(node)) {
			this.childNodes.remove(node);
		}
		// aggiungo il nodo
		this.childNodes.addFirstNode(node);
			if (node instanceof HTMLElement) {
				((HTMLDefaultElement)node).setParentNode(this);
			}
			else if(node instanceof HTMLComment){
				((HTMLComment)node).setParentNode(this);
			}	
	}

	@Override
	public HTMLNode next() {
		// Ottengo il padre
		HTMLNode next = null ;
		HTMLNode parent = getParentNode();
		// ottengo i figli del parent 
		HTMLNodeList list = parent.getChildNodes();
		// faccio iterare i figli del parent in modo tale che
		// individuo l'elemento che si trova dopo
		// l'elemento in questione, ipotizzando che ci sia
		boolean flag = false ;
		int pos = 0 ;
		for (int i = 0; i < list.getLength(); i++) {
			HTMLNode node = list.item(i);
			if (node.equals(this)) {
				flag = true ;
				pos = i ;
				break ;
			}
		}
		if (flag) {
			// verifico se esiste un elemento successivo
			int value = pos+1 ;
			if (value <= list.getLength()-1) {
				next = list.item(value);
			}
		}
		return next ;
	}

	@Override
	public HTMLNode previous() {
		HTMLNode previous = null ;
		boolean flag = false ;
		int pos = 0 ;
		// primo passo, prendo il padre
		HTMLNode parent = getParentNode();
		
		// prendo i figli del padre 
		
		HTMLNodeList list = parent.getChildNodes();
		
		// faccio iterare i figli del padre
		
		for (int i = 0; i < list.getLength(); i++) {
			// okok abbiamo trovato l'elemento
			if(list.item(i).equals(this)){
				flag = true ; pos = i ; break ;
			}
		}
		if (flag) {
			// qui dentro verifico se la posizione è corretta per l'array
			int value = pos - 1 ;
			if (value > -1) {
				// quindi qui se vogliamo prendere 
				// l'elemento precedente, sull'ultimo elemento
				// della lista quindi il 4, si decrementa, quindi non c'è
				// mai il rischio di arrivare alla lunghezza
				//massimo si può prendere l'ultimo elemento della lista
				// che potrebbe essere pericoloso, peccato che però il metodo
				// poi decrementa il valore. Kmq testiamolo per bene
				previous = list.item(value);
			}
		}
		return previous ;
	}

	@Override
	public boolean hasThisChild(HTMLNode node) {
		boolean flag = false ;
		HTMLNodeList listNodes = getChildNodes();
		for (int i = 0; i < listNodes.getLength(); i++) {
			if (listNodes.item(i).equals(node)) {
				flag = true ;
				break ;
			}
		}
		return flag ;
	}

	@Override
	public HTMLNode getNodeByPath(String nodePath) {
		String[]split = nodePath.split("/");
		HTMLNode currentNode = this ;
		boolean found ;
		for (int i = 0; i < split.length; i++) {
			String nodeName = split[i].trim();
			found = false ;
			HTMLNodeList listNodes = currentNode.getChildNodes();
			for (int j = 0; j < listNodes.getLength(); j++) {
				HTMLNode node = listNodes.item(j);
				if (node.getNodeName().equals(nodeName)) {
					currentNode = node ;
					found = true ;
					break ;
				}
				if (node instanceof HTMLElement) {
					if(nodeName.startsWith("#")){
						if (((HTMLElement)node).isPresent("id")) {
							if (((HTMLElement)node).getId().equals(nodeName.replace("#",""))) {
								currentNode = node ;
								found = true ;
								break;
							}
						}
					}
					else if(nodeName.startsWith(".")){
						if (((HTMLElement)node).isPresent("class")) {
							String classAttributeValue = ((HTMLElement)node).getAttributeValue("class");
							String[]classes = classAttributeValue.split(" ");
							for (int k = 0; k < classes.length; k++) {
							if (classes[k].trim().equals(nodeName.replace(".",""))) {
								currentNode = node ;
								found = true ;
								break;
							}
							}
						}
					}	
				}
			}
			if (!found) {
				// spezziamo la catena
				currentNode = null ;
				break ; // esco dal controllo del path,poichè non è più ricostruibile
				// in quanto un elemento non è stato trovato
			}
		}
		return currentNode ;
	}

	@Override
	public boolean contains(String nodeName) {
		HTMLNodeList childNodes = getChildNodes();
		boolean flag = false ;
		for (int i = 0; i < childNodes.getLength(); i++) {
			if (nodeName.equals(childNodes.item(i).getNodeName())) {
				flag = true ;
				break;
			}
		}
		return flag ;
	}
}
