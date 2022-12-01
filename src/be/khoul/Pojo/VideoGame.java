package be.khoul.Pojo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import be.khoul.DAO.DAO;
import be.khoul.DAOFactory.AbstractDAOFactory;

public class VideoGame implements Serializable {
	 
	private static final long serialVersionUID = -6793786723230122631L;
	private static final AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private static final DAO<VideoGame> videoGameDao = adf.getVideoGameDAO();
	
	private int id;
	private String name;
	private int creditCost;
	private String console;
	
	private ArrayList<Booking> bookings;
	private ArrayList<Copy> copies;
	private ArrayList<HistoryCredits> historiesCredits;
	
	//Constructor
	public VideoGame(String name, int creditCost, String console) {
		
		this.name = name;
		this.creditCost = creditCost;
		this.console = console;
		bookings = new ArrayList<>();
		copies = new ArrayList<>();
		historiesCredits = new ArrayList<>();
		
	}
	
    public VideoGame(int id, String name, int creditCost, String console) {
		
    	this.id = id;
		this.name = name;
		this.creditCost = creditCost;
		this.console = console;
		bookings = new ArrayList<>();
		copies = new ArrayList<>();
		historiesCredits = new ArrayList<>();
		
	}
	
	//Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
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

	
	public ArrayList<Copy> getCopies() {
		return copies;
	}

	public void setCopies(ArrayList<Copy> copies) {
		this.copies = copies;
	}
	
	

	public ArrayList<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(ArrayList<Booking> bookings) {
		this.bookings = bookings;
	}
	
	public ArrayList<HistoryCredits> getHistoriesCredits() {
		return historiesCredits;
	}

	public void setHistoriesCredits(ArrayList<HistoryCredits> historiesCredits) {
		this.historiesCredits = historiesCredits;
	}
	//Methods
	
	public void addBooking(Booking b) {
		bookings.add(b);
	}
	
	public void removeBooking(Booking b) {
		bookings.remove(b);
	}
	
	public void addCopy(Copy c) {
		copies.add(c);
	}
	
	public void removeCopy(Copy c) {
		copies.remove(c);
	}
	
	public void addHistoryCredit(HistoryCredits hc) {
		System.out.println(hc);
		historiesCredits.add(hc);
	}
	
	public void removeHistoryCredit(HistoryCredits hc) {
		historiesCredits.remove(hc);
	}
	
	public ArrayList<Copy> getAvailableCopies(){
		getVideoGameCopies();
		ArrayList<Copy> availableCopies = new ArrayList<>();
		for(Copy c: copies) {
			if(c.isAvailable()) {
				availableCopies.add(c);
			}
		}
		return availableCopies;
	}
	

	//Methods
	public Copy copyAvailable() {
		getVideoGameCopies();
		Copy copy = null;
		if(copies.size() > 0) {
			for(Copy c: copies) {
				if(c.isAvailable()) {
					return c;
				}
			}
		}
		return copy;
	}
	
	
	public ArrayList<Copy> getAvailableCopiesForPlayer(Player player){
		ArrayList<Copy> availableCopies = getAvailableCopies();
		//Get available copies excluding the copies of the player
		ArrayList<Copy> availableCopiesForPlayer = new ArrayList<>();
		for(Copy c: availableCopies) {
			if(c.getOwner().getId() != player.getId()) {
				availableCopiesForPlayer.add(c);	
			}
		}
		
		return availableCopiesForPlayer;
		
	}
	
	private ArrayList<Booking> selectMostCredits(){
		ArrayList<Booking> mostCredits = new ArrayList<>();
		
		
		mostCredits.add(bookings.get(0));
		//Get the player who got the most credit
		for(int i=1; i < bookings.size(); i++) {
			if(bookings.get(i).getBorrower().getCredit() > mostCredits.get(0).getBorrower().getCredit()) {
				mostCredits.set(0, bookings.get(i));
			}
		}

		//Check if multiples players got the most credits
		for(int i=1; i < bookings.size(); i++) {
			if(bookings.get(i).getBorrower().getCredit() == mostCredits.get(0).getBorrower().getCredit() && bookings.get(i) != mostCredits.get(0)) {
				mostCredits.add(bookings.get(i));
			}
		}
		
		return mostCredits;
	}	
	
	private ArrayList<Booking> selectOldestBookings(ArrayList<Booking> mostCredits){
		ArrayList<Booking> oldestBookings = new ArrayList<>();
		
		oldestBookings.add(mostCredits.get(0));
		for(int i=1; i < mostCredits.size(); i++) {
			if(mostCredits.get(i).getBookingDate().isBefore(oldestBookings.get(0).getBookingDate())) {
				oldestBookings.set(0, mostCredits.get(i));
			}
		}
		//Check if others players got the oldest booking
		for(int i=1; i < mostCredits.size(); i++) {
			if(mostCredits.get(i).getBookingDate().equals(oldestBookings.get(0).getBookingDate()) && mostCredits.get(i) != oldestBookings.get(0)) {
				oldestBookings.add(mostCredits.get(i));
			}
		}
		
		return oldestBookings;
	}	
	
	
	private ArrayList<Booking> selectOldestRegistrationDate(ArrayList<Booking> oldestBookings){
		ArrayList<Booking> oldestRegistrationDate = new ArrayList<>();
		oldestRegistrationDate.add(oldestBookings.get(0));
		
		for(int i=1; i < oldestBookings.size(); i++) {
			if(oldestBookings.get(i).getBorrower().getRegistrationDate().isBefore(oldestRegistrationDate.get(0).getBorrower().getRegistrationDate())) {
				oldestRegistrationDate.set(0, oldestBookings.get(i));
			}
		}
		//Check if others players got the oldest booking
		for(int i=1; i < oldestBookings.size(); i++) {
			if(oldestBookings.get(i).getBorrower().getRegistrationDate().equals(oldestRegistrationDate.get(0).getBorrower().getRegistrationDate()) && oldestBookings.get(i) != oldestRegistrationDate.get(0)) {
				oldestRegistrationDate.add(oldestBookings.get(i));
			}
		}
		
		return oldestRegistrationDate;
	}	
	
	private ArrayList<Booking> selectOldestBirthDate(ArrayList<Booking> oldestRegistrationDate){
		ArrayList<Booking> oldestBirthDate = new ArrayList<>();
		oldestBirthDate.add(oldestRegistrationDate.get(0));
		
		for(int i=1; i < oldestRegistrationDate.size(); i++) {
			if(oldestRegistrationDate.get(i).getBorrower().getDateOfBirth().isBefore(oldestBirthDate.get(0).getBorrower().getDateOfBirth())) {
				oldestBirthDate.set(0, oldestRegistrationDate.get(i));
			}
		}
		//Check if others players got the oldest booking
		for(int i=1; i < oldestRegistrationDate.size(); i++) {
			if(oldestRegistrationDate.get(i).getBorrower().getDateOfBirth().equals(oldestBirthDate.get(0).getBorrower().getDateOfBirth()) && oldestRegistrationDate.get(i) != oldestBirthDate.get(0)) {
				oldestBirthDate.add(oldestRegistrationDate.get(i));
			}
		}
		
		return oldestBirthDate;
	}	
	
	
	public Booking selectBooking() {
		
		ArrayList<Booking> mostCredits = new ArrayList<>();
		getVideoGameBookings();
		if(bookings.size() > 0) {
			//Check for the most credits
			mostCredits = selectMostCredits();
			if(mostCredits.size() > 1) {
				//check for the oldest booking
				ArrayList<Booking> oldestBookings = new ArrayList<>();
				oldestBookings = selectOldestBookings(mostCredits);
				
				if(oldestBookings.size() > 1) {
					ArrayList<Booking> oldestRegistrationDate = new ArrayList<>();
					 oldestRegistrationDate = selectOldestRegistrationDate(oldestBookings);
					 
					 if(oldestRegistrationDate.size() > 1) {
						 ArrayList<Booking> oldestBirthDate = new ArrayList<>();
						 oldestBirthDate = selectOldestBirthDate(oldestRegistrationDate);
						
						 if(oldestBirthDate.size() > 1) {
							 //Choose random booking
							 Random rand = new Random();
							 return oldestBirthDate.get(rand.nextInt(oldestBirthDate.size()-1));
							 
						 }
						 else {
							 return oldestBirthDate.get(0);
						 }
					 }
					 else {
						 return oldestRegistrationDate.get(0);
					 }
					 
				}
				else {
					return oldestBookings.get(0);
				}
				
			}
			else {
				return mostCredits.get(0);
			}
			
		}
				
		return null;

	}
	
	public void getBookingIntoLoan() {
		
		//while there are at least one available copy and a booking
		
		while(copyAvailable() != null && getVideoGameBookings().size() > 0) {
		
			Booking booking = selectBooking();
			
			//Get one available copy
			Copy copy = copyAvailable();
				
		    //Calculate end date 
		    LocalDate endDate = LocalDate.now().plusWeeks(booking.getDuration());
		    //Create Loan
		    Loan loan = new Loan(LocalDate.now(), endDate, true, copy, copy.getOwner(),booking.getBorrower());
		    //If the loan got created without problems the booking can be deleted
		    if(copy.borrow(loan)) {
		    	booking.delete();	
		    }	
		}
	}
	
	public ArrayList<Copy> getVideoGameCopies() {
		 copies = Copy.getCopiesFor(this);
		 
		 return copies;
	}
	
	public ArrayList<Booking> getVideoGameBookings() {
		 bookings = Booking.getBookingsFor(this);
		 
		 return bookings;
	}
	
	//Methods for DAO
	public static ArrayList<VideoGame> getAllVideoGames() {
		return  videoGameDao.findAll();
	}
    
	public boolean create() {
		
		return videoGameDao.create(this);
	}
	
	
	public boolean updateCredit(int newCredit) {
		int oldCredit = creditCost;
		creditCost = newCredit;
		boolean success = videoGameDao.update(this);
		if(!success) {
		
			creditCost = oldCredit;
		}
		return success;
	}
	
	public boolean delete() {
		return videoGameDao.delete(this);
	}
		
	
	
	
	
}
