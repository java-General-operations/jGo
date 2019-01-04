package cloud.jgo.jjdom.dom.nodes.xml.color;

import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.dom.ColorRecursion;
import cloud.jgo.jjdom.dom.Colorable;
import cloud.jgo.jjdom.dom.nodes.Comment;
import cloud.jgo.jjdom.dom.nodes.Element;
import cloud.jgo.jjdom.dom.nodes.html.HTMLElement;
import cloud.jgo.jjdom.dom.nodes.html.color.HTMLColorElement;
import cloud.jgo.jjdom.dom.nodes.xml.XMLDocument;
import cloud.jgo.jjdom.dom.nodes.xml.XMLElement;
import cloud.jgo.utils.ColorString;

public class XMLColorDocument extends XMLDocument implements Colorable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getColorMarkup() {
		ColorString xmlCode = new ColorString();
		ColorRecursion.examines_xml(this, xmlCode,null); // provvisorio, poi gli dobbiamo passare il document
		String result = xmlCode.toString();
		return result;
	}

	
	public XMLColorDocument(String charsetName, JjDom home, String rootElementName) {
		super(charsetName, home); 
		this.rootElement = createElement(rootElementName);
		((XMLColorElement)this.rootElement).setParentNode(this);
		appendChild(this.rootElement);
	}


	public XMLColorDocument() {
		super();
	}


	@Override
	public XMLColorElement createElement(String elementName) {
		return new XMLColorElement(elementName, this);
	}
	
	@Override
	public XMLColorElement getRootElement() {
		// TODO Auto-generated method stub
		return (XMLColorElement) this.rootElement ;
	}
	
	@Override
	public XMLColorComment createComment(String comment) {
		// TODO Auto-generated method stub
		return new XMLColorComment(comment, this);
	}

}
