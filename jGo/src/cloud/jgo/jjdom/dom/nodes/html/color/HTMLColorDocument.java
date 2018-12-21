package cloud.jgo.jjdom.dom.nodes.html.color;
import cloud.jgo.jjdom.JjDom;
import cloud.jgo.jjdom.dom.ColorRecursion;
import cloud.jgo.jjdom.dom.Recursion;
import cloud.jgo.jjdom.dom.nodes.html.HTMLDefaultDocument;
import cloud.jgo.jjdom.dom.nodes.html.HTMLDefaultElement;
import cloud.jgo.utils.ColorString;
public class HTMLColorDocument extends HTMLDefaultDocument implements Colorable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected HTMLColorDocument(String charsetName, JjDom home) {
		super(charsetName, home);
		// TODO Auto-generated constructor stub
	}
	public HTMLColorDocument(String charsetName, String baseUri, JjDom home) {
		super(charsetName, baseUri, home);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getColorMarkup() {
		ColorString htmlCode = new ColorString();
		ColorRecursion.examines_html(this, htmlCode); // provvisorio, poi gli dobbiamo passare il document
		String result = htmlCode.toString();
		return result;
	}
	
	public HTMLColorComment createColorComment(String comment) {
		return new HTMLColorComment(comment,this);
	}
	
	public HTMLColorElement createColorElement(String elementName) {
		return new HTMLColorElement(elementName, this);
	}
	
}
