const allTermsCheck = document.querySelector("#allTermsCheck");
const checkTerms = document.querySelectorAll(".checkTerms");


// dialog에 대한 정보 설정 ( 상세보기 )
	var dialog = $("#dialog-form").dialog({
			autoOpen : false,
			modal : true,
			height : 600,
			width:700,
			buttons : {
				목록보기 : function(){
					updateData();
					$(this).dialog("close");
				}
				,
		        수정하기: function () {
		            var loginId = $("#loginId").val();
		            var selectedId = $("#seletedid").val();

		            if (loginId === "admin") {
		                $("#seletedtitle, #seletedcontent, #fixed_y, #fixed_n").prop("disabled", false);

		                $(this).dialog("option", "buttons", {
		                    "수정 완료": function () {
		                        update();
		                    },
		                    목록보기 : function(){
		                    	updateData();
		    					$(this).dialog("close");
		    				} 
		                });
		            } else {
		                alert("수정할 수 있는 권한이 없습니다.");
		            }
		        },삭제하기 : function(){
		        	if (loginId === "admin") {
		        		deleteNotice		        		
		        	} else {
		        		alert("삭제할 권한이 없습니다.");
		        	}
		        }
		}
		})


//// Dialog 열기 전에 실행될 함수
//dialog.on("dialogopen", function () {
//    var loginId = $("#writerId").val();
//    var selectedId = $("#seletedid").val();
//    if (loginId === "admin") {
//        // uid와 id가 일치하는 경우
//    	dialog.dialog("option", "buttons", {
//            "수정하기": function () {
//                $("#seletedtitle, #seletedcontent, #fixed_y, #fixed_n").prop("disabled", false);
//
//                $(this).dialog("option", "buttons", {
//                    "수정 완료": function () {
//                        update();
//                    },
//                    목록보기: function () {
//                        $(this).dialog("close");
//                    }
//                });
//            },
//            목록보기: function () {
//                $(this).dialog("close");
//            },
//            삭제하기: deleteBoard
//        });
//    } else {
//        // uid와 id가 일치하지 않는 경우
//        dialog.dialog("option", "buttons", {
//            목록보기: function () {
//                $(this).dialog("close");
//            }
//        });
//    }
//});	
		
//페이지 데이터 가져오기
function updateData() {
    var pageNo = $("#pageNo2").val();
    var send = {
        pageNo: pageNo,
        searchTitle: $("#searchTitle").val(),
        pageLength: $("#pageLength").val()
    }
    $.ajax({
        url: "/project4/notice/getlist.do",
        type: 'POST',
        contentType: "application/json; charset=UTF-8",
        data: JSON.stringify(send),
        dataType: "json",
        success: function (data) {
            if (data.result) {
                var noticeList = data.noticeList.noticeList;
                var listArea = $("#listArea");
                listArea.empty();
                
                $.each(noticeList, function (index, notice) {
                    var row = $("<tr></tr>");
                    
                    row.append("<td class='boardNum'><input class='boardInfoNum' value='" + notice.boardNum + "' name='boardNum' readonly='readonly'></td>");
                    row.append("<td>" + notice.title + "</td>");
                    row.append("<td>" + notice.id + "</td>");
                    row.append("<td>" + notice.regdate + "</td>");
                    row.append("<td>" + notice.readcount + "</td>");
                    row.append("<td><button class='detailBtn'>상세보기</button></td>");

                    listArea.append(row);
                });
            } else {
                alert("페이지 로드 실패");
            }
        }
    });
}
		
// 상세보기
	$(document).on("click", ".detailBtn", function() {
	    var boardnum = $(this).closest("tr").find(".boardInfoNum").val();
	    console.log("boardnum = " + boardnum);
	    var send = {
	        boardNum: boardnum
	    };
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
	                if(data.fixed_yn =="Y"){
	                    $("#fixed_y").prop("checked", true);
	                } else {
	                    $("#fixed_n").prop("checked", true);
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
	});		
		
		
		
		
		
// 새글 작성 dialog설정
var wirteDialog = $("#dialog-form2").dialog({
      autoOpen : false,
      modal : true,
      height : 600,
      width:700,
      buttons : {
            작성하기 : addNotice,
            새로고침 : reload,
            목록보기 : function(){
            	updateData();
               $(this).dialog("close");
            }
      }
})

$("#newBoard").click(function(){
		loginCheck();
})

// 로그인 체크 
function loginCheck(){
   console.log("로그인체크 ");
   
      fetch("/project4/member/loginCheck.do", {
        method: 'POST',
          headers : {
                'Content-Type': 'application/json; charset=UTF-8'
         }
   })
   .then(response => response.json())
   .then(function(data) {
       if (data.result) {
       	var user = $("#writerId").val();
  		  	var now = new Date();
  			var year = now.getFullYear();
  			var month = ("0" + (now.getMonth() + 1)).slice(-2); // 월은 0부터 시작하므로 1을 더하고 두 자리로 만듦
  			var day = ("0" + now.getDate()).slice(-2); // 날짜를 두 자리로 만듦
  			var formattedDate = year + "-" + month + "-" + day;
  	   		$("#newid").val(user);
  	   		$("#newregdate").val(formattedDate);
  		    wirteDialog.dialog("open");
          } else {
       	   alert("로그인 후 이용해주세요.");
          }
   })
}


// 게시글 추가하기
   function addNotice(){
	   
	  var fixed_yn;
		
	  if($("#newfixed_y").prop("checked")){
		  fixed_yn = $("#newfixed_y").val();
	  } else {
		  fixed_yn = $("#newfixed_n").val();
	  }
	  console.log("fixed_ yn = " + fixed_yn);
        var sendData={
			id:  $("#writerId").val(),
			title:$("#newtitle").val(),
			content:$("#newcontent").val(),
			fixed_yn: fixed_yn
		};
      
      if($("#newtitle").val() !==""){
		    $.ajax({
			url : "/project4/notice/insertNotice.do",
		   type: 'POST',
            data: JSON.stringify(sendData),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function(data) {
                if (data.result) {
                    alert("게시판 등록이 완료되었습니다.");
                    updateData();
                    wirteDialog.dialog("close");              
                } 
             }
	  })
	  } else {
		  alert("제목을 입력해주세요.");
	  }
   }

function reload(){
	var empty = "";
	$("#newtitle").val(empty);
	$("#newcontent").val(empty);
}

function jsPageNo(pageNo) {
	console.log("페이지 이동" + pageNo);
	document.querySelector("#pageNo1").value = pageNo;
	document.querySelector("#pageForm").submit(); 
}

	
	
// 게시글 수정하기
  function update() {
	  var fixed_yn;
	  if($("#fixed_y").prop("checked")){
		  fixed_yn = $("#fixed_y").val();
	  } else {
		  fixed_yn = $("#fixed_n").val();
	  }
	  
        var send = {
            boardNum: $("#seletedboardnum").val(),
            title: $("#seletedtitle").val(),
            content: $("#seletedcontent").val(),
            fixed_yn: fixed_yn
        };

        $.ajax({
            url: "/project4/notice/update.do",
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(send),
            dataType: "json",
            success: function(data) {
                if (data.result) {
                    alert("공지사항 수정에 성공하였습니다");
                    updateData();
                    dialog.dialog("close");
	                $("#seletedtitle").val(data.notice.title);
	                $("#seletedid").val(data.notice.id);
	                $("#seletedboardnum").val(data.notice.boardNum);
	                $("#seletedregdate").val(data.notice.regdate);
	                $("#seletedcontent").val(data.notice.content);
	                $("#seletedreadcount").val(data.notice.readcount);
	                if(data.fixed_yn =="Y"){
	                    $("#fixed_y").prop("checked", true);
	                } else {
	                    $("#fixed_n").prop("checked", true);
	                }
	                dialog.dialog("open");
                    
                    
                } else {
                    alert("공지사항 수정에 실패하였습니다");
                }

                // 입력란 비활성화 및 버튼 변경
                $("#seletedtitle, #seletedcontent, #fixed_y, #fixed_n").prop("disabled", true);
                dialog.dialog("option", "buttons", {
                    "목록보기": function() {
                    	updateData();
                        $(this).dialog("close");
                    },
                    "수정하기": function() {
                        // 수정 완료 버튼으로 변경
                        $(this).dialog("option", "buttons", {
                            "수정 완료": function() {
                                update();
                            }
                        });

                        // 입력란 활성화
                $("#seletedtitle, #seletedcontent, #fixed_y, #fixed_n").prop("disabled", false);
                    },
                    "삭제하기": deleteNotice
                });
            }
        });
    }

// 게시글 삭제하기
	function deleteNotice (){
		if(confirm("정말 삭제하시겠습니까?")){
			var deleteBoard =  [$("#seletedboardnum").val()];  
			var sendData = {
				deleteNumList : deleteBoard
			}
			deletes(sendData);
		}
	}


// 삭제시 사용할 ajax 함수 
function deletes(sendData){
				$.ajax({
			url : "/project4/notice/delete.do",
			 type :'POST',
			 data : JSON.stringify(sendData),
			 dataType: "json",
        	contentType: "application/json; charset=utf-8",
        	success: function(data) {
            if (data.result) {	
 					alert("게시판삭제가 완료되었습니다."); 
 					updateData();
 					dialog.dialog("close");
            } 
        }			
			})
	
	
}


/*
//  더보기 버튼 클릭시 10개 정보 가져오기
let plusBtn = document.querySelector("#plusBtn");
plusBtn.onclick=()=>{
 let lastTableRow = document.querySelector("#noticeList tr:last-child");
  let boardNumInput = lastTableRow.querySelector(".boardInfoNum");
  let boardNum = boardNumInput.value;
  
  
	console.log("boardNum = " +  boardNum);
		

	const param = {
	        boardNum: boardNum
	      };

	      fetch("/project3/notice/noticePlus.do", {
	        method: "POST",
	        headers: {
	          "Content-Type": "application/json; charset=UTF-8",
	        },
	        body: JSON.stringify(param),
	      })
	      .then((response) => response.json())
		.then(function(data) {
    if (data.status) {
      const noticeList = data.noticeList;
      const noticeItemTemplate = document.querySelector("#noticeItem");
		const noticeListTbody = document.querySelector("#noticeList");
      for (let i = 0; i < noticeList.length; i++) {
        const notice = noticeList[i];
        const newNoticeItem = noticeItemTemplate.cloneNode(true);
        
        newNoticeItem.querySelector(".boardInfoNum").value = notice.boardNum;
        newNoticeItem.querySelector("#noticeTitle").innerText = notice.title;
        newNoticeItem.querySelector("#noticeId").innerText = notice.id;
        newNoticeItem.querySelector("#noticeRegDate").innerText = notice.regdate;
        newNoticeItem.querySelector("#noticeReadCount").innerText = notice.readcount;
		
        newNoticeItem.removeAttribute("style");
        newNoticeItem.removeAttribute("id");
     	noticeListTbody.appendChild(newNoticeItem);
      }
    }
  });
	return false;
}
*/

//전체선택하기 누를시 모든 체크박스 선택하기
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

// 체크박스에 전부 다 체크할시 전체선택의 체크박스 선택하기
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


// 선택된 게시물 삭제하기
const checkDeletebtn = document.querySelector("#checkDelete");
checkDeletebtn.addEventListener("click", checkBoardDelete);
function checkBoardDelete(){
	let deleteNumList =[];
	let checkTerms = document.querySelectorAll(".checkTerms");
	for(let i=0; i<checkTerms.length; ++i){
		if(checkTerms[i].checked){
			 let boardNum = checkTerms[i].parentNode.parentNode.querySelector(".boardNum").querySelector(".boardInfoNum").value;
			deleteNumList.push(boardNum);
		}
	}
	if(deleteNumList.length != 0){
			const sendData = {
				deleteNumList : deleteNumList
			}
			deletes(sendData);
	} else {
		alert("삭제할 리스트가 없습니다.");
	}
}

