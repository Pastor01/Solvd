package com.solvd.projectSocialNetwork.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.projectSocialNetwork.dao.ICountryDAO;
import com.solvd.projectSocialNetwork.model.City;
import com.solvd.projectSocialNetwork.model.Country;


public class CountryDAO extends MySQLDAO implements ICountryDAO{
	
	private final String GET_COUNTRY= "select * from Countries where id=?";
	private final String REMOVE_COUNTRY= "delete from Countries where id=?";
	private final String SAVE_COUNTRY= "insert into Countries(name) values(?)";
	private Logger log = LogManager.getLogger(CountryDAO.class);
	
	@Override
	public Country save(Country c) {
		Connection con = null;
		PreparedStatement pre= null;		
        try {
			con = cp.getConnection();
			pre = con.prepareStatement(SAVE_COUNTRY, Statement.RETURN_GENERATED_KEYS);
			pre.setString(1,c.getName());
			int rset = pre.executeUpdate();
			if(rset==1)
				log.info("Country saved");
            ResultSet rs = pre.getGeneratedKeys();
            if(rs.next())
            {
                c.setId(rs.getInt(1));
            }
		} catch (SQLException e) {
			log.error("SQL Exception, can not insert",e);
		} catch (InterruptedException e) {
			log.error("Cant get a connection",e);
		}finally {
			try {
				pre.close();
				cp.addConnection(con);
			} catch (SQLException e) {
				log.error("Cant close",e);
			}		
		}
        return c;
	}

	@Override
	public Country getById(long id) {
        Country c = null;
		Connection con = null;
		PreparedStatement pre= null;
		ResultSet rset=null;
		try {
			con = cp.getConnection();
			pre = con.prepareStatement(GET_COUNTRY);
			pre.setLong(1,id);
			rset = pre.executeQuery();
			if (rset.next()) c= new Country(rset.getLong("id"),rset.getString("name"),new ArrayList<City>());
			

		} catch (SQLException e) {
			log.error("SQL Exception, can not get",e);
		} catch (InterruptedException e) {
			log.error("Cant get a connection",e);
		}finally {
			try {
				pre.close();
				rset.close();
				cp.addConnection(con);
			} catch (SQLException e) {
				log.error("Cant close",e);
			}		
		}
        return c;
	}

	@Override
	public void remove(long id) {
        Connection con = null;
		PreparedStatement pre= null;

        try {
			con = cp.getConnection();
			pre = con.prepareStatement(REMOVE_COUNTRY);
			pre.setLong(1,id);
			int rset = pre.executeUpdate();
			if(rset!=0)
				log.info("Country deleted");

		} catch (SQLException e) {
			log.error("SQL Exception, can not insert",e);
		} catch (InterruptedException e) {
			log.error("Cant get a connection",e);
		}finally {
			try {
				pre.close();
				cp.addConnection(con);
			} catch (SQLException e) {
				log.error("Cant close",e);
			}		

		}
		
	}

}