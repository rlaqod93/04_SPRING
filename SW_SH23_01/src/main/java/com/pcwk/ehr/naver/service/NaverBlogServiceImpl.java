/**
* <pre>
* com.pcwk.ehr.naver.service
* Class Name : NaverBlogServiceImpl.java
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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.SearchVO;
import com.pcwk.ehr.naver.domain.Channel;
import com.pcwk.ehr.naver.domain.Item;

/**
 * @author ITSC
 *
 */
@Service("naverBlogService")
public class NaverBlogServiceImpl implements NaverBlogService {

	final Logger LOG = LogManager.getLogger(this.getClass());
	
	public NaverBlogServiceImpl() {
		
	}
	
	@Override
	public List<Item> doRetrieve(SearchVO dto) throws SQLException {
		
		String clientId = "w7CZy4kWb13gklCaYPPz"; //회원ID
		String clientSecret = "D0GPTIhYIe";       //회원비번
		LOG.debug("=======================================");
		Channel channel = null;
		try {
			String searchText = URLEncoder.encode(dto.getSearchWord(), "UTF-8");
//			String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query=" + searchText; //xml
			String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + searchText + "&display=" +dto.getPageSize(); //json
			URL url = new URL(apiURL);
			
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			
			int responseCode = con.getResponseCode();
			LOG.debug("=responseCode=" + responseCode);
			
			BufferedReader br;
			if(responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine = "";
			StringBuffer responseData = new StringBuffer();
			
			while((inputLine = br.readLine()) != null) {
				LOG.debug(inputLine);
				responseData.append(inputLine);
			}
			br.close();
			
			Gson gson = new Gson();
			channel = gson.fromJson(responseData.toString(), Channel.class);
			for(Item item : channel.getItems()) {
				LOG.debug(item);
			}
		}catch(Exception e) {
			LOG.debug("=======================================");
			LOG.debug("=e=" + e.getMessage());
			LOG.debug("=======================================");
			e.printStackTrace();
		}
		
		return channel.getItems();
	}

}
