package cloud.jgo.test;

import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.dom.nodes.Comment;
import cloud.jgo.jjdom.dom.nodes.Document;
import cloud.jgo.jjdom.dom.nodes.Element;
import cloud.jgo.jjdom.dom.nodes.xml.XMLDocument;

public class XMLTest2 {
public static void main(String[] args) {
	
	
	
	Document document = JjDom.connect("localhost","wasp91","wasp91dayno").download("upload/test/ven.xml");
	Element rootElement = document.getRootElement();
	System.out.println(rootElement.getMarkup());
	
}
}
