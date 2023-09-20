
var logoutbtn = document.querySelector("#logoutbtn");
var boardBtn = document.querySelector("#board");

//로그인하기
$("#login_form").dialog({
autoOpen: false,
modal: true,
height: 350,
width: 500,
buttons: {
  로그인하기: login,
  아이디찾기 : function() {
	  $("#searchId_dialog").dialog("open")
},
  비밀번호찾기 : function() {
	  $("#searchPwd_dialog").dialog("open")
},
  닫기: function () {
      $(this).dialog("close");
  }
}
});


$("#loginBtn").on("click", function(){
$("#login_form").dialog("open")
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
         $("#login_form").dialog("close");
         location.href = "/project4/admin.do"
        
          
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
             location.href="/project4/admin.do";
         } else {
             alert("로그아웃 실패");
         }
     }
     
 });
});


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
	 $("#mypageEmail").prop("disabled", n);
}


// 회원 정보 수정하기
function updateMember() {
	 var send = {
		uid : $("#mypageId").val(),
		pwd : $("#mypagePwd").val(),
		name : $("#mypageName").val(),
		phone : $("#mypagePhone").val(),
		phone : $("#mypageEmail").val(),
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
