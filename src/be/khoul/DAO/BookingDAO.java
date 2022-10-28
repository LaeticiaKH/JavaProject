package be.khoul.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.khoul.Pojo.*;

public class BookingDAO extends DAO<Booking> {

	public BookingDAO(Connection conn) {
		super(conn);
	}

	public boolean create(Booking obj){		
		return false;
	}
	
	public boolean delete(Booking obj){
		return false;
	}
	
	public boolean update(Booking obj){
		return false;
	}
	
	public ArrayList<Booking> findAll() {
		
		return null;
	}

	
	
}
