package cloud.jgo.net.config;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import cloud.jgo.£;
import cloud.jgo.£Func;
import cloud.jgo.io.File;
import cloud.jgo.net.ServerType;
import cloud.jgo.net.ServersUtils;
import cloud.jgo.net.handlers.Handler;
import cloud.jgo.net.tcp.TCPServerConfiguration;
public abstract class ServerConfiguration extends Configuration{
	protected final static DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
	protected static DocumentBuilder builder = null;
	protected Document document=null;
	public final static String XML_ROOT_NAME = "server.configuration";
	//KEYS :
	public final static ConfigurationKey SERVER_NAME = new ConfigurationKey("jgo.net.server_name",String.class,ServerConfiguration.class);
	public final static ConfigurationKey SERVER_TYPE = new ConfigurationKey("jgo.net.server_type",String.class,ServerConfiguration.class);
	public final static ConfigurationKey LPORT = new ConfigurationKey("jgo.net.server.lport",Integer.class,ServerConfiguration.class);
	public final static ConfigurationKey LHOST = new ConfigurationKey("jgo.net.server.lhost",String.class,ServerConfiguration.class);
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
	public ServerConfiguration() {
		// TODO Auto-generated constructor stub
		super.put(TCPServerConfiguration.SERVER_TYPE.key,getServerType().TYPE);
	}
	@Override
	public StringBuffer AllConfigurations() {
		StringBuffer buffer = new StringBuffer();
		£.each3(this,new £Func() {
			@Override
			public Object function(Object e) {
				
				Map.Entry<String,Object>entry = (java.util.Map.Entry<String, Object>) e ;
				
				if (entry.getKey().equals("jgo.net.server.handler_model")) {
					buffer.append(entry.getKey()+"="+entry.getValue().getClass().getName()+"\n");
				}
				else{
					buffer.append(entry+"\n");
				}
				return true ;
			}
		});
		return new StringBuffer(buffer.toString().trim());
	}
	public abstract ServerType getServerType();
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
			String ky = configurationKey.key;
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
					if (key.configurationType.isAssignableFrom(getClass())) {
						obj = (V) super.put(key.key,value);
					}
					else{
						try {
							throw new InvalidConfigurationException(key.key);
						} catch (InvalidConfigurationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
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
					if (key.configurationType.isAssignableFrom(getClass())) {
						obj = (V) super.putIfAbsent(key.key,value);
					}
					else{
						try {
							throw new InvalidConfigurationException(key.key);
						} catch (InvalidConfigurationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
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
					if (key.configurationType.isAssignableFrom(getClass())) {
						obj = (V) super.replace(key.key,value);
					}
					else{
						try {
							throw new InvalidConfigurationException(key.key);
						} catch (InvalidConfigurationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
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
	// quindi il valore oldValue deve essere uguale a quello reale della chiave di configurazione
	@Override
	public boolean replace(ConfigurationKey key, Object oldValue, Object newValue) {
		boolean obj = false ;
		ConfigurationKey ky = null ;
		Object oldValue_ = null ;
		for (ConfigurationKey configurationKey : availableConfigurations) {
			if (configurationKey.equals(key)) {
				ky = configurationKey;
				break ;
			}
		}
		if (ky!=null) {
			oldValue_ = get(ky.key);
			// quindi se i valori vecchi sono uguali
			// sia quello da input che quello attuale
			// della configurazione
			if (oldValue.equals(oldValue_)) {
				// okok impostiamo il nuovo valore
				if(!key.type.isInstance(newValue)){
					try {
						throw new InvalidConfigurationException(key.key);
					} catch (InvalidConfigurationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					if (key.configurationType.isAssignableFrom(getClass())) {
						obj = super.replace(ky.key, oldValue_, newValue);
					}
					else{
						try {
							throw new InvalidConfigurationException(key.key);
						} catch (InvalidConfigurationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
		return obj ;
	}
	@Override
	public boolean replace(String key,Object oldValue,Object newValue) {
		ConfigurationKey ky = null ;
		for (ConfigurationKey configurationKey : availableConfigurations) {
			if (configurationKey.key.equals(key)) {
				ky = configurationKey;
				break ;
			}
		}
		if (ky!=null) {
			return replace(ky,oldValue,newValue);
		}
		else{
			return false ;
		}
	}
		// metodo provato che ottiene il nome del tag xml
		// da per scontato che la prop sia giusta
		// metodo analoghi :
		private String getXMLTag(String property){
			String[]split = property.split("\\.");
			String tagName = split[split.length-1].replaceAll("_",".");
			return tagName ;
		}
	@Override
	public File toXML(String fileName) {
		// creo il documento 
		document = builder.newDocument();
		// creo il file di destinazione 
		File file = new File(fileName);
		// in questo metodo uso il document XML
		// 1 passo : stabilisco il nodo root 
		Element root = document.createElement(ServerConfiguration.XML_ROOT_NAME);
		// 2 passo faccio iterare gli elementi
		£.each3(this,new £Func() {
			@Override
			public Object function(Object e) {
				Map.Entry<String,Object>entry = (java.util.Map.Entry<String, Object>) e ;
					Element el = document.createElement(getXMLTag((String)entry.getKey()));
					if (Handler.class.isInstance(entry.getValue())) {
						el.setTextContent(entry.getValue().getClass().getName());
					}
					else if(entry.getKey().equals("jgo.net.server_type")){
						el.setTextContent(entry.getValue()+"");
						el.setAttribute("excludes","true");
					}
					else{
						el.setTextContent(entry.getValue().toString());	
					}
					root.appendChild(el);
				return true ;
			}
		});
		document.appendChild(root);
		TransformerFactory factory_ = TransformerFactory.newInstance();
		try {
			Transformer transformer = factory_.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(file);
			try {
				transformer.transform(source, result);
			} catch (TransformerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (TransformerConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		return file ;
	}
	
	private String getProp(String tagName){
		String prop = null ;
		if (tagName.contains(".")) {
			tagName = tagName.replaceAll("\\.","_");
		}
		List<ConfigurationKey>props = availableConfigurations;
		for (int i = 0; i < props.size(); i++) {
			if (props.get(i).key.endsWith(tagName)) {
				prop = props.get(i).key;
				break ;
			}
		}
		return prop ;
	}
	
	@Override
	public boolean fromXML(File xmlFile) {clear();
	ConfigurationKey key = null ;	
	boolean flag = false ;
		if (xmlFile.exists()) {
			try {
				document = builder.parse(xmlFile);
				// prendo il nodo root
				Element root = (Element) document.getElementsByTagName(XML_ROOT_NAME).item(0);
				NodeList listNodes = root.getChildNodes();
				// sappiamo che sono tutti elementi
				for (int i = 0; i < listNodes.getLength(); i++) {
					if (listNodes.item(i).getNodeType()== Node.ELEMENT_NODE) {
						Element el = (Element) listNodes.item(i);
						if(!el.hasAttribute("excludes")){
							String ky = el.getNodeName();
							String value = el.getTextContent();
							ky = getProp(ky);
							if (ky!=null) {
								// adesso controllo che tipo di chiave
								// è per inserire il valore giusto 
								for (ConfigurationKey configurationKey : availableConfigurations) {
									String currentKey = configurationKey.key;
									if (ky.equals(currentKey)) {
										key = configurationKey;
										break ;
									}
								}
								if (key!=null) {
									if (key.type.getSimpleName().equalsIgnoreCase("String")) {
										putIfAbsent(key,value);
									}
									else if(key.type.getSimpleName().equalsIgnoreCase("Integer")){
										putIfAbsent(key,Integer.parseInt(value));
									}
									else if(key.type.getSimpleName().equalsIgnoreCase("Double")){
										putIfAbsent(key,Double.parseDouble(value));
									}
									else if(key.type.getSimpleName().equalsIgnoreCase("Boolean")){
										putIfAbsent(key,Boolean.parseBoolean(value));
									}
									else if(key.type.getSimpleName().equalsIgnoreCase("Long")){
										putIfAbsent(key,Long.parseLong(value));
									}
									else{
										// qui vuol dire che è un tipo di oggetto diverso
										// per cui ne creo una instanza
										try {
											Class<?>clazz = Class.forName(value);
											try {
												Object obj = clazz.newInstance();
												putIfAbsent(key,obj);
											} catch (InstantiationException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											} catch (IllegalAccessException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										} catch (ClassNotFoundException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								}
								else{
									// qui devo vedere se il caso di dare l'eccezzione
									System.out.println("E entrato nell'else");
									return false ;
								}	
							}
						}
					}
				}
				// all fine del ciclo
				// cerco il tipo del server
				// e verifico se è compatibile con la classe della configurazione 
				Element serverTypeElement = (Element) document.getElementsByTagName("server.type").item(0);
				if (serverTypeElement!=null) {
					String serverType = serverTypeElement.getTextContent();
					String orServerType = this.getServerType().TYPE;
					if (serverType.equals(orServerType)) {
						// va bene
						// quindi inseriamo il tipo di nuovo
						super.put(ServerConfiguration.SERVER_TYPE.key,serverType);
					}
					else{
						try {
							throw new InvalidConfigurationException(ServerConfiguration.SERVER_TYPE.key);
						} catch (InvalidConfigurationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				else{
					// qui non c'è il tipo di server nella configurazione
					// quindi vedere che fare ...
				}
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag ;
	}
	@Override
	public boolean fromXML(String fileName) {
		// TODO Auto-generated method stub
		return fromXML(new File(fileName));
	}
	// sub class config :
	public static class ServerConfigurationKey extends ConfigurationKey{
		public ServerConfigurationKey(String key, Class<?> type,
				Class<? extends Configuration> configurationType) {
			super(key, type, configurationType);
			// TODO Auto-generated constructor stub
		}
	}
}
