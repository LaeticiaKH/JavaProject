package be.khoul.Pojo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Player extends User implements Serializable{
	
	
	private static final long serialVersionUID = -6054067668325036045L;
	
	private int credit;
	private LocalDate registrationDate;
	private LocalDate dateOfBirth;
	private String pseudo;
	
	private ArrayList<Booking> bookings;
	private ArrayList<Loan> loans;
	private ArrayList<Copy> copies;
	
	
	//Constructor
	public Player(String username, String password, int credit, LocalDate registrationDate, LocalDate dateOfBirth, String pseudo){
		super(username, password);
		this.credit = credit;
		this.registrationDate = registrationDate;
		this.dateOfBirth = dateOfBirth;
		this.pseudo = pseudo;
		this.bookings = new ArrayList<>();
		this.loans = new ArrayList<>();
		this.copies = new ArrayList<>();
	}
	

	//Getters and Setters
	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public ArrayList<Booking> getBookings() {
		return bookings;
	}


	public void setBookings(ArrayList<Booking> bookings) {
		this.bookings = bookings;
	}
	
	
	public ArrayList<Loan> getLoans() {
		return loans;
	}


	public void setLoans(ArrayList<Loan> loans) {
		this.loans = loans;
	}


	//Methods
	public void addBooking(Booking b) {
		bookings.add(b);
	}
	public void addLoan(Loan l) {
		loans.add(l);
	}
	
	public void addCopy(Copy c) {
		copies.add(c);
	}
	
	public void removeBooking(Booking b) {
		bookings.remove(b);
	}
	public void removeLoan(Loan l) {
		loans.remove(l);
	}
	
	public void removeCopy(Copy c) {
		copies.remove(c);
	}
	
	public boolean loanAllowed() {
		//Check is user has enough credit
		if(credit > 0) {
			return true;
		}
		return false;
	}
	
	public void addBirthdayBonus() {
		
	}
	
	
	
}
