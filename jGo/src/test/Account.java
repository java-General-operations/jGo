package test;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import cloud.jgo.io.jon.JONClass;
import cloud.jgo.io.jon.JONField;
import cloud.jgo.net.tcp.http.jor.JOR;
import cloud.jgo.net.tcp.http.jor.ResponseType;
@JOR(field_id = "username", url_Pattern = "/accounts", responseType = ResponseType.HTML_TYPE)
@JONClass
@XmlRootElement
public class Account {
	@JONField
	private String username ;
	@JONField
	private String password ;
	@XmlElement
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@XmlElement
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Account() {
		// TODO Auto-generated constructor stub
	}
	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.username+" "+this.password;
	}
	
}
