<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
$(document).ready(function(){
	var id_flag = false;
	$("#idCheck").click(function(){
		var id = $("#id").val();
		$.post("/member/id-check/"+id)//ajax post요청
		.done(function(data){
			r = eval("("+data+")")
			var msg = "";
			if(r.flag){
				msg = "사용가능한 아이디";
				id_flag = true;
			}else{
				msg = "중복된 아이디";
				$("#id").val("");
				id_flag = false;
			}
			$("#res").html(msg);
		});
	});
	$("#join").click(function(){
		if(id_flag){
			$("form").submit()
		}else{
			alert("아이디 중복체크 하시오");
		}
	});
});
</script>
</head>
<body>
<h3>회원가입 폼</h3>
<form action="/member/join" method="post">
<table border="1">
<tr>
  <th>ID</th>
  <td>
    <input type="text" name="id" id="id">
    <input type="button" value="중복체크" id="idCheck">
    <span id="res"></span>
  </td>
</tr>
<tr>
  <th>PWD</th>
  <td><input type="password" name="pwd"></td>
</tr>
<tr>
  <th>NAME</th>
  <td><input type="text" name="name"></td>
</tr>
<tr>
  <th>EMAIL</th>
  <td><input type="text" name="email"></td>
</tr>
<tr><td colspan="2"><input type="button" value="join" id="join"></td></tr>
</table>
</form>
</body>
</html>