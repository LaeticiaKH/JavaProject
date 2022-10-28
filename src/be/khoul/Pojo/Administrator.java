package be.khoul.Pojo;

import java.io.Serializable;

public class Administrator extends User implements Serializable {

	
	private static final long serialVersionUID = 8864231752697485542L;

	//Constructor
	public Administrator(String username, String password) {
		super(username, password);
		
	}
	
	public Administrator() {}

	
	//Methods
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	

	
}
