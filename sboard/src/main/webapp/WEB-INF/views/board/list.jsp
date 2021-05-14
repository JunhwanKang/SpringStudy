<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h2>게시글</h2>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회</th>
				</tr>
			</thead>
			<c:forEach items="${page.list }" var="b">
				<tbody>
						<td>${b.bno }</td>
						<td>
							<c:if test="${b.attachmentCnt>0 }"><i class="fa fa-paperclip"></i></c:if>
							<a href="/sboard/board/read?bno=${b.bno}">${b.title }</a>&nbsp;&nbsp;[${b.commentCnt}]
						</td>
						<td>${b.writer }</td>
						<td>${b.writeTimeString }</td>
						<td>${b.readCnt }</td>
				</tbody>
			</c:forEach>
		</table>
		<ul class="pagination">
			<c:if test="${page.prev>0 }">
				<li><a href="/sboard/board/list?pageno=${page.prev }">이전으로</a></li>
			</c:if>
			
			<c:forEach begin="${page.start }" end="${page.end }" var="i">
				<c:if test="${page.pageNo==i}">
					<li class="active"><a href="/sboard/board/list?pageno=${i }">${i}</a></li>
				</c:if>
				<c:if test="${page.pageNo != i }">
					<li><a href="/sboard/board/list?pageno=${i }">${i}</a></li>
				</c:if>
			</c:forEach>
			
			<c:if test="${page.next>0 }">
				<li><a href="/sboard/board/list?pageno=${page.next }">다음으로</a></li>
			</c:if>
		</ul>
	</div>
</body>
</html>