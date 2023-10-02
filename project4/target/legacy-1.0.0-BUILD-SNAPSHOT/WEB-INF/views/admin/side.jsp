<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



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
