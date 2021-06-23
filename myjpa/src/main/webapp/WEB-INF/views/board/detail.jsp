<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 상세보기</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
$(document).ready(function(){
	$("#del").click(function(){
		location.href="/board/del/${b.num }";
	});
});
</script>
</head>
<style>
	h2 { text-align: center;}
	h3 { text-align: center;}
  table {  
  width: 600px;
  height: 20px;
  }
  textarea { 
  width: 100%;}
 	#outter {
		display: block;
		width: 30%;
		margin: auto;
	}
</style>
<body>

<h2>게시판</h2>
<br><br><br>

	<table align="center" border="1">
		
		<tr>
			<th width="30%">글 번호</th><td align="center">${b.num }</td>
		</tr>
		<tr>
			<th>작성자</th><td align="center">${b.writer.name }</td>
		</tr>
		<tr>
			<th>제 목</th><td align="center">${b.title }</td>
		</tr>
		<tr>
			<th>작성일</th><td align="center"><fmt:formatDate value="${b.w_date }" pattern="yyyy.MM.dd hh:mm:ss" /></td>
		</tr>	
		<tr>
			<td colspan="2">
			
			<div align="center">
			<img src="/testimgs/${f.filename }" alt="">			
			</div>
			
			<div style="width: 600px; height: 100px; margin: 10px; display: inline-block;">
			${b.content }
			</div>
			
			</td>
		</tr>
		<tr>
		<th>
	<c:if test="${sessionScope.id == b.writer.id }">
		<button type="button" onclick="location.href='/board/edit/${b.num }'">수정</button> <!-- 할것 -->
		<input type="submit" value="삭제" id="del"> 
	</c:if>
	<input type="button" value="글 목록" style="float: right;" onclick="location.href='/board/list';"> 
	</th>
	</tr>
	</table>
	<h3>댓글목록</h3>
	<table align="center" border="1">
 	 <tr>
 	 <th width="20%">작성자</th>
 	 <th>내용</th>
 	 </tr>
 	 <c:forEach var="r" items="${reps }">
   	 <tr>
     
      <td>${r.writer.id }</td>
      <td>${r.content }</td> 
     </tr>
     </c:forEach>
	</table>
<a href="/board/write?parent=${b.num }">댓글작성</a><br/>

</body>
</html>