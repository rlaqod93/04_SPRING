/**
* <pre>
* com.pcwk.ehr.naver
* Class Name : NaverBlogSearch.java
* Description: 네이버 블로그 검색(
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
package com.pcwk.ehr.naver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.pcwk.ehr.naver.domain.Channel;
import com.pcwk.ehr.naver.domain.Item;


/**
 * @author ITSC
 *
 */
public class NaverBlogSearch {

	final static Logger LOG = LogManager.getLogger(NaverBlogSearch.class);

	public static void main(String[] args) {
		String clientId = "xkHJBdkGsqi8u1fyY_WT";//회원ID
		String clientSecret = "gHSDoIjdTs";      //회원비번
		LOG.debug("================================");

		try {
			String searchText = URLEncoder.encode("신촌", "UTF-8") ;
		    String apiURL     = "https://openapi.naver.com/v1/search/blog?query="+searchText+"&display=";//json
		    //String apiURL     = "https://openapi.naver.com/v1/search/blog.xml?query="+searchText; //xml
		    
		    URL  url=new URL(apiURL);
		    
		    HttpURLConnection  con = (HttpURLConnection)url.openConnection();
		    con.setRequestMethod("GET");
		    con.setRequestProperty("X-Naver-Client-Id", clientId);
		    con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
		    
		    int responseCode = con.getResponseCode();
		    LOG.debug("=responseCode="+responseCode);
		    
		    BufferedReader  br;
		    
		    if(200 == responseCode) {
		    	br=new BufferedReader(new InputStreamReader(con.getInputStream()));
		    }else {
		    	br=new BufferedReader(new InputStreamReader(con.getErrorStream()));
		    }
		    
		    String inputLine = "";
		    StringBuffer  responseData=new StringBuffer(2000);
		    
		    while( (inputLine=br.readLine()) !=null) {
		    	//LOG.debug(inputLine);
		    	responseData.append(inputLine);
		    }
		    br.close();
		    
		    Gson gson=new Gson();
		    Channel channel = gson.fromJson(responseData.toString(), Channel.class);
		    
		    for(Item item  :channel.getItems()) {
		    	LOG.debug(item);
		    }
		    
		    
		    
		    
		    
		    
		}catch(Exception e) {
			LOG.debug("================================");
			LOG.debug("=e="+e.getMessage());
			LOG.debug("================================");
			e.printStackTrace();
		}
	}

}
