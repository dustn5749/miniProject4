<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"   isELIgnored="false"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>메인 페이지</title>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
 <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
</head>
<body>
    <!-- <h2>대시보드</h2>
    <p>환영합니다! 관리자 페이지의 대시보드입니다.</p> -->
  

     
     <div id="mainMenu">
	     <div id="boardContent">
	    <div id="titleNotice"><span id="title2">게시판 top5List</span> <span id="plusBtn"><a href='<c:url  value="/board/adminList.do"/>' >더보기+</a></span></div>
	     <ul id="noticeUl">
	     	 <c:forEach items="${boardtop5List}" var="list"> 
                <li><a class="top5List">
                [<span class="top5boardNum"><c:out  value="${list.boardNum}"/></span>]  <span class="top5boardTitle"><c:out  value="${list.title}"/></span> 
                </a></li>                     
                <input type="hidden" value='<c:url value="/notice/detail.do"></c:url>' class="hiddenNum">
              </c:forEach>
	     </ul>
	     </div>
	     
	     <div id="noticeContent">
	     <div id="titleNotice"><span id="title2">공지사항 top5List</span> <span id="plusBtn"><a href='<c:url  value="/board/adminList.do"/>' >더보기+</a></span></div>
	     <ul id="noticeUl">
	     	 <c:forEach items="${noticetop5List}" var="list"> 
                <li><a class="top5List">
                [<span class="top5boardNum"><c:out  value="${list.boardNum}"/></span>]  <span class="top5boardTitle"><c:out  value="${list.title}"/></span> 
                </a></li>                     
                <input type="hidden" value='<c:url value="/notice/detail.do"></c:url>' class="hiddenNum">
              </c:forEach>
	     </ul>
	     </div>
	     
	     <div id="memberContent"></div>
	     <div id="emptyContent"></div>
     </div>

</body>

</html>