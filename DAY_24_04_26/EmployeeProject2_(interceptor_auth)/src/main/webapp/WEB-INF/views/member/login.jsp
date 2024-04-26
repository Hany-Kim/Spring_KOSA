<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${empty sessionScope.email}"> <!-- session scope에 이메일이 비어있는지 -->
	<h1>로그인</h1>
	<form action="#" method="post">
	아이디: <input type="text" name="userid" required><p>
	비밀번호: <input type="password" name="password" required><p>
	<input type="submit" value=" 로그인 ">
	</form>
</c:if>
<c:if test="${!empty sessionScope.email}">
	${sessionScope.email}님 로그인 중...<p>
	<a href="logout">로그아웃</a>
</c:if>
</body>
</html>