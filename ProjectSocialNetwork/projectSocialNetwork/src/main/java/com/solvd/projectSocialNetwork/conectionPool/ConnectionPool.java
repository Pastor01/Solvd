package com.solvd.projectSocialNetwork.conectionPool;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionPool {

	private Logger log = LogManager.getLogger(ConnectionPool.class);
	private AtomicInteger connectionCount;
	private BlockingQueue<Connection> connections;
	private static ConnectionPool instance;
	public static final Integer MAX_POOL= 5;
	
	private ConnectionPool() {
		connections = new LinkedBlockingQueue<Connection>(MAX_POOL);
		connectionCount = new AtomicInteger(0);
	}
	
	public static ConnectionPool getInstance() {
		if(instance == null) {
			instance = new ConnectionPool();
		}
		return instance;
	}
	
	public void initConnection() {
		
		
		try {
			Properties prop= new Properties();
			prop.load(new FileInputStream("src/resources/database.properties"));
			Class.forName(prop.getProperty("DRIVER")).newInstance();
			connections.add(DriverManager.getConnection(prop.getProperty("URL"), prop.getProperty("USER_NAME"), prop.getProperty("PASSWORD")));
			connectionCount.incrementAndGet();
			
		} catch (IOException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			log.error("Cannot get a connection for the moment",e);
			
		}

	}
	
	public Connection getConnection() throws InterruptedException {
		synchronized(ConnectionPool.class) {
			if(connections.size()==0 && connectionCount.get() < MAX_POOL) {
				initConnection();
			}	
		}
		return connections.take();
	}
	
	public void addConnection(Connection conection) {
		connections.offer(conection);
	}
	
	public void closeAll() {
		connections.forEach(connect -> {
			
			try {
				connect.close();
			} catch (SQLException e) {
				log.warn("Problem closing one connection", e);
			}
		});
		connections = new LinkedBlockingQueue<Connection>(MAX_POOL);
		connectionCount.set(0);	
	}
}
