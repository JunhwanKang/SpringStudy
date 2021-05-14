<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#btn{
		text-align:center;
	}
	#img_box{
		margin:0 auto;
		height:400px;
		width:400px;
		border-radius: 50%;
		text-align:center;
		overflow: hidden;
	}
	#show_profile{
		width: 100%;
    	height: 100%;
   		object-fit: cover;
	
	}
	
	.success {
		color: green;
		font-size: 0.75em;
	}
	
	.fail {
		color: red;
		font-size: 0.75em;
	}
</style>
<script>
function loadProfile() {
		var $file = $("#profile")[0].files[0];
		var maxSize = 1024*1024;			// 업로드할 프로필 최대 크기 : 1MB
		if($file.size>maxSize) {
			Swal.fire({
				icon: 'error',
				title : '프로필 크기 오류',
				text : '프로필 사진은 1MB를 넘을 수 없습니다'
			});
			$("#profile").val("");
			$("#show_profile").removeAttr("src");
			return false;
		}
	
		var $file = $("#profile")[0].files[0];
		var reader = new FileReader();
		
		reader.onload = function () {
		 	$("#show_profile").attr("src", reader.result);  
		}
		
		reader.readAsDataURL($file);
}
// name, pattern, span_msg, text
function check(name, pattern, span_msg, text){
	if(name==""){
		span_msg.text("필수 입력입니다").attr("class", "fail");
		return false;
	}
	if(pattern.test(name)==false){
		span_msg.text(text).attr("class", "fail");
		return false;
	}
	return true;
}	  

function idCheck(){
	var $username = $("#username").val().toUpperCase();
	$("#username").val($username);
	var pattern = /^[A-Z0-9]{8,10}$/;
	return check($username, pattern, $("#username_msg"), "아이디는 대문자와 숫자 8~10자입니다"); 
}	  

function emailCheck(){
	var $email = $("#email").val();
	var pattern =  /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	
	return check($email, pattern, $("#email_msg"), "올바르지 않은 이메일 형식입니다");
}

// 아이디 이메일 비밀번호 비밀번호확인 생년월일 이름
function passwordCheck(){
	$("#password_msg").text("");
	var $password = $("#password").val();
	var pattern = /^[0-9A-Za-z~!@#$%^&*()_+]{8,10}$/;
	
	return check($password, pattern, $("#password_msg"), "비밀번호는 영문, 숫자, 특수문자 8~10자입니다");
}

function birthdayCheck(){
	$("#birthday_msg").text("");
	var $birthday = $("#birthday").val();
	var pattern = /^[0-9]{4}-[0-9]{2}-[0-9]{2}$/;

	return check($birthday, pattern, $("#birthday_msg"), "정확한 생년월일을 입력해주세요");
}

function irumCheck(){
	$("#irum_msg").text("");
	var $irum = $("#irum").val();1
	var pattern = /^[가-힣]{2,}$/;

	return check($irum, pattern, $("#irum_msg"), "올바르지 않은 형식입니다");
}

function passwordSameCheck(){
	$("#password2_msg").text("");
	var $password = $("#password").val();
	var $password2 = $("#password2").val();
	
	if($password2==""){
		$("#password2_msg").text("필수입력입니다").attr("class", "fail");
		return false;
	}
	
	if($password2!==$password){
		$("#password2_msg").text("비밀번호가 일치하지 않습니다").attr("class", "fail");	
		return false		
	}
	return true;
}

function join() {
	var formData = new FormData($("#join_form")[0]);
	$.ajax({
		url: "/sboard/users/member",
		method: "post",
		data: formData,
		processData: false,
		contentType: false
	}).done(()=>Swal.fire("가입신청 완료","이메일을 확인하세요", "success"))
	.fail((msg)=>Swal.fire('가입신청 실패', msg,'error'));
}

$(document).ready(function() {
	$("#profile").on("change", loadProfile);
	$("#password").on("blur", passwordCheck);
	$("#irum").on("blur", irumCheck);
	$("#password2").on("blur", passwordSameCheck);
	$("#birthday").on("blur", birthdayCheck);
	
	$("#username").on("blur", function(){
		if(idCheck()==false)
			return false;
		$.ajax({
			url: "/sboard/users/user/username?username=" + $("#username").val()
		}).done(()=>{$("#username_msg").text("사용 가능한 아이디입니다").attr("class", "success")})
		.fail(()=>{$("#username_msg").text("이미 사용중인 아이디입니다").attr("class", "fail")})
	});
	
	$("#email").on("blur", function(){
		if(emailCheck()==false)
			return false;
		$.ajax({
			url:"/sboard/users/user/email?email=" + $("#email").val()
		}).done(()=>{$("#email_msg").text("사용 가능한 이메일입니다").attr("class", "success")})
		.fail(()=>{$("#email_msg").text("이미 사용중인 이메일입니다").attr("class", "fail")})
	})
	
	$("#join").on("click", function(){
		var r1 = idCheck();
		var r2 = emailCheck();
		var r3 = passwordCheck();
		var r4 = birthdayCheck();
		var r5 = irumCheck();
		var r6 = passwordSameCheck();
		
		if((r1&&r2&&r3&&r4&&r5&&r6)==false){
			return false;
		}
			
		$.when($.ajax("/sboard/users/user/username?username="+$("#username").val()),
				$.ajax("/sboard/users/user/email?email="+$("#email").val()))
			.done(()=>join()).fail(()=>Swal.fire('확인 실패','아이디나 이메일이 사용중입니다',"error"));
	});
});
</script>
</head>
<body>
	<div class="container">
		<form id="join_form">
			<div id="img_box">
				<img id="show_profile">
				<input type="hidden" name="_csrf" value="${_csrf.token }">
			</div>
			<div>
				<label for="profile">프로필 사진</label>
				<input id="profile" type="file" name="profile" class="form-control"  accept=".jpg,.jpeg,.png,.gif">
			</div>
			<div>
				<label for="username">아이디</label>
				<span id="username_msg"></span>
				<div class="form-group">
					<input type="text" class="form-control" id="username" name="username">
				</div>
			</div>
			<div>
				<label for="irum">이름</label>
				<span id="irum_msg"></span>
				<div class="form-group">
					<input type="text" class="form-control" id="irum" name="irum">
				</div>
			</div>
			<div>
				<label for="password">비밀번호</label>
				<span id="password_msg"></span>
				<div class="form-group">
					<input id="password" type="password" class="form-control" name="password">
				</div>
			</div>
			<div>
				<label for="password2">비밀번호 확인</label>
				<span id="password2_msg"></span>
				<div class="form-group">
					<input id="password2" type="password" class="form-control">
				</div>	
			</div>
			<div>
				<label for="email">이메일</label>
				<span id="email_msg"></span>
				<div class="form-group">
					<input id="email" type="text" name="email" class="form-control">
				</div>
			</div>
			<div>
				<label for="birthday">생년월일</label>
				<span id="birthday_msg"></span>
				<div class="form-group">
					<input id="birthday" type="date" name="birthday" class="form-control">
				</div>
			</div>
			<div id="btn">
				<button type="button" id="join" class="btn btn-primary">가입</button>
				<button type="button" id="home" class="btn btn-basic">홈으로</button>
			</div>
		</form>
	</div>
</body>
</html>