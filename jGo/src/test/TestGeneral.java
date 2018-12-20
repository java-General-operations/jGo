package test;

import org.fusesource.jansi.Ansi.Color;

import cloud.jgo.j£;
import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.dom.nodes.Document;
import cloud.jgo.jjdom.dom.nodes.html.HTMLDefaultDocument;
import cloud.jgo.jjdom.dom.nodes.html.color.HTMLColorDocument;

public class TestGeneral {
@SuppressWarnings("static-access")
public static void main(String[] args) {
	
	
	
	String startTag = "<h2>";
	
	
	
	String stringaTrasformata = j£.colorTheStringsSyntax(startTag,Color.RED);
	
	System.out.println(stringaTrasformata);
	
	
	
}
}
