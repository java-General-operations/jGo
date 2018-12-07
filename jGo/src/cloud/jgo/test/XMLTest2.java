package cloud.jgo.test;

import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.dom.nodes.Comment;
import cloud.jgo.jjdom.dom.nodes.Document;
import cloud.jgo.jjdom.dom.nodes.Element;

public class XMLTest2 {
public static void main(String[] args) {
	
	
	// okok l'update si fa al momento della migrazione
	// o meglio dopo la migrazione
	
	
	Document document = JjDom.connect("localhost","wasp91","wasp91dayno").download("upload/test/ven.xml");
	
	System.out.println(document.getMarkup());
	
}
}
