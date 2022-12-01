package be.khoul.Pojo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Administrator extends User implements Serializable {

	
	private static final long serialVersionUID = 8864231752697485542L;
	

	//Constructor
	public Administrator(String username, String password) {
		super(username, password);
		
	}
	
	public Administrator() {}

	
	//Methods
	
	public boolean changeCredit(VideoGame videoGame, int newCredit) {
		
		int currentCredit = videoGame.getCreditCost();
		//create a history for the new change
		HistoryCredits historyCredits = new HistoryCredits(LocalDate.now(), videoGame.getCreditCost(), newCredit, videoGame);
		
		//Create historyCredits and update the credit
		if(historyCredits.create() && videoGame.updateCredit(newCredit) ) {
			//Get all the copies for the video game
			ArrayList<Copy> listCopies= videoGame.getCopies();
			for(Copy c: listCopies) {
				if(!c.isAvailable()) {
					//Calculate credit of each loan for a copy of the video game
					Loan loan = c.getLoan();
					//balance with new credit
					if(loan.isOngoing()) {
						loan.calculateBalance();
					}
				}
			}
			return true;
		}
		else {
			return false;
		}
		
		
		
	}
	
}
