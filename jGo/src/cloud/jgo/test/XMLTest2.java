package cloud.jgo.test;

import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.dom.nodes.Document;

public class XMLTest2 {
public static void main(String[] args) {
	
	
	Document document = JjDom.connect("localhost","wasp91","wasp91dayno").download("upload/test/ven.xml");
	
	
	System.out.println(document.getMarkup());
	
	
	
}
}
