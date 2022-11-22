package be.khoul.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.khoul.Pojo.*;

public class VideoGameDAO extends DAO<VideoGame> {

	public VideoGameDAO(Connection conn) {
		super(conn);
	}

	public boolean create(VideoGame obj){
		boolean success = true;
		
		try(PreparedStatement statement = connect.prepareStatement("INSERT INTO VideoGame(name, credit_cost, console) VALUES(?,?,?)")) {
			
			statement.setString(1, obj.getName());
			statement.setInt(2, obj.getCreditCost());
			statement.setString(3, obj.getConsole());
			
			statement.executeUpdate();
			
		}
		catch(Exception e){
			e.printStackTrace();
			success = false;
		}
		
		return success;
	}
	
	
	public boolean delete(VideoGame obj){
		boolean success = true;
		try(PreparedStatement statement = connect.prepareStatement("DELETE FROM VideoGame WHERE id_videogame = ?")) {
			
			statement.setInt(1, obj.getId());
			statement.executeUpdate();
			
		}
		catch(Exception e){
			e.printStackTrace();
			success = false;
		}
		return success;
	}
	
	public boolean update(VideoGame obj){
		boolean success = true;
		
		try(
			PreparedStatement statement = connect.prepareStatement("Update VideoGame SET credit_cost = ? WHERE id_videogame = ?")){
		
			statement.setInt(1, obj.getCreditCost());
			statement.setInt(2, obj.getId());
			statement.executeUpdate();
			
		}
		catch(Exception e){
			e.printStackTrace();
			success = false;
		}
		
		return success;
	}

	public ArrayList<VideoGame> findAll() {
		ArrayList<VideoGame> list = new ArrayList<>();
		try(PreparedStatement statement = connect.prepareStatement("SELECT * FROM VideoGame ORDER BY name");
				ResultSet result = statement.executeQuery()){
			
			while(result.next()) {
				list.add(new VideoGame(result.getInt("id_videogame"), result.getString("name"), result.getInt("credit_cost"), result.getString("console")));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public VideoGame find(int id) {
		VideoGame videoGame = null;
		try(PreparedStatement statement = connect.prepareStatement("SELECT * FROM VideoGame WHERE id_videogame = ?")){
			
			
			statement.setInt(1, id);
		    try(ResultSet result = statement.executeQuery()){
		    	if(result.next()) {
					videoGame = new VideoGame(result.getInt("id_videogame"), result.getString("name"), result.getInt("credit_cost"), result.getString("console"));
				}
		    }
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return videoGame;
	}
	

}
