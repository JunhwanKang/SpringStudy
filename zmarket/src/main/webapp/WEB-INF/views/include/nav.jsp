<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
$(function() {
	$("#logout").on("click", function() {
		var $input = $("<input>").attr("type","hidden").attr("name","_csrf").val('${_csrf.token}');
		$("<form>").attr("action","/zmarket/user/logout").attr("method","post").append($input).appendTo("body").submit();
	});
})
</script>
</head>
<body>
	<div id="nav">
		<sec:authorize access="isAnonymous()">
			<div>
				<a href="/zmarket/user/login"><button type="button" id="login">로그인</button></a>
				<a href="/zmarket/user/join"><button type="button">회원가입</button></a>
				<a href="/zmarket/users/findUsername"><button type="button">아이디찾기</button></a>
				<a href="/zmarket/users/findPassword"><button type="button">비밀번호찾기</button></a>
			</div>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			<div>
				 <sec:authentication property="principal.username"/>님 안녕하세요
				<button type="button" id="logout">로그아웃</button><hr>
			</div>
		</sec:authorize>
		<span>득템마켓</span>
		<input type="search" name="search" placeholder="상품명, 지역명 입력">
		<a href="/zmarket/product/sell"><button type="button">판매하기</button></a>
		<button type="button">내상점</button>
		<button type="button">알림</button>
	</div>
</body>
</html>