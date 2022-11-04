package be.khoul.DAO;

import java.sql.Connection;
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
	
	public ArrayList<Copy> findCopiesFor(VideoGame videoGame) {
		ArrayList<Copy> list = new ArrayList<>();
		try{
			
			PreparedStatement statement = connect.prepareStatement("SELECT * FROM Copy c INNER JOIN VideoGame v ON c.id_videogame = v.id_videogame WHERE v.id_videogame = ?");
			statement.setInt(1, videoGame.getId());
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				System.out.println(result.getObject(1) + " " + result.getObject(2));
				PlayerDAO playerDao = new PlayerDAO(this.connect);
				Copy c = new Copy(result.getInt("id_copy"), videoGame, playerDao.find(result.getInt("id_user_lender")));
				list.add(c);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return list;
		
	}

	@Override
	public Copy find(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean isCopyAvailable(int id) {
		boolean available = true;
		try{
			PreparedStatement statement = connect.prepareStatement("SELECT * FROM Copy c INNER JOIN Loan l ON c.id_copy = l.id_copy WHERE id_copy = ?");
			statement.setInt(1, id);
			ResultSet result =  statement.executeQuery();
			if(result.next()) {
				if(result.getBoolean("ongoing")) {
					available = false;
				}
			}
			
			
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return available;
	}

}
