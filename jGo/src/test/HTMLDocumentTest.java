package test;

import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.dom.nodes.Element;

public class HTMLDocumentTest {
public static void main(String[] args) {
	
	
	JjDom.newDocument().setMinimalTags();
	
	
	Element secondoRoot = JjDom.document.createElement("h1");
	
	
	JjDom.document.appendChild(secondoRoot);
	
	JjDom.document.printMarkup();
	
	
}
}
