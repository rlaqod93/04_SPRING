package com.pcwk.ehr.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pcwk.ehr.board.dao.BoardDao;
import com.pcwk.ehr.board.domain.BoardVO;

@RunWith(SpringJUnit4ClassRunner.class) //JUnit기능을 스프링 프레임으로 확장!
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		                           "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
}) //applicationContext.xml loading
public class JunitBoardDaoTest {
	final Logger LOG = LogManager.getLogger(this.getClass());
	
	@Autowired  //컨텍스트 프레임워크는 변수 타입과 일치하는 컨텍스트 내의 빈을 찾고, 변수에 주입
	ApplicationContext context;
	
	@Autowired
	BoardDao dao;
	
	BoardVO board01;
	BoardVO board02;
	BoardVO board03;
	
	@Before
	public void setUp() throws Exception {
		  LOG.debug("====================");
		  LOG.debug("=0.setUp()=");
		  LOG.debug("====================");
		  
		  board01 = new BoardVO(04,"제목_04","내용_04", 0,"10","날짜_사용않함","김병완","날짜_사용않함","김병완");
		  board02 = new BoardVO(040,"제목_040","내용_04", 0,"10","날짜_사용않함","김병완","날짜_사용않함","김병완");
		  board03 = new BoardVO(0400,"제목_0400","내용_04", 0,"10","날짜_사용않함","김병완","날짜_사용않함","김병완");
				  
		  
		  LOG.debug("context:"+context);
		  LOG.debug("dao:"+dao);
		  
		  assertNotNull(context);
		  assertNotNull(dao);
		  //null이 아닌지를 확인!
	}


	@Test
	public void addAndGet() throws SQLException {
		  LOG.debug("====================");
		  LOG.debug("=1.addAndGet()=");
		  LOG.debug("====================");
		  
		  //1.삭제
		  dao.doDelete(board01);
		  dao.doDelete(board02);
		  dao.doDelete(board03);
		  
		  //2.등록
		  dao.doInsert(board01);
		  
		  //3.단건조회
		  BoardVO outVO = dao.doSelectOne(board01);
		  
		  //4.비교
		  isSameData(outVO, board01);
		  
		  dao.doInsert(board02);
		  BoardVO outVO2 = dao.doSelectOne(board02);
		  isSameData(outVO2, board02);
		  

	}	
	
	private void isSameData(BoardVO voVO,BoardVO orgVO) {
		assertEquals(voVO.getSeq(), orgVO.getSeq());
		assertEquals(voVO.getTitle(), orgVO.getTitle());
		assertEquals(voVO.getContents(), orgVO.getContents());
		assertEquals(voVO.getReadCnt(), orgVO.getReadCnt());
		assertEquals(voVO.getDiv(), orgVO.getDiv());
		assertEquals(voVO.getRegId(), orgVO.getRegId());
		assertEquals(voVO.getModId(), orgVO.getModId());
		
	}
	

}
