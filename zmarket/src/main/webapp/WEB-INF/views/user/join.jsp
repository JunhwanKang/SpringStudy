<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<script>
function usernameCheck(){
		$("#usernameMsg").text("");
		if($("#username").val()==""){
			$("#usernameMsg").text("필수입력입니다").css("color", "red").css("font-size", "0.75em");
			return false;			
		}
		
		$.ajax({
			url : "/zmarket/users/usernameCheck",
			method : "get",
			data : {
				username: $("#username").val()
			}
		}).done(()=>$("#usernameMsg").text("사용가능한 아이디입니다").css("color", "green").css("font-size", "0.75em"))
		.fail(()=>$("#usernameMsg").text("이미 사용중인 아이디입니다").css("color", "red").css("font-size", "0.75em"));
}

function passwordCheck(){
		$("#passwordMsg").text("");
		if($("#password").val()==""){
			$("#passwordMsg").text("필수입력입니다").css("color", "red").css("font-size", "0.75em");
			return false;			
		}
}
function emailCheck(){
		$("#emailMsg").text("");
		if($("#email").val()==""){
			$("#emailMsg").text("필수입력입니다").css("color", "red").css("font-size", "0.75em");
			return false;			
		}
		$.ajax({
			url : "/zmarket/users/emailCheck",
			method : "get",
			data : {
				email: $("#email").val()
			}
		}).done(()=>$("#emailMsg").text("사용가능한 이메일입니다").css("color", "green").css("font-size", "0.75em"))
		.fail(()=>$("#emailMsg").text("이미 사용중인 이메일입니다").css("color", "red").css("font-size", "0.75em"));
}
function nameCheck(){
		$("#nameMsg").text("");
		if($("#name").val()==""){
			$("#nameMsg").text("필수입력입니다").css("color", "red").css("font-size", "0.75em");
			return false;			
		}
}

$(document).ready(function(){
	$("#username").on("blur", usernameCheck);
	$("#password").on("blur", passwordCheck);
	$("#email").on("blur", emailCheck);
	$("#name").on("blur", nameCheck);
	
	$("#join").on("click", function(){
		var check1 = usernameCheck()
		var check2 = passwordCheck()
		var check3 = emailCheck()
		var check4 = nameCheck();
		var allCheck = check1&&check2&&check3&&check4;
		if(allCheck!=false){
			var user = {
					username: $("#username").val(),
					password: $("#password").val(),
					email : $("#email").val(),
					name : $("#name").val(),
					_csrf: "${_csrf.token}"
			}	
			$.ajax({
				url : "/zmarket/user/join",
				method : "post",
				data: user
			}).done(()=>alert("이메일을 확인하여 회원가입을 완료해주세요"));
		}
	})
	
})
</script>
<body>
	<div class="container" style="width: 800px;">
		<jsp:include page="../include/nav_sub.jsp"></jsp:include>
		<h2>회원가입</h2>
		
			<div class="form-group">
				<label for="usr">아이디:</label> 
				<input type="text" class="form-control" id ="username" name="username">
				<span id="usernameMsg"></span>
			</div>
			<div class="form-group">
				<label for="pwd">비밀번호:</label> 
				<input type="password" class="form-control" id="password" name="password">
				<span id="passwordMsg"></span>
			</div>
			<div class="form-group">
				<label for="email">이메일:</label>
				<input type="text" class="form-control" id="email" name="email">
				<span id="emailMsg"></span>
			</div>
			<div class="form-group">
				<label for="name">이름:</label> 
				<input type="text" class="form-control" id="name" name="name">
				<span id="nameMsg"></span>
			</div>
			<button type="button" class="btn btn-primary" id="join">가입</button>
	</div>
</body>
</html>