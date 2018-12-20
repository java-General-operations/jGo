package cloud.jgo.jjdom;

import cloud.jgo.jjdom.dom.nodes.html.HTMLDocument;

// version 1.0.9
public class InvalidDocumentTypeException extends Exception {
	private static final long serialVersionUID = 1L;
	private Class<?extends HTMLDocument> clazz = null;
	public InvalidDocumentTypeException(Class<?extends HTMLDocument> clazz) {
		// TODO Auto-generated constructor stub
		super(" - Document Type non supported #");
		this.clazz = clazz;
		clazz = null ;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Exception in " + getStackTrace()[0].getClassName() + "" + getMessage() + " :\n" + "Type "
				+ clazz.getSimpleName() + " is not supported " + getClass().getName();
	}
}
