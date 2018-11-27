package cloud.jgo.net.tcp.login;
import cloud.jgo.net.ServerType;
import cloud.jgo.net.config.Configuration;
import cloud.jgo.net.config.ServerConfiguration;
import cloud.jgo.net.config.Configuration.ConfigurationKey;
import cloud.jgo.net.tcp.TCPServerConfiguration;
import cloud.jgo.net.tcp.TCPServerTypes;
public class TCPLoginServerConfiguration extends TCPServerConfiguration{
	public final static ConfigurationKey PASSW = new ServerConfiguration.ServerConfigurationKey("jgo.net.server_passw",String.class,TCPLoginServerConfiguration.class);
	public final static ConfigurationKey USER = new ServerConfiguration.ServerConfigurationKey("jgo.net.server_user",String.class,TCPLoginServerConfiguration.class);
	public final static ConfigurationKey ATTEMPTS = new ServerConfiguration.ServerConfigurationKey("jgo.net.server_attempts",Integer.class,TCPLoginServerConfiguration.class);
	public final static ConfigurationKey AES_KEY = new ServerConfiguration.ServerConfigurationKey("jgo.net.server.aes_key",String.class,TCPLoginServerConfiguration.class);
	static{
		availableConfigurations.add(ATTEMPTS);
		availableConfigurations.add(AES_KEY);
		availableConfigurations.add(USER);
		availableConfigurations.add(PASSW);
	}
	@Override
	public ServerType getServerType() {
		// TODO Auto-generated method stub
		return TCPServerTypes.TYPE_LOGIN;
	}
	public TCPLoginServerConfiguration(String xmlFileName) {
		// TODO Auto-generated constructor stub
		super(xmlFileName);
	}
	public TCPLoginServerConfiguration() {
		// TODO Auto-generated constructor stub
		super();
	}
}
