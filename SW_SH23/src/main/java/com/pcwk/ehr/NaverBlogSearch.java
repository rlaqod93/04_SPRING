/**
* <pre>
* com.pcwk.ehr
* Class Name : NaverBlogSearch.java
* Description:
* Author: ITSC
* Since: 2022/06/28
* Version 0.1
* Copyright (C) by KandJang All right
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2022/06/28 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.pcwk.ehr;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import com.google.gson.Gson;

/**
 * @author ITSC
 *
 */
public class NaverBlogSearch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// https://jsonformatter.org/json-parser

		String clientId = "xkHJBdkGsqi8u1fyY_WT";// 애플리케이션 클라이언트 아이디값";
		String clientSecret = "gHSDoIjdTs";// 애플리케이션 클라이언트 시크릿값";
		try {
			String text = URLEncoder.encode("이대", "UTF-8");
			String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + text; // json 결과
			// String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text;
			// // xml 결과
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			// "items": [{"title": "<b>홍대</b> 맛집, 마라에 중독되다","link":
			// "https:\/\/blog.naver.com\/ydi007?Redirect=Log&logNo=221583468898","description":
			// "받는데, <b>홍대</b>에 위치한 라화쿵부는 마라탕을 비롯해 각종 중국식 요리를 파는 곳이다 <b>홍대</b>입구역에서 나와 도보로
			// 조금 걸어가다 보면 10분안에 도착하는 <b>홍대</b> 맛집 라화쿵부 9번출구로 나와서 맥도날드를 지나...
			// ","bloggername": "당신의 맛집은 안녕하십니까?","bloggerlink":
			// "https://blog.naver.com/ydi007","postdate": "20190713"},
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			// System.out.println(response.toString());
			Gson gson = new Gson();
			Channel channel = gson.fromJson(response.toString(), Channel.class);
			List<Item> list = channel.getItems();
			// System.out.println("list: "+list.size() );
			for (Item item : list) {
				System.out.println(item.toString());
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}