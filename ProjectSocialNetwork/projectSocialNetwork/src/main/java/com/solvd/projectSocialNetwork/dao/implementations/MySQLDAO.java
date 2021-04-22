package com.solvd.projectSocialNetwork.dao.implementations;

import com.solvd.projectSocialNetwork.conectionPool.ConnectionPool;

public abstract class MySQLDAO {

	protected ConnectionPool cp;
	
	public MySQLDAO() {
		this.cp= ConnectionPool.getInstance();
	}
}
