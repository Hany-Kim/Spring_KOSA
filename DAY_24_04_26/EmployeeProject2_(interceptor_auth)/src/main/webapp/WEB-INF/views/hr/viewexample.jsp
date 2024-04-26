<%@page import="com.example.myapp.hr.model.Emp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>사원정보</h1>
<!-- 아래 출력방법은 같은 출력이 가능한 3가지 방법이다. 편한것을 사용하면된다. -->
<!-- Expression Language (EL) -->
<h1>${emp}</h1> <!-- emp.toString()으로 자동으로 문자열 타입으로 변경됨 -->
<p>아이디 : ${emp.employeeId}</p>
<p>이름 : ${emp.firstName}</p>
<p>성 : ${emp.lastName}</p>
<p>이메일 : ${emp.email}</p>
<p>전화번호 : ${emp.phoneNumber}</p>
<p>입사일 : ${emp.hireDate}</p>
<p>직무아이디 : ${emp.jobId}</p>
<p>급여 : ${emp.salary}</p>
<p>보너스율 : ${emp.commissionPct}</p>
<p>매니저 아이디 : ${emp.managerId}</p>
<p>부서 아이디 : ${emp.departmentId}</p>
<p>
<% // scriptlet 태그 안에서 자바 코드 사용하면 됨
Emp emp = (Emp)request.getAttribute("emp");
out.println("<h1>" + emp + "</h1>"); // toString() 생략되어있음
out.println("<p>아이디 : " + emp.getEmployeeId() + "</p>");
out.println("<p>이름 : " + emp.getFirstName() + "</p>");
out.println("<p>성 : " + emp.getLastName() + "</p>");
out.println("<p>이메일 : " + emp.getEmail() + "</p>");
out.println("<p>전화번호 : " + emp.getPhoneNumber() + "</p>");
out.println("<p>입사일 : " + emp.getHireDate() + "</p>");
out.println("<p>직무아이디 : " + emp.getJobId() + "</p>");
out.println("<p>급여 : " + emp.getSalary() + "</p>");
out.println("<p>보너스율  : " + emp.getCommissionPct() + "</p>");
out.println("<p>매니저 아이디 : " + emp.getManagerId() + "</p>");
out.println("<p>부서 아이디 : " + emp.getDepartmentId() + "</p>");
%>
</p>
<p> <!-- expression -->
<h1><%= emp.toString() %></h1> <!-- out.println()과 비슷함, toString()생략 가능 -->
<p>아이디 : <%= emp.getEmployeeId() %></p>
<p>이름 : <%= emp.getFirstName() %></p>
<p>성 : <%= emp.getLastName() %></p>
<p>이메일 : <%= emp.getEmail() %></p>
<p>전화번호 : <%= emp.getPhoneNumber() %></p>
<p>입사일 : <%= emp.getHireDate() %></p>
<p>직무아이디 : <%= emp.getJobId() %></p>
<p>급여 : <%= emp.getSalary() %></p>
<p>보너스율 : <%= emp.getCommissionPct() %></p>
<p>매니저 아이디 : <%= emp.getManagerId() %></p>
<p>부서 아이디 : <%= emp.getDepartmentId() %></p>
</p>
</body>
</html>