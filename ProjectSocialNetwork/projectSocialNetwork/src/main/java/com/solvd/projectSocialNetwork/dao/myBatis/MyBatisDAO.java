package com.solvd.projectSocialNetwork.dao.myBatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public abstract class MyBatisDAO {

	protected SqlSessionFactory sqlSessionFactory;
	private Logger log = LogManager.getLogger(MyBatisDAO.class);

	public MyBatisDAO() {

		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream("mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			
			log.error(e);
		}
		
	}
	
}
