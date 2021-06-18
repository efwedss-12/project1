<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 수정</title>
</head>
<style>
  table, th, td {
    border: 1px solid #bcbcbc;
  }
table{
	width:600px;
}
</style>
<body>

<h2 align="center">수정</h2>
<br><br>
<div id="outter">
<form action="/board/edit" method="post">
	<table align="center">
		
		<tr>
			<th width="30%">글 번호</th><td align="center"><input type="text" value="${editb.num }" name="num" readonly></td>
		</tr>
		<tr>
			<th width="30%">작성자</th><td align="center"><input type="text" value="${editb.writer.id }" name="writer" readonly></td>
		</tr>
		<tr>
			<th width="30%">제 목</th><td align="center"><input type="text" value="${editb.title }" name="title"></td>
		</tr>	
		<tr>
			<td colspan="2">
			
			<div align="center">
			<img src="/img/${editf.filename }" alt="">			
			</div>
			
			<textarea style="resize: none;" cols="100%" rows="5" name="content">${editb.content }</textarea>
			</td>
		</tr>
	</table>
	<button type="submit">수정</button>  
</form>	
</div>
</body>
</html>