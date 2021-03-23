<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/zws/user/login" method="post">
		<input type="text" name="username" value="spring"><br>
		<input type="password" name="password" value="1234"><br>
		<input type="hidden" name="_csrf" value="${_csrf.token }"><br>
		<button>로그인</button>
	</form>
</body>
</html>