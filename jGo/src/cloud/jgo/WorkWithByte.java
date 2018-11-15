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
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
@Deprecated
public class WorkWithByte {
	/*
	 * Domani testerò ogni singolo metodo 
	 * perchè per quanto insignificante può sembrare
	 * mi risparmierà un sacco di lavoro
	 * quando dovrò fare sempre quelle solite operazioni
	 * specialmente prendere i byte di un file
	 */
	
	/*
	 * Fields della classe 
	 */
	private byte[]byteFile ;
	private File file ;
	private ImageIcon icon ;
	private BufferedImage image ;
	private String pathAndNameFile ;
	
	public String getNameFile() {
		return pathAndNameFile;
	}
	public void setNameFile(String nameFile) {
		this.pathAndNameFile = nameFile;
	}

	public byte[] getByteFile() {
		return byteFile;
	}
	public void setByteFile(byte[] byteFile) {
		this.byteFile = byteFile;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public ImageIcon getIcon() {
		return icon;
	}
	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	
	public BufferedImage convertByteInImage(byte[]byteImage) throws IOException{
		
		InputStream in = new ByteArrayInputStream(byteImage);
		
	return ImageIO.read(in);
		
	}
	
	public byte[] convertFileInByte(File file) throws IOException{
		
		if(file.exists()){
			this.pathAndNameFile=file.getPath();
			FileInputStream fileInput = new FileInputStream(file);
			byte[]byteFile = new byte[fileInput.available()];
			fileInput.read(byteFile);
	        fileInput.close();
	        return byteFile;
		}
		else{
		return null ;	
		}	
	}
	
	public File convertByteInFile(byte[]byteFile,String nameFileAndPath) throws IOException{
		
		File file = new File(nameFileAndPath);
		if(file.exists()){
			JOptionPane.showMessageDialog(null,"Il file già esiste");
			return null ;
		}
		else {
			FileOutputStream fileOutputStream =new FileOutputStream(file);
			fileOutputStream.write(byteFile);
			fileOutputStream.flush();
			fileOutputStream.close();
			return file ;
		}
	}
	
	
	public byte[] convertImageInByte(BufferedImage image) throws IOException{
		
		ByteArrayOutputStream outByte = new ByteArrayOutputStream();
		ImageIO.write(image,"PNG",outByte);
		return outByte.toByteArray();
		
	}
	
	
	public ImageIcon convertByteInImageIcon(byte[]byteIcon){
		
		return new ImageIcon(byteIcon);
	
	}
	
	
	public String convertByteInString(byte[]byteString){
		
		return new String(byteString);
		
	}
	
public static double VerificationNumberOfBytes(File file) throws IOException{
		
		FileInputStream fileIn = new FileInputStream(file);
		byte[]byteFile = new byte[fileIn.available()];
		fileIn.read(byteFile);
		fileIn.close();
		int numeroByte = byteFile.length;
		return (double)numeroByte;
	}
	

public static double VerificationNumberOfBytes(String byteString) throws IOException{
	
	
	byte[]byteFile = byteString.getBytes();
	int numeroByte = byteFile.length;
	return (double)numeroByte;
}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
