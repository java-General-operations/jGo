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

import cloud.jgo.£;
import cloud.jgo.£Func;
import cloud.jgo.io.File;
public abstract class ServerConfiguration2 extends Configuration2{
	protected final static DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
	protected static DocumentBuilder builder = null;
	protected Document document=null;
	public final static String XML_ROOT_NAME = "server.configuration";
	//KEYS :
	public final static ConfigurationKey SERVER_NAME = new ServerConfigurationKey("jgo.net.server_name",String.class);
	public final static ConfigurationKey SERVER_TYPE = new ServerConfigurationKey("jgo.net.server_type",String.class);
	public final static ConfigurationKey LPORT = new ServerConfigurationKey("jgo.net.server.lport",Integer.class);
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
		£.each3(this,new £Func() {
			@Override
			public Object function(Object e) {
				
				Map.Entry<String,Object>entry = (java.util.Map.Entry<String, Object>) e ;
				
				buffer.append(entry+"\n");
				
				return true ;
			}
		});
		return new StringBuffer(buffer.toString().trim());
	}
	
	
	public static List<ConfigurationKey> getAvailableConfigurations() {
		return availableConfigurations;
	}
	
	
	@Override
	public Object put(String key, Object value) {
		ConfigurationKey configKey = null ;
		for (ConfigurationKey configurationKey : availableConfigurations) {
			String ky = configurationKey.key;
			if (key.equals(ky)) {
				configKey = configurationKey ;
				break ;
			}
		}
		if (configKey!=null) {
			return put(configKey, value);
		}
		else{
			return null ;
		}
	}
	
	
	@Override
	public Object putIfAbsent(String key, Object value) {
		ConfigurationKey configKey = null ;
		for (ConfigurationKey configurationKey : availableConfigurations) {
			String ky = configKey.key;
			if (key.equals(ky)) {
				configKey = configurationKey ;
				break ;
			}
		}
		if (configKey!=null) {
			return putIfAbsent(configKey, value);
		}
		else{
			return null ;
		}
	}
	
	
	
	// ridefinisco i metodi per l'inserimento degli elementi 
	@Override
	public <V> V put(ConfigurationKey key, Object value) {
		boolean validKey;
		validKey = false ;
		V obj = null ;
		// controllo la chiave 
		for (ConfigurationKey configurationKey : availableConfigurations) {
			if (configurationKey.equals(key)) {
				validKey = true ;
				break ;
			}
		}
		if (validKey) {
			// controllo del valore 
			if (key.equals(SERVER_TYPE)) {
				try {
					throw new ConfigurationNotAccessibleException();
				} catch (ConfigurationNotAccessibleException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				// diamo per scontato che la chiave sia corretta dunque
				if (!key.type.isInstance(value)) {
					// vuol dire che il valore associato non è quello richiesto
					try {
						throw new InvalidConfigurationException(key.key);
					} catch (InvalidConfigurationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					// qui invece significa che è tutto apposto 
					// per cui chiamo il put della super classe
					obj = (V) super.put(key.key,value);
				}
			}
		}
		else{
			try {
				throw new InvalidConfigurationException(value.toString());
			} catch (InvalidConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return obj;
	}
	
	@Override
	public <V> V putIfAbsent(ConfigurationKey key, Object value) {
		boolean validKey;
		validKey = false ;
		V obj = null ;
		// controllo la chiave 
		for (ConfigurationKey configurationKey : availableConfigurations) {
			if (configurationKey.equals(key)) {
				validKey = true ;
				break ;
			}
		}
		if (validKey) {
			// controllo del valore 
			if (key.equals(SERVER_TYPE)) {
				try {
					throw new ConfigurationNotAccessibleException();
				} catch (ConfigurationNotAccessibleException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				// diamo per scontato che la chiave sia corretta dunque
				if (!key.type.isInstance(value)) {
					// vuol dire che il valore associato non è quello richiesto
					try {
						throw new InvalidConfigurationException(key.key);
					} catch (InvalidConfigurationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					// qui invece significa che è tutto apposto 
					// per cui chiamo il put della super classe
					obj = (V) super.putIfAbsent(key.key,value);
				}
			}
		}
		else{
			try {
				throw new InvalidConfigurationException(value.toString());
			} catch (InvalidConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return  obj;
	}
	@Override
	public <V> V getConfig(ConfigurationKey key) {
		// TODO Auto-generated method stub
		return (V) get(key.key);
	}
	
	@Override
	public <V> V replace(ConfigurationKey key,Object value) {
		boolean validKey;
		validKey = false ;
		V obj = null ;
		// controllo la chiave 
		for (ConfigurationKey configurationKey : availableConfigurations) {
			if (configurationKey.equals(key)) {
				validKey = true ;
				break ;
			}
		}
		if (validKey) {
			// controllo del valore 
			if (key.equals(SERVER_TYPE)) {
				try {
					throw new ConfigurationNotAccessibleException();
				} catch (ConfigurationNotAccessibleException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				// diamo per scontato che la chiave sia corretta dunque
				if (!key.type.isInstance(value)) {
					// vuol dire che il valore associato non è quello richiesto
					try {
						throw new InvalidConfigurationException(key.key);
					} catch (InvalidConfigurationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					// qui invece significa che è tutto apposto 
					// per cui chiamo il put della super classe
					obj = (V) super.replace(key.key, value);
				}
			}
		}
		else{
			try {
				throw new InvalidConfigurationException(value.toString());
			} catch (InvalidConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return obj;
	}
	@Override
	public Object replace(String key, Object value) {
		// ottengo la chiave
		ConfigurationKey ky = null ;
		for (ConfigurationKey configurationKey : availableConfigurations) {
			if (configurationKey.key.equals(key)) {
				ky = configurationKey;
				break ;
			}
		}
		if (ky!=null) {
			return replace(ky, value);
		}
		else{
			return null ;
		}
	}
	
	// mi creo la classe figlia della configurazioneChiave 
	public static class ServerConfigurationKey extends Configuration2.ConfigurationKey{
		private ServerConfigurationKey(String key, Class<?> type) {
			super(key, type);
			// TODO Auto-generated constructor stub
		}
	}
}
