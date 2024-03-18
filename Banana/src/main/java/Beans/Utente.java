package Beans;

import javax.servlet.http.HttpSession;

public class Utente {	
	private String username;
	private String password;
	private HttpSession session;
	
    public Utente() {		
		// TODO Auto-generated constructor stub
		this.session = null;
		this.username = "";
		this.password = "";
	}
    
    public Utente(String username, String password) {
    	this.username = username;
    	this.password = password;
    	this.session = null;    	
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

	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	@Override
	public String toString() {
		return "User [userName=" + username + ", password=" + password + "]";
	}
	@Override
	public boolean equals(Object obj) {
		Utente u = (Utente)obj;
		return this.username.equals(u.getUsername());
		// TODO Auto-generated method stub
	
	}
	
	public static boolean passwordCheck(String password) {
		return password.length() > 4;
	}
	
	
	
	

}
