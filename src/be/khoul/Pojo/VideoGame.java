package be.khoul.Pojo;

import java.io.Serializable;
import java.util.ArrayList;

public class VideoGame implements Serializable {

	
	 
	private static final long serialVersionUID = -6793786723230122631L;
	private String name;
	private int creditCost;
	private String console;
	
	private ArrayList<Booking> bookings = new ArrayList<>();
	private ArrayList<Copy> copies;
	
	//Constructor
	public VideoGame(String name, int creditCost, String console, ArrayList<Copy> copies) {
		
		this.name = name;
		this.creditCost = creditCost;
		this.console = console;
		this.copies = copies;
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
	/*public Copy copyAvailable() {
	
	}*/
	
	public void selectBooking() {
		
	}
	
	
	
}