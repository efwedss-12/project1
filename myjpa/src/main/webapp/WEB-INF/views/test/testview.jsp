<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:forEach 	items="${li}" var="li">
<table border="1">
	<tr>
		<td>${li.originFname }</td>
		<td><img src="/testimgs/${li.fname}" style="width:150px; height:100px;"></td>
		<td><button type="button" onclick="location.href='/test/del/${li.tfno}'">삭제</button></td>
	</tr>
</table>
</c:forEach>
<a href="/test/testupload">돌아가기</a>
</body>
</html>