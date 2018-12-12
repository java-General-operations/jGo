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
package cloud.jgo.net.tcp.http.headers;

import javax.activation.MimeType;

public class MimeHeader extends DefaultHeader {

	// questi sono ammessi solo i valori
	// CONTET TYPE e ACCEPT
	// da comunicare all'utente

	private Type type = null;

	public MimeHeader(MimeType mime) {
		setName(Header.Type.CONTENT_TYPE.getType());
		setValue(mime);
	}

	@Override
	public Type getType() {
		/*
		 * 
		 * JGO Auto-generated method stub Author : £ wasp91 £ Date 19 feb 2018 qui
		 * restituiamo il suo tipo specifico ,risolvendo cosi quell'errore
		 * 
		 */
		return this.type;
	}

	@Override
	public MimeType getValue() {
		/*
		 * 
		 * JGO Auto-generated method stub Author : £ wasp91 £ Date 17 feb 2018
		 * 
		 */
		return (MimeType) this.value;
	}

	@Override
	public void setValue(Object value) {
		// qui diamo per scontato che si tratta di un Mime
		// quindi prendiamo il toString
		this.value = value;
	}

}
