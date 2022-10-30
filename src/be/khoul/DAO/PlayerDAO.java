package be.khoul.DAO;

import java.sql.*;

import java.util.ArrayList;

import be.khoul.Pojo.*;

public class PlayerDAO extends DAO<Player> {

	public PlayerDAO(Connection conn) {
		super(conn);
	}
	
	public boolean create(Player obj){	
		boolean success = true;
		
		try {
			
			PreparedStatement statement = connect.prepareStatement("INSERT INTO Users(username, password, usertype) VALUES(?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, obj.getUsername());
			statement.setString(2, obj.getPassword());
			statement.setInt(3, User.PLAYER);
			statement.executeUpdate();
			
			ResultSet key_user = statement.getGeneratedKeys();
			if(key_user.next()){
				System.out.println("key : " + key_user.getInt(1));
			}
			
			
			statement = connect.prepareStatement("INSERT INTO Player(id_user, credit, registration_date, date_of_birth, pseudo) VALUES(?,?,?,?,?)");
			statement.setInt(1, key_user.getInt(1));
			statement.setInt(2, 10);
			statement.setDate(3, obj.getRegistrationDateToDate());
			statement.setDate(4, obj.getDateOfBirthToDate());
			statement.setString(5, obj.getPseudo());
			System.out.println(obj.getDateOfBirthToDate());
			System.out.println(obj.getRegistrationDateToDate());
			statement.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
			success = false;
		}
		
		return success;
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


