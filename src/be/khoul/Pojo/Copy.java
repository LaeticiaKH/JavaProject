package be.khoul.Pojo;

import java.io.Serializable;
import java.util.ArrayList;

import be.khoul.DAO.*;
import be.khoul.DAOFactory.AbstractDAOFactory;

public class Copy implements Serializable {
	
	
	private static final long serialVersionUID = 484910389772802237L;
	private static final AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private static final DAO<Copy> copyDao = adf.getCopyDAO();
	
	private int id;
	private VideoGame videoGame;
	private Player owner;
	private Loan loan;
	
	//Constructor
	public Copy(int id, VideoGame videoGame,Player owner) {
		this.id = id;
		this.videoGame = videoGame;
		this.owner = owner;
	}
	
	public Copy(VideoGame videoGame,Player owner) {
		this(0, videoGame, owner);
		
	}
	
	//Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public VideoGame getVideoGame() {
		return videoGame;
	}

	public void setVideoGame(VideoGame videoGame) {
		this.videoGame = videoGame;
	}
	
	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

	//Methods
	public boolean releaseCopy() {
		Boolean success = loan.endLoan();
		if(success) {
			owner.setCredit(loan.getLender().getCredit());
			loan = null;
			
		}
		return success;
	}
	
	public boolean borrow(Loan l) {
		loan = l;
		Boolean success = loan.createLoan();
		if(!success) {
			loan = null;
		}
		return success;
	}
	
	public boolean isAvailable() {
		//Check if copy is in a loan 
		//If copy is in a loan => check if loan is ongoing or not
		return ((CopyDAO) copyDao).isCopyAvailable(id);
		
	}
	
	
	//Methods DAO
	public static ArrayList<Copy> getCopiesFor(VideoGame videogame) {
		ArrayList<Copy> listCopies = ((CopyDAO) copyDao).findCopiesFor(videogame);
		for(Copy c : listCopies) {
			if(!c.isAvailable()) {
				//if copy is already in a loan
				c.setLoan(Loan.getLoanFor(c));
			}
			
		}
		return listCopies;
	}
	
	public static ArrayList<Copy> getCopiesFor(Player player) {
		ArrayList<Copy> listCopies = ((CopyDAO) copyDao).findCopiesFor(player);
		for(Copy c : listCopies) {
			if(!c.isAvailable()) {
				//if copy is already in a loan
				c.setLoan(Loan.getLoanFor(c));
			}
			
		}
		return listCopies;
		
	}
	
	public boolean create() {
		boolean success = copyDao.create(this);
		if(success) {
			videoGame.addCopy(this);
			owner.addCopy(this);
		}
		return success;
	}
	
	public boolean delete() {
		boolean success = copyDao.delete(this);
		if(success) {
			videoGame.removeCopy(this);
			owner.removeCopy(this);
		}
		
		return success;
	
	}

	
	
}
