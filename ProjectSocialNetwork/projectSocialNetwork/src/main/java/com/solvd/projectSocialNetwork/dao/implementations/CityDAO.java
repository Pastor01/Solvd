package com.solvd.projectSocialNetwork.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.projectSocialNetwork.dao.ICityDAO;
import com.solvd.projectSocialNetwork.model.City;


public class CityDAO extends MySQLDAO implements ICityDAO{

	private final String GET_CITY= "select * from Cities where id=?";
	private final String REMOVE_CITY= "delete from Cities where id=?";
	private final String SAVE_CITY= "insert into Cities(name,id_country) values(?,?)";
	private final String GET_CITIES_FROM_COUNTRY_ID= "select * from Cities where id_country=?";
	
	private Logger log = LogManager.getLogger(CityDAO.class);
	
	@Override
	public City save(City c) {
		Connection con = null;
		PreparedStatement pre= null;
		try {
			con = cp.getConnection();
			pre = con.prepareStatement(SAVE_CITY, Statement.RETURN_GENERATED_KEYS);
			pre.setString(1,c.getName());
			pre.setLong(2,c.getIdCountry());
			int rset = pre.executeUpdate();
			if(rset==1)
				log.info("City saved");
            ResultSet rs = pre.getGeneratedKeys();
            if(rs.next())
            {
                c.setId(rs.getInt(1));
            }
		} catch (SQLException e) {
			log.error("SQL Exception, can not insert",e);
		} catch (InterruptedException e) {
			log.error("Cant get a connection",e);
		}finally{
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
	public City getById(long id) {
		City c = null;
		Connection con = null;
		PreparedStatement pre= null;
		ResultSet rset=null;
		try {
			con = cp.getConnection();
			pre = con.prepareStatement(GET_CITY);
			pre.setLong(1,id);
			rset = pre.executeQuery();
			if (rset.next()) c= new City(rset.getLong("id"),rset.getString("name"),rset.getLong("id_country"));
        } catch (SQLException e) {
			log.error("SQL Exception, can not get",e);
		} catch (InterruptedException e) {
			log.error("Cant get a connection",e);
		}finally{
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
	public void remove(long id) {
		Connection con = null;
		PreparedStatement pre= null;
        try {
			con = cp.getConnection();
			pre = con.prepareStatement(REMOVE_CITY);
			pre.setLong(1,id);
			int rset = pre.executeUpdate();
			if(rset!=0)
				log.info("City deleted");
		} catch (SQLException e) {
			log.error("SQL Exception, can not insert",e);
		} catch (InterruptedException e) {
			log.error("Cant get a connection",e);
		}finally{
			try {
				pre.close();
				cp.addConnection(con);
			} catch (SQLException e) {
				log.error("Cant close",e);
			}		
		}
		
	}

	@Override
	public ArrayList<City> getCitiesByCountryId(long id) {
		ArrayList<City> result = new ArrayList<City>();
		Connection con = null;
		PreparedStatement pre= null;
		ResultSet rset=null;
        try {
			con = cp.getConnection();
			pre = con.prepareStatement(GET_CITIES_FROM_COUNTRY_ID);
			pre.setLong(1,id);
			rset = pre.executeQuery();
			while (rset.next()) {
				  result.add(new City(rset.getLong("id"),rset.getString("name"),rset.getLong("id_country")));
			}
			log.info("Cities retrived");
		} catch (SQLException e) {
			log.error("SQL Exception, can not get",e);
		} catch (InterruptedException e) {
			log.error("Cant get a connection",e);
		}finally{
			try {
				pre.close();
				rset.close();
				cp.addConnection(con);
			} catch (SQLException e) {
				log.error("Cant close",e);
			}		
		}
        return result;

	}


}
