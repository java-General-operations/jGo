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
/**
 * 
 * @author Martire91<br>
 * it represents a response body
 *
 */
public  class HTTPBody {

	// qui ci starebbe bene un bel ByteBuffer
	
	private HTTPResponse response;
	private boolean ready = false ;

	/**
	 * This method adds the text in body
	 * @param text the text you want to add
	 */
	public void addBytes(String text){
		byte[]buffer = text.getBytes();
		// aggiungo anche alla stream completo di byte della risposta
		try {
			response.getResponseBytes().write(buffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			response.getResponseBytes().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public HTTPBody(HTTPResponse response) {
		// TODO Auto-generated constructor stub
		this.response = response ;
	}
	
	/**
	 * This method adds a file in body
	 * @param file the file you want to add
	 * @throws IOException 1 exception
	 */
	public void addBytes(cloud.jgo.io.File file) throws IOException{
		byte[]buffer = file.getBytes();
		// aggiungo anche alla stream completo di byte della risposta
		response.getResponseBytes().write(buffer);
		response.getResponseBytes().flush();
	}
	
	/**
	 * This method adds a buffer in body
	 * @param buffer the buffer that you want to add
	 */
	public void addBytes(StringBuffer buffer){
		byte[]bufferBytes = buffer.toString().getBytes();
		// aggiungo anche alla stream completo di byte della risposta
		try {
			response.getResponseBytes().write(bufferBytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			response.getResponseBytes().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * This method communicates that the body is ready.
	 * Alert : this method closes the output stream
	 */
	public void ready(){
		try {
			response.getResponseBytes().flush();
			response.getResponseBytes().close();
			this.ready = true ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @return true if the body is ready
	 */
	public boolean isReady() {
		return this.ready;
	}
}
