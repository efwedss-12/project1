<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>    
</head>
<style>
	img{
		width:200px;
		height:auto;
	}
</style>
<body>
<h3 align="center">글작성 폼</h3>
<form action="/board/write" method="post" enctype="multipart/form-data">
  <table border="1" align="center">
		<tr>
			<th>작성자</th><td align="center">${sessionScope.id }</td>
		</tr>
		<tr>
			<th>제 목</th><td align="center"><input type="text" name="title" id="title"></td>
		</tr>
		<tr>
			<td colspan="2">
			<!-- <input type="text" name="content" style="height: 300px; margin: 10px; display: inline-block"/> -->
			<textarea rows="5" cols="50" name="content" id="content"></textarea>
			</td>
		</tr>
		<tr>
		<th>
 			 <input type="submit" value="작성" id="btn">		 		
		</th>
		</tr>
  </table>
  <input type="hidden" name="writer" value="${sessionScope.id }">
  
  <input type="hidden" name="parent" value="${parent }">
  <table border="1" align="center">
  <tr>
  	<th>
		<input multiple="multiple" type="file"  id="image" name="files" accept="image/*" onchange="setThumbnail(event)"> 
		<!-- 여기서 files는 controller에 @RequestPart MultipartFile files -->
		
  	</th>
  </tr>
  <tr> 
  <td><div id="imgView" ></div></td>
  </tr>
  </table>
</form>
  <br>  
  
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
<script type="text/javascript">
	$(document).ready(function(){
		$("#btn").click(function(){
			if($("#title").val().length==0){
				alert("제목을 입력하세요");
				return false;
			}
			if($("#content").val().length==0){
				alert("내용을 입력하세요");
				return false;
			}
		});
	});
</script>	
</body>
</html>