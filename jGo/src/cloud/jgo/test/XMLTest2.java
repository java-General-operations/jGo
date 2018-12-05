package cloud.jgo.test;

import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.dom.nodes.Document;
import cloud.jgo.jjdom.dom.nodes.Node;

public class XMLTest2 {
public static void main(String[] args) {
	
	Document document = JjDom.deserializes("doc.ser");
	
	Node telNode = document.getNodeByPath("wasp.project/contacts/contact/tel");
	
	System.out.println(telNode.getMarkup());

}
}
