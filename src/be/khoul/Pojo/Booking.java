package be.khoul.Pojo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import be.khoul.DAO.BookingDAO;
import be.khoul.DAO.DAO;
import be.khoul.DAOFactory.AbstractDAOFactory;

public class Booking implements Serializable {

	
	private static final long serialVersionUID = 8934435103788942302L;
	private static final AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private static final DAO<Booking> bookingDao = adf.getBookingDAO();
	
	private int id;
	private LocalDate bookingDate;
	private int duration;
	private Player borrower;
	private VideoGame videoGame;

	//Constructors
	public Booking(int id, LocalDate bookingDate, int duration , Player borrower, VideoGame videoGame) {
		this.id = id;
		this.bookingDate = bookingDate;
		this.duration= duration;
		this.borrower = borrower;
		this.videoGame = videoGame;
	}
	
	public Booking(LocalDate bookingDate, int duration , Player borrower, VideoGame videoGame) {
		this(0,bookingDate, duration, borrower, videoGame);
	}

	
	//Getters and Setters
	
	public int getId() {
		return this.id;
	}


	public void setId(int id) {
		this.id = id;
	}

	
	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	
	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}


	public Player getBorrower() {
		return borrower;
	}

	public void setBorrower(Player borrower) {
		this.borrower = borrower;
	}

	
	public VideoGame getVideoGame() {
		return videoGame;
	}


	public void setVideoGame(VideoGame videoGame) {
		this.videoGame = videoGame;
	}


	//Methods
	public boolean delete() {
		Boolean success = bookingDao.delete(this);
		if(success) {
			borrower.removeBooking(this);
			videoGame.removeBooking(this);
		}
		
		return success;
	}
	
	
	
	public boolean createBooking() {
		Boolean success = bookingDao.create(this);
		if(success) {
			borrower.addBooking(this);
			videoGame.addBooking(this);
		}

		return success;
	}
	
	public static ArrayList<Booking> getBookingsFor(Player player) {
		ArrayList<Booking> allBookings = bookingDao.findAll();
		ArrayList<Booking> bookingsForPlayer = new ArrayList<>();

		for(Booking booking :allBookings) {
			if(booking.borrower.getId() == player.getId()) {
				bookingsForPlayer.add(booking);
				
			}
		}
		
		return bookingsForPlayer;
	}
	
	public static ArrayList<Booking> getBookingsFor(VideoGame v) {
		ArrayList<Booking> allBookings = bookingDao.findAll();
		ArrayList<Booking> bookingsForVideoGame = new ArrayList<>();
		
		for(Booking booking :allBookings) {
			if(booking.videoGame.getId() == v.getId()) {
				bookingsForVideoGame.add(booking);
				
			}
		}
		
		return bookingsForVideoGame;
		
	}

	
}
