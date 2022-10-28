package be.khoul.DAO;

import java.sql.Connection;
import java.util.ArrayList;

import be.khoul.Pojo.*;

public class VideoGameDAO extends DAO<VideoGame> {

	public VideoGameDAO(Connection conn) {
		super(conn);
	}

	public boolean create(VideoGame obj){		
		return false;
	}
	
	public boolean delete(VideoGame obj){
		return false;
	}
	
	public boolean update(VideoGame obj){
		return false;
	}

	public ArrayList<VideoGame> findAll() {
		
		return null;
	}
	

}
