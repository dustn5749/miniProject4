<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 정보 상세보기</title>
    <link rel="stylesheet" href='<c:url value="/resources/css/memberlist.css"></c:url>'>
     <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
</head>
<body>
<script>

    var currentPageNo = ${result.member.pageNo};
</script>

<div class="container">
		<input type="hidden" name="pageNo" id="pageNo1" value="${result.member.pageNo}" />
		<input type="hidden" name="pageLength" id="pageLength" value="${result.member.pageLength}" >
    <table>
        <caption>회원 정보 상세 보기</caption>
        <tr>
        </tr>
        <tr>
        <td><button id="checkDelete">선택 삭제</button></td>
        <td>
        </td>
        <td colspan="5"></td>
        </tr>
        <tr>
        	<th><input type="checkbox" id="allTermsCheck">전체선택</th>
            <th>번호</th>
            <th>이름</th>
            <th>아이디</th>
            <th>비밀번호</th>
            <th>전화번호</th>
        </tr>
        <tbody id="memberList">
        <c:set var="index" value="1" />
     	<c:forEach items="${result.memberList}"  var ="member">
            <tr class="memberTr">
             	<td><input type="checkbox" class="checkTerms"></td>
                <td><c:out value="${index}" /></td>
                <td class="memberName"><c:out value="${member.name}"/></td>
                <td class="memberUid"><c:out value="${member.uid}"/></td>
                <td class="memberPwd"><c:out value="${member.pwd}"/></td>
                <td class="memberPhone"><c:out value="${member.phone}"/></td>
            </tr>
    <c:set var="index" value="${index + 1}" />
     	</c:forEach>
     	</tbody>
     	 <tr id="memberItem" style="display:none;" >
                     <td><input type="checkbox" class="checkTerms" /></td>
                     <td id="indexNum"></td>
                     <td id="memberName"></td>
                     <td id="memberId"></td>
                     <td id="memberPwd"></td>
                     <td id="memberPhone"></td>
                  </tr>       
     	
    </table>
    <div id="btnDiv">
   <a id="homeBtn" href='<c:url  value="/admin.do"/>'>홈으로가기</a>    
    </div>
</div>

<div id="pageIndex">
    <c:if test="${result.member.navStart != 1}">
        <span class="pageLink" onclick="jsPageNo(${result.member.navStart - 1}); return false;" style="padding: 10px;"> &lt; </span>
    </c:if>
    <c:forEach var="item" begin="${result.member.navStart}" end="${result.member.navEnd}">
        <c:choose>
            <c:when test="${result.member.pageNo != item}">
                <span class="pageLink" onclick="jsPageNo(${item}); return false;" style="padding: 10px; margin: 0 auto; text-align: center;">${item}</span>
            </c:when>
            <c:otherwise>
                <span class="pageLink activePage" style="padding: 10px; margin: 0 auto; text-align: center;">${result.member.pageNo}</span>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:if test="${result.member.navEnd != result.member.totalPageSize}">
        <a href="#" onclick="jsPageNo(${result.member.navEnd + 1}); return false;"></a>
    </c:if>
</div>

</body>
<script src='<c:url value="/resources/js/memberlist.js"></c:url>'>
</script>
</html>