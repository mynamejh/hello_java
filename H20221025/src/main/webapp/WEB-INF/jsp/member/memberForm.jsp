<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<style>
form label{width : 80px; float:left;}
</style>
</head>
<body>
	<h3>회원 가입</h3>
	<form action ="./signUp.do" method="post" >
		<label for="id">ID :</label> <input type="text" name = "id" id="id"><br>
		<label for="passwd">비밀번호 :</label> <input type ="password" name="passwd" id="passwd"><br> 
		<label for="name">이름 :</label><input type="text" name = "name"><br>
		<label for ="email">이메일 :</label> <input type="email" name = "email"><br>
		<input type="submit" value="회원가입">
		<input type="reset" value="초기화"><br>
	</form>
</body>
</html>