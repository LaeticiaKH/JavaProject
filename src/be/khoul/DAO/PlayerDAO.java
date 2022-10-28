package be.khoul.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.khoul.Pojo.*;

public class PlayerDAO extends DAO<Player> {

	public PlayerDAO(Connection conn) {
		super(conn);
	}
	
	public boolean create(Player obj){		
		return false;
	}
	
	public boolean delete(Player obj){
		return false;
	}
	
	public boolean update(Player obj){
		return false;
	}
	
	
	public ArrayList<Player> findAll() {
		
		
		return null;
	}
}


