<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/zmarket/user/findUsername" method="post">
			<div class="form-group">
				<label for="name">
				이름:</label> <input type="text" class="form-control" id="name" name="name" placeholder="이름을 입력해 주세요.">
			</div>
			<div class="form-group">
				<label for="email">
				이메일:</label> <input type="text" class="form-control" id="email" name="email" placeholder="이메일 주소전체를 입력해 주세요.">
			</div>
			<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
			<button class="btn btn-primary" id="findUsername_btn">아이디 찾기</button>
		</form>
</body>
</html>