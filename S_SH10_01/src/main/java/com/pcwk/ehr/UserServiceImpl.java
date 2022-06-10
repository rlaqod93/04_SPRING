package com.pcwk.ehr;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl implements UserService {

	final Logger LOG = LogManager.getLogger(this.getClass());
	
	private UserDao userDao;
	
	public UserServiceImpl() {
		
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public void upgradeLevels(UserVO inVO) throws SQLException {
		
		List<UserVO> list = userDao.getAll(inVO);
		
		for(UserVO user : list) {
			boolean changeLevel = false;
			// BASIC -> SILVER(50회 이상 로그인)
			if(user.getLevel() == Level.BASIC && user.getLogin() >= 50) {
				changeLevel = true;
				user.setLevel(Level.SILVER);
			// SILVER -> GOLD(30번 이상 추천)
			}else if(user.getLevel() == Level.SILVER && user.getRecommend() >= 30) {
				changeLevel = true;
				user.setLevel(Level.GOLD);
			// GOLD는 대상이 아님
			}else if(user.getLevel() == Level.GOLD) {
				changeLevel = false;
			}else {
				changeLevel = false;
			}
			// 등업대상이면 update한다.
			if(changeLevel == true) {
				userDao.doUpdate(user);
			}
		}
	}

	@Override
	public int add(UserVO inVO) throws SQLException {
		
		if(inVO.getLevel() == null) {
			inVO.setLevel(Level.BASIC);
		}
		return this.userDao.doInsert(inVO);
	}
}
