<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>    
</head>
<style>
	img{
		width:200px;
		height:auto;
	}
</style>
<body>
<h3 align="center">test글작성 폼</h3>
<form action="/board/testwrite" method="post" enctype="multipart/form-data" onsubmit="return registerAction()">
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
  		<button type="button" id="btn_upload">파일첨부</button>
		<input multiple="multiple" type="file"  id="input_file" name="files" accept="image/*" style="display:none;"> 
		<!-- 여기서 files는 controller에 @RequestPart MultipartFile files -->
		<div class="data_file_txt" id="data_file_txt" style="margin:40px;">
		<span>첨부 파일</span>
		<br />
		<div id="articlefileChange">
		</div>
	</div>
  	</th>
  </tr>
  <tr> 
  <td><div id="imgView" ></div></td>
  </tr>
  </table>
</form>
  <br>  

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
<script>
//파일이 첨부될 때 filecheck함수 실행
$(document).ready(function(){
	$("#input_file").on("change",fileCheck);
});

$(function(){
	$('#btn_upload').click(function(e){
		e.preventDefault();
		$('#input_file').click();
	});
});

var fileCount = 0;//업로드한 파일 갯수
var totalCount = 10;//최대 업로드 갯수
var fileNum = 0;//파일 고유번호
var content_files = new Array();//첨부파일 배열

function fileCheck(e){
	var files = e.target.files;
	//파일정보 배열담기
	var filesArr = Array.prototype.slice.call(files);
	//파일갯수 확인 및 갯수 제한
	if(fileCount + filesArr.length > totalCount){
		alert('파일은 최대 '+ totalCount + '개 까지 업로드 할 수 있습니다.');
		return;
	} else { 
		fileCount = fileCount + filesArr.length;
	}
	
	//각 파일 배열에 담기
	filesArr.forEach(function(f){
		var reader = new FileReader();
		reader.onload = function(e){
			content_files.push(f);
			$('#articlefileChange').append(
				'<div id="file' + fileNum + '">'
		       	+ '<font style="font-size:12px">' + f.name + '</font>'  
		     	+ '<button type="button"" onclick="fileDelete(\'file' + fileNum + '\')">삭제</button>' 
		       	+ '<div/>'			
			);
			fileNum ++;
		};
		reader.readAsDataURL(f);
	});
	console.log(content_files);
	//초기화
	$("#input_file").val("");
}

//파일 부분 삭제
function fileDelete(fileNum){
	var no = fileNum.replace(/[^0-9]/g, "");
	content_files[no].is_delete = true;
	$('#' + fileNum).remove();
	fileCount --;
	console.log(content_files);
	
}

function registerAction(){
	//is_delete = true 가 아닌것들만 처리
	var form = $("form")[0];
	var formData = new FormData(form);
		for(var x = 0; x < content_files.length;x++){
			if(!content_files[x].is_delete){
				formData.append("article_file", content_files[x]);
			}
		}
	/* $.ajax({
 	      type: "POST",
   	   	  enctype: "multipart/form-data",
   	      url: "/board/testwrite",
       	  data : formData,
       	  processData: false,
   	      contentType: false,
   	      success: function (data){
   	    	  alert("업로드 success");
   	      },
   	      complete: function (data){
   	    	  alert("업로드 complete");
   	      }
	}); */
	
	return false;
}
</script>


</body>
</html>