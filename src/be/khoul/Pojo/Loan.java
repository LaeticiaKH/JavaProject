package be.khoul.Pojo;

import java.io.Serializable;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import be.khoul.DAO.DAO;
import be.khoul.DAO.LoanDAO;
import be.khoul.DAOFactory.AbstractDAOFactory;

public class Loan implements Serializable{
	
	private static final long serialVersionUID = 5642696465117845192L;
	private static final AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	
	private int id;
	private LocalDate startDate;
	private LocalDate endDate;
	private boolean ongoing;
	private Copy copy;
	private Player lender;
	private Player borrower;
	
	//Constructor
	public Loan(int id, LocalDate startDate, LocalDate endDate, boolean ongoing, Copy copy, Player lender, Player borrower) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.ongoing = ongoing;
		this.copy = copy;
		this.lender = lender;
		this.borrower = borrower;
	}
	
	public Loan(LocalDate startDate, LocalDate endDate, boolean ongoing, Copy copy, Player lender, Player borrower) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.ongoing = ongoing;
		this.copy = copy;
		this.lender = lender;
		this.borrower = borrower;
	}

	//Getters and Setters 
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	
	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public boolean isOngoing() {
		return ongoing;
	}

	public void setOngoing(boolean ongoing) {
		this.ongoing = ongoing;
	}
	
	public Copy getCopy() {
		return copy;
	}

	public void setCopy(Copy copy) {
		this.copy = copy;
	}

	public Player getLender() {
		return lender;
	}

	public void setLender(Player lender) {
		this.lender = lender;
	}

	public Player getBorrower() {
		return borrower;
	}

	public void setBorrower(Player borrower) {
		this.borrower = borrower;
	}


	//Methods
	
	public int calculateBalance() {
		int weeks = (int) ChronoUnit.WEEKS.between(startDate, endDate);
		
		return (weeks * copy.getVideoGame().getCreditCost()) + calculatePenality();
	}
	
	public int calculatePenality() {
		int penality = 0;
		
		//If the loan is still ongoing after the end_date
		if(LocalDate.now().isAfter(endDate) && ongoing == true) {
			int days = (int) ChronoUnit.DAYS.between(endDate, LocalDate.now());
			penality += days * 5;
		}		
		return penality;
		
	}
	
	public boolean endLoan() {
		DAO<Loan> loanDao = adf.getLoanDAO();
		ongoing = false;
		borrower.removeCredits(calculateBalance());
		lender.addCredits(calculateBalance());
		
		return loanDao.update(this);

	}
	
	//Method DAO
	public boolean createLoan() {
		DAO<Loan> loanDao = adf.getLoanDAO();
		return loanDao.create(this);
	}
	
	public static ArrayList<Loan> getLoansFor(Player player) {
		LoanDAO loanDao = (LoanDAO) adf.getLoanDAO();
		
		return loanDao.getLoansForPlayer(player);
	}
	
	public static Loan getLoanForCopy(Copy c) {
		LoanDAO loanDao = (LoanDAO) adf.getLoanDAO();
		
		return loanDao.getLoanForCopy(c);
	}
	
	
	
}
