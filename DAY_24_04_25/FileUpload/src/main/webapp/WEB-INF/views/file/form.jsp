<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File Upload</title>
</head>
<body>
<h1>파일 업로드</h1>
<form action="#" method="post" enctype="multipart/form-data">
카테고리: <input type="text" name="category" required><p> <!-- required : null값을 허용하지 않음 -->
파일명: <input type="file" name="file" required><p>
<input type="submit" value=" 저장 ">
<input type="reset" value=" 취소 ">
</form>
</body>
</html>