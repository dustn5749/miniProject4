<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:insertAttribute name="title"/></title>
<link rel="stylesheet" href='<c:url value="/resources/css/adminStyle.css"></c:url>'> 
<link href="https://fonts.googleapis.com/css2?family=Bagel+Fat+One&family=Caprasimo&family=Lato:wght@100&family=Nanum+Pen+Script&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
 <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>

</head>
<body>


 	<div id="container">
	<div id="sidebar-left">
          <tiles:insertAttribute name="side"/> 
      </div>
	<div id="content">		
		<tiles:insertAttribute name="body"/>
	</div>
	</div>
	
	  
    			<div id="login_form" title="로그인">
    		<fieldset>
    			<label for="loginId">아이디 : </label>
    				<input type="text" name="loginId" id="loginId" required="required" placeholder="아이디를 입력해주세요."><br><br>
    		
    			<label for="loginPwd">비밀번호 : </label>
    			<input type="text" name="loginPwd" id="loginPwd" required="required" placeholder="비밀번호를 입력해주세요."><br><br>
    			
    			<!--  <input type="button" value="로그인하기" id="login2">-->
    		</fieldset>
    	</div>
    	
    	<div id="searchId_dialog" title="아이디찾기">
    	<fieldset>
    			<label for="searchName1">이름 : </label>
    				<input type="text" name="searchName1" id="searchName1" required="required" placeholder="이름을 입력해주세요."><br><br>
    		
    			<label for="searchPhone1">핸드폰 번호 : </label>
    			<input type="text" name="searchPhone1" id="searchPhone1" required="required" placeholder="전화번호를 입력해주세요."><br><br>

    			<label for="searchId1">찾으신 아이디 : </label>
    			<input type="text" name="searchId1" id="searchId1"  placeholder="찾으신 아이디" disabled="disabled"><br><br>

    			<!--  <input type="button" value="로그인하기" id="login2">-->
    		</fieldset>
    	</div>
    	
      	<div id="searchPwd_dialog" title="비밀번호찾기">
    	<fieldset>
    
    			<label for="searchId2">아이디 : </label>
    			<input type="text" name="searchId2" id="searchId2" required="required" placeholder="아이디를 입력해주세요."><br><br>
    			
    			<label for="searchName2">이름 : </label>
    			<input type="text" name="searchName2" id="searchName2" required="required" placeholder="이름을 입력해주세요."><br><br>
    		
    			<label for="searchPhone2">핸드폰 번호 : </label>
    			<input type="text" name="searchPhone2" id="searchPhone2" required="required" placeholder="전화번호를 입력해주세요."><br><br>

    			<label for="searchPwd">핸드폰 번호 : </label>
    			<input type="text" name="searchPwd" id="searchPwd" placeholder="찾으신 비밀번호" disabled="disabled"><br><br>
    			<!--  <input type="button" value="로그인하기" id="login2">-->
    		</fieldset>
    	</div>
  
  
	  <div id="mypage_form" title="마이페이지">
	    <fieldset>
	        <label for="mypageId">아이디 : </label>
	        <input type="text" name="mypageId" id="mypageId" class="input-field" disabled="disabled"  value='<c:out value="${admin.uid}"/>'><br><br>
	
	        <label for="mypageName">이름 : </label>
	        <input type="text" name="mypageName" id="mypageName" class="input-field" disabled="disabled" value='<c:out value="${admin.name}"/>'><br><br>
	
	        <label for="mypagePwd">비밀번호 : </label>
	        <input type="text" name="mypagePwd" id="mypagePwd" class="input-field" disabled="disabled" value='<c:out value="${admin.pwd}"/>'><br><br>
	
	        <label for="mypagePhone">핸드폰 번호 : </label>
	        <input type="text" name="mypagePhone" id="mypagePhone" class="input-field" disabled="disabled" value='<c:out value="${admin.phone}"/>'><br><br>
	     
	        <label for="mypageEmail">이메일 : </label>
	        <input type="text" name="mypageEmail" id="mypageEmail" class="input-field" disabled="disabled" value='<c:out value="${admin.email}"/>'><br><br>
	    </fieldset>
	</div>
	
	 <script src='<c:url value="/resources/js/admin.js"/>'></script>
	 </body>
</html>  