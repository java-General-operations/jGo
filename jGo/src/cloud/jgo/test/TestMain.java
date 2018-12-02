package cloud.jgo.test;

import javax.xml.ws.spi.http.HttpHandler;

import cloud.jgo.£;
import cloud.jgo.£Func;
import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.dom.HTMLRecursion;
import cloud.jgo.jjdom.dom.nodes.Element;
import cloud.jgo.jjdom.dom.nodes.Node;
import cloud.jgo.jjdom.dom.nodes.html.HTMLElement;
import cloud.jgo.jjdom.dom.nodes.xml.XMLDocument;
import cloud.jgo.jjdom.dom.nodes.xml.XMLElement;

public class TestMain {

	public static void main(String[] args) {
		// sviluppare i nodi XML adesso :)
		
		XMLDocument document = new XMLDocument("server.configuration","UTF-8");
		
		
		XMLElement proxy,lport,lhost = null ;
		
		
		proxy = new XMLElement("proxy", document);
		lport = new XMLElement("lport", document);
		lhost = new XMLElement("lhost", document);
		
		proxy.setTextContent("8.8.8.8");
		lport.setTextContent(3333+"");
		lhost.setTextContent("localhost");
		
		
		document.getRootElement().appendChild(proxy);
		document.getRootElement().appendChild(lport);
		document.getRootElement().appendChild(lhost);
		
		
	}
}
