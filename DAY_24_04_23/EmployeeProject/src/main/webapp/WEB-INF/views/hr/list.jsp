<%@page import="com.example.myapp.hr.model.Emp"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employees</title>
</head>
<body>
<h1>사원 목록</h1>
${message} <!-- 리다이렉트 메시지 출력 -->
<a href="./insert">신규 사원정보 입력</a>
<table border="1">
<tr>
	<th>EMPLOYEE_ID</th>
	<th>FIRST_NAME</th>
	<th>LAST_NAME</th>
	<th>EMAIL</th>
	<th>PHONE_NUMBER</th>
	<th>HIRE_DATE</th>
	<th>JOB_ID</th>
	<th>SALARY</th>
	<th>COMMISSION_PCT</th>
	<th>MANAGER_ID</th>
	<th>DEPARTMENT_ID</th>
</tr>
<c:forEach var="emp" items="${empList}"> <!-- controller의 model을 담은 변수 -->
<tr>
	<td><a href="./${emp.employeeId}">${emp.employeeId}</a></td>
	<td>${emp.firstName}</td>
	<td>${emp.lastName}</td>
	<td>${emp.email}</td>
	<td>${emp.phoneNumber}</td>
	<td>${emp.hireDate}</td>
	<td>${emp.jobId}</td>
	<td>${emp.salary}</td>
	<td><c:if test="${emp.commissionPct != 0.0}">${emp.commissionPct}</c:if></td>
	<td><c:if test="${emp.managerId != 0}">${emp.managerId}</c:if></td>
	<td><c:if test="${emp.departmentId != 0}">${emp.departmentId}</c:if></td>
</tr>
</c:forEach>
</table>
<p>
<hr>
<%
List<Emp> empList = (List<Emp>)request.getAttribute("empList");
	for(Emp emp : empList){
%>
	<%=emp.getEmployeeId() %> <%=emp.getFirstName()%><p>
	<%-- ${emp.employeeId} ${emp.firstName} ${emp.lastName}<p> --%>

<%
	}
%>
${empList[0].employeeId}<p>
${empList[1].employeeId}<p>
</body>
</html>