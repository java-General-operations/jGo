package cloud.jgo.jjdom.dom.nodes.html.color;

import cloud.jgo.jjdom.dom.ColorRecursion;
import cloud.jgo.jjdom.dom.Colorable;
import cloud.jgo.jjdom.dom.Recursion;
import cloud.jgo.jjdom.dom.nodes.html.HTMLDefaultElement;
import cloud.jgo.jjdom.dom.nodes.html.HTMLDocument;
import cloud.jgo.utils.ColorString;

public class HTMLColorElement extends HTMLDefaultElement implements Colorable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected HTMLColorElement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HTMLColorElement(String elementName, HTMLDocument document) {
		super(elementName, document);
		// TODO Auto-generated constructor stub
	}

	// mi ridefinisco soltanto i metodi per il markup
	@Override
	public String getColorMarkup() {
		ColorString htmlCode = new ColorString();
		ColorRecursion.examines_html(this, htmlCode); // provvisorio, poi gli dobbiamo passare il document
		String result = htmlCode.toString();
		return result;
	}

	@Override
	protected void setType(HTMLElementType type) {
		// TODO Auto-generated method stub
		super.setType(type);
	}
}
