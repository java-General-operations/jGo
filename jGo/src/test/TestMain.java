/**
 * JGO - A pure Java library,
 * its purpose is to make life easier for the programmer.
 *
 * J - Java
 * G - General
 * O - Operations
 *
 * URL Software : https://www.jgo.cloud/
 * URL Documentation : https://www.jgo.cloud/docs/
 *
 * Copyright © 2018 - Marco Martire (www.jgo.cloud)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the MIT License.
 *
 * You may obtain a copy of License at :
 * https://www.jgo.cloud/LICENSE.txt
 *
 * To collaborate on this project, you need to do it from the software site.
 * 
 */
package test;
import static cloud.jgo.jjdom.JjDom.*;
import cloud.jgo.£;
import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.dom.HTMLElement;
import cloud.jgo.jjdom.jquery.Event;
import cloud.jgo.jjdom.jquery.jQueryfunction;

public class TestMain {
@SuppressWarnings("static-access")
public static void main(String[] args) {
	
	
	// BUG : se aggiungiamo dopo aver aggiunto il ready, istruzioni jquery
	// le aggiunge correttamente all'interno del ready, quindi l'unico
	// bug da risolvere è : se noi aggiungiamo una istruzione jquery
	// eppoi aggiungo il ready, il ready me lo aggiunge vuoto, quindi
	// non mi inserisce le istruzioni jquery nel ready, e noi questo
	// dobbiamo riuscire a fare, per forza, pomeriggio risolvo questo bug
	
	// verifichiamo questo bug
	
	
	JjDom.newDocument().useDoctype(true).setMinimalTags().home().jqueryInit();
	
	
	// mi creo qualche elemento 
	
	HTMLElement h1,h2,p,p2 = null ;
	
	h1 = JjDom.document.createElement(HTMLElement.HTMLElementType.H1);
	h2 = JjDom.document.createElement(HTMLElement.HTMLElementType.H2);
	p = JjDom.document.createElement(HTMLElement.HTMLElementType.P);
	p2 = JjDom.document.createElement(HTMLElement.HTMLElementType.P);
	
	// imposto i testi dei tags
	
	JjDom.document.getTitle().setTextContent("Titolo del sito");
	h1.setTextContent("Intestazione 1");
	h2.setTextContent("Intestazione 2");
	p.setTextContent("Paragrafo 1");
	p2.setTextContent("Paragrafo 2");
	
	// aggiungo gli elementi 
	JjDom.document.getBody().appendChilds(h1,h2,p,p2);
	
	
	// jquery code :
	
	$("p").hide("slow").show("slow");

	jquery(DOCUMENT).ready(new jQueryfunction() {
		
		@Override
		public void function(Event event) {
			
			
			
		}
	});
	
	JjDom.printDocumentMarkup();
	
}
}
