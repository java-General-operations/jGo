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
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.gson.Gson;

import cloud.jgo.SMTPHosts.SMTPEntry;
import cloud.jgo.jjdom.JjDom;
import cloud.jgo.net.Server;
import cloud.jgo.net.tcp.TCPServer;
import cloud.jgo.net.tcp.http.HTTPServer;
import cloud.jgo.net.tcp.http.jor.JOR;
import cloud.jgo.net.tcp.http.jor.JORServer;
/**
 * @author Martire91
 * @see £#_A
 * @see £#_S
 * @see £#_W
 * @version 1.0.0
   <!--<link rel='styleSheet' href='https://www.jgo.cloud/docStyle.css'> --><br>
    <!--Author : *** Marco Martire *** -->  
     <img id='logo'src='https://www.jgo.cloud/wp-content/uploads/2018/11/jgo2.png' alt='logo jgo' style='float: left;margin-right:15px;'><br>
   <h1 style='color: #282828;'>jGo<strong style='color: green;'>.cloud</strong>/<a href='#'>Boosted</a></h1>
   <em>To be used as a <a href='https://maven.apache.org/'>Maven</a> dependency</em><br><br><br>
   <strong>Description :</strong><br> This class facilitates all the difficult tasks
   For example,In a code line, you take a screenshot, and do other general operations. 
   the calls to the methods can be concatenated, so that different operations can be performed in the same programmatic instruction.
   jGo was born to facilitate the life of the java programmer, besides it provides different technologies like {@link JjDom} and {@link cloud.jgo.io.jon.JON} for example.
   jGo also allows you to go to specific phases for specific programming +, for example if our application is a JFrame, we can find the general methods in the _S phase, object of the SwingUtils class
   Moreover jGo contains 3 phases for specific programming, for example if our application is a {@link JFrame}, we will find the general Swing methods in the {@link £#_S} phase, static constant of type {@link SwingUtils}.
   However the WebUtils and AndroidUtils phases are still empty. The goal is to create a jar package comprising a wide range of general methods available to the user, so as to facilitate the life of the programmer, increase the performance of the program, and not less important, save a lot of time in terms of writing of the code.
   Moreover jGo provides very powerful objects, for example it is possible to create very powerful servers, for the moment servers whose protocol must be tcp, or an extension like http.
   Study the following server creation packages well:<br>
   <ul>
   <li><a href='#'>cloud.jgo.net</a> - contains all important interfaces, including the {@link Server} interface</li>
   <li><a href='#'>cloud.jgo.net.tcp</a> - contains all the tcp classes needed to implement a TCP server - {@link TCPServer}
   <li><a href='#'>cloud.jgo.net.http</a> - contains all the http classes needed to implement a HTTP server - {@link HTTPServer}
   <li><a href='#'>cloud.jgo.net.http.jor</a> - contains all the classes necessary for the implementation of a server with JOR technology - {@link JORServer}
   </ul>
   Or we can implement a terminal thanks to the objects contained in the package <a href='#'>cloud.jgo.utils.command</a> and <a href='#'>cloud.jgo.utils.command.terminal</a>. 
   Then we have {@link JjDom}, a technology that allows us to create an HTML document and write <a href='https://jquery.com/'>jquery</a> instructions in java. Furthermore we can of course write the css code necessary for our document.
   To use jGo, read the <a href='https://www.jgo.cloud/cookbook'>cookbook</a> and consult the website at <a href='https://www.jgo.cloud/'>https://www.jgo.cloud/</a>. 
   <br>
   <em>Have fun !</em>
   <br><br>
   JGO is very powerful and it follows the <a href='https://jquery.com/'>jquery</a> policy :<br><br>
   <em><u><a href='https://medium.com/laboratoria-how-to/write-less-do-more-e049d0824f4'>(Write a little and get a lot)</a></u></em><br><br>
   <strong>j</strong> - <em>java</em><br><br>
   <strong>G</strong> - <em>General</em><br><br>
   <strong>o</strong> - <em>operations</em><br><br>
   <hr>
   <strong>1 Example -</strong>This instruction opens the webcam, takes a snapshot and closes the webcam :<br><br>
<div class="cm_source">
1 | <code class="cm_n_n_n_0">j&pound;.openWebcam().capture(</code><code class="cm_n_n_n_2A00FF">&quot;snapshot.png&quot;</code><code class="cm_n_n_n_0">).closeWebcam();</code>
</div>
<br>
<strong>2 Example - </strong>This instruction sends a simple email without authentication :<br><br>
<div class="cm_source">
1 | <code class="cm_n_n_n_0">j&pound;.sendSimpleEmail(</code><code class="cm_n_n_n_2A00FF">&quot;test@tim.it&quot;</code><code class="cm_n_n_n_0">,</code><code class="cm_n_n_n_2A00FF">&quot;staff@jgo.cloud&quot;</code><code class="cm_n_n_n_0">,</code><code class="cm_n_n_n_2A00FF">&quot;Test&quot;</code><code class="cm_n_n_n_0">,</code><code class="cm_n_n_n_2A00FF">&quot;Hello&nbsp;World&quot;</code><code class="cm_n_n_n_0">,</code><code class="cm_n_n_n_2A00FF">&quot;aruba&quot;</code><code class="cm_n_n_n_0">,</code><code class="cm_n_n_n_2A00FF">&quot;Email&nbsp;sent&nbsp;@&quot;</code><code class="cm_n_n_n_0">);</code>
</div>
<br>
   <strong>3 Example -</strong>This instruction prints in the console "hello world" and "hello world 2",<br>also increases and decreases twice a counter:<br><br>
<div class="cm_source">
<code class="cm_n_n_n_0">1 | &pound;._O(</code><code class="cm_n_n_n_2A00FF">&quot;Hello&nbsp;world&quot;</code><code class="cm_n_n_n_0">).increment().decrement()<br>
  2 |&nbsp;  ._O(</code><code class="cm_n_n_n_2A00FF">&quot;Hello&nbsp;World&nbsp;2&quot;</code><code class="cm_n_n_n_0">).increment().decrement();</code>
</div>
   <br>
   <strong>4 Example -</strong>
   Opens the terminal, opens the notepad and emits an acoustic signal,<br>also prints in the console "Hello world":<br><br>
<div class="cm_source">
1 | <code class="cm_n_n_n_0">&pound;.openTerminal().openNotepad().beep()._O(</code><code class="cm_n_n_n_2A00FF">&quot;Hello&nbsp;World&quot;</code><code class="cm_n_n_n_0">);</code>
</div>
<br>
 <strong>5 Example -</strong>This instruction creates a file, a folder,<br>and prints hello world using two alert windows:<br><br>
<div class="cm_source">
1 | <code class="cm_n_n_n_0">&pound;.createFile(</code><code class="cm_n_n_n_2A00FF">&quot;hello&nbsp;world&quot;</code><code class="cm_n_n_n_0">).md().alert(</code><code class="cm_n_n_n_2A00FF">&quot;Hello&quot;</code><code class="cm_n_n_n_0">).alert(</code><code class="cm_n_n_n_2A00FF">&quot;World&quot;</code><code class="cm_n_n_n_0">);</code>
</div>
<br>
<strong>6 Example (Iteration with jGo) - </strong>Simple use of the "each" method of jGo:<br><br>
<div class="cm_source">
1 &nbsp;| <code class="cm_n_n_n_0">String[]arr&nbsp;=&nbsp;{</code><code class="cm_n_n_n_2A00FF">&quot;Alan&quot;</code><code class="cm_n_n_n_0">,</code><code class="cm_n_n_n_2A00FF">&quot;Peter&quot;</code><code class="cm_n_n_n_0">,</code><code class="cm_n_n_n_2A00FF">&quot;Lucas&quot;</code><code class="cm_n_n_n_0">};<br>
  2 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;<br>
  3 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&pound;.each(arr,</code><code class="cm_b_n_n_7F0055">new</code><code class="cm_n_n_n_0">&nbsp;&pound;Func()&nbsp;{<br>
  4 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
  5 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;@Override<br>
  6 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="cm_b_n_n_7F0055">public</code><code class="cm_n_n_n_0">&nbsp;Object&nbsp;function(Object&nbsp;e)&nbsp;{<br>
  7 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
  8 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;System.out.println(</code><code class="cm_n_n_n_2A00FF">&quot;Name:&quot;</code><code class="cm_n_n_n_0">+e);<br>
  9 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
 10 |&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="cm_b_n_n_7F0055">return</code><code class="cm_n_n_n_0">&nbsp;</code><code class="cm_b_n_n_7F0055">true</code><code class="cm_n_n_n_0">&nbsp;;<br>
 11 |&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br>
 12 |&nbsp;&nbsp;&nbsp;&nbsp;});</code>
</div><br>
<strong>7 Example - </strong>Obviously more complex instructions can be combined <br>
Increment the counter twice, create a file, iterate the array using the "each" method,
at the end it queries on google, decreases the value of the counter, and takes its value:<br><br>
<div class="cm_source">
1 &nbsp;| <code class="cm_b_n_n_7F0055">int</code><code class="cm_n_n_n_0">&nbsp;counterValue&nbsp;=&nbsp;<br>
  2 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
  3 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&pound;.increment().increment().createFile(</code><code class="cm_n_n_n_2A00FF">&quot;test.json&quot;</code><code class="cm_n_n_n_0">)<br>
  4 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;.each(arr,</code><code class="cm_b_n_n_7F0055">new</code><code class="cm_n_n_n_0">&nbsp;&pound;Func()&nbsp;{<br>
  5 &nbsp;|
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
  6 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;@Override<br>
  7 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="cm_b_n_n_7F0055">public</code><code class="cm_n_n_n_0">&nbsp;Object&nbsp;function(Object&nbsp;e)&nbsp;{<br>
  8 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
  9 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&pound;._O(</code><code class="cm_n_n_n_2A00FF">&quot;Name&nbsp;:&quot;</code><code class="cm_n_n_n_0">+e);<br>
  10 |&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
  11 |&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="cm_b_n_n_7F0055">return</code><code class="cm_n_n_n_0">&nbsp;</code><code class="cm_b_n_n_7F0055">true</code><code class="cm_n_n_n_0">&nbsp;;<br>
  12 |&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br>
  13 |&nbsp;&nbsp;&nbsp;&nbsp;}).executeGoogleQuery(</code><code class="cm_n_n_n_2A00FF">&quot;jGo&quot;</code><code class="cm_n_n_n_0">).decrement().value();</code>
</div>
<br>
<strong>In jGo there are 3 phases :</strong><br><br>
<ul>
<li>{@link #_A} - {@link £.AndroidUtils} - (<code>cloud.jgo.£.AndroidUtils</code>) - <em style="color: #303030;">&nbsp;for <a href='https://it.wikipedia.org/wiki/Sviluppo_di_software_Android'>android</a> programming</em></li>
<li>{@link #_S} - {@link £.SwingUtils} - (<code>cloud.jgo.£.SwingUtils</code>) - <em style="color: #303030;">&nbsp;for <a href='https://it.wikipedia.org/wiki/Swing_(Java)'>swing</a> programming</em></li>
<li>{@link #_W} - {@link £.WebUtils} - (<code>cloud.jgo.£.WebUtils</code>) - <em style="color: #303030;">&nbsp;for <a href='https://it.wikipedia.org/wiki/World_Wide_Web'>web</a> programming</em></li>
</ul><br>
For example, let's see how to apply some swing methods :<br><br>
<div class="cm_source">
1 | <code class="cm_n_n_n_0">&pound;.<strong style='color: blue;'>_S</strong>.hide(jframe,Effect.<strong style='color: blue;'>SLOW</strong>).show(jframe,&nbsp;Effect.<strong style='color: blue;'>SLOW</strong>);</code>
</div>
<br>
We can associate a click event to the button :<br><br>
<div class="cm_source">
<code class="cm_n_n_n_0">1 &nbsp;| &pound;.<strong class='costants'>_S</strong>.click(button,</code><code class="cm_b_n_n_7F0055">new</code><code class="cm_n_n_n_0">&nbsp;&pound;Func()&nbsp;{<br>
2 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
3 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;@Override<br>
4 &nbsp;| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="cm_b_n_n_7F0055">public</code><code class="cm_n_n_n_0">&nbsp;Object&nbsp;function(Object&nbsp;e)&nbsp;{<br>
5 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
6 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
7 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="cm_b_n_n_7F0055">return</code><code class="cm_n_n_n_0">&nbsp;&pound;.alert(</code><code class="cm_n_n_n_2A00FF">&quot;Hello&nbsp;world&quot;</code><code class="cm_n_n_n_0">);<br>
8 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
9 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br>
10 |&nbsp;&nbsp;&nbsp;&nbsp;});</code>
</div><br>
<strong>In any Phase we find ourselves, we can return to the access point using the "home ()" method :</strong><br><br>
<div class="cm_source">
<code class="cm_n_n_n_0">1 | &pound;.<strong style='color: blue;'>_S</strong>.applyEffect(Effect.<strong style='color: blue;'>CRAZY</strong>,jframe,jframe2,jframe3)<br>
2 |&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;.home().alert(</code><code class="cm_n_n_n_2A00FF">&quot;Returned&quot;</code><code class="cm_n_n_n_0">);</code>
</div>
<br>
<strong>Now let's move on to the jGo technologies :</strong><br><br>
<ul>
<li>{@link JON} - <code style='color: green;'>Java Object Notation</code> - (<code>cloud.jgo.io.jon</code>)</li>
<li>{@link JOR} - <code style='color: green;'>Java Object Representation</code> - (<code>cloud.jgo.io.jon</code>)</li>
<li>{@link JjDom} - <code style='color: green;'>Java <a href='#'>jQuery</a> Dom</code> - (<code>cloud.jgo.io.jon</code>)</li>
</ul>
<br>
<em>Visit the jGo website</em> : <a href='https://www.jgo.cloud/'>https://www.jgo.cloud/</a>
 */
public final class j£ extends cloud.jgo.£{
	/**
	 * Webcam
	 */
	/*
	 * this.pannelloWebcam = new WebcamPanel(this.webcam);
		this.pannelloWebcam.setDisplayDebugInfo(true);
		this.pannelloWebcam.setMirrored(true);
	 */
	public final static Webcam webcam = Webcam.getDefault();
	private static WebcamPanel webcamPanel = null ; 
	static{
		instance = getPowerfulInstance();
		webcam.setViewSize(WebcamResolution.VGA.getSize()); // è per metodi statici, quindi via le preoccupazioni
		webcamPanel = new WebcamPanel(webcam);
		webcamPanel.setDisplayDebugInfo(true);
		webcamPanel.setMirrored(true);
	}
	private static j£ getPowerfulInstance(){
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
	private j£() {}
	/**
	 * This method retrieves the object from a json file
	 * @param fileName the file name
	 * @param objClass the object class
	 * @param <T> the object type
	 * @return the deserialized object
	 */
	public static <T> T json(String fileName,Class<?>objClass){
		T t = null ;
		cloud.jgo.io.File jsonFile = new cloud.jgo.io.File(fileName);
		if (jsonFile.exists()) {
			BufferedReader reader=null;
			try {
				reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(jsonFile)));
				final Gson gson = new Gson();
				t = (T) gson.fromJson(reader,objClass);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return t ;
	}
	//Version - 1.0.5:
	// Mail methods
	// JSON main methods - 1.0.5:
	/**
	 * This method writes an object to a json file
	 * @param fileName the file name
	 * @param obj the object to be serialized
	 * @return the json file
	 */
	public static cloud.jgo.io.File json(String fileName,Object obj){
		if (£.extractFormatFromFileName(fileName).equals("json")) {
			cloud.jgo.io.File jsonFile = new cloud.jgo.io.File(fileName);
			Gson gson = new Gson();
			String json=gson.toJson(obj);
			£.writeFile(jsonFile, false, new String[]{json});
			return jsonFile;
		}
		else{
			return null ;
		}
	}
	// version : 1.0.5
		/**
		 * This method converts a java object to a string json
		 * @param obj the object to be serialized
		 * @return the json string
		 */
		public static String convertFromObjectToJsonString(Object obj){
			final Gson gson = new Gson();
			String jsonString = null ;
			jsonString = gson.toJson(obj);
			return jsonString ;
		}
		// version : 1.0.5
		/**
		 * This method converts a json string to object
		 * @param jsonString the json string
		 * @param objClass the object class
		 * @param <T> the object type
		 * @return the deserialized object
		 */
		public static <T> T convertFromJsonStringToObject(String jsonString,Class<?>objClass){
			final Gson gson = new Gson();
			T t = (T) gson.fromJson(jsonString,objClass);
			return t;
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
			inst = getPowerfulInstance();
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
			inst = getPowerfulInstance();
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
	 * @param smtpHostType email host type : example : gmail
	 * @param successLog the log that is printed in case the email is sent correctly
	 * @return the jGo access point
	 */
	public static j£ sendSimpleEmail(String recipient,String sender,String subject,String text,String smtpHostType,String successLog){
		j£ inst = null ;
		Properties props = new Properties();
		SMTPEntry entry = j£.SMTP_HOSTS.get(smtpHostType);
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
				inst = getPowerfulInstance();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return inst ;
	}	
	
	/**
	 * This method sends an email, but authenticating itself.
	 * Requires the sender's login credentials.
	 * @param recipient email recipient
	 * @param sender email sender 
	 * @param subject email subject
	 * @param text email text
	 * @param smtpHostType email host type : example : gmail
	 * @param successLog the log that is printed in case the email is sent correctly
	 * @param username sender username
	 * @param password sender password
	 * @return the jGo access point
	 */
	public static j£ sendSimpleEmailWithAuthentication
	(String recipient,String sender,String subject,
	 String text,String smtpHostType,String successLog,
	 String username,String password)
	{
		j£ inst = null ;
		
		Properties props = new Properties();
		
		SMTPHosts hosts = SMTPHosts.getInstance();
		
		SMTPEntry entry = hosts.get(smtpHostType);
		
		if (entry!=null) {
			
			props.put("mail.smtp.host",entry.getHost());
			
			props.put("mail.smtp.port",entry.getPort());
			
			props.put("mail.smtp.starttls.enable", "true");
		
			props.put("mail.smtp.auth", "true"); 
			
			Authenticator autentication = new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					// TODO Auto-generated method stub
					return new PasswordAuthentication(username,password);
				}
			};
			Session session = Session.getDefaultInstance(props,autentication);
			
			MimeMessage message = new MimeMessage(session);
			
			try {
				message.setFrom(new InternetAddress(sender));
				message.addRecipient(Message.RecipientType.TO,new InternetAddress(recipient));
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				message.setSubject(subject);
				message.setText(text);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Transport.send(message);
				if (successLog!=null) {
					System.out.println(successLog);
				}
				inst = getPowerfulInstance();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return inst ;
	}
	
	
	/**
	 * This method sends an email, but authenticating itself.
	 * Requires the sender's login credentials.
	 * @param recipient email recipient
	 * @param sender email sender 
	 * @param subject email subject
	 * @param text email text
	 * @param smtpHostType email host type : example : gmail
	 * @param successLog the log that is printed in case the email is sent correctly
	 * @param username sender username
	 * @param password sender password
	 * @param attached attached file
	 * @return the jGo access point
	 */
	public static j£ sendSimpleEmailWithAuthentication
	(String recipient,String sender,String subject,
	 String text,String smtpHostType,String successLog,
	 String username,String password,File attached)
	{
		j£ inst = null ;
		
		Properties props = new Properties();
		
		SMTPHosts hosts = SMTPHosts.getInstance();
		
		SMTPEntry entry = hosts.get(smtpHostType);
		
		if (entry!=null) {
			
			props.put("mail.smtp.host",entry.getHost());
			
			props.put("mail.smtp.port",entry.getPort());
			
			props.put("mail.smtp.starttls.enable", "true");
		
			props.put("mail.smtp.auth", "true"); 
			
			Authenticator autentication = new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					// TODO Auto-generated method stub
					return new PasswordAuthentication(username,password);
				}
			};
			Session session = Session.getDefaultInstance(props,autentication);
			
			MimeMessage message = new MimeMessage(session);
			
			try {
				message.setFrom(new InternetAddress(sender));
				message.addRecipient(Message.RecipientType.TO,new InternetAddress(recipient));
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				message.setSubject(subject);
				message.setText(text);
				if (attached!=null) {
					if (attached.exists()) {
						message.setFileName(attached.getPath());
					}
				}
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Transport.send(message);
				if (successLog!=null) {
					System.out.println(successLog);
				}
				inst = getPowerfulInstance();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return inst ;
	}
	
	/**
	 * This method sends an email, but authenticating itself.
	 * Requires the sender's login credentials.
	 * @param recipient email recipient
	 * @param sender email sender 
	 * @param subject email subject
	 * @param text email text
	 * @param smtp_host smtp host
	 * @param smtp_port smtp port
	 * @param successLog the log that is printed in case the email is sent correctly
	 * @param username sender username
	 * @param password sender password
	 * @param attached attached file
	 * @return the jGo access point
	 */
	public static j£ sendSimpleEmailWithAuthentication
	(String recipient,String sender,String subject,
	 String text,String smtp_host,int smtp_port,String successLog,
	 String username,String password,File attached)
	{
		j£ inst = null ;
		
		Properties props = new Properties();
	
			props.put("mail.smtp.host",smtp_host);
			
			props.put("mail.smtp.port",smtp_port);
			
			props.put("mail.smtp.starttls.enable", "true");
		
			props.put("mail.smtp.auth", "true"); 
			
			Authenticator autentication = new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					// TODO Auto-generated method stub
					return new PasswordAuthentication(username,password);
				}
			};
			Session session = Session.getDefaultInstance(props,autentication);
			
			MimeMessage message = new MimeMessage(session);
			
			try {
				message.setFrom(new InternetAddress(sender));
				message.addRecipient(Message.RecipientType.TO,new InternetAddress(recipient));
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				message.setSubject(subject);
				message.setText(text);
				if (attached!=null) {
					if (attached.exists()) {
						message.setFileName(attached.getPath());
					}
				}
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Transport.send(message);
				if (successLog!=null) {
					System.out.println(successLog);
				}
				inst = getPowerfulInstance();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return inst ;
	}
	
	
	
	/**
	 * This method sends an email, but authenticating itself.
	 * Requires the sender's login credentials.
	 * @param recipient email recipient
	 * @param sender email sender 
	 * @param subject email subject
	 * @param text email text
	 * @param smtp_host smtp host
	 * @param smtp_port smtp port
	 * @param successLog the log that is printed in case the email is sent correctly
	 * @param username sender username
	 * @param password sender password
	 * @param attached attached file
	 * @param contentType the content type
	 * @return the jGo access point
	 */
	public static j£ sendSimpleEmailWithAuthentication
	(String recipient,String sender,String subject,
	 String text,String smtp_host,int smtp_port,String successLog,
	 String username,String password,File attached,String contentType)
	{
		j£ inst = null ;
		
		Properties props = new Properties();
	
			props.put("mail.smtp.host",smtp_host);
			
			props.put("mail.smtp.port",smtp_port);
			
			props.put("mail.smtp.starttls.enable", "true");
		
			props.put("mail.smtp.auth", "true"); 
			
			Authenticator autentication = new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					// TODO Auto-generated method stub
					return new PasswordAuthentication(username,password);
				}
			};
			Session session = Session.getDefaultInstance(props,autentication);
			
			MimeMessage message = new MimeMessage(session);
			
			try {
				message.setFrom(new InternetAddress(sender));
				message.addRecipient(Message.RecipientType.TO,new InternetAddress(recipient));
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				message.setSubject(subject);
				message.setContent(text,contentType);
				if (attached!=null) {
					if (attached.exists()) {
						message.setFileName(attached.getPath());
					}
				}
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Transport.send(message);
				if (successLog!=null) {
					System.out.println(successLog);
				}
				inst = getPowerfulInstance();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return inst ;
	}
	
	// Webcam methods - version 1.0.5
	
	/**
	 * This method opens the webcam
	 * @return jGo access point
	 */
	public static j£ openWebcam(){
		j£ inst = null ;
		if (webcam!=null) {
			boolean flag = webcam.open();
			if (flag) {
				inst = getPowerfulInstance();
			}
		}
		return inst ;
	}
	
	/**
	 * This method closes the webcam
	 * @return jGo access point
	 */
	public static j£ closeWebcam(){
		j£ inst = null ;
		if (webcam!=null) {
			boolean flag = webcam.close();
			if (flag) {
				inst = getPowerfulInstance();
			}
		}
		return inst ;
	}
	// version 1.0.5
	/**
	 * This method returns a desktop application for webcam monitoring
	 * @param title jframe title
	 * @param icon jframe icon
	 * @param visibility jframe visibility
	 * @return the jframe
	 */
	public static JFrame getJFrameWebcam(String title,ImageIcon icon, boolean visibility){
		JFrame frame = j£._S.createFrame(title,icon, false);
		frame.setLocationRelativeTo(null);
		frame.add(webcamPanel);
		if (visibility) {
			frame.setVisible(true);
		}
		 frame.pack() ;
		 return frame ;
	}
	
	/**
	 * This method returns a desktop application for webcam monitoring
	 * @param title jframe title
	 * @param icon jframe icon
	 * @param width jframe width
	 * @param height jframe height
	 * @param visibility jframe visibility
	 * @return the jframe
	 */
	public static JFrame getJFrameWebcam(String title,ImageIcon icon,int width,int height,boolean visibility){
		JFrame frame = j£._S.createFrame(title, width, height, icon, visibility);
		frame.setLocationRelativeTo(null);
		frame.add(webcamPanel);
		if (visibility) {
			frame.setVisible(true);
		}
		return frame ;
	}
	/**
	 * This method takes photos from the webcam .
	 * @param fileName file name
	 * @return jGo access point
	 */
	public static j£ capture(String fileName){
		j£ inst = null ;
		if (webcam!=null) {
			if (webcam.isOpen()) {
				BufferedImage image = webcam.getImage();
				try {
					ImageIO.write(image,"PNG",new File(fileName));
					inst = getPowerfulInstance();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return inst ;
	}
}
