package test;
import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import cloud.jgo.SMTPHosts;
import cloud.jgo.j£;
import cloud.jgo.£Func;
import cloud.jgo.SMTPHosts.SMTPEntry;
import cloud.jgo.net.tcp.login.TCPLoginServer;
import cloud.jgo.£.Effect;
import cloud.jgo.£;
public class TestGeneral {
public static void main(String[] args) throws HeadlessException, AWTException, IOException {
	
	
	
	// a questo punto possiamo completare con i metodi dell'email
	
	
	String html = "<html><h1><img src='https://www.jgo.cloud/wp-content/uploads/2018/11/jgo.png'>Hello Marco</h1></html>";
	
	j£.sendSimpleEmailWithAuthentication
	("marco91martire@gmail.com","wasp91dayno@gmail.com","Test5",
	 html,"smtp.gmail.com",587,"Email Inviata @","wasp91dayno@gmail.com",
	 "bellissimo91",new File("ciao.txt"),"text/html");
	
	
	
	
	
	
}
}
