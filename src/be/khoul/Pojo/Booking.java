package be.khoul.Pojo;

import java.io.Serializable;
import java.time.LocalDate;

public class Booking implements Serializable {

	
	private static final long serialVersionUID = 8934435103788942302L;
	private LocalDate bookingDate;
	private Player borrower;
	private VideoGame videoGame;

	//Constructors
	public Booking(LocalDate bookingDate, Player borrower, VideoGame videoGame) {
		this.bookingDate = bookingDate;
		this.borrower = borrower;
		this.videoGame = videoGame;
	}

	
	//Getters and Setters
	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	
	public Player getBorrower() {
		return borrower;
	}

	public void setBorrower(Player borrower) {
		this.borrower = borrower;
	}

	//Methods
	public void delete() {
		
	}
	
	
	
	
}
