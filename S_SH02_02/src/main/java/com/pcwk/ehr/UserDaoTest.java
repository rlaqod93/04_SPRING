package com.pcwk.ehr;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDaoTest {
	
	static final Logger LOG = LogManager.getLogger(UserDaoTest.class);
	
	UserDao dao;
	UserVO userVo;
	public UserDaoTest() {
		dao = new NUserDao();
		userVo = new UserVO("p03", "김동호", "1658");
	}
	
	public void get() throws SQLException, ClassNotFoundException{
		UserVO outVO = dao.get(userVo);
		if(outVO != null) {
			LOG.debug("========================");
			LOG.debug("=성공=");
			LOG.debug("========================");
		}else {
			LOG.debug("========================");
			LOG.debug("=실패=");
			LOG.debug("========================");
		}
	}
	
	public void add() throws SQLException, ClassNotFoundException{
		int flag = dao.add(userVo);
		if(flag == 1) {
			LOG.debug("========================");
			LOG.debug("=성공=");
			LOG.debug("========================");
		}else {
			LOG.debug("========================");
			LOG.debug("=실패=");
			LOG.debug("========================");
		}
	}
	
	public static void main(String[] args) {
		UserDaoTest main = new UserDaoTest();
		try {
			main.add();
			main.get();
		} catch (ClassNotFoundException | SQLException e) {
			LOG.debug("========================");
			LOG.debug("=ClassNotFoundException | SQLException=" + e.getMessage());
			LOG.debug("========================");
			e.printStackTrace();
		}
	}
}
