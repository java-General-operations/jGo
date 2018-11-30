package cloud.jgo.test;

import javax.xml.ws.spi.http.HttpHandler;

import cloud.jgo.£;
import cloud.jgo.£Func;
import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.dom.HTMLRecursion;
import cloud.jgo.jjdom.dom.nodes.HTMLElement;
import cloud.jgo.jjdom.dom.nodes.HTMLElements;
import cloud.jgo.jjdom.dom.nodes.HTMLNodeList;
import cloud.jgo.jjdom.dom.nodes.Node;

public class TestMain {

	
	public static void main(String[] args) {
		
		JjDom.newDocument().useDoctype(true).setMinimalTags().home().jqueryInit();
		HTMLElement h1 = JjDom.document.createElement(HTMLElement.HTMLElementType.H1);
		h1.setTextContent("Mio sito");
		JjDom.document.getTitle().setTextContent("Titolo del sito");
		HTMLElement divParags,divLinks = null ;
		JjDom.document.getBody().setId("body");
		
		divParags = JjDom.document.createElement(HTMLElement.HTMLElementType.DIV);
		divLinks = JjDom.document.createElement(HTMLElement.HTMLElementType.DIV);
		divParags.setId("p-container");divLinks.setId("links-container");
		
		HTMLElement p,p2,p3 = null ;
		HTMLElement a,a2,a3 = null ;
		
		p = JjDom.document.createElement(HTMLElement.HTMLElementType.P);
		p2 = JjDom.document.createElement(HTMLElement.HTMLElementType.P);
		p3 = JjDom.document.createElement(HTMLElement.HTMLElementType.P);
		
		a = JjDom.document.createLink("#","Primo link");
		a2 = JjDom.document.createLink("#", "Secondo link");
		a3 = JjDom.document.createLink("#", "Terzo link");
		
		p.setTextContent("Primo paragrafo");
		p2.setTextContent("Secondo paragrafo");
		p3.setTextContent("Terzo paragrafo");
		
		// aggiugo delle classi 
		
		p.addClasses("primi","parags");
		p2.addClasses("secondi","parags");
		p3.addClasses("terzi","parags");
		
		a.addClasses("primi","links");
		a2.addClasses("secondi","links");
		a3.addClasses("terzi","links");
		
		divParags.appendChilds(p,p2,p3);
		divLinks.appendChilds(a,a2,a3);
		
		// inserisco un paragrafo esterno
		
		HTMLElement pExternal = JjDom.document.createElement(HTMLElement.HTMLElementType.P);
		pExternal.setTextContent("Paragrafo esterno");
		
		JjDom.document.getBody().appendChilds(h1,divParags,divLinks,pExternal);
		
		// si lavora :
		Node node = JjDom.document.getBody().getNodeByPath("#links-container/.primi");
		
	
		
	}
}
