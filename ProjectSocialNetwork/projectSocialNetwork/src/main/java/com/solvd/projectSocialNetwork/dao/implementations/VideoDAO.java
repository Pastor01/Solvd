package com.solvd.projectSocialNetwork.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.projectSocialNetwork.dao.IVideosDAO;
import com.solvd.projectSocialNetwork.model.Videos;

public class VideoDAO extends MySQLDAO implements IVideosDAO{

	private final String GET_VIDEO= "select * from Videos where id=?";
	private final String REMOVE_VIDEO= "delete from Videos where id=?";
	private final String SAVE_VIDEO= "insert into Videos(duration,contents,multimediaId) values(?,?,?)";
	private final String GET_VIDEO_FROM_MULTIMEDIA_ID= "select id from videos where id=?";
	private Logger log = LogManager.getLogger(VideoDAO.class);
	
	@Override
	public Videos save(Videos s) {
		Connection c = null;
		PreparedStatement pr= null;
		ResultSet rs=null;
		
		try {
			c=cp.getConnection();
			pr= c.prepareStatement(SAVE_VIDEO,Statement.RETURN_GENERATED_KEYS);
			pr.setDouble(1, s.getDuration());
			pr.setString(2, s.getContests());
			pr.setLong(3, s.getMultimediaId());
			
			int rset= pr.executeUpdate();
			
			if(rset == 1)
				log.info("Video saved");
			
			rs = pr.getGeneratedKeys();
			if(rs.next()) {
				s.setId(rs.getLong(1));
			}
			
			
		} catch (InterruptedException e) {
			log.error("Cant get a connection", e);
		} catch (SQLException e) {
			log.error("SQL exception, can not insert",e);
			
		}finally {
			
			try {
				pr.close();
				rs.close();
				cp.addConnection(c);
			} catch (SQLException e) {
				log.error("Cant close",e);
			}
		}
		
		return s;
	}

	@Override
	public Videos getById(long id) {
		Videos a = null;
		Connection c = null;
		PreparedStatement pr=null;
		ResultSet rs= null;
		
		try {
			c=cp.getConnection();
			pr= c.prepareStatement(GET_VIDEO);
			pr.setLong(1, id);
			rs= pr.executeQuery();
			if(rs.next())
				a= new Videos(rs.getLong("id"),rs.getDouble("duration"),rs.getString("contents"),rs.getLong("multimediaId"));
			
			
		} catch (InterruptedException e) {
			log.error("Cant get a connection", e);
		} catch (SQLException e) {
			log.error("SQL Exception, cant not get the element", e);
		}finally {
			
			try {
				pr.close();
				rs.close();
				cp.addConnection(c);
			} catch (SQLException e) {
				log.error("Cant close",e);
			}
		}

		return a;
	}

	@Override
	public void remove(long id) {
		Connection c = null;
		PreparedStatement pr=null;
		
		try {
			c= cp.getConnection();
			pr= c.prepareStatement(REMOVE_VIDEO);
			pr.setLong(1, id);
			int rset= pr.executeUpdate();
			if(rset!=0)
				log.info("Audio deleted");
		} catch (InterruptedException e) {
			log.error("Can not get a connection", e);
		} catch (SQLException e) {
			log.error("SQL exception, cant insert",e);
		}finally {
			try {
				pr.close();
				cp.addConnection(c);
			} catch (SQLException e) {
				log.error("Cant close",e);
			}
		}
		
	}

	@Override
	public long getVideoFromMultimediaId(long id) {
		long result = 0;
		Connection c = null;
		PreparedStatement pr = null;
		ResultSet rset = null;
        try {
			c = cp.getConnection();
			pr = c.prepareStatement(GET_VIDEO_FROM_MULTIMEDIA_ID);
			pr.setLong(1,id);
			rset = pr.executeQuery();
			if(rset.next()) 
				  result=(rset.getLong("id"));
			
			log.info("Video retrived");
		} catch (SQLException e) {
			log.error("SQL Exception, can not get",e);
		} catch (InterruptedException e) {
			log.error("Cant get a connection",e);
		}finally{
			try {
				pr.close();
				rset.close();
				cp.addConnection(c);
			} catch (SQLException e) {
				log.error("Cant close",e);
			}		
		}
        return result;
	}

}
