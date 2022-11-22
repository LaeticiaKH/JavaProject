package be.khoul.Pojo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import be.khoul.DAO.DAO;
import be.khoul.DAO.HistoryCreditsDAO;
import be.khoul.DAOFactory.AbstractDAOFactory;

public class HistoryCredits implements Serializable{

	
	private static final long serialVersionUID = 7957815340729468531L;
	private static final AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private static DAO<HistoryCredits> historyCreditsDao = adf.getHistoryCreditsDAO();
	
	private LocalDate changeDate;
	private int oldCredit;
	private int newCredit;
	private VideoGame videoGame;
	
	
	public HistoryCredits(LocalDate changeDate, int oldCredit,int newCredit, VideoGame videoGame) {
		this.changeDate = changeDate;
		this.oldCredit = oldCredit;
		this.newCredit = newCredit;
		this.videoGame = videoGame;
	}
	//Getters and Setters
	public LocalDate getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(LocalDate changeDate) {
		this.changeDate = changeDate;
	}
	public int getOldCredit() {
		return oldCredit;
	}
	public void setOldCredit(int newCredit) {
		this.oldCredit = newCredit;
	}
	
	public int getNewCredit() {
		return newCredit;
	}
	public void setNewCredit(int newCredit) {
		this.newCredit = newCredit;
	}
	public VideoGame getVideoGame() {
		return videoGame;
	}
	public void setVideoGame(VideoGame videoGame) {
		this.videoGame = videoGame;
	}
	
	//Methods
	
	public boolean create() {
		boolean success = historyCreditsDao.create(this);
		if(success) {
			videoGame.addHistoryCredit(this);
		}
		return success;
	}
	
	public static ArrayList<HistoryCredits> findHistoryCreditsFor(Loan loan) {
		
		return ((HistoryCreditsDAO) historyCreditsDao).findHistoriesCreditsFor(loan);
	}
	@Override
	public String toString() {
		return "HistoryCredits [changeDate=" + changeDate + ", oldCredit=" + oldCredit + ", newCredit=" + newCredit
				+ ", videoGame=" + videoGame + "]";
	}
	
	
	
	
	
	
	

}
