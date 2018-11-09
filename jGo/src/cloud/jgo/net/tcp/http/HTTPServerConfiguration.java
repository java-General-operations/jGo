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
import cloud.jgo.net.Server;
import cloud.jgo.net.handlers.Handler;
import cloud.jgo.net.tcp.NotSupportedModelException;
import cloud.jgo.net.tcp.TCPServerConfiguration;
import cloud.jgo.net.tcp.TCPServerTypes;
/**
 * 
 * @author Martire91<br>
 * This class is the http server configuration,
 * and is a subclass of {@link TCPServerConfiguration}
 *
 */
public class HTTPServerConfiguration extends TCPServerConfiguration{

	public final static String KEY_ROOT = "jgo.net.server.root";
	
	// qui c'è un bel lavoro da fare se vogliamo estendere correttamente
	// la TCPServerConfiguration
	private String rootFolder = null ;
	
	/**
	 * This method returns the root folder
	 * @return the root folder
	 */
	public String getRootFolder() {
		return rootFolder;
	}

	static {
		SERVER_TYPE = TCPServerTypes.TYPE_HTTP ;
	}

	/**
	 * This method sets the root folder
	 * @param rootFolder the root folder
	 */
	public void setRootFolder(String rootFolder){
		this.rootFolder = rootFolder ;
		
		if(!this.getProps().containsKey(KEY_ROOT)){
			this.getProps().put(KEY_ROOT, this.rootFolder.replace("\\", "/"));
			counterSettings++ ;
		}
		else{
			this.getProps().replace(KEY_ROOT, this.rootFolder);
		}
	}
	
	
	@Override
	public void setModel(Handler handler) {
		if (handler instanceof HTTPHandlerConnection) {
			this.model = handler ;
		}
		else{
			this.model = null ;
			try {
				throw new NotSupportedModelException();
			} catch (NotSupportedModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(handler!=null){
			if(!this.getProps().containsKey(KEY_SERVER_MODEL)){
				this.getProps().put(KEY_SERVER_MODEL,this.model.getClass().getSimpleName());
				this.counterSettings++ ;
			}
			else{
				this.getProps().replace(KEY_ROOT, this.rootFolder);
			}
		}
	}
	
	@Override
	public Handler getModel() {
		// TODO Auto-generated method stub
		return super.getModel();
	}
	
	
}
