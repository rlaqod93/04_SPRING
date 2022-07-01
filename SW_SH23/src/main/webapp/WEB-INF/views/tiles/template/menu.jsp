<%--
/**
	Class Name:
	Description:
	Modification information
	
	수정일     수정자      수정내용
    -----   -----  -------------------------------------------
    2022. 7. 1.        최초작성 
    
    author eclass 개발팀
    since 2020.11.23
    Copyright (C) by KandJang All right reserved.
*/
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value="${pageContext.request.contextPath}"/>
<c:set var="resources" value="/resources"/>
<c:set var="CP_RES" value="${CP}${resources}"/>

<table>
    <tr>
        <td><a href="${CP}/hello/hello.do">Hello Spring!</a></td>
    </tr>
    <tr>
        <td><a href="${CP}/board/boardView.do?div=10">공지사항</a></td>
    </tr>
    <tr>
        <td><a href="${CP}/user/userView.do">회원관리</a></td>
    </tr>
</table>