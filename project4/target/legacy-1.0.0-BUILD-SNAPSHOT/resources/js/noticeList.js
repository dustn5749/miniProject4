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
					
					$(this).dialog("close");
				}
			}
		})


		
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
		
		
		


function jsPageNo(pageNo) {
	console.log("페이지 이동" + pageNo);
	document.querySelector("#pageNo1").value = pageNo;
	document.querySelector("#pageForm").submit(); 
}

	

