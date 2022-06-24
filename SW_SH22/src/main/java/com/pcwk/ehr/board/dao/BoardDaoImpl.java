package com.pcwk.ehr.board.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pcwk.ehr.board.domain.BoardVO;
import com.pcwk.ehr.cmn.DTO;

@Repository("boardDao")
public class BoardDaoImpl implements BoardDao {
	
	final Logger LOG = LogManager.getLogger(this.getClass());
	
	//mybatis namespace
	final String NAVESPACE ="com.pcwk.ehr.board";
	
	//mybatis db연결객체
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
		
	public BoardDaoImpl() {}

	@Override
	public List<BoardVO> doRetrieve(DTO dto) throws SQLException {
		
		return null;
	}

	@Override
	public int doDelete(BoardVO inVO) throws SQLException {
		int flag = 0;
		String statement = NAVESPACE+".doDelete";
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" + statement);
		LOG.debug("==============================");
		
		flag = sqlSessionTemplate.delete(statement, inVO);
		LOG.debug("flag:" + flag);
		
		return flag;
	}

	@Override
	public int doUpdate(BoardVO inVO) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCount(BoardVO inVO) throws SQLException {
		int count = 0;
		String statement = NAVESPACE+".getCount";
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" + statement);
		LOG.debug("==============================");
		
		count = sqlSessionTemplate.selectOne(statement, inVO);
		LOG.debug("flag:" + count);
		
		return count;
	}

	@Override
	public int doInsert(BoardVO inVO) throws SQLException {
		int flag = 0;
		String statement = NAVESPACE+".doInsert";
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" + statement);
		LOG.debug("==============================");
		
		flag = sqlSessionTemplate.insert(statement, inVO);
		LOG.debug("flag:" + flag);
		
		return flag;
	}

	@Override
	public BoardVO doSelectOne(BoardVO inVO) throws SQLException {
		BoardVO outVO = null;
		String statement = NAVESPACE+".doSelectOne";
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" + statement);
		LOG.debug("==============================");
		
		outVO = sqlSessionTemplate.selectOne(statement, inVO);
		LOG.debug("outVO:" + outVO);
		
		return outVO;
	}


}
