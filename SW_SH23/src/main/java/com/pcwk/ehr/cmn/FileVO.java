/**
* <pre>
* com.pcwk.ehr.cmn
* Class Name : FileVO.java
* Description:
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
package com.pcwk.ehr.cmn;

/**
 * @author ITSC
 *
 */
public class FileVO extends DTO {
	//파일 아이디
	//파일 순번
	private String orgFileNm; //원본 파일명
	private String saveFileNm;//저장 파일명
	private String savePath; //저장 경로
	private long fileSize;//파일 사이즈
	private String ext;//확장자
	
	public FileVO() {
		
	}

	public String getOrgFileNm() {
		return orgFileNm;
	}

	public void setOrgFileNm(String orgFileNm) {
		this.orgFileNm = orgFileNm;
	}

	public String getSaveFileNm() {
		return saveFileNm;
	}

	public void setSaveFileNm(String saveFileNm) {
		this.saveFileNm = saveFileNm;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	@Override
	public String toString() {
		return "FileVO [orgFileNm=" + orgFileNm + ", saveFileNm=" + saveFileNm + ", savePath=" + savePath
				+ ", fileSize=" + fileSize + ", ext=" + ext + ", toString()=" + super.toString() + "]";
	}
	
}
