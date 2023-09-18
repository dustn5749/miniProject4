<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>        
<link rel="stylesheet" href='<c:url value="/resources/css/style.css"></c:url>'> 

        <header>
        <form id="boardSortFm" method="post" action='<c:url value="/board/list.do"/>'>
          <div id="logo">
            <a href='<c:url value="/index/index.do"/>'>
              <img src="<c:url value="/resources/images/logo1.png"/>" height="30px" alt="">
            </a>
          </div>
          <nav>
            <ul id="topMenu">
            <c:if test="${admin != null}">
              <li><a href="#" id="mypageBtn">마이페이지</a></li>            
            </c:if>
              <li><a href="#">회원 관리<span>▼</span></a>
                <ul>
                  <li><a href='<c:url  value="/admin/memberlist.do"/>' id="memberList">전체 목록</a></li>
                </ul>
                 <li><a href="#">게시글관리<span>▼</span></a>
                <ul>
                  <li><a href='<c:url  value="/board/adminList.do"/>' id="board">게시판</a></li>
                  <li><a href='<c:url value="/notice/adminList.do"/>' id="notice">공지사항</a></li>
                </ul>
              </li>			
			<c:choose>
			<c:when test="${admin != null}">
      		  <li><a href="#" id="logoutbtn" >로그아웃</a></li>
			</c:when>
			<c:otherwise >
			<li><a href="#" id="loginBtn">로그인</a></li>
			</c:otherwise>
			</c:choose>              
            </ul>
          </nav>
          </form>
        </header>