<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 읽기</title>
<style>
	#board_read{
		display: inline-block;
		margin: 0 auto;
	}
	#title{
		text-align: center;
		width:200px;
  		height:100px;
  		font-size:20px;
  		margin:0;
	}
	.writer{
		font-size:15px;
	}
	.writeTime{
		font-size:15px;	
		float: right;
	}
	.readCnt{
		font-size:15px;
		float: right;	
	}
	
	#board_content{
		min-height: 300px;
	}
</style>
<!-- 로그인 여부, 글쓴이 여부(글쓴이면 글 삭제, 수정가능)-->
<script src="/sboard/ckeditor/ckeditor.js"></script>
<sec:authorize access="isAuthenticated()">
	<script>
		var isLogin = true;
		var isWriter = ${board.isWriter};
	</script>
</sec:authorize>

<sec:authorize access="isAnonymous()">
	<script>
		var isLogin = false;
		var isWriter = false;
	</script>
</sec:authorize>

<script>
$(function() {
	
	if(isLogin==true && isWriter==true){
		$("#board_title").prop("disabled", false);
		var ck = CKEDITOR.replace('board_content', {
			filebrowserUploadUrl : '/sboard/boards/ck?_csrf=${_csrf.token}'
			
		});
		
		$("#comment_box").prop("readonly", false)
		.attr("placeholder", "욕설이나 모욕적인 댓글은 삭제될 수 있습니다");

		
	} else if(isLogin==true && isWriter==false) {
		$("#comment_box").prop("readonly", false)
		.attr("placeholder", "욕설이나 모욕적인 댓글은 삭제될 수 있습니다");

	}
	
	$("#go_list").on("click", function(){
		location.href="/sboard/board/list";
	})
	
	$("#write_comment").on("click", function(){
		var params = {
			bno: "${board.bno}",
			content: $("#comment_box").val(),
			_csrf: "${_csrf.token}"
		}
		$.ajax({
			url: "/sboard/comments",
			method: "post",
			data: params
		}).done(()=>location.reload());
	})
	
	$(".delete_attachment").on("click", function(){
		var params = {
				_csrf: "${_csrf.token}",
				_method: "delete",
				bno: $(this).data("bno")
		};
		$.ajax({
			url: "/sboard/attachments/" + $(this).data("ano"),
			method: "post",
			data: params
		}).done(()=>{
			alert("삭제되었습니다.");
			location.reload();
		});
	})
	
	$(".delete_comment").on("click", function(){
		console.log("클릭");
		console.log($(this).data("bno"));
		var params = {
			_csrf: "${_csrf.token}",
			_method: "delete",
			bno: $(this).data("bno")
		}
		$.ajax({
			url: "/sboard/comments/" + $(this).data("cno"),
			method: "post",
			data: params
		}).done(()=>{
			alert("댓글이 삭제되었습니다");
			location.reload();
		})
	});

	$("#update_content").on("click", function(){
		var formData = {
				content: $("#board_content")[0],
				title: $("#board_title"),
				_csrf: "${_csrf.token}"
		}
		$.ajax({
			url: "/sboard/board/updateContent",
			method: "post",
			data: formData,
			enctype: 'multipart/form-data',
			processData : false,
			contentType: false
		}).done(()=>alert("수정되었습니다"));
	})
	
	$("#delete_content").on("click", function(){
		if(confirm("정말로 삭제하시겠습니까?")==false)
			return;
		var params = {
				_csrf: "${_csrf.token}",
				bno: "<c:out value='${board.bno}'/>"
		}
		$.ajax({
			url: "/sboard/board/deleteContent",
			method: "post",
			data: params 
		})
	})
	
	// 댓글 수정 부분
	$(".update_comment").on("click", function(){
		
	})
});
</script>
</head>
<body>
	<div id="board_read">
		<div id="title">
			<span>${board.bno }</span>
			<input type="text" value="${board.title }" disabled="disabled" id="board_title"><br><hr>
		</div>
		<div id="board_info">
			<span class="writer">작성자: ${board.writer }</span>
			<span class="writeTime">작성일: ${board.writeTimeString }</span>
			<span class="readCnt">조회수: ${board.readCnt }</span>
		</div>
		<div>
			<ul id="attachment">
				<c:forEach items="${board.attachments }" var="attachment">
					<li style="overflow:hidden; width: 300px;">
						<c:if test="${attachment.isImage==true }">
							<i class="fa fa-file-image-o"></i>
						</c:if>
						<c:if test="${attachment.isImage==false }">
							<i class="fa fa-paperclip"></i>
						</c:if>
						<a href='/sboard/attachments/${attachment.ano }'>${attachment.originalFileName}</a>
						<div style="float:right;">										
							<c:if test="${board.isWriter==true}">
								<span class='delete_attachment' data-ano='${attachment.ano}' data-bno='${attachment.bno}' 
									title='${attachment.originalFileName} 삭제' style='cursor:pointer;'>X</span>
							</c:if>
						</div>
					</li>	
				</c:forEach>
			</ul>
		</div>
		<div id="board_content">${board.content }</div>
		<c:if test= "${board.isWriter == true}">
			<button type="button" id= "update_content">수정</button>
			<button type="button" id= "delete_content">삭제</button>
		</c:if>
		<div>
			<label for="comment_box">댓글을 입력하세요</label>
			<textarea id="comment_box" cols="80" rows="7" placeholder="댓글을 작성하려면 로그인을 해주세요" readonly="readonly"></textarea>
		</div>
		
		<button type="button" id="write_comment">댓글 쓰기</button><br>
		<button type="button" id="go_list">목록으로</button>
		<hr>
		<div id="comments">
			<c:forEach items="${board.comments }" var="comment">
				<div>
					<div>
						<span style="color:blue;">${comment.writer }</span> ${comment.writeTimeString }
					</div>
					<div style="overflow:hidden;">
						<img src="${comment.profile }" style="width:60px;">
						<span>${comment.content }</span>
						<c:if test="${comment.isWriter==true }">
							<button class="delete_comment" data-cno="${comment.cno}"
							data-bno="${comment.bno}" style="float:right;">삭제</button>
							<button class="update_comment" data-cno="${comment.cno}"
							data-bno="${comment.bno}" style="float:right;">수정</button>
						</c:if>
					</div>
				</div>
				<hr>
			</c:forEach>
		</div>
	</div>
</body>
</html>