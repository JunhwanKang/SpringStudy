<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Insert title here</title>
<script>
	$(function(){
		$("#view_btn1").on("click", function(){
			window.open("http://localhost:8081/test001/pdfView1","_blank");
		})
		$("#view_btn2").on("click", function(){
			window.open("http://localhost:8081/test001/pdfView2","_blank");
		})
		$("#view_btn3").on("click", function(){
			window.open("http://localhost:8081/test001/pdfView3","_blank");
		})
	})
</script>
</head>
<body>
	<button id="view_btn1" type="button">미리보기(iframe)</button><hr>
	<button id="view_btn2" type="button">미리보기(embed)</button><hr>
	<button id="view_btn3" type="button">미리보기(PdfObject)</button><hr>
</body>
</html>