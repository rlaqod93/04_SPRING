/**
* <pre>
* com.pcwk.ehr.file.controller
* Class Name : FileController.java
* Description:
* Author: ITSC
* Since: 2022/06/29
* Version 0.1
* Copyright (C) by KandJang All right
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2022/06/29 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.pcwk.ehr.file.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.pcwk.ehr.cmn.FileVO;
import com.pcwk.ehr.cmn.StringUtil;

/**
 * @author ITSC
 *
 */
@Controller("fileController")
@RequestMapping("file")
public class FileController {

	final Logger LOG = LogManager.getLogger(getClass());
	
	final String FILE_PATH = "C:\\upload\\temp\\file";
	//final String IMG_PATH  = "C:\\DCOM_20220127\\04_SPRING\\workspace\\SW_SH23\\src\\main\\webapp\\upload";
	final String IMG_PATH  = "C:\\upload\\temp\\image";
	
	//C:\DCOM_20220127\04_SPRING\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\SW_SH23
	@Resource(name="downloadView")
	View download;
	
	public FileController() {}
	
	@RequestMapping(value = "/viewImag.do")
	public ResponseEntity<org.springframework.core.io.Resource> viewImage(FileVO fileVO) throws SQLException {
		LOG.debug("====================");
		LOG.debug("=viewImage()=");
		LOG.debug("=FileVO=" + fileVO);		
		
		String fileName = "spring.png";
		File downloadFile = new File(IMG_PATH,
				fileName);
		
		
		HttpHeaders header = new HttpHeaders();
		Path filePath = null;
		try {
			filePath = Paths.get(IMG_PATH, fileName);
			header.add("Content-type", Files.probeContentType(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}		
		  
		org.springframework.core.io.Resource  resource =new FileSystemResource(downloadFile);
		
		return new ResponseEntity<org.springframework.core.io.Resource>(resource, header, HttpStatus.OK);
	}
	
	@RequestMapping(value="/download.do",method = RequestMethod.POST)
	public ModelAndView download(FileVO fileVO,ModelAndView modelAndView ) {
		LOG.debug("=========================");
		LOG.debug("==download()=");
		LOG.debug("==fileVO()="+fileVO);
		LOG.debug("=========================");		
		
		//path, 저장파일명
		File downloadFile=new File(fileVO.getSavePath(),fileVO.getSaveFileNm());
		
		modelAndView.setView(download);
		
		modelAndView.addObject("orgFileNm", fileVO.getOrgFileNm());
		
		modelAndView.addObject("downloadFile",downloadFile);
		
		return modelAndView;
	}
	
	
	
	
	
	
	@RequestMapping(value="/upload.do", method = RequestMethod.POST)
	public ModelAndView upload(ModelAndView modelAndView, MultipartHttpServletRequest mReg ) throws IllegalStateException, IOException {
		LOG.debug("=========================");
		LOG.debug("==upload()=");
		LOG.debug("=========================");
		
		//1.원본파일
		//2.저장파일(yyyyMMddHH24mmss+UUID
		
		//동적 폴더 생성
		File imgRootDir = new File(IMG_PATH);
		if(imgRootDir.isDirectory() == false) {
			boolean mkdirFlag =imgRootDir.mkdirs();
			LOG.debug("=imgRootDir mkdirFlag="+mkdirFlag);	
		}
		
		File fileRootDir = new File(FILE_PATH);
		if(fileRootDir.isDirectory() == false) {
			boolean mkdirFlag = fileRootDir.mkdirs();
			LOG.debug("=fileRootDir mkdirFlag="+mkdirFlag);	
		}
		
		String title = StringUtil.nvl(mReg.getParameter("title"),"");
		String fileDiv = StringUtil.nvl(mReg.getParameter("fileDiv"),"");
		
		LOG.debug("=title="+title);	
		LOG.debug("=fileDiv="+fileDiv);	
		
		//file read
		List<FileVO> list =new ArrayList<>();
		
		//파일이 여러개인 경우
		Iterator<String> fileNames =  mReg.getFileNames();
		
		while(fileNames.hasNext()) {
			//LOG.debug("fileNames:"+fileNames.next());
			FileVO  fileVO=new FileVO();
			String upVariableName = fileNames.next();
			LOG.debug("upVariableName:"+upVariableName);
			
			MultipartFile mFile = mReg.getFile(upVariableName);
			
			//원본파일명
			String orgFileName = mFile.getOriginalFilename();
			LOG.debug("orgFileName:"+orgFileName);
			if(null == orgFileName || "".equals(orgFileName) ) {
				continue;
			}
			
			//저장파일명
			String saveFile = StringUtil.getRenameFile("yyyyMMddHHmmss");
			LOG.debug("saveFile:"+saveFile);
			
			//확장자
			//pom.xml -> xml
			String ext = "";
			
			if(orgFileName.lastIndexOf(".")>-1) {
				ext=orgFileName.substring(orgFileName.lastIndexOf(".")+1);
				saveFile +="."+ext;
			}
			LOG.debug("ext:"+ext);
			LOG.debug("saveFile:"+saveFile);
			
			//파일 사이즈: byte
			long fileSize = mFile.getSize();
			LOG.debug("fileSize:"+fileSize);
			
			String savePath = "";
			if("10".equals(fileDiv)) {//file
				savePath = FILE_PATH;
			}else {
				savePath = IMG_PATH;//image
			}
			
			fileVO.setOrgFileNm(orgFileName);
			fileVO.setSaveFileNm(saveFile);
			fileVO.setFileSize(fileSize);
			fileVO.setSavePath(savePath);
			fileVO.setExt(ext);
			LOG.debug("fileVO:"+fileVO);
			list.add(fileVO);
			
			//파일 전송
			File reNameFile = new File(fileVO.getSavePath(),fileVO.getSaveFileNm());
			mFile.transferTo(reNameFile);
		}
		
		modelAndView.addObject("list", list);
		modelAndView.setViewName("file/file_upload");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/fileUpView.do")
	public String fileUpView() {
		LOG.debug("=========================");
		LOG.debug("==fileUpView()=");
		LOG.debug("=========================");
		
		return "file/file_upload";
		
	}
	
	
}
