var uid = document.querySelector("#uid");
var pwd = document.querySelector("#pwd");
var pwd2 = document.querySelector("#pwd2");
var user = document.querySelector("#name");
var phone = document.querySelector("#phone");
var checkPwdBtn = document.querySelector("#pwdcheckBtn");
var checkIdBtn = document.querySelector("#idcheckBtn");
var allCheck = document.querySelector("#allCheck");
var checkTermsA = document.querySelectorAll(".checkTermsA");
var checkTermsB = document.querySelectorAll(".checkTermsB");
var backBtn = document.querySelector("#backBtn");
var joinBtn = document.querySelector("#joinBtn");

var idTest = false;
// 아이디 유효성 검사
uid.addEventListener("keyup", testId);
console.log(uid);
function testId(){
	let result = false;
	let idRegex = /^[a-zA-Z0-9]{4,12}$/;
	let testReturn = document.querySelector("#testIdReturn");
	if(idRegex.test(this.value)){
		testReturn.innerHTML = "";
		idCheck =  true;
		result = true;
	} else {
		testReturn.innerHTML = "영문, 숫자 4-12자로 입력해주세요.";
		testReturn.style.color = "red";
	}
	return result;
}
console.log("idTest = " + idTest);

// 아이디 중복확인.
checkIdBtn.addEventListener("click",idCheck);
function idCheck(){
	var uid = document.querySelector("#uid").value;
	const param = {uid:uid}
	console.log(param);
	if(testId()){
		fetch("/project3/member/existUid.do", {
        method: 'POST',
       	headers : {
                "Content-Type": "application/json; charset=UTF-8",
		   },
		   body : JSON.stringify(param)
	})
	.then(response => response.json())
	.then(function(data) {
		if(data.result){
			alert("이미 존재하는 아이디입니다.");
		}else{
			alert("사용가능한 아이디 입니다.");
			
		}
	})
	} else {
		alert("알맞은 아이디를 입력해주세요");
	}
}



// 패스워드 유효성 검사
pwd.addEventListener("keyup", testPwd);
function testPwd(){
	let result = false;
	let pwdRegex = RegExp(/^(?=.*[A-Za-z])(?=.*[0-9]).{5,16}$/);
	let testReturn = document.querySelector("#testPwdReturn");
	if(pwdRegex.test(this.value)){
		testReturn.innerHTML = "";
		result = true;
	} else {
		testReturn.innerHTML = "영문, 숫자 조합 5-16자로 입력해주세요.";
		testReturn.style.color = "red";
	}
	return result;
}
//패스워드 일치 확인.
checkPwdBtn.addEventListener("click", function(){
if(testPwd()){
	var result = false;
	if(pwd.value === pwd2.value){
		result = true;
	}
	if(!result){
		alert("비밀번호가 일치하지 않습니다.");
		pwd.value = "";
		pwd2.value = "";
	} 
}alert("알맞은 형태의 비밀번호를 입력해주세요");

});



// 이름 유효성 검사
user.addEventListener("keyup", testName);
function testName(){
    let nameTest = RegExp(/[가-힣]{2,10}/);
	let testReturn = document.querySelector("#testNameReturn");
if(nameTest.test(this.value)){
		testReturn.innerHTML = "";
	} else {
		testReturn.innerHTML = "이름 형태로 입력해주세요.";
		testReturn.style.color = "red";
	}
}

// 전화번호 유효성 검사
phone.addEventListener("keyup", testPhone);
function testPhone(){
	let phoneTest = RegExp(/[[0-9]{11}/);
	let testReturn = document.querySelector("#testPhoneReturn");
	if(phoneTest.test(this.value)){
		testReturn.innerHTML = "";
	} else {
		testReturn.innerHTML = "옳바른 전화번호를 입력해주세요.(-없이 입력)";
		testReturn.style.color = "red";
	}
	}


// 전체 클릭시 모든 요소 체크 해제시 모든 요소 해제
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
// 모든 요소 클릭 시 전체 요소 체크
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
//회원 가입 버튼 클릭시 필수 요소 체크 했는지 검사
joinBtn.addEventListener("click", function(){
	let cnt = 0;
	for(let i=0; i<checkTermsA.length; ++i){
		if(checkTermsA[i].checked){
			cnt++;
		}
	}
	if(cnt != checkTermsA.length){
		alert("필수 사항에 체크해주세요.");		
	}else  if(cnt == checkTermsA.length){
		const param = {
			uid:uid.value,
			pwd:pwd.value,
			name:user.value,
			phone:phone.value	
		};
			fetch("/project3/member/add.do", {
        method: 'POST',
       	headers : {
                "Content-Type": "application/json; charset=UTF-8",
		   },
		   body : JSON.stringify(param)
	})
		.then(response=>response.json())
		.then(function(data){
			if(data.result){
				alert("회원 가입에 성공하셨습니다.")
				if(confirm("로그인하기로 이동하시겠습까?") ){
				location.href = "/project3/member/loginForm.do";			
				}
			}
		})
	}
	 })

// 뒤로 가기 버튼 클릭 시 홈페이지로 돌아옴.
backBtn.onclick =()=>{
	location.href = "/project3/index/index.do";
}


// 마우스 위로 올릴 시/ 나갈 시 버튼 색상 변경 
checkIdBtn.addEventListener("mouseover", colorChange);
checkIdBtn.addEventListener("mouseout", colorBack);
checkPwdBtn.addEventListener("mouseover", colorChange);
checkPwdBtn.addEventListener("mouseout", colorBack);
joinBtn.addEventListener("mouseover", colorChange);
joinBtn.addEventListener("mouseout", colorBack);
backBtn.addEventListener("mouseover", colorChange);
backBtn.addEventListener("mouseout", colorBack);

function colorChange(){
	this.style.backgroundColor = "rgb(175, 240, 184)";
	this.style.color = "black";
}
function colorBack(){
	this.style.backgroundColor = "";
	this.style.color = "";
}