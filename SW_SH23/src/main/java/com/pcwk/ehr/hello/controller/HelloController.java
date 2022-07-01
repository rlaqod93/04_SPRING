/**
* <pre>
* com.pcwk.ehr.hello.controller
* Class Name : HelloController.java
* Description:
* Author: ITSC
* Since: 2022/07/01
* Version 0.1
* Copyright (C) by KandJang All right
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2022/07/01 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.pcwk.ehr.hello.controller;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pcwk.ehr.cmn.SearchVO;

/**
 * @author ITSC
 *
 */
@Controller("helloController")
@RequestMapping("hello")
public class HelloController {
	final Logger LOG = LogManager.getLogger(getClass());
	@RequestMapping(value = "/hello.do", method = RequestMethod.GET)
	public String hello() throws SQLException {
		LOG.debug("========================");
		LOG.debug("=hello()=");
		LOG.debug("========================");
		
		return "hello/hello";           
	}
	
}
