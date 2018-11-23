package cloud.jgo.test;

import javax.swing.text.html.HTML;

import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.dom.HTMLDocument;
import cloud.jgo.jjdom.dom.HTMLElement;
import cloud.jgo.jjdom.dom.HTMLElements;

public class JjDomTest {
public static void main(String[] args) {
	
	
	
	HTMLDocument document = JjDom.newDocument().setMinimalTags().useDoctype(true).home().jqueryInit().document;
	
	HTMLDocument document2 = JjDom.newDocument().setMinimalTags().useDoctype(true).home().jqueryInit().document;
	
	document.getTitle().setTextContent("Titolo del documento");
	document2.getTitle().setTextContent("Titolo del secondo documento");
	
	HTMLElement h1,h1_2 = null ;
	h1 = document.createElement(HTMLElement.HTMLElementType.H1);
	h1_2 = document2.createElement(HTMLElement.HTMLElementType.H1);
	
	h1.setTextContent("Primo documento");
	h1_2.setTextContent("Secondo documento");
	
	document.getBody().appendChild(h1);
	document2.getBody().appendChild(h1_2);
	

	// fino a qui bene
	// ora vogliamo lavorare con jquery su entrambi i file
	
	
	JjDom.jquery("h1").hide(4000).show(4000).preview().printDocumentMarkup();
	
	// adesso sul primo documento chiamo una istruzione diversa

	JjDom.swicth(document).$("h1").hide(4000).show(4000).preview().printDocumentMarkup();
	
	
	
	
	
	
	
	
	
	
}
}
