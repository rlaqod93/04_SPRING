/**
* <pre>
* com.pcwk.ehr.board.controller
* Class Name : BoardController.java
* Description: 게시 컨트롤러
* Author: ITSC
* Since: 2022/06/27
* Version 0.1
* Copyright (C) by KandJang All right
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2022/06/27 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.pcwk.ehr.board.controller;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pcwk.ehr.board.domain.BoardVO;
import com.pcwk.ehr.board.service.BoardService;
import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.SearchVO;
import com.pcwk.ehr.cmn.StringUtil;

/**
 * @author ITSC
 *
 */
@Controller("boardController")
@RequestMapping("board")
public class BoardController {

	final Logger LOG = LogManager.getLogger(getClass());
	
	@Autowired
	BoardService boardService;
	
	public BoardController() {}
	
	
	@RequestMapping(value = "/doRetrieve.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody//스프링에서 비동기 처리를 하는 경우,HTTP 요청의 분문 body 부분이 그대로 전달된다.
	public String doRetrieve(SearchVO inVO) throws SQLException {
		String jsonString = "";
		LOG.debug("========================");
		LOG.debug("=doRetrieve()=");
		LOG.debug("=inVO="+inVO);
		LOG.debug("========================");			
		
		//페이지사이즈
		if(0==inVO.getPageSize()) {
			inVO.setPageSize(10);
		}
		
		//페이지 번호
		if(0==inVO.getPageNum()) {
			inVO.setPageNum(1);
		}
		
		//검색구분
		if(null == inVO.getSearchDiv()) {
			inVO.setSearchDiv(StringUtil.nvl(inVO.getSearchDiv(), ""));
		}
		
		
		//검색어
		if(null == inVO.getSearchWord()) {
			inVO.setSearchWord(StringUtil.nvl(inVO.getSearchWord(),""));
		}
		
		//게시구분
		if(null == inVO.getDiv()) {
			inVO.setDiv(StringUtil.nvl(inVO.getDiv() ,"10"));
		}		
		
		LOG.debug("==============================");
		LOG.debug("=inVO="+inVO);
		LOG.debug("==============================");	
		
		List<BoardVO> list = boardService.doRetrieve(inVO);
		Gson gson=new Gson();
		
		jsonString = gson.toJson(list);
		
		LOG.debug("==============================");
		LOG.debug("=jsonString="+jsonString);
		LOG.debug("==============================");	
		
		return jsonString;
	}
	
	
	//board/board_list
	//board/board_reg
	//board/board_mod
	@RequestMapping(value="/boardView.do",method=RequestMethod.GET)
	public String boardView(Model model, SearchVO inVO) throws SQLException {
		LOG.debug("========================");
		LOG.debug("=boardView()=");
		LOG.debug("========================");		
		
		//페이지사이즈
		if(0==inVO.getPageSize()) {
			inVO.setPageSize(10);
		}
		
		//페이지 번호
		if(0==inVO.getPageNum()) {
			inVO.setPageNum(1);
		}
		
		//검색구분
		if(null == inVO.getSearchDiv()) {
			inVO.setSearchDiv(StringUtil.nvl(inVO.getSearchDiv(), ""));
		}
		
		
		//검색어
		if(null == inVO.getSearchWord()) {
			inVO.setSearchWord(StringUtil.nvl(inVO.getSearchWord(),""));
		}
		
		//게시구분
		if(null == inVO.getDiv()) {
			inVO.setDiv(StringUtil.nvl(inVO.getDiv() ,"10"));
		}
		
		LOG.debug("==============================");
		LOG.debug("=inVO="+inVO);
		LOG.debug("==============================");		
		
		
		List<BoardVO> list = boardService.doRetrieve(inVO);
		
		model.addAttribute("list", list);
		model.addAttribute("vo", inVO);
		
		return "board/board_list";
		
	}
	
	
	
	
	
	

	
	
}
