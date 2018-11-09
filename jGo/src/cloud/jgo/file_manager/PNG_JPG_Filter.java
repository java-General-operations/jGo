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
package cloud.jgo.file_manager;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class PNG_JPG_Filter extends FileFilter{

	@Override
	public boolean accept(File f) {
		boolean corrected = false ; 
		if(f.getName().toLowerCase().endsWith("png")){
			corrected = true ;
		}
		else if(f.getName().toLowerCase().endsWith("jpg")) {
			corrected = true ;
		}
		return corrected ;
				
	}

	@Override
	public String getDescription() {
		
		return "Images(.png,.jpg)";
	}

}
