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

import com.solvd.projectSocialNetwork.dao.IMultimediaDAO;
import com.solvd.projectSocialNetwork.model.Multimedia;
import com.solvd.projectSocialNetwork.model.UserFriends;

public class MultimediaDAO extends MySQLDAO implements IMultimediaDAO{

	private final String GET_MULTIMEDIA= "select * from ultimedia where id=?";
	private final String REMOVE_MULTIMEDIA= "delete from multimedia where id=?";
	private final String SAVE_MULTIMEDIA= "insert into multimedia(type,name,link,postId,metaInfo) values(?,?,?,?,?)";
	private final String GET_MULTIMEDIA_FROM_POST_ID= "select id from multimedia where id=?";
	private Logger log = LogManager.getLogger(MultimediaDAO.class);
	
	
	@Override
	public Multimedia save(Multimedia s) {
		Connection c = null;
		PreparedStatement pr  = null;
        try {
			c = cp.getConnection();
			pr = c.prepareStatement(SAVE_MULTIMEDIA, Statement.RETURN_GENERATED_KEYS);
			pr.setInt(1,s.getType());
			pr.setString(2,s.getName());
			pr.setString(3,s.getLink());
			pr.setLong(4,s.getPostId());
			pr.setString(5,s.getMetaInfo());
			
			
			int rset = pr.executeUpdate();
			if(rset==1)
				log.info("Multmedia saved");
            ResultSet rs = pr.getGeneratedKeys();
            if(rs.next())
            {
                s.setId(rs.getInt(1));
            }
		} catch (SQLException e) {
			log.error("SQL Exception, can not insert",e);
		} catch (InterruptedException e) {
			log.error("Cant get a connection",e);
		}finally{
			try {
				pr.close();
				cp.addConnection(c);
			} catch (SQLException e) {
				log.error("Cant close",e);
			}		
		}
        return s;
	}

	@Override
	public Multimedia getById(long id) {
		Multimedia mu = null;
		Connection c = null;
		PreparedStatement pr  = null;
		ResultSet rset = null;
		try {
			c = cp.getConnection();
			pr = c.prepareStatement(GET_MULTIMEDIA);
			pr.setLong(1,id);
			rset = pr.executeQuery();
			if (rset.next()) mu= new Multimedia(rset.getLong("id"),rset.getString("type").charAt(0),rset.getString("name"),rset.getString("link"),rset.getLong("postId"),rset.getString("metaInfo"));
        } catch (SQLException e) {
			log.error("SQL Exception, can not get",e);
		} catch (InterruptedException e) {
			log.error("Cant get a connection",e);
		}finally{
			try {
				pr.close();
				cp.addConnection(c);
			} catch (SQLException e) {
				log.error("Cant close",e);
			}		
		}
        return mu;
	}

	@Override
	public void remove(long id) {
		Connection c = null;
		PreparedStatement pr = null;
        try {
			c = cp.getConnection();
			pr = c.prepareStatement(REMOVE_MULTIMEDIA);
			pr.setLong(1,id);
			int rset = pr.executeUpdate();
			if(rset!=0)
				log.info("Multimedia eliminated deleted");
		} catch (SQLException e) {
			log.error("SQL Exception, can not insert",e);
		} catch (InterruptedException e) {
			log.error("Cant get a connection",e);
		}finally{
			try {
				pr.close();
				cp.addConnection(c);
			} catch (SQLException e) {
				log.error("Cant close",e);
			}		
		}
	}

	@Override
	public List<Multimedia> getMultimediaFromPostId(long id) {
		List<Multimedia> result = new ArrayList<Multimedia>();
		Connection c = null;
		PreparedStatement pr = null;
		ResultSet rset = null;
        try {
			c = cp.getConnection();
			pr = c.prepareStatement(GET_MULTIMEDIA_FROM_POST_ID);
			pr.setLong(1,id);
			rset = pr.executeQuery();
			while (rset.next()) {
				  result.add(new Multimedia(rset.getLong("id"),rset.getString("type").charAt(0),rset.getString("name"),rset.getString("link"),rset.getLong("postId"),rset.getString("metaInfo")));
			}
			log.info("Multimedia retrived");
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
