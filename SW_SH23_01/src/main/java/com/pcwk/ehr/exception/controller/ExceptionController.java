/**
* <pre>
* com.pcwk.ehr.exception.controller
* Class Name : ExceptionController.java
* Description: 예외처리 테스트
* Author: ITSC
* Since: 2022/06/29
* Version 0.1
* Copyright (C) by KandJang All right reserved.
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2022/06/29 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.pcwk.ehr.exception.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pcwk.ehr.user.domain.UserVO;

/**
 * @author ITSC
 *
 */
@Controller
@RequestMapping("except")
public class ExceptionController {
	
	// http://localhost:8081/ehr/except/illegal.do?uId=
	@RequestMapping(value="/illegal.do")
	public String IllegalArgumentException(UserVO inVO) {
		if(null == inVO.getuId() || "".equals(inVO.getuId())) {
			throw new java.lang.IllegalArgumentException("아이디 타입을 확인하세요!!");
		}
		return "main/main";
	}
	
	// http://localhost:8081/ehr/except/nullPointer.do?uId=
	@RequestMapping(value = "/nullPointer.do")
	public String nullPointerException(UserVO inVO) {
		if(null == inVO.getuId() || "".equals(inVO.getuId())) {
			throw new NullPointerException("아이디를 입력해주세요");
		}
		return "main/main";
	}
}
