package cloud.jgo.net.config;

public class InvalidConfigurationException extends Exception{
	public InvalidConfigurationException() {
		// TODO Auto-generated constructor stub
		super(" - Invalid configuration #");
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Exception in "+getStackTrace()[0].getClassName()+""
				+ getMessage()+" : \n"+"This configuration has not been recognized,"
				+ "make sure the type of configuration is correct. >\n"+getClass().getName();
	}
}
