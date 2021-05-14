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
<style>
 #login_btn{
 	 font-size:20px; padding: 15px 119px
 }
</style>
<body>
	<div class="container" style="width: 800px;">
		<jsp:include page="../include/nav_sub.jsp"></jsp:include>
		<h2>login</h2>
		<form action="/zmarket/user/login" method="post">
			<div class="form-group">
				<label for="username">
				아이디:</label> <input type="text" class="form-control" id="username" name="username">
			</div>
			<div class="form-group">
				<label for="pwd">
				비밀번호:</label> <input type="password" class="form-control" id="pwd" name="password">
			</div>
			<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
			<button class="btn btn-primary" id="login_btn">로그인</button>
			<div>
				<a href="${naver_url }">
					<img width="300" alt="Naver login" src="../resources/img/naver_login.png">
				</a>
			</div>
		</form>
	</div>
</body>
</html>