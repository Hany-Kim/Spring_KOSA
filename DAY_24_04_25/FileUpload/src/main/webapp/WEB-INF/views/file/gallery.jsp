<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Example</title>
<script type="text/javascript">
window.onload = function() {
	var deleteButtons = document.querySelectorAll(".delete");
	for(var i=0; i<deleteButtons.length; i++){
		deleteButtons[i].onclick = function() {
			if(confirm("파일을 삭제하겠습니까?")) {
				return true;
			} else {
				return false;
			}
		}
	}
}
</script>
</head>
<body>
<c:url var="actionURL" value="/file/category/update" />
<form action="${actionURL}" method="post">
<table border="1">
<tr>
	<th>ID</th>
	<td>카테고리</td>
	<td>이미지</td>
	<td>크기</td>
	<td>유형</td>
	<td>날짜</td>
	<td>삭제</td>
</tr>
<c:forEach var="file" items="${fileList}">
<tr>
	<td><input type="checkbox" name="fileIds" value="${file.fileId}">${file.fileId}</td>
	<td>${file.categoryName}</td>
	<td>
		<c:set var="len" value="${fn:length(file.fileName)}"/>
		<c:set var="filetype" value="${fn:toUpperCase(fn:substring(file.fileName, len-4, len))}"/>
		<c:if test="${(filetype eq '.JPG') or (filetype eq 'JPEG') or (filetype eq '.PNG') or (filetype eq '.GIF')}">
			<c:url var="imageName" value="/file/${file.fileId}"/>
			<img src="${imageName}" width="100">
		</c:if>
	</td>
	<td>
		<fmt:formatNumber value="${file.fileSize/1024}" pattern="#,###" />KB
	</td>
	<td>${file.fileContentType}</td>
	<td>${file.fileUploadDate}</td>
	<td>
		<c:url var="deletelink" value="/file/delete/${file.fileId}" />
		<a href="${deletelink}" class="delete">삭제</a>
	</td>
</tr>
</c:forEach>
</table>
선택한 파일을 <select name="categoryName">
	<option value="/">/</option>
	<option value="image">이미지</option>
	<option value="data">데이터</option>
</select>카테고리로 <input type="submit" value=" 이동 "><p>
<a href='<c:url value="/file/new"/>'>업로드</a>
<a href='<c:url value="/file"/>'>처음으로</a>
</form>
</body>
</html>