<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<form action="/member/login" method="post">
  <p>
	<label>ID :</label>
	<input type="text" name="id">
  </p>	
  <p>
	<label>PASSWORD :</label>
	<input type="password" name="pwd">
  </p>
  <button type="submit">로그인</button>  	
  <button type="button" onclick="location.href='/member/join'">회원가입</button>
</form>
</body>
</html>