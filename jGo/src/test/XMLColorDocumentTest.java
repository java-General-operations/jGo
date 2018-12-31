package test;

import cloud.jgo.jjdom.dom.nodes.Document;
import cloud.jgo.jjdom.dom.nodes.Node;
import cloud.jgo.jjdom.dom.nodes.xml.color.XMLColorDocument;
import cloud.jgo.jjdom.dom.nodes.xml.color.XMLColorElement;

public class XMLColorDocumentTest {
public static void main(String[] args) {
	
	
	
	XMLColorDocument doc = new XMLColorDocument(Document.CHARSET_UTF_8, null, "server.config");
	
	
	// aggiungo un nodo al root 
	
	XMLColorElement element = doc.createElement("lport");
	
	element.setTextContent(3333+"");
	
	doc.getRootElement().appendChild(element);
	
	// adesso mi trasferisco dal documento alla porta nodo 
	
	Node node = doc.getNodeByPath("server.config/lport");
	
	
	System.out.println(node);
	
	
	
	
	
	
	
}
}
