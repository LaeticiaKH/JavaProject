package be.khoul.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.khoul.Pojo.Copy;
import be.khoul.Pojo.Loan;

public class LoanDAO extends DAO<Loan> {

	public LoanDAO(Connection conn) {
		super(conn);
	}
	
	public boolean create(Loan obj){		
		return false;
	}
	
	public boolean delete(Loan obj){
		return false;
	}
	
	public boolean update(Loan obj){
		return false;
	}
	
	public ArrayList<Loan> findAll() {
		
		return null;
	}

	@Override
	public Loan find(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
