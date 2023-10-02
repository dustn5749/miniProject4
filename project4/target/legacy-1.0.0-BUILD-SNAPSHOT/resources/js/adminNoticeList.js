const allTermsCheck = document.querySelector("#allTermsCheck");
const checkTerms = document.querySelectorAll(".checkTerms");


var loginId = $("#writerId").val();

var noticedialog = $("#dialog-form").dialog({
    autoOpen: false,
    modal: true,
    height: 600,
    width: 700
});

// 버튼 구성 객체를 초기화합니다.
var buttonsConfig = {};

// loginId에 따라 버튼을 설정합니다.
if (loginId == "admin") {
    buttonsConfig["수정하기"] = function () {
        $("#seletedtitle, #seletedcontent, #fixed_y, #fixed_n").prop("disabled", false);
        
        $(this).dialog("option", "buttons", {
            "수정 완료": function () {
                update();
            },
            목록보기: function () {
                $(this).dialog("close");
            },
            삭제하기: deleteNotice // "삭제하기" 버튼 추가
        });
    };
}

buttonsConfig["목록보기"] = function () {
    $(this).dialog("close");
};

// 버튼 구성을 다이얼로그에 적용합니다.
noticedialog.dialog("option", "buttons", buttonsConfig);


		
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
                    var row = $("<tr class='boardTr'></tr>");

                    if (notice.fixed_yn === "Y") {
                        row.addClass("yellowBackground");
                    }

                    row.append(" <td><input type='checkbox' class='checkTerms'> </td>");
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
	    	noticedialog.dialog("open");
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
	    wirteDialog.dialog("open");
})


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
                    noticedialog.dialog("close");
	                $("#seletedtitle").val(data.notice.title);
	                $("#seletedid").val(data.notice.id);
	                $("#seletedboardnum").val(data.notice.boardNum);
	                $("#seletedregdate").val(data.notice.regdate);
	                $("#seletedcontent").val(data.notice.content);
	                $("#seletedreadcount").val(data.notice.readcount);
	                if(data.fixed_yn =="Y"){
	                    $("#fixed_y").prop("checked", true);
	                    $("#fixed_y").addClass("yellowBackground");
	                } else {
	                    $("#fixed_n").prop("checked", true);
	                }
	                
	                noticedialog.dialog("open");
                    
                    
                } else {
                    alert("공지사항 수정에 실패하였습니다");
                }

                // 입력란 비활성화 및 버튼 변경
                $("#seletedtitle, #seletedcontent, #fixed_y, #fixed_n").prop("disabled", true);
                noticedialog.dialog("option", "buttons", {
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
 					noticedialog.dialog("close");
            } 
        }			
			})
	
	
}



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

