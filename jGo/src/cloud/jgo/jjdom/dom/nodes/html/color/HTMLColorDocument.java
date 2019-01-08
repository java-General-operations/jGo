package cloud.jgo.jjdom.dom.nodes.html.color;

import java.util.List;

import javax.swing.JOptionPane;

import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.dom.ColorRecursion;
import cloud.jgo.jjdom.dom.Colorable;
import cloud.jgo.jjdom.dom.Recursion;
import cloud.jgo.jjdom.dom.nodes.Node;
import cloud.jgo.jjdom.dom.nodes.NodeList;
import cloud.jgo.jjdom.dom.nodes.html.HTMLComment;
import cloud.jgo.jjdom.dom.nodes.html.HTMLDefaultDocument;
import cloud.jgo.jjdom.dom.nodes.html.HTMLDefaultElement;
import cloud.jgo.jjdom.dom.nodes.html.HTMLElement;
import cloud.jgo.jjdom.dom.nodes.html.HTMLElement.HTMLElementType;
import cloud.jgo.utils.ColorString;

public class HTMLColorDocument extends HTMLDefaultDocument implements Colorable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HTMLColorDocument(String charsetName, JjDom home) {
		super(charsetName, home);
		// mi creo il nodo root
		this.rootElement = createColorElement(HTMLElement.HTMLElementType.HTML);
		((HTMLColorElement) this.rootElement).setParentNode(this);
		appendChild(this.rootElement);
	}

	@Override
	public String getColorMarkup() {
		ColorString htmlCode = new ColorString();
		ColorRecursion.examines_html(this, htmlCode); // provvisorio, poi gli dobbiamo passare il document
		String result = htmlCode.toString();
		return result;
	}

	@Override
	public HTMLColorElement getRootElement() {
		// TODO Auto-generated method stub
		return (HTMLColorElement) this.rootElement;
	}

	public HTMLColorElement createColorElement(HTMLElementType type) {
		HTMLColorElement element = null;
		for (int i = 0; i < HTMLElementType.availableTypes.size(); i++) {
			if (type.equals(HTMLElementType.availableTypes.get(i))) {
				element = new HTMLColorElement(type.toString(), this);
				element.setType(type);
				break;
			}
		}
		return element;
	}

	public HTMLColorElement createColorElement(String elementName) {
		HTMLElementType type = null;
		List<HTMLElement.HTMLElementType> types = HTMLElementType.availableTypes;
		for (HTMLElementType htmlElementType : types) {
			if (htmlElementType.toString().equals(elementName)) {
				type = htmlElementType;
				break;
			}
		}
		if (type != null) {
			return createColorElement(type);
		} else {
			return null;
		}
	}
}
