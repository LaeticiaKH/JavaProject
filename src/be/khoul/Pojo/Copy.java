package be.khoul.Pojo;

import java.io.Serializable;
import java.util.ArrayList;

import be.khoul.DAO.*;
import be.khoul.DAOFactory.AbstractDAOFactory;

public class Copy implements Serializable {
	
	
	private static final long serialVersionUID = 484910389772802237L;
	private static final AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	
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
		this.videoGame = videoGame;
		this.owner = owner;
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
	public void releaseCopy() {
		loan.endLoan();
		loan = null;
	}
	
	public boolean borrow() {
		return loan.createLoan();
	}
	
	public boolean isAvailable() {
		CopyDAO copyDao = (CopyDAO)adf.getCopyDAO();
		//Check if copy is in a loan 
		//If copy is in a loan => check if loan is ongoing or not
		return copyDao.isCopyAvailable(id);
		
	}
	
	
	//Methods DAO
	public static ArrayList<Copy> getCopiesFor(VideoGame videogame) {
		CopyDAO copyDao = (CopyDAO)adf.getCopyDAO();
		return copyDao.findCopiesFor(videogame);
	}
	
	public static ArrayList<Copy> getCopiesFor(Player player) {
		CopyDAO copyDao = (CopyDAO)adf.getCopyDAO();
		return copyDao.findCopiesFor(player);
	}
	
	public boolean create() {
		DAO<Copy> copyDAO = adf.getCopyDAO();
		
		return copyDAO.create(this);
	}

	@Override
	public String toString() {
		return "Copy [id=" + id + ", videoGame=" + videoGame.getId() + ", owner=" + owner.getId() + ", loan=" + loan + "]";
	}
	
	
}
