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
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import cloud.jgo.SMTPHosts.SMTPEntry;
/**
 * @author Martire91
 * @see j£#_A
 * @see j£#_S
 * @see j£#_W
 * @version 1.0.0
   <!--<link rel='styleSheet' href='https://www.jgo.cloud/docStyle.css'> -->
    <!--Author : *** Marco Martire *** -->  
   <h1 style='color: #282828;'>jGo<span style='color: green;'>.cloud</span>/<strong><a href='https://www.jgo.cloud/boosted'>Boosted</a></strong></h1>
   <img id='logo'src='https://www.jgo.cloud/imgs/logo.png' alt='logo jgo' width='50px' height='50px'><br>
   <strong>Description :</strong><br> This class facilitates all the difficult tasks
   For example,In a code line, you take a screenshot.<br>
   JGO is very powerful and it follows the jquery policy :<br>
   <em><u>(Write a little and get a lot)</u></em><br>
   <strong>J</strong> - <em>Java</em><br>
   <strong>G</strong> - <em>General</em><br>
   <strong>O</strong> - <em>Operations</em><br><br>
   <em style="color: red;">Read :</em><br>
   <strong>
   This class is an extension of jGo, however it is advisable to use<br>
   it only for Maven projects as it groups all the methods that require<br>
   third-party libraries, such as javamail.
   </strong>
   <br><br>
   <strong>1 Example</strong>  -  <em class='explanations'>Prints "Welcome in JGO", executes a screenshot with a final function and finally emits an acoustic signal :</em> : <br>
    public static void main(<em class='type'>String</em>[]args){<br>
    <em class='type'>£</em>._O("Welcome in JGO").<em class='method'>screenshot</em>(new <em class='type'>£Func</em>() {<br>
    <strong style='color:darkgray'>@Override</strong><br>
    public Object <em style='#303030'>function</em>(<em class='type'>Object</em> e) {<br>
    <em class='type'>£</em>.<em class='method'>alert</em>("Hello World");<br>
    return null ;<br>
    }}).<em class='method'>beep</em>().<em class='method'>beep</em>();<br>
    &nbsp;}
    <br><br>
    <strong>2 Example</strong>  -  <em class='explanations'>Emits an acoustic signal and opens the windows cmd for 3 times and opens windows notepad :</em><br>
    <em class='type'>£</em>.<em class='method'>beep</em>().<em class='method'>openCmd</em>(3).<em class='method'>openNotepad</em>();
    <br><br>
    <strong>3 Example</strong>  -  <em class='explanations'>Slide panel :</em><br>
    <em class='type'>£</em>._<span style='color: #006200;'>S</span>.<em class='method'>slide</em>(panel,Effect.<span style='color: #006200;'>VERTICAL</span>,Effect.<span style='color: #006200;'>FAST</span>);
    <br><br>
    <strong>4 Example</strong>  -  <em class='explanations'>Moves two times the frame :</em><br>
    <em class='type'>£</em>._<em style='color:#282828;'>S</em>.<em class='method'>moveJFrame</em>(frm, 0, 0,<span style='color: #006200;'>Effect</span>.SLOW).<em class='method'>moveJFrame</em>(frm, 300, 300, Effect.<span style='color: #006200;'>FAST</span>);
 */
public final class j£ extends cloud.jgo.£{
	static{
		instance = getInstance();
	}
	public static j£ getInstance(){
		if (instance==null) {
			instance = new j£();
		}
		else{
			if (instance instanceof £) {
				instance = new j£();
			}
		}
		return (j£) instance ;
	}
	// Methods for email :version 1.0.5
	/**
	 * This method sends a simple email.
	 * There is no authentication for this method,
	 * and you can not set a contentType nor attach a document.
	 * @param recipient recipient email
	 * @param sender sender email
	 * @param subject email subject
	 * @param text email text
	 * @param smtpHost smtp host
	 * @param smtpPort smtp port
	 * @param successLog the log that is printed in case the email is sent correctly
	 * @return the jGo access point
	 */
	public static j£ sendSimpleEmail(String recipient,String sender,String subject,String text,String smtpHost,int smtpPort,String successLog){
		j£ inst = null ;
		Properties props = new Properties();
		props.put("mail.smtp.host",smtpHost);
		props.put("mail.smtp.port",smtpPort);
		Session session = Session.getDefaultInstance(props);
		// creo il messaggio impostando la sessione al suo interno 
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(sender));
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(recipient));
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// imposto l'oggetto
		try {
			message.setSubject(subject);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			message.setText(text);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// invio
		try {
			Transport.send(message);
			if (successLog!=null) {
				System.out.println(successLog);
			}
			inst = (j£) instance;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inst ;
	}
	/**
	 * This method sends a simple email.
	 * There is no authentication for this method,
	 * and you can not set a contentType nor attach a document.
	 * @param recipient recipient email
	 * @param sender sender email
	 * @param subject email subject
	 * @param text email test
	 * @param host smtp host
	 * @param successLog the log that is printed in case the email is sent correctly
	 * @return the jGo access point
	 */
	public static j£ sendSimpleEmail(String recipient,String sender,String subject,String text,SMTPEntry host,String successLog){
		j£ inst = null ;
		Properties props = new Properties();
		props.put("mail.smtp.host",host.getHost());
		props.put("mail.smtp.port",host.getPort());
		Session session = Session.getDefaultInstance(props);
		// creo il messaggio impostando la sessione al suo interno 
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(sender));
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(recipient));
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// imposto l'oggetto
		try {
			message.setSubject(subject);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			message.setText(text);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// invio
		try {
			Transport.send(message);
			if (successLog!=null) {
				System.out.println(successLog);
			}
			inst = (j£) instance;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (j£) instance ;
	}
	/**
	 * This method sends a simple email.
	 * There is no authentication for this method,
	 * and you can not set a contentType nor attach a document.
	 * @param recipient recipient email
	 * @param sender sender email
	 * @param subject email subject
	 * @param text email test
	 * @param hostType email host type : example : gmail
	 * @param successLog the log that is printed in case the email is sent correctly
	 * @return the jGo access point
	 */
	public static j£ sendSimpleEmail(String recipient,String sender,String subject,String text,String hostType,String successLog){
		j£ inst = null ;
		Properties props = new Properties();
		SMTPEntry entry = j£.SMTP_HOSTS.get(hostType);
		if (entry!=null) {
			props.put("mail.smtp.host",entry.getHost());
			props.put("mail.smtp.port",entry.getPort());
			Session session = Session.getDefaultInstance(props);
			// creo il messaggio impostando la sessione al suo interno 
			MimeMessage message = new MimeMessage(session);
			try {
				message.setFrom(new InternetAddress(sender));
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				message.addRecipient(Message.RecipientType.TO,new InternetAddress(recipient));
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// imposto l'oggetto
			try {
				message.setSubject(subject);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				message.setText(text);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// invio
			try {
				Transport.send(message);
				if (successLog!=null) {
					System.out.println(successLog);
				}
				inst = (j£) instance;
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return inst ;
	}	
}
