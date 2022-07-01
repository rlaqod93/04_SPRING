/**
* <pre>
* com.pcwk.ehr.naver.domain
* Class Name : Channel.java
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
package com.pcwk.ehr.naver.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ITSC
 *
 */
public class Channel {

	private List<Item>  items = new ArrayList<Item>();
	
	public Channel() {}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	
	
}
