<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board</title>
<link rel="stylesheet" href='<c:url value="/resources/css/boardForm.css"></c:url>'>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
 <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
 <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>

</head>
<body>

<div id="container">

<a href='<c:url value="/index/index.do"/>'><img  src=  '<c:url value="/resources/images/logo1.png"/>'  id="logo"></a>
<table id="content-boardList">
        <tr>
            <td id="boardListTitle" colspan="5">게시판 목록</td>
        </tr>
        <tr>
           <form name="pageForm" id="pageForm" action="<c:url value='/board/list.do'/>" method="post" >
				<input type="hidden" name="pageNo" id="pageNo1" value="${result.board.pageNo}" />
				<input type="hidden" name="searchTitle" id="searchTitle" value="${result.board.searchTitle}" >
				<input type="hidden" name="pageLength" id="pageLength" value="${result.board.pageLength}" >
           </form>
        	<form id="searchFm" action='<c:url value="/board/list.do"/>'  method="post" >
			<input type="hidden" name="pageNo" id="pageNo2" value="${result.board.pageNo}" />
        	<td>
        	<select name="pageLength" id="pageLength">
        	<option value="10" ${result.board.pageLength == 10 ? 'selected="selected"' : ''} >10건</option>
        	<option value="20"  ${result.board.pageLength == 20 ? 'selected="selected"' : ''}>20건</option>
        	<option value="30" ${result.board.pageLength == 30 ? 'selected="selected"' : ''}>30건</option>
        	<option value="50" ${result.board.pageLength == 50 ? 'selected="selected"' : ''}>50건</option>
        	<option value="100"  ${result.board.pageLength == 100 ? 'selected="selected"' : ''}>100건</option>
        	</select>
        	
        	</td>
            <td colspan="3"><input type="text" placeholder="검색어를 입력해주세요." id="searchTitle"  name="searchTitle"  value="${result.board.searchTitle}"></td>
           	<td id="searchBtnTd"><button id="searchBtn"><img src='<c:url value="/resources/images/search.png"/>'></button></td>
            </form>
            <td colspan="3" id="btnSpace">
                <button id="newBoard">새글작성</button>
            </td>
        </tr>
        <tr>
            <td class="boardMenu">번호</td>
            <td class="boardMenu boardTitle" >제목</td>
            <td class="boardMenu">작성자</td>
            <td class="boardMenu">작성일</td>
            <td class="boardMenu">조회수</td>
            <td class="boardMenu">상세보기</td>
     </tr>
      <tbody id="listArea">
        <tr>
		<c:forEach items="${result.boardList}" var="list" >
            <tr >
                <td class="boardNum"><input class="boardInfoNum" value='<c:out value="${list.boardNum}" />' name="boardNum" readonly="readonly"></td>
                 <td style="text-align: left">
                     <span style="padding-left:${(list.level-1)*30}px"></span>
                	<c:if test="${list.level != 1}">
                	↪[답글]
                	</c:if>
           		 <c:out value="${list.title}"></c:out>
                <td><c:out value="${list.id}" /></td>
                <td><c:out value="${list.regdate}" /></td>
                <td><c:out value="${list.readcount}" /></td>
        		<td><button class="detailBtn">상세보기</button></td>
            </tr>	
		</c:forEach>
		</tbody>
			 <c:if test="${empty result.boardList}">
	                <tr>
	                		<td colspan=7>검색결과가 없습니다</td>
	                 	</tr>
	                </c:if>
        </tr>
    </table>
	<div style="text-align: center;margin-top:20px;">

		           	<c:if test="${result.board.navStart != 1}">
		           		<a href="#" onclick="jsPageNo(${result.board.navStart-1})" style="padding: 10px;"> &lt; </a> 
		           	</c:if>
		           	<c:forEach var="item" begin="${result.board.navStart}" end="${result.board.navEnd}">
		           		<c:choose>
		           			<c:when test="${result.board.pageNo != item }">
		           				<a href="#" onclick="jsPageNo(${item})" style="padding: 10px;">${item}</a>  
		           			</c:when>
		           			<c:otherwise>
		           				<strong style="font-size:1.3rem">${item}</strong>   
		           			</c:otherwise>
		           		</c:choose>
		           	</c:forEach>
		           	<c:if test="${result.board.navEnd != result.board.totalPageSize}">
		           		<a href="#" onclick="jsPageNo(${result.board.navEnd+1})" style="padding: 10px;"> &gt; </a> 
		           	</c:if>
	           	
	           	</div>
    </div>
  <div id="dialog-form" title="게시글 정보">
    <fieldset>
      <label for="title">제목</label>
      <input type="text" name="title" id="seletedtitle" class="info" disabled="disabled">
      <label for="boardnum">글번호</label>
      <input type="text" name="boardnum" id="seletedboardnum" class="info" disabled="disabled">
      <label for="id">작성자</label>
      <input type="text" name="id" id="seletedid"  class="info" disabled="disabled"><br>
      <label for="regdate">작성일</label>
      <input type="date" name="regdate" id="seletedregdate" class="info" disabled="disabled">
       <label for="readcount">조회수</label>
      <input type="text" name="readcount" id="seletedreadcount" class="info" disabled="disabled">
		<div id="attachFileList"></div>
      </table>
    <label for="content">내용</label>
      <input type="text" name="content" id="seletedcontent"class="info" disabled="disabled"><br>
      <!-- Allow form submission with keyboard without duplicating the dialog button -->
      <input type="submit" tabindex="-1" style="position:absolute; top:-1000px"><br>
    <hr>
    <div id="commentDiv"> 
    <div id="commentList"></div>
    <div id="plus"></div>
	<div id="replyContent">
	<c:if test="${loginMember != null}">
	<span id="idSpan">${loginMember.uid}</span>
	<input type="text" id="reply" placeholder="댓글을 남겨보세요.">
	<span id="replyBtnSpan"><input type="button" value="등록" id="replyBtn"></span>
	</c:if>
	</div>
    </div>  
    </fieldset>
</div>
<input type="hidden" id="writerId" value="${loginMember.uid}" >


<div id="dialog-form2" title="게시글 작성하기">
      <form id="attachForm" enctype="multipart/form-data">
    <fieldset>
      <label for="title">제목</label>
      <input type="text" name="title" id="newtitle" class="info" >
      <label for="boardnum">글번호</label>
      <input type="text" name="boardNum" id="newboardnum" class="info" readonly="readonly">
      
      <label for="id">작성자</label>
      <input type="text" name="id" id="newid" value="${loginMembe.uid}" class="info" readonly="readonly"><br>
      <label for="regdate">작성일</label>
      <input type="date" name="regdate" id="newregdate" class="info" disabled="disabled">
       <label for="readcount">조회수</label>
      <input type="text" name="readcount" id="newreadcount" class="info" disabled="disabled">
      <table class="attacheTb">
      	<tr>
      		<td  class="attacheFile"><button class="attacheFileBtn">첨부파일</button></td>
      		<td class="attachFileInfo"><div class="d_file"></div></td>
      	</tr>      	
      </table>
    <label for="content">내용</label>
     <input type="text" name="content" id="newcontent"class="info" ><br>
      <!-- Allow form submission with keyboard without duplicating the dialog button -->
    </fieldset>
      </form>
</div>

<div id="replyWrite-form" title="답글 작성하기">
    <fieldset>
    <form id="attachForm2" enctype="multipart/form-data">
      <label for="title">제목</label>
      <input type="text" name="title" id="replytitle" class="info" >
      <label for="boardnum">글번호</label>
      <input type="text" name="boardNum" id="replyboardnum" class="info" disabled="disabled">
      <label for="id">작성자</label>
      <input type="text" name="id" id="replyid" value="${loginMembe.uid}  class="info"readonly="readonly"><br>
      <label for="regdate">작성일</label>
      <input type="date" name="replyregdate" id="replyregdate" class="info" disabled="disabled">
       <label for="readcount">조회수</label>
      <input type="text" name="replyreadcount" id="replyreadcount" class="info" disabled="disabled">
      <input type="hidden" name="pnum" id="replyPboardnum" class="info" >
      
      <table id="attacheTb">
      	<tr>
      		<td  class="attacheFile"><button class="attacheFileBtn">첨부파일</button></td>
      		<td class="attachFileInfo"><div class="d_file"></div></td>
      	</tr>      	
      </table>
    <label for="content">내용</label>
     <input type="text" name="content" id="replycontent2"class="info" ><br>
      <!-- Allow form submission with keyboard without duplicating the dialog button -->
      <input type="submit" tabindex="-1" style="position:absolute; top:-1000px"><br>
    </form>  
    </fieldset>
</div>

<button id="openDialogBtn" style="display: none;">다이얼로그 열기</button>
</body>



<script src='<c:url value="/resources/js/boardList.js"/>' > </script>

<script type="text/javascript">
document.querySelector("#searchFm").addEventListener("submit", e => {
	console.log(document.querySelector("#searchTitle").value);
	document.querySelector("#searchFm > #pageNo2").value = "1";
	return true;
});

</script>

</html>