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
				수정하기 : function() {
				    $("#fixed_y, #fixed_n, #seletedtitle, #seletedcontent").prop("disabled", false);
			         $(this).dialog("option", "buttons", {
			             "수정 완료": function() {
			            	 updateNotice();
			             },
			         	"닫기" :function () {
			    	        $(this).dialog("close");
			    	    }
			         });
				},
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


//// 공지사항 수정 활성화
//function enableInputs() {
//    $("#fixed_y, #fixed_n, #seletedtitle, #seletedcontent").prop("disabled", false);
//}

// 공시사항 수정 완료
function updateNotice() {
	var boardNum = $("#seletedboardnum").val()
	var title = $("#seletedtitle").val();
	var fixed_yn;
	  if($("#fixed_y").prop("checked")){
		  fixed_yn = $("#fixed_y").val();
	  } else {
		  fixed_yn = $("#fixed_n").val();
	  }
	var content = $("#seletedcontent").val()
	
	if(title == "" || fixed_yn == ""){
		alert("제목, 고정유무를 모두 입력해주세요.");	
	} else {
		var send = {
				boardNum : boardNum,
				title : title,
				fixed_yn : fixed_yn,
				content : content
			}
			
			$.ajax({
				url : "/project4/notice/update.do",
			    type: 'POST',
			    contentType:   "application/json; charset=UTF-8",
			    data: JSON.stringify(send),
			    dataType: "json",
			    success: function (data) {
			        if (data.result) {
			        	alert("공지사항 수정이 완료되었습니다.")
			            $("#seletedtitle").val(data.notice.title);
			        	document.querySelector(".top5boardTitle").value=data.notice.title;
			        	$("#seletedid").val(data.notice.id);
			            $("#seletedboardnum").val(data.notice.boardNum);
			            $("#seletedregdate").val(data.notice.regdate);
			            $("#seletedcontent").val(data.notice.content);
			            $("#seletedreadcount").val(data.notice.readcount);
			            
			            if(data.notice.fixed_yn =="Y"){
			                $("#fixed_y").prop("checked", true);
			            } else {
			                $("#fixed_n").prop("checked", true);
			            }
			            $("#fixed_y, #fixed_n, #seletedtitle, #seletedcontent").prop("disabled", true);
			            
			            	dialog.dialog("option", "buttons",{
			        		    수정하기: function() {
						            $("#fixed_y, #fixed_n, #seletedtitle, #seletedcontent").prop("disabled", false);
			        		         $(this).dialog("option", "buttons", {
			        		             "수정 완료": function() {
			        		            	 updateNotice();
			        		             },
			        		         	"닫기" :function () {
			        		    	        $(this).dialog("close");
			        		    	    }
			        		         });
			        				},
			        			    닫기: function () {
			        			        $(this).dialog("close");
			        			    }
			        	 })
			            
			            
			            
			            $("#openDialogBtn").click();
			         
			        } else {
			            alert("공지사항 수정에 실패해였습니다.");
			        }
			    }			
			})
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
  url: "/project4/admin/login.do", 
  type: "POST", 
  contentType: "application/json; charset=UTF-8",
  data: JSON.stringify(send),
  dataType: "json",
  success: function (data) {
      if (data.result) {
         alert("관리자 계정으로 로그인되었습니다.");
         $("#dialog_form3").dialog("close");
         
          location.href= "/project4/admin.do";
          
      } else {
          alert("아이디 비밀번호를 다시 확인해주세요.");
      }
  },
  error: function () {
      alert("로그인 중 오류가 발생했습니다.");
  }
});

}


//로그아웃하기
$("#logoutbtn").on("click", function() {
 $.ajax({
     url: "/project4/member/adminlogout.do",
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



//마이페이지 띄우기
$("#mypage_form").dialog({
	autoOpen: false,
	modal: true,
	height: 500,
	width: 500,
	buttons: {
	    수정하기: function() {
	    	enableInputs(false);
         $(this).dialog("option", "buttons", {
             "수정 완료": function() {
            	 updateMember();
             },
         	"닫기" :function () {
    	        $(this).dialog("close");
    	    }
         });
		},
//	    수정완료: updateMember,
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
function enableInputs(n) {
	 $("#mypageName").prop("disabled", n);
	 $("#mypagePhone").prop("disabled", n);
	 $("#mypagePwd").prop("disabled", n);
}


// 회원 정보 수정하기
function updateMember() {
	 var send = {
		uid : $("#mypageId").val(),
		pwd : $("#mypagePwd").val(),
		name : $("#mypageName").val(),
		phone : $("#mypagePhone").val()
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
	                enableInputs(true);
	        		$("#mypage_form").dialog("option", "buttons",{
	        		    수정하기: function() {
	        		    	 enableInputs(false);
	        		         $(this).dialog("option", "buttons", {
	        		             "수정 완료": function() {
	        		            	 updateMember();
	        		             },
	        		         	"닫기" :function () {
	        		    	        $(this).dialog("close");
	        		    	    }
	        		         });
	        				},
	        			    탈퇴하기: deleteMember,
	        			    닫기: function () {
	        			        $(this).dialog("close");
	        			    }
	        	 })
	        	 
	        	 
	            } else {
	                alert("회원 수정 실패");
	            }
	        }
	        
	    });
	 
	 
}

//회원탈퇴하기
function deleteMember() {
	if(confirm("이 계정은 관리자 계정입니다. 탈퇴하시면 관리자 페이지에 접속할 수 없습니다. 탈퇴하시겠습니까?")){
	var pwdCheck = prompt("탈퇴하시려면 비밀번호를 입력해주세요.");
	
	if(pwdCheck == $("#mypagePwd").val()){
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
	
	}else {
		alert("비밀번호가 일치하지 않습니다.");
	}


	}
}
