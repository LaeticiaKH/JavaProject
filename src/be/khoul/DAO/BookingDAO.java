package be.khoul.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.khoul.Pojo.*;

public class BookingDAO extends DAO<Booking> {

	public BookingDAO(Connection conn) {
		super(conn);
	}

	public boolean create(Booking obj){		
		boolean success = true;
		
		try {
			PreparedStatement statement = connect.prepareStatement("INSERT INTO Booking(booking_date, duration, id_user_borrower, id_videogame) VALUES(?,?,?,?)");
			statement.setDate(1, Date.valueOf(obj.getBookingDate()));
			statement.setInt(2, obj.getDuration());
			statement.setInt(3, obj.getBorrower().getId());
			statement.setInt(4, obj.getVideoGame().getId());
			statement.executeUpdate();
			
		}
		catch(Exception e){
			e.printStackTrace();
			success = false;
		}
		
		return success;
	
	}
	
	public boolean delete(Booking obj){
		boolean success = true;
		try {
			PreparedStatement statement = connect.prepareStatement("DELETE FROM Booking WHERE id_booking = ?");
			statement.setInt(1, obj.getId());
			statement.executeUpdate();
			
		}
		catch(Exception e){
			e.printStackTrace();
			success = false;
		}
		return success;
	}
	
	public boolean update(Booking obj){
		return false;
	}
	
	public ArrayList<Booking> findAll() {
		
		return null;
	}

	@Override
	public Booking find(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<Booking> findBookingsFor(Player player){
		ArrayList<Booking> listBookings = new ArrayList<>();
		
		try {
			PreparedStatement statement = connect.prepareStatement("SELECT * FROM Booking WHERE id_user_borrower = ?");
			statement.setInt(1, player.getId());

			ResultSet result = statement.executeQuery();
			while(result.next()) {
				VideoGameDAO videoGameDao = new VideoGameDAO(this.connect);
				VideoGame videoGame = videoGameDao.find(result.getInt("id_videogame"));
				
				listBookings.add(new Booking(result.getInt("id_booking"),result.getDate("booking_date").toLocalDate(), result.getInt("duration"), player, videoGame));
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		
		return listBookings;
	}
	
	public ArrayList<Booking> findBookingsFor(VideoGame videoGame){
		ArrayList<Booking> listBookings = new ArrayList<>();
		try {
			PreparedStatement statement = connect.prepareStatement("SELECT * FROM Booking WHERE id_videogame = ?");
			statement.setInt(1, videoGame.getId());

			ResultSet result = statement.executeQuery();
			while(result.next()) {
				PlayerDAO playerDao = new PlayerDAO(this.connect);
				Player player = playerDao.find(result.getInt("id_user_borrower"));
				System.out.println("id booking in DAO" + result.getInt("id_booking"));
				listBookings.add(new Booking(result.getInt("id_booking"),result.getDate("booking_date").toLocalDate(), result.getInt("duration"), player, videoGame));
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		return listBookings;
		
	}

	
	
}
