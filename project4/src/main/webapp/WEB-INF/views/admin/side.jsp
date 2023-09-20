<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
 <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
 <style>
   .no-underline {
      text-decoration: none;
   }

   /* 사이드 메뉴 스타일 */
   ul {
      list-style: none;
      padding: 0;
   }

   ul li {
      margin-bottom: 30px; /* 각 메뉴 항목 아래 간격 */
   }
   
   
   a { 
   cursor: pointer;
   text-decoration:  none;
   }
   
   a:hover {
   	color : green;
   }

   ul li a {
   	  margin-bottom: 15px;
      color: white; /* 글자 색상 */
      font-size: 20px; /* 글자 크기 */
      display: block;
   }

   ul li ul {
      display: none; /* 초기에 하단 메뉴 숨김 */
   }

    ul li.active ul {
      display: block; /* active 클래스가 있는 메뉴만 하단 메뉴 표시 */
   } 
   

 </style>
  <meta charset="UTF-8">
  <title>사이드 메뉴</title>
</head>
<body>
	<h1 id="menuTitle">Side Menu</h1>
	
	<ul>
	
		<li><a href="#"  onclick="displayMenu(this)">Main<span>▼</span></a>
			<ul>
            	<li><a href='<c:url  value="/admin.do"/>' >home</a></li>
            	<c:choose>
            		<c:when test="${admin.uid != 'admin'}">
            		<li><a href="#" id="loginBtn">로그인</a></li>	
            		</c:when>
            		<c:otherwise>
	            	<li><a href="#" id="logoutbtn" >로그아웃</a></li>
            		<li><a href="#" id="mypageBtn">마이페이지</a></li>
            		</c:otherwise>
            	</c:choose>
         	</ul>
		<li><a href="#" onclick="displayMenu(this)">회원 관리<span>▼</span></a>
			<ul>
            	<li><a href='<c:url  value="/admin/memberlist.do"/>' id="memberList">전체 목록</a></li>
         	</ul>
        </li>
 		<li><a href="#" onclick="displayMenu(this)">게시글관리<span>▼</span></a>
            <ul>
               <li><a href='<c:url  value="/board/adminList.do"/>' id="board">게시판</a></li>
               <li><a href='<c:url value="/notice/adminList.do"/>' id="notice">공지사항</a></li>
            </ul>
         </li>

	</ul>
<script type="text/javascript">
 	function displayMenu(clickedElement) {
		var parentListItem = clickedElement.parentElement;
		parentListItem.classList.toggle("active");
	} 

</script>	
	
</body>

</html>