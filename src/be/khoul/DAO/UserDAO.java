package be.khoul.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	public User getUser(String username, String password) {
		Player player = null;
		Administrator admin = null;
		try(PreparedStatement statement = connect.prepareStatement("SELECT * FROM Users WHERE username = ? AND password = ?")){
			
			
			statement.setString(1, username);
			statement.setString(2, password);
			
			try(ResultSet result = statement.executeQuery()){
				if(result.next()) {
					int usertype = result.getInt("usertype");
					int id = result.getInt("id_user");
					
					if(usertype == User.PLAYER) {
						PlayerDAO playerDao = new PlayerDAO(this.connect);
					    player = playerDao.find(id);
						return player;
						
					}
					else {
						
						AdministratorDAO adminDao = new AdministratorDAO(this.connect);
						admin = adminDao.find(id);
	
						return admin;
					}
				}
			}

		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}
