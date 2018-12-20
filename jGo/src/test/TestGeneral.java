package test;

import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.dom.nodes.Document;
import cloud.jgo.jjdom.dom.nodes.html.HTMLDefaultDocument;
import cloud.jgo.jjdom.dom.nodes.html.color.HTMLColorDocument;

public class TestGeneral {
@SuppressWarnings("static-access")
public static void main(String[] args) {
	
	
	
	JjDom.newDocument().useDoctype(true).setMinimalTags().home().jqueryInit();
	
	
	
	JjDom.document.printMarkup();
	
	
	
	
}
}
