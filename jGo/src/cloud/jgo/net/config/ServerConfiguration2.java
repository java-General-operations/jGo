package cloud.jgo.net.config;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import javax.naming.ldap.HasControls;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
public abstract class ServerConfiguration2 extends Properties implements Configuration2{
	protected final static DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
	protected static DocumentBuilder builder = null;
	protected Document document=null;
	public final static String XML_ROOT_NAME = "server.configuration";
	//KEYS :
	public final static ConfigurationKey SERVER_NAME = new ServerConfigurationKey("jgo.net.server_name",String.class);
	public final static ConfigurationKey SERVER_TYPE = new ServerConfigurationKey("jgo.net.server_type",String.class);
	public final static ConfigurationKey LPORT = new ServerConfigurationKey("jgo.net.server.lport",String.class);
	public final static ConfigurationKey LHOST = new ServerConfigurationKey("jgo.net.server.lhost",String.class);
	// available
	protected static List<ConfigurationKey>availableConfigurations = new ArrayList<ConfigurationKey>();
	static{
		availableConfigurations.add(LPORT);
		availableConfigurations.add(TIMER);
		availableConfigurations.add(LHOST);
		availableConfigurations.add(SERVER_NAME);
		availableConfigurations.add(SERVER_TYPE);
		// init xml builder 
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public StringBuffer AllConfigurations() {
		StringBuffer buffer = new StringBuffer();
		Iterator<java.util.Map.Entry<Object, Object>>iterator = entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<java.lang.Object, java.lang.Object> entry = (Map.Entry<java.lang.Object, java.lang.Object>) iterator
					.next();
			buffer.append(entry+"\n");
		}
		return new StringBuffer(buffer.toString().trim());
	}
	public static List<ConfigurationKey> getAvailableConfigurations() {
		return availableConfigurations;
	}
	// ridefinisco i metodi per l'inserimento degli elementi 
	@Override
	public <T> Object put(ConfigurationKey key, T value) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> Object putIfAbsent(ConfigurationKey key, T value) {
		// TODO Auto-generated method stub
		return null;
	}
	// mi creo la classe figlia della configurazioneChiave 
	public static class ServerConfigurationKey extends Configuration2.ConfigurationKey{
		private ServerConfigurationKey(String key, Class<?> type) {
			super(key, type);
			// TODO Auto-generated constructor stub
		}
	}
	
}
