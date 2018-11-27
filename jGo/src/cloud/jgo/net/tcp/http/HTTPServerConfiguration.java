package cloud.jgo.net.tcp.http;

import cloud.jgo.net.ServerType;
import cloud.jgo.net.config.Configuration;
import cloud.jgo.net.config.ServerConfiguration;
import cloud.jgo.net.config.Configuration.ConfigurationKey;
import cloud.jgo.net.tcp.TCPServerConfiguration;
import cloud.jgo.net.tcp.TCPServerTypes;

public class HTTPServerConfiguration extends TCPServerConfiguration{
	public final static ConfigurationKey ROOT_FOLDER = new ServerConfiguration.ServerConfigurationKey("jgo.net.server.root_dir",String.class,HTTPServerConfiguration.class);
	static{
		availableConfigurations.add(ROOT_FOLDER);
	}
	@Override
	public ServerType getServerType() {
		// TODO Auto-generated method stub
		return TCPServerTypes.TYPE_HTTP;
	}
	public HTTPServerConfiguration(String xmlFileName) {
		// TODO Auto-generated constructor stub
		super(xmlFileName);
	}
	
	public HTTPServerConfiguration() {
		// TODO Auto-generated constructor stub
		super();
	}
}
