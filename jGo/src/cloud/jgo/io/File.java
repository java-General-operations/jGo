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
package cloud.jgo.io;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileSystemView;
import cloud.jgo.£;

/**
 * 
 * @author Martire<br>
 * This class is just an extension of the java.io.File class .
 * Adds just a few more features
 *
 */
public class File extends java.io.File{

	
	// okok ottimo metodo poi però devo continuare dando altre funzionalità a questa classe
	// e gestendola in un modo più professionale
	
	public byte[] getBytes() throws IOException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		FileInputStream fis = new FileInputStream(File.this);
		BufferedInputStream in = new BufferedInputStream(fis);
		byte[]buffer = new byte[1024];
		int leggi ;
		while((leggi = in.read(buffer, 0, buffer.length))>-1){

			baos.write(buffer, 0, leggi);
		
		}
		baos.flush();
		baos.close();
		in.close();
		return baos.toByteArray();
	}
	public Icon getIcon(){
		
		if(exists()){
			return FileSystemView.getFileSystemView().getSystemIcon(File.this);
		}
		else{
			return null ;
		}
		
	}
      public ImageIcon getImageIcon(){
		
		if(exists()){
			Icon icon = FileSystemView.getFileSystemView().getSystemIcon(File.this);
			
			ImageIcon image = (ImageIcon)icon ;
			
			return image ;
		}
		else{
			return null ;
		}
		
	}
	public File(java.io.File parent, String child) {
		super(parent, child);
		// TODO Auto-generated constructor stub
	}

	public File(String parent, String child) {
		super(parent, child);
		// TODO Auto-generated constructor stub
	}

	public File(String pathname) {
		super(pathname);
		// TODO Auto-generated constructor stub
	}

	public File(URI uri) {
		super(uri);
		// TODO Auto-generated constructor stub
	}
}
