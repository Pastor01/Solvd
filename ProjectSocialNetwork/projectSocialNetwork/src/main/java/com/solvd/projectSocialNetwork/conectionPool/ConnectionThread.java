package com.solvd.projectSocialNetwork.conectionPool;

import java.sql.Connection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionThread extends Thread {

	private String thName;
	private ConnectionPool pool;
	private Logger log = LogManager.getLogger(ConnectionThread.class);
	
	public ConnectionThread() {
		
	}
	public ConnectionThread(String thName, ConnectionPool pool) {
		this.thName=thName;
		this.pool=pool;
	}
	
	public void run() {
		Connection myConnection = null;
		try {
			myConnection = pool.getConnection();
		} catch (InterruptedException e) {
			log.error("Thread " + thName+ "cant get one connection",e);
		}
		
		log.info("Thread "+ thName+ "take a connection " + myConnection);
		
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
