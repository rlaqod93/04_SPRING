/**
* <pre>
* com.pcwk.ehr.board
* Class Name : JUnitBoardController.java
* Description: 게시판 컨트롤러 테스트
* Author: ITSC
* Since: 2022/06/27
* Version 0.1
* Copyright (C) by KandJang All right reserved.
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2022/06/27 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.pcwk.ehr.board;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pcwk.ehr.board.dao.BoardDao;
import com.pcwk.ehr.board.domain.BoardVO;
import com.pcwk.ehr.cmn.SearchVO;

/**
 * @author ITSC
 *
 */

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) // JUnit기능을 스프링 프레임으로 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
								   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"}) // applicationContext.xml loading
public class JUnitBoardController {

	final Logger LOG = LogManager.getLogger(this.getClass());
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	//브라우저 대역(Mock)
	MockMvc mockMvc;
	
	@Autowired
	BoardDao boardDao;
	
	BoardVO board01;
	BoardVO board02;
	BoardVO board03;
	
	SearchVO search;
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("========================");
		LOG.debug("=0.setUp()=");
		LOG.debug("========================");
		search = new SearchVO(10, 1, "", "", "10");
		
		board01 = new BoardVO(30, "제목_30", "내용_30", 0, "10", "날짜_사용안함", "김동호", "날짜_사용안함", "김동호");
		board02 = new BoardVO(300, "제목_300", "내용_300", 0, "10", "날짜_사용안함", "김동호", "날짜_사용안함", "김동호");
		board03 = new BoardVO(3000, "제목_3000", "내용_3000", 0, "10", "날짜_사용안함", "김동호", "날짜_사용안함", "김동호");
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		LOG.debug("mockMvc : " + mockMvc);
		LOG.debug("boardDao : " + boardDao);
		LOG.debug("webApplicationContext : " + webApplicationContext);
		
		assertNotNull(webApplicationContext);
		assertNotNull(mockMvc);
		assertNotNull(boardDao);
	}

	@Test
	public void doRetrieve() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = 
				MockMvcRequestBuilders.get("/board/doRetrieve.do")
				.param("pageSize"  , String.valueOf(search.getPageSize()))
				.param("pageNum"   , String.valueOf(search.getPageNum()))
				.param("searchDiv" , search.getSearchDiv())
				.param("searchWord", search.getSearchWord())
				.param("div", search.getDiv());
				
		// 대역 객체 통해 호출
		ResultActions resultActions = mockMvc.perform(requestBuilder)
				.andExpect(status().is2xxSuccessful());
		
		String result = resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
		LOG.debug("========================");
		LOG.debug("result : " + result);
		LOG.debug("========================");
		
		//jsonString to List<UserVO>
		Gson gson = new Gson();
		// gson List<BoardVO> jsonString -> List<UserVO>
		List<BoardVO> list = gson.fromJson(result, new TypeToken<List<BoardVO>>() {}.getType());
		for(BoardVO vo : list) {
			LOG.debug("vo = " + vo);
		}
		
	}
}
