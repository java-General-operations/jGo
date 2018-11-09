package cloud.jgo.jjdom.test;

import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.dom.HTMLElement;
import cloud.jgo.jjdom.jquery.Event;
import cloud.jgo.jjdom.jquery.jQueryfunction;
import static cloud.jgo.jjdom.JjDom.*;
public class JjDomTest {
public static void main(String[] args) {
	
	
	// start
	
	
	// creazione del documento ....
	
	JjDom.newDocument().useDoctype(true).setMinimalTags().home().jqueryInit();
	
	
	// aggiungo qualche elemento 
	
	HTMLElement h1,h2,p,p2 = null ;
	
	h1 = JjDom.document.createElement(HTMLElement.HTMLElementType.H1);
	h2 = JjDom.document.createElement(HTMLElement.HTMLElementType.H2);
	p = JjDom.document.createElement(HTMLElement.HTMLElementType.P);
	p2 = JjDom.document.createElement(HTMLElement.HTMLElementType.P);
	
	h1.setTextContent("Intestazione primaria");
	h2.setTextContent("Intestazione secondaria");
	p.setTextContent("Paragrafo 1");
	p2.setTextContent("Paragrafo 2");
	JjDom.document.getTitle().setTextContent("Titolo del sito");
	
	// aggiungo gli elementi 
	
	JjDom.document.getBody().appendChilds(h1,h2,p,p2);
	
	
	
	$("document").ready(new jQueryfunction() {
		
		@Override
		public void function(Event event) {
			jquery("p").hide("slow");
		}
	});
	
	
	
	printDocumentMarkup();

}
}
