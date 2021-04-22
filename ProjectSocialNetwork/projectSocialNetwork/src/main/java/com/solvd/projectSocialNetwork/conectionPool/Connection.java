package com.solvd.projectSocialNetwork.conectionPool;

import java.sql.SQLException;

public class Connection {

	private int id;
	
	public Connection(int id) {
		this.id=id;
	}
	
	public boolean closeConnection() throws SQLException {
		//I close this connection
		return true;
	}
	
	public String toString() {
		return String.valueOf(id);
	}
}
