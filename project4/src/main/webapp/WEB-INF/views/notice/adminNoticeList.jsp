<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Notice</title>
<link rel="stylesheet" href='<c:url value="/resources/css/boardForm.css"></c:url>'>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
 <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
  

</head>
<body>
<div id="container">
<a href='<c:url value="/admin.do"/>'><img  src=  '<c:url value="/resources/images/logo1.png"/>'  id="logo"></a>
 <table id="content-boardList">
        <tr>
            <td id="boardListTitle" colspan="5">공지사항 목록</td>
        </tr>
        <tr>
           <form name="pageForm" id="pageForm" action="<c:url value='/notice/list.do'/>" method="post" >
				<input type="hidden" name="pageNo" id="pageNo1" value="${result.notice.pageNo}" />
				<input type="hidden" name="searchTitle" id="searchTitle" value="${result.notice.searchTitle}" >
				<input type="hidden" name="pageLength" id="pageLength" value="${result.notice.pageLength}" >
           </form>
        	<form id="searchFm" action='<c:url value="/notice/list.do"/>'  method="post" >
			<input type="hidden" name="pageNo" id="pageNo2" value="${result.notice.pageNo}" />
        	<td>
        	<select name="pageLength" id="pageLength">
        	<option value="10" ${result.notice.pageLength == 10 ? 'selected="selected"' : ''} >10건</option>
        	<option value="20"  ${result.notice.pageLength == 20 ? 'selected="selected"' : ''}>20건</option>
        	<option value="30" ${result.notice.pageLength == 30 ? 'selected="selected"' : ''}>30건</option>
        	<option value="50" ${result.notice.pageLength == 50 ? 'selected="selected"' : ''}>50건</option>
        	<option value="100"  ${result.notice.pageLength == 100 ? 'selected="selected"' : ''}>100건</option>
        	</select>
        	
        	</td>
            <td colspan="3"><input type="text" placeholder="검색어를 입력해주세요." id="searchTitle"  name="searchTitle"  value="${result.notice.searchTitle}"></td>
           	<td id="searchBtnTd"><button id="searchBtn"><img src='<c:url value="/resources/images/search.png"/>'></button></td>
            </form>
            <c:if test="${loginMember.uid=='admin'}">
            <td colspan="3" id="btnSpace">
                <button id="newBoard">새글작성</button>
            </td>
            </c:if>
        </tr>
        
        <tr>
        <c:if test="${admin.uid=='admin'}">
            <td id="checkAll">
                <input type="checkbox" name="boardNo" value="0" id="allTermsCheck">전체선택
            </td>
            <td >
                <button id="checkDelete">선택 삭제</button>
            </td>
            </c:if>
        </tr>
        <tr>
        <c:if test="${admin.uid=='admin'}">
            <td class="boardMenu">선택</td>
            </c:if>
            <td class="boardMenu">번호</td>
            <td class="boardMenu">제목</td>
            <td class="boardMenu">작성자</td>
            <td class="boardMenu">작성일</td>
            <td class="boardMenu">조회수</td>
            <td class="boardMenu">상세보기</td>
     </tr>
        <tr>
		<c:forEach items="${result.noticeList}" var="list" >
            <tr >
            	<c:if test="${admin.uid=='admin'}">
                <td><input type="checkbox" class="checkTerms"> </td>
                </c:if>
                <td class="boardNum"><input class="boardInfoNum" value='<c:out value="${list.boardNum}" />' name="boardNum" readonly="readonly"></td>
                <td><!-- <a class="boardInfoPage" href="#" > --><c:out value="${list.title}" /><!-- </a> --></td>
                <td><c:out value="${list.id}" /></td>
                <td><c:out value="${list.regdate}" /></td>
                <td><c:out value="${list.readcount}" /></td>
        		<td><button class="detailBtn">상세보기</button></td>
            </tr>	
		</c:forEach>
			 <c:if test="${empty result.noticeList}">
	                <tr>
	                		<td colspan=7>검색결과가 없습니다</td>
	                 	</tr>
	                </c:if>
        </tr>
    </table>
		<div style="text-align: center;margin-top:20px;">
			<c:if test="${result.notice.navStart != 1}">
			<a href="#" onclick="jsPageNo(${result.notice.navStart-1})"style="padding: 10px;"> &lt; </a>
			</c:if>		
		    <c:forEach var="item" begin="${result.notice.navStart}" end="${result.notice.navEnd}">
		    	<c:choose>
		    		<c:when test="${result.notice.pageNo != item}">
		    			<a href="#" onclick="jsPageNo(${item})" style="padding: 10px;">${item}</a>  
		    		</c:when>
		    		<c:otherwise>
		    			<strong style="font-size:1.3rem">${item}</strong>  
		    		</c:otherwise>
		    	</c:choose>
		    </c:forEach>
		    <c:if test="${result.notice.navEnd != result.notice.totalPageSize}">
		    	<a href="#" onclick="jsPageNo(${result.notice.navEnd+1})" style="padding: 10px;"> &gt; </a>
		    </c:if>
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
<input type="hidden" id="writerId" value="${loginMember.uid}" >


<div id="dialog-form2" title="게시글 작성하기">
    <fieldset>
      <label for="title">제목</label>
      <input type="text" name="newtitle" id="newtitle" class="info" >
        <label for="fixed_yn">고정여부
      Yes<input type="radio" name="fixed_yn" value="Y" id="newfixed_y" class="info" >
      No<input type="radio" name="fixed_yn" value="N"class="info" id="newfixed_n">
      </label>
      <label for="boardnum">글번호</label>
      <input type="text" name="newboardnum" id="newboardnum" class="info" disabled="disabled">
      <label for="id">작성자</label>
      <input type="text" name="newid" id="newid"  class="info" disabled="disabled"><br>
      <label for="regdate">작성일</label>
      <input type="date" name="newregdate" id="newregdate" class="info" disabled="disabled">
       <label for="readcount">조회수</label>
      <input type="text" name="newreadcount" id="newreadcount" class="info" disabled="disabled">
    <label for="content">내용</label>
      <input type="text" name="newcontent" id="newcontent"class="info" ><br>
      <!-- Allow form submission with keyboard without duplicating the dialog button -->
      <input type="submit" tabindex="-1" style="position:absolute; top:-1000px"><br>
    </fieldset>
</div>
	<input type="hidden" value="${loginMember.uid}" id="loginId">
    <button id="openDialogBtn" style="display: none;">다이얼로그 열기</button>
</body>



<script src='<c:url value="/resources/js/noticeList.js"/>' > </script>

<script type="text/javascript">
document.querySelector("#searchFm").addEventListener("submit", e => {
	console.log(document.querySelector("#searchTitle").value);
	document.querySelector("#searchFm > #pageNo2").value = "1";
	return true;
});

</script>

</html>