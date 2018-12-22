package test;

import org.fusesource.jansi.AnsiConsole;
import org.fusesource.jansi.Ansi.Color;

import cloud.jgo.j£;
import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.dom.DomColors;
import cloud.jgo.jjdom.dom.nodes.Document;
import cloud.jgo.jjdom.dom.nodes.Element;
import cloud.jgo.jjdom.dom.nodes.html.HTMLDefaultDocument;
import cloud.jgo.jjdom.dom.nodes.html.color.HTMLColorDocument;

public class HTMLColorDocumentTest {
@SuppressWarnings("static-access")
public static void main(String[] args) {
	
	j£.ANSI_CONSOLE.systemInstall();
	
	DomColors.TAG_COLOR = Color.MAGENTA;
	DomColors.NODENAME_COLOR = Color.GREEN;
	DomColors.ATTRIBUTE_VALUE_COLOR = Color.WHITE;
	
	HTMLColorDocument document = new HTMLColorDocument(Document.CHARSET_UTF_8, null);
	
	document.useDoctype(true);
	
	document.setMinimalTags();
	
	System.out.println(document.getColorMarkup());
	
	j£.ANSI_CONSOLE.systemUninstall();
}
}
