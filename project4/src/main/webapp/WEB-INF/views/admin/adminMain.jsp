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
  

     
     
     <div id="mainMenu">
	     <div id="boardContent">
	    <div class="titleNotice"><span class="title2">게시판 top5List</span> <span class="plusBtn"><a href='<c:url  value="/board/adminList.do"/>' >더보기+</a></span></div>
	     <ul class="noticeUl">
	     	 <c:forEach items="${boardtop5List}" var="list"> 
                <li><a class="top5List">
                [<span class="top5boardNum"><c:out  value="${list.boardNum}"/></span>]  <span class="top5boardTitle"><c:out  value="${list.title}"/></span> 
                <span class="regdate"> | ${list.regdate}</span></a></li>                     
              </c:forEach>
	     </ul>
	     </div>
	     
	     <div id="noticeContent">
	     <div class="titleNotice"><span class="title2">공지사항 top5List</span> <span class="plusBtn"><a href='<c:url  value="/notice/adminList.do"/>' >더보기+</a></span></div>
	     <ul class="noticeUl">
	     	 <c:forEach items="${noticetop5List}" var="list"> 
                <li><a class="top5List">
                [<span class="top5boardNum"><c:out  value="${list.boardNum}"/></span>]  <span class="top5boardTitle"><c:out  value="${list.title}"/></span> 
                <span class="regdate"> | ${list.regdate}</span></a></li> 

              </c:forEach>
	     </ul>
	     </div>
	     
	     <div id="memberContent">
	      <div class="titleNotice"><span class="title2">회원 정보</span> <span class="plusBtn"><a href='<c:url  value="/admin/memberlist.do"/>' >더보기+</a></span></div>
	     <ul class="noticeUl">
	     	 <c:forEach items="${membertop5List}" var="list"> 
                <li><a class="top5List">
                ◆ <span class="top5boardNum">아이디 : ${list.uid} </span>|  <span class="top5boardTitle">이름 : <c:out  value="${list.name}"/></span> 
                </a></li>                     
              </c:forEach>
	     </ul>
	     </div>
	     <div id="emptyContent">
	     	빈 페이지 입니다
	     </div>
     </div>

</body>

</html>