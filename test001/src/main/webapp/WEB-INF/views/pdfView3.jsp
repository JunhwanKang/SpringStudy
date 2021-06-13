<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	.pdfobject-container { height: 800px; border: 1rem solid rgba(0,0,0,.1); }
</style>
</head>
<body>
	<div id="examPdf"></div>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfobject/2.2.5/pdfobject.min.js"></script>
	<script src="/js/pdfobject.js"></script>
	<script>PDFObject.embed("pdfFiles/sessionInfo.pdf", "#examPdf");</script>
</body>
</html>