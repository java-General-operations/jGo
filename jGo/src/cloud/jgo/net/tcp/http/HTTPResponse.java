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

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;

import cloud.jgo.£;
import cloud.jgo.io.File;
import cloud.jgo.net.Server;
import cloud.jgo.net.tcp.http.headers.Header;
import cloud.jgo.net.tcp.http.headers.MimeHeader;
import cloud.jgo.net.tcp.http.mime.MimeTypeFactory;
/**
 * 
 * @author Martire91<br>
 * This class represents an http response,
 * and is an implementation of {@link HTTPMessage}
 *
 */
public class HTTPResponse implements HTTPMessage{
	// tutte una serie di variabili
	private cloud.jgo.io.File rootFolder = null ;
	private cloud.jgo.io.File fileToBeProcessed = null ;
	private  HTTPRequest request = null ;
	private DataOutputStream out = null;
	private String statusLine = null ;
	private HTTPBody body = new HTTPBody(this);
	
	/**
	 * This method returns the server root folder
	 * @return root folder
	 */
	public cloud.jgo.io.File getRootFolder() {
		return rootFolder;
	}
	
	/**
	 *	This method returns the processed file
	 * @return the processed file
	 */
	public cloud.jgo.io.File getFileToBeProcessed() {
		return this.fileToBeProcessed;
	}
	
	/**
	 * This method adds headers in the response
	 * @param headers the headers
	 */
	public void addHeaders(Header...headers){
		
		for (int i = 0; i < headers.length; i++) {
		      addHeader(headers[i]);
		}
	}
	public HTTPResponse(HTTPRequest request, DataOutputStream out) {
		// TODO Auto-generated constructor stub
		this.request = request ;
		if(request.getFileName()!=null){
			this.fileToBeProcessed = new File(request.getFileName());
		}
		this.rootFolder = new File(request.getRoot());
		this.out  = out ;
	}
	
	/**
	 * This method configures the response by default.<br>
	 * Use the HttpMainPage annotation for this method.
	 * @param class_ handler type
	 * @throws MalformedURLException 1 exception
	 * @throws MimeTypeParseException 2 exception
	 */
	public void basicConfig(Class<?extends HTTPHandlerConnection>class_) throws MalformedURLException, MimeTypeParseException{
		
		if (getFileToBeProcessed()!=null && getRootFolder()!=null) {
			// okok imposto lo status line 	
			
			// verifico di che file si tratta 
		
			if(getFileToBeProcessed().exists()){
				setStatusLine(ResponseCode.RESPONSE_CODE_OK, HTTPServer.HTTP_VERSION);
				if (getFileToBeProcessed().isDirectory()) {
					
					
					// è una cartella 
					// verifico se si tratta della cartella root 
					
					if (getFileToBeProcessed().equals(rootFolder)) {
						
						
						boolean mainPagePresent = class_.isAnnotationPresent(HTTPMainPage.class);
						if (mainPagePresent) {
							// è presente l'annotazione che indica i nomi dei files home/index
							
							cloud.jgo.io.File mainPage = null ;
							
							// ora devo prendere i valori attribuiti a questa annotazione 
							
							HTTPMainPage ann = class_.getAnnotation(HTTPMainPage.class);
							
							// okok ci siamo prendo i valori 
							
							for (String fileName:ann.filesNames()) {
								
								if (£.fl_(getRootFolder(),fileName).exists()) {
									// okok esiste 
									mainPage = £.fl_(getRootFolder(), fileName);
									// posso uscire dal ciclo perchè ho trovato il file 
									break ;
								}
							}
							if (mainPage!=null) {
								
								
								
								// okok configuro la risposta in virtù di questo file 
								
								Header header=null;
								try {
									header = Header.getDefaultInstance(Header.Type.CONTENT_LENGTH, mainPage.getBytes().length);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								MimeHeader headerMime = new MimeHeader(MimeTypeFactory.getDefaultMimeType(£.extractFormatFromFile(mainPage)));
								
								// aggiungo gli headers 
								
								try {
									addHeaders(header,headerMime);
									insertVoidRow();
									getBody().addBytes(mainPage);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
							
						}
						else{
							// qui comunichiamo che non è stata settata nessuna annotazione
				            StringBuffer bufferCode = new StringBuffer();
				            bufferCode.append("<html>"+Header.CRLF);
				            bufferCode.append("<body>"+Header.CRLF);
				            bufferCode.append("<h1 style='background:#282828;color:white;'>JGO_1.0.0</h1>"+Header.CRLF);
				            bufferCode.append("<h3 style='color:red'># You must use the annotation : @HTTPMainPage</h3>"+Header.CRLF);
				            bufferCode.append("</body>"+Header.CRLF);
				            bufferCode.append("</html>"+Header.CRLF);
				            Header header = Header.getDefaultInstance(Header.Type.CONTENT_LENGTH, bufferCode.toString().getBytes().length);
				            MimeType mime = MimeTypeFactory.getDefaultMimeType(MimeTypeFactory.FORMAT_HTML);
				            MimeHeader header_2 = new MimeHeader(mime);
				            addHeaders(header,header_2);
							insertVoidRow();
							getBody().addBytes(bufferCode);
						}
					}
					else
					{
						
						/**
						 * è una cartella ma non la root 
						 */
						StringBuffer buffer = new StringBuffer();
						java.io.File files[] = fileToBeProcessed.listFiles();
						buffer.append("<html>\n");
						buffer.append("<head><style>.size{text-align:center;}.format{text-align:center;}</style></head>");
						buffer.append("<body><div><h1><img src='https://www.jgo.cloud/wp-content/uploads/2018/11/jgo2.png'></h1></div><h2><font face='verdana'>Index of "+fileToBeProcessed.getPath()+"</font></h2>\n");
						buffer.append("<table border='1px'>\n");
						buffer.append("<tr><th>Nome</th><th>Size</th><th>URL</th><th>Type</th></tr>\n");
						for (int i = 0; i < files.length; i++) {
							
							java.io.File currentFile = files[i];
							
							if(currentFile.isDirectory()){
								try {
									buffer.append("<tr><td><a href='"+request.getDecodedUrl().toString()+"/"+currentFile.getName()+"'><img src='http://findicons.com/files/icons/2221/folder/128/normal_folder.png' width='20'>&nbsp&nbsp"+currentFile.getName()+"</a></td>&nbsp&nbsp<td class='size'>#</td></td><td class='format'><a href='"+request.getDecodedUrl().toString()+"/"+currentFile.getName()+"'>"+request.getDecodedUrl().toString()+"/"+currentFile.getName()+"</a></td><td>Directory</td></tr>\n");
								} catch (UnsupportedEncodingException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							else{
								FileInputStream fis=null;
								try {
									fis = new FileInputStream(currentFile);
								} catch (FileNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								int available = 0;
								try {
									available=fis.available();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								byte[]buffer_ = new byte[available];
								try {
									fis.read(buffer_);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								try {
									fis.close();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								try {
									buffer.append("<tr><td><a href='"+request.getDecodedUrl().toString()+"/"+currentFile.getName()+"'><img src='http://icons.iconarchive.com/icons/dtafalonso/android-lollipop/512/Docs-icon.png' width='20'>&nbsp&nbsp"+currentFile.getName()+"</a></td>&nbsp&nbsp<td class='size'>"+buffer_.length+" bytes</td><td class='format'><a href='"+request.getDecodedUrl().toString()+"/"+currentFile.getName()+"'>"+request.getDecodedUrl().toString()+"/"+currentFile.getName()+"</a></td><td>File</td></tr>\n");
								} catch (UnsupportedEncodingException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							
							
						}
						buffer.append("</table><br></br>\n");
						buffer.append("<center><a href='http://"+request.getServerAddress()+"/'><h2>Return to Root</h2></a></center>");
						buffer.append("</body>\n");
						buffer.append("</html>\n");
						// okok creo gli header 
						
						addHeaders(Header.getDefaultInstance(Header.Type.CONTENT_TYPE,buffer.toString().getBytes().length)
						,new MimeHeader(MimeTypeFactory.getDefaultMimeType(MimeTypeFactory.FORMAT_HTML)));
						insertVoidRow();
						// preparo il body 
						getBody().addBytes(buffer);
						
					}	
				}
				else{
					
					// is a file 
					
	             if (getFileToBeProcessed().getName().equals("favicon.ico")) {
					
	            	 // qui devo verificare se esiste nella root una favicon
	            	 
	            	 if (£.fl_(getRootFolder(),"favicon.ico").exists()) {
						 // se esiste bene se no niente 
	            		 Header header = null ;
	      				try {
	      					header  = Header.getDefaultInstance(Header.Type.CONTENT_LENGTH,getFileToBeProcessed().getBytes().length);
	      				} catch (IOException e) {
	      					// TODO Auto-generated catch block
	      					e.printStackTrace();
	      				}
	      				MimeHeader headerMime = new MimeHeader(MimeTypeFactory.getDefaultMimeType(£.extractFormatFromFile(getFileToBeProcessed())));
	      				try {
	      					addHeaders(header,headerMime);
	      					insertVoidRow();
	      					getBody().addBytes(getFileToBeProcessed());
	      				} catch (IOException e) {
	      					// TODO Auto-generated catch block
	      					e.printStackTrace();
	      				}	
					} 
				}
	             else{
	            	 // è un file ma non è il file favicon.ico
	            	 Header header = null ;
	 				try {
	 					header  = Header.getDefaultInstance(Header.Type.CONTENT_LENGTH,getFileToBeProcessed().getBytes().length);
	 				} catch (IOException e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				}
	 				MimeHeader headerMime = new MimeHeader(MimeTypeFactory.getDefaultMimeType(£.extractFormatFromFile(getFileToBeProcessed())));
	 				try {
	 					addHeaders(header,headerMime);
	 					insertVoidRow();
	 					getBody().addBytes(getFileToBeProcessed());
	 				} catch (IOException e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				}	
	             }
				}
				// si procede da qui ,imposto il body su pronto 
			}
			else{
				
				// la risorsa non esiste 
				
				setStatusLine(ResponseCode.RESOURCE_NOT_FOUND, HTTPServer.HTTP_VERSION);
				
				
			}
			getBody().ready();
			Transport.trasfer(this); 
		}
	}

	/**
	 * This method returns the response output
	 * @return response output
	 */
	public DataOutputStream output() {
		return this.out ;
	}

	/**
	 * This method sets the status line
	 * @param responseCode response code
	 * @param http_version http version
	 */
	public void setStatusLine(ResponseCode responseCode,String http_version){
		this.statusLine = http_version+" "+responseCode+"\r\n";
		try {
			this.getResponseBytes().write(this.statusLine.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method returns the response buffer
	 * @return response buffer
	 */
	public byte[] getResponseBuffer() {
		
		return this.getResponseBytes().toByteArray();
	}
	
	/**
	 * This method inserts a void row.
	 * You must use it after adding the headers.
	 */
	public void insertVoidRow(){
		
		String voidRow = "\r\n";
		byte[]buffer = voidRow.getBytes();
		try {
			this.getResponseBytes().write(buffer);
			this.getResponseBytes().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Empty the entire buffer.
	 * Delete all the bytes entered in the response
	 */
	public void clear(){
		getResponseBytes().reset();
	}
	
	/**
	 * This method returns the response body
	 * @return response body
	 */
	public HTTPBody getBody() {
		// TODO Auto-generated method stub
		return (HTTPBody) this.body ;
	}
	
	/**
	 * This method sets a new body
	 * @param body the body
	 */
	public void setBody(HTTPBody body) {
		this.body = body;
	}

	private List<Header>headers = new ArrayList<>();
	private ByteArrayOutputStream responseBytes = new ByteArrayOutputStream();
	/**
	 * This method returns the status line
	 * @return the status line
	 */
	public String getStatusLine() {
		return statusLine;
	}
	
	/**
	 * This method returns true if there isn't headers in the response
	 * @return the flag
	 */
    public boolean isEmptyHeaders(){
    	return this.headers.isEmpty() ;
    }
    
    /**
     * This method adds a header
     * @param header the header
     */
	public void addHeader(Header header){
		this.headers.add(header);
		byte[]buffer = header.toString().getBytes();
		try {
			responseBytes.write(buffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			responseBytes.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public boolean isPresent(Header.Type type){
		boolean present = false ;
		List<Header>list = headers;
		for (int i = 0; i < list.size(); i++) {
			Header.Type headerType = list.get(i).getType();
			if(headerType.equals(type)){
				present = true ;
				break ;
			}
		}
		return present ;
	}
	
	@Override
	public void removeHeader(Header.Type type){
		// controllo in tanto se il tipo è disponibile 
		String type_ = type.getType();
		if (Header.Type.isAvailable(type_)) {
			// ora qui mi serve un metodo che mi indica se questo tipo è presente nella risposta 
			
			if(isPresent(type)){
			
				// okok è presente questo header quindi provvedo per eliminarlo 
				
				for (int i = 0; i < headers.size(); i++) {
					
					if (type.equals(headers.get(i).getType())) {
						
						// elimino l'header 
						try {
							 removeHeader(i);
						} catch (IOException e) {
							/*
							 
							JGO Auto-generated catch block
							Author : £ wasp91 £
							Date 19 feb 2018
							
							*/
							e.printStackTrace();
						}
					}
				}
			}
			
		}
	}
	@Override
	public void removeAllHeaders() throws IOException{
		if (!headers.isEmpty()) {
			headers.clear();
			
			// reinizializzo il buffer di bytes 
			
			this.responseBytes = new ByteArrayOutputStream();
			
			// qui verifico se lo status line è diverso da null
			
			// in modo tale da reinserirlo nel bytes di risposta 
			
			if(this.statusLine!=null){
				byte[]buffer = this.statusLine.getBytes();
				responseBytes.write(buffer);
				responseBytes.flush();
			}
		}
	}
	@Override
	public void printHeaders(){
		if(!isEmptyHeaders()){
			for (int i = 0; i < headers.size(); i++) {
				System.out.println(headers.get(i));
			}
		}
	}
	
	
	/*
	 * Sembra che vada bene ,in ogni caso devo testarlo per bene
	 */
	@Override
	public boolean removeHeader(int index) throws IOException{
		boolean flag = false ;
		if(index<=headers.size()-1){
			this.headers.remove(index);
			
			// adesso qui controlliamo se non ci sono più headers
			// oppure ve ne sono ancora 
			
			// in qualsiasi caso reinizializzo lo stream di output 
			this.responseBytes = new ByteArrayOutputStream();
			
			// di sicuro verifico se lo status line era impostato cosi lo riscrivo 
			
			if(statusLine!=null){
				
				this.responseBytes.write(this.statusLine.getBytes());
				this.responseBytes.flush();
			}
			
			// adesso qui è importante,devo verificare solo se la lista non  è vuota o meno
			// per inserire gli headers rimanenti nello stream di output
			if(!isEmptyHeaders()){ // se non è vuota ,allora inserisci tutti gli headers rimanenti 
				
				for (int i = 0; i < headers.size(); i++) {
					if(headers.get(i)!=null){
						
						this.responseBytes.write(headers.get(i).toString().getBytes());
						this.responseBytes.flush();
					}
				}
			}
			flag = true ; // qui settiamo a true perchè l'eliminazione è avvenuta
		}
		
		return flag ;
	}
	
	protected ByteArrayOutputStream getResponseBytes() {
		return responseBytes;
	}

	/**
	 * This method returns the headers number
	 * @return the headers number
	 */
	public int getHeadersCount(){
		return headers.size();
	}

	@Override
	public Iterator<Header> iterator() {
		return this.headers.iterator();
	}
	
	
	public Header header(int index){
		
		if (getHeadersCount()>0) {
			
			return this.headers.get(index);
			
		}
		else{
			return null ;
		}
	}
	
	
	
	public Header header(Header.Type type){
	     boolean valid = false ;
	     Header headerFound = null ;
		// qui dv per prima cosa controllare che il tipo sia giusto
		for (int i = 0; i < Header.Type.getAvailableTypes().length; i++) {
			
			if(type.equals(Header.Type.getAvailableTypes()[i])){
				valid = true ;
			}
		}
		
		if(valid){
			// adesso controlliamo se è stato inserito nella risposta un header di questo tipo 
			// qui prendo gli headers della risposta 
			if(getHeadersCount()>0){
				
				Iterator<Header>iterator = this.iterator();
				
				while (iterator.hasNext()) {
					Header header = (Header) iterator.next();
					// prendo il tipo dell'header 
					Header.Type typ = header.getType();
					if (type.equals(typ)) {
						headerFound = header ;
					}
				}
				
				return headerFound ;
			}
			else{
				return null ;
			}
		}
		else{
			return null ;
		}
		
	}
	
	
}
