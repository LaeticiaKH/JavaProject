package be.khoul.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
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

	
	public Administrator find(int id) {
		Administrator admin = null;
		
		try{
			
			PreparedStatement statement = connect.prepareStatement("SELECT * FROM Users WHERE id_user = ?");
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
			    admin = new Administrator(result.getString("username"), result.getString("password"));
			}
			
				
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return admin;
	}

	
	
}
