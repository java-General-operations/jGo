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
	@Override
	public synchronized Object put(String key, Object value) {
		// TODO Auto-generated method stub
		super.put(key, value);
		Object obj = null ;
		boolean validType,validValue ;
		validType = false ;validValue = true ;
		for (int i = 0; i < availableConfigurations.size(); i++) {
			if (key.equals(availableConfigurations.get(i))) {
				validType = true ;
				break ;
			}
		}
		if (validType) {
			switch(key){
			case MULTI_CONNECTIONS:
				if (!Boolean.class.isInstance(value)) {
					validValue = false ;
				}
				break;
			case ACCEPTED_SOCKET:
				if (!String.class.isInstance(value)) {
					validValue = false ;
				}
				break;
			case MAXIMUM_SOCKETS:
				if (!Integer.class.isInstance(value)) {
					validValue = false ;
				}
				break;
			case SERVER_HANDLER_MODEL:
				if (!Handler.class.isInstance(value)) {
					validValue = false ;
				}
				break;
			}
			if (validValue) {
			 	obj = super.putIfAbsent(key, value);
			}
			else{
				try {
					throw new InvalidConfigurationException(key);
				} catch (InvalidConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return obj ;
	}
}
