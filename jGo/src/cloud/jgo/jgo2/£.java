package cloud.jgo.jgo2;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import cloud.jgo.SMTPHosts.SMTPEntry;
public class £ extends cloud.jgo.£{

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
	public static void sendSimpleEmail(String recipient,String sender,String subject,String text,String smtpHost,int smtpPort,String successLog){
		£ inst = null ;
		Properties props = new Properties();
		props.put("mail.smtp.host","smtp.gmail.com");
		props.put("mail.smtp.port", "587");
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
			System.out.println(successLog);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public static void sendSimpleEmail(String recipient,String sender,String subject,String text,SMTPEntry host,String successLog){
		£ inst = null ;
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
			System.out.println(successLog);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public static void sendSimpleEmail(String recipient,String sender,String subject,String text,String hostType,String successLog){
		£ inst = null ;
		Properties props = new Properties();
		SMTPEntry entry = £.SMTP_HOSTS.get(hostType);
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
				System.out.println(successLog);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}	
}
