package cloud.jgo.net.tcp.chat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;


public class MemorizerAccount {

	// questo programma sarà sul server quindi
	// quando si chiama il metodo serializle si fa attenzione
	// se c'è una cartella chiamata accounts nello stesso path del programma
	// se non c'è la si crea
	
	
	
	public static void serializle(Account account) throws IOException{
	File defaultFolder = null ;
	if(!(defaultFolder = new File("accounts")).exists()){
		// la creo
		defaultFolder.mkdir();
	}
	FileOutputStream fos = new FileOutputStream(defaultFolder.getPath()+"\\"+account.getUsername()+".ser");
	ObjectOutputStream out = new ObjectOutputStream(fos);
	out.writeObject(account);out.flush();out.close();	
	}
	
	
	
    public static Account deserializle(String username) throws IOException, ClassNotFoundException{
		username = username+".ser";
    	Account account = null ;
    	boolean unserializeable = false ;
		File folderDefault = new File("accounts");
		int pos = 0 ;
		if(folderDefault.exists()){
			
			File[]accounts = folderDefault.listFiles();
			
			for (int i = 0; i < accounts.length; i++) {
				if(accounts[i].getName().toLowerCase().contentEquals(username.toLowerCase())){
					unserializeable = true ;
					pos = i ;
				}
			}
			if(unserializeable){
				FileInputStream fis = new FileInputStream(accounts[pos]);
				ObjectInputStream in = new ObjectInputStream(fis);
				account = (Account) in.readObject();
				in.close();
			}
		}
		return account ;
   }
	
	
	
	
	
	
}
