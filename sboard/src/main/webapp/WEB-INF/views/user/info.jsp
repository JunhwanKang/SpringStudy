<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	
	#profile_img{
		width: 400px;
		height: 300px;
		margin: 0 auto;
		margin-bottom: 30px;
	}
	#info{
		width: 500px;
		margin: 0 auto;	
	}
	.info_name{
		width: 150px;
		background: lightgreen;
		text-align: center;
	}
	#btn_div{
		width: 200px;
		margin: 0 auto;
		margin-top: 30px;
	}
</style>
<script>
$(document).ready(function(){
	$("#pwd_area").hide();
	$("#pwd_btn").on("click", function(){
		$("#pwd_area").toggle();
	})
	// 비밀번호 박스 css 처리
	$("#pwd").on("blur", function(){
		$("#pwd_msg").text("");
		var $pwd = $("#pwd").val();
		if($pwd==""){
			$("#pwd_msg").text("필수입력입니다").css("color", "red").css("font-size", "0.75em");
			return false;			
		}
	})
	$("#new_pwd").on("blur", function(){
		$("#newPwd_msg").text("");
		var $new_pwd = $("#new_pwd").val();
		if($new_pwd==""){
			$("#newPwd_msg").text("필수입력입니다").css("color", "red").css("font-size", "0.75em");
			return false;			
		}
	})
	$("#new_pwd_check").on("blur", function(){
		$("#newPwdCheck_msg").text("");
		var $new_pwd_check = $("#new_pwd_check").val();
		if($new_pwd_check==""){
			$("#newPwdCheck_msg").text("필수입력입니다").css("color", "red").css("font-size", "0.75em");
			return false;			
		}
		if($new_pwd_check!=""){
			var $new_pwd = $("#new_pwd").val();
			var $new_pwd_check = $("#new_pwd_check").val();
			if($new_pwd!==$new_pwd_check)
				$("#newPwdCheck_msg").text("비밀번호가 일치하지 않습니다").css("color", "red").css("font-size", "0.75em");
		}
	})
	
	// 비밀번호 변경 버튼을 눌렀을 때
	$("#pwd_change").on("click", function(){
		var params = {
				password : $("#new_pwd").val(),
				_csrf: "${_csrf.token}"
		}
		$.ajax({
			url: "/sboard/users/newPassword",
			method: "post",
			data: params
		}).done(()=>alert("비밀번호가 변경되었습니다"));
	})
	
	// 회원 탈퇴
	$("#resign").on("click", function() {
		if(confirm("정말로 탈퇴하시겠습니까?")==false)
			return;
		var $form = $("<form>").attr("action","/sboard/user/resign").attr("method","post").appendTo($("body"));
		$("<input>").attr("type","hidden").attr("name","_csrf").val("${_csrf.token}").appendTo($form);
		$form.submit();
	
})
</script>
</head>
<body>
	<div id="info_page">
		<div id="profile_img">
			<img src="${user.profile }">
		</div>
		<table class="table table-hover" id="info">
			<tr>
				<td class="info_name">이름</td>
				<td>${user.irum }<td>
			</td>	
			<tr>
				<td class="info_name">생년월일</td>
				<td>${user.birthday }<td>
			</td>	
			<tr>
				<td class="info_name">가입일</td>
				<td>${user.joinday}<td>
			</td>	
			<tr>
				<td class="info_name">비밀번호</td>
				<td colspan="2">
					<button type="button" id="pwd_btn" class="btn btn-success">비밀번호 수정</button>
					<div id="pwd_area">
						현재 비밀번호: <input type="password" id="pwd"><br>
						<span id="pwd_msg"></span><br>
						새 비밀번호:  <input type="password" id="new_pwd"><br>
						<span id="newPwd_msg"></span><br>
						새 비밀번호 확인:  <input type="password" id="new_pwd_check"><br>
						<span id="newPwdCheck_msg"></span><br>
						<button type="button" id="pwd_change" class="btn btn-success">변경</button>
					</div>
				<td>
			</td>	
			<tr>
				<td class="info_name">이메일</td>
				<td>${user.email}<td>
			</td>	
		</table>
		<div id="btn_div">
			<button type="button" id="info_change" class="btn btn-success">변경</button>
			<button type="button" id="info_delete" class="btn btn-danger">탈퇴</button>
		</div>
	</div>	
</body>
</html>