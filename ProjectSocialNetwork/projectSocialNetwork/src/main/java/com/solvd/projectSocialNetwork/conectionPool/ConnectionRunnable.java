package com.solvd.projectSocialNetwork.conectionPool;

import java.sql.Connection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionRunnable implements Runnable{
	private String name;
	private ConnectionPool pool;
	private Logger log = LogManager.getLogger(ConnectionRunnable.class);
	
	public ConnectionRunnable() {
		
	}
	public ConnectionRunnable(String name, ConnectionPool pool) {
		this.name=name;
		this.pool=pool;
	}
	
	public void run() {
		Connection myConnection = null;
		try {
			myConnection = pool.getConnection();
		} catch (InterruptedException e) {
			log.error("Runnable " + name+ "cant get one connection",e);
		}
		
		log.info("Runnable "+ name+ "take a connection " + myConnection);
		
		try {
			Thread.sleep(2000);
			//Simulation that the thread its doing something
		} catch (InterruptedException e) {
			log.warn("Cant sleep");
		}
		
		pool.addConnection(myConnection);
		log.info("The connection " + myConnection+ "was added");
	}
}
