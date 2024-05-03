<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디 정보 상세페이지</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Gowun+Batang&display=swap" rel="stylesheet">
<style>
    body {
        font-family: "Gowun Batang", serif;
        background-image: url('/images/front/background4.jpg');
        background-size: cover; 
        background-repeat: no-repeat;
        width: 100%;
        height: 100vh;
        color: black;
    }
    .container {
        display: grid;
        grid-template-columns: 1fr;
        grid-gap: 20px;
        margin: 50px auto;
        padding: 20px;
        background-color: white;
        border-radius: 15px;
        box-shadow: 0 2px 4px rgba(0,0,0,0.2);
        border: 1px solid #000;
        max-width: 600px;
    }
    .box {
        padding: 20px;
        background: #fff;
        border-radius: 10px;
        box-shadow: 0 2px 6px rgba(0,0,0,0.1);
    }
    table {
        width: 100%;
        border-collapse: collapse;
    }
    th, td {
        padding: 10px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }
    th {
        background-color: #f4f4f4;
    }
    .button {
        background-color: #f4f4f4;
        border: 1px solid #000;
        color: black;
        padding: 8px 16px;
        border-radius: 4px;
        text-align: center;
        cursor: pointer;
        text-decoration: none;
        transition: background-color 0.3s;
    }
    .button:hover {
        background-color: #ccc;
    }
    .study_img {
        width: 20%;
        height: auto;
        border-radius: 5px;
    }
</style>
</head>
<body>
    <div class="container">
        <h1>스터디 정보 상세페이지</h1>
        <div class="box">
            <form action="/ms/reserve_update" method="post">
                <input type="hidden" name="reserveId" value="${future.reserveId}" readonly>
                <table>
                    <tr>
                        <th>스터디 장</th>
                        <td>${future.captainName}</td>
                        <th>스터디 P.H</th>
                        <td>${future.captainPhone}</td>
                    </tr>
                    <tr>
                        <th>시작 시간</th>
                        <td>${future.startDate} ${future.startTime}</td>
                        <th>종료 시간</th>
                        <td>${future.endDate} ${future.endTime}</td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <img src="${future.studyTitle}" alt="Study Image" class="study_img">
                        </td>
                    </tr>
                    <tr>
                        <th>스터디 내용</th>
                        <td colspan="3">${future.studyContent}</td>
                    </tr>
                    <tr>
                        <td colspan="4" style="text-align:center;">
                            <a href="reserve_update?reserveid=${future.reserveId}" class="button">내용 수정</a>
                            <a href="/ms/${future.userId}" class="button">상세 페이지로</a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</body>
</html>
