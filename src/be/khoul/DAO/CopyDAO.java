package be.khoul.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.khoul.Pojo.*;

public class CopyDAO extends DAO<Copy> {

	public CopyDAO(Connection conn) {
		super(conn);
	}
	
	public boolean create(Copy obj){	
		boolean success = true;
		
		try(PreparedStatement statement = connect.prepareStatement("INSERT INTO Copy(id_videogame, id_user_lender) VALUES(?,?)")) {
			
			statement.setInt(1, obj.getVideoGame().getId());
			statement.setInt(2, obj.getOwner().getId());
			
			statement.executeUpdate();
			
		}
		catch(Exception e){
			e.printStackTrace();
			success = false;
		}
		
		return success;
	}
	
	public boolean delete(Copy obj){
		boolean success = true;
		
		try(PreparedStatement statement = connect.prepareStatement("DELETE FROM Copy WHERE id_copy = ?")){
			
			statement.setInt(1, obj.getId());
			statement.executeUpdate();
			
		}
		catch(Exception e){
			e.printStackTrace();
			success = false;
		}
		
		return success;
		
	}
	
	public boolean update(Copy obj){
		return false;
	}
	
	public ArrayList<Copy> findAll() {
			
		return null;
	}
	
	public ArrayList<Copy> findCopiesFor(VideoGame videoGame) {
		ArrayList<Copy> list = new ArrayList<>();
		try(PreparedStatement statement = connect.prepareStatement("SELECT * FROM Copy c INNER JOIN VideoGame v ON c.id_videogame = v.id_videogame WHERE v.id_videogame = ?")){
			
			statement.setInt(1, videoGame.getId());
			try(ResultSet result = statement.executeQuery()){
				while(result.next()) {
					PlayerDAO playerDao = new PlayerDAO(this.connect);
					Copy c = new Copy(result.getInt("id_copy"), videoGame, playerDao.find(result.getInt("id_user_lender")));
					list.add(c);
				}
			}
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	public ArrayList<Copy> findCopiesFor(Player player) {
		ArrayList<Copy> list = new ArrayList<>();
		try(PreparedStatement statement = connect.prepareStatement("SELECT * FROM Copy c INNER JOIN VideoGame v ON c.id_videogame = v.id_videogame WHERE c.id_user_lender = ?")){
			
			statement.setInt(1, player.getId());
			try(ResultSet result = statement.executeQuery()){
				while(result.next()) {
					VideoGameDAO videoGameDao = new VideoGameDAO(this.connect);
					Copy c = new Copy(result.getInt("id_copy"), videoGameDao.find(result.getInt("id_videogame")), player);
					list.add(c);
				}
			}
			
		}
		catch(SQLException e){
			e.printStackTrace();
		} 
		
		return list;
		
	}

	@Override
	public Copy find(int id) {
		Copy copy = null;
		try(PreparedStatement statement = connect.prepareStatement("SELECT * FROM Copy WHERE id_copy = ?");){
			statement.setInt(1, id);
			
			try(ResultSet result =  statement.executeQuery()){
				if(result.next()) {
					VideoGameDAO videoGameDao = new VideoGameDAO(this.connect);
					PlayerDAO playerDao = new PlayerDAO(this.connect);
					copy = new Copy(result.getInt("id_copy"), videoGameDao.find(result.getInt("id_videogame")), playerDao.find(result.getInt("id_user_lender")) );
					
				}	
			}
			
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return copy;
	}
	
	public boolean isCopyAvailable(int id) {
		boolean available = true;
		try(PreparedStatement statement = connect.prepareStatement("SELECT * FROM Copy c INNER JOIN Loan l ON c.id_copy = l.id_copy WHERE c.id_copy = ?")){
			statement.setInt(1, id);
			
			try(ResultSet result =  statement.executeQuery()){
				while(result.next()) {
					if(result.getBoolean("ongoing")) {
						available = false;
					}	
				}	
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return available;
	}

}
