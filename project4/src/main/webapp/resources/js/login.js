var loginBtn = document.querySelector("#login");
var joinMembers = document.querySelector("#joinMembers");
var searchIdPwd = document.querySelector("#searchIdPwd");



// 로그인하기 누를시 아이디 비번값 확인하여 로그인하기
loginBtn.addEventListener("click", function(){
var uid = document.querySelector("#inputId").value;
var pwd = document.querySelector("#inputPwd").value;
	
	const param = {
		uid : uid,
		pwd : pwd
	};
	fetch("/project3/member/login.do", {
        method: 'POST',
       	headers : {
                "Content-Type": "application/json; charset=UTF-8",
		   },
        body: JSON.stringify(param),
	})
	.then(response => response.json())
	.then(function(data) {
		if(data.result == true){
			alert("로그인에 성공했습니다.");
			location.href="/project3/index/index.do";
		}else {
			alert("아이디 또는 비밀번호가 일치하지 않습니다.");
		}
	})
	.catch(error =>{
		console.error('Error:', error);
	})
})

// 마우스 위로 올릴 시/ 나갈 시 버튼 색상 변경 
loginBtn.addEventListener("mouseover", colorChange);
loginBtn.addEventListener("mouseout", colorBack);
joinMembers.addEventListener("mouseover", colorChange);
joinMembers.addEventListener("mouseout", colorBack);
searchIdPwd.addEventListener("mouseover", colorChange);
searchIdPwd.addEventListener("mouseout", colorBack);

function colorChange(){
	this.style.backgroundColor = "rgb(175, 240, 184)";
	this.style.color = "black";
}
function colorBack(){;
	this.style.backgroundColor = "";
	this.style.color = "";
}

//회원가입창으로 이동
joinMembers.addEventListener("click", function(){
	location.href= "/project3/member/joinForm.do";
})