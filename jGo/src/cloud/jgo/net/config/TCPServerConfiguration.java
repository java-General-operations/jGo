package cloud.jgo.net.config;
import java.util.logging.Logger;

import cloud.jgo.io.File;
import cloud.jgo.net.ServerType;
import cloud.jgo.net.ServerTypes;
import cloud.jgo.net.handlers.Handler;
public class TCPServerConfiguration extends ServerConfiguration2{
	private Logger logger = null ;
	// creo le costanti di questa classe :
	public final static ConfigurationKey DEFAULT_PRINT_FOR_ACCEPTANCE_SOCKET = new ConfigurationKey("jgo.net.server.default_print",Boolean.class,TCPServerConfiguration.class);
	public final static ConfigurationKey MULTI_CONNECTIONS = new ConfigurationKey("jgo.net.server.multi_connections",Boolean.class,TCPServerConfiguration.class);
	public final static ConfigurationKey ACCEPTED_SOCKET = new ConfigurationKey("jgo.net.server.accepted_socket",String.class,TCPServerConfiguration.class);
	public final static ConfigurationKey MAXIMUM_SOCKETS = new ConfigurationKey("jgo.net.server.maximum_sockets",Integer.class,TCPServerConfiguration.class);
	public final static ConfigurationKey HANDLER_MODEL = new ConfigurationKey("jgo.net.server.handler_model",Handler.class,TCPServerConfiguration.class);
	static{
		availableConfigurations.add(MULTI_CONNECTIONS);availableConfigurations.add(ACCEPTED_SOCKET);
		availableConfigurations.add(MAXIMUM_SOCKETS);availableConfigurations.add(HANDLER_MODEL);
	    availableConfigurations.add(DEFAULT_PRINT_FOR_ACCEPTANCE_SOCKET);
	}
	// mi creo due costruttori
	public TCPServerConfiguration() {
		// TODO Auto-generated constructor stub
		super();
		this.logger = Logger.getLogger("cloud.jgo.net");
	}
	public TCPServerConfiguration(String xmlFileName){
		fromXML(xmlFileName);
	}
	@Override
	public Logger getLogger() {
		// TODO Auto-generated method stub
		return this.logger ;
	}
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	@Override
	public int getSettingsCounter() {
		// TODO Auto-generated method stub
		return size();
	}
	@Override
	public ServerType getServerType() {
		// TODO Auto-generated method stub
		return ServerTypes.TYPE_TCP;
	}
}
