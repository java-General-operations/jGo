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
	public final static String SERVER_NAME = "jgo.net.server_name";
	public final static String SERVER_TYPE = "jgo.net.server_type";
	// available
	protected static List<String>availableConfigurations = new ArrayList<String>();
	static{
		availableConfigurations.add(Configuration2.PORT);
		availableConfigurations.add(Configuration2.TIMER);
		availableConfigurations.add(Configuration2.HOST);
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
	public static List<String> getAvailableConfigurations() {
		return availableConfigurations;
	}
	// si da per scontato che la key è una stringa
	@Override
	public Object put(Object key, Object value) {
		Object obj = null ;
		Boolean exceptionFlag = false ;
		boolean validKey,validValue ;
		validKey = false;validValue = true ;
		for (String config : availableConfigurations) {
			if (config.equals(key)) {
				validKey = true ;
				break ;
			}
		}
		if (validKey) {
			switch((String)key){
			case SERVER_NAME:
				if (!String.class.isInstance(value)) {
					validValue = false ;
				}
				break ;
			case SERVER_TYPE:
				try {
					throw new ConfigurationNotAccessibleException();
				} catch (ConfigurationNotAccessibleException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					exceptionFlag = true ;
				}
				break;
			case PORT:
				if (!Integer.class.isInstance(value)) {
					validValue = false ;
				}
				break;
			case TIMER:
				if (!Integer.class.isInstance(value)) {
					validValue = false ;
				}
				break;
			case HOST:
				if (!String.class.isInstance(value)) {
					validValue = false ;
				}
				break;
			}
			if (validValue) {
				obj = super.put(key, value);
			}
			else{
				// qui ci entra solo se il server name non è una stringa
				try {
					throw new InvalidConfigurationException((String)key);
				} catch (InvalidConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					exceptionFlag = true ;
				}
			}
		}
		if (exceptionFlag==true) {
			return exceptionFlag ;
		}
		else{
			return obj ;
		}
	}
	@Override
	public Object putIfAbsent(Object key, Object value) {
		Object obj = null ;
		Boolean exceptionFlag = false ;
		boolean validKey,validValue ;
		validKey = false;validValue = true ;
		for (String config : availableConfigurations) {
			if (config.equals(key)) {
				validKey = true ;
				break ;
			}
		}
		if (validKey) {
			switch((String)key){
			case SERVER_NAME:
				if (!String.class.isInstance(value)) {
					validValue = false ;
				}
				break ;
			case SERVER_TYPE:
				try {
					throw new ConfigurationNotAccessibleException();
				} catch (ConfigurationNotAccessibleException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					exceptionFlag = true ;
				}
				break;
			case PORT:
				if (!Integer.class.isInstance(value)) {
					validValue = false ;
				}
				break;
			case TIMER:
				if (!Integer.class.isInstance(value)) {
					validValue = false ;
				}
				break;
			case HOST:
				if (!String.class.isInstance(value)) {
					validValue = false ;
				}
				break;
			}
			if (validValue) {
				obj = super.putIfAbsent(key, value);
			}
			else{
				// qui ci entra solo se il server name non è una stringa
				try {
					throw new InvalidConfigurationException((String)key);
				} catch (InvalidConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					exceptionFlag = true ;
				}
			}
		}
		if (exceptionFlag==true) {
			return exceptionFlag ;
		}
		else{
			return obj ;
		}
	}
}
