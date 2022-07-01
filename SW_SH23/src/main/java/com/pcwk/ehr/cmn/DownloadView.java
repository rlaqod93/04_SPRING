/**
* <pre>
* com.pcwk.ehr.cmn
* Class Name : DownloadView.java
* Description:
* Author: ITSC
* Since: 2022/06/30
* Version 0.1
* Copyright (C) by KandJang All right
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2022/06/30 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.pcwk.ehr.cmn;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

/**
 * @author ITSC
 *
 */
public class DownloadView extends AbstractView {

	final Logger LOG = LogManager.getLogger(getClass());
	
	public DownloadView() {
		setContentType("application/download;charset=utf-8");
	}
	
	/**
	 * 다운로드 파일의 이름 변경: 원본파일로 변경
	 * @param orgFileNm
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void setDownloadFileName(String orgFileNm, HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		String userAgent = request.getHeader("User-Agent");//브라우저 구분
		LOG.debug("===========================");
		LOG.debug("==userAgent="+userAgent);
		LOG.debug("==orgFileNm="+orgFileNm);
		
		orgFileNm = URLEncoder.encode(orgFileNm, "utf-8");
		LOG.debug("==encode orgFileNm="+orgFileNm);
		
		//header에 원본 파일명 전달
		response.setHeader("Content-Disposition", "attachment; fileName=\""+orgFileNm+"\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
	}
	
	/**
	 * 파일 다운로드 
	 * @param downloadFile
	 */
	public void downloadFile(File downloadFile, HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		FileInputStream  in=new FileInputStream(downloadFile);
		
		OutputStream     out = response.getOutputStream();
		
		try {
			
			int bytes = FileCopyUtils.copy(in, out);
			LOG.debug("===========================");
			LOG.debug("==bytes="+bytes);			
			LOG.debug("===========================");
		}catch(IOException e) {
			throw e;
		}finally {
			if(null !=in) {
				try {
					in.close();
				}catch(IOException e) {
					
				}
			}
			
			if(null !=out) {
				try {
					out.close();
				}catch(IOException e) {
					
				}				
			}
			
			
		}
		
	}
	
	
	//다운로드 view에 시작
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		setResponseContentType(request, response);
		String orgFileNm = (String) model.get("orgFileNm");
		File downloadFile = (File) model.get("downloadFile");
		
		LOG.debug("===========================");
		LOG.debug("==orgFileNm="+orgFileNm);
		LOG.debug("==downloadFile="+downloadFile);
		LOG.debug("===========================");
		
		//다운로드 파일을 원본파일로 변경
		setDownloadFileName(orgFileNm,request,response);
		
		//stream을 통한 파일 다운로드
		downloadFile(downloadFile,request,response);
		
	}

}












