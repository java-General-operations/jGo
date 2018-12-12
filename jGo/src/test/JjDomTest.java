package test;

import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.dom.nodes.Element;
import cloud.jgo.jjdom.dom.nodes.html.HTMLElement;
import cloud.jgo.jjdom.jquery.Event;
import cloud.jgo.jjdom.jquery.jQueryfunction;

import static cloud.jgo.jjdom.JjDom.jquery;
public class JjDomTest {
@SuppressWarnings("static-access")
public static void main(String[] args) {
	
	
	
	JjDom.newDocument().setMinimalTags().useDoctype(true).home().jqueryInit();
	
	// aggiungo qualche elemento
	
	Element h1,h2,link ;
	
	h1 = JjDom.document.createElement(HTMLElement.HTMLElementType.H1);
	h2 = JjDom.document.createElement(HTMLElement.HTMLElementType.H2);
	link = JjDom.document.createLink("#","link");
	
	h1.setTextContent("website di Wasp91");
	h2.setTextContent("This website is private #");
	
	JjDom.document.getTitle().setTextContent("Titolo del sito");
	
	JjDom.document.getBody().appendChilds(h1,h2,link);
	
	JjDom.printDocumentMarkup();

	// jquery code :
	
	
			
			jquery("a,h2").hide("slow").show("slow");
			
	JjDom.preview();
}
}
