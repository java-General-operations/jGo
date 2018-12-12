/**
 * JGO - A pure Java library,
 * its purpose is to make life easier for the programmer.
 *
 * J - Java
 * G - General
 * O - Operations
 *
 * URL Software : https://www.jgo.cloud/
 * URL Documentation : https://www.jgo.cloud/docs/
 *
 * Copyright © 2018 - Marco Martire (www.jgo.cloud)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the MIT License.
 *
 * You may obtain a copy of License at :
 * https://www.jgo.cloud/LICENSE.txt
 *
 * To collaborate on this project, you need to do it from the software site.
 * 
 */
package cloud.jgo.net.tcp.http.headers;

import java.util.StringTokenizer;

import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;

import cloud.jgo.net.tcp.http.mime.MimeTypeFactory;

/**
 * 
 * @author Martire91<br>
 *         This class represents an http header
 *
 */
public abstract class Header {

	private Header.Type type = null;

	/**
	 * 
	 * @author Martire91<br>
	 *         The http header types
	 *
	 */
	public static class Type {
		/*
		 * headers common
		 */
		public final static Type DATE = new Type("Date", 0);
		public final static Type CONTENT_LENGTH = new Type("Content-Length", 1);
		public final static Type CONTENT_ENCODING = new Type("Content-Encoding", 2);
		public final static Type CONTENT_TYPE = new Type("Content-Type", 3);
		public final static Type CACHE_CONTROL = new Type("Cache-Control", 4);// Cache-Control
		public final static Type CONNECTION = new Type("Connection", 5);
		public final static Type LAST_MODIFIED = new Type("Last-Modified", 6);
		public final static Type SERVER = new Type("Server", 7);
		public final static Type X_POWERED_BY = new Type("X-Powered-By", 8);// X-Powered-By
		public final static Type PRAGMA = new Type("Pragma", 9);
		public final static Type REFRESH = new Type("Refresh", 10);
		/*
		 * Types Requests
		 */
		public final static Type ACCEPT = new Type("Accept", 11);
		public final static Type HOST = new Type("Host", 12);
		public final static Type PROXY_AUTHHORIZATION = new Type("Proxy-Authorization", 13);
		public final static Type USER_AGENT = new Type("User-Agent", 14);
		public final static Type ACCEPT_ENCODING = new Type("Accept-Encoding", 15);
		public final static Type ACCEPT_LANGUAGE = new Type("Accept-Language", 16);

		private final static Type[] availableTypes = { ACCEPT, ACCEPT_ENCODING, ACCEPT_LANGUAGE, USER_AGENT,
				CONTENT_LENGTH, CONTENT_ENCODING, CONTENT_TYPE, CACHE_CONTROL, CONNECTION, LAST_MODIFIED, SERVER,
				X_POWERED_BY, PRAGMA, REFRESH, HOST };

		public static Type[] getAvailableTypes() {
			return availableTypes;
		}

		/**
		 * This method checks if a header is available
		 * 
		 * @param headerType
		 *            the header type
		 * @return true if is available
		 */
		public static boolean isAvailable(String headerType) {
			boolean isAvailable = false;
			for (int i = 0; i < availableTypes.length; i++) {
				if (availableTypes[i].getType().equals(headerType)) {
					isAvailable = true;
				}
			}
			return isAvailable;
		}

		@Override
		public boolean equals(Object obj) {
			Type cast = (Type) obj;
			if (cast.getType().equals(this.getType()) && cast.getIndexConstant() == this.getIndexConstant()) {
				return true;
			} else {
				return false;
			}
		}

		/*
		 * cache-control: controlla la modalità di caching per una pagina web
		 * content-disposition: può essere utilizzato per specificare un file binario in
		 * allegato alla response content-length: specifica la lunghezza del body della
		 * response, espresso in byte content-type: specifica il MIME type del documento
		 * di risposta content-encoding: specifica il tipo di codifica utilizzato nella
		 * risposta. Spesso, utilizzare una codifica di tipo GZIP incrementa
		 * notevolmente le performance expires: specifica la durata della cache
		 * last-modified: indica, in termini temporali, l’ultimo aggiornamento
		 * effettuato su una pagina pragma: disabilita la cache sui vecchi browser
		 * attraverso l’utilizzo della stringa “no-cache” refresh: specifica il numero
		 * di secondi trascorsi i quali il browser deve richiedere un aggiornamento
		 * della pagina
		 * 
		 * 
		 * 
		 * 
		 */

		private String type;
		private int indexConstant;

		private Type(String type, int indexCostant) {
			this.type = type;
			this.indexConstant = indexCostant;
		}

		/**
		 * This method returns the type
		 * 
		 * @return the type
		 */
		public String getType() {
			return type;
		}

		/**
		 * This method returns the type id
		 * 
		 * @return the type id
		 */
		public int getIndexConstant() {
			return indexConstant;
		}

	}

	// metodo factory all'interno della classe

	public static String CRLF = "\r\n";

	/**
	 * This method returns header name
	 * 
	 * @return header name
	 */
	public abstract String getName();

	/**
	 * This method returns header value
	 * 
	 * @return the header value
	 */
	public abstract Object getValue();

	/**
	 * This method sets header value
	 * 
	 * @param value
	 *            the header value
	 */
	public abstract void setValue(Object value);

	// this.response.append("HTTP/1.0 200 OK\r\n");
	// this.response.append("Content-Type:text/html\r\n\r\n");

	// questo metodo è usato per prendere l'header test senza CRLF

	/**
	 * This method returns the whole header
	 * 
	 * @return the whole header
	 */
	public String header() {
		return getName() + ": " + getValue();
	}

	/**
	 * This is the factory method
	 * 
	 * @param type
	 *            header type
	 * @param value
	 *            header value
	 * @return the header
	 * @throws MimeTypeParseException
	 *             1 exception
	 */
	public static Header getDefaultInstance(Header.Type type, Object value) throws MimeTypeParseException {
		Header header = null;
		if (type != null) {
			if (type.equals(Header.Type.DATE)) {
				header = new DefaultHeader();
				((DefaultHeader) header).setName(type.getType());
				header.setValue(value);
				header.type = Header.Type.DATE;
			} else if (type.equals(Header.Type.CONTENT_LENGTH)) {
				header = new DefaultHeader();
				((DefaultHeader) header).setName(type.getType());
				header.setValue(value);
				header.type = Header.Type.CONTENT_LENGTH;
			}
			// okok altro errore quasi risolto
			else if (type.equals(Header.Type.CONTENT_TYPE)) {

				header = new MimeHeader(null);
				if (header != null) {
					((MimeHeader) header).setName(type.getType());

					// canc da qui a @

					// qui prendo formato e valore del mimeType input

					if (value != null) {
						if (value.toString().split("/").length == 2) {
							String formatMimeType = value.toString().split("/")[0].trim();
							String valueMimeType = value.toString().split("/")[1].trim();
							MimeType mime = MimeTypeFactory.getDefaultMimeType(formatMimeType);
							mime.setSubType(formatMimeType);
							mime.setPrimaryType(valueMimeType);
							// imposto il valore dell'header mime
							header.setValue(mime);
							// @
							header.type = Header.Type.CONTENT_TYPE;
						}
					} else {
						header = null;
					}

				}
			} else if (type.equals(Header.Type.CONTENT_ENCODING)) {
				header = new DefaultHeader();
				((DefaultHeader) header).setName(type.getType());
				header.setValue(value);
				header.type = Header.Type.CONTENT_ENCODING;
			} else if (type.equals(Header.Type.CACHE_CONTROL)) {
				header = new DefaultHeader();
				((DefaultHeader) header).setName(type.getType());
				header.setValue(value);
				header.type = Header.Type.CACHE_CONTROL;
			} else if (type.equals(Header.Type.CONNECTION)) {
				header = new DefaultHeader();
				((DefaultHeader) header).setName(type.getType());
				header.setValue(value);
				header.type = Header.Type.CONNECTION;
			} else if (type.equals(Header.Type.LAST_MODIFIED)) {
				header = new DefaultHeader();
				((DefaultHeader) header).setName(type.getType());
				header.setValue(value);
				header.type = Header.Type.LAST_MODIFIED;
			} else if (type.equals(Header.Type.REFRESH)) {
				header = new DefaultHeader();
				((DefaultHeader) header).setName(type.getType());
				header.setValue(value);
				header.type = Header.Type.REFRESH;
			} else if (type.equals(Header.Type.SERVER)) {
				header = new DefaultHeader();
				((DefaultHeader) header).setName(type.getType());
				header.setValue(value);
				header.type = Header.Type.SERVER;
			} else if (type.equals(Header.Type.PRAGMA)) {
				header = new DefaultHeader();
				((DefaultHeader) header).setName(type.getType());
				header.setValue(value);
				header.type = Header.Type.PRAGMA;

			} else if (type.equals(Header.Type.X_POWERED_BY)) {
				header = new DefaultHeader();
				((DefaultHeader) header).setName(type.getType());
				header.setValue(value);
				header.type = Header.Type.X_POWERED_BY;
			}
			// da qui
			else if (type.equals(Header.Type.ACCEPT)) {
				header = new DefaultHeader();
				((DefaultHeader) header).setName(type.getType());
				header.setValue(value);
				header.type = Header.Type.ACCEPT;
			} else if (type.equals(Header.Type.HOST)) {
				header = new DefaultHeader();
				((DefaultHeader) header).setName(type.getType());
				header.setValue(value);
				header.type = Header.Type.HOST;
			}
			// copiare da qui in poi nell'altro metodos
			else if (type.equals(Header.Type.USER_AGENT)) {
				header = new DefaultHeader();
				((DefaultHeader) header).setName(type.getType());
				header.setValue(value);
				header.type = Header.Type.USER_AGENT;
			} else if (type.equals(Header.Type.ACCEPT_ENCODING)) {
				header = new DefaultHeader();
				((DefaultHeader) header).setName(type.getType());
				header.setValue(value);
				header.type = Header.Type.ACCEPT_ENCODING;
			} else if (type.equals(Header.Type.ACCEPT_LANGUAGE)) {
				header = new DefaultHeader();
				((DefaultHeader) header).setName(type.getType());
				header.setValue(value);
				header.type = Header.Type.ACCEPT_LANGUAGE;
			}
		}
		return header;
	}

	/**
	 * This is the factory method
	 * 
	 * @param type
	 *            header type
	 * @return the header
	 */
	public static Header getDefaultInstance(Header.Type type) {
		Header header = null;
		if (type.equals(Header.Type.DATE)) {
			header = new DefaultHeader();
			((DefaultHeader) header).setName(type.getType());
			header.type = Header.Type.DATE;
		} else if (type.equals(Header.Type.CONTENT_LENGTH)) {
			header = new DefaultHeader();
			((DefaultHeader) header).setName(type.getType());
			header.type = Header.Type.CONTENT_LENGTH;
		} else if (type.equals(Header.Type.CONTENT_TYPE)) {
			header = new MimeHeader(null);
			((MimeHeader) header).setName(type.getType());
			header.type = Header.Type.CONTENT_TYPE;
		} else if (type.equals(Header.Type.CONTENT_ENCODING)) {
			header = new DefaultHeader();
			((DefaultHeader) header).setName(type.getType());
			header.type = Header.Type.CONTENT_ENCODING;
		} else if (type.equals(Header.Type.CACHE_CONTROL)) {
			header = new DefaultHeader();
			((DefaultHeader) header).setName(type.getType());
			header.type = Header.Type.CACHE_CONTROL;
		} else if (type.equals(Header.Type.CONNECTION)) {
			header = new DefaultHeader();
			((DefaultHeader) header).setName(type.getType());
			header.type = Header.Type.CONNECTION;
		} else if (type.equals(Header.Type.LAST_MODIFIED)) {
			header = new DefaultHeader();
			((DefaultHeader) header).setName(type.getType());
			header.type = Header.Type.LAST_MODIFIED;
		} else if (type.equals(Header.Type.REFRESH)) {
			header = new DefaultHeader();
			((DefaultHeader) header).setName(type.getType());
			header.type = Header.Type.REFRESH;
		} else if (type.equals(Header.Type.SERVER)) {
			header = new DefaultHeader();
			((DefaultHeader) header).setName(type.getType());
			header.type = Header.Type.SERVER;
		} else if (type.equals(Header.Type.PRAGMA)) {
			header = new DefaultHeader();
			((DefaultHeader) header).setName(type.getType());
			header.type = Header.Type.PRAGMA;
		} else if (type.equals(Header.Type.X_POWERED_BY)) {
			header = new DefaultHeader();
			((DefaultHeader) header).setName(type.getType());
			header.type = Header.Type.X_POWERED_BY;
		} else if (type.equals(Header.Type.ACCEPT)) {
			header = new DefaultHeader();
			((DefaultHeader) header).setName(type.getType());
			header.type = Header.Type.ACCEPT;
		} else if (type.equals(Header.Type.HOST)) {
			header = new DefaultHeader();
			((DefaultHeader) header).setName(type.getType());
			header.type = Header.Type.HOST;
		} else if (type.equals(Header.Type.USER_AGENT)) {
			header = new DefaultHeader();
			((DefaultHeader) header).setName(type.getType());
			header.type = Header.Type.USER_AGENT;
		} else if (type.equals(Header.Type.ACCEPT_ENCODING)) {
			header = new DefaultHeader();
			((DefaultHeader) header).setName(type.getType());
			header.type = Header.Type.ACCEPT_ENCODING;
		} else if (type.equals(Header.Type.ACCEPT_LANGUAGE)) {
			header = new DefaultHeader();
			((DefaultHeader) header).setName(type.getType());
			header.type = Header.Type.ACCEPT_LANGUAGE;
		}
		return header;
	}

	/**
	 * This is the factory method
	 * 
	 * @param header
	 *            text header
	 * @return the header
	 * @throws MimeTypeParseException
	 *             1 exception
	 */
	public static Header parse(String header) throws MimeTypeParseException {
		// qui in tanto devo ottenere nome/valore

		StringTokenizer tokenizer = new StringTokenizer(header, ":");

		// dividiamo per : quindi lo split deve essere di due

		Header header_ = null;

		int size = tokenizer.countTokens();

		if (size == 2) {
			// prendo il tipo

			String type = tokenizer.nextToken();
			String value = tokenizer.nextToken();

			// ottengo l'header desiderato tramite il tipo ricavato
			if (Header.Type.isAvailable(type)) {
				Header.Type[] types = Header.Type.availableTypes;
				for (int i = 0; i < types.length; i++) {
					if (types[i].getType().equals(type)) {
						header_ = Header.getDefaultInstance(types[i], value);
						break;
					}
				}

			}
		}
		return header_;
	}

	// con questo metodo possiamo creare un instanza header non classificata
	// per personalizzarla a modo proprio ,quindi è utile in tutti quei casi in cui
	// non vogliamo un headers predefinito ma vogliamo crearne uno noi

	/**
	 * This is the factory method
	 * 
	 * @return a new header
	 */
	public static DefaultHeader getInstance() {
		return new DefaultHeader();
	}

	/**
	 * This method returns the header type
	 * 
	 * @return header type
	 */
	public Header.Type getType() {
		return this.type;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub

		return getName() + ": " + getValue() + CRLF;

	}

}
