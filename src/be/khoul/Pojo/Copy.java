package be.khoul.Pojo;

import java.io.Serializable;

public class Copy implements Serializable {
	
	
	private static final long serialVersionUID = 484910389772802237L;
	private VideoGame videoGame;
	private Player owner;
	private Loan loan;
	
	//Constructor
	public Copy(VideoGame videoGame,Player owner) {
		this.videoGame = videoGame;
		this.owner = owner;
	}
	
	//Getters and Setters
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
		
	}
	
	public void borrow() {
		
	}
	
	public boolean isAvailable() {
		return false;
	}
}
