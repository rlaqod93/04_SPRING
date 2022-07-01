<%--
/**
	Class Name: file_upload.jsp
	Description: 파일 업로드
	Modification information
	
	수정일     수정자      수정내용
    -----   -----  -------------------------------------------
    2022. 6. 29.        최초작성 
    
    author eclass 개발팀
    since 2020.11.23
    Copyright (C) by KandJang All right reserved.
*/
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- /ehr -->
<c:set var="CP" value="${pageContext.request.contextPath}"/>
<c:set var="resources" value="/resources"/>
<c:set var="CP_RES" value="${CP}${resources}"/>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="shortcut icon" type="image/x-icon" href="${CP}/favicon.ico">
	<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <title>파일 업로드</title>
    
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
        	
        	$(".downloadTable > tbody").on("click", "tr", function(e){
        		console.log(".downloadTable > tbody");
        		let tdArrays = $(this).children();
        		let orgFileNm = tdArrays.eq(0).text();
        		let saveFileNm = tdArrays.eq(1).text();
        		let savePath = tdArrays.eq(4).text();
        		console.log("orgFileNm : " + orgFileNm);
        		console.log("saveFileNm : " + saveFileNm);
        		console.log("savePath : " + savePath);
        		
        		$("#orgFileNm").val(orgFileNm);
        		$("#saveFileNm").val(saveFileNm);
        		$("#savePath").val(savePath);
        		
        		document.fileDownloadFrm.submit();
        		
        		
        	});
        });
    </script>
</head>
<body>
    <!-- div container -->
    <div class="container">
        <!-- 제목 -->
        <div class="page-header">
            <h2>파일 업로드</h2>
        </div>
        <!--//제목  ------------------------------------------->
        <!-- form -->
        <form action="${CP}/file/upload.do" class="form-horizontal" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="title" class="col-sm-3 col-md-2 col-lg-2 control-label">제목</label>
                <div class="col-sm-9 col-md-10 col-lg-10">
                    <input type="text" maxlength="100" name="title" id="title" placeholder="제목" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="title" class="col-sm-3 col-md-2 col-lg-2 control-label">구분</label>
                <div class="col-sm-9 col-md-10 col-lg-10">
                    <select class="form-control" name="fileDiv" id="fileDiv">
                        <option value="10">파일</option>
                        <option value="20">이미지</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="file01" class="col-sm-3 col-md-2 col-lg-2 control-label">파일1</label>
                <div class="col-sm-9 col-md-10 col-lg-10">
                    <input type="file" maxlength="100" name="file01" id="file01" placeholder="파일1" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="file02" class="col-sm-3 col-md-2 col-lg-2 control-label">파일2</label>
                <div class="col-sm-9 col-md-10 col-lg-10">
                    <input type="file" maxlength="100" name="file02" id="file02" placeholder="파일2" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="doInsert" class="col-sm-3 col-md-2 col-lg-2 control-label"></label>
                <div class="col-sm-9 col-md-10 col-lg-10">
                    <input type="submit" maxlength="100" id="doInsert" class="btn btn-primary btn-sm" value="전송">
                </div>
            </div>
        </form>
        <!--//form -------------------------------------------->
        list:${list}
        <!-- table-------------------------------------------------- -->
        <div class="table-responsive">
          <table class="downloadTable table table-striped table-bordered table-hover table-condensed">
              <thead class="bg-primary">
                  <tr>
                      <th class="text-center col-sm-3 col-md-3 col-lg-3">원본파일명</th>
                      <th class="text-center col-sm-3 col-md-3 col-lg-3">저장파일명</th>
                      <th class="text-center col-sm-2 col-md-2 col-lg-2">사이즈</th>
                      <th class="text-center col-sm-2 col-md-2 col-lg-2">확장자</th>
                      <th class="text-center col-sm-2 col-md-2 col-lg-2">저장경로</th>
                  </tr>
              </thead>
              <tbody>
                <!-- 문자왼쪽, 숫자오른쪽, 같으면가운데 -->
                <c:choose>
                    <c:when test="${list.size() > 0}">
                       <c:forEach var="vo" items="${list}">
                          <tr>
			                  <td class="text-left col-sm-3 col-md-3 col-lg-3">${vo.orgFileNm}</td>
			                  <td class="text-left   col-sm-3 col-md-3 col-lg-3">${vo.saveFileNm}</td>
			                  <td class="text-center col-sm-2 col-md-2 col-lg-2">${vo.fileSize}</td>
			                  <td class="text-center col-sm-2 col-md-2 col-lg-2">${vo.ext}</td>
			                  <td class="text-left  col-sm-2 col-md-2 col-lg-2">${vo.savePath}</td>
			              </tr>
			            </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="99" class="text-center">NO DATA FOUND</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
              
              </tbody>
          </table>
        </div>
        <!-- //table --------------------------------------------------->
        <!-- file download: 저장파일명을 원본파일로 변경 -->
        <form action="${CP}/file/download.do" method="post" name="fileDownloadFrm">
            <input type="text" name="orgFileNm" id="orgFileNm">
            <input type="text" name="saveFileNm" id="saveFileNm">
            <input type="text" name="savePath" id="savePath">
        </form>
    </div>
    <!--//div container --------------------------------------->
</body>
</html>