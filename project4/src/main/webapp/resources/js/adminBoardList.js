const newBoardBtn = document.querySelector("#newBoard");
const allTermsCheck = document.querySelector("#allTermsCheck");
const checkTerms = document.querySelectorAll(".checkTerms");
const  btn = document.getElementsByTagName("button");
const checkDeletebtn = document.querySelector("#checkDelete");
const checkModifybtn = document.querySelector("#checkModify");
const boardInfoPage = document.querySelectorAll(".boardInfoPage");


//dialog에 대한 정보 설정 (상세보기)
var dialog = $("#dialog-form").dialog({
	autoOpen: false,
	modal: true,
	height: 600,
	width: 700
});

// Dialog 열기 전에 실행될 함수
dialog.on("dialogopen", function () {
    var loginId = $("#writerId").val();
    var selectedId = $("#seletedid").val();
    if (loginId =='admin') {
        dialog.dialog("option", "buttons", {
            "수정하기": function () {
                $("#seletedtitle, #seletedcontent").prop("readonly", false);
          	  	var row = $("#seletedattachFile").children().length;
          	  	if(row != 0){
            	   $("#fileDelete").append("<a id='fileDeleteBtn' onclick='deleteFile()' href='#'>삭제</a>");
               }
                
                
                $(this).dialog("option", "buttons", {
                    "수정 완료": function () {
                        update();
                    },
                    목록보기: function () {
                    	updateData()
                        $(this).dialog("close");
                    },
                   
                });
            },
            목록보기: function () {
            	updateData()
                $(this).dialog("close");
            },
            삭제하기: deleteBoard
            ,답글쓰기 : function () {
            	replyWriteDialog.dialog("open");
			}
        });
    } else {

        if(loginId!=""){
        	dialog.dialog("option", "buttons",{
        		답글쓰기 : function () {
                	replyWriteDialog.dialog("open");
    			},
        		 목록보기: function () {
        			 updateData()
                     $(this).dialog("close");
                 }
        	})
        	
        } else {
            dialog.dialog("option", "buttons", {
                목록보기: function () {
                	updateData()
                    $(this).dialog("close");
                }
            });
        }
    }
});

// 답글 작성 dialog설정
var replyWriteDialog = $("#replyWrite-form").dialog({
	autoOpen: false,
	modal: true,
	height: 600,
	width: 700,
	buttons:{
		작성하기 : addBoard2
	}
})


// 페이지 데이터 가져오기
function updateData() {
  var pageNo = $("#pageNo2").val();
  var send = {
      pageNo: pageNo,
      searchTitle: $("#searchTitle").val(),
      pageLength: $("#pageLength").val()
  }
  $.ajax({
      url: "/project4/board/getlist.do",
      type: 'POST',
      contentType: "application/json; charset=UTF-8",
      data: JSON.stringify(send),
      dataType: "json",
      success: function (data) {
          if (data.result) {
              var boardList = data.boardList.boardList;
              var listArea = $("#listArea");
              listArea.empty();
              
              $.each(boardList, function (index, board) {
                  var row = $("<tr></tr>");
                  
                  row.append(" <td><input type='checkbox' class='checkTerms'> </td>");
                  row.append("<td class='boardNum'><input class='boardInfoNum' value='" + board.boardNum + "' name='boardNum' readonly='readonly'></td>");
                  row.append("<td style='text-align: left'><span style='padding-left:" + (board.level - 1) * 30 + "px'></span>");
                  if (board.level != 1) {
                      row.find("td:last").append("↪[답글]");
                  }
                  row.find("td:last").append(board.title + "</td>");
                  row.append("<td>" + board.id + "</td>");
                  row.append("<td>" + board.regdate + "</td>");
                  row.append("<td>" + board.readcount + "</td>");
                  
                  var detailBtn = $("<button class='detailBtn'>상세보기</button>");
                  detailBtn.click(function () {
                  });
                  row.append($("<td></td>").append(detailBtn));

                  listArea.append(row);
              });
          } else {
              alert("페이지 로드 실패");
          }
      }
  });
  return false;

}

// 새글 작성 dialog설정
var wirteDialog = $("#dialog-form2").dialog({
      autoOpen : false,
      modal : true,
      height : 600,
      width:700,
      buttons : {
            작성하기 : addBoard,
            새로고침 : reload,
            목록보기 : function(){
            	updateData()
               $(this).dialog("close");
            }
      }
})

$("#newBoard").click(function(){
	 wirteDialog.dialog("open");
})



// 첨부파일 버튼 추가하기
function attachFileBehavior() {
	var cnt = 1;
    $(".d_file").append("<br>" + "<input type='file'  name='file" + cnt++ +"' class='fileUpload'/>");
    }




// 게시글 작성하기(새글 추가)
function addBoard() {
	 var attachForm = $("#attachForm")[0];
	 var formData = new FormData(attachForm);	    
	 var option = {
			    method: 'POST',
			    body: formData
			}
	
	   fetch("/project4/board/boardInsert.do", option)
	    .then(response => response.json())
	    .then(data => {
	    	console.log("step4");
	    	if (data.result) {
	    		alert("게시글이 성공적으로 작성되었습니다");
	    		updateData();
	    		wirteDialog.dialog("close");
	    		
	    	} else {
	    		alert("게시글 작성에 실패하였습니다");
	    	}
	    });
	 return false;
}


// 답글 추가하기
function addBoard2() {
	 var attachForm = $("#attachForm2")[0];
	
	 var formData = new FormData(attachForm);	    
	 var option = {
			    method: 'POST',
			    body: formData
			}
	
	   fetch("/project4/board/boardInsert2.do", option)
	    .then(response => response.json())
	    .then(data => {
	    	console.log("step4");
	    	if (data.result) {
	    		alert("게시글이 성공적으로 작성되었습니다");
	    		updateData();
	    		replyWriteDialog.dialog("close");
	    		
	    	} else {
	    		alert("게시글 작성에 실패하였습니다");
	    	}
	    });

}

$("#dialog-form2, #replyWrite-form").on("click", ".attacheFileBtn", function (e) {
	 e.preventDefault(); 
	attachFileBehavior();
});



$("#dialog-form2, #replyWrite-form").on("click", ".uploadBtn", function (e) {
	uploadFile(e)
});



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


// 페이지 로드시 공지사항 게시물의 배경색 녹색으로 표시하기
document.addEventListener("DOMContentLoaded", function() {
    var boardSortElements = document.getElementsByClassName("boardSort");
    for (var i = 0; i < boardSortElements.length; i++) {
        if (boardSortElements[i].textContent.trim() === "notice") {
            boardSortElements[i].parentNode.style.backgroundColor = "rgb(241, 247, 240)";
        }
    }
});

//첨부파일 삭제하기
function deleteFile() {
	$("#seletedattachFile").empty();
	$(".result_images").empty();
	$("#fileDelete").empty();
	$("#seletedattachFile").empty();
	var cnt = 1;
	$("#fileBtn").append("<br>" + "<input type='file'  name='file" + cnt++ +"' class='fileUpload'/>");
	
}

//게시글 수정하기
function update() {
	  var formData = new FormData($("#updateForm")[0]);	    
		 
	  
	  var option = {
				    method: 'POST',
				    body: formData
				}
		
		   fetch("/project4/board/boardUpdate.do", option)
		    .then(response => response.json())
		    .then(data => {
		    	console.log("step4");
		    	if (data.result) {
		    		alert("게시판 수정에 성공하였습니다.");
		    		updateData();
		    		dialog.dialog("close");
		    		$("#seletedattachFile").empty();
		    		$("#result_images").empty();
		    		$("#fileBtn").empty();
		    		
		               $("#seletedtitle").val(data.board.title);
		                $("#seletedid").val(data.board.id);
		                $("#seletedboardnum").val(data.board.boardNum);
		                $("#replyPboardnum").val(data.board.boardNum);
		                $("#seletedregdate").val(data.board.regdate);
		                $("#seletedcontent").val(data.board.content);
		                $("#seletedreadcount").val(data.board.readcount);

		                
		                var attaheFiles= data.attacheFile;
		                var fileListHTML1 = "";
		                var fileListHTML2 = "";
		                if(attaheFiles.length> 0){
			                for(let i=0; i<attaheFiles.length; ++i ){
			                	let file = attaheFiles[i];
			                	displayImages(file);
			                	fileListHTML1 += "<div><span class='download'>" + file.fileNameOrg + "<button class='downloadBtn'><img src='/project4/resources/images/download.jpg' class='downloadIcon' data-fileNo="+ file.fileNo+"></button></span></div>";
			                }
			                
			                $("#seletedattachFile").append(fileListHTML1);
		                }

		                

		                var user = $("#writerId").val();
		       		  	var now = new Date();
		       			var year = now.getFullYear();
		       			var month = ("0" + (now.getMonth() + 1)).slice(-2); 
		       			var day = ("0" + now.getDate()).slice(-2); // 날짜를 두 자리로 만듦
		       			var formattedDate = year + "-" + month + "-" + day;
		       	   		$("#replyid").val(user);
		    	   		$("#replyregdate").val(formattedDate);
		                
		                $("#commentList").empty();

		                for (var i = 0; i < data.commentList.length; i++) {
		                    var comment = data.commentList[i];
		                    var commentHtml =
		                    	'<div><input class="commentNum" type="hidden" value="' + comment.boardnum + '">' +
		                        '<span class="comment-id">' + comment.id + '</span> ' +
		                        '<input type="text" class="comment-content" value="' + comment.content + '" disabled="disabled"> ' +
		                        '<span class="comment-regdate">' + comment.regdate + '</span>';

		                
		                    
		                    if (id ==comment.id) {
		                    	commentHtml +=  '<button class="modifyCommentBtn">수정</button>'+
		                            '<button class="deleteCommentBtn" >삭제</button>' ;
		                    }
		                    


		                    commentHtml += '</div>';

		                    $("#commentList").append(commentHtml);
		                }
		               
		                $("#plusCommentBtn").remove();
		                
		                var rowCount = $("#commentList").children().length;
		                if(data.commentLength >rowCount){
		                	var commentHtml = '<a href="#" id="plusCommentBtn">더보기</a>'
		                		$("#plus").append(commentHtml);
		                }else{
		                    $("#plusCommentBtn").remove();

		                }
		                
		                  $("#openDialogBtn").click();
		                  
		                  dialog.dialog("open")
		    		
		    		
		    		  // 입력란 비활성화 및 버튼 변경
	                $("#seletedtitle, #seletedcontent").prop("readonly", true);

		    		
		    	} else {
		    		alert("게시글 수정 실패하였습니다");
		    	}
		    });


	  return false; 
         
    
 }

// 게시글 삭제하기
	function deleteBoard (){
		if(confirm("정말 삭제하시겠습니까?")){
 			var page = $("#pageNo1").val(); 
			
			var deleteBoard =  [$("#seletedboardnum").val()];  
			var sendData = {
				deleteNumList : deleteBoard
			}
			deletes(sendData);
			
			$("#dialog-form").dialog("close");
		}
	}
// 삭제시 사용할 ajax 함수 
function deletes(sendData){
			$.ajax({
			url : "/project4/board/deleteBoards.do",
			 type :'POST',
			 data : JSON.stringify(sendData),
			 dataType: "json",
        	contentType: "application/json; charset=utf-8",
        	success: function(data) {
            if (data.result) {	
 				alert("게시판삭제가 완료되었습니다.");
 				updateData();
            } 
        }
				
			})
}
// 게시글 상세보기 누를시 상세보기 dialog 열기
$(document).on("click", ".detailBtn", function() {
	var boardnum = $(this).closest("tr").find(".boardInfoNum").val();
	var send = {
		boardNum : boardnum
	};
	 	$.ajax({
			 url : "/project4/board/boardInfo.do",
			 type :'POST',
			 data : JSON.stringify(send),
			 dataType: "json",
        	contentType: "application/json; charset=utf-8",
        	success: function(data) {
            if (data.result) {	
            	$("#seletedattachFile").empty();
            	$("#result_images").empty();
            	$("#fileBtn").empty();
                $("#seletedtitle").val(data.board.title);
                $("#seletedid").val(data.board.id);
                $("#seletedboardnum").val(data.board.boardNum);
                $("#replyPboardnum").val(data.board.boardNum);
                $("#seletedregdate").val(data.board.regdate);
                $("#seletedcontent").val(data.board.content);
                $("#seletedreadcount").val(data.board.readcount);

                
                var attaheFiles= data.attacheFile;
                var fileListHTML1 = "";
                var fileListHTML2 = "";
                
                for(let i=0; i<attaheFiles.length; ++i ){
                	let file = attaheFiles[i];
                	displayImages(file);
                	fileListHTML1 += "<div><span class='download'>" + file.fileNameOrg + "<button class='downloadBtn'><img src='/project4/resources/images/download.jpg' class='downloadIcon' data-fileNo="+ file.fileNo+"></button></span></div>";
                }
                
                $("#seletedattachFile").append(fileListHTML1);
                

                var user = $("#writerId").val();
       		  	var now = new Date();
       			var year = now.getFullYear();
       			var month = ("0" + (now.getMonth() + 1)).slice(-2); 
       			var day = ("0" + now.getDate()).slice(-2); // 날짜를 두 자리로 만듦
       			var formattedDate = year + "-" + month + "-" + day;
       	   		$("#replyid").val(user);
    	   		$("#replyregdate").val(formattedDate);
                
                $("#commentList").empty();

                for (var i = 0; i < data.commentList.length; i++) {
                    var comment = data.commentList[i];
                    var commentHtml =
                    	'<div><input class="commentNum" type="hidden" value="' + comment.boardnum + '">' +
                        '<span class="comment-id">' + comment.id + '</span> ' +
                        '<input type="text" class="comment-content" value="' + comment.content + '" disabled="disabled"> ' +
                        '<span class="comment-regdate">' + comment.regdate + '</span>';

                
                    
                    if (id ==comment.id) {
                    	commentHtml +=  '<button class="modifyCommentBtn">수정</button>'+
                            '<button class="deleteCommentBtn" >삭제</button>' ;
                    }
                    


                    commentHtml += '</div>';

                    $("#commentList").append(commentHtml);
                }
               
                $("#plusCommentBtn").remove();
                
                var rowCount = $("#commentList").children().length;
                if(data.commentLength >rowCount){
                	var commentHtml = '<a href="#" id="plusCommentBtn">더보기</a>'
                		$("#plus").append(commentHtml);
                }else{
                    $("#plusCommentBtn").remove();

                }
                
                  $("#openDialogBtn").click();
            } else {
                alert("상세보기 실패");
            }
        }
		 })
		$("#openDialogBtn").click(function() {
        dialog.dialog("open");
   //     upReadCount();
    });
		
	
})

// 파일 이미지 출력하기
function displayImages(file) {
	var imageContainer = $(".result_images"); // 이미지를 표시할 컨테이너 엘리먼트
	imageContainer.empty();
	//alert(file.fileNo)
    var imageElement = $("<img>").attr("src", "/project4/file/displayImage.do?fileNo=" + file.fileNo);
    imageContainer.append(imageElement); // 이미지 엘리먼트를 컨테이너에 추가
   
    
}



// 파일 다운로드하기
$("#seletedattachFile").on("click",".downloadBtn", function (e) {
	var fileNo = $(e.target).attr("data-fileNo");
	location.href = "/project4/file/download.do?fileNo=" + fileNo;
})


// 더보기 버튼 클릭 이벤트
$("#plus").on("click", "#plusCommentBtn", function() {
    var lastCommentNum = $("#commentList .commentNum:last").val(); // 마지막 댓글의 번호 가져오기
    var boardnum =  $("#seletedboardnum").val();
    var send = {
        parentnum: boardnum, // 게시글 번호
        boardnum: lastCommentNum // 마지막 댓글 번호
    };


    $.ajax({
        url: "/project4/comment/plusComment.do",
        type: "POST",
        data: JSON.stringify(send),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function(data) {
                for (var i = 0; i < data.commentList.length; i++) {
                    var comment = data.commentList[i];
                    var commentHtml =
                        '<div><input class="commentNum" type="hidden" value="' + comment.boardnum + '">' +
                        '<span class="comment-id">' + comment.id + '</span> ' +
                        '<input type="text" class="comment-content" value="' + comment.content + '" disabled="disabled"> ' +
                        '<span class="comment-regdate">' + comment.regdate + '</span>';

                    if (id == comment.id) {
                        commentHtml += '<button class="modifyCommentBtn">수정</button>' +
                            '<button class="deleteCommentBtn">삭제</button>';
                    }

                    commentHtml += '</div>';

                    $("#commentList").append(commentHtml);
                }
               
                $("#plusCommentBtn").remove();

                var rowCount = $("#commentList").children().length;

                if(data.commentLength >rowCount){
                	var commentHtml = '<a href="#" id="plusCommentBtn">더보기</a>'
                		$("#plus").append(commentHtml);
                }else{
                    $("#plusCommentBtn").remove();
                
           
        }
        }
    });
});

// 댓글 등록하기
var id = document.querySelector("#writerId").value;

$("#replyBtn").on("click", function () {
    var reply = $("#reply").val();
    if (reply == "") {
        alert("댓글 내용을 입력해주세요");
    } else {

        var parentnum = $("#seletedboardnum").val();

        var send = {
            id: document.querySelector("#idSpan").innerHTML,
            content: reply,
            parentnum: $("#seletedboardnum").val()
        }

        $.ajax({
            url: "/project4/comment/insert.do",
            type: 'POST',
            data: JSON.stringify(send),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                if (data.result) {
                    alert("댓글 등록이 완료되었습니다.");
                    dialog.dialog("close");

                    $("#commentList").empty();

                  
                        var comment = data.commentList[i];
                        var commentHtml =
                        	'<div><input class="commentNum" type="hidden" value="' + comment.boardnum + '">' +
                            '<span class="comment-id">' + comment.id + '</span> ' +
                            '<input type="text" class="comment-content" value="' + comment.content + '" disabled="disabled"> ' +
                            '<span class="comment-regdate">' + comment.regdate + '</span>';

                        if (id ==comment.id) {
                        	commentHtml +=    '<button class="modifyCommentBtn">수정</button>'+
                              '<button class="deleteCommentBtn">삭제</button>';
                        }

                        commentHtml += '</div>';

                        $("#commentList").append(commentHtml);
                    }
                    
                $("#plusCommentBtn").remove();

                    
                    var rowCount = $("#commentList").children().length;
                    if(data.commentLength >rowCount){
                    	var commentHtml = '<a href="#" id="plusCommentBtn">더보기</a>'
                    		$("#plus").append(commentHtml);
                    }else{
                        $("#plusCommentBtn").remove();
                    }

                    dialog.dialog("open");
                 
            }
        });
    }
});

//댓글 삭제하기
$("#commentList").on("click", ".deleteCommentBtn", function (e) {
    var boardnum = $(e.target).closest("div").find(".commentNum").val();
    var parentnum = $("#seletedboardnum").val();
    var send = {
        boardnum: boardnum,
        parentnum : parentnum
    }

    $.ajax({
        url: "/project4/comment/deleteComment.do",
        type: 'POST',
        data: JSON.stringify(send),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            if (data.result) {
                alert("댓글이 삭제되었습니다");
                dialog.dialog("close");

                $("#commentList").empty();

                for (var i = 0; i < data.commentList.length; i++) {
                    var comment = data.commentList[i];
                    var commentHtml =
                        '<div><input class="commentNum" type="hidden" value="' + comment.boardnum + '">' +
                        '<span class="comment-id">' + comment.id + '</span> ' +
                        '<input type="text" class="comment-content" value="' + comment.content + '" disabled="disabled"> ' +
                        '<span class="comment-regdate">' + comment.regdate + '</span>';

                    if (id == comment.id) {
                    	commentHtml +=  '<button class="modifyCommentBtn">수정</button>' + 
                            '<button class="deleteCommentBtn">삭제</button>';
                    }

                    commentHtml += '</div>';

                    $("#commentList").append(commentHtml);
                }

                $("#plusCommentBtn").remove();

                var rowCount = $("#commentList").children().length;

                if(data.commentLength >rowCount){
                	var commentHtml = '<a href="#" id="plusCommentBtn">더보기</a>'
                		$("#plus").append(commentHtml);
                }else{
                    $("#plusCommentBtn").remove();
                }
                
                
                dialog.dialog("open");
            } else {
                alert("댓글 삭제에 실패하였습니다");
            }
        }
    });
});

//댓글 수정버튼 활성화
$("#commentList").on("click", ".modifyCommentBtn", function (e) {
    let target = $(e.target);
    if (target.html() == "완료") {
        var boardnum = target.closest("div").find(".commentNum").val();
        var parentnum = $("#seletedboardnum").val();
        var content = target.closest("div").find(".comment-content").val();
        var send = {
            boardnum: boardnum,
            content: content,
            parentnum: parentnum
        }

        $.ajax({
            url: "/project4/comment/updateComment.do",
            type: 'POST',
            data: JSON.stringify(send),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                if (data.result) {
                    alert("댓글이 수정되었습니다.");
                    dialog.dialog("close");

                    $("#commentList").empty();

                    for (var i = 0; i < data.commentList.length; i++) {
                        var comment = data.commentList[i];
                        var commentHtml =
                            '<div><input class="commentNum" type="hidden" value="' + comment.boardnum + '">' +
                            '<span class="comment-id">' + comment.id + '</span> ' +
                            '<input type="text" class="comment-content" value="' + comment.content + '" disabled="disabled"> ' +
                            '<span class="comment-regdate">' + comment.regdate + '</span>';

                        if (id == comment.id) {
                        	  commentHtml += '<button class="modifyCommentBtn">수정</button>'+
                               '<button class="deleteCommentBtn">삭제</button>';
                        }

                        commentHtml += '</div>';

                        $("#commentList").append(commentHtml);
                    }
                    
                    $("#plusCommentBtn").remove();

                    var rowCount = $("#commentList").children().length;

                    if(data.commentLength >rowCount){
                    	var commentHtml = '<a href="#" id="plusCommentBtn">더보기</a>'
                    		$("#plus").append(commentHtml);
                    }else{
                        $("#plusCommentBtn").remove();
                    }
                   
                    
                    

                    dialog.dialog("open");
                } else {
                    alert("댓글 수정에 실패하였습니다");
                }
            }
        });

        target.closest("div").find(".comment-content").prop("disabled", true);
        target.html("수정");
    } else {
        target.closest("div").find(".comment-content").prop("disabled", false);
        target.html("완료");
    }
});


//
// 전체선택하기 누를시 모든 체크박스 선택하기
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
	console.log("삭제할 번호 리스트 = " + deleteNumList);

			const sendData = {
				deleteNumList : deleteNumList
			}
			deletes(sendData);
	} else {
		alert("삭제할 리스트가 없습니다.");
	}

}


