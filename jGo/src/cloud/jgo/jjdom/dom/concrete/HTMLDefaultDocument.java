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
 * Copyright � 2018 - Marco Martire (www.jgo.cloud)
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
package cloud.jgo.jjdom.dom.concrete;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cloud.jgo.�;
import cloud.jgo.io.File;
import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.css.CSSRule;
import cloud.jgo.jjdom.css.CSSSelector;
import cloud.jgo.jjdom.css.CSSStyle;
import cloud.jgo.jjdom.css.concrete.CSSDefaultRule;
import cloud.jgo.jjdom.css.concrete.CSSDefaultStyle;
import cloud.jgo.jjdom.dom.HTMLComment;
import cloud.jgo.jjdom.dom.HTMLDocument;
import cloud.jgo.jjdom.dom.HTMLElement;
import cloud.jgo.jjdom.dom.HTMLElement.HTMLElementType;
import cloud.jgo.jjdom.dom.HTMLElements;
import cloud.jgo.jjdom.dom.HTMLNode;
import cloud.jgo.jjdom.dom.HTMLNodeList;
import cloud.jgo.jjdom.dom.HTMLRecursion;
// N.B.
// ci sono alcuni metodi di questa classe che restituiscono null
// quindi devo segnalarli
/**
 * 
 * @author Martire91<br>
 * @see HTMLDocument
 * This class represents the default html document
 */
public class HTMLDefaultDocument implements HTMLDocument{
	private static final long serialVersionUID = 12L;
	private HTMLElement jquerySourceTag = null ;
	private String jqueryPath = JjDom.JQUERY_URL_SNIPPET;
	private String charsetName = null ;
	private HTMLNodeList childNodes = null ;
	private HTMLElement rootElement = null ;
	private StringBuffer htmlCode = new StringBuffer();
	private StringBuffer jsBuffer = new StringBuffer(); // LOCATION INTERNAL
	private boolean doctype = false ;
	private JjDom home = null ;
	private String textContent = null ;
	private HTMLElement metaTag,head,title,body,style= null ;
	private CSSStyle styleSheet = null ;
	private boolean ready = false ;
	// unico costruttore della classe
	// stabilire un criterio per questo costruttore
	// si deve creare una instanza di questa classe
	// solo da JJDom e la stessa cosa vale per gli elementi
	public HTMLDefaultDocument(String charsetName,String baseUri,JjDom home) {
		// TODO Auto-generated constructor stub
		this.home = home ;
		this.charsetName = charsetName ;
		// inizializzo la lista di nodi 
		this.childNodes = new HTMLNodeList();
		
		// creo automaticamente il nodo root : html
		
		rootElement = createElement(HTMLElement.HTMLElementType.HTML);
		
		// qui imposto il tag del padre che � il documento 
		
		((HTMLDefaultElement)rootElement).setParentNode(this);
		
		// aggiungo il nodo all'albero 
		
		appendChild(rootElement);
	}
	
	private HTMLDefaultDocument(String charsetName,JjDom home){
		this.home = home ;
		this.charsetName = charsetName ;
		// inizializzo la lista di nodi 
		this.childNodes = new HTMLNodeList();
	}
	
	@Override
	public CSSStyle createStyle() {
		// TODO Auto-generated method stub
		return new CSSDefaultStyle();
	}

	@Override
	public CSSRule createRule(String selection,String comment) {
		// TODO Auto-generated method stub
		return new CSSDefaultRule(selection,comment);
	}
	@Override
	public boolean isReady(){
		if (jsSource().toString().startsWith(JjDom.start_ready)&&jsSource().toString().endsWith(JjDom.end_ready)) {
			return ready = true ;
		}
		else{
			return ready = false ;
		}
	}
	
	/**
	 * This method creates a new html document
	 * @param charsetName the charset name
	 * @param home the jjdom instance
	 * @return the new document
	 */
	public static HTMLDocument newVoidDocument(String charsetName,JjDom home){
		return new HTMLDefaultDocument(charsetName, home);
	}
	
	// restituisce - 1 se non trova l'indice qui, poich� non esiste un padre
	// non ha senso l'indice
		@Override
		public int getIndex() {
			return -1 ;
		}

	@Override
	public HTMLNode appendChild(HTMLNode node) {
		// controllo se c'� gi� questo nodo
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
						this.rootElement = (HTMLElement) node ;
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
	public void addFirstChild(HTMLNode node){
		if(this.childNodes.contains(node)){
			this.childNodes.remove(node);
		}
		this.childNodes.addFirstNode(node);
		if (node instanceof HTMLElement) {
			((HTMLDefaultElement)node).setParentNode(this);
			if(node.getNodeName().equals("html")){
				// collego il root element al nodo html ricevuto come parametro
				this.rootElement = (HTMLElement) node ;
			}
		}
		else if(node instanceof HTMLComment){
			((HTMLComment)node).setParentNode(this);
		}
	}
	

	@Override
	public HTMLNodeList getChildNodes() {
		// TODO Auto-generated method stub
		return this.childNodes ;
	}
	
	@Override
	public HTMLNode child(int index) {
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
	/*
	 *@
	 */
	
	/**
	 * N.B QUI CASOMAI DA PROBLEMI, PASSARE COME NODO ALL'ESAMINATORE IL ROOT
	 */
	// metodo che si occupa di ottenere proprio il codice 
	@Override
	public String getMarkup(){
		HTMLRecursion.examines(this,htmlCode); // provvisorio, poi gli dobbiamo passare il document
		String result = htmlCode.toString();
		// pulisco il buffer code html
		htmlCode = new StringBuffer();
		return result ;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getMarkup();
	}

	@Override
	public String getLocalName() {
		// TODO Auto-generated method stub
		return getNodeName();
	}

	// questo metodo sul documento restituisce null, poich�
	// non c'� nessun nodo successivo al document, poic� ancora
	// quest'ultimo � il primo nodo, da cui parte tutto
	@Override
	public HTMLElement getNextSibling() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNodeName() {
		// TODO Auto-generated method stub
		return JjDom.DOCUMENT ;
	}

	@Override
	public HTMLNodeType getNodeType() {
		// TODO Auto-generated method stub
		return HTMLNodeType.HTML_DOCUMENT;
	}

	@Override
	public HTMLElement getParentNode() {
		// TODO Auto-generated method stub
		return null ;
	}


	@Override
	public boolean hasChildNodes() {
		// TODO Auto-generated method stub
		if (this.childNodes.getLength()>0) {
			return true ;
		}
		else{
			return false ;
		}
	}
	// metodo doppio ricorsivo 
	
	@Override
	public HTMLNode insertBefore(HTMLNode newNode, HTMLNode refChild) {
		return recursive(newNode, refChild, this,"before");
	}
	
	@Override
	public HTMLNode insertAfter(HTMLNode newNode, HTMLNode refChild) {
		// da completare
		return recursive(newNode, refChild, this, "after");
	}
	// metodo di supporto per il metodo insertBefore 
	private HTMLNode recursive(HTMLNode newNode, HTMLNode refChild,HTMLNode node,String mode){
		HTMLNode parent = null ;
		// qui in tanto vado alla ricerca del nodo padre che contiene 
		// il riferimento 
		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			// qui per prima cosa verifico che non si tratti proprio di questo nodo
			if(node.getChildNodes().item(i).equals(refChild)){
				
				// okok lo abbiamo trovato gi�
				parent = node.getChildNodes().item(i).getParentNode();
				break ;
			}
			
			// metodo ricorsivo, quindi vuol dire che ancora non si � trovato 
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
	public boolean isEqualNode(HTMLNode node) {
		if (node instanceof HTMLDocument) {
			return true ;
		}
		else{
			return false ;
		}
	}

	// questo metodo deve essere ricorsivo
	// e mi deve restituire l'elemento eliminato
	
	
	
	@Override
	public HTMLNode removeNode(HTMLNode node) {
		
		// qui come nodo ci passo il documento
	
		return HTMLRecursion.removeNode(node,this);
	
	}
	
	@Override
	public HTMLDocument removeNodes(HTMLNode... nodes) {
		for (int i = 0; i < nodes.length; i++) {
			removeNode(nodes[i]);
		}
		return this ;
	}
		
	@Override
	public HTMLNode replaceChild(HTMLNode newNode, HTMLNode oldNode) {
		return HTMLRecursion.replaceChild(newNode, oldNode, this);
	}


	private HTMLElement createElement(String elementName) {
		HTMLElement element = new HTMLDefaultElement(elementName,this);
		return element ;
	}
	
	// quindi partiamo prima a sviluppare questo metodo qui:
	// che non fa altro che limitare il metodo createElement(elementName)
	// nel senso che se il type passato � valido
	// si crea il tag con dei criteri, per esempio il fatto che abbia un tag di chiusura, per esempio
	@Override
	public HTMLElement createElement(HTMLElementType type) {
		HTMLElement element = null ;
		for (int i = 0; i < HTMLElementType.availableTypes.length; i++) {
			if (type.equals(HTMLElementType.availableTypes[i])) {
				element = createElement(type.toString());
				((HTMLDefaultElement)element).setType(type);
				break ;
			}
		}
		return element ;
	}
	
	// N.B. : 
	//se tentiamo di creare nuovamente un tipo di elemento gi� esistente
	// e cio� un elemento che possiamo benissimo creare con il metodo che chiede come parag
	// il tipo, ci restituisce null, ci� vuol dire che con questo metodo possiamo creare solo
	// tipi di elementi che non sono previsti nei tipi standart della classe HTMLElementType
	// questo metodo serve per creare elementi nuovi, nel caso non fossero previsti nei tipi predefiniti
	// ora bisogna capire se questo metodo va bene oppure no, quindi in attesa di test ...
	@Override
	public HTMLElement createNewElement(String elementName, boolean hasClosingTag, boolean thereCanBeMore) {
		boolean existingType = false ;
		HTMLElement element = null ;
		HTMLElementType type = null ;
		for (int i = 0; i < HTMLElementType.availableTypes.length; i++) {
			if (elementName.equals(HTMLElementType.availableTypes[i].toString())) {
				// okok il tipo esiste quindi, non vale niente
				existingType = true ;
				break ;
			}
		}
		if (!existingType) { // se il tipo di elemento non � esistente lo crei,
			//anche se questo tipo non esiste in html, quindi da segnalare :)
			element = new HTMLDefaultElement(elementName, this);
			// mi creo il tipo 
			type = HTMLElementType.newType(elementName); // qui abbiamo creato un nuovo tipo
			type.setClosingTag(hasClosingTag);
			type.setThereCanBeMore(thereCanBeMore);
			// imposto il tipo dell'elemento 
			((HTMLDefaultElement)element).setType(type);
		}
		return element ;
	}

	// per ora funziona, poi vediamo se mi viene in mente qualcosa
	@Override
	public HTMLElement getElementById(String elementId) {
		HTMLElement idEl = HTMLRecursion.examinesForId(elementId,this);
		return idEl;
	}
	
	@Override
	public HTMLElements getElementsByClassName(String className) {
		return HTMLRecursion.examinesForClass(className,this);
	}


	@Override
	public HTMLElements getElementsByTag(String tagName) {
		// TODO Auto-generated method stub
		return HTMLRecursion.examinesForTag(tagName,this);
	}
	
	@Override
	public HTMLElements getDirectChildrenByTag(String tagName) {
		HTMLElements elements = new HTMLElements();
		HTMLNodeList listNodes = this.childNodes;
		for (int i = 0; i < listNodes.getLength(); i++) {
			if (listNodes.item(i)instanceof HTMLElement) {
				if (listNodes.item(i).getNodeName().equals(tagName)) {
					elements.add((HTMLElement) listNodes.item(i));
				}
			}
		}
		return elements ;
	}
	

	@Override
	public HTMLElements getElementsByType(HTMLElementType type) {
		// TODO Auto-generated method stub
		return HTMLRecursion.examinesForType(type,this);
	}
	
	@Override
	public HTMLElements getElementsByAttribute(String attribute) {
		// TODO Auto-generated method stub
		return HTMLRecursion.examinesForAttribute(attribute,this);
	}
	
	@Override
	public HTMLElements getElementsByAttributeValue(String value) {
		// TODO Auto-generated method stub
		return HTMLRecursion.examinesForAttributeValue(value,this);
	}
	
	@Override
	public HTMLElements getElementsByAttributeValue(String attr, String val) {
		// TODO Auto-generated method stub
		return HTMLRecursion.examinesForAttributeValue_(attr, val, this,"=");
	}
	
	@Override
	public HTMLElements getElementsByDifferentAttributeValue(String attr, String val) {
		// TODO Auto-generated method stub
		return HTMLRecursion.examinesForAttributeValue_(attr, val, this, CSSSelector.DIFFERENT_OPERATOR);
	}
	
	@Override
	public HTMLElements getElementsThatStartWithAttributevalue(String attr, String val) {
		// TODO Auto-generated method stub
		return HTMLRecursion.examinesForAttributeValue_(attr, val, this, CSSSelector.STARTS_WITH_OPERATOR);
	}
	
	@Override
	public HTMLElements getElementsThatEndWithAttributeValue(String attr, String val) {
		// TODO Auto-generated method stub
		return HTMLRecursion.examinesForAttributeValue_(attr, val, this, CSSSelector.ENDS_WITH_OPERATOR);
	}
	
	@Override
	public HTMLElements getElementsThatContainTheAttributeValue(String attr, String val) {
		// TODO Auto-generated method stub
		return HTMLRecursion.examinesForAttributeValue_(attr, val, this, CSSSelector.CONTAINS_OPERATOR);
	}

	@Override
	public HTMLElement getRootElement() {
		// TODO Auto-generated method stub
		return this.rootElement ;
	}
	
	@Override
	public HTMLNode appendChilds(HTMLNode... childs) {
		for (int i = 0; i < childs.length; i++) {
			appendChild(childs[i]);
		}
		return this ;
	}

	@Override
	public HTMLDocument useDoctype(boolean flag) {
		// TODO Auto-generated method stub
		this.doctype = flag;
		return this ;
	}
	
	public boolean doctypeIsPresent(){
		return this.doctype ;
	}

	@Override
	public HTMLDocument setMinimalTags() {
		// controllo se esiste gi� una testa 
		HTMLElement root = getRootElement();
		// sappiamo che se head � inizializzato
		// vuol dire che � posto sulla pagina 
		// qui abbiamo detto che se la variabile head
		// ha un valore � effettivamente questo tag � posto sulla pagina
		// va bene, abbiamo gi� un head
		if (getHead()!=null && JjDom.document.getElementsByTag("head").size()>0) {
			
			if (getMetaTag()==null) {
				this.metaTag = createElement(HTMLElementType.META);
				this.metaTag.setAttribute("charset",this.charsetName);
				getHead().addFirstChild(this.metaTag);
			}
			if (getTitle()==null) {
				this.title = this.createElement(HTMLElementType.TITLE);
				getHead().appendChild(this.title);
			}
			// controllo se esiste il root element
			if (this.rootElement==null) {
				this.rootElement = JjDom.document.createElement(HTMLElementType.HTML);
				appendChild(this.rootElement);
			}
			
			// controllo del body 
			if (getBody()==null) {
				body = JjDom.document.createElement(HTMLElementType.BODY);
				// lo aggiungo al root
				this.rootElement.appendChild(body);
			}
		}
		else{
			// non abbiamo una testa
			// per cui procediamo tranquillamente
			this.head = this.createElement(HTMLElementType.HEAD);
			this.title = this.createElement(HTMLElementType.TITLE);
			this.body = this.createElement(HTMLElementType.BODY);
			this.metaTag = createElement(HTMLElementType.META);
			this.style = createElement(HTMLElementType.STYLE);
			this.style.setAttribute("type","text/css"); // questo non viene aggiunto, perch� non indispensabile
			this.metaTag.setAttribute("charset",this.charsetName);
			// inserisco i nodi 
			head.appendChilds(this.metaTag,this.title);
			if (this.rootElement==null) {
				this.rootElement = JjDom.document.createElement(HTMLElementType.HTML);
				// qui sappiamo che non esiste questo nodo root
				// quindi oltre ad averlo creato lo aggiungo al document
				appendChild(this.rootElement);
			}
			getRootElement().appendChilds(head,body);
		}
		return this ;
	}

	@Override
	public String getBaseURI() {
		return JjDom.documentURL;
	}
	
	@Override
	public JjDom home() {
		// TODO Auto-generated method stub
		return this.home ;
	}

	@Override
	public HTMLComment createComment(String comment) {
		// TODO Auto-generated method stub
		HTMLComment comm = new HTMLComment(comment,this);
		return comm ;
	}

	// N.B. segnalare questo metodo, ritorna se stesso
	@Override
	public HTMLDocument getDocument() {
		// TODO Auto-generated method stub
		return this ;
	}

	// restituisce il valore testuale del tag root
	@Override
	public String getNodeValue() {
		// TODO Auto-generated method stub
		return this.textContent ;
	}

	// restituisce il valore testuale del tag root
	@Override
	public String getTextContent() {
		// TODO Auto-generated method stub
		return this.textContent ;
	}

	@Override
	public HTMLNode setNodeValue(String nodeValue) {
		// TODO Auto-generated method stub
		this.textContent = nodeValue ;
		return this ;
	}

	// questo metodo imposta il testo del nodo root 
	@Override
	public HTMLNode setTextContent(String textContent) {
		// TODO Auto-generated method stub
		this.textContent = textContent ;
		return this ;
	}

	

	@Override
	public String getCharset() {
		// TODO Auto-generated method stub
		return this.charsetName ;
	}

	@Override
	public HTMLElement getMetaTag() {
		// TODO Auto-generated method stub
		return this.metaTag ;
	}

	@Override
	public HTMLNode printMarkup() {
		�._O(getMarkup());
		return this ;
	}

	// this is a recursive method
	
	@Override
	public Set<HTMLComment> getComments() {
		
		Set<HTMLComment>comments = HTMLRecursion.getAllComments(this);
		
		HTMLRecursion.resetCommentsSet();
		
		return comments ;
	}
	
	@Override
	public List<HTMLComment> getListComments(){
		List<HTMLComment>comments = HTMLRecursion.getListComments(this);
		
		HTMLRecursion.resetCommentsList();
	
		return comments ;
	}

	@Override
	public HTMLElement getBody() {
		// TODO Auto-generated method stub
		return this.body;
	}
	
	/**
	 * This method sets the body tag
	 * @param body the body
	 */
	public void setBody(HTMLElement body) {
		this.body = body;
	}

	@Override
	public HTMLElement getHead() {
		// TODO Auto-generated method stub
		return this.head ;
	}
	/**
	 * This method sets the head tag
	 * @param head the head
	 */
	public void setHead(HTMLElement head) {
		this.head = head;
	}

	@Override
	public HTMLElement getTitle() {
		// TODO Auto-generated method stub
		return this.title ;
	}
	
	/**
	 * This method sets the title tag
	 * @param title the title
	 */
	public void setTitle(HTMLElement title) {
		this.title = title;
	}
	
	/**
	 * This method sets the meta tag
	 * @param metaTag the meta tag
	 */
	public void setMetaTag(HTMLElement metaTag) {
		this.metaTag = metaTag;
	}

	@Override
	public CSSStyle getStyleSheet() {
		// TODO Auto-generated method stub
		return this.styleSheet ;
	}

	@Override
	public HTMLElement getStyleTag() {
		// TODO Auto-generated method stub
		return this.style ;
	}
	
	/**
	 * This method sets the style tag
	 * @param styleTag the style tag
	 */
	public void setStyleTag(HTMLElement styleTag) {
		this.style = styleTag;
	}
	// questo metodo aggiunge
	// questo � l'unico metodo che aggiunge
	// il tag stile alla testa del sito web
	// questo metodo per il momento � in grado
	// di settare uno stile interno
	@Override
	public HTMLDocument setStyleSheet(CSSStyle style) {
		this.styleSheet = style ;
		if (style instanceof CSSDefaultStyle) {
			// qui sappiamo che si tratta di uno stile interno
			if (this.style==null) {
				this.style = JjDom.document.createElement(HTMLElementType.STYLE);
				this.style.setAttribute("type","text/css");
			}
			this.style.setTextContent(this.styleSheet.toString());
			// aggiungo provvisoriamente il nodo alla testa se c'�
			if (this.head!=null) {
				this.head.appendChild(this.style);
			}
			
			// qui imposto il documento dello stile in maniera anonima
			((CSSDefaultStyle)this.styleSheet).setDocument(this);
		}
		return this  ;
	}

	@Override
	public HTMLDocument addClass(String className, HTMLElement... elements) {
		for (int i = 0; i < elements.length; i++) {
			elements[i].addClass(className);
		}
		return this ;
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

	// questo metodo ha lo stesso codice di quello dell'elemento
	// ma si sconsiglia il suo uso, poich� il documento non ha fratelli
	@Override
	public HTMLElements getAdiacentSiblingsByTag(String tagName) {
		return null ;
	}
	
	@Override
	public HTMLElements getGeneralSiblingsByTag(String tagName) {
		return null ;
	}

	// restituisce null, per una questione di logica
	@Override
	public HTMLNodeList getBrothers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HTMLNode next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HTMLNode previous() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HTMLElement createLink(String href, String text) {
		// TODO Auto-generated method stub
		return (HTMLElement) createElement(HTMLElementType.A)
		.setAttribute("href", href).setTextContent(text);
	}

	// restituisce il link 
	@Override
	public HTMLElement createImageLink(String srcImage, String href) {
		HTMLElement img,link = null ;
		img = JjDom.document.createElement(HTMLElementType.IMG);
		img.setAttribute("src", srcImage);
		link = JjDom.document.createElement(HTMLElementType.A);
		link.setAttribute("href",href);
		link.appendChild(img);
		return link ;
	}

	@Override
	public HTMLElement createMenu(String idMenu, String... items) {
		HTMLElement ul = JjDom.document.createElement(HTMLElementType.UL);
		ul.setId(idMenu);
		ArrayList<HTMLElement>list = new ArrayList<>();
		for (int i = 0; i < items.length; i++) {
			HTMLElement link = JjDom.document.createElement(HTMLElementType.A);
			link.setAttribute("href","#").setTextContent(items[i]);
			HTMLElement li = JjDom.document.createElement(HTMLElementType.LI);
			li.appendChild(link);
			ul.appendChild(li);
		}
		return ul ;
	}
	
	

	@Override
	public HTMLElement createForm(String action, String method) {
		HTMLElement form = JjDom.document.createElement(HTMLElementType.FORM);
		form.setAttribute("method",method);
		form.setAttribute("action",action);
		return form ;
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

	@SuppressWarnings("static-access")
	@Override
	public HTMLDocument write(String text) {
		final String jsCode = "document.write('"+text+"');";
		JjDom.window.executeJsMethod(jsCode);
		return this ;
	}

	@Override
	public HTMLElement createInput(String inputType, String name, String value) {
		HTMLElement input = JjDom.document.createElement(HTMLElementType.INPUT);
		if (inputType!=null) {
			input.setAttribute("type",inputType);
		}
		if (name!=null) {
			input.setAttribute("name",name);
		}
		if (value!=null) {
			input.setAttribute("value",value);
		}
		return input ;
	}

	@Override
	public HTMLElement createButton(String btnText, String btnId) {
		HTMLElement button = JjDom.document.createElement(HTMLElementType.BUTTON);
		button.setId(btnId).setTextContent(btnText);
		return button ;
	}

	@Override
	public HTMLElement createDiv(String divId) {
		return JjDom.document.createElement(HTMLElementType.DIV).setId(divId);
	}
	
	@Override
	public HTMLElement createDiv(String divClass, String divId) {
		return createDiv(divId).addClass(divClass);
	}
	
	@Override
	public StringBuffer jsSource() {
		return jsBuffer;
	}

	@Override
	public HTMLDocument setJsSource(StringBuffer buffer) {
		this.jsBuffer = buffer ;
		return this ;
	}

	@Override
	public HTMLDocument printJsSource() {
		System.out.println(jsSource().toString());
		return this ;
	}

	@Override
	public HTMLElement getJsSourceTag() {
		return jquerySourceTag ;
	}

	@Override
	public HTMLDocument setJsSourceTag(HTMLElement scriptTag) {
		// TODO Auto-generated method stub
		this.jquerySourceTag = scriptTag ;
		return this ;
	}

	@Override
	public String jqueryPath() {
		// TODO Auto-generated method stub
		return this.jqueryPath ;
	}

	@Override
	public HTMLDocument setJqueryPath(String jqueryPath) {
		// TODO Auto-generated method stub
		this.jqueryPath = jqueryPath ;
		return this ;
	}

	@Override
	public HTMLDocument bgColor(String color) {
		body.setAttribute("bgcolor",color);
		return this ;
	}

	@Override
	public HTMLDocument fgColor(String color) {
		body.setAttribute("text",color);
		return this ;
	}

	@Override
	public HTMLElement createElement(HTMLElementType type, String textElement) {
		HTMLElement element = createElement(type);
		element.setTextContent(textElement);
		return element ;
	}
	// version 1.0.7
	@Override
	public HTMLNode getNodeByPath(String nodePath) {
		
		HTMLNode currentNode = this ; // poich� si parte da questo elemento 
		
		// 1 passo :split del nodePath
		
		String[]split = nodePath.split("/");
		
		// faccio iterare i nomi dei nodi del path
		boolean found ;
		for (int i = 0; i < split.length; i++) {
			// ottengo il nome del nodeName
			
			String nodeName = split[i].trim();
			
			found = false ;
			
			// 2 passo faccio iterare i figli di questo nodo
			
			for (int j = 0; j < currentNode.getChildNodes().getLength(); j++) {
				
				HTMLNode node = currentNode.getChildNodes().item(j);
				
				// 3 passo : controllo per prima cosa il nome del nodo
				
				if (node.getNodeName().equals(nodeName)) {
					
					// bene abbiamo trovato il nodo
					// quindi la catena continua
					// passando al secondo nodeName del pathNode
					currentNode = node ;
					found = true ;
					break ;
				}
				else if(nodeName.contains("#")){
					// abbiamo un nodeName con id
					// controllo se l'elemento � di fatto un elemento 
					if (node instanceof HTMLElement) {
						
						// bene a questo punto prendo l'id in tanto
						String id = nodeName.substring(nodeName.indexOf("#")).replace("#","").trim();
						
						// sostituisco l'id nel nodeName 
						
						String replacedNodeName = nodeName.replace("#"+id,"");
						if (((HTMLElement)node).isPresent("id")) {
							if (((HTMLElement)node).getId().equals(id)&&node.getNodeName().equals(replacedNodeName)) {
								
								// anche qui si prende l'elemento e si continua con la catena
								found = true ;
								currentNode = node ;
								break ;
							}
						}
					}
				}	
			}
			// quindi quando si sta per passare alla prossima iterazione, quindi al prossimo nome del path
			// si controlla se l'elemento � stato trovato, in caso contrario
			// devo chiudere il metodo poich� la catena � stata spezzata.
			// quindi se cosi fosse, setto anche il currentNode a null
			// poich� faccio affidamento sulla variabile booleana, quindi
			// se la var � false, vuol dire che il currentNode � quello precedente
			// e quindi lo risetto a null
			if (!found){currentNode = null;break;}
		}
		return currentNode ;
	}
}
