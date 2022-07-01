/**
* <pre>
* com.pcwk.ehr.naver.service
* Class Name : NaverBlogService.java
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
package com.pcwk.ehr.naver.service;

import java.sql.SQLException;
import java.util.List;

import com.pcwk.ehr.cmn.SearchVO;
import com.pcwk.ehr.naver.domain.Item;

/**
 * @author ITSC
 *
 */
public interface NaverBlogService {
	/**
	 * 게시글 목록조회
	 * @param dto
	 * @return List<BoardVO>
	 * @throws SQLException
	 */
	List<Item> doRetrieve(SearchVO dto) throws SQLException;
	
}
