package be.khoul.DAOFactory;

import java.sql.Connection;

import be.khoul.Connection.VideoGameConnection;
import be.khoul.DAO.*;
import be.khoul.Pojo.*;


public class DAOFactory extends AbstractDAOFactory{

	protected static final Connection conn = VideoGameConnection.getInstance();
	
	public DAO<User> getUserDAO(){
		return new UserDAO(conn);
	}
	
	public DAO<Player> getPlayerDAO(){
		return new PlayerDAO(conn);
	}
	
	public DAO<Administrator> getAdministratorDAO(){
		return new AdministratorDAO(conn);
	}
	
	public DAO<Booking> getBookingDAO(){
		return new BookingDAO(conn);
	}
	
	public DAO<VideoGame> getVideoGameDAO(){
		return new VideoGameDAO(conn);
	}
	
	public DAO<Copy> getCopyDAO(){
		return new CopyDAO(conn);
	}
		
	public DAO<Loan> getLoanDAO(){
		return new LoanDAO(conn);
	}
	
	public DAO<HistoryCredits> getHistoryCreditsDAO(){
		return new HistoryCreditsDAO(conn);
	}

	

		

}
