<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form modelAttribute="formVo" action="/save" method="post">
		<table>
			<tr>
				<td><form:label path="name">이름</form:label></td>
				<td><form:label path="name"/></td>
			</tr>
			<tr>
				<td>아이디</td>
				<td><form:input path="id"/></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><form:input path="pwd"/></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><form:input path="email"/></td>		
			</tr>		
		</table>
		<input type="submit" value="전송"/>
	</form:form>
</body>
</html>