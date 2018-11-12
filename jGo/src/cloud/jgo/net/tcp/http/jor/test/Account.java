package cloud.jgo.net.tcp.http.jor.test;

import cloud.jgo.net.tcp.http.jor.JORServer.JOR;

@JOR(field_id = "username", url_Pattern = "accounts/")
public class Account {
	private String username ;
	private String password ;
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
	
	public Account() {
		// TODO Auto-generated constructor stub
	}
	public Account(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.username+" "+this.password;
	}
	
}
