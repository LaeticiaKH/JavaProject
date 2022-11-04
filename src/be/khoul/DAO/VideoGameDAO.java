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
		return false;
	}
	
	public boolean delete(VideoGame obj){
		return false;
	}
	
	public boolean update(VideoGame obj){
		return false;
	}

	public ArrayList<VideoGame> findAll() {
		ArrayList<VideoGame> list = new ArrayList<>();
		try{
			
			PreparedStatement statement = connect.prepareStatement("SELECT * FROM VideoGame");
			ResultSet result = statement.executeQuery();
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
		try{
			
			PreparedStatement statement = connect.prepareStatement("SELECT * FROM VideoGame WHERE id_videogame = ?");
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				videoGame = new VideoGame(result.getInt("id_videogame"), result.getString("name"), result.getInt("credit_cost"), result.getString("console"));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return videoGame;
	}
	

}
