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
// version 1.0.5
package cloud.jgo;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
/**
 * 
 * @author Martire91
 * This class is a collection of smtp hosts
 * Facilitates the use of jGo methods to send emails.
 */
public class SMTPHosts extends Hashtable<String,SMTPHosts.SMTPEntry>{
	private static SMTPHosts instance = null ;
	// mi costruisco un costruttore privato, cosi non faccio creare instanze di questa classe
	private SMTPHosts() {
		// gmail 
		put("gmail",new SMTPEntry("smtp.gmail.com",465));
		// gmail+StartTLS
		put("gmail/starttls",new SMTPEntry("smtp.gmail.com",587));
		// outlook
		put("outlook",new SMTPEntry("smtp.live.com",587));
		// Yahoo
		put("yahoo",new SMTPEntry("smtp.mail.yahoo.com",465));
		// Hotmail
		put("yahoo",new SMTPEntry("smtp.live.com",465));
		// Net@ddress by USA.NET
		put("net",new SMTPEntry("smtp.postoffice.net",465));
		// Alice
		put("alice",new SMTPEntry("out.alice.it",465)); // OK for alice email
		// Alice+StartTLS
		put("alice/starttls",new SMTPEntry("out.alice.it",587));
		// Alice+StartTLS - 2
		put("alice/starttls-2",new SMTPEntry("out.alice.it",25));
		// aruba
		put("aruba",new SMTPEntry("smtp.aruba.it",465));
		// aruba+StartTLS
		put("aruba/starttls",new SMTPEntry("smtp.aruba.it",587));
		// aruba+StartTLS - 2 
		put("aruba/starttls-2",new SMTPEntry("smtp.aruba.it",25));
		// Fastweb
		put("fastweb",new SMTPEntry("smtp.fastwebnet.it",465));
		// Fastweb+StartTLS
		put("fastweb/starttls",new SMTPEntry("smtp.fastwebnet.it",587));
		// Fastweb+StartTLS - 2
		put("fastweb/starttls-2",new SMTPEntry("smtp.fastwebnet.it",25));
		// Hotpop
		put("hotpop",new SMTPEntry("smtp.hotpop.com",465));
		// Hotpop+StartTLS
		put("hotpop/starttls",new SMTPEntry("smtp.hotpop.com",587));
		// Libero
		put("libero",new SMTPEntry("mail.libero.it",465));
		// Libero+StartTLS
		put("libero/starttls",new SMTPEntry("mail.libero.it",587));
		// tim
		put("tim",new SMTPEntry("box.posta.tim.it",465));
		// tim2
		put("tim2",new SMTPEntry("smtp.tim.it",465));
		// tim3
		put("tim3",new SMTPEntry("smtp.tim.it",25));
		// tim+StartTLS
		put("tim/starttls",new SMTPEntry("box.posta.tim.it",587));
		// tin
		put("tin",new SMTPEntry("smtp.tin.it",465));
		// tin+StartTLS
		put("tin/starttls",new SMTPEntry("smtp.tin.it",587));
		// tin+alice
		put("tin/alice",new SMTPEntry("mail.tin.it",465));
		// tin+alice+StartTLS
		put("tin/alice/starttls",new SMTPEntry("mail.tin.it",587));
		// tiscali
		put("tiscali",new SMTPEntry("smtp.tiscali.it",465));
		// tiscali+StartTLS
		put("tiscali",new SMTPEntry("smtp.tiscali.it",587));
		// Virgilio
		put("virgilio",new SMTPEntry("out.virgilio.it",465));
		// Virgilio 2
		put("virgilio2",new SMTPEntry("smtp.virgilio.it",465));
		// Virgilio+StartTLS
		put("virgilio/starttls",new SMTPEntry("out.virgilio.it",587));
		// Virgilio+StartTLS
		put("virgilio2/starttls",new SMTPEntry("smtp.virgilio.it",587));
		// vodafone
		put("vodafone",new SMTPEntry("smtp.net.vodafone.it",465));
		// vodafone+StartTLS
		put("vodafone/starttls",new SMTPEntry("smtp.net.vodafone.it",587));
	}
	public static SMTPHosts getInstance(){
		if (instance==null) {
			instance = new SMTPHosts();
		}
		return instance;
	}
	/**
	 * @author Martire91
	 * This class represents a simple entry smtp.
	 */
	public static class SMTPEntry{
		private String host;
		private Integer port;
		public SMTPEntry(String smtpHost,Integer smtpPort) {
			// TODO Auto-generated constructor stub
			this.host = smtpHost;
			this.port = smtpPort;
		}
		public SMTPEntry() {
			// TODO Auto-generated constructor stub
			this.host = null ;
			this.port = null;
		}
		public String getHost() {
			return host;
		}
		public void setHost(String host) {
			this.host = host;
		}
		public Integer getPort() {
			return port;
		}
		public void setPort(Integer port) {
			this.port = port;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.host+":"+this.port;
		}
	}
}
