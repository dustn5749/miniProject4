<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<script type="text/javascript">
    // 페이지가 로드될 때 실행되는 함수
    window.onload = function () {
    	var uid = "<c:out value='${loginMember.uid}'/>";
        var pwd = "<c:out value='${loginMember.pwd}'/>";
        var name = "<c:out value='${loginMember.name}'/>";
        var phone = "<c:out value='${loginMember.phone}'/>";
        var email = "<c:out value='${loginMember.email}'/>";
        $("#mypageId").val(uid);
        $("#mypageName").val(name);
        $("#mypagePwd").val(pwd);
        $("#mypagePhone").val(phone);
        $("#mypageEmail").val(email);
    };
        
</script>

        <div id="slideShow">
          <div id="slides">
            <img src='<c:url value="/resources/images/photo1.jpg"></c:url>' alt="">
            <img src='<c:url value="/resources/images/photo2.jpg"></c:url>' alt="">
            <img src='<c:url value="/resources/images/photo3.jpg"></c:url>' alt="">
            <img src='<c:url value="/resources/images/photo4.jpg"></c:url>' alt="">
            <button id="prev">&lang;</button>
            <button id="next">&rang;</button>
          </div>
        </div>
        <div id="contents">
          <div id="tabMenu">
            <input type="radio" id="tab1" name="tabs" checked>
            <label for="tab1">공지사항</label>
            <input type="radio" id="tab2" name="tabs">
            <label for="tab2">갤러리</label>      
            <div id="notice" class="tabContent">
              <ul>  
              <c:choose>
            <c:when test="${empty top5List}">
            	검색 결과가 없습니다
            </c:when>  
			<c:otherwise>                    
              <c:forEach items="${top5List}" var="list"> 
                <li><a class="top5List">
                [<span class="top5boardNum"><c:out  value="${list.boardNum}"/></span>]  <span class="top5boardTitle"><c:out  value="${list.title}"/></span> 
                </a></li>                     
                <input type="hidden" value='<c:url value="/notice/detail.do"></c:url>' class="hiddenNum">
              </c:forEach>
              </c:otherwise>   
			</c:choose>
              </ul>
            </div>
            <div id="gallery" class="tabContent">
              <h2>갤러리 내용입니다.</h2>
              <ul>
                <li><img src='<c:url value="/resources/images/pet1.jpeg"></c:url>'></li>
                <li><img src='<c:url value="/resources/images/pet2.jpeg"></c:url>'></li>
                <li><img src='<c:url value="/resources/images/pet3.jpeg"></c:url>'></li>

              </ul>
            </div>      
          </div>
               <div id="links">
            <ul>
              <li>
                <a href="#">
                  <span id="quick-icon1">
                  </span>
                  <p>kosa3기</p>
                </a>
              </li>
              <li>
                <a href="#">
                  <span id="quick-icon2"></span>
                  <p>커리큘럼</p>
                </a>            
              </li>
              <li>
                <a href="#">
                  <span id="quick-icon3"></span>
                  <p>문의하기</p>
                </a>            
              </li>
            </ul>
          </div>
        </div>
      </div>


   <div id="dialog-form" title="게시글 정보">
    <fieldset>
      <label for="title">제목</label>
      <input type="text" name="title" id="seletedtitle" class="info" disabled="disabled">
       <label for="fixed_yn">고정여부
      Yes<input type="radio" name="fixed_yn" value="Y" id="fixed_y" class="info" disabled="disabled">
      No<input type="radio" name="fixed_yn" value="N"class="info" id="fixed_n"disabled="disabled">
      </label>
      <label for="boardnum">글번호</label>
      <input type="text" name="boardnum" id="seletedboardnum" class="info" disabled="disabled">
      <label for="id">작성자</label>
      <input type="text" name="id" id="seletedid"  class="info" disabled="disabled"><br>
      <label for="regdate">작성일</label>
      <input type="date" name="regdate" id="seletedregdate" class="info" disabled="disabled">
       <label for="readcount">조회수</label>
      <input type="text" name="readcount" id="seletedreadcount" class="info" disabled="disabled">
    <label for="content">내용</label>
      <input type="text" name="content" id="seletedcontent"class="info" disabled="disabled"><br>
      <!-- Allow form submission with keyboard without duplicating the dialog button -->
      <input type="submit" tabindex="-1" style="position:absolute; top:-1000px"><br>
    </fieldset>
	</div>
	
	  <div id="dialog-form2" title="회원가입하기">
        <form id="joinForm" class="dialogform">
            <fieldset>
                <legend>kosa 3기 회원 가입에 오신 여러분을 환영합니다.</legend>
                <!-- 회원가입 폼 요소 -->
                <label for="uid">아이디:</label>
                <input type="text" id="uid" name="uid" placeholder="아이디" required>
                <input type="button" id="idCheckBtn" value="아이디 중복검사">
                <br><br>

                <label for="pwd">비밀번호:</label>
                <input type="password" id="pwd" name="pwd" placeholder="비밀번호" maxlength="12" required><br><br>

                <label for="pwd2">비밀번호 확인:</label>
                <input type="password" id="pwd2" name="pwd2" placeholder="비밀번호 확인" required><br><br>

                <label for="name">이름:</label>
                <input type="text" id="name" name="name" placeholder="이름" required><br><br>

                <label for="phone">전화번호:</label>
                <input type="tel" id="phone" name="phone" placeholder="전화번호" required><br><br>

                <label for="email">이메일:</label>
                <input type="text" id="email" name="email" placeholder="이메일" required><br><br>
                <!-- 동의사항 -->
                <table>
                    <tr class="inputTr">
                        <td style="width: 500px;">
                            <input type="checkbox" id="allCheck">
                            <span>[전체] 모두 확인했으며 동의합니다.</span>
                        </td>
                    </tr>
                    <tr class="inputTr">
                        <td id="info">전체 동의에는 필수 및 선택 정보에 대한 동의가 포함되어 있으며, 개별적으로 동의를 선택 하실 수 있습니다. 선택 항목에 대한 동의를 거부하시는 경우에도 서비스 이용이 가능합니다.</td>
                    </tr>
                </table>

                <!-- 동의사항 체크박스 목록 -->
                <table id="checkboxTb">
                    <tr class="inputTr">
                        <td>
                            <input type="checkbox" class="checkTermsA">
                        </td>
                        <td class="checkboxInfo">[필수] 만 14세 이상입니다.</td>
                    </tr>
                    <tr class="inputTr">
                        <td>
                            <input type="checkbox" class="checkTermsA">
                        </td>
                        <td class="checkboxInfo">[필수] 이용약관 동의</td>
                    </tr>
                    <!-- 다른 필수 항목 추가 -->
                    <tr class="inputTr">
                        <td>
                            <input type="checkbox" class="checkTermsB">
                        </td>
                        <td class="checkboxInfo">[선택] 마케팅 목적의 개인정보 수집 및 이용 동의</td>
                    </tr>
                    <tr class="inputTr">
                        <td>
                            <input type="checkbox" class="checkTermsB">
                        </td>
                        <td class="checkboxInfo">[선택] 광고성 정보 수신 동의</td>
                    </tr>
                </table>
            </fieldset>
        	</form>
		</div>
    
    
    	<div id="dialog_form3" title="로그인">
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
	        <input type="text" name="mypageId" id="mypageId" class="input-field" disabled="disabled"><br><br>
	
	        <label for="mypageName">이름 : </label>
	        <input type="text" name="mypageName" id="mypageName" class="input-field" disabled="disabled"><br><br>
	
	        <label for="mypagePwd">비밀번호 : </label>
	        <input type="text" name="mypagePwd" id="mypagePwd" class="input-field" disabled="disabled"><br><br>
	
	        <label for="mypagePhone">핸드폰 번호 : </label>
	        <input type="text" name="mypagePhone" id="mypagePhone" class="input-field" disabled="disabled"><br><br>
	     
	        <label for="mypageEmail">이메일 : </label>
	        <input type="text" name="mypageEmail" id="mypageEmail" class="input-field" disabled="disabled"><br><br>
	    </fieldset>
	</div>

     <button id="openDialogBtn" style="display: none;">다이얼로그 열기</button>

