<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/member/edit" method="post">
<table border="1">
<tr>
  <th>ID</th>
  <td>
    <input type="text" name="id" value="${editInfo.id }" readonly>
  </td>
</tr>
<tr>
  <th>PWD</th>
  <td><input type="text" name="pwd" value="${editInfo.pwd }" ></td>
</tr>
<tr>
  <th>NAME</th>
  <td><input type="text" name="name" value="${editInfo.name }" ></td>
</tr>
<tr>
  <th>EMAIL</th>
  <td><input type="text" name="email" value="${editInfo.email }" ></td>
</tr>
<tr>
  <td colspan="2"><input type="submit" value="정보수정"></td>
</tr>
</table>
</form>
</body>
</html>