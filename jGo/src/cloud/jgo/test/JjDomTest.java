package cloud.jgo.test;

import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.dom.nodes.Element;
import cloud.jgo.jjdom.dom.nodes.html.HTMLElement;
import cloud.jgo.jjdom.jquery.Event;
import cloud.jgo.jjdom.jquery.jQueryfunction;

public class JjDomTest {
public static void main(String[] args) {
	// concludere questa versione di JjDom con il metodo is
	// 2 : creare i nuovi metodi jquery
	// 3 : completare il dom xml, quindi aggiungere i metodi per salvare/serializzare il file
	
	
	JjDom.newDocument().useDoctype(true).setMinimalTags().home().jqueryInit();
	
	// aggiungo gli elementi 
	
	Element h1,divParags,divLinks,p,p2,p3,link,link2,link3 ;
	
	h1 = JjDom.document.createElement(HTMLElement.HTMLElementType.H1);
	divParags = JjDom.document.createElement(HTMLElement.HTMLElementType.DIV);
	divLinks = JjDom.document.createElement(HTMLElement.HTMLElementType.DIV);
	divLinks = JjDom.document.createElement(HTMLElement.HTMLElementType.DIV);
	p = JjDom.document.createElement(HTMLElement.HTMLElementType.P);
	p2 = JjDom.document.createElement(HTMLElement.HTMLElementType.P);
	p3 = JjDom.document.createElement(HTMLElement.HTMLElementType.P);
	link = JjDom.document.createElement(HTMLElement.HTMLElementType.A);
	link2 = JjDom.document.createElement(HTMLElement.HTMLElementType.A);
	link3 = JjDom.document.createElement(HTMLElement.HTMLElementType.A);
	
	// imposto i testi dei nodi 
	
	JjDom.document.getTitle().setTextContent("Titolo del sito");
	h1.setTextContent("Website");
	p.setTextContent("1 paragrafo");
	p2.setTextContent("2 paragrafo");
	p3.setTextContent("3 paragrafo");
	link.setTextContent("1 link");
	link2.setTextContent("2 link");
	link3.setTextContent("3 link");
	
	
	
	// imposto gli id dei divs
	divLinks.setAttribute("id","links-div");
	divParags.setAttribute("id","parags-div");		
	divParags.setTextContent("div dei paragrafi");
	
	// aggiungo i nodi al documento 
	
	divParags.appendChilds(p,p2,p3);
	divLinks.appendChilds(link,link2,link3);
	
	JjDom.document.getBody().appendChilds(h1,divParags,divLinks);
	
    // cancello tutti i figli di body 
	
	JjDom.printDocumentMarkup();

}
}
