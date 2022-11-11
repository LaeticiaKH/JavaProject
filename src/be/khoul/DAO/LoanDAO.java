package be.khoul.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.khoul.Pojo.*;

public class LoanDAO extends DAO<Loan> {

	public LoanDAO(Connection conn) {
		super(conn);
	}
	
	public boolean create(Loan obj){
		boolean success = true;
		
		try {
			PreparedStatement statement = connect.prepareStatement("INSERT INTO Loan(start_date, end_date, ongoing, id_copy, id_user_borrower) VALUES(?,?,?,?,?)");
			statement.setDate(1, Date.valueOf(obj.getStartDate()));
			statement.setDate(2, Date.valueOf(obj.getEndDate()));
			statement.setBoolean(3, obj.isOngoing());
			statement.setInt(4, obj.getCopy().getId());
			statement.setInt(5, obj.getBorrower().getId());
			statement.executeUpdate();
			
		}
		catch(Exception e){
			e.printStackTrace();
			success = false;
		}
		
		return success;

	}
	
	public boolean delete(Loan obj){
		return false;
	}
	
	public boolean update(Loan obj){
		boolean success = true;
		
		try {
			PreparedStatement statement = connect.prepareStatement("Update Loan SET ongoing = ? WHERE id_loan = ?");
			statement.setBoolean(1,obj.isOngoing());
			statement.setInt(2, obj.getId());
			statement.executeUpdate();
			
		}
		catch(Exception e){
			e.printStackTrace();
			success = false;
		}
		
		return success;
		
	}
	
	
	
	public ArrayList<Loan> getLoansForPlayer(Player player) {
		ArrayList<Loan> list = new ArrayList<>();
		try{
			
			PreparedStatement statement = connect.prepareStatement("SELECT * FROM Loan l INNER JOIN Player p ON l.id_user_borrower = p.id_user WHERE p.id_user = ?");
			statement.setInt(1, player.getId());
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				CopyDAO copyDao = new CopyDAO(this.connect);
				Copy copy = copyDao.find(result.getInt("id_copy"));
				Loan loan = new Loan(result.getInt("id_loan"), result.getDate("start_date").toLocalDate(), result.getDate("end_date").toLocalDate(), result.getBoolean("ongoing"), copy, copy.getOwner(), player);
				list.add(loan);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return list;
	}

	public Loan getLoanForCopy(Copy copy) {
		Loan loan = null;
		
		try {
			PreparedStatement statement = connect.prepareStatement("SELECT * FROM Loan WHERE id_copy = ? and ongoing = ?");
			statement.setInt(1, copy.getId());
			statement.setBoolean(2, true);
			ResultSet result;
			result = statement.executeQuery();
		
			if(result.next()) {
				PlayerDAO playerDao = new PlayerDAO(this.connect);
				Player player = playerDao.find(result.getInt("id_user_borrower"));
				loan = new Loan(result.getInt("id_loan"), result.getDate("start_date").toLocalDate(), result.getDate("end_date").toLocalDate(), result.getBoolean("ongoing"), copy, copy.getOwner(), player);
				
			}
		}
		 catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return loan;
	}
	@Override
	public Loan find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Loan> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
