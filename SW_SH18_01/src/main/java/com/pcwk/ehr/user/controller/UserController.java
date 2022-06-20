package com.pcwk.ehr.user.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.MessageVO;
import com.pcwk.ehr.user.domain.UserVO;
import com.pcwk.ehr.user.service.UserService;

@Controller("userController")   
@RequestMapping("user")  //모든 메소드 user+/doSelectOne.do
public class UserController {   

	final Logger LOG = LogManager.getLogger(getClass());
	
	@Autowired
	UserService userService;
	
	public UserController() {}
	
	@RequestMapping(value = "/doUpdata.do",method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody//스프링에서 비동기 처리를 하는 경우,HTTP 요청의 분문 body 부분이 그대로 전달된다.
	public String doUpdate(UserVO inVO) throws SQLException{
		String jsonString = "";
		LOG.debug("==============================");
		LOG.debug("=inVO="+inVO);
		LOG.debug("==============================");
		
		String resultMsg ="";
		int flag = userService.doUpdate(inVO);
		if(1==flag) {
			resultMsg = inVO.getuId()+"가 수정 되었습니다.";
		}else {
			resultMsg = inVO.getuId()+"가 수정 실패!";
		}
		
		MessageVO message = new MessageVO(String.valueOf(flag),resultMsg);
		Gson gson = new Gson();
		jsonString = gson.toJson(message);
		LOG.debug("==============================");
		LOG.debug("=jsonString="+jsonString);
		LOG.debug("==============================");	
		
		return jsonString;
	}
	
	@RequestMapping(value = "/add.do",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	@ResponseBody//스프링에서 비동기 처리를 하는 경우,HTTP 요청의 분문 body 부분이 그대로 전달된다.
	public String add(UserVO inVO) throws SQLException{
		String jsonString = "";
		LOG.debug("==============================");
		LOG.debug("=inVO="+inVO);
		LOG.debug("==============================");
		String resultMsg = "";
		
		int flag = userService.add(inVO);
		String resultMessage = "";
		if(1==flag) {//등록 성공
			resultMessage = inVO.getuId()+"가 등록 되었습니다.";
		}else {
			resultMessage = inVO.getuId()+"등록 실패.";
		}
		
		MessageVO message=new MessageVO(String.valueOf(flag), resultMessage);
		Gson gson=new Gson();
		jsonString = gson.toJson(message);
		LOG.debug("==============================");
		LOG.debug("=jsonString="+jsonString);
		LOG.debug("==============================");		
		
		return jsonString;
	}
	
	
	//GET방식으로 : http://localhost:8081/ehr/user/doDelete.do?uId=p31
	@RequestMapping(value="/doDelete.do",method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doDelete(HttpServletRequest req, UserVO inVO) throws SQLException{
		String jsonString = "";
		  
		//HttpServletRequest param읽기
		String uId = req.getParameter("uId");
		LOG.debug("==============================");
		LOG.debug("=uId="+uId);
		//Command로 param읽기(from name값이 VO member변수와 이름이 일치(setter로 값이 설정됨)
		LOG.debug("=inVO="+inVO);
		LOG.debug("==============================");		
		
		String resultMsg = "";
		int flag = userService.doDelete(inVO);
		if(1==flag) {
			resultMsg = inVO.getuId()+"가 삭제 되었습니다.";
		}else {
			resultMsg = inVO.getuId()+"가 삭제 실패!";
		}
		
		MessageVO message=new MessageVO(String.valueOf(flag), resultMsg);	
		Gson gson=new Gson();
		
		jsonString = gson.toJson(message);
		LOG.debug("==============================");
		LOG.debug("=jsonString="+jsonString);		
		LOG.debug("==============================");
		
		return jsonString;
	}
	
	//GET방식으로 : http://localhost:8081/ehr/user/doSelectOne.do?uId=p31
	@RequestMapping(value = "/doSelectOne.do",method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody //스프링에서 비동기 처리를 하는 경우, Http 요청의 본문 body부분이 전달된다.
	//UserVO inVO : form name VO 멤버변수명이 동일하면 자동으로 메핑한다.
	public String doSelectOne(UserVO inVO) throws SQLException{
		LOG.debug("==============================");
		LOG.debug("=inVO="+inVO);
		LOG.debug("==============================");
		
		UserVO  outVO = userService.doSelectOne(inVO);
		Gson gson=new Gson();
		String jsonString = gson.toJson(outVO);
		
		LOG.debug("==============================");
		LOG.debug("=jsonString="+jsonString);
		LOG.debug("==============================");		
		return jsonString;
	}
}

