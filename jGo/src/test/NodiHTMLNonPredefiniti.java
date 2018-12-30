package test;

import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.dom.nodes.html.HTMLDefaultElement;
import cloud.jgo.jjdom.dom.nodes.html.HTMLElement;
import cloud.jgo.jjdom.dom.nodes.html.HTMLElement.HTMLElementType;

public class NodiHTMLNonPredefiniti {
@SuppressWarnings("static-access")
public static void main(String[] args) {
	
	
	
	
	JjDom.newDocument().setMinimalTags().useDoctype(true).home().jqueryInit();
	
	
	// mi creo gli elementi 
	
	
	HTMLElement el,el2 = null ;
	
	HTMLElement.HTMLElementType newType = HTMLElementType.newType("el");
	newType.setClosingTag(true);
	newType.setThereCanBeMore(true);
	
	el = HTMLElement.createNewElement("el",newType,JjDom.document);
	el2 = HTMLElement.createNewElement("el", newType, JjDom.document);
	
	// aggiungo il testo 
	String testo = "testo di prova";
	
	el.setTextContent(testo);
	el2.setTextContent(testo);
	
	
	JjDom.document.getBody().appendChilds(el,el2);
	
	JjDom.printDocumentMarkup();
	
	

	
	
	
	
	
}
}
