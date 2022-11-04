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
	
	private int id;
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
	
    public VideoGame(int id, String name, int creditCost, String console) {
		
    	this.id = id;
		this.name = name;
		this.creditCost = creditCost;
		this.console = console;
		bookings = new ArrayList<>();
		copies = new ArrayList<>();
		
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
	
	public int getNumberOfAvailableCopies() {
		int num_available = 0;
		for(Copy c: copies) {
			if(c.isAvailable()) {
				num_available++;
			}
		}
		return num_available;
	}
	

	//Methods
	public Copy copyAvailable() {
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
			if(oldestBookings.get(i).getBorrower().getRegistrationDate().isBefore(oldestRegistrationDate.get(0).getBookingDate()) && oldestBookings.get(i) != oldestRegistrationDate.get(0)) {
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
			if(oldestRegistrationDate.get(i).getBorrower().getDateOfBirth().isBefore(oldestBirthDate.get(0).getBorrower().getDateOfBirth()) && oldestRegistrationDate.get(i) != oldestBirthDate.get(0)) {
				oldestBirthDate.add(oldestRegistrationDate.get(i));
			}
		}
		
		return oldestBirthDate;
	}	
	
	
	public Booking selectBooking() {
		/*1. Le plus d’unités sur son compte
		2. Réservation la plus ancienne
		3. Abonné inscrit depuis le plus longtemps
		4. Abonné le plus âgé
		5. Aléatoire.*/
		ArrayList<Booking> mostCredits = new ArrayList<>();
		
		if(bookings.size() > 0) {
			
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
						 for(Booking b:oldestBirthDate) {
							 System.out.println(b);
						 }
						
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
				System.out.println(mostCredits.get(0));
				return mostCredits.get(0);
			}
			
		}
				
		
		
		
		return null;

	}
	
	//Methods for DAO
	public static ArrayList<VideoGame> getAllVideoGames() {
		DAO<VideoGame> videoGameDao= adf.getVideoGameDAO();

		return  videoGameDao.findAll();
	}


	@Override
	public String toString() {
		return "VideoGame [id=" + id + ", name=" + name + ", creditCost=" + creditCost + ", console=" + console + "]";
	}
	
	
	
}
