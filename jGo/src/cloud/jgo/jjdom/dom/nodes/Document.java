package cloud.jgo.jjdom.dom.nodes;
import java.util.List;
import java.util.Set;
import cloud.jgo.jjdom.dom.Manipulable;
import cloud.jgo.jjdom.dom.nodes.html.HTMLComment;
import cloud.jgo.jjdom.dom.nodes.html.HTMLDocument;
import cloud.jgo.jjdom.dom.nodes.html.HTMLElement;
public interface Document extends Node,Manipulable{
	/**
	 * This method creates a comment
	 * @param comment the comment text
	 * @return the comment
	 */
	public abstract Comment createComment(String comment);
	/**
	 * This method creates an element
	 * @param elementName the element name
	 * @return the element
	 */
	public Element createElement(String elementName);
	/**
	 * This method returns the root element
	 * @return the root element
	 */
	public abstract Element getRootElement();
	/**
	 * This method returns the document charset
	 * @return the document charset
	 */
	public abstract String getCharset();
	/**
	 * This method removes the nodes
	 * @param nodes the nodes to be removed
	 * @return the document on which the method was invoked
	 */
	public abstract Document removeNodes(Node...nodes);
	
	public abstract Set<?extends Comment>getComments();
	/**
	 * This method returns the comments list
	 * @return the comments list
	 */
	public abstract List<?extends Comment>getListComments();
	// version 1.0.7
	/**
	 * This method returns the format the file needs for this document.
	 * @return the document format - example : html
	 */
	public abstract String getDocumentFormat();
	/**
	 * 1 DEFAULT CHARSET : UTF_8
	 */
	public static final String CHARSET_UTF_8 = "UTF-8";
	/**
	 * 2 DEFAULT CHARSET : ISO-8859-1
	 */
	public static final String CHARSET_ISO_8859_1 = "ISO-8859-1";
}
