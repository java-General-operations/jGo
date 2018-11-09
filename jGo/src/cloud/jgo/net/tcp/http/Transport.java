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
import java.net.SocketException;
/**
 * 
 * @author Martire91<br>
 * This class takes care of transferring the response
 */
public abstract class Transport {

	/**
	 * This method transfers the response
	 * @param response the response
	 */
	public static void trasfer(HTTPResponse response){
		if(response.getBody().isReady() && response.getResponseBuffer()!=null){
			try {
				response.output().flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				response.output().write(response.getResponseBuffer());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				response.output().flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * This method transfers the responses
	 * @param responses the responses
	 * @throws IOException 1 exception
	 */
	public static void trasfer(HTTPResponse...responses) throws IOException{
		for (HTTPResponse response : responses) {
			if(response.getBody().isReady() && response.getResponseBuffer()!=null){
				response.output().flush();
				try {
			    	
					response.output().write(response.getResponseBuffer());
					
				} catch (SocketException e) {
					System.err.println("Don't possible to write to socket #");
				}
				response.output().flush();
				response.output().close();
			}
		}
	}
	
	
	
	
	
}
