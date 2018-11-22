package cloud.jgo.net.config;

public class ConfigurationNotAccessibleException extends Exception {
	public ConfigurationNotAccessibleException() {
		// TODO Auto-generated constructor stub
		super(" - Configuration Not accessible #");
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Exception in "+getStackTrace()[0].getClassName()+""
				+ getMessage()+" : \n"+"You do not have permission to set up this configuration."
						+ " These configurations are made by\nthe library it self >\n"+getClass().getName();
	}
}
