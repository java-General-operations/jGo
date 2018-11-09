package cloud.jgo.net.tcp.chat;

import java.io.Serializable;

public class Mexg implements Serializable{

	private String user ;
	private String mex ;
	public Mexg(String user, String mex) {
		super();
		this.user = user;
		this.mex = mex;
	}
	
	public void setUser(String user) {
		this.user = user;
	}

	public void setMex(String mex) {
		this.mex = mex;
	}

	public Mexg() {
		super();
		this.user = null;
		this.mex = null;
	}
	
	

	
	
	
	public String getUser() {
		return user;
	}

	public String getMex() {
		return mex;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "("+this.user+") : "+this.mex ;
	}
	
	
}
