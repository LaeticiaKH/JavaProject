package be.khoul.DAO;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
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
				statement = connect.prepareStatement("INSERT INTO Player(id_user, credit, registration_date, date_of_birth, pseudo) VALUES(?,?,?,?,?)");
				statement.setInt(1, key_user.getInt(1));
				statement.setInt(2, 10);
				statement.setDate(3, Date.valueOf(obj.getRegistrationDate()));
				statement.setDate(4, Date.valueOf(obj.getDateOfBirth()));
				statement.setString(5, obj.getPseudo());
				statement.executeUpdate();
			}
			
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
		boolean success = true;
		
		try{
			
			PreparedStatement statement = connect.prepareStatement("UPDATE Player SET credit = ? WHERE id_user = ?");
			statement.setInt(1, obj.getCredit());
			statement.setInt(2, obj.getId());
			statement.executeUpdate();
			
				
		}
		catch(SQLException e){
			e.printStackTrace();
			success = false;
		}
		
		return success;
	}
	
	
	public ArrayList<Player> findAll() {
		
		
		return null;
	}
	
	public boolean pseudoExist(String pseudo) {
		boolean exist = false;
		try{
			
			PreparedStatement statement = connect.prepareStatement("SELECT * FROM Player WHERE pseudo = ?");
			statement.setString(1, pseudo);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				exist = true;
			}
				
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return exist;
	}
	
	public boolean usernameExist(String username) {
		boolean exist = false;
		try{
			
			PreparedStatement statement = connect.prepareStatement("SELECT * FROM Users WHERE username = ?");
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				exist = true;
			}
				
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return exist;
	}

	
	public Player find(int id) {
		Player player = null;
		String username = null;
		String password = null;
			
		try{
			
			PreparedStatement statement = connect.prepareStatement("SELECT * FROM Users WHERE id_user = ?");
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
			   username = result.getString("username");
			   password = result.getString("password");
			}
			
			PreparedStatement statement2 = connect.prepareStatement("SELECT * FROM Player WHERE id_user = ?");
			statement2.setInt(1, id);
			ResultSet result2 = statement2.executeQuery();
			if(result2.next()) {
				
				LocalDate registration = result2.getDate("registration_date").toLocalDate();
				LocalDate birth = result2.getDate("date_of_birth").toLocalDate();
				player = new Player(id,username, password, result2.getInt("credit"), registration , birth , result2.getString("pseudo"));
			}
				
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return player;
	}
	
}


