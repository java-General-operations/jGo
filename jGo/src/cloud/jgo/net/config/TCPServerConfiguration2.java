package cloud.jgo.net.config;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import cloud.jgo.£;
import cloud.jgo.£Func;
import cloud.jgo.io.File;
import cloud.jgo.net.handlers.Handler;
public class TCPServerConfiguration2 extends ServerConfiguration2{
	public final static String SERVER_MULTI_CONNECTIONS = "jgo.net.server_multi_connections";
	public final static String SERVER_ACCEPTED_SOCKET = "jgo.net.server_accepted_socket";
	public final static String SERVER_MAXIMUM_SOCKETS = "jgo.net.server_maximum_sockets";
	public final static String SERVER_HANDLER_MODEL = "jgo.net.server_handler_model";
	static{
		availableConfigurations.add(SERVER_MULTI_CONNECTIONS);
		availableConfigurations.add(SERVER_ACCEPTED_SOCKET);
		availableConfigurations.add(SERVER_MAXIMUM_SOCKETS);
		availableConfigurations.add(SERVER_HANDLER_MODEL);
	}
	private Logger logger = null ;
	public TCPServerConfiguration2() {
		// TODO Auto-generated constructor stub
		this.logger = Logger.getLogger("cloud.jgo.net");
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
	public Object put(Object key, Object value) {
		// TODO Auto-generated method stub
		Object result =super.put(key, value);
		Object obj = null ;
		Boolean exceptionFlag = false ;
		if (!Boolean.class.isInstance(result)) {
			// quindi qui significa che 
			// è entrato nell'eccezzione del primo metodo
			// quindi non eseguo nemmeno questo metodo
			boolean validType,validValue ;
			validType = false ;validValue = true ;
			for (int i = 0; i < availableConfigurations.size(); i++) {
				if (key.equals(availableConfigurations.get(i))) {
					validType = true ;
					break ;
				}
			}
			if (validType) {
				switch((String)key){
				case SERVER_MULTI_CONNECTIONS:
					if (!Boolean.class.isInstance(value)) {
						validValue = false ;
					}
					break;
				case SERVER_ACCEPTED_SOCKET:
					if (!String.class.isInstance(value)) {
						validValue = false ;
					}
					break;
				case SERVER_MAXIMUM_SOCKETS:
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
				 	obj = super.put(key, value);
				}
				else{
					try {
						throw new InvalidConfigurationException((String)key);
					} catch (InvalidConfigurationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						exceptionFlag = true ;
					}
				}
			}
		}
		if (exceptionFlag==true) {
			return exceptionFlag ;
		}
		else{
			return obj;
		}
	}
	@Override
	public Object putIfAbsent(Object key, Object value) {
		Object result =super.putIfAbsent(key, value);
		Object obj = null ;
		Boolean exceptionFlag = false ;
		if (!Boolean.class.isInstance(result)) {
			// quindi qui significa che 
			// è entrato nell'eccezzione del primo metodo
			// quindi non eseguo nemmeno questo metodo
			boolean validType,validValue ;
			validType = false ;validValue = true ;
			for (int i = 0; i < availableConfigurations.size(); i++) {
				if (key.equals(availableConfigurations.get(i))) {
					validType = true ;
					break ;
				}
			}
			if (validType) {
				switch((String)key){
				case SERVER_MULTI_CONNECTIONS:
					if (!Boolean.class.isInstance(value)) {
						validValue = false ;
					}
					break;
				case SERVER_ACCEPTED_SOCKET:
					if (!String.class.isInstance(value)) {
						validValue = false ;
					}
					break;
				case SERVER_MAXIMUM_SOCKETS:
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
						throw new InvalidConfigurationException((String)key);
					} catch (InvalidConfigurationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						exceptionFlag = true ;
					}
				}
			}
		}
		if (exceptionFlag==true) {
			return exceptionFlag ;
		}
		else{
			return obj;
		}
	}
	@Override
	public File toXML(String fileName) {
		// creo il documento 
		document = builder.newDocument();
		// creo il file di destinazione 
		File file = new File(fileName);
		// in questo metodo uso il document XML
		// 1 passo : stabilisco il nodo root 
		Element root = document.createElement(ServerConfiguration2.XML_ROOT_NAME);
		// 2 passo faccio iterare gli elementi
		£.each(this,new £Func() {
			@Override
			public Object function(Object e) {
				Map.Entry<Object,Object>entry = (java.util.Map.Entry<Object, Object>) e ;
				Element el = document.createElement(getXMLTag((String)entry.getKey()));
				el.setTextContent(entry.getValue().toString());
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
	// metodo provato che ottiene il nome del tag xml
	// da per scontato che la prop sia giusta
	private static String getXMLTag(String property){
		String[]split = property.split("\\.");
		String tagName = split[split.length-1].replaceAll("_",".");
		return tagName ;
	}
	
	@Override
	public void fromXML(File xmlFile) {clear(); // cancello l'attuale configurazione
		try {
			document = builder.parse(xmlFile);
			// prendo il nodo root
			Element root = (Element) document.getElementsByTagName(XML_ROOT_NAME).item(0);
			NodeList listNodes = root.getChildNodes();
			// sappiamo che sono tutti elementi
			for (int i = 0; i < listNodes.getLength(); i++) {
				if (listNodes.item(i).getNodeType()== Node.ELEMENT_NODE) {
					Element el = (Element) listNodes.item(i);
					String nodeName = el.getNodeName();
					String nodeValue = el.getTextContent();
					putIfAbsent(nodeName, nodeValue);
				}
			}
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
