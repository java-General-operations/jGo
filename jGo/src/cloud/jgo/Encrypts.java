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
package cloud.jgo;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
@Deprecated
public class Encrypts {

	public final static String TEXT_KEY_DEFAULT = "@§01@1@#f%r%a&x@";
	public final static String ALGORITHM = "AES";
	private String text ;
	private ArrayList<String> textFile ;
	private String encryptedText ;
	private String encryptedTextFile;
	private Key key =null;
	private java.io.File fileCrypto ;
	private String nameFileCryptato ;
	
	public Encrypts(String keyText,String £_id) throws IOException {
		if(£_id.startsWith("jo_")){

			if(£_id.length()==6 || £_id.length()==7){
				/*
				 * Qui verifica se c'è la chiave è diversa da null
				 */
				this.key = new SecretKeySpec(keyText.getBytes(),ALGORITHM);
			}
			else{
				System.err.println("id session is not correct ###");
			}
		}
		else{
			System.err.println("id session is not correct ###");
		}
	}

	
	public Key generatesKey() throws NoSuchAlgorithmException{
		
		KeyGenerator generatorKey = KeyGenerator.getInstance("AES");
		generatorKey.init(128);
		return this.key =generatorKey.generateKey();
	}
	
	
	
	
	public String crypta(String text,Key key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		this.key = key;
		if(this.key!=null){
			Cipher cifrario = Cipher.getInstance("AES");
			cifrario.init(Cipher.ENCRYPT_MODE,this.key);
		   byte[]byteCrypt= cifrario.doFinal(text.getBytes());
		   return encryptedText=Base64.getEncoder().encodeToString(byteCrypt);
		   
		}
		else{
			return null ;
		}
		
	}
	
	public String decrypt(String cryptText,byte[]byteKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		
		Key key = new SecretKeySpec(byteKey,"AES");
		Cipher c = Cipher.getInstance("AES");
		c.init(Cipher.DECRYPT_MODE,key);
		// ottengo i  byte criptati 
		byte[]byteCrypto = Base64.getDecoder().decode(cryptText);
		// ottengo i byte decriptati 
		byte []byteDecriptati = c.doFinal(byteCrypto);
		return new String(byteDecriptati);	
	}
	
	
	
	public String EncryptImage(BufferedImage image) throws IOException{
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(image, "PNG",out);
		byte[]byteFile = out.toByteArray();
		
		// crypto i byte dell'immagine 
		
		String codeCrypto = Base64.getEncoder().encodeToString(byteFile);
		
		return codeCrypto ;
		
		
	}
	
	public BufferedImage DecryptImage(String code) throws IOException{
		
		
		byte[]byteImage = Base64.getDecoder().decode(code);
		
		BufferedImage image = ImageIO.read(new ByteArrayInputStream(byteImage));
		
		return image ;
		
	}
	
	
	public boolean EncryptImage(String path){
		return false;
		
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ArrayList<String> getTextFile() {
		return textFile;
	}

	public void setTextFile(ArrayList<String> textFile) {
		this.textFile = textFile;
	}

	public String getEncryptedText() {
		return encryptedText;
	}

	public void setEncryptedText(String encryptedText) {
		this.encryptedText = encryptedText;
	}

	public String getEncryptedTextFile() {
		return encryptedTextFile;
	}

	public void setEncryptedTextFile(String encryptedTextFile) {
		this.encryptedTextFile = encryptedTextFile;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}
	
	public File decryptaFileExecutable(File file) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		
		/*
		 * Ci sono riuscito 
		 * In teoria questo metodo permette di DEcodificare quindi decriptare un file
		 * eseguibile
		 */
		Key key = new SecretKeySpec(this.key.getEncoded(),"AES");
		Cipher c = Cipher.getInstance("AES");
		c.init(Cipher.DECRYPT_MODE,key);
		// ottengo i byte criptati 
		FileInputStream fileInput = new FileInputStream(file);
		byte[]byteFile = new byte[fileInput.available()];
		fileInput.read(byteFile);
		fileInput.close();
		String nameFile = file.getPath();
		// elimino il file 
		file.delete();
		// decripto i byte 
		byte[]byteCrypto = Base64.getDecoder().decode(byteFile);
		byte[]byteDecript = c.doFinal(byteCrypto);
		FileOutputStream fileOut = new FileOutputStream(nameFile);
		fileOut.write(byteDecript);
		fileOut.flush();
		fileOut.close();
		return new File(new String(nameFile));
	}
	
	
public cloud.jgo.io.File decryptaFileExecutable(cloud.jgo.io.File file) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		
		/*
		 * Ci sono riuscito 
		 * In teoria questo metodo permette di DEcodificare quindi decriptare un file
		 * eseguibile
		 */
		Key key = new SecretKeySpec(this.key.getEncoded(),"AES");
		Cipher c = Cipher.getInstance("AES");
		c.init(Cipher.DECRYPT_MODE,key);
		// ottengo i byte criptati 
		FileInputStream fileInput = new FileInputStream(file);
		byte[]byteFile = new byte[fileInput.available()];
		fileInput.read(byteFile);
		fileInput.close();
		String nameFile = file.getPath();
		// elimino il file 
		file.delete();
		// decripto i byte 
		byte[]byteCrypto = Base64.getDecoder().decode(byteFile);
		byte[]byteDecript = c.doFinal(byteCrypto);
		FileOutputStream fileOut = new FileOutputStream(nameFile);
		fileOut.write(byteDecript);
		fileOut.flush();
		fileOut.close();
		return new cloud.jgo.io.File(new String(nameFile));
	}
	
	public void cryptaFileExecutable(File file) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		
		/*
		 * Ci sono riuscito 
		 * In teoria questo metodo permette di codificare quindi criptare un file
		 * eseguibile
		 */
		
		String pathFile = file.getPath();
		String nameFile = file.getName();
		if(this.key !=null){
			// va bene
			
			WorkWithByte work 
			= new WorkWithByte();
			byte[]byteFile=work.convertFileInByte(file);
			
			// ho ottenuto i byte ora li crypto 
			
			Cipher cifrario = Cipher.getInstance("AES");
			cifrario.init(Cipher.ENCRYPT_MODE,this.key);
			// ottento i byte 
			byte[]biteCryptati=cifrario.doFinal(byteFile);
			byte[]codeCrypto = Base64.getEncoder().encode(biteCryptati);
			
			/*
			 * Elimino il file 
			 */
			file.delete();
			File fileCryptato = new File(pathFile);
			fileCryptato.createNewFile();
			
			// scrivo il codice sul file
			// test di stampa 
			
			FileOutputStream fileOut = new FileOutputStream(fileCryptato);
			fileOut.write(codeCrypto);
			fileOut.flush();
			fileOut.close();
			
		
		}
		else{
			System.out.println("Negative Key #321#");
		}
		
		
		
		
		
	}
	
public void cryptaFileExecutable(cloud.jgo.io.File file) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		
		/*
		 * Ci sono riuscito 
		 * In teoria questo metodo permette di codificare quindi criptare un file
		 * eseguibile
		 */
		
		String pathFile = file.getPath();
		String nameFile = file.getName();
		if(this.key !=null){
			// va bene
			
			WorkWithByte work 
			= new WorkWithByte();
			byte[]byteFile=work.convertFileInByte(file);
			
			// ho ottenuto i byte ora li crypto 
			
			Cipher cifrario = Cipher.getInstance("AES");
			cifrario.init(Cipher.ENCRYPT_MODE,this.key);
			// ottento i byte 
			byte[]biteCryptati=cifrario.doFinal(byteFile);
			byte[]codeCrypto = Base64.getEncoder().encode(biteCryptati);
			
			/*
			 * Elimino il file 
			 */
			file.delete();
			File fileCryptato = new File(pathFile);
			fileCryptato.createNewFile();
			
			// scrivo il codice sul file
			// test di stampa 
			
			FileOutputStream fileOut = new FileOutputStream(fileCryptato);
			fileOut.write(codeCrypto);
			fileOut.flush();
			fileOut.close();
			
		
		}
		else{
			System.out.println("Negative Key #321#");
		}
		
		
		
		
		
	}

	
	public java.io.File getFileCrypto() {
		return fileCrypto;
	}

	public void setFileCrypto(java.io.File fileCrypto) {
		this.fileCrypto = fileCrypto;
	}

	public String getNameFileCryptato() {
		return nameFileCryptato;
	}

	public void setNameFileCryptato(String nameFileCryptato) {
		this.nameFileCryptato = nameFileCryptato;
	}
	
	
	
}
