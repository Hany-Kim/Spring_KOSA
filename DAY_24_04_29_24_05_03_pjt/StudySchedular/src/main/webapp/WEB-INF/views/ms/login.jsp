<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap" rel="stylesheet">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Batang&family=Nanum+Myeongjo&family=Noto+Serif+KR&family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap" rel="stylesheet">
<style>
.styled-button {
    background-color: #f4f4f4;
    border: 1px solid #000;
    color: #333;
    padding: 10px 20px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    border-radius: 4px;
    transition: background-color 0.3s;
    font-weight: 500;
}

.styled-button:hover {
    background-color: #ccc;
    color: #333;
}

.styled-button:active {
    background-color: #aaa;
    border-color: #777;
    color: #222;
}

.noto-serif-kr-regular {
  font-family: "Noto Serif KR", serif;
  font-weight: 400;
  font-style: normal;
  font-size: 50px;
  text-align: center;
  margin-top: 200px;
}

.font-han {
  font-family: "Playfair Display", serif;
  font-optical-sizing: auto;
  font-weight: <weight>;
  font-style: normal;
}

body {
  background-image: url('/images/front/background4.jpg');
  background-size: cover; 
  background-repeat: no-repeat;
}

form {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 30px;
}

table {
  width: 80%;
}

.joinForm {
  position: absolute;
  width: 300px;
  height: 200px;
  padding: 30px;
  background-color: #FFFFFF;
  text-align: center;
  top: 50%;
  left: 50%;
  transform: translate(-50%,-50%);
  border-radius: 15px;
  border: 1px solid #000;
}

.textForm {
  border-bottom: 2px solid #adadad;
  margin: 30px;
  padding: 10px 10px;
}

.input-container {
  position: relative;
  width: 300px;
  margin: 20px auto;
}

.input-container input {
  font-size: 16px;
  color: #222222;
  width: 100%;
  border: none;
  padding-bottom: 10px;
  padding-left: 10px;
  background: none;
  outline: none; /* 입력 필드 포커스시 테두리 제거 */
}

.input-container input::placeholder {
  color: #aaaaaa;
}

.input-container label {
  position: absolute;
  color: #aaa;
  left: 10px;
  bottom: 8px;
  font-size: 16px;
  transition: all .2s;
}

.input-container input:focus ~ label,
.input-container input:valid ~ label {
  font-size: 12px;
  bottom: 40px;
  color: #666;
  font-weight: bold;
}

.input-container span {
  display: block;
  position: absolute;
  bottom: 0;
  left: 0;
  background-color: #666;
  width: 0;
  height: 2px;
  border-radius: 2px;
  transition: width 0.3s ease;
}

.input-container input:focus ~ span,
.input-container input:valid ~ span {
  width: 100%;
}

.login-button-container {
  margin-top: 70px; /* 버튼을 원하는 만큼 아래로 내리기 위해 조정 */
}

/* 오류 메세지 스타일 */
#error-message {
  color: white; /* 글씨 색상 */
  background-color: salmon; /* 배경 색상, 분홍색과 빨간색 사이 */
  padding: 10px 20px; /* 내부 여백 */
  margin: 10px 0; /* 바깥 여백 */
  border-radius: 5px; /* 테두리 둥글기 */
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; /* 글꼴 */
  font-size: 0.9em; /* 글씨 크기 */
  text-align: center; /* 텍스트 가운데 정렬 */
  box-shadow: 0 2px 4px rgba(0,0,0,0.2); /* 박스 그림자 */
  opacity: 1; /* 시작 시에는 에러 메시지가 완전히 보임 */
  transition: opacity 1s ease-out; /* opacity 변화가 1초 동안 일어남 */
}

</style>
</head>
<body>
  <h1 class="noto-serif-kr-regular" >로그인</h1>
  
  <div class="joinForm">
    <form action="login" method="post">
      <div class="input-container">
        <input type="text" name="phone" class="font-han" required>
        <span></span>
        <label>Phone Number</label>
      </div>
      <div class="input-container">
        <input type="password" name="password" required>
        <span></span>
        <label>Password</label>
      </div>
      <c:if test="${not empty sessionScope.loginError}">
  		<div id="error-message">${sessionScope.loginError}</div>
  
  		<script>
		    window.onload = function() {
		      // 3초 후 에러 메시지를 서서히 사라지게 함
		      setTimeout(function() {
		        var errorMessage = document.getElementById('error-message');
		        if (errorMessage) {
		          errorMessage.style.opacity = '0';
		        }
		      }, 2000); // 시간은 밀리초 단위로, 3000ms = 3초
		    };
  		</script>
	</c:if>
      <div class="login-button-container">
         <!-- Cancel Button -->
    <input type="button" onclick="history.back();" class="styled-button font-han" value="cancel" style="margin-right: 10px;">

    <!-- Login Button -->
    <input type="submit" class="styled-button font-han" value="log in">

    <!-- Register Button -->
    <input type="button" onclick="location.href='/member/memberform';" class="styled-button font-han" value="new" style="margin-left: 10px;">
      </div>
    </form>
  </div>

</body>
</html>
