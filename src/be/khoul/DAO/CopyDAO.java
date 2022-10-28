package be.khoul.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.khoul.Pojo.Copy;

public class CopyDAO extends DAO<Copy> {

	public CopyDAO(Connection conn) {
		super(conn);
	}
	
	public boolean create(Copy obj){		
		return false;
	}
	
	public boolean delete(Copy obj){
		return false;
	}
	
	public boolean update(Copy obj){
		return false;
	}
	
	public ArrayList<Copy> findAll() {
			
		return null;
	}

}
