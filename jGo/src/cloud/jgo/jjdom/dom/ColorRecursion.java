package cloud.jgo.jjdom.dom;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.fusesource.jansi.Ansi.Color;

import cloud.jgo.j£;
import cloud.jgo.£;
import cloud.jgo.jjdom.dom.nodes.Comment;
import cloud.jgo.jjdom.dom.nodes.Element;
import cloud.jgo.jjdom.dom.nodes.Node;
import cloud.jgo.jjdom.dom.nodes.Node.NodeType;
import cloud.jgo.jjdom.dom.nodes.html.HTMLComment;
import cloud.jgo.jjdom.dom.nodes.html.HTMLDefaultElement;
import cloud.jgo.jjdom.dom.nodes.html.HTMLDocument;
import cloud.jgo.jjdom.dom.nodes.html.HTMLElement;
import cloud.jgo.jjdom.dom.nodes.html.color.HTMLColorElement;
import cloud.jgo.jjdom.dom.nodes.xml.XMLDocument;
import cloud.jgo.jjdom.dom.nodes.xml.XMLElement;
import cloud.jgo.utils.ColorString;

public abstract class ColorRecursion {

	public static void examines_html(Node node, ColorString htmlCode) {
		// for doctype from here to @
		String key = null;
		if (node instanceof Element) {
			// qui che sappiamo che si tratta di un elemento html
			// gestiamo gli attributi
			if (((Element) node).hasAttributes()) {
				// System.out.println("L'elemento "+node.getNodeName()+" ha i seguenti attributi
				// :");
				Map<String, String> attributes = ((Element) node).getAttributes();
				Iterator<Entry<String, String>> iterator = attributes.entrySet().iterator();
				while (iterator.hasNext()) {
					Map.Entry<java.lang.String, java.lang.String> entry = (Map.Entry<java.lang.String, java.lang.String>) iterator
							.next();
					key = entry.getKey();
					// casomai eliminare da qui a @
					if (((HTMLDefaultElement) node).getStartTag().contains(" " + key + "='" + entry.getValue() + "'")) {
						((HTMLDefaultElement) node).setStartTag(((HTMLDefaultElement) node).getStartTag()
								.replace(" " + key + "='" + entry.getValue() + "'", ""));
					}
					// @ - in tanto
					((HTMLDefaultElement) node).setStartTag(((HTMLDefaultElement) node).getStartTag().replace(">", ""));
					((HTMLDefaultElement) node).setStartTag(((HTMLDefaultElement) node).getStartTag() + " " + key + "='"
							+ entry.getValue() + "'" + ">");
					// System.out.println(key+":"+entry.getValue());
				}
			}
			if (((HTMLElement) node).getType().equals(HTMLElement.HTMLElementType.HTML)) {
				// qui ottengo il documento del nodo
				HTMLDocument doc = (HTMLDocument) ((Element) node).getDocument();
				if (doc.doctypeIsPresent()) {
					// inserisco il doctype
					htmlCode.append('<',DomColors.TAG_COLOR).append("!DOCTYPE html",DomColors.NODENAME_COLOR).append(">\n",DomColors.TAG_COLOR);
				}
			}
		}
		// @
		if (node.getTextContent() != null) {
			if (node.getNodeType().equals(NodeType.ELEMENT)) {
//				htmlCode.append(((HTMLColorElement) node).getStartTag());
				htmlCode.append("<",DomColors.TAG_COLOR).append(j£.colorTheStringsSyntax(((HTMLDefaultElement) node).getStartTag().replace("<","").replace(">",""),DomColors.ATTRIBUTE_VALUE_COLOR),DomColors.NODENAME_COLOR).append(">",DomColors.TAG_COLOR);
			} else if (node.getNodeType().equals(NodeType.COMMENT)) {
//				htmlCode.append(((HTMLComment) node).getStartTag());
				htmlCode.append(((HTMLComment) node).getStartTag(),DomColors.COMMENT_COLOR);
			}
			// questo controllo è valido solo per commenti
			if (node.getNodeType().equals(NodeType.COMMENT)) {
				htmlCode.append(node.getTextContent(),DomColors.COMMENT_COLOR); // provvisoria	
			}
			else {
				htmlCode.append(node.getTextContent(),DomColors.NODEVALUE_COLOR); // provvisoria	
			}
		} else {
			if (node instanceof Element) {
//				htmlCode.append(((HTMLColorElement) node).getStartTag() + "\n");
				htmlCode.append("<",DomColors.TAG_COLOR).append(j£.colorTheStringsSyntax(((HTMLDefaultElement) node).getStartTag().replace("<","").replace(">",""),DomColors.ATTRIBUTE_VALUE_COLOR),DomColors.NODENAME_COLOR).append(">\n",DomColors.TAG_COLOR);
			}
		}

		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			examines_html(node.getChildNodes().item(i), htmlCode);
		}

		// chiudo il tag
		if (node instanceof Element) {
			if (((HTMLDefaultElement) node).getType() != null) {
				// solo se ha il tag di chisura lo inseriamo nel codice html
				if (((HTMLDefaultElement) node).getType().hasClosingTag()) {
					// per il momento inserisco una @ per capire
//					htmlCode.append(((HTMLColorElement) node).getEndTag() + "\n");
					htmlCode.append("</",DomColors.TAG_COLOR).append(((HTMLDefaultElement) node).getEndTag().replace("</","").replace(">",""),DomColors.NODENAME_COLOR).append(">\n",DomColors.TAG_COLOR);
				}
			} else {
				// di sicuro se il nodo non ha un tipo di riferimento
				// chiudiamo in maniera standart : con il tag di chiusura
				htmlCode.append("</",DomColors.TAG_COLOR).append(((HTMLDefaultElement) node).getEndTag().replace("</","").replace(">",""),DomColors.NODENAME_COLOR).append(">\n",DomColors.TAG_COLOR);
			}
		} else {
			// qui invece significa che non è un elemento html
			// quindi deve essere per forza un commento, almeno per il momento
			// magari per sicurezza:controllo che sia cosi
			if (node instanceof HTMLComment) {
//				htmlCode.append(((HTMLComment) node).getEndTag() + "\n");
				htmlCode.append(((HTMLComment) node).getEndTag()+"\n",DomColors.COMMENT_COLOR);
			}
		}
	}
	
	// okok ora dobbiamo sviluppare questo metodo : 
		public static void examines_xml(Node node, StringBuffer xmlCode, String charset) {
			// for doctype from here to @
			if (node instanceof XMLDocument) {
				xmlCode.append(
						"<?xml version=" + £.escp(XMLDocument.XML_VERSION) + " encoding=" + £.escp(charset) + "?>\n");
			}
			String key = null;
			if (node instanceof Element) {
				// qui che sappiamo che si tratta di un elemento html
				// gestiamo gli attributi
				if (((Element) node).hasAttributes()) {
					// System.out.println("L'elemento "+node.getNodeName()+" ha i seguenti attributi
					// :");
					Map<String, String> attributes = ((Element) node).getAttributes();
					Iterator<Entry<String, String>> iterator = attributes.entrySet().iterator();
					while (iterator.hasNext()) {
						Map.Entry<java.lang.String, java.lang.String> entry = (Map.Entry<java.lang.String, java.lang.String>) iterator
								.next();
						key = entry.getKey();
						// casomai eliminare da qui a @
						if (((XMLElement) node).getStartTag().contains(" " + key + "='" + entry.getValue() + "'")) {
							((XMLElement) node).setStartTag(((XMLElement) node).getStartTag()
									.replace(" " + key + "='" + entry.getValue() + "'", ""));
						}
						// @ - in tanto
						((XMLElement) node).setStartTag(((XMLElement) node).getStartTag().replace(">", ""));
						((XMLElement) node).setStartTag(
								((XMLElement) node).getStartTag() + " " + key + "='" + entry.getValue() + "'" + ">");
						// System.out.println(key+":"+entry.getValue());
					}
				}
			}
			// @
			if (node.getTextContent() != null) {
				if (node.getNodeType().equals(NodeType.ELEMENT)) {
					xmlCode.append(((XMLElement) node).getStartTag());
				} else if (node.getNodeType().equals(NodeType.COMMENT)) {
					xmlCode.append(((HTMLComment) node).getStartTag());
				}
				xmlCode.append(node.getTextContent());
			} else {
				if (node instanceof Element) {
					xmlCode.append(((XMLElement) node).getStartTag() + "\n");
				}
			}
			for (int i = 0; i < node.getChildNodes().getLength(); i++) {
				examines_xml(node.getChildNodes().item(i), xmlCode, charset);
			}
			// chiudo il tag
			if (node instanceof Element) {
				// di sicuro se il nodo non ha un tipo di riferimento
				// chiudiamo in maniera standart : con il tag di chiusura
				xmlCode.append(((XMLElement) node).getEndTag() + "\n");
			} else {
				// qui invece significa che non è un elemento html
				// quindi deve essere per forza un commento, almeno per il momento
				// magari per sicurezza:controllo che sia cosi
				if (node instanceof Comment) {
					xmlCode.append(((HTMLComment) node).getEndTag() + "\n");
				}
			}
		}
}
