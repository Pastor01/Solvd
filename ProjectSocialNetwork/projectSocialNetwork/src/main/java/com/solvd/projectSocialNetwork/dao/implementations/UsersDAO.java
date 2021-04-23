package com.solvd.projectSocialNetwork.dao.implementations;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.projectSocialNetwork.dao.IUsersDAO;
import com.solvd.projectSocialNetwork.model.Posts;
import com.solvd.projectSocialNetwork.model.Users;

public class UsersDAO extends MySQLDAO implements IUsersDAO{

	private final String GET_USER= "select * from Users where id=?";
	private final String REMOVE_USER= "delete from Users where id=?";
	private final String SAVE_USER= "insert into Users(name,lastName,userName,registerAt,mobile) values(?,?,?,?,?)";
	private Logger log = LogManager.getLogger(UsersDAO.class);
	
	@Override
	public Users save(Users s) {
		Connection c = null;
		PreparedStatement pr= null;
		ResultSet rs=null;
		
		try {
			c=cp.getConnection();
			pr= c.prepareStatement(SAVE_USER,Statement.RETURN_GENERATED_KEYS);
			pr.setString(1, s.getName());
			pr.setString(2, s.getLastName());
			pr.setString(3, s.getUserName());
			pr.setDate(4, s.getRegisterAt());
			pr.setInt(5, s.getMobile());
			
			int rset= pr.executeUpdate();
			
			if(rset == 1)
				log.info("User saved");
			
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
	public Users getById(long id) {
		Users p = null;
		Connection c = null;
		PreparedStatement pr = null;
		ResultSet rset = null;
        try {
			c = cp.getConnection();
			pr = c.prepareStatement(GET_USER);
			pr.setLong(1,id);
			rset = pr.executeQuery();
			if (rset.next())
				p= new Users(rset.getLong("id"),rset.getString("name"),rset.getString("lastName"),rset.getString("userName"),rset.getDate("registerAt"),rset.getInt("mobile"),new ArrayList<Posts>(),new ArrayList<Users>());
					
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
        return p;
	}

	@Override
	public void remove(long id) {
		Connection c = null;
		PreparedStatement pr = null;

        try {
			c = cp.getConnection();
			pr = c.prepareStatement(REMOVE_USER);
			pr.setLong(1,id);
			int rset = pr.executeUpdate();
			if(rset!=0)
				log.info("User deleted");
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
		
}



