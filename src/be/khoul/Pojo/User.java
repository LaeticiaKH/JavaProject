package be.khoul.Pojo;

import java.io.Serializable;

public abstract class User implements Serializable{

	private static final long serialVersionUID = 3673341612140501824L;
	private String username;
	private String password;
	
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public User() {}


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
	
	@Override
	public String toString() {
		return "Pseudo: " + username + " Mot de passe:  " + password;
	}
	
	
	
	
}
