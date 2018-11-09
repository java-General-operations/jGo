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
/**
 * 
 * @author Martire91<br>
 * This class represents a simple http response code
 *
 */
public class ResponseCode{
//	200 OK. Il server ha fornito correttamente il contenuto nella sezione body.
//	301 Moved Permanently. La risorsa che abbiamo richiesto non è raggiungibile perché è stata spostata in modo permanente.
//	302 Found. La risorsa è raggiungibile con un altro URI indicato nel header Location. Di norma i browser eseguono la richiesta all'URI indicato in modo automatico senza interazione dell'utente.
//	400 Bad Request. La risorsa richiesta non è comprensibile al server.
//	404 Not Found. La risorsa richiesta non è stata trovata e non se ne conosce l'ubicazione. Di solito avviene quando l'URI è stato indicato in modo incorretto, oppure è stato rimosso il contenuto dal server.
//	500 Internal Server Error. Il server non è in grado di rispondere alla richiesta per un suo problema interno.
//	505 HTTP Version Not Supported. La versione di http non è supportata.
//	
	private int code ;
	private String mex ;
	private String explanation ;
	private static ResponseCode instance = null ;
	/**
	 * This method returns the response code
	 * @return response code
	 */
	public int getCode() {
		return code;
	}
	/**
	 * This method returns the response message
	 * @return response message
	 */ 
	public String getMex() {
		return mex;
	}
	/**
	 * This method returns the response explanation
	 * @return response explanation
	 */
	public String getExplanation() {
		return explanation;
	}
	/**
	 * This method sets the response explanation
	 * @param explanation the explanation
	 */
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getCode()+" "+this.getMex();
	}
	
	private ResponseCode(int code,String mex) {
		// TODO Auto-generated constructor stub
		this.code = code ;
		this.mex = mex ;
	}
	
	private ResponseCode(int code,String mex,String explanation) {
		// TODO Auto-generated constructor stub
	this.code = code ;
	this.mex = mex ;
	this.explanation = explanation ;
	}
	public final static ResponseCode RESPONSE_CODE_OK = new ResponseCode(200,"OK");
	public final static ResponseCode RESPONSE_MOVED= new ResponseCode(301,"Moved Permanently");
	public final static ResponseCode RESPONSE_FOUND= new ResponseCode(302,"Found");
	public final static ResponseCode RESPONSE_BAD_REQUEST= new ResponseCode(400,"Bad Request");
	public final static ResponseCode RESOURCE_NOT_FOUND= new ResponseCode(404,"Not Found");
	public final static ResponseCode RESPONSE_INTERNAL_SERVER_ERROR= new ResponseCode(500,"Internal Server Error");
	public final static ResponseCode RESPONSE_NOT_SUPPORTED_VERSION= new ResponseCode(505,"HTTP Version Not Supported");
	
	private static final ResponseCode[]responseCodes = {RESPONSE_CODE_OK,RESPONSE_MOVED,RESPONSE_FOUND,
			RESPONSE_BAD_REQUEST,RESPONSE_INTERNAL_SERVER_ERROR,RESPONSE_NOT_SUPPORTED_VERSION};
	
	/**
	 * This method returns the desired response code
	 * @param code int response code
	 * @return response code
	 */
	public static ResponseCode getResponseCode(int code){
		ResponseCode instance = null ;
		for (ResponseCode responseCode : responseCodes) {
			 ResponseCode codeRes = responseCode ;
			 if(codeRes.getCode()==code){
				 instance = codeRes ;
				 // esco dal ciclo
				 break ;
			 }
		}
		return instance ;
	}
	
	/**
	 * This method returns the desired response code
	 * @param mex the response mex
	 * @return the response code
	 */
	public static ResponseCode getResponseCode(String mex){
		ResponseCode instance = null ;
		for (ResponseCode responseCode : responseCodes) {
			 ResponseCode codeRes = responseCode ;
			 if(codeRes.getMex().toLowerCase().equals(mex.toLowerCase())){
				 instance = codeRes ;
				 // esco dal ciclo
				 break ;
			 }
		}
		return instance ;
	}
	
	/**
	 * This method returns a new response code
	 * @param code the response code
	 * @param mex the response mex
	 * @return the response code
	 */
	public static ResponseCode getNewCode(final int code,final String mex){
		if (instance == null) {
			instance = new ResponseCode(code, mex);
		}
		return instance ;
	}
	
}
