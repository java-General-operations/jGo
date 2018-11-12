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

import cloud.jgo.£;
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
import cloud.jgo.net.tcp.http.jor.JORServer.JOR;
import cloud.jgo.net.tcp.http.mime.MimeTypeFactory;
/**
 * @author Martire91<br>
 * This class represents the jor handler connection	
 */
public abstract class JORHandlerConnection extends HTTPHandlerConnection{
	@SuppressWarnings("static-access")
	private void processingEngine(JOR annotation,Object servObject,String locationResource,HTTPResponse res) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, IOException{
		locationResource = URLDecoder.decode(locationResource);
		if (locationResource.endsWith("/")) {
			int lastSlash = locationResource.lastIndexOf("/");
			locationResource = locationResource.substring(0,lastSlash);
		}
			Map<Object,String> structure = new HashMap<>();
			Object[]objects = (Object[]) servObject ;
			String url_pattern = annotation.url_Pattern() ;
			final String originalUrlPattern = url_pattern ;
			cloud.jgo.net.tcp.http.jor.JORServer.JOR.ResponseType typeRes = annotation.responseType();
			String idField = annotation.field_id();
			
			
			Object found = null ;
			String nameObject = null ;
			if (servObject.getClass().getComponentType().getDeclaredField(idField)!=null && servObject.getClass().getComponentType().getDeclaredField(idField).getType().getSimpleName().equals("String")) {
				if (url_pattern !=null && typeRes!=null && idField!=null) {
					// organizzo la struttura da qui a @
					for (int i = 0; i < objects.length; i++) {
						Field field = servObject.getClass().getComponentType().getDeclaredField(idField);
						field.setAccessible(true);
						// prendo l'id dell'oggetto in questione 
						String objectId = (String) field.get(objects[i]);
						if (objectId!=null) {
							String finalURL = url_pattern.concat("/"+objectId);
							structure.put(objects[i], finalURL);
						}
					}
					// @
					// faccio iterare gli oggetti in tanto giacchè si tratti di un array 
					
					for (int i = 0; i < objects.length; i++) {
						Field field = servObject.getClass().getComponentType().getDeclaredField(idField);
						field.setAccessible(true);
						
						// prendo l'id dell'oggetto in questione 
						
						String objectId = (String) field.get(objects[i]);
						if (objectId!=null) {
							
							
							// completo l'url giusto dell'oggetto 
							String finalURL = url_pattern.concat("/"+objectId);
							
							// confronto questo url con quello digitato dal client
							
							if (locationResource.equals(finalURL)) {
								
							
								// abbiamo trovato l'oggetto
								// quindi possiamo 
								found = objects[i];
								nameObject = objectId ;
								break ;
							}
							else if(locationResource.equals(url_pattern)){
								
								organizesObjectsRootPage(structure,res,originalUrlPattern);
							}
							
						}
						
					}
					
					if (found!=null) {
						// adesso qui devo 
						// verificare che tipo di ritorno è
						// quindi se è un tipo : jgon oppure xml
						// allora diamo una risposta predefinita mostrando
						// il sorgente del formato richiesto
						// tuttavia se è in html dobbiamo definire un metodo
						// con il quale andiamo a rappresentare l'oggetto richiesto
						// in html customizzando a modo nostro
						
						// 1 passo imposto lo status line 
						
						res.setStatusLine(ResponseCode.RESPONSE_CODE_OK, HTTPServer.HTTP_VERSION);
						switch(typeRes){
						case JON_TYPE :
							JONFile file = null ;
							try {
								try {
									try {
										file = £.JON.marsh(found,getServer().getRootFolder()+File.separator+nameObject+".jon");
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
							
							if (file!=null) {
								// imposto gli headers
								int content = file.getFile().getBytes().length;
								Header header = Header.getDefaultInstance(Header.Type.CONTENT_LENGTH);
								
								header.setValue(content);
								
								MimeType mime=null;
								try {
									mime = MimeTypeFactory.getDefaultMimeType(MimeTypeFactory.FORMAT_TEXT_FILE);
								} catch (MimeTypeParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								MimeHeader headerMime = new MimeHeader(mime);
								res.addHeaders(header,headerMime);
								
								// inserisco una stringa vuota
								
								res.insertVoidRow();
								
								// preparo il body 
								
								res.getBody().addBytes(file.getFile());
								res.getBody().ready();
								
								// invio la risposta al client 
								
								Transport.trasfer(res);
							}
							
							break ;
							
						case XML_TYPE:
							cloud.jgo.io.File fileXML = £.convertFromObjectToXML(found.getClass(),nameObject+".xml",found);
							if (fileXML!=null) {
								
								int content = fileXML.getBytes().length;
								Header header = Header.getDefaultInstance(Header.Type.CONTENT_LENGTH);
								
								header.setValue(content);
								
								MimeHeader headerMime=null;
								try {
									headerMime = new MimeHeader(MimeTypeFactory.getDefaultMimeType(MimeTypeFactory.FORMAT_XML));
								} catch (MimeTypeParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								res.addHeaders(header,headerMime);
								
								// inserisco una stringa vuota
								
								res.insertVoidRow();
								
								// preparo il body 
								
								res.getBody().addBytes(fileXML);
								res.getBody().ready();
								
								// invio la risposta al client 
								
								Transport.trasfer(res);
							}
					
							break ;
							
						case HTML_TYPE :
							// qui imposto solo il mimeHeader html
							MimeHeader mimeHeader = null ;
							try {
								mimeHeader = new MimeHeader(MimeTypeFactory.getDefaultMimeType(MimeTypeFactory.FORMAT_HTML));
							} catch (MimeTypeParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							res.addHeader(mimeHeader);
							html_represents(found,res,nameObject);
							break ;
						}
						
					}
					
				}
			}		
			
	}
	// spiegare questo metodo : da implementare
	/**
	 * through this method, we have the possibility to represent on the web a root page,
	 * containing all the objects of that particular class, or all the links of the objects.
	 * @param structure the structure
	 * @param response the http response
	 * @param originalUrlPattern the url pattern
	 */
	protected abstract void organizesObjectsRootPage(Map<Object, String> structure,HTTPResponse response,final String originalUrlPattern);

	/**
	 * This method must be redefined to represent the object in question in html format
	 * @param obj the object
	 * @param res the http response
	 * @param nameObj the object name
	 */
	protected abstract void html_represents(Object obj, HTTPResponse res, String nameObj);

	@Override
	public void manage(HTTPRequest request, HTTPResponse response) throws IOException, ClassNotFoundException {
		// qui in tanto verifico se il file richiesto esiste
		
		if (response.getFileToBeProcessed()!=null) {
			
			if(response.getFileToBeProcessed().exists()){

				try {
					response.basicConfig((Class<HTTPHandlerConnection>) getServer().getModel().getClass());
				} catch (MimeTypeParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
					// non esiste la risorsa 
				// qui controllo se si tratta di un server jor
				
				if (getServer()instanceof JORServer) {
					// bene
					
					// ottengo la matrice 
					ArrayList<Object>matrix = ((JORServer)getServer()).getMatrix();
					
					
					// faccio iterare la matrice 
					
					for (int i = 0; i < matrix.size(); i++) {
						
						
						Object obj = matrix.get(i);
						
						if (obj.getClass().isArray()) {
							if (obj.getClass().getComponentType().isAnnotationPresent(JOR.class)) {
								// okok gli oggetti sono annotati con la JGO_web
								JOR jgo_web_instance = obj.getClass().getComponentType().getAnnotation(JOR.class);
								try {
									try {
										processingEngine(jgo_web_instance,obj,request.getLocationResource(),response);
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
						}
						else{
							// qui è una lista : però la converto in array
							List<?>list = (List<?>) obj;
							Class<?>class_ = list.get(0).getClass();
							if (class_.isAnnotationPresent(JOR.class)) {
								// trasformo in array 
								JOR jgo_web_instance = class_.getAnnotation(JOR.class);
								Object[]arr = (Object[]) Array.newInstance(class_,list.size());
								for (int j = 0; j < arr.length; j++) {
									arr[j] = list.get(j);
								}
								 try {
										processingEngine(jgo_web_instance,arr,request.getLocationResource(),response);
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
