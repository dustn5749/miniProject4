<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
	<script type="text/javascript">
    // 페이지가 로드될 때 실행되는 함수
    window.onload = function () {
    	var uid = "<c:out value='${admin.uid}'/>";
        var pwd = "<c:out value='${admin.pwd}'/>";
        var name = "<c:out value='${admin.name}'/>";
        var phone = "<c:out value='${admin.phone}'/>";
        $("#mypageId").val(uid);
        $("#mypageName").val(name);
        $("#mypagePwd").val(pwd);
        $("#mypagePhone").val(phone);
    };
        
</script>


        <div id="slideShow">
          <div id="slides">
          	<p class="image-text">관리자 페이지 입니다</p>
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
              <c:forEach items="${top5List}" var="list"> 
                <li><a class="top5List">
                [<span class="top5boardNum"><c:out  value="${list.boardNum}"/></span>]  <span class="top5boardTitle"><c:out  value="${list.title}"/></span> 
                </a></li>                     
                <input type="hidden" value='<c:url value="/notice/detail.do"></c:url>' class="hiddenNum">
              </c:forEach>   

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

    
    <div id="dialog_form3" title="관리자 로그인">
    		<fieldset>
    			<label for="loginId">아이디 : </label>
    				<input type="text" name="loginId" id="loginId" required="required" placeholder="아이디를 입력해주세요."><br><br>
    		
    			<label for="loginPwd">비밀번호 : </label>
    			<input type="text" name="loginPwd" id="loginPwd" required="required" placeholder="비밀번호를 입력해주세요."><br><br>
    			
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
	    </fieldset>
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


     <button id="openDialogBtn" style="display: none;">다이얼로그 열기</button>

