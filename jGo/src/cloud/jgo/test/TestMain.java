package cloud.jgo.test;

import javax.xml.ws.spi.http.HttpHandler;

import cloud.jgo.£;
import cloud.jgo.£Func;
import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.dom.Recursion;
import cloud.jgo.jjdom.dom.nodes.Document;
import cloud.jgo.jjdom.dom.nodes.Element;
import cloud.jgo.jjdom.dom.nodes.Node;
import cloud.jgo.jjdom.dom.nodes.html.HTMLDefaultDocument;
import cloud.jgo.jjdom.dom.nodes.html.HTMLDocument;
import cloud.jgo.jjdom.dom.nodes.html.HTMLElement;
import cloud.jgo.jjdom.dom.nodes.xml.XMLDocument;
import cloud.jgo.jjdom.dom.nodes.xml.XMLElement;

public class TestMain {

	public static void main(String[] args) {
		
		Document document = new XMLDocument(Document.CHARSET_UTF_8,null,"exploit.conf");
		
		// windows/x64/meterpreter/reverse_tcp
		// mi creo gli elementi 
		
		Element moduleElement = null ;
		
		// elementi dell'elemento modulo 
		
		Element osName,osArch,service,vulnerability = null ;
		
		Element payload = null ;
		
		Element lhost,lport = null ;
		
		lhost = document.createElement("LHOST");
		lport = document.createElement("LPORT");
		
		
		moduleElement = document.createElement("module");
		osName = document.createElement("osName");
		osArch = document.createElement("osArch");
		service = document.createElement("service");
		vulnerability = document.createElement("vulnerability");
		
		osName.setTextContent("windows");
		osArch.setTextContent("x64");
		service.setTextContent("frxservice");
		vulnerability.setTextContent("reverse_tcp");
		
		// creo il nodo del payload 
		
		payload = document.createElement("payload");
		
		lhost.setTextContent("192.168.1.2");
		lport.setTextContent("3333");
		
		payload.appendChilds(lhost,lport);
		
		moduleElement.appendChilds(osName,osArch,service,vulnerability);
		
		document.getRootElement().appendChilds(moduleElement,payload);
		
		document.printMarkup();
		
		
		
		
		
		
		
		
	}
}
