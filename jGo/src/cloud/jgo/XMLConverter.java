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

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import cloud.jgo.io.File;

public final class XMLConverter {
	public static cloud.jgo.io.File convertFromObjectInXML(Class type, String fileName, Object obj)
			throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(type);
		Marshaller jaxbMarshaller = context.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		File file = new File(fileName);
		jaxbMarshaller.marshal(obj, file);
		return file;
	}

	public static Object convertFromXMLInObject(cloud.jgo.io.File file, Class type) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(type);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Object customer = (Object) jaxbUnmarshaller.unmarshal(file);
		return customer;
	}
}
