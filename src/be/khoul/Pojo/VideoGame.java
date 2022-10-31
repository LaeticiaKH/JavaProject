package be.khoul.Pojo;

import java.io.Serializable;
import java.util.ArrayList;

import be.khoul.DAO.DAO;
import be.khoul.DAOFactory.AbstractDAOFactory;

public class VideoGame implements Serializable {
	 
	private static final long serialVersionUID = -6793786723230122631L;
	private static final AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	
	private String name;
	private int creditCost;
	private String console;
	
	private ArrayList<Booking> bookings;
	private ArrayList<Copy> copies;
	
	//Constructor
	public VideoGame(String name, int creditCost, String console) {
		
		this.name = name;
		this.creditCost = creditCost;
		this.console = console;
		bookings = new ArrayList<>();
		copies = new ArrayList<>();
		
	}
	
	//Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCreditCost() {
		return creditCost;
	}

	public void setCreditCost(int creditCost) {
		this.creditCost = creditCost;
	}

	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}
	
	public ArrayList<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(ArrayList<Booking> bookings) {
		this.bookings = bookings;
	}

	
	public ArrayList<Copy> getCopies() {
		return copies;
	}

	public void setCopies(ArrayList<Copy> copies) {
		this.copies = copies;
	}

	//Methods
	public Copy copyAvailable() {
		
		return null;
	}
	
	public void selectBooking() {
		
	}
	
	//Methods for DAO
	public static ArrayList<VideoGame> getAllVideoGames() {
		DAO<VideoGame> videoGameDao= adf.getVideoGameDAO();

		return  videoGameDao.findAll();
	}
	
	
}
