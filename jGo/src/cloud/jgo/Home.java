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
 * https://www.jgo.com/terms/LICENSE.txt
 *
 * To collaborate on this project, you need to do it from the software site.
 * 
 */
package cloud.jgo;

import java.awt.HeadlessException;

public class Home {
	
	public static £ home = null ;

	 static{
		
		
		try {
			home=  £.instance;
		} catch (HeadlessException e) {
			/*
			 
			JGO Auto-generated catch block
			Author : £ wasp91 £
			Date 06 mar 2018
			
			*/
			e.printStackTrace();
		}

	}

}
