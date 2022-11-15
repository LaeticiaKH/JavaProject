package be.khoul.Pojo;

import java.io.Serializable;
import java.sql.Date;
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
	
	public int calculateCredit() {
		int balance = 0;
		LocalDate startDate = getStartDate();
		VideoGame videoGame = copy.getVideoGame();
		LocalDate endDate = getEndDate();
		int weeks =  (int) ChronoUnit.WEEKS.between(startDate, endDate);
		System.out.println("DEBUT :" + startDate + " FIN : " + endDate);
		System.out.println("Nombre de semaines :" + weeks);
		int previousWeeks;
		
		//Get all histories for the video game
		videoGame.setHistoriesCredits(HistoryCredits.findHistoryCreditsFor(this.copy));
		ArrayList<HistoryCredits> histories = videoGame.getHistoriesCredits();
		System.out.println(histories.size());
		for(HistoryCredits h: histories) {
			System.out.println(h);
			
		}
		
		if(histories.size() == 0) {
			//if no credit change happened 
			System.out.println("no credit change");
			balance = weeks * videoGame.getCreditCost();
			
			return balance;
		}
		
		
		if(ChronoUnit.WEEKS.between(getStartDate(), histories.get(histories.size()-1).getChangeDate()) == 0) {
			//if it's the first week => weeks between start date and end date * newCredit
			System.out.println("first week");
			balance = weeks * videoGame.getCreditCost();
		}
		
		else {
			HistoryCredits latestChange = histories.get(0);
			for(int i = 1; i <= weeks; i++) {
				boolean change = false;
				for(int j=0; j < histories.size() && change == false; j++) {
					
					HistoryCredits h = histories.get(j);
					//Check if a change happened during the week
					if((h.getChangeDate().isAfter(startDate.plusWeeks(i-1)) ||  h.getChangeDate().isEqual(startDate.plusWeeks(i-1))) && (h.getChangeDate().isBefore(startDate.plusWeeks(i)) || h.getChangeDate().isEqual(startDate.plusWeeks(i)))) {
						balance += histories.get(j).getNewCredit();
					    //Save the change as latest change 
						latestChange = h;
						change = true;
						
						
					}
					else {
						change = false;
					}
				}
				if(!change) {
					//no change, take the latest change to get the credit
					balance += latestChange.getOldCredit();
					System.out.println("No change happened during the week : " + balance);
				}
				System.out.println("Balance semaine " + i + "  : " + balance);
			}
			System.out.println("Balance final" + balance);
		}

		return balance;
	}
	
	public int calculateBalance() {
		
		return calculateCredit() + calculatePenality();
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
