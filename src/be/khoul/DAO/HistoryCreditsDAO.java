package be.khoul.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import be.khoul.Pojo.*;

public class HistoryCreditsDAO extends DAO<HistoryCredits> {

	public HistoryCreditsDAO(Connection conn) {
		super(conn);
		
	}

	@Override
	public boolean create(HistoryCredits obj) {
		boolean success = true;
		
		try(PreparedStatement statement = connect.prepareStatement("INSERT INTO HistoryCredits(change_date, old_credit, new_credit, id_videogame) VALUES(?,?,?,?)")) {
			
			statement.setDate(1,Date.valueOf(obj.getChangeDate()));
			statement.setInt(2, obj.getOldCredit());
			statement.setInt(3, obj.getNewCredit());
			statement.setInt(4, obj.getVideoGame().getId());
			
			statement.executeUpdate();
			
		}
		catch(Exception e){
			e.printStackTrace();
			success = false;
		}
		
		return success;
	
	}

	@Override
	public boolean delete(HistoryCredits obj) {
		
		return false;
	}

	@Override
	public boolean update(HistoryCredits obj) {
		
		return false;
	}

	@Override
	public ArrayList<HistoryCredits> findAll() {
		ArrayList<HistoryCredits> list = new ArrayList<>();
		try(PreparedStatement statement = connect.prepareStatement("SELECT * FROM HistoryCredits");
				ResultSet result = statement.executeQuery()){
			
			while(result.next()) {
				VideoGameDAO videoGameDao = new VideoGameDAO(this.connect);
				VideoGame videoGame = videoGameDao.find(result.getInt("id_videogame"));
				HistoryCredits hc = new HistoryCredits(result.getDate("change_date").toLocalDate(), result.getInt("old_credit"), result.getInt("new_credit"), videoGame);
				list.add(hc);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		} 
		
		return list;
	}

	@Override
	public HistoryCredits find(int id) {
		
		return null;
	}
	
	public ArrayList<HistoryCredits> findHistoriesCreditsFor(Loan l) {
		ArrayList<HistoryCredits> list = new ArrayList<>();
		try(PreparedStatement statement = connect.prepareStatement("SELECT h.change_date, h.old_credit, h.new_credit FROM "
				+ "((( HistoryCredits h INNER JOIN VideoGame v ON h.id_videogame = v.id_videogame) "
				+ "INNER JOIN Copy c ON c.id_videogame = v.id_videogame) "
				+ "INNER JOIN Loan l ON l.id_copy = c.id_copy) WHERE v.id_videogame = ? AND h.change_date >= ? GROUP BY h.change_date, h.old_credit, h.new_credit ORDER BY h.change_date");){
			
			statement.setInt(1, l.getCopy().getVideoGame().getId());
			statement.setDate(2, Date.valueOf(l.getStartDate()));
			
			try(ResultSet result = statement.executeQuery()){
				while(result.next()) {
					HistoryCredits h = new HistoryCredits(result.getDate("change_date").toLocalDate(), result.getInt("old_credit"), result.getInt("new_credit"), l.getCopy().getVideoGame());
					list.add(h);
				}
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		} 
		
		return list;
	}

}
