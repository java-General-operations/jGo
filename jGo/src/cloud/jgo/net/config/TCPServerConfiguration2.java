package cloud.jgo.net.config;

import java.util.logging.Logger;

public class TCPServerConfiguration2 extends ServerConfiguration2{
	private Logger logger = null ;
	public TCPServerConfiguration2() {
		// TODO Auto-generated constructor stub
		this.logger = Logger.getLogger("cloud.jgo.net");
	}
	@Override
	public StringBuffer AllConfigurations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Logger getLogger() {
		// TODO Auto-generated method stub
		return this.logger;
	}

	@Override
	public int getSettingsCounter() {
		// TODO Auto-generated method stub
		return size();
	}
	
	
}
