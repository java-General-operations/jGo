package test;

import cloud.jgo.jjdom.dom.nodes.Element;
import cloud.jgo.jjdom.dom.nodes.xml.XMLDocument;
import cloud.jgo.jjdom.dom.nodes.xml.XMLElement;

public class XMLDocumentTest {
public static void main(String[] args) {
	
	
	
	
	XMLDocument document = new XMLDocument();
	
	Element rootElement = document.createElement("server.config");
	
	document.appendChild(rootElement);
	
	// okok mi creo gli elementi 
	
	XMLElement host,port ;
	host = (XMLElement) document.createElement("host");
	port = (XMLElement)document.createElement("port");
	
	host.setTextContent("192.168.1.3");
	port.setTextContent("3333");
	
	
	document.getRootElement().appendChilds(host,port);
	
	document.printMarkup();
	
	
	
	
	
	
	
}
}
