<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>file upload</title>
</head>
<style>
	img{
		width:200px;
		height:auto;
	}
</style>
<body>

	<h2>파일업로드</h2>

	<div class="container">
		<form action="/file/fileinsert" method="post"
			enctype="multipart/form-data">
			<input type="file" id="image" name="files" accept="image/*" onchange="setThumbnail(event)"> 
			<!-- 여기서 files는 controller에 @RequestPart MultipartFile files -->
			<div id="imgView" ></div>
			<button type="submit" class="btn btn-dark">업로드</button>
		</form>
	</div>
	<div>
		<h2>업로드 목록</h2>
		<ul>
		<c:forEach items="${list }" var="li">
				<li><a href="http://localhost:8888/file/view/${li.fno }">${li.fno }</a></li>
		</c:forEach>
		</ul>
	</div>
	
<script>
	function setThumbnail(event){
		var reader = new FileReader();
		
		reader.onload = function(event){
			var img = document.createElement("img");
			img.setAttribute("src",event.target.result);
			document.querySelector("div#imgView").appendChild(img);
		};
		
		reader.readAsDataURL(event.target.files[0]);
	}
</script>	
</body>
</html>