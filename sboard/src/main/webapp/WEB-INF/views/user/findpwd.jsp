<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	/* float을 clear하는 방법 -> overflow:hidden; */
	#reset { width: 780px; margin: 0 auto; overflow: hidden;}
	#reset_pwd { width: 50%; float: right; margin-top: 50px; }
</style>
<script>
$(document).ready(function() {
	$("#resetPwd").on("click", function() {
		$("#reset_form").attr("action","/sboard/user/reset_pwd").attr("method","post").submit();
	})
})
</script>
</head>
<body>
	<div id="reset">
		<div id="reset_pwd">
			<form id="reset_form">
				<div class="form-group">
					<label for="username">아이디</label>
					<span id="username"></span>
					<input type="text" name="username" id="username">
				</div>
				<div class="form-group">
					<label for="email_right">이메일</label>
					<span id="email_msg_right"></span>
					<input type="text" name="email" id="email_right">
				</div>
				<input type="hidden" name="_csrf" value="${_csrf.token }">
				<button type="button" class="btn btn-success" id="resetPwd">비밀번호 찾기</button>
			</form>
		</div>
	</div>
</body>
</html>







