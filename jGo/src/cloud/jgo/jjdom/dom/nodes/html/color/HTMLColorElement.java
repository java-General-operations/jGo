package cloud.jgo.jjdom.dom.nodes.html.color;

import cloud.jgo.jjdom.dom.ColorRecursion;
import cloud.jgo.jjdom.dom.Recursion;
import cloud.jgo.jjdom.dom.nodes.html.HTMLDefaultElement;
import cloud.jgo.utils.ColorString;

public class HTMLColorElement extends HTMLDefaultElement{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	// mi ridefinisco soltanto i metodi per il markup 
	
	@Override
	public String getMarkup() {
		ColorString htmlCode = new ColorString();
		ColorRecursion.examines_html(this,htmlCode); // provvisorio, poi gli dobbiamo passare il document
		String result = htmlCode.toString();
		return result;
	}

}
