package cloud.jgo.jjdom.dom.nodes.xml.color;

import cloud.jgo.jjdom.dom.ColorRecursion;
import cloud.jgo.jjdom.dom.nodes.Document;
import cloud.jgo.jjdom.dom.nodes.html.HTMLComment;
import cloud.jgo.jjdom.dom.nodes.html.color.Colorable;
import cloud.jgo.utils.ColorString;

public class XMLColorComment extends HTMLComment implements Colorable{

	public XMLColorComment(String comment, Document document) {
		super(comment, document);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getColorMarkup() {
		ColorString xmlCode = new ColorString();
		ColorRecursion.examines_xml(this, xmlCode,null); // provvisorio, poi gli dobbiamo passare il document
		String result = xmlCode.toString();
		return result;
	}

}
