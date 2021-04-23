package com.solvd.projectSocialNetwork.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.projectSocialNetwork.dao.IPostsDAO;
import com.solvd.projectSocialNetwork.model.Multimedia;
import com.solvd.projectSocialNetwork.model.Posts;


public class PostsDAO extends MySQLDAO implements IPostsDAO{

	private final String GET_POSTS= "select * from posts where id=?";
	private final String REMOVE_POSTS= "delete from posts where id=?";
	private final String SAVE_POSTS= "insert into posts(message,referenceLink,userId) values(?,?,?)";
	private final String GET_POST_FROM_USER_ID= "select id from posts where id=?";
	private Logger log = LogManager.getLogger(PostsDAO.class);
	
	
	@Override
	public Posts save(Posts s) {
		Connection c = null;
		PreparedStatement pr  = null;
        try {
			c = cp.getConnection();
			pr = c.prepareStatement(SAVE_POSTS, Statement.RETURN_GENERATED_KEYS);
			pr.setString(1,s.getMassage());
			pr.setString(2,s.getReferenceLink());
			pr.setLong(3,s.getUserId());
			
			
			int rset = pr.executeUpdate();
			if(rset==1)
				log.info("Post saved");
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
	public Posts getById(long id) {
		Posts uf = null;
		Connection c = null;
		PreparedStatement pr  = null;
		ResultSet rset = null;
		try {
			c = cp.getConnection();
			pr = c.prepareStatement(GET_POSTS);
			pr.setLong(1,id);
			rset = pr.executeQuery();
			if (rset.next()) uf= new Posts(rset.getLong("id"),rset.getString("message"),rset.getString("referenceLink"),rset.getLong("userId"),new ArrayList<Multimedia>() );
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
        return uf;
	}

	@Override
	public void remove(long id) {
		Connection c = null;
		PreparedStatement pr = null;
        try {
			c = cp.getConnection();
			pr = c.prepareStatement(REMOVE_POSTS);
			pr.setLong(1,id);
			int rset = pr.executeUpdate();
			if(rset!=0)
				log.info("Post eliminated deleted");
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
	public long getPostFromUserId(long id) {
		long result = 0;
		Connection c = null;
		PreparedStatement pr = null;
		ResultSet rset = null;
        try {
			c = cp.getConnection();
			pr = c.prepareStatement(GET_POST_FROM_USER_ID);
			pr.setLong(1,id);
			rset = pr.executeQuery();
			if(rset.next()) 
				  result=(rset.getLong("id"));
			
			log.info("Post retrived");
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
