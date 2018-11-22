package cloud.jgo.net.config;

import java.util.logging.Logger;

import cloud.jgo.net.handlers.Handler;

public class TCPServerConfiguration2 extends ServerConfiguration2{
	public final static String MULTI_CONNECTIONS = "jgo.net.multi_connections";
	public final static String ACCEPTED_SOCKET = "jgo.net.accepted_socket";
	public final static String MAXIMUM_SOCKETS = "jgo.net.maximum.sockets";
	public final static String SERVER_HANDLER_MODEL = "jgo.net.server.handler_model";
	static{
		availableConfigurations.add(MULTI_CONNECTIONS);
		availableConfigurations.add(ACCEPTED_SOCKET);
		availableConfigurations.add(MAXIMUM_SOCKETS);
		availableConfigurations.add(SERVER_HANDLER_MODEL);
	}
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
	
	// ridefinisco il metodo put
	
}
