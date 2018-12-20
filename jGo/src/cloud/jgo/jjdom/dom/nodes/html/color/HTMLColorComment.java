package cloud.jgo.jjdom.dom.nodes.html.color;

import cloud.jgo.jjdom.dom.ColorRecursion;
import cloud.jgo.jjdom.dom.Recursion;
import cloud.jgo.jjdom.dom.nodes.Document;
import cloud.jgo.jjdom.dom.nodes.html.HTMLComment;
import cloud.jgo.utils.ColorString;

public class HTMLColorComment extends HTMLComment{

	public HTMLColorComment(String comment, Document document) {
		super(comment, document);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getMarkup() {
		ColorString htmlCode = new ColorString();
		ColorRecursion.examines_html(this, htmlCode); // provvisorio, poi gli dobbiamo passare il document
		String result = htmlCode.toString();
		return result;
	}

}
