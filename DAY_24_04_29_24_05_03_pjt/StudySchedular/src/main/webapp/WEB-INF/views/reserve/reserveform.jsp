<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약 정보 입력</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Playfair+Display:ital,wght@0,400..900;1,400..900|Gowun+Batang&display=swap">
<style>
body {
	font-family: 'Gowun Batang', serif;
	background-image: url('/images/front/background4.jpg');
	background-size: cover;
	background-repeat: no-repeat;
	color: black;
	height: 100vh;
	display: flex;
	align-items: center;
	justify-content: center;
}

.container {
    display: flex;
    width: 100%;
    max-width: 1200px;
    margin-right: 200px;
    background-color: white;
    border-radius: 15px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    overflow: hidden;
}

.calendar, .form-container {
	flex: 1;
	padding: 20px;
}

.form-container {
	border-left: 2px solid #ddd;
}

.styled-button {
	background-color: #f4f4f4;
	border: 1px solid #000;
	color: black;
	padding: 10px 20px;
	font-size: 16px;
	border-radius: 4px;
	cursor: pointer;
	transition: background-color 0.3s;
	text-decoration: none;
	margin: 0 5px;
}

.styled-button:hover {
	background-color: #ccc;
}

.input-box {
	margin-bottom: 20px;
}

input[type="datetime-local"], textarea, select {
	width: 100%;
	padding: 10px;
	margin-top: 5px;
	box-sizing: border-box;
	border: 1px solid #ccc;
	border-radius: 4px;
}

textarea {
	height: 100px;
}

label {
	display: block;
	margin-bottom: 5px;
	color: #666;
}

.buttons {
	text-align: center;
	padding-top: 20px;
}

.calAndForm, .timetable, reserveformClass {
	flex: 1;
}
.font-han2 {
  font-family: "Playfair Display", serif;
  font-optical-sizing: auto;
  font-weight: normal;
  font-style: normal;
  text-align: center;
  margin-top: 100px;
  font-size: 50px;
}

#my-sort {
	text-align:center;
}
</style>
<link rel="stylesheet" href="/css/calendar.css">
<link rel="stylesheet" href="/css/timetable.css">

</head>
<body>
	<div id="my-sort">
		<div>
			<b><h1 class="font-han2">Reservation</h1></b><br>
		</div>
		<div class="container">
			<div class="calendar">
				<div class="calAndForm">
					<!-- 달력 -->
					<div class="calendar">
						<div class="header">
							<!-- <button class="nav-btn go-prev" onclick="prevMonth()">&lt;</button>
			                <button class="year-month" onclick="goToday()"></button>
			                <button class="nav-btn go-next" onclick="nextMonth()">&gt;</button> -->
							<button class="nav-btn go-prev" />
							<button class="year-month" />
							<button class="nav-btn go-next" />
						</div>
						<div class="main">
							<div class="days">
								<div class="day">Sun</div>
								<div class="day">Mon</div>
								<div class="day">Tue</div>
								<div class="day">Wed</div>
								<div class="day">Thu</div>
								<div class="day">Fri</div>
								<div class="day">Sat</div>
							</div>
							<div class="dates"></div>
						</div>
					</div>
					<script src="/js/calendar.js"></script>
					<!-- <script src="/js/todo.js"></script>
			    <script src="/js/select.js"></script> -->
				</div>
			</div>
			<div class="form-container">
				<form action="#" method="post">
					<div class="input-box">
						<label for="start_time">시작 시간:</label> <input
							type="datetime-local" id="start_time" name="start"
							min="2024-03-11T00:00" max="2024-08-31T00:00">
					</div>
					<div class="input-box">
						<label for="end_time">종료 시간:</label> <input type="datetime-local"
							id="end_time" name="end" min="2024-03-11T00:00"
							max="2024-08-31T00:00">
					</div>
					<div class="input-box">
						<label for="study-id">스터디 주제:</label> <select name="studyId">
							<c:forEach var="study" items="${studyList}">
								<option value="${study.studyId}">${study.studyName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="input-box">
						<label for="study-content">스터디 내용:</label>
						<textarea id="study-content" name="content" required></textarea>
					</div>
					<div class="buttons">
						<button class="styled-button" type="submit">예약하기</button>
						<button class="styled-button" type="button"
							onclick="window.location.href='/';">처음으로</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>