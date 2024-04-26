<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사원정보 입력</title>
<link rel="stylesheet" href="../css/default.css"/>
<style>
td {
	padding: 10px;
}
</style>
</head>
<body>
<h1>사원정보 입력</h1>
<p>사원정보를 입력하세요</p>
<form action="#" method="post">
<table border="1">
<tr>
	<th>사원번호</th>
	<td class="empid"><input type="number" name="employeeId"></td>
</tr>
<tr>
	<th>이름, 성</th>
	<td><input type="text" name="firstName" placeholder="이름">
	<input type="text" name="lastName" placeholder="성"> 
	</td>
</tr>
<tr>
	<th>이메일</th>
	<td><input type="text" name="email"></td>
</tr>
<tr>
	<th>전화번호</th>
	<td><input type="text" name="phoneNumber"></td>
</tr>
<tr>
	<th>입사일</th>
	<td><input type="date" name="hireDate" max="2024-04-05"></td>
</tr>
<tr>
	<th>직무</th>
	<!-- <td><input type="text" name="jobId"></td> -->
	<td>
		<select name="jobId">
			<c:forEach var="job" items="${jobList}">
				<option value="${job.jobId}">${job.jobTitle}</option>
			</c:forEach>
		</select>
	</td>
</tr>
<tr>
	<th>급여</th>
	<td><input type="number" name="salary" min="0" max="20000" step="0.01"></td>
</tr>
<tr>
	<th>보너스율</th>
	<td><input type="number" name="commissionPct" 
	min="0.0" max="0.99" step="0.01"></td>
</tr>
<tr>
	<th>매니저</th>
	<!-- <td><input type="number" name="managerId"></td> -->
	<td>
		<select name="managerId">
			<c:forEach var="manager" items="${managerList}">
				<option value="${manager.managerId}">${manager.firstName}</option>
			</c:forEach>
		</select>
	</td>
</tr>
<tr>
	<th>부서</th>
	<!-- <td><input type="number" name="departmentId"></td> -->
	<td>
		<select name="departmentId">
			<c:forEach var="dept" items="${deptList}">
				<option value="${dept.departmentId}">${dept.departmentName}</option>
			</c:forEach>
		</select>
</tr>
<tr>
	<td colspan="2">
	<input type="submit" value=" 저장 ">
	<input type="reset" value=" 취소 ">
	</td>
</tr>
</table>
</form>
</body>
</html>