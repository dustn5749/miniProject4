var submitBoardbtn = document.querySelector("#submitBoard");
var reroad = document.querySelector("#reroad");
var boardListbtn = document.querySelector("#boardList");

    // 게시판 목록으로 이동
boardListbtn.addEventListener("click", boardListShow);
function boardListShow(){
		location.href="/project3/board/boardForm.do";
};



// 게시판 추가하기 
submitBoardbtn.addEventListener("click", addBoardList);
function addBoardList(){
	let title = document.querySelector("#title").value;
	let id = document.querySelector("#newboardWriter").innerHTML;
	let content = document.querySelector("#content-memo").value;
	let sort = document.querySelector("#selectbox").value;
	if(title != ""){
		const param = {
		title : title,
		id : id, 
		content : content,
		sort : sort
	 }
			let sort2="all";
		fetch("/project3/board/insertBoard.do", {
        method: 'POST',
       	headers : {
                'Content-Type': 'application/json; charset=UTF-8'
		   },
		   body : JSON.stringify(param)
	})
	.then(response => response.json())
	.then(function(data) {
		if(data.result){
			alert("게시판 등록이 완료되었습니다.");
			location.href="/project3/board/boardForm.do";
		}else {
			alert("제목을 입력해주세요.");
		}
	})
	}
}

// 새로고침 버튼 누를 시  내용 리셋
reroad.addEventListener("click", reloadWriteBoard);
function reloadWriteBoard(){
	document.querySelector("#title").value = "";
   document.querySelector("#content-memo").value="";
}

