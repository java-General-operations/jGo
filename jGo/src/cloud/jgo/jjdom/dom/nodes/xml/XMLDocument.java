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
import java.util.List;
import java.util.Set;
import cloud.jgo.£;
import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.css.CSSSelector;
import cloud.jgo.jjdom.dom.Recursion;
import cloud.jgo.jjdom.dom.nodes.Comment;
import cloud.jgo.jjdom.dom.nodes.Document;
import cloud.jgo.jjdom.dom.nodes.Element;
import cloud.jgo.jjdom.dom.nodes.Elements;
import cloud.jgo.jjdom.dom.nodes.Node;
import cloud.jgo.jjdom.dom.nodes.NodeList;
import cloud.jgo.jjdom.dom.nodes.html.HTMLComment;
import cloud.jgo.jjdom.dom.nodes.html.HTMLDefaultElement;
import cloud.jgo.jjdom.dom.nodes.html.HTMLDocument;
import cloud.jgo.jjdom.dom.nodes.html.HTMLElement;
/**
 * 
 * @author Martire91<br>
 * This class represents an XML document
 */
public class XMLDocument implements Document{
	public final static String XML_VERSION = "1.0";
	private String charset = Document.CHARSET_UTF_8 ;
	private String charsetName = null ;
	private NodeList childNodes = null ;
	private XMLElement rootElement = null ;
	private StringBuffer xmlCode = new StringBuffer();
	private String textContent=null;
	public XMLDocument(String charsetName,String rootElementName) {
		this.charsetName = charsetName ;
		this.childNodes = new NodeList();
		rootElement = new XMLElement(rootElementName, this);
		((XMLElement)rootElement).setParentNode(this);
		appendChild(rootElement);
	}
	public XMLDocument(String rootElementName) {
		// TODO Auto-generated constructor stub
		this.charsetName = XMLDocument.CHARSET_UTF_8;
		this.childNodes = new NodeList();
		rootElement = new XMLElement(rootElementName, this);
		((XMLElement)rootElement).setParentNode(this);
		appendChild(rootElement);
	}
	protected XMLDocument(String charsetName,JjDom home){
		this.charsetName = charsetName ;
		// inizializzo la lista di nodi 
		this.childNodes = new NodeList();
	}
	@Override
	public Node appendChild(Node node) {
		// controllo se c'è già questo nodo
				if (this.childNodes.contains(node)) {
					this.childNodes.remove(node);
				}
				// aggiungo il nodo
				boolean result = this.childNodes.addNode(node);
				if (result == true) {
					if (node instanceof XMLElement) {
							((XMLElement)node).setParentNode(this);
						}
						else if(node instanceof Comment){
							((HTMLComment)node).setParentNode(this);
						}
					
					return  node ;
				}
				else {
					return null ;
				}
	}

	@Override
	public Node appendChilds(Node... childs) {
		for (int i = 0; i < childs.length; i++) {
			appendChild(childs[i]);
		}
		return this ;
	}

	@Override
	public String getMarkup() {
		Recursion.examines_xml(this,xmlCode,this.charset);
		String markup = xmlCode.toString();
		xmlCode = new StringBuffer();
		return markup ;
	}

	@Override
	public Node printMarkup() {
		System.out.println(getMarkup());
		return this ;
	}

	@Override
	public String getPath() {
		// TODO Auto-generated method stub
		return "document";
	}

	@Override
	public NodeList getChildNodes() {
		// TODO Auto-generated method stub
		return this.childNodes ;
	}

	@Override
	public boolean hasThisChild(Node node) {
		return false ;
	}

	@Override
	public Node child(int index) {
		// TODO Auto-generated method stub
		return this.childNodes.item(index);
	}

	@Override
	public Node next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node previous() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getIndex() {
		// TODO Auto-generated method stub
		return -1 ;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node getNextSibling() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFirstChild(Node node) {
		if(this.childNodes.contains(node)){
			this.childNodes.remove(node);
		}
		this.childNodes.addFirstNode(node);
		if (node instanceof Element) {
			((XMLElement)node).setParentNode(this);
		}
		else if(node instanceof Comment){
			((HTMLComment)node).setParentNode(this);
		}
	}

	@Override
	public String getNodeName() {
		// TODO Auto-generated method stub
		return JjDom.DOCUMENT;
	}

	@Override
	public boolean contains(Node node) {
		boolean contains = false ;
		NodeList listNodes = this.childNodes;
		for (int i = 0; i < listNodes.getLength(); i++) {
			if (listNodes.item(i).equals(node)) {
				contains = true ;
				break ;
			}
		}
		return contains ;
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
	public Node setNodeValue(String nodeValue) {
		// TODO Auto-generated method stub
		this.textContent = textContent ;
		return this ;
	}

	@Override
	public Node setTextContent(String textContent) {
		// TODO Auto-generated method stub
		this.textContent = textContent ;
		return this ;
	}

	@Override
	public String getBaseURI() {
		String baseURI = null ;
		if (JjDom.documentURL!=null) {
			if (£.extractFormatFromFileName(JjDom.documentURL).equals("xml")) {
				baseURI = JjDom.documentURL;
			}
		}
		return baseURI;
	}

	@Override
	public XMLDocument getDocument() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Node getParentNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeType getNodeType() {
		// TODO Auto-generated method stub
		return NodeType.DOCUMENT;
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
	public Node insertBefore(Node newNode, Node refChild) {
		// TODO Auto-generated method stub
		return recursive(newNode, refChild, this,"before");
	}

	@Override
	public Node insertAfter(Node newNode, Node refChild) {
		// TODO Auto-generated method stub
		return recursive(newNode, refChild, this, "after");
	}
	
	private Node recursive(Node newNode, Node refChild,Node node,String mode){
		Node parent = null ;
		// qui in tanto vado alla ricerca del nodo padre che contiene 
		// il riferimento 
		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			// qui per prima cosa verifico che non si tratti proprio di questo nodo
			if(node.getChildNodes().item(i).equals(refChild)){
				
				// okok lo abbiamo trovato già
				parent = node.getChildNodes().item(i).getParentNode();
				break ;
			}
			
			// metodo ricorsivo, quindi vuol dire che ancora non si è trovato 
			recursive(newNode, refChild, node.getChildNodes().item(i),mode);
		}
		if (parent!=null) {
			// qui richiamo il metodo ricorsivo dell'element 
			if (mode.equals("before")) {
				return parent.insertBefore(newNode, refChild);
			}
			else{
				// diamo per scontato che si tratti di after
				return parent.insertAfter(newNode, refChild);
			}
		}
		else{
			return null ;
		}
	}

	@Override
	public boolean isEqualNode(Node node) {
		if (node instanceof XMLDocument) {
			return true ;
		}
		else{
			return false ;
		}
	}

	@Override
	public Node removeNode(Node node) {
		// TODO Auto-generated method stub
		return Recursion.removeNode(node,this);
	}

	@Override
	public Node replaceChild(Node newNode, Node oldNode) {
		// TODO Auto-generated method stub
		return Recursion.replaceChild(newNode, oldNode, this);
	}

	@Override
	public Node getNodeByPath(String nodePath) {
		String[]split = nodePath.split("/");
		Node currentNode = this ;
		boolean found ;
		for (int i = 0; i < split.length; i++) {
			String nodeName = split[i].trim();
			found = false ;
			NodeList listNodes = currentNode.getChildNodes();
			for (int j = 0; j < listNodes.getLength(); j++) {
				Node node = listNodes.item(j);
				if (node.getNodeName().equals(nodeName)) {
					currentNode = node ;
					found = true ;
					break ;
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
		NodeList childNodes = getChildNodes();
		boolean flag = false ;
		for (int i = 0; i < childNodes.getLength(); i++) {
			if (nodeName.equals(childNodes.item(i).getNodeName())) {
				flag = true ;
				break;
			}
		}
		return flag ;
	}
	@Override
	public Comment createComment(String comment) {
		// TODO Auto-generated method stub
		return new HTMLComment(comment, this);
	}

	@Override
	public Element getRootElement() {
		// TODO Auto-generated method stub
		return this.rootElement ;
	}

	@Override
	public String getCharset() {
		// TODO Auto-generated method stub
		return this.charset ;
	}

	@Override
	public Document removeNodes(Node... nodes) {
		for (int i = 0; i < nodes.length; i++) {
			removeNode(nodes[i]);
		}
		return this ;
	}

	@Override
	public Set<? extends Comment> getComments() {
		Set<Comment>comments = Recursion.getAllComments(this);
		
		Recursion.resetCommentsSet();
		
		return comments ;
	}

	@Override
	public List<? extends Comment> getListComments() {
		List<Comment>comments = Recursion.getListComments(this);
		
		Recursion.resetCommentsList();
	
		return comments ;
	}
	@Override
	public Element createElement(String elementName) {
		// TODO Auto-generated method stub
		return new XMLElement(elementName, this);
	}
	@Override
	public Element getElementById(String elementId) {
		XMLElement idEl = (XMLElement) Recursion.examinesForId(elementId,this);
		return idEl;
	}
	@Override
	public Elements getElementsByTag(String tagName) {
		// TODO Auto-generated method stub
		return Recursion.examinesForTag(tagName,this);
	}
	@Override
	public Elements getElementsByName(String name) {
		return Recursion.examinesForName(name, this);
	}
	@Override
	public Elements getElementsByClassName(String className) {
		// TODO Auto-generated method stub
		return Recursion.examinesForClass(className,this);
	}
	@Override
	public Elements getElementsByAttribute(String attribute) {
		// TODO Auto-generated method stub
		return Recursion.examinesForAttribute(attribute,this);
	}
	@Override
	public Elements getElementsByAttributeValue(String value) {
		// TODO Auto-generated method stub
		return Recursion.examinesForAttributeValue(value,this);
	}
	@Override
	public Elements getElementsByAttributeValue(String attr, String val) {
		// TODO Auto-generated method stub
		return Recursion.examinesForAttributeValue_(attr, val, this,"=");
	}
	@Override
	public Elements getElementsByDifferentAttributeValue(String attr, String val) {
		// TODO Auto-generated method stub
		return Recursion.examinesForAttributeValue_(attr, val, this, CSSSelector.DIFFERENT_OPERATOR);
	}
	@Override
	public Elements getElementsThatStartWithAttributevalue(String attr,
			String val) {
		// TODO Auto-generated method stub
		return Recursion.examinesForAttributeValue_(attr, val, this, CSSSelector.STARTS_WITH_OPERATOR);
	}
	@Override
	public Elements getElementsThatEndWithAttributeValue(String attr, String val) {
		// TODO Auto-generated method stub
		return Recursion.examinesForAttributeValue_(attr, val, this, CSSSelector.ENDS_WITH_OPERATOR);
	}
	@Override
	public Elements getElementsThatContainTheAttributeValue(String attr,
			String val) {
		// TODO Auto-generated method stub
		return Recursion.examinesForAttributeValue_(attr, val, this, CSSSelector.CONTAINS_OPERATOR);
	}
	@Override
	public Elements getDirectChildrenByTag(String tagName) {
		Elements elements = new Elements();
		NodeList listNodes = this.childNodes;
		for (int i = 0; i < listNodes.getLength(); i++) {
			if (listNodes.item(i)instanceof Element) {
				if (listNodes.item(i).getNodeName().equals(tagName)) {
					elements.add((XMLElement) listNodes.item(i));
				}
			}
		}
		return elements ;
	}
	@Override
	public Elements getAdiacentSiblingsByTag(String tagName) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Elements getGeneralSiblingsByTag(String tagName) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Node removeChildren() {
		NodeList children = getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node currentNode = children.item(i);
			removeNode(currentNode);
			i-- ;
		}
		return this ;
	}
	@Override
	public String getDocumentFormat() {
		// TODO Auto-generated method stub
		return "xml";
	}
}