<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디 정보 수정</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap" rel="stylesheet">
<style>
    body {
        font-family: 'Gowun Batang', serif;
        background-image: url('/images/front/background4.jpg');
        background-size: cover;
        background-repeat: no-repeat;
        color: black;
        width: 100%;
        height: 100vh;
    }
    .container {
        display: grid;
        grid-template-columns: 1fr;
        margin: 50px auto;
        padding: 20px;
        background-color: white;
        border-radius: 15px;
        box-shadow: 0 2px 4px rgba(0,0,0,0.2);
        border: 1px solid #000;
        max-width:850px;
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
        padding: 12px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }
    th {
        background-color: #f4f4f4;
    }
    input[type="text"], input[type="datetime-local"], select, input[type="submit"], input[type="button"] {
        width: 100%;
        padding: 8px;
        margin-top: 5px;
        box-sizing: border-box;
        border: 1px solid #ccc;
        border-radius: 4px;
    }
    .button {
        background-color: #f4f4f4;
        border: 1px solid #000;
        color: black;
        padding: 8px 16px;
        font-size: 16px;
        border-radius: 4px;
        text-align: center;
        cursor: pointer;
        text-decoration: none;
        transition: background-color 0.3s;
    }
    .button:hover {
        background-color: #ccc;
    }
</style>
</head>
<body>
<h1>스터디 정보 수정</h1>
<div class="container">
    <div class="box">
        <form action="/ms/reserve_update" method="post">
            <table>
            	<input type="hidden" name="reserveId" value="${future.reserveId}" readonly />
                <tr>
                    <th>스터디 장</th>
                    <td><input type="text" name="captainName" value="${future.captainName}" readonly></td>
                    <th>스터디 P.H</th>
                    <td><input type="text" name="captainPhone" value="${future.captainPhone}" readonly></td>
                </tr>
                <tr>
                    <th>스터디 시작</th>
                    <td><input type="datetime-local" name="start" value="${future.start}"></td>
                    <th>스터디 종료</th>
                    <td><input type="datetime-local" name="end" value="${future.end}"></td>
                </tr>
                <tr>
                    <th>스터디 주제</th>
                    <td colspan="3">
                        <select name="studyId">
                            <c:forEach var="study" items="${studyList}">
                                <option value="${study.studyId}">${study.studyName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td colspan="3"><input type="text" name="studyContent" value="${future.studyContent}"></td>
                </tr>
                <tr>
                    <td colspan="4">
                        <input type="submit" value="수정" class="button">
                        <input type="button" class="button" value="취소" onclick="window.history.back();" >
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
