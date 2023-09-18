var selectBtn = document.querySelector("#checkDelete");
var homeBtn = document.querySelector("#homeBtn");


// 버튼위로 마우스 올릴 시 색 변화
	homeBtn.onmouseover = ()=>{
		homeBtn.style.color = "white";
		homeBtn.style.backgroundColor = "rgb(62, 164, 54)";
	}
	
	// 마우스가 버튼 밖으로 나갈 시 색 변화
	homeBtn.onmouseout =()=>{
		homeBtn.style.color = "";
		homeBtn.style.backgroundColor = "";
	}


	var allTermsCheck = document.querySelector("#allTermsCheck");
	var checkTerms = document.querySelectorAll(".checkTerms");
	
	// 체크 요소 
	allTermsCheck.addEventListener("click", allcheck);
	function allcheck(){
		if(allTermsCheck.checked){
			for(let i=0; i<checkTerms.length; ++i){
				checkTerms[i].checked = true;
			}
		} else {
			for(let i=0; i<checkTerms.length; ++i){
				checkTerms[i].checked = false;
			}
		}
	}
	for(let i=0; i<checkTerms.length; ++i){
		checkTerms[i].addEventListener("click", checkedAll);
	}
	
	function checkedAll(){
		let checkLength = checkTerms.length;
		let cnt = 0;
		for(let i=0; i<checkTerms.length; ++i){
		if(checkTerms[i].checked){
			cnt++;
		}
	}
	if(cnt == checkLength){
		allTermsCheck.checked = true;
	}
	}
	
// 멤버 삭제하기 
selectBtn.addEventListener("click", deleteMember);
function deleteMember(){
	let deleteIdList =[];
	let checkTerms = document.querySelectorAll(".checkTerms");
	for(let i=0; i<checkTerms.length; ++i){
		if(checkTerms[i].checked){
			 let memberId = checkTerms[i].parentNode.parentNode.querySelector("td:nth-child(4)").innerText;
			deleteIdList.push(memberId);
		}
	}

	let pageNo = document.querySelector("#pageNo1").value;
	let memberList = document.querySelector("#memberList");
	let items = document.querySelector(".checkTerms:checked");
	
	if(deleteIdList.length != 0){	
	const list= {
		uids:deleteIdList
	}
		fetch("/project4/admin/deleteMembers.do", {
        method: 'POST',
       	headers : {
                'Content-Type': 'application/json; charset=UTF-8'
		   },
		   body : JSON.stringify(list)
	})
	.then(response => response.json())
	.then(function(data) {
		if(data.result){
			alert("회원  삭제가 정상적으로 진행되었습니다.");
			location.href="/project4/admin/memberlist.do"
		}else {
			alert("회원 삭제에 실패했습니다.");
		}
	})
		
	} else {
		alert("삭제할 리스트가 없습니다.");
	}
}

// 페이지 이동하여 멤버값 가져오기
function jsPageNo(pageNo){
	document.querySelector("#pageNo1").value = pageNo;

	$(".pageLink").removeClass("activePage"); 
	$(".pageLink:nth-child(" + pageNo + ")").addClass("activePage");
	var send = {
		pageNo : pageNo,
		pageLength : $("#pageLength").val()
	}
	

	$.ajax({
		url : "/project4/admin/memberlist2.do",
	      type: 'POST',
	      contentType:   "application/json; charset=UTF-8",
	      data: JSON.stringify(send),
	      dataType: "json",
	      success: function (data) {
	    	var memberList = data.memberList;
	        var tbody = $("#memberList");
	        tbody.empty();
	        
	        $.each(memberList, function (index, member) {
	            var row = $("<tr class='memberTr'></tr>");

	            row.append("<td><input type='checkbox' class='checkTerms'></td>");
	            row.append("<td>" + (index + 1) + "</td>");
	            row.append("<td class='memberName'>" + member.name + "</td>");
	            row.append("<td class='memberUid'>" + member.uid + "</td>");
	            row.append("<td class='memberPwd'>" + member.pwd + "</td>");
	            row.append("<td class='memberPhone'>" + member.phone + "</td>");

	            tbody.append(row);
	          });
	    	
	   }
	})
	
}


