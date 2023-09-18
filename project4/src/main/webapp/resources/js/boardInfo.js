

// 목록보기 버튼 누를시 전체 게시판 목록보기
document.querySelector("#boardListbtn").addEventListener("click", function(){
	location.href="/project3/board/list.do";
})

let currentLikeCount = parseInt(document.querySelector("#contSpan").innerHTML); 

//  좋아요 버튼 누를 시 좋아요 개수 증가
const loveBtn = document.querySelector("#loveBtn");
loveBtn.addEventListener("click", () => {
    const boardnum = document.querySelector("#newboardNo").innerHTML;
    const param = {
        boardnum: boardnum
    };
    console.log("boardNum = " + boardnum);
    fetch("/project3/love/updateLove.do", {
        method: 'POST',
        headers: {
            "Content-Type": "application/json; charset=UTF-8",
        },
        body: JSON.stringify(param)
    })
    .then(response => response.json())
    .then(function(data) {
        if (data.count != 0) {
            currentLikeCount += data.count;
            contSpan.innerHTML = currentLikeCount;
        }
    });
});

// 댓글 등록하기
	const replyBtn = document.querySelector("#replyBtn");
	replyBtn.addEventListener("click", ()=>{
	let id  = document.querySelector("#idSpan").innerHTML;
 	let content = document.querySelector("#reply").value;
 	let parentnum = document.querySelector("#newboardNo").innerHTML;
	console.log("parentnum = " + parentnum + "reply = " + reply);
	const param  ={
		id : id,
		content:content,
		parentnum : parentnum
	}
 	fetch("/project3/comment/insetComment.do", {
		 method:'POST',
		 headers:{
			        "Content-Type": "application/json; charset=UTF-8", 
		 },
		body: JSON.stringify(param)
	 })
	  .then(response => response.json())
      .then(function (data) {
        if (data.result) {
          alert("댓글이 등록되었습니다.");
         	document.querySelector("#boardInfoFm").submit();
        }
      })
})

// 댓글 수정하기
const updateReplyButtons = document.querySelectorAll(".updateReply");
for(let i=0; i<updateReplyButtons.length; ++i){
	updateReplyButtons[i].addEventListener("click", ()=>{
		 const replyContentInput = updateReplyButtons[i].closest(".replyArea").querySelector(".replyInput");
        
        if (updateReplyButtons[i].innerHTML === "수정하기") {
            replyContentInput.disabled = false;
            replyContentInput.focus(); // 입력 필드 선택
            updateReplyButtons[i].innerHTML = "수정완료";			
            updateReplyButtons[i].style.backgroundColor = "rgb(186, 235, 186)";
        } else if (updateReplyButtons[i].innerHTML === "수정완료") {
            replyContentInput.disabled = true;
            updateReplyButtons[i].innerHTML = "수정하기";	
            updateReplyButtons[i].style.backgroundColor = "#c0e0fe";	
        
            const reply = replyContentInput.value;
            console.log("reply = " + reply);
            const boardnum = updateReplyButtons[i].closest(".replyArea").querySelector(".replynum").textContent.trim();
            const parentnum = document.querySelector("#newboardNo").innerHTML;
            console.log("boardnum = " + boardnum);
            console.log("parentnum = " + parentnum);
         
			const param = {
				boardnum : boardnum,
				content : reply,
				parentnum : parentnum 
			};
			
            fetch("/project3/comment/updateComment.do", {
                    method: 'POST',
                    headers: {
                        "Content-Type": "application/json; charset=UTF-8", 
                    },
                    body: JSON.stringify(param)
                })
                .then(response => response.json())
                .then(function (data) {
                    if (data.result) {
                        alert("댓글이 수정되었습니다.");
                       document.querySelector("#boardInfoFm").submit();
                    }
                })
 
        }
		
	})
}

// 댓글 삭제하기
const deleteReplybtn = document.querySelectorAll(".deleteReply");
for(let i=0; i<deleteReplybtn.length; ++i){
	deleteReplybtn[i].addEventListener("click", ()=>{
	let loginMemberPwd = document.querySelector("#loginMemberPwd").value; 
     const replynum = deleteReplybtn[i].closest(".replyArea").querySelector(".replynum").textContent.trim();
     console.log(loginMemberPwd)
     let pwd = prompt("댓글을 삭제하시려면 비밀번호를 입력해주세요.");
     
     const parentnum = document.querySelector("#newboardNo").innerHTML;
   	console.log("replynum = " + replynum);
     console.log("pwd = " + pwd);      
     
     
           if(pwd == ""){
				alert("비밀번호를 입력해주세요.");
			} 
			else if(loginMemberPwd === pwd){
		 	const param = {
				 boardnum : replynum
			 }    
               fetch("/project3/comment/deleteComment.do", {
                    method: 'POST',
                    headers: {
                        "Content-Type": "application/json; charset=UTF-8", 
                    },
                    body:JSON.stringify(param)
                } )
                .then(response => response.json())
                .then(function (data) {
                    if (data.result) {
                        alert("댓글이 삭제되었습니다.");
                        document.querySelector("#boardInfoFm").submit();
                        }
                })
			} else {
				alert("비밀 번호가 일치하지 않습니다.")
			}

        }
	)
}

// 게시글 수정하기 
var boardUpdatebtn = document.querySelector("#boardUpdatebtn");
boardUpdatebtn.addEventListener("click", function () {
  if (boardUpdatebtn.innerHTML === "수정하기") {
    document.querySelector("#title").disabled = false;
    document.querySelector("#selectbox").disabled = false;
    document.querySelector("#content-memo").disabled = false;
    boardUpdatebtn.innerHTML = "수정완료";

  } else if (boardUpdatebtn.innerHTML === "수정완료") {
    document.querySelector("#title").disabled = true;
    document.querySelector("#selectbox").disabled = true;
    document.querySelector("#content-memo").disabled = true;
    boardUpdatebtn.innerHTML = "수정하기";
    let title = document.querySelector("#title").value;
    let id = document.querySelector("#newboardWriter").innerHTML;
    let sort = document.querySelector("#selectbox").value;
    let content = document.querySelector("#content-memo").value;
    let boardnum = document.querySelector("#newboardNo").innerHTML;
    console.log("boardnum = " + boardnum);
	
	const param = {
		boardNum : boardnum,
		title : title,
		id : id,
		content : content,
		sort : sort,
	}
		
    fetch("/project3/board/boardUpdate.do", {
      method: "POST",
      headers: {
        "Content-Type": "application/json; charset=UTF-8",
      },
      body:  JSON.stringify(param)

    })
      .then(response => response.json())
      .then(function (data) {
		  alert(data.message);
        if (data.result) {
    	 document.querySelector("#boardInfoFm").submit();
        }
      })
  }
});

//게시판 삭제하기
let boardDeletebtn = document.querySelector("#boardDeletebtn");
	boardDeletebtn.addEventListener("click", ()=>{
	let boardnum = [parseInt(document.querySelector("#newboardNo").innerHTML)];
	console.log(boardnum);
	
	const param = {
		deleteNumList : boardnum
	}
	
	fetch("/project3/board/deleteBoards.do",{
		 method: "POST",
      headers: {
        "Content-Type": "application/json; charset=UTF-8",
      },
      body:  JSON.stringify(param)
    })
      .then(response => response.json())
      .then(function (data) {
        if (data.result) {
          alert("게시판 삭제가 완료되었습니다.");
    		location.href = "/project3/board/boardForm.do?sort=all" ;
        }
      })
     
})