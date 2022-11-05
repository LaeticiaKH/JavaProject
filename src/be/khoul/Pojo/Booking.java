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
		this.bookingDate = bookingDate;
		this.duration= duration;
		this.borrower = borrower;
		this.videoGame = videoGame;
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
		DAO<Booking> bookingDao = adf.getBookingDAO();
		
		return bookingDao.delete(this);
		
	}
	
	
	//Methods DAO
	public boolean createBooking() {
		DAO<Booking> bookingDao = adf.getBookingDAO();
		
		return bookingDao.create(this);
	}
	
	public static ArrayList<Booking> getBookings(Player player) {
		BookingDAO bookingDao = (BookingDAO)adf.getBookingDAO();
		
		return bookingDao.findBookingsFor(player);
	}
	
	public static ArrayList<Booking> getBookings(VideoGame v) {
		BookingDAO bookingDao = (BookingDAO)adf.getBookingDAO();
		
		return bookingDao.findBookingsFor(v);
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", bookingDate=" + bookingDate + ", duration=" + duration + ", borrower="
				+ borrower + ", videoGame=" + videoGame + "]";
	}


	
	
	
	
}
