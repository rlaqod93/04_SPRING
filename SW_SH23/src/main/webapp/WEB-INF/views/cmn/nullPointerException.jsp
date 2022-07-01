<%--
/**
	Class Name:nullPointerException.jsp
	Description:nullPointerException발생시 처리
	Modification information
	
	수정일     수정자      수정내용
    -----   -----  -------------------------------------------
    2022. 6. 29.        최초작성 
    
    author ehr 개발팀
    since 2022.01.27
    Copyright (C) by KandJang All right reserved.
*/
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value="${pageContext.request.contextPath}"/>
<c:set var="resources" value="/resources"/>
<c:set var="CP_RES"    value="${CP}${resources}" />
<!DOCTYPE html>
<html>
<head>  
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <link rel="shortcut icon" type="image/x-icon" href="${CP}/favicon.ico">    
    <title>예외</title>
    <!-- 부트스트랩 -->
    <link href="${CP_RES}/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="${CP_RES}/js/jquery-1.12.4.js"></script>
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <script src="${CP_RES}/js/bootstrap.min.js"></script>
    <!-- jquery_bootstrap paging -->
    <script type="text/javascript" src="${CP_RES}/js/jquery.bootpag.js"></script>
            
    <script type="text/javascript">
      $(document).ready(function(){
    	console.log("document.ready");  
    	  
      });
      
      
    </script>
</head>
<body>
    <div class="container">
       <div class="col-md-12">
         <div class="error-template">
           <h2>NullPointerException</h2>
           
           <div class="error-details">
              <h3>요청 처리 과정에 에러가 발생 했습니다.</h3>
              <p>상태코드: <c:out value="${requestScope['javax.servlet.error.status_code']}"/></p>
              <p>서블릿 이름: <c:out value="${requestScope['javax.servlet.error.servlet_name']}"/></p>
              <p>예외 이름: <c:out value="${requestScope['javax.servlet.error.exception']}"/></p>
              <p>요청URI: <c:out value="${requestScope['javax.servlet.error.request_uri']}"/></p>
              <p>메시지: <c:out value="${requestScope['javax.servlet.error.message']}"/></p>
           </div>
           
         </div>
       </div>
    </div>
</body>
</html>




















