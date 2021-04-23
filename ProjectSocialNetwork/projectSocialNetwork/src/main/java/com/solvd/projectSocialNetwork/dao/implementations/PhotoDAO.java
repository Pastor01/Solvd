package com.solvd.projectSocialNetwork.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.projectSocialNetwork.dao.IPhotoDAO;
import com.solvd.projectSocialNetwork.model.Audios;
import com.solvd.projectSocialNetwork.model.Photos;

public class PhotoDAO extends MySQLDAO implements IPhotoDAO{

	private final String GET_PHOTO= "select * from Photos where id=?";
	private final String REMOVE_PHOTO= "delete from Photos where id=?";
	private final String SAVE_PHOTO= "insert into Photos(resolution,multimediaId) values(?,?)";
	private final String GET_PHOTO_FROM_MULTIMEDIA_ID= "select id from videos where id=?";
	private Logger log = LogManager.getLogger(PhotoDAO.class);
	
	@Override
	public Photos save(Photos s) {
		Connection c = null;
		PreparedStatement pr= null;
		ResultSet rs=null;
		
		try {
			c=cp.getConnection();
			pr= c.prepareStatement(SAVE_PHOTO,Statement.RETURN_GENERATED_KEYS);
			pr.setString(1, s.getResolution());
			pr.setLong(2, s.getMultimediaId());
			
			int rset= pr.executeUpdate();
			
			if(rset == 1)
				log.info("Photo saved");
			
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
	public Photos getById(long id) {
		Photos a = null;
		Connection c = null;
		PreparedStatement pr=null;
		ResultSet rs= null;
		
		try {
			c=cp.getConnection();
			pr= c.prepareStatement(GET_PHOTO);
			pr.setLong(1, id);
			rs= pr.executeQuery();
			if(rs.next())
				a= new Photos(rs.getLong("id"),rs.getString("resolution"),rs.getLong("multimediaId"));
			
				
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
				pr= c.prepareStatement(REMOVE_PHOTO);
				pr.setLong(1, id);
				int rset= pr.executeUpdate();
				if(rset!=0)
					log.info("Photo deleted");
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
	public long getPhotoFromMultimediaId(long id) {
		long result = 0;
		Connection c = null;
		PreparedStatement pr = null;
		ResultSet rset = null;
        try {
			c = cp.getConnection();
			pr = c.prepareStatement(GET_PHOTO_FROM_MULTIMEDIA_ID);
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
