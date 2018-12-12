package test;

import cloud.jgo.jjdom.dom.nodes.Document;
import cloud.jgo.jjdom.dom.nodes.Element;
import cloud.jgo.jjdom.dom.nodes.xml.XMLDocument;

public class XMLDocumentTest {
public static void main(String[] args) {
	
	
	
	
	Document document = new XMLDocument("registration");
	
	// creo gli elementi 
	
	Element regDate,accounts,account,username,password = null ;
	
	
	regDate = document.createElement("date");
	accounts = document.createElement("accounts");
	account = document.createElement("account");
	username = document.createElement("username");
	password = document.createElement("password");
	
	regDate.setTextContent("12/12/2018");
	username.setTextContent("wasp91");
	password.setTextContent("wasp91dayno");
	
	
	account.appendChilds(username,password);
	accounts.appendChild(account);
	
	document.getRootElement().appendChilds(regDate,accounts);
	
	
	document.printMarkup();

	
	
	
}
}
