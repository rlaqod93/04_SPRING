/**
* <pre>
* com.pcwk.ehr
* Class Name : RenameFile.java
* Description:
* Author: ITSC
* Since: 2022/06/30
* Version 0.1
* Copyright (C) by KandJang All right reserved.
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2022/06/30 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.pcwk.ehr;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author ITSC
 *
 */
public class RenameFile {
	final static String IMG_PATH = "C:\\upload\\temp\\file";
	public static void main(String[] args) {
//		System.out.println(currentDate("yyyyMMddHHmmss")); //14
//		System.out.println(getUUID());
//		System.out.println(getRenameFile("yyyyMMddHHmmss"));
//		System.out.println(getRenameFile("yyyyMMddHHmmss").length());
		
		File imgRootDir = new File(IMG_PATH);
		if(imgRootDir.isDirectory() == false) {
			boolean mkdirFlag = imgRootDir.mkdirs();
			System.out.println("=imgRootDir mkdirFlag=" + mkdirFlag);
		}
	}
	
	/**
	 * File Rename
	 * @param dateFormat
	 * @return 46byte 데이터
	 */
	public static String getRenameFile(String dateFormat) {
		return currentDate(dateFormat) + getUUID();
	}
	
	/**
	 * 현재 날짜를 format에 따라 추출
	 * @param dateFormat
	 * @return
	 */
	public static String currentDate(String dateFormat) {
		Date curDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(curDate);		
	}
	
	/**UUID는 범용고유식별자(Universal Unique Identifier)라고 한다.
	 * 32bit uuid
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
