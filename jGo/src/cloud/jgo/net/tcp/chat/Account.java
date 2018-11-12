package cloud.jgo.net.tcp.chat;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.filechooser.FileFilter;

import cloud.jgo.*;
import cloud.jgo.encrypt.Encrypts;
import cloud.jgo.file_manager.JFileManager;
import cloud.jgo.file_manager.PNG_JPG_Filter;

public class Account implements Serializable{

	
	private String username ; // che può essere anche l'email
	private String password ;
	private ImageIcon profileImage ;
	
	public ImageIcon getProfileImage() {
		return profileImage;
	}


	public void setProfileImage(ImageIcon profileImage) {
		this.profileImage = profileImage;
	}


	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}


	private Date registrationDate = null;
	
	public Date getRegistrationDate() {
		return registrationDate;
	}


	private byte[] bufferProfileImage = null ;
	
	private boolean registered ;


	public boolean isRegistered() {
		return registered;
	}
	
	
	public Account() {
		// TODO Auto-generated constructor stub
		this.username = null ;
		this.password = null ;
	}
	
	
	

public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




/**
 * 
 * @param filter il filtro per selezionare solo alcunui tipi di file
 * @throws IOException 1 exception
 */
public void setProfileImage(FileFilter filter) throws IOException{
		
		
		// qui si apre direttamente un file manager
		
	
	cloud.jgo.file_manager.JFileManager jfileManager = new JFileManager();
	jfileManager.getChooser().setDialogTitle("Import profile image");	
	if(filter != null){
		jfileManager.getChooser().setFileFilter(filter);
	}
	jfileManager.getChooser().setApproveButtonText("import");
	jfileManager.setVisible(true, JFileManager.MODE_OPEN,null);
	File image =jfileManager.setVisible(true, JFileManager.MODE_OPEN,null);
	// qui prendo i byte dell'immagine
	
		bufferProfileImage = £.getByteFrom(image);
		// adesso abbiamo i byte che si possono serializzare	
	}
	
	
	public Account(String user,String passw) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, HeadlessException, AWTException, IOException {
		
		this.username = user ;
		this.password = passw;
		£ obj = £.instance;
		if(this.password!=null){
			this.password=obj.getEncrypt().crypta(passw,obj.getEncrypt().getKey());
		}
		// password criptata
	}
	
	
	@Override
	public String toString() {
		return "User : "+this.username+" - Passw :"+this.password;
	}
	
	
	
	
	
}
