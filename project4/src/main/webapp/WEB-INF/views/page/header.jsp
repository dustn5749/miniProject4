<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
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
              <li><a href="#">대보정보통신 <span>▼</span></a>
                <ul>
                  <li><a href="https://www.dbcs.co.kr/kr/company/overview">회사 이념</a></li>
                  <li><a href="https://dbcs.recruiter.co.kr/appsite/company/callSubPage?code1=3000&code2=3100&code3=3150">회사 소개</a></li>
                </ul>
              </li>
              <li><a href="#">kosa 교육 <span>▼</span></a>
                <ul>
                  <li><a href="/project3/education">교육 목적</a></li>
                  <li><a href="#">실습 내용</a></li>
                  <li><a href='<c:url  value="/board/list.do"/>' id="board">게시판</a></li>
                  <li><a href='<c:url value="/notice/list.do"/>' id="notice">공지사항</a></li>
                </ul>
              </li>			
			<c:choose>
			<c:when test="${loginMember != null}">
      		  <li><a href="#" id="mypageBtn">마이페이지</a></li>
              <li><a href="#" id="logoutbtn" >로그아웃</a></li>
			</c:when>
			<c:otherwise >
			<li><a href="#" id="joinBtn">회원가입</a></li>
            <li><a href="#" id="loginBtn">로그인</a></li>
			</c:otherwise>
			</c:choose>              
            </ul>
          </nav>
          </form>
        </header>