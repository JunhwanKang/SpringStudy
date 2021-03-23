<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	var websocket;
	$("#connect").on("click", function(){
		websocket = new WebSocket("ws://localhost:8081/zws/do_chat2");
		
		websocket.onmessage = function(msg){
			console.log(msg);
			$("#chat_area").append(msg.data + "<br>")
		}
		websocket.onopen = function(){
			alert("연결되었습니다");
		}
		websocket.onclose = function(){
			alert("연결이 종료되었습니다");
		}
	});
	$("#disconnect").on("click", function(){
		websocket.close();
	});
	$("#message").on("keypress", function(e){
		if(e.keyCode==13){
			websocket.send($("#message").val());
			$("#message").val("");
		}
	});
});
</script>
<style>
	#chat_area {
		width: 200px;
		height: 200px;
		overflow: auto;
		border: 1px solid black;
	}
</style>
</head>
<body>
	<button id="connect">연결</button>
	<button id="disconnect">종료</button>
	<h1>대화 영역</h1>
	<div id="chat_area"></div>
	<input type="text" id="message">
</html>