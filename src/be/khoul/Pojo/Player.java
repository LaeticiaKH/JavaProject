package be.khoul.Pojo;
import be.khoul.DAO.CopyDAO;
import be.khoul.DAO.DAO;
import be.khoul.DAO.PlayerDAO;
import be.khoul.DAOFactory.AbstractDAOFactory;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Player extends User implements Serializable{
	
	
	private static final long serialVersionUID = -6054067668325036045L;
	private static final AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private static final DAO<Player> playerDao = adf.getPlayerDAO();
	
	private int id;
	private int credit;
	private LocalDate registrationDate;
	private LocalDate dateOfBirth;
	private String pseudo;
	private boolean isBonusGiven;
	
	private ArrayList<Booking> bookings;
	private ArrayList<Loan> loans;
	private ArrayList<Copy> copies;
	
	
	//Constructor
	public Player(int id, String username, String password, int credit, LocalDate registrationDate, LocalDate dateOfBirth, String pseudo, boolean isBonusGiven){
		super(username, password);
		this.id = id;
		this.credit = credit;
		this.registrationDate = registrationDate;
		this.dateOfBirth = dateOfBirth;
		this.pseudo = pseudo;
		this.isBonusGiven = isBonusGiven;
		this.bookings = new ArrayList<>();
		this.loans = new ArrayList<>();
		this.copies = new ArrayList<>();
	}
	
	public Player(String username, String password, int credit, LocalDate registrationDate, LocalDate dateOfBirth, String pseudo, boolean isBonusGiven){
		super(username, password);
		this.credit = credit;
		this.registrationDate = registrationDate;
		this.dateOfBirth = dateOfBirth;
		this.pseudo = pseudo;
		this.isBonusGiven = isBonusGiven;
		this.bookings = new ArrayList<>();
		this.loans = new ArrayList<>();
		this.copies = new ArrayList<>();
	}
	
	public Player() {
		
	}

	//Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
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
	

	public boolean isBonusGiven() {
		return isBonusGiven;
	}

	public void setBonusGiven(boolean isBonusGiven) {
		this.isBonusGiven = isBonusGiven;
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
	
	public ArrayList<Copy> getCopies() {
		return copies;
	}


	public void setCopies(ArrayList<Copy> copies) {
		this.copies = copies;
	}
	


	@Override
	public String toString() {
		return "Player [credit=" + credit + ", registrationDate=" + registrationDate + ", dateOfBirth=" + dateOfBirth
				+ ", pseudo=" + pseudo + ", bookings=" + bookings + ", loans=" + loans + ", copies=" + copies + "]";
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
		//if the bonus was not given this year
	    if(!isBonusGiven) {
	    	if(dateOfBirth.getDayOfMonth() == LocalDate.now().getDayOfMonth() && dateOfBirth.getMonth() ==  LocalDate.now().getMonth()) {
				credit += 2;
				isBonusGiven = true;
				playerDao.update(this);
				
				
			}
	    } else {
	    	//isBonusGiven back to false if today is the next day after the birthday
	    	if(dateOfBirth.plusDays(1).getDayOfMonth() == LocalDate.now().getDayOfMonth() && dateOfBirth.plusDays(1).getMonth() ==  LocalDate.now().getMonth()) {
	    		isBonusGiven = false;
	    		playerDao.update(this);
	    	}
	    }
		
		
	}
	
	public boolean isBirthday() {
		boolean birthday = false;
		if(dateOfBirth.getDayOfMonth() ==  LocalDate.now().getDayOfMonth() && dateOfBirth.getMonth() ==  LocalDate.now().getMonth()) {
			birthday = true;
			
		}
		
		return birthday;
	}
	
	public void addCredits(int credits) {
		credit += credits;
		//DAO<Player> playerDao = adf.getPlayerDAO();
		playerDao.update(this);
	}
	
	public void removeCredits(int credits) {
		credit -= credits;
		//DAO<Player> playerDao = adf.getPlayerDAO();
		playerDao.update(this);
		
	}
	
	public void getOwnLoans(){
		loans = Loan.getLoansFor(this);
	}
	
	public void getOwnCopies(){
		copies = Copy.getCopiesFor(this);
		
	}
	
	public void getOwnBookings(){
		bookings = Booking.getBookings(this);
	}
	
	
	//Methods for playerDAO
	
	public boolean signUp() {
	
		return playerDao.create(this);
	}
	
	public static boolean pseudoExist(String pseudo) {
		
		return ((PlayerDAO) playerDao).pseudoExist(pseudo);
	}
	
	public static boolean usernameExist(String username) {
		
		return ((PlayerDAO) playerDao).usernameExist(username);
	}
	
	public static ArrayList<Player> findAllPlayers(){
		return playerDao.findAll();
	}
	
	
	
}
