package cloud.jgo.jjdom.dom.nodes.xml;
import java.util.List;
import java.util.Set;

import cloud.jgo.£;
import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.dom.Recursion;
import cloud.jgo.jjdom.dom.nodes.Comment;
import cloud.jgo.jjdom.dom.nodes.Document;
import cloud.jgo.jjdom.dom.nodes.Element;
import cloud.jgo.jjdom.dom.nodes.Node;
import cloud.jgo.jjdom.dom.nodes.NodeList;
import cloud.jgo.jjdom.dom.nodes.html.HTMLComment;
import cloud.jgo.jjdom.dom.nodes.html.HTMLDefaultElement;
import cloud.jgo.jjdom.dom.nodes.html.HTMLDocument;
import cloud.jgo.jjdom.dom.nodes.html.HTMLElement;
public class XMLDocument implements Document{
	
	// in tanto prendo il charset cosi creo il primo tag
	// inaccesibile però, un pochino come il docType
	// nell'htmlDocument
	// <?xml version="1.0" encoding="UTF-8"?>
	public final static String XML_VERSION = "1.0";
	private String charset = Document.CHARSET_UTF_8 ;
	private String charsetName = null ;
	private NodeList childNodes = null ;
	private XMLElement rootElement = null ;
	private StringBuffer xmlCode = new StringBuffer();
	public XMLDocument(String charsetName,String baseUri,String rootElementName) {
		this.charsetName = charsetName ;
		// inizializzo la lista di nodi 
		this.childNodes = new NodeList();
		// creo automaticamente il nodo root : html
		rootElement = new XMLElement(rootElementName, this);
		// qui imposto il tag del padre che è il documento 
		
		((XMLElement)rootElement).setParentNode(this);
		// aggiungo il nodo all'albero 
		
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
					if (node instanceof HTMLElement) {
							((HTMLDefaultElement)node).setParentNode(this);
							if(node.getNodeName().equals("html")){
								// collego il root element al nodo html ricevuto come parametro
								this.rootElement = (XMLElement) node ;
							}
						}
						else if(node instanceof HTMLComment){
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
		// TODO Auto-generated method stub
		return null;
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
		return null;
	}

	@Override
	public NodeList getChildNodes() {
		// TODO Auto-generated method stub
		return this.childNodes ;
	}

	@Override
	public boolean hasThisChild(Node node) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Node child(int index) {
		// TODO Auto-generated method stub
		return null;
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
		return 0;
	}

	@Override
	public Node getFirstChild() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node getLastChild() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLocalName() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getNodeName() {
		// TODO Auto-generated method stub
		return "document";
	}

	@Override
	public boolean contains(Node node) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getNodeValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTextContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node setNodeValue(String nodeValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node setTextContent(String textContent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBaseURI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HTMLDocument getDocument() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Node insertBefore(Node newNode, Node refChild) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node insertAfter(Node newNode, Node refChild) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEqualNode(Node node) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Node removeNode(Node node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node replaceChild(Node newNode, Node oldNode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node getNodeByPath(String nodePath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(String nodeName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public JjDom home() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Element createNewElement(String elementName, boolean hasClosingTag,
			boolean thereCanBeMore) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment createComment(String comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Element getRootElement() {
		// TODO Auto-generated method stub
		return this.rootElement ;
	}

	@Override
	public String getCharset() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document removeNodes(Node... nodes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<? extends Comment> getComments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends Comment> getListComments() {
		// TODO Auto-generated method stub
		return null;
	}

}
