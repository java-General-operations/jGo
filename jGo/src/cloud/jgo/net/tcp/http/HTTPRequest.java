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
package cloud.jgo.net.tcp.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.activation.MimeTypeParseException;

import cloud.jgo.£;
import cloud.jgo.net.Server;
import cloud.jgo.net.tcp.http.headers.Header;
import cloud.jgo.net.tcp.http.headers.Header.Type;

/**
 * 
 * @author Martire91<br>
 *         This class represents an http request, and is an implementation of
 *         {@link HTTPMessage}
 *
 */
public class HTTPRequest implements HTTPMessage {
	private String decoder_type = "UTF-8";
	private URL url = null;
	private String urlText = null;
	// variabile che rimarrà nascosta
	private String infoRequest = null;
	private String root = null;
	// tutte le variabili di questa classe
	private String address = null;
	private String serverAddress = null;
	private String locationResource = null;
	private String http_version = null;
	private String connection = null;
	private String accept = null;
	private String userAgent = null;
	private String acceptEncoding = null;
	private String acceptLanguage = null;
	private String fileName = null;
	private String httpMethod = null;
	private List<Header> headers = new ArrayList<>();

	/**
	 * 
	 * @return http decoder type
	 */
	public String getDecoder_type() {
		return decoder_type;
	}

	/**
	 * This method sets http decoder type
	 * 
	 * @param decoder_type
	 *            the http decoder type
	 */
	public void setDecoder_type(String decoder_type) {
		this.decoder_type = decoder_type;
	}

	/**
	 * This method return the root
	 * 
	 * @return the root
	 */
	public String getRoot() {
		return root;
	}

	/**
	 * This method returns the file format
	 * 
	 * @return requested file format
	 */
	public String getFormatFileName() {
		return this.formatFileName;
	}

	private int serverPort = 0;
	private String formatFileName;

	/**
	 * This method returns http version
	 * 
	 * @return the http version
	 */
	public String getHttp_version() {
		return http_version;
	}

	/**
	 * This method returns request info
	 * 
	 * @return request info
	 */
	public String getInfoRequest() {
		return infoRequest;
	}

	// per creare un instanza (server side)
	public HTTPRequest(String infoRequest, String rootFolder, int serverPort, boolean notifyConnections)
			throws UnknownHostException, UnsupportedEncodingException, MalformedURLException, MimeTypeParseException {
		// TODO Auto-generated constructor stub
		this.serverPort = serverPort;
		this.root = rootFolder.trim();
		this.infoRequest = infoRequest;
		String[] split = this.infoRequest.split("\n");
		int posUserAgent = 0; // for moment
		int posAcceptLanguage = 0; // for moment
		int posAcceptEncoding = 0; // for moment
		int posAddress = 0;
		int posHostServer = 0;
		int posAccept = 0;
		int posConnection = 0;
		int posHttpVersion = 0;
		int posCacheControl = 0;

		for (int i = 0; i < split.length; i++) {
			if (notifyConnections == true) {
				// N.B.

				/*
				 * Unico problema qui è che stampiamo in modo predefinito nella system.out
				 * invece noi dobbiamo stampare nella sorgente impostata dall'utente,però questo
				 * lo posso risolvere con calma
				 */
				System.out.println((i + 1) + ":" + split[i]);
			}
			// controllo quale riga contiene l'user agent
			if (split[i].toLowerCase().startsWith("User-Agent:".toLowerCase())) {
				posUserAgent = i;
			} else if (split[i].toLowerCase().startsWith("Accept-Language:".toLowerCase())) {
				posAcceptLanguage = i;
			} else if (split[i].toLowerCase().startsWith("Accept-Encoding:".toLowerCase())) {
				posAcceptEncoding = i;
			} else if (split[i].toLowerCase().startsWith("Connection from:".toLowerCase())) {
				posAddress = i;
			} else if (split[i].toLowerCase().startsWith("Host:".toLowerCase())) {
				posHostServer = i;
			} else if (split[i].toLowerCase().startsWith("Accept:".toLowerCase())) {
				posAccept = i;
			} else if (split[i].toLowerCase().startsWith("Connection:".toLowerCase())) {
				posConnection = i;
			} else if (split[i].toLowerCase().startsWith("Cache-Control:".toLowerCase())) {
				posCacheControl = i;
			}

			// okok ho risolto mettendo un if anzichè un else if
			// kmq qui prendo sia la riga in cui c'è la versione http
			// e sia la riga del metodo utilizzato dalla richiesta
			if (split[i].toLowerCase().contains("http/")) {
				posHttpVersion = i;
			}
			// qui controllo se la riga che contiene la versione e il metodo
			// ha come lenght 2

		}
		/*
		 * ADDRESS
		 */

		String[] splitForHost = split[posAddress].split(" ");
		this.address = splitForHost[splitForHost.length - 1].replace("/", "");

		/*
		 * HTTP VERSION
		 */

		String twoRow = split[posHttpVersion];
		String[] splitForVersion = twoRow.split(" ");
		this.http_version = splitForVersion[splitForVersion.length - 1];

		/*
		 * Method GET OR POST
		 */
		this.httpMethod = splitForVersion[0];

		/*
		 * HOST server
		 */
		String threeRow = split[posHostServer];

		this.serverAddress = threeRow.substring(threeRow.indexOf(":") + 1).trim();

		// JOptionPane.showMessageDialog(null,this.serverAddress,"host
		// server",JOptionPane.INFORMATION_MESSAGE);

		// incapsulo il primo header
		Header hostHeader = Header.getDefaultInstance(Header.Type.HOST, this.serverAddress);
		this.headers.add(hostHeader);
		/*
		 * LOCATION la locazione del file nel server (Molto utile)
		 */
		this.locationResource = splitForVersion[1];

		/*
		 * CONNECTION
		 */

		String row_4 = split[posConnection];
		String[] splitForConnection = row_4.split(" ");
		this.connection = splitForConnection[1];

		// incapsulo il secondo header
		Header connectionHeader = Header.getDefaultInstance(Header.Type.CONNECTION, this.connection);
		this.headers.add(connectionHeader);
		/*
		 * ACCEPT
		 */

		String row_5 = split[posAccept];
		String[] splitForAccept = row_5.split(":");
		this.accept = splitForAccept[1];

		// aggiungo il terzo header

		Header acceptHeader = Header.getDefaultInstance(Header.Type.ACCEPT, this.accept);
		this.headers.add(acceptHeader);

		/*
		 * USER AGENT
		 */

		String rowUserAgent = split[posUserAgent];
		String[] splitForUserAgent = rowUserAgent.split(":");
		this.userAgent = splitForUserAgent[1];

		// aggiungo il 4 header
		this.headers.add(Header.getDefaultInstance(Header.Type.USER_AGENT, this.userAgent));

		/*
		 * ACCEPT Encoding
		 */
		String rowEncoding = split[posAcceptEncoding];
		String[] splitEncoding = rowEncoding.split(":");
		this.acceptEncoding = splitEncoding[1];
		// aggiungo il quinto header

		this.headers.add(Header.getDefaultInstance(Header.Type.ACCEPT_ENCODING, this.acceptEncoding));

		/*
		 * Accept Language
		 */
		String rowAcceptLanguage = split[posAcceptLanguage];
		String[] splitForLanguage = rowAcceptLanguage.split(" ");
		this.acceptLanguage = splitForLanguage[1];

		// aggiungo il sesto header

		this.headers.add(Header.getDefaultInstance(Header.Type.ACCEPT_LANGUAGE, this.acceptLanguage));

		/*
		 * OKOKO eliminare da qui a @ in caso di problemi Cache-Control: max-age=0
		 */

		String rowCacheControl = split[posCacheControl];
		String cacheControl = null;
		if (rowCacheControl != null) {
			if (!rowCacheControl.isEmpty()) {

				// faccio lo split in base ai due punti
				String[] splitForTwoPoint = rowCacheControl.split(":");
				if (splitForTwoPoint.length == 2) {
					// va bene
					// quindi ora devo prendere il valore
					cacheControl = splitForTwoPoint[1].trim(); // cancello lo spazio iniziale che rimane dopo i due
																// punti

					// quindi se effettivamente si è trovato questo header
					// lo incapsulo nell'oggetto appunto header

					// aggiungo il settimo header

					this.headers.add(Header.getDefaultInstance(Header.Type.CACHE_CONTROL, cacheControl));
				}

			}
		}

		/*
		 * @
		 */

		/*
		 * FILENAME NOME DEL FILE RICHIESTO
		 */
		String replace = null;

		if (split.length >= 2) {
			replace = split[1].replace("GET ", "");
			this.fileName = replace.replace(http_version, "");
			char[] charat = this.fileName.toCharArray();
			char[] newCharat = new char[charat.length - 1];
			int y = 0;
			for (int i = 1; i < charat.length; i++, y++) {
				newCharat[y] = charat[i];
			}

			this.fileName = new String(newCharat);

			/*
			 * URL
			 */

			this.urlText = "http://" + this.serverAddress + this.locationResource;
			/*
			 * File Name
			 */

			this.fileName = this.root + "\\" + this.fileName;

			this.fileName = this.fileName.replace("\\", "/").trim(); // qui si formatta il file con slash linux
			// decodifico e sostituisco l'eventuale simbolo per i spazi
			this.fileName = URLDecoder.decode(this.fileName, this.decoder_type);
			if (this.fileName.contains("%20")) {

				// lo sostituisco
				this.fileName = this.fileName.replaceAll("%20", " ");
			}

			// qui estraggo il tipo del file quindi la formattazione

			this.formatFileName = £.extractFormatFromFileName(this.fileName);

			// qui faccio un ulteriore controllo provvisorio ,poi kmq devo ragionarci meglio
			// verifico se la versione http contiene appunto la dicitura

		} else {
			/**
			 * @author Martire91 E entrato nell'else quindi split e inferiore di due
			 */

		}
	}

	/**
	 * This method returns the requested location resource
	 * 
	 * @return location resource
	 */
	public String getLocationResource() {
		return locationResource;
	}

	/**
	 * This method returns address
	 * 
	 * @return address
	 */
	public String getAddress() {
		// TODO Auto-generated method stub
		return this.address;
	}

	/**
	 * This method returns server address
	 * 
	 * @return server address
	 */
	public String getServerAddress() {
		// TODO Auto-generated method stub
		return this.serverAddress;
	}

	/**
	 * This method returns the connection header
	 * 
	 * @return connection info
	 */
	public String getConnection() {
		// TODO Auto-generated method stub
		return this.connection;
	}

	/**
	 * This method returns the accept connection
	 * 
	 * @return accept connection
	 */
	public String getAccept() {
		// TODO Auto-generated method stub
		return this.accept;
	}

	/**
	 * This method returns user agent header
	 * 
	 * @return user agent
	 */
	public String getUserAgent() {
		return this.userAgent;
	}

	/**
	 * This method returns accept language header
	 * 
	 * @return accept language
	 */
	public String getAcceptLanguage() {

		return acceptLanguage;

	}

	/**
	 * This method returns the accept encoding header
	 * 
	 * @return accept encoding
	 */
	public String getAcceptEncoding() {

		return acceptEncoding;
	}

	/**
	 * This method returns the requested file name
	 * 
	 * @return requested file name
	 */
	public String getFileName() {
		return this.fileName;
	}

	/**
	 * This method returns the server port
	 * 
	 * @return server port
	 */
	public int getServerPort() {
		return this.serverPort;
	}

	/**
	 * This method returns the http method
	 * 
	 * @return http method
	 */
	public String getHttpMethod() {

		return this.httpMethod;

	}

	/**
	 * This method returns the decoded url
	 * 
	 * @return decoded url
	 * @throws UnsupportedEncodingException
	 *             1 exception
	 * @throws MalformedURLException
	 *             2 exception
	 */
	public URL getDecodedUrl() throws UnsupportedEncodingException, MalformedURLException {
		if (this.urlText != null) {
			String url = URLDecoder.decode(this.urlText, this.decoder_type);
			return this.url = new URL(url);
		} else {
			return null;
		}
	}

	/**
	 * This method returns the encoded url
	 * 
	 * @return encoded url
	 * @throws UnsupportedEncodingException
	 *             1 exception
	 * @throws MalformedURLException
	 *             2 exception
	 */
	public URL getEncodedUrl() throws UnsupportedEncodingException, MalformedURLException {
		if (this.urlText != null) {
			return this.url = new URL(urlText);
		} else {
			return null;
		}
	}

	@Override
	public Iterator<Header> iterator() {
		/*
		 * 
		 * JGO Auto-generated method stub Author : £ wasp91 £ Date 22 feb 2018
		 * 
		 */
		return headers.iterator();
	}

	@Override
	public void removeHeader(Type type) {

		if (!isEmptyHeaders()) {

			for (int i = 0; i < this.headers.size(); i++) {
				if (headers.get(i).getType().equals(type)) {

					headers.remove(i);
					// esco perchè tanto lo abbiamo eliminato il tipo
					// si da per scontato che ci sia solo un header di quel tipo
					// , motivo per il quale ora esco dal ciclo
					break;
				}
			}
		}
	}

	@Override
	public boolean removeHeader(int headerIndex) throws IOException {
		boolean flag = false;
		if (headerIndex <= headers.size() - 1) {

			flag = this.headers.remove(this.headers.get(headerIndex));
		}

		return flag;
	}

	@Override
	public void removeAllHeaders() throws IOException {
		if (!isEmptyHeaders()) {
			headers.clear();
		}
	}

	@Override
	public void printHeaders() {
		if (!isEmptyHeaders()) {
			for (int i = 0; i < headers.size(); i++) {
				System.out.println(headers.get(i));
			}
		}
	}

	@Override
	public boolean isPresent(Type type) {
		boolean present = false;
		List<Header> list = headers;
		for (int i = 0; i < list.size(); i++) {
			Header.Type headerType = list.get(i).getType();
			if (headerType.equals(type)) {
				present = true;
				break;
			}
		}
		return present;
	}

	@Override
	public boolean isEmptyHeaders() {
		return this.headers.isEmpty();
	}

	@Override
	public Header header(int index) {
		if (getHeadersCount() > 0) {

			return this.headers.get(index);

		} else {
			return null;
		}
	}

	public int getHeadersCount() {
		return headers.size();
	}

	@Override
	public Header header(Type type) {
		boolean valid = false;
		Header headerFound = null;
		// qui dv per prima cosa controllare che il tipo sia giusto
		for (int i = 0; i < Header.Type.getAvailableTypes().length; i++) {
			if (type.equals(Header.Type.getAvailableTypes()[i])) {
				valid = true;
			}
		}

		if (valid) {
			// adesso controlliamo se è stato inserito nella risposta un header di questo
			// tipo
			// qui prendo gli headers della risposta
			if (getHeadersCount() > 0) {
				Iterator<Header> iterator = this.iterator();
				while (iterator.hasNext()) {
					Header header = (Header) iterator.next();
					// prendo il tipo dell'header
					if (header != null) {
						Header.Type typ = header.getType();
						if (type.equals(typ)) {
							headerFound = header;
						}
					}
				}
				return headerFound;
			} else {
				return null;
			}
		} else {
			return null;
		}

	}

}
