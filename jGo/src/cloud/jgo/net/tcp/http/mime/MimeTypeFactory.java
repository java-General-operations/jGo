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
package cloud.jgo.net.tcp.http.mime;

import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;

/**
 * 
 * @author Martire91<br>
 *         This class is the MimeType Factory
 *
 */
public class MimeTypeFactory {
	public final static String FORMAT_HTML = "html";
	public final static String FORMAT_XML = "xml";
	public final static String FORMAT_JAVA_SCRIPT = "javascript";
	public final static String FORMAT_PNG_IMAGE = "png";
	public final static String FORMAT_TEXT_FILE = "plain";
	public final static String FORMAT_ICON = "x-icon"; //// image/x-icon
	public final static String FORMAT_CSS = "css";
	public final static String FORMAT_JPG_IMAGE = "jpeg";
	public final static String FORMAT_GIF_IMAGE = "gif";
	public final static String FORMAT_JAVA = "x-java-source"; // text/x-java-source
	public final static String FORMAT_PHP = "php";
	public final static String FORMAT_JSON = "json";
	public final static String FORMAT_JON = "x-jon";
	public final static String FORMAT_FRX = "frx";
	// provvisorio

	private MimeTypeFactory() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * This method allows the creation of a default mimetype
	 * 
	 * @param format
	 *            the mimetype format
	 * @return the default mimetype
	 * @throws MimeTypeParseException
	 *             1 exception
	 */
	public static MimeType getDefaultMimeType(final String format) throws MimeTypeParseException {

		// e il metodo factory si occupa di
		// di dare il giusto oggetto al mime type
		return configureMimeType(format);
	}

	/**
	 * This method allows the creation of a download mimetype
	 * 
	 * @return the download mimetype
	 * @throws MimeTypeParseException
	 *             1 exception
	 */
	public static MimeType getDownloadMimType() throws MimeTypeParseException {
		MimeType mime = new MimeType();
		mime.setPrimaryType("application");
		mime.setSubType("octet-stream");
		return mime;
	}

	/**
	 * This method allows the creation of a new mimetype
	 * 
	 * @return the mimetype
	 */
	public static MimeType newMimeType() {
		MimeType mime = new MimeType();
		return mime;
	}

	private static MimeType configureMimeType(String format) throws MimeTypeParseException {
		if (format != null) {
			format = format.trim().toLowerCase();
			MimeType mimeType = new MimeType();
			// qui prima del costrutto faccio una serie di controllio per verificare se devo
			// cambiare
			// qualcosa nel formato che a me metodo mi è appena arrivato
			if (format.equals("js".trim())) {
				format = "javascript";
			} else if (format.equals("txt".trim())) {
				format = FORMAT_TEXT_FILE;
			} else if (format.equals("jpg".trim())) {
				format = FORMAT_JPG_IMAGE;
			} else if (format.equals("java".trim())) {
				format = FORMAT_JAVA;
			}
			// qui forse devo continuare a fare controlli

			switch (format) {

			case FORMAT_HTML:

				mimeType.setSubType(FORMAT_HTML);

				mimeType.setPrimaryType("text");

				break;

			case FORMAT_JAVA_SCRIPT:

				mimeType.setSubType(FORMAT_JAVA_SCRIPT);

				mimeType.setPrimaryType("text");

				break;
			case FORMAT_XML:

				mimeType.setSubType(FORMAT_XML);

				mimeType.setPrimaryType("text");

				break;
			case FORMAT_PNG_IMAGE:

				mimeType.setSubType(FORMAT_PNG_IMAGE);

				mimeType.setPrimaryType("image");

				break;
			// continuare da qui con gli altri mime type

			case "ico":

				// image/x-icon

				mimeType.setSubType(FORMAT_ICON);

				mimeType.setPrimaryType("image");

				break;
			case FORMAT_TEXT_FILE:
				mimeType.setSubType(format);

				mimeType.setPrimaryType("text");

				break;

			case FORMAT_CSS:
				mimeType.setSubType(FORMAT_CSS);

				mimeType.setPrimaryType("text");

				break;
			case FORMAT_JPG_IMAGE:

				mimeType.setSubType(FORMAT_JPG_IMAGE);

				mimeType.setPrimaryType("image");

				break;
			case FORMAT_GIF_IMAGE:

				mimeType.setSubType(FORMAT_GIF_IMAGE);

				mimeType.setPrimaryType("image");
				break;
			case FORMAT_JAVA:

				mimeType.setSubType(FORMAT_JAVA);

				mimeType.setPrimaryType("text");

				break;

			case FORMAT_PHP:

				mimeType.setSubType(FORMAT_PHP);

				mimeType.setPrimaryType("text");

				break;

			case FORMAT_JSON:

				mimeType.setSubType(FORMAT_JSON);

				mimeType.setPrimaryType("application");

				break;

			case "jon":
				mimeType.setSubType(FORMAT_JON);
				mimeType.setPrimaryType("text");
				break;

			// continuare da qui con altri mimeType
			}
			return mimeType;
		} else {
			return null;
		}
	}
}
