package com.pcwk.ehr.mainController;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("mainController")
@RequestMapping("main")
public class MainController {
	final Logger LOG = LogManager.getLogger(getClass());
	
	public MainController() {
		LOG.debug("=====================");
		LOG.debug("=LonginController()=");
		LOG.debug("=====================");
	}
	
	@RequestMapping(value = "/mainView.do" , method = RequestMethod.GET)
	public String mainView()throws SQLException{
		LOG.debug("=====================");
		LOG.debug("=mainView()=");
		LOG.debug("=====================");
		
		//WEB-INF/views/main/main.jsp
		return "main/main";
	}
	
}
