package com.solvd.projectSocialNetwork.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.projectSocialNetwork.dao.IAudioDAO;
import com.solvd.projectSocialNetwork.model.Audios;

public class AudiosDAO extends MySQLDAO implements IAudioDAO {

	private final String GET_AUDIO= "select * from Audios where id=?";
	private final String REMOVE_AUDIO= "delete from Audios where id=?";
	private final String SAVE_AUDIO= "insert into Audios(duration,multimediaId) values(?,?)";
	private final String GET_AUDIO_FROM_MULTIMEDIA_ID= "select id from videos where id=?";
	private Logger log = LogManager.getLogger(AudiosDAO.class);
	
	@Override
	public Audios save(Audios s) {
		Connection c = null;
		PreparedStatement pr= null;
		ResultSet rs=null;
		
		try {
			c=cp.getConnection();
			pr= c.prepareStatement(SAVE_AUDIO,Statement.RETURN_GENERATED_KEYS);
			pr.setDouble(1, s.getDuration());
			pr.setLong(2, s.getMultimediaId());
			
			int rset= pr.executeUpdate();
			
			if(rset == 1)
				log.info("Audio saved");
			
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
	public Audios getById(long id) {
		Audios a = null;
		Connection c = null;
		PreparedStatement pr=null;
		ResultSet rs= null;
		
		try {
			c=cp.getConnection();
			pr= c.prepareStatement(GET_AUDIO);
			pr.setLong(1, id);
			rs= pr.executeQuery();
			if(rs.next())
				a= new Audios(rs.getLong("id"),rs.getDouble("duration"),rs.getLong("multimediaId"));
			
			
			
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
			pr= c.prepareStatement(REMOVE_AUDIO);
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
	public long getAudioFromMultimediaId(long id) {
		long result = 0;
		Connection c = null;
		PreparedStatement pr = null;
		ResultSet rset = null;
        try {
			c = cp.getConnection();
			pr = c.prepareStatement(GET_AUDIO_FROM_MULTIMEDIA_ID);
			pr.setLong(1,id);
			rset = pr.executeQuery();
			if(rset.next()) 
				  result=(rset.getLong("id"));
			
			log.info("Audio retrived");
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
