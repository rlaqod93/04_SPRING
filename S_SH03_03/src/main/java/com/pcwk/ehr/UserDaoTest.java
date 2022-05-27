package com.pcwk.ehr;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserDaoTest {

	static final Logger LOG = LogManager.getLogger(UserDaoTest.class);

	// 멤버변수
	UserDao dao;
	UserVO userVO;
	
	ApplicationContext context;
	
	public UserDaoTest() {
		context = new AnnotationConfigApplicationContext(DaoFactory.class);
		dao = context.getBean("userDao", UserDao.class);
		LOG.debug("==============================");
		LOG.debug("=context="+context);
		LOG.debug("=dao="+dao);
		LOG.debug("==============================");
		
		//dao = new DaoFactory().userDao();
		
		userVO = new UserVO("P04", "김병완", "7894");
	}

	// 단건조회
	public void get() throws SQLException, ClassNotFoundException {
		UserVO outVO = dao.get(userVO);
		if (null != outVO) {
			LOG.debug("==================");
			LOG.debug("=성공=");
			LOG.debug("==================");
		} else {
			LOG.debug("==================");
			LOG.debug("=실패=");
			LOG.debug("==================");
		}
	}

	// 사용자 등록
	public void add() throws SQLException, ClassNotFoundException {
		int flag = dao.add(userVO);
		if (1 == flag) {
			LOG.debug("==================");
			LOG.debug("=성공=");
			LOG.debug("==================");
		} else {
			LOG.debug("==================");
			LOG.debug("=실패=");
			LOG.debug("==================");
		}
	}

	public static void main(String[] args) {
		UserDaoTest main = new UserDaoTest();
		try {
			//main.add();
			main.get();
		} catch (ClassNotFoundException | SQLException e) {
			LOG.debug("======================");
			LOG.debug("=ClassNotFoundException | SQLException=" + e.getMessage());
			LOG.debug("======================");
			e.printStackTrace();
		}

	}

}
