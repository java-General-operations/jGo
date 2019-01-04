package cloud.jgo.jjdom.dom.nodes.xml.color;

import cloud.jgo.jjdom.dom.ColorRecursion;
import cloud.jgo.jjdom.dom.Colorable;
import cloud.jgo.jjdom.dom.nodes.xml.XMLDocument;
import cloud.jgo.jjdom.dom.nodes.xml.XMLElement;
import cloud.jgo.utils.ColorString;

public class XMLColorElement extends XMLElement implements Colorable{
	
	
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

	public XMLColorElement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public XMLColorElement(String elementName, XMLDocument document) {
		super(elementName, document);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public XMLColorDocument getDocument() {
		// TODO Auto-generated method stub
		return (XMLColorDocument) super.getDocument();
	}

}
