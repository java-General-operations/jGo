package cloud.jgo.jjdom.dom.nodes;
import java.util.Map;
import cloud.jgo.jjdom.dom.Manipulable;
import cloud.jgo.jjdom.dom.nodes.html.HTMLElement;
public interface Element extends Node,Manipulable{
	/**
	 * this method checks if this element has brothers
	 * @return true if has brothers
	 */
	public abstract boolean hasBrothers();
	/**
	 * This method returns the element attributes
	 * @return the element attributes
	 */
	public abstract Map<String, String> getAttributes();
	/**
	 * This method sets an attribute
	 * @param attr the attribute
	 * @param val the attribute value
	 * @return the element on which the method was invoked
	 */
	public abstract Element setAttribute(String attr,String val);
	/**
	 * This method replaces an attribute value
	 * @param attr the attribute
	 * @param newValue new attribute value
	 * @return the element on which the method was invoked
	 */
	public abstract Element replaceAttributeValue(String attr,String newValue);
	/**
	 * This method removes an attribute
	 * @param attr the attribute
	 * @return the element on which the method was invoked
	 */
	public abstract Element removeAttribute(String attr);
	/**
	 * This method returns the attribute
	 * @param attr the attribute text
	 * @return the attribute
	 */
	public abstract String getAttribute(String attr);
	/**
	 * This method returns the attribute value
	 * @param attr the attribute text
	 * @return the attribute value
	 */
	public abstract String getAttributeValue(String attr);
	/**
	 * This method gets the previous sibling
	 * @return the previous sibling
	 */
	public abstract Node getPreviousSibling();
	/**
	 * This method checks if the attribute passed as a parameter is present
	 * @param attribute the attribute
	 * @return true if is present
	 */
	public abstract boolean isPresent(String attribute);
	/**
	 * This method checks if the element has attributes
	 * @return true if has attributes
	 */
	public abstract boolean hasAttributes();
	// version : 1.0.1
	/**
	 * This method returns the complete path:
	 * {@link HTMLElement#getBaseURI()} + {@link HTMLElement#getPath()}
	 * 
	 * @return the complete path
	 */
	public abstract String getCompletePath();
}
