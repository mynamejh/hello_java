<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String id = (String) session.getAttribute("id");
		String auth = (String) session.getAttribute("auth");
	%>
	<% if(id != null) { %>
		<p>아이디 : <%=id %></p>
	<%} else {%>
		<p>손님</p>
	<%} %>
	<h2>♣Member의 첫페이지♣</h2>
	<% if(auth.equals("admin")) { %>
		<a href ="./memberAddForm.do">회원정보 생성 페이지.</a><br>
	<%} %>
	<a href ="./memberModifyForm.do">회원정보 수정 페이지</a><br>
	<a href = "./memberRemoveForm.do">회원정보 삭제 페이지</a><br>
	<a href = "./memberSearchForm.do">회원정보 상세 페이지</a><br>
	<a href = "./memberList.do">회원목록 보기 페이지</a><br>
	<% if(id == null) {%>
		<a href = "./loginForm.do">로그인화면</a><br>
	<%} else { %>
		<a href = "./logOut.do">로그아웃화면</a><br>
	<% } %>
</body>
</html>