package be.khoul.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.khoul.Pojo.*;

public class AdministratorDAO extends DAO<Administrator> {

	public AdministratorDAO(Connection conn) {
		super(conn);
	}

	public boolean create(Administrator obj){		
		return false;
	}
	
	public boolean delete(Administrator obj){
		return false;
	}
	
	public boolean update(Administrator obj){
		return false;
	}
	
	public ArrayList<Administrator> findAll() {
		
		ArrayList<Administrator> administratorList = new ArrayList<>();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM User WHERE usertype= 'admin'");
			while(result.next())
				administratorList.add(new Administrator(result.getString("username"), result.getString("password")));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return administratorList;
	}

	
	
}
