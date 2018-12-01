package cloud.jgo.test;

import javax.xml.ws.spi.http.HttpHandler;

import cloud.jgo.£;
import cloud.jgo.£Func;
import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.dom.HTMLRecursion;
import cloud.jgo.jjdom.dom.nodes.Element;
import cloud.jgo.jjdom.dom.nodes.Node;
import cloud.jgo.jjdom.dom.nodes.html.HTMLElement;
import cloud.jgo.jjdom.dom.nodes.html.HTMLNodeList;

public class TestMain {

	public static void main(String[] args) {
		
		
		
		JjDom.newDocument().setMinimalTags().useDoctype(true).home().jqueryInit();
		
		
		JjDom.document.getTitle().setTextContent("Io sono il titolo della pagina web");
		
		// aggiungo qualche elemento 
		Element h1,h2 = null ;
		
		// okok prossimo passi gestire il metodo che crea
		// l'elemento secondo il tipo, e quello che 
		// ottiene i nodi tramite il tipo, questi devono
		// essere riservati per il documento HTML
		// quindi quelli per creare il documento XML
		// devono essere diversi, vedere come meglio gestire
		// in tanto vedere se ci sn errori, e correggere eventuali
		// bugs
		
		
		
	}
}
