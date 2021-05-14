<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	.imgs_wrap{
		width: 600px;
		margin-top: 50px;
	}
	.imgs_wrap img {
		max-width: 200px;
	}
</style>
<script>
	var sel_files = [];
	
	$(document).ready(function() {
		$("#product_image").on("change", handleImgsFileSelect);
		$("#register").on("click", registerFun);
	});
	
	function handleImgsFileSelect(e) {
		var files = e.target.files;
		var filesArr = Array.prototype.slice.call(files);
		
		filesArr.forEach(function(f) {
			if(!f.type.match("image.*")) {
				alert("이미지 파일만 가능합니다");
				return;
			}
			
			sel_files.push(f);
			
			var reader = new FileReader();
			reader.onload = function(e) {
				var img_html = "<img src=\""+ e.target.result+"\" />";
				$(".imgs_wrap").append(img_html);
			}
			reader.readAsDataURL(f);
		});
	}
	
	function registerFun(){
		$.ajax({
			type: "post",
			enctype: "formData",
			url: "/zmarket/product/sell",
			processData: false,
			contentType: false
		}).done(()=>alert("등록되었습니다")).fail(()=>alert("등록 실패"));
	}
	
	
</script>
<body>
	<h3>상품 등록</h3>
	<form action="zmarket/product/sell" method="post">
		<table border="1">
			<tr>
				<td>상품이미지1</td>
				<td>
					<input type="file" id="product_image" multiple/>
					<div class="imgs_wrap">
						
					</div>
				</td>
			</tr>
			<tr>
				<td>카테고리<td>
				<select>
					<option>카테고리선택</option>
					<option>디지털/가전</option>
					<option>가구/인테리어</option>
					<option>유아동/유아도서</option>
					<option>생활/가공식품</option>
					<option>스포츠/레져</option>
					<option>여성잡화</option>
					<option>여성의류</option>
					<option>남성패션/잡화</option>
					<option>게임/취미</option>
					<option>뷰티/미용</option>
					<option>반려동물용품</option>
					<option>도서/티켓/음반</option>
					<option>식물</option>
					<option>기타 중고물품</option>
				</select>
			</tr>
			<tr>
				<td>가격</td>
				<td><input type="text" name="price"></td>
			</tr>
			<tr>
				<td colspan="2">
					<p>상품 설명</p>
					<textarea></textarea>
				</td>
			</tr>
		</table>
		<button type="button" id="register">등록</button>
	</form>
</body>
</html>