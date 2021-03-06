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
 * Copyright � 2018 - Marco Martire (www.jgo.cloud)
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
package cloud.jgo.net.tcp.http.jor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;
import javax.swing.JOptionPane;

import com.google.gson.Gson;

import cloud.jgo.�;
import cloud.jgo.io.jon.JON.JONFile;
import cloud.jgo.io.jon.JONMarshallingException;
import cloud.jgo.io.jon.JONNotSupportedTypeException;
import cloud.jgo.net.tcp.http.HTTPHandlerConnection;
import cloud.jgo.net.tcp.http.HTTPRequest;
import cloud.jgo.net.tcp.http.HTTPResponse;
import cloud.jgo.net.tcp.http.HTTPServer;
import cloud.jgo.net.tcp.http.ResponseCode;
import cloud.jgo.net.tcp.http.Transport;
import cloud.jgo.net.tcp.http.headers.Header;
import cloud.jgo.net.tcp.http.headers.MimeHeader;
import cloud.jgo.net.tcp.http.jor.annotations.JOR;
import cloud.jgo.net.tcp.http.mime.MimeTypeFactory;

/**
 * @author Martire91<br>
 *         This class represents the jor handler connection
 */
public abstract class JORHandlerConnection extends HTTPHandlerConnection {
	@SuppressWarnings("static-access")
	private void processingEngine(JOR annotation, Object servObject, String locationResource, HTTPResponse res)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException,
			IOException {
		locationResource = URLDecoder.decode(locationResource);
		if (locationResource.endsWith("/")) {
			int lastSlash = locationResource.lastIndexOf("/");
			locationResource = locationResource.substring(0, lastSlash);
		}
		boolean toSave = annotation.SaveFiles();
		String sep = annotation.separator();
		Map<Object, String> structure = new HashMap<>();
		Object[] objects = (Object[]) servObject;
		String url_pattern = annotation.url_Pattern();
		if (!url_pattern.startsWith("/")) {
			url_pattern = "/"+url_pattern ;
		}
		final String originalUrlPattern = url_pattern;
		ResponseType typeRes = annotation.responseType();
		String idField = annotation.field_id();
		boolean concat = false;
		String[] idFields = null;
		// okok qui dobbiamo controllare se � solo un
		// idField oppure ce ne sono diversi ...
		if (idField.contains("+")) {
			concat = true;
			// qui dentro mi faccio lo split
			idFields = idField.split("\\+");
		}
		Object found = null;
		String nameObject = null;
		if (url_pattern != null && typeRes != null && idField != null) {
			for (int i = 0; i < objects.length; i++) {
				if (concat) {
					StringBuffer urlBuffer = new StringBuffer();
					urlBuffer.append("/");
					// c'� un concatenamento
					// faccaio iterare i campi
					for (int j = 0; j < idFields.length; j++) {
						String currentIdField = idFields[j].trim();
						if (!currentIdField.isEmpty()) {
							Field field = servObject.getClass().getComponentType().getDeclaredField(currentIdField);
							field.setAccessible(true);
							String objId = null;
							if (field.getType().getSimpleName().equals("String")) {
								// si tratta di una stringa
								objId = (String) field.get(objects[i]);
							} else if (field.getType().isPrimitive()) {

								if (field.getType().getSimpleName().equals("int")) {
									objId = String.valueOf(field.getInt(objects[i]));
								} else if (field.getType().getSimpleName().equals("double")) {
									objId = String.valueOf(field.getDouble(objects[i]));
								} else if (field.getType().getSimpleName().equals("float")) {
									objId = String.valueOf(field.getFloat(objects[i]));
								} else if (field.getType().getSimpleName().equals("long")) {
									objId = String.valueOf(field.getLong(objects[i]));
								} else if (field.getType().getSimpleName().equals("char")) {
									objId = String.valueOf(field.getChar(objects[i]));
								} else if (field.getType().getSimpleName().equals("boolean")) {
									objId = String.valueOf(field.getBoolean(objects[i]));
								} else if (field.getType().getSimpleName().equals("short")) {
									objId = String.valueOf(field.getShort(objects[i]));
								}
							} else {
								// qui si tratta di un oggetto quindi va gestito...
							}
							if (j < idFields.length - 1) {
								urlBuffer.append(objId + sep);
							} else {
								urlBuffer.append(objId);
							}
						}
					}
					// organizzo la struttura :
					String finalURL = url_pattern.concat(urlBuffer.toString());
					structure.put(objects[i], finalURL);
				} else {
					// quindi se non c'� un concatenament si opera come prima @
					Field field = servObject.getClass().getComponentType().getDeclaredField(idField);
					field.setAccessible(true);
					// prendo l'id dell'oggetto in questione
					String objectId = (String) field.get(objects[i]);
					if (objectId != null) {
						String finalURL = url_pattern.concat("/" + objectId);
						structure.put(objects[i], finalURL);
					}
				}
			}
			// @
			// faccio iterare gli oggetti in tanto giacch� si tratti di un array

			for (int i = 0; i < objects.length; i++) {
				if (concat) {
					StringBuffer urlBuffer = new StringBuffer();
					String objId = null;
					urlBuffer.append("/");
					// da sviluppare ...
					for (int j = 0; j < idFields.length; j++) {
						String currentIdField = idFields[j].trim();
						Field field = servObject.getClass().getComponentType().getDeclaredField(currentIdField);
						field.setAccessible(true);
						if (field.getType().getSimpleName().equals("String")) {
							// si tratta di una stringa
							objId = (String) field.get(objects[i]);
						} else if (field.getType().isPrimitive()) {

							if (field.getType().getSimpleName().equals("int")) {
								objId = String.valueOf(field.getInt(objects[i]));
							} else if (field.getType().getSimpleName().equals("double")) {
								objId = String.valueOf(field.getDouble(objects[i]));
							} else if (field.getType().getSimpleName().equals("float")) {
								objId = String.valueOf(field.getFloat(objects[i]));
							} else if (field.getType().getSimpleName().equals("long")) {
								objId = String.valueOf(field.getLong(objects[i]));
							} else if (field.getType().getSimpleName().equals("char")) {
								objId = String.valueOf(field.getChar(objects[i]));
							} else if (field.getType().getSimpleName().equals("boolean")) {
								objId = String.valueOf(field.getBoolean(objects[i]));
							} else if (field.getType().getSimpleName().equals("short")) {
								objId = String.valueOf(field.getShort(objects[i]));
							}
						} else {
							// qui si tratta di un oggetto quindi va gestito...
						}
						if (j < idFields.length - 1) {
							urlBuffer.append(objId + sep);
						} else {
							urlBuffer.append(objId);
						}
					}
					String finalURL = url_pattern.concat(urlBuffer.toString());

					// confronto questo url con quello digitato dal client

					if (locationResource.equals(finalURL)) {
						found = objects[i];
						nameObject = urlBuffer.toString().replaceAll("/", "");
						break;
					} else if (locationResource.equals(url_pattern)) {

						organizesObjectsRootPage(structure, res, originalUrlPattern);
					}
				} else {
					Field field = servObject.getClass().getComponentType().getDeclaredField(idField);
					field.setAccessible(true);
					// prendo l'id dell'oggetto in questione
					String objectId = (String) field.get(objects[i]);
					if (objectId != null) {
						// completo l'url giusto dell'oggetto
						String finalURL = url_pattern.concat("/" + objectId);

						// confronto questo url con quello digitato dal client

						if (locationResource.equals(finalURL)) {

							// abbiamo trovato l'oggetto
							// quindi possiamo
							found = objects[i];
							nameObject = objectId;
							break;
						} else if (locationResource.equals(url_pattern)) {

							organizesObjectsRootPage(structure, res, originalUrlPattern);
						}

					}
				}
			}
			if (found != null) {
				res.setStatusLine(ResponseCode.RESPONSE_CODE_OK, HTTPServer.HTTP_VERSION);
				switch (typeRes) {
				case JON:
					JONFile file = null;
					try {
						try {
							try {
								if (getServer().getRootFolder() != null) {
									file = �.JON.marsh(found,
											getServer().getRootFolder() + File.separator + nameObject + ".jon");
								} else {

									file = �.JON.marsh(found, nameObject + ".jon");
								}
							} catch (JONNotSupportedTypeException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} catch (JONMarshallingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if (file != null) {
						// imposto gli headers
						int content = file.getFile().getBytes().length;
						Header header = Header.getDefaultInstance(Header.Type.CONTENT_LENGTH);

						header.setValue(content);

						MimeType mime = null;
						try {
							mime = MimeTypeFactory.getDefaultMimeType(MimeTypeFactory.FORMAT_TEXT_FILE);
						} catch (MimeTypeParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						MimeHeader headerMime = new MimeHeader(mime);
						res.addHeaders(header, headerMime);

						// inserisco una stringa vuota

						res.insertVoidRow();

						// preparo il body

						res.getBody().addBytes(file.getFile());
						res.getBody().ready();

						// invio la risposta al client

						Transport.trasfer(res);
						if (toSave == false) {
							boolean deleted = file.getFile().delete();
							System.out.println("Eliminazione file:" + deleted);
							if (!deleted) {
								file.getFile().deleteOnExit();
								System.out.println("File eliminato @");
							}
						}
					}

					break;

				case JSON:
					cloud.jgo.io.File jsonFile = null;
					if (getServer().getRootFolder() != null) {
						jsonFile = new cloud.jgo.io.File(
								getServer().getRootFolder() + File.separator + nameObject + ".json");
					} else {
						jsonFile = new cloud.jgo.io.File(nameObject + ".json");
					}
					if (jsonFile != null) {
						Gson gson = new Gson();
						String json = gson.toJson(found);
						�.writeFile(jsonFile, false, new String[] { json });
						int bytesLen = jsonFile.getBytes().length;
						Header contentHeader = Header.getDefaultInstance(Header.Type.CONTENT_LENGTH);
						MimeHeader mimeHead = null;
						try {
							mimeHead = new MimeHeader(MimeTypeFactory.getDefaultMimeType(MimeTypeFactory.FORMAT_JSON));
							contentHeader.setValue(bytesLen);
							res.addHeaders(contentHeader, mimeHead);
							res.insertVoidRow();
							res.getBody().addBytes(jsonFile);
							res.getBody().ready();
							Transport.trasfer(res);
							if (toSave == false) {
								boolean deleted = jsonFile.delete();
								if (!deleted) {
									jsonFile.deleteOnExit();
								}
							}
						} catch (MimeTypeParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					break;
				case XML:
					cloud.jgo.io.File fileXML = null;
					if (getServer().getRootFolder() != null) {
						fileXML = �.convertFromObjectToXML(found.getClass(),
								getServer().getRootFolder() + File.separator + nameObject + ".xml", found);
					} else {
						fileXML = �.convertFromObjectToXML(found.getClass(), nameObject + ".xml", found);
					}
					if (fileXML != null) {
						int content = fileXML.getBytes().length;
						Header header = Header.getDefaultInstance(Header.Type.CONTENT_LENGTH);

						header.setValue(content);

						MimeHeader headerMime = null;
						try {
							headerMime = new MimeHeader(MimeTypeFactory.getDefaultMimeType(MimeTypeFactory.FORMAT_XML));
						} catch (MimeTypeParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						res.addHeaders(header, headerMime);

						// inserisco una stringa vuota

						res.insertVoidRow();

						// preparo il body

						res.getBody().addBytes(fileXML);
						res.getBody().ready();

						// invio la risposta al client

						Transport.trasfer(res);

						// elimino il file
						if (toSave == false) {
							boolean deleted = fileXML.delete();
							if (!deleted) {
								fileXML.deleteOnExit();
							}
						}
					}

					break;

				case HTML:
					// qui imposto solo il mimeHeader html
					MimeHeader mimeHeader = null;
					try {
						mimeHeader = new MimeHeader(MimeTypeFactory.getDefaultMimeType(MimeTypeFactory.FORMAT_HTML));
					} catch (MimeTypeParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					res.addHeader(mimeHeader);
					html_represents(found, res, nameObject);
					break;
				}
			}

		}
	}

	// spiegare questo metodo : da implementare
	/**
	 * through this method, we have the possibility to represent on the web a root
	 * page, containing all the objects of that particular class, or all the links
	 * of the objects.
	 * 
	 * @param structure
	 *            the structure
	 * @param response
	 *            the http response
	 * @param originalUrlPattern
	 *            the url pattern
	 */
	protected abstract void organizesObjectsRootPage(Map<Object, String> structure, HTTPResponse response,
			final String originalUrlPattern);

	/**
	 * This method must be redefined to represent the object in question in html
	 * format
	 * 
	 * @param obj
	 *            the object
	 * @param res
	 *            the http response
	 * @param nameObj
	 *            the object name
	 */
	protected abstract void html_represents(Object obj, HTTPResponse res, String nameObj);

	@Override
	public void manage(HTTPRequest request, HTTPResponse response) throws IOException, ClassNotFoundException {
		// qui in tanto verifico se il file richiesto esiste

		if (response.getFileToBeProcessed() != null) {

			if (response.getFileToBeProcessed().exists()) {

				try {
					response.basicConfig((Class<HTTPHandlerConnection>) getServer().getModel().getClass());
				} catch (MimeTypeParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				// non esiste la risorsa
				// qui controllo se si tratta di un server jor

				if (getServer() instanceof JORServer) {
					// bene

					// ottengo la matrice
					ArrayList<Object> matrix = ((JORServer) getServer()).getMatrix();

					// faccio iterare la matrice

					for (int i = 0; i < matrix.size(); i++) {

						Object obj = matrix.get(i);

						if (obj.getClass().isArray()) {
							if (obj.getClass().getComponentType().isAnnotationPresent(JOR.class)) {
								// okok gli oggetti sono annotati con la JGO_web
								JOR jgo_web_instance = obj.getClass().getComponentType().getAnnotation(JOR.class);
								try {
									try {
										processingEngine(jgo_web_instance, obj, request.getLocationResource(),
												response);
									} catch (IllegalArgumentException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (IllegalAccessException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} catch (NoSuchFieldException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (SecurityException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}
						} else {
							// qui � una lista : per� la converto in array
							List<?> list = (List<?>) obj;
							Class<?> class_ = list.get(0).getClass();
							if (class_.isAnnotationPresent(JOR.class)) {
								// trasformo in array
								JOR jgo_web_instance = class_.getAnnotation(JOR.class);
								Object[] arr = (Object[]) Array.newInstance(class_, list.size());
								for (int j = 0; j < arr.length; j++) {
									arr[j] = list.get(j);
								}
								try {
									processingEngine(jgo_web_instance, arr, request.getLocationResource(), response);
								} catch (NoSuchFieldException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (SecurityException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IllegalArgumentException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IllegalAccessException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
		}

		getSocket().close();
	}

}
