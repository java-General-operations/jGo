package cloud.jgo.jjdom.jquery;
// version 1.0.3
/**
 *
 * @author Martire91
 * This exception occurs when jquery is not set in JjDom
 *
 */
public class jQueryNotInitializedException extends Exception{
	
	public jQueryNotInitializedException() {
		// TODO Auto-generated constructor stub
		super(" - jQuery not initialized");
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Exception in "+getStackTrace()[0].getClassName()+""
				+ getMessage()+" : \n"+"the jquery reference is absent."
						+ "No jquery reference was found >\n"+getClass().getName();
	}
}
