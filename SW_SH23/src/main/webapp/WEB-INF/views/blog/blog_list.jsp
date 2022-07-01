<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value="${pageContext.request.contextPath}"/>
<c:set var="resources" value="/resources"/>
<c:set var="CP_RES"    value="${CP }${resources}" />
<!DOCTYPE html>
<html>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <link rel="shortcut icon" type="image/x-icon" href="${CP}/favicon.ico">
        
    <title>부트 스트랩-boot_list</title>
    <!-- 부트스트랩 -->
    <link href="${CP_RES}/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="${CP_RES}/js/jquery-1.12.4.js"></script>
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <!-- 사용자 정의 function, ISEmpty -->
    <script src="${CP_RES}/js/eUtil.js"></script>
    <!-- 사용자 정의 function, callAjax -->
    <script src="${CP_RES}/js/eclass.js"></script>
    
    <script src="${CP_RES}/js/bootstrap.min.js"></script>
    <!-- jquery_bootstrap paging -->
    <script type="text/javascript" src="${CP_RES}/js/jquery.bootpag.js"></script>
        
    <script type="text/javascript">
      $(document).ready(function(){
    	console.log("document.ready");  
    	  
        //검색어 enter event
        $("#searchWord").on("keypress",function(e){
            console.log("searchWord: "+e.which); 
            
            if(13==e.which){
            	e.preventDefault();
                doRetrieve();
            }
            
        });
        
        function doRetrieve(){
            console.log("doRetrieve");  
            let url = "${CP}/naverBlog/doRetrieve.do";
            let method = "GET";
            let async  = true;
            let parameters = {
                    searchWord:$("#searchWord").val(),
                    pageSize:$("#pageSize").val()
            };              
            
            EClass.callAjax(url, parameters, method, async, function(data) {
                console.log("EClass.callAjax data:"+data);
                let parsedData = data;
                let htmlData = "";//동적으로 tbody아래 데이터 생성
                
                $("#blog_table>tbody").empty();//기존 데이터 삭제
                //조회 데이터가 있는 경우
                if(null !=parsedData && parsedData.length>0){
                    $.each(parsedData, function(i, item) {
                        console.log("item:"+item);
                        htmlData +="  <tr>                                                                   ";
                        htmlData +="   <td class='text-center col-sm-1 col-md-1 col-lg-1'>"+<c:out value='(i+1)' />+"</td> ";
                        htmlData +="   <td class='text-left   col-sm-6 col-md-6 col-lg-7'>"+<c:out value='item.title' />+"</td> ";
                        htmlData +="   <td class='text-left col-sm-3 col-md-3 col-lg-3'>"+<c:out value='item.bloggername' />+"</td> ";
                        htmlData +="   <td class='text-center col-sm-2 col-md-2 col-lg-1'>"+<c:out value='item.postdate' />+"</td> ";
                        htmlData +="  </tr>                                                                   ";
                    });                 
                }else{
                    htmlData +=" <tr>                                                       ";
                    htmlData +="    <td colspan='99' class='text-center'>No data found</td> ";
                    htmlData +=" </tr>                                                      ";
                }                    
                
                //조회 데이터가 없는 경우
                $("#blog_table>tbody").append(htmlData);                
            });        	
        	
        }
        
    	$("#doRetrieve").on("click",function(e){
    		doRetrieve();
    	});
    	
      });
      
      
    </script>
</head>
<body>
       <!-- div container -->
       <div class="container">
          <!-- 제목 -->
          <div class="page-header">
              <h2>블로그 목록</h2>
          </div>
          <!--// 제목 ----------------------------------------------------------->
          
          <!-- 검색영역 -->
          <div class="row">
            <form action="#" class="form-inline col-sm-12 col-md-12 col-lg-12 text-right">
               <div class="form-group">
                 <input type="text" class="form-control  input-sm" name="searchWord" id="searchWord" placeholder="검색어" />
                 <select class="form-control  input-sm"  name="pageSize" id="pageSize">
                    <option value="10">10</option>
                    <option value="20">20</option>
                    <option value="30">30</option>
                 </select>  
               <input type="button" class="btn btn-primary btn-sm" value="목록" id="doRetrieve" />                         
               </div>
            </form>
          
          </div>
          <!--// 검색영역 ----------------------------------------------------------->
          
          
          <!-- tabble -->
          <div class="table-responsive">
           <table id="blog_table" class="table table-striped table-bordered table-hover table-condensed">
               <thead class="bg-primary">
                 <tr>
                     <th class="text-center col-sm-1 col-md-1 col-lg-1">번호</th>
                     <th class="text-center col-sm-7 col-md-7 col-lg-9">제목</th>
                     <th class="text-center col-sm-2 col-md-2 col-lg-1">작성자</th>
                     <th class="text-center col-sm-2 col-md-2 col-lg-1">작성일</th>
                 </tr>
               </thead>
               <tbody>                           
               </tbody>
           </table>
          </div>
          <!--// tabble ----------------------------------------------------------->

          
       </div>
       <!--// div container --------------------------------------------------->
   
       
       
       
       
</body>
</html>