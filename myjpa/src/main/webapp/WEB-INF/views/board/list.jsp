<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
<h1 align="center">게시판</h1>
<button type="button" onclick="location.href='/member/result'">메인</button>
<c:set var="address">onclick="location.href='/board/write?parent=0'"</c:set>
<c:if test="${sessionScope.id eq null }">
<c:set var="address" >onclick="location.href='/member/login'"</c:set>
</c:if>
<button type="button" ${address }>글 작성</button>
<table align="center" border="1" width="80%">
	  <tr height="10" align="center">
	  	<th>글번호</th><th>작성자</th><th>제목</th><th>작성일</th>
	  </tr>
  <c:forEach var="b" items="${list }">
    <tr align="center">
      <td width="10%">${b.num }</td>
      <td width="20%">${b.writer.id }</td>
      <td width="40%"><a href="/board/read/${b.num }">${b.title }</a></td>
      <td width="10%"><fmt:formatDate value="${b.w_date }" pattern="yyyy.MM.dd" /></td>
    </tr>
  </c:forEach>
</table><br/>
</body>
</html>