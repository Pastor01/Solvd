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

import com.solvd.projectSocialNetwork.dao.IUserFriendsDAO;

import com.solvd.projectSocialNetwork.model.UserFriends;

public class UserFriendsDAO extends MySQLDAO implements IUserFriendsDAO{

	private final String GET_FRIENDS= "select * from userFriends where id=?";
	private final String REMOVE_FRIENDS= "delete from userFriends where id=?";
	private final String SAVE_FRIENDS= "insert into userFriends(friendType,createdAt,status,notes,sourceId,targetId) values(?,?,?,?,?,?)";
	private final String GET_FRIENDS_FROM_USER_ID= "select targetId from userFriends where id=?";
	private Logger log = LogManager.getLogger(UserFriendsDAO.class);
	
	
	
	@Override
	public UserFriends save(UserFriends s) {
		Connection c = null;
		PreparedStatement pr  = null;
        try {
			c = cp.getConnection();
			pr = c.prepareStatement(SAVE_FRIENDS, Statement.RETURN_GENERATED_KEYS);
			pr.setInt(1,s.getFriendType());
			pr.setDate(2,s.getCreatedAt());
			pr.setInt(3,s.getStatus());
			pr.setString(4,s.getNotes());
			pr.setLong(5,s.getSourceId());
			pr.setLong(6,s.getTargetId());
			
			
			int rset = pr.executeUpdate();
			if(rset==1)
				log.info("UserFriends saved");
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
	public UserFriends getById(long id) {
		UserFriends uf = null;
		Connection c = null;
		PreparedStatement pr  = null;
		ResultSet rset = null;
		try {
			c = cp.getConnection();
			pr = c.prepareStatement(GET_FRIENDS);
			pr.setLong(1,id);
			rset = pr.executeQuery();
			if (rset.next()) uf= new UserFriends(rset.getLong("id"),rset.getInt("friendType"),rset.getInt("status"),rset.getString("notes"),rset.getDate("createdAt"),rset.getLong("sourceId"),rset.getLong("targetId"));
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
			pr = c.prepareStatement(REMOVE_FRIENDS);
			pr.setLong(1,id);
			int rset = pr.executeUpdate();
			if(rset!=0)
				log.info("Friend eliminated deleted");
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
	public List<Long> getFriendFromUserId(long id) {
		List<Long> result = new ArrayList<Long>();
		Connection c = null;
		PreparedStatement pr = null;
		ResultSet rset = null;
        try {
			c = cp.getConnection();
			pr = c.prepareStatement(GET_FRIENDS_FROM_USER_ID);
			pr.setLong(1,id);
			rset = pr.executeQuery();
			while (rset.next()) {
				  result.add(rset.getLong("targetId"));
			}
			log.info("UserFriends retrived");
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
