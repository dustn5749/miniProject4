var slides = document.querySelectorAll("#slides > img");
var prev = document.getElementById("prev");
var next = document.getElementById("next");
var logoutbtn = document.querySelector("#logoutbtn");
var boardBtn = document.querySelector("#board");

boardBtn.addEventListener("click", function(){
	document.querySelector("#boardSortFm").submit();
})
var current = 0;

		
showSlides(current);
prev.onclick = prevSlide;
next.onclick = nextSlide;

// 슬라이드 쇼 보여주기 
function showSlides(n) {
  for (let i = 0; i < slides.length; i++) {
	 // 모든 슬라이드 이미지 숨김 
    slides[i].style.display = "none";
  }
  // 현재 인덱스에 해당하는 슬라이드 이미지 보여줌  
  slides[n].style.display = "block";
}

// 클릭시 이전슬라이드로 이동 
function prevSlide() {
  if (current > 0) current -= 1;
  else
    current = slides.length - 1;
    showSlides(current);
}

// 클릭시 다음 슬라이드로 이동 
function nextSlide() {
  if (current < slides.length - 1) current += 1;
  else
    current = 0;
    showSlides(current);  
}

//dialog에 대한 정보 설정 ( 상세보기 )
var top5List = document.querySelectorAll(".top5List");
var dialog = $("#dialog-form").dialog({
			autoOpen : false,
			modal : true,
			height : 600,
			width:600,
			buttons : {
				닫기 : function(){
					$(this).dialog("close");
		}
		}
})



//공지사항 클릭시 정보 보기 
top5List.forEach((list) => list.addEventListener("click", function(){
let boardNumElement = list.querySelector(".top5boardNum"); // 해당 list 내의 top5boardNum 요소 선택
let boardnum= "";
  if (boardNumElement) {
     boardnum  = boardNumElement.innerHTML;
  }
  
  var send = {
		        boardNum: boardnum
	}
	
      $.ajax({
      url: "/project4/notice/detail.do",
      type: 'POST',
      contentType:   "application/json; charset=UTF-8",
      data: JSON.stringify(send),
      dataType: "json",
      success: function (data) {
          if (data.result) {
              $("#seletedtitle").val(data.notice.title);
              $("#seletedid").val(data.notice.id);
              $("#seletedboardnum").val(data.notice.boardNum);
              $("#seletedregdate").val(data.notice.regdate);
              $("#seletedcontent").val(data.notice.content);
              $("#seletedreadcount").val(data.notice.readcount);
              if(data.notice.fixed_yn =="Y"){
                  $("#fixed_n").prop("checked", true);
              } else {
                  $("#fixed_y").prop("checked", true);
              }
              $("#openDialogBtn").click();
          } else {
              alert("상세보기 실패");
          }
      }
  });
  $("#openDialogBtn").click(function() {
      dialog.dialog("open");
  });
  
}));




// 회원가입창에 대한 dialog 설정
$("#dialog-form2").dialog({
autoOpen: false,
modal: true,
height: 700,
width: 500,
buttons: {
    가입하기: addMember,
    닫기: function () {
        $(this).dialog("close");
    }
}
});

//가입하기 버튼 클릭 이벤트 처리
$("#joinBtn").click(function () {
$("#dialog-form2").dialog("open");
});


//아이디 중복 검사
$("#idCheckBtn").on("click", function() {
	var uid = $("#uid").val();
	var send = {
			uid : uid
	}
	
	$.ajax({
		url : "/project4/member/existUid.do",
		 type: "POST", // HTTP 요청 메서드 선택 (POST 또는 GET)
		    contentType: "application/json; charset=UTF-8",
		    data: JSON.stringify(send),
		    dataType: "json",
		    success: function (data) {
		        if (data.result) {
		            alert("사용가능한 아이디입니다.");
		        } else {
		        	alert("이미 가입된 아이디입니다.");
		        	$("#uid").val("");
		        }
		    },
		    error: function () {
		        alert("아이디 중복 검사에 실패하였습니다.");
		    }
	})
})

function addMember() {
// 회원가입 폼 데이터를 수집
var uid = $("#uid").val();
var pwd = $("#pwd").val();
var pwd2 = $("#pwd2").val();
var name = $("#name").val();
var phone = $("#phone").val();
var email =  $("#email").val();

// 회원가입 폼 유효성 검사
if (uid === "" || pwd === "" || pwd2 === "" || name === "" || phone === "" || email === "") {
    alert("모든 필드를 입력하세요.");
    return;
}

if (pwd !== pwd2) {
    alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
    return;
}

var send = {
    uid: uid,
    pwd: pwd,
    name: name,
    phone: phone,
    email : email
};

// 회원가입 
$.ajax({
    url: "/project4/member/addMember.do", 
    type: "POST", 
    contentType: "application/json; charset=UTF-8",
    data: JSON.stringify(send),
    dataType: "json",
    success: function (data) {
        if (data.result) {
            alert("회원가입이 완료되었습니다.");
            $("#dialog-form2").dialog("close"); 
        } else {
            alert("회원가입에 실패했습니다. 다시 시도해주세요.");
        }
    },
    error: function () {
        alert("회원가입 중 오류가 발생했습니다.");
    }
});
}

var allCheck = document.querySelector("#allCheck");
var checkTermsA = document.querySelectorAll(".checkTermsA");
var checkTermsB = document.querySelectorAll(".checkTermsB");

//전체 클릭시 모든 요소 체크 해제시 모든 요소 해제
allCheck.onclick =()=>{
 if(allCheck.checked){
     for(let i=0; i<checkTermsA.length; ++i){
         checkTermsA[i].checked = true;
     }
     for(let i=0; i<checkTermsB.length; ++i){
         checkTermsB[i].checked = true;
     }
 } else {
     for(let i=0; i<checkTermsA.length; ++i){
         checkTermsA[i].checked = false;
     }
     for(let i=0; i<checkTermsB.length; ++i){
         checkTermsB[i].checked = false;
     }
 }
}
//모든 요소 클릭 시 전체 요소 체크
for(let i=0; i<checkTermsA.length; ++i){
	if(checkTermsA[i].checked){
		checkTermsA[i].addEventListener("click", allCheckd)
	}
}
for(let i=0; i<checkTermsB.length; ++i){
	if(checkTermsB[i].checked){
		checkTermsB[i].addEventListener("click", allCheckd)
	}
}

function allCheckd(){
	let Termslenght = checkTermsA.length + checkTermsB.length;
	let cnt = 0;
	for(let i=0; i<checkTermsA.length; ++i){
	if(checkTermsA[i].checked){
		cnt++;
	}
}
for(let i=0; i<checkTermsB.length; ++i){
	if(checkTermsB[i].checked){
		cnt++;
		}
}
if(cnt == Termslenght){
	allCheck.checked = true;
} else {
	allCheck.checked = false;

}
}


//로그인하기
$("#dialog_form3").dialog({
autoOpen: false,
modal: true,
height: 350,
width: 500,
buttons: {
    로그인하기: login,
    아이디찾기 : searchId,
    비밀번호찾기 : searchPwd,
    닫기: function () {
        $(this).dialog("close");
    }
}
});


$("#loginBtn").on("click", function(){
$("#dialog_form3").dialog("open")
})

function login() {
var send = {
      uid: $("#loginId").val(),
      pwd : $("#loginPwd").val()
}

$.ajax({
    url: "/project4/member/login.do", 
    type: "POST", 
    contentType: "application/json; charset=UTF-8",
    data: JSON.stringify(send),
    dataType: "json",
    success: function (data) {
        if (data.result) {
           alert("성공적으로 로그인되었습니다.");
           $("#dialog_form3").dialog("close");
           
            location.href= "/project4/index/index.do";
            
        } else {
            alert("아이디 비밀번호를 다시 확인해주세요.");
        }
    },
    error: function () {
        alert("로그인 중 오류가 발생했습니다.");
    }
});

}

//아이디 찾기
$("#searchId_dialog").dialog({
	autoOpen: false,
	modal: true,
	height: 470,
	width: 500,
	buttons: {
	    아이디찾기: returnId,
	    닫기 : function () {
	        $(this).dialog("close");
	    }
	    	
	}
})


function searchId() {
	$("#searchId_dialog").dialog("open");
}


function returnId () {
	
	var name = $("#searchName1").val();
	var phone =$("#searchPhone1").val(); 
	
	if(name == "" || phone == ""){
		alert("이름, 전화번호를 모두 입력해주세요.");
	}else {
		var send = {
				name : name,
				phone: phone
			}
			
			
			$.ajax({
		        url: "/project4/member/searchId.do",
		        type: "POST",
		        data: JSON.stringify(send),
		        contentType: "application/json; charset=UTF-8",
		        dataType: "json",
		        success: function(data) {
		            if (data.result) {
		                $("#searchId1").val(data.uid)
		            } else {
		                alert("일치하는 정보가 없습니다. 다시 확인하고 입력해주세요.");
		            }
		        }
			})
	}
	

}


//비밀번호찾기
$("#searchPwd_dialog").dialog({
	autoOpen: false,
	modal: true,
	height: 470,
	width: 500,
	buttons: {
	    비밀번호찾기: returnPwd,
	    닫기 : function () {
	        $(this).dialog("close");
	    }
	}
})
function searchPwd() {
	$("#searchPwd_dialog").dialog("open")
}

function returnPwd() {
	
	var id= $("#searchId2").val();
	var name = $("#searchName2").val();
	var phone =$("#searchPhone2").val(); 
	
	if(id == "" || name =="" || phone==""){
		alert("정보를 모두 입력해주세요.");
	}else {
		var send = {
				uid : id,
				name : name,
				phone: phone
			}
			
			$.ajax({
		        url: "/project4/member/searchPwd.do",
		        type: "POST",
		        data: JSON.stringify(send),
		        contentType: "application/json; charset=UTF-8",
		        dataType: "json",
		        success: function(data) {
		            if (data.result) {
		                $("#searchPwd").val(data.pwd);
		            } else {
		                alert("일치하는 정보가 없습니다. 다시 확인하고 입력해주세요.");
		            }
		        }
			})
	}
	
	
}
// 로그아웃하기
$("#logoutbtn").on("click", function() {
    $.ajax({
        url: "/project4/member/logout.do",
        type: "POST",
        contentType: "application/json; charset=UTF-8",
        dataType: "json",
        success: function(data) {
            if (data.result) {
                alert("로그아웃이 완료되었습니다.");
               location.href="/project4/index/index.do";
            } else {
                alert("로그아웃 실패");
            }
        }
        
    });
});
	
// 마이페이지 띄우기
$("#mypage_form").dialog({
	autoOpen: false,
	modal: true,
	height: 500,
	width: 500,
	buttons: {
	    수정하기: enableInputs,
	    수정완료: updateMember,
	    탈퇴하기: deleteMember,
	    닫기: function () {
	        $(this).dialog("close");
	    }
	}
})

$("#mypageBtn").on("click", function() {
	$("#mypage_form").dialog("open")
})
//회원 수정창 활성화
function enableInputs() {
	 $("#mypageName").prop("disabled", false);
	 $("#mypagePhone").prop("disabled", false);
	 $("#mypagePwd").prop("disabled", false);
	 $("#mypageEmail").prop("disabled", false);
}


// 회원 정보 수정하기
function updateMember() {
	 var send = {
		uid : $("#mypageId").val(),
		pwd : $("#mypagePwd").val(),
		name : $("#mypageName").val(),
		phone : $("#mypagePhone").val(),
		email : $("#mypageEmail").val()
		
	 }
	    $.ajax({
	        url: "/project4/member/updateMember.do",
	        type: "POST",
	        data: JSON.stringify(send),
	        contentType: "application/json; charset=UTF-8",
	        dataType: "json",
	        success: function(data) {
	            if (data.result) {
	                alert("회원정보 수정에 성공하였습니다");
	                $("#mypage_form").dialog("close");
	           	 	$("#mypageName").prop("disabled", true);
	           	 	$("#mypagePhone").prop("disabled", true);
	           	 	$("#mypagePwd").prop("disabled", true);
	           	 	$("#mypageEmail").prop("disabled", true);
	           	 	$("#mypageName").val(data.member.uid);
	           	 	$("#mypagePhone").val(data.member.phone);
	           	 	$("#mypagePwd").val(data.member.pwd);
	           	 	$("#mypageEmail").val(data.member.email);
	           	 	$("#mypage_form").dialog("open");
	        	 
	            } else {
	                alert("회원 수정 실패");
	            }
	        }
	        
	    });
	 
	 
}

//회원탈퇴하기
function deleteMember() {
	if(confirm("정말 탈퇴하시겠습니까?")){
	
	var send = {
			uid : $("#mypageId").val()
		}

  $.ajax({
      url: "/project4/member/delete.do",
      type: "POST",
      data: JSON.stringify(send),
      contentType: "application/json; charset=UTF-8",
      dataType: "json",
      success: function(data) {
          if (data.result) {
              alert("회원 탈퇴에 성공하였습니다.");
              $("#mypage_form").dialog("close");
              location.href="/project4/index/index.do"
          } else {
              alert("회원 탈퇴 실패");
          }
      }
      
  });
	}
}

