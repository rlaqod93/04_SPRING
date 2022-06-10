package com.pcwk.ehr;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestUserService extends UserServiceImpl{
	
	final Logger LOG = LogManager.getLogger(this.getClass());
	
	//사용자 ID
	private String uId;
	
	public TestUserService() {
		
	}
	public TestUserService(String uId) {
		super();
		this.uId = uId;
		LOG.debug("===========================");
		LOG.debug("=uId=" + this.uId);
		LOG.debug("===========================");
	}
	
	/**
	 * 5명의 사용자
	 * 등업대상자는 2, 4번째
	 * 4번째에서 강제로 예외 발생!
	 */
	@Override
	public void upgradeLevel(UserVO user) throws SQLException, TestUserServiceException {
		if(this.uId.equals(user.getuId())) {
			LOG.debug("===========================");
			LOG.debug("=upgradeLevel=");
			LOG.debug("===========================");
			
			throw new TestUserServiceException("TestUserServiceException : " + uId);
		}
		super.upgradeLevel(user);
	}
}
