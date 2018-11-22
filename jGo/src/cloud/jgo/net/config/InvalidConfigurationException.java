package cloud.jgo.net.config;

public class InvalidConfigurationException extends Exception{
	private String value;
	public InvalidConfigurationException(String value) {
		// TODO Auto-generated constructor stub
		super(" - Invalid configuration #");
		this.value = value ;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Exception in "+getStackTrace()[0].getClassName()+""
				+ getMessage()+" : \n"+"This configuration has not been recognized ("+value+"),\n"
				+ "make sure that the type of configuration but also the value of the same is correct. >\n"+getClass().getName();
	}
}
