package be.khoul.Pojo;

import java.io.Serializable;

import be.khoul.DAO.UserDAO;
import be.khoul.DAOFactory.AbstractDAOFactory;

public abstract class User implements Serializable{

	private static final long serialVersionUID = 3673341612140501824L;
	private static final AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private String username;
	private String password;
	
	public static final int ADMIN = 0;
	public static final int PLAYER= 1;
	
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
	
	
	//Methods for UserDAO
	public static User logIn(String username, String password) {
		UserDAO userDao = (UserDAO) adf.getUserDAO();
		return userDao.getUser(username, password);
	}
	
	
	
	
}
