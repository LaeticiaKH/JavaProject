package be.khoul.DAO;

import java.sql.Connection;
import java.util.ArrayList;

import be.khoul.Pojo.*;

public class UserDAO extends DAO<User> {

	public UserDAO(Connection conn) {
		super(conn);
	}
	
	public boolean create(User obj){		
		return false;
	}
	
	public boolean delete(User obj){
		return false;
	}
	
	public boolean update(User obj){
		return false;
	}

	public ArrayList<User> findAll() {
		
		return null;
	}

	

	
}
