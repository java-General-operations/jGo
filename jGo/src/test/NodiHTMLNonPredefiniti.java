package test;

import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.dom.nodes.html.HTMLDefaultElement;
import cloud.jgo.jjdom.dom.nodes.html.HTMLElement;
import cloud.jgo.jjdom.dom.nodes.html.HTMLElement.HTMLElementType;

public class NodiHTMLNonPredefiniti {
@SuppressWarnings("static-access")
public static void main(String[] args) {
	
	
	// adesso però vogliamo poter ampliare la lista di tipi predefiniti ...
	
	JjDom.newDocument().setMinimalTags().useDoctype(true).home().jqueryInit();
	
	
	// adesso mettiamo il caso che voglio aggiungere un nuovo tipo a quelli predefiniti
	
	
	HTMLElement.HTMLElementType newType = HTMLElementType.newType("el");
	
	// configuro il nuovo tipo :
	
	newType.setClosingTag(true).setThereCanBeMore(true);
	
	// con questa istruzione aggiungo il nuovo tipo a quelli predefiniti 
	
	HTMLElement.HTMLElementType.availableTypes.add(newType);
	
	
	// adesso mi creo gli elementi con questo tipo
	
	HTMLElement element,element2 ;
	
	
	
	
	
	
	
	
	
	
	
	
	
}
}
