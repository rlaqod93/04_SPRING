package com.pcwk.ehr.file.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("fileController")
@RequestMapping("file")
public class FileController {

	final Logger LOG = LogManager.getLogger(getClass());
	
	public FileController() {}
	
	
	@RequestMapping(value = "/fileUpView.do")
	public String fileUpView() {
		LOG.debug("========================");
		LOG.debug("=fileUpView()=");
		LOG.debug("========================");
		
		return "file/file_upload";
	}
	
}
