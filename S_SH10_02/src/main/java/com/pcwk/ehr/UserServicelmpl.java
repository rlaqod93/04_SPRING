package com.pcwk.ehr;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionSynchronization;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class UserServicelmpl implements UserService {
	
	final Logger LOG = LogManager.getLogger(this.getClass());
	// 상수 도입 : 30, 50
	// BASIC -> SILVER로 가는 최소 로그인수
	public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
	
	// SILVER -> GOLD로 가는 추천수
	public static final int MIN_RECOMMEND_FOR_GOLD = 30;
	
	private UserDao userDao;
	
	private DataSource dataSource;
	
	// 외부에서 DI할수 있도록 : JDBC 트랙잭션 추상 OBJECT
	private PlatformTransactionManager transactionManager;

	// mail
	private JavaMailSenderImpl mailSender;
	
	public UserServicelmpl() {
		
	}
	
	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	
	@Override
	public void upgradeLevels(UserVO inVO) throws SQLException {
		// 트랜잭션 시작
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			List<UserVO> list = userDao.getAll(inVO);
		
			for(UserVO user : list) {
				if(canUpgradeLevel(user) == true) {
					upgradeLevel(user);
				}
			}
			// 정상적으로 완료되면 트랜잭션 commit
			transactionManager.commit(status);
		}catch(Exception e) {
			LOG.debug("================");
			LOG.debug("=rollback******=");
			LOG.debug("================");
			transactionManager.rollback(status);
			throw e;
		}
	}
	
	/**
	 * 레벨 업그레이드 작업
	 * @param user
	 * @throws SQLException 
	 */
	public void upgradeLevel(UserVO user) throws SQLException {
		// 다음레벨로 up
		user.upgradeLevel();
		this.userDao.doUpdate(user);
		
		// 등업되면 mail 전송
		sendupgradeMail(user);
	}
	
	/**
	 * 등업되면 메일 전송
	 * BASIC -> SILVER : 2번째
	 * SILVER -> GOLD : 4번째
	 * @param user
	 */
	public void sendupgradeMail(UserVO user) {
		try {
			LOG.debug("======================");
			LOG.debug("=sendupgradeMail=" + user.getEmail());
			LOG.debug("======================");
			
			MimeMessage mail = this.mailSender.createMimeMessage();
			MimeMessageHelper mailHelper = new MimeMessageHelper(mail, "UTF-8");
			
			mailHelper.setTo(user.getEmail()); // 받는 사람
			mailHelper.setSubject("등업 안내");
			mailHelper.setText("사용자의 등급이 " + user.getLevel() + "로 업그레이드 되었습니다.");
			mailSender.send(mail);
			
		}catch(MessagingException e) {
			LOG.debug("======================");
			LOG.debug("=MessagingException=" + e.getMessage());
			LOG.debug("======================");
		}
	}
	
	
	/**
	 * User가 업그레이드 대상인지 확인
	 * @param user
	 * @return 대상(true) / 대상이 아니면(false)
	 */
	private boolean canUpgradeLevel(UserVO user) {
		Level currentLevel = user.getLevel();
		
		switch(currentLevel) {
		case BASIC:
			return (user.getLogin() >= MIN_LOGCOUNT_FOR_SILVER);
		case SILVER:
			return (user.getRecommend() >= MIN_RECOMMEND_FOR_GOLD);
		case GOLD:
			return false;
		default:
			throw new IllegalAccessError("Unknown Level : " + currentLevel);
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