package com.pcwk.ehr.user.dao;

import java.sql.SQLException;
import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.SearchVO;
import com.pcwk.ehr.user.domain.Level;
import com.pcwk.ehr.user.domain.UserVO;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	final Logger LOG = LogManager.getLogger(this.getClass());
	
	// mybatis namespace
	final String NAMESPACE = "com.pcwk.ehr.user";
	
	// mybatis db연결객체
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public UserDaoImpl() {
		
	}

	@Override
	public int doUpdate(UserVO inVO) throws SQLException {
		int flag = 0;
		String statement = this.NAMESPACE + ".doUpdate";
		LOG.debug("============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement: " + statement);
		LOG.debug("============================");
		
		flag = sqlSessionTemplate.update(statement, inVO);
		LOG.debug("flag : " + flag);
		return flag;
	}
	
	@Override
	public List<UserVO> getAll(UserVO inVO){
		List<UserVO> list = null;
		String statement = this.NAMESPACE + ".getAll";
		LOG.debug("===========================");
		LOG.debug("=param : " + inVO.toString());
		LOG.debug("=statement : " + statement);
		LOG.debug("===========================");

		list = sqlSessionTemplate.selectList(statement, inVO);
		for(UserVO vo : list) {
			LOG.debug("vo : " + vo.toString());
		}
		return list;
	}
	
	
	// 총 건수
	@Override
	public int getCount(UserVO inVO) throws SQLException{
		int count = 0;
		
		String statement = this.NAMESPACE + ".getCount";
		
		LOG.debug("============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement: " + statement);
		LOG.debug("============================");

		count = this.sqlSessionTemplate.selectOne(statement, inVO);
		LOG.debug("============================");
		LOG.debug("count=" + count);
		LOG.debug("============================");
				
		return count;
	}
	
	/**
	 * 사용자 등록
	 * @param inVO
	 * @return 1(성공) / 0(실패)
	 * @throws ClassCastException
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	@Override
	public int doInsert(final UserVO inVO) throws SQLException{
		int flag = 0;
		
		String statement = NAMESPACE + ".doInsert";
		LOG.debug("============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" + statement);
		LOG.debug("============================");
		
		flag = this.sqlSessionTemplate.insert(statement, inVO);
		LOG.debug("flag : " + flag);
		
		return flag;
	}
	
	
	@Override
	public void deleteAll() throws SQLException{
		String statement = this.NAMESPACE + ".deleteAll";
		LOG.debug("============================");
		LOG.debug("statement: " + statement);
		LOG.debug("============================");
		
		sqlSessionTemplate.delete(statement);
	}
	
	/**
	 * 회원 단건 return
	 * @param inVO
	 * @return UserVO
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	@SuppressWarnings("deprecation")
	public UserVO doSelectOne(UserVO inVO) throws SQLException{
		UserVO outVO = null;
		
		String statement = this.NAMESPACE + ".doSelectOne";	
		LOG.debug("============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" + statement);
		LOG.debug("============================");
		
		outVO = this.sqlSessionTemplate.selectOne(statement, inVO);
		LOG.debug("============================");
		LOG.debug("**outVO=" + outVO.toString());
		LOG.debug("============================");
		
		return outVO;
	}

	@Override
	public int doDelete(UserVO inVO) throws SQLException {
		int flag = 0;
		String statement = NAMESPACE + ".doDelete";
		LOG.debug("============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" + statement);
		LOG.debug("============================");
		flag = this.sqlSessionTemplate.delete(statement, inVO);
		LOG.debug("flag = " + flag);
		return flag;
	}

	@Override
	public List<UserVO> doRetrieve(DTO dto) throws SQLException {
		SearchVO inVO = (SearchVO) dto;
		String statement = this.NAMESPACE + ".doRetrieve";
		LOG.debug("============================");
		LOG.debug("param:" + dto.toString());
		LOG.debug("statement:" + statement);
		LOG.debug("============================");
		List<UserVO> list = sqlSessionTemplate.selectList(statement, inVO);
		
		for(UserVO vo : list) {
			LOG.debug(vo);
		}
		return list;
	}

}