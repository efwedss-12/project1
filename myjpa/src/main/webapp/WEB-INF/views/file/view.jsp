<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>사진 확인</title>
</head>
<body>
		<img src="/img/${file.filename}" style="width:500px; height:auto;">
	<div>
	</div>
	<button type="button" onclick="location.href='/file/insert'">돌아가기</button>
	<button type="button" onclick="location.href='/file/delete/${file.fno}'">삭제</button>
</body>
</html>