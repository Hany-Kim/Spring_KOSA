<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>test</title>
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
        grid-template-columns: 1fr 3fr;
        grid-gap: 20px;
        margin: 50px auto;
        padding: 20px;
        background-color: white;
        border-radius: 15px;
        box-shadow: 0 2px 4px rgba(0,0,0,0.2);
  		border: 1px solid #000;
  		max-width:1200px;
        
    }
	.box {
	    padding: 20px; /* 내부 패딩 조정 */
	    background: #fff; /* 배경색 변경 */
	    border-radius: 10px; /* 모서리 둥글게 */
	    box-shadow: 0 2px 6px rgba(0,0,0,0.1); /* 그림자 효과 추가 */
	}
    table {
        width: 100%;
        table-layout: fixed; /* 고정된 테이블 레이아웃 사용 */
    	border-collapse: collapse; /* 셀 경계를 합침 */
    	}
    th, td {
	    border-bottom: 1px solid #ddd; /* 셀 간 경계선 강조 */
    white-space: nowrap; /* 내용을 한 줄로 표시 */
    text-overflow: ellipsis; /* 내용이 넘칠 때 말줄임표 사용 */
		}
	
	th {
    	background-color: #f4f4f4; /* 헤더 배경색 변경 */
		}
    h1 {
        font-family: "Playfair Display", serif;
        text-align: center;
        margin-top: 0px;
        font-size: 50px;
    }
    .table-header {
    background-color: #f9f9f9; /* 헤더 배경색 변경 */
    color: #333; /* 헤더 텍스트 색상 */
    font-weight: bold;
    border-bottom: 2px solid #ccc; /* 하단 테두리 강조 */
	}
    
    .phone {
    color: #0056b3; /* 전화번호 색상 변경 */
    font-weight: bold; /* 글씨 두껍게 */
	}
    .button {
    background-color: #f4f4f4; /* 배경 색상 */
    border: 1px solid #000; /* 테두리 */
    color: black; /* 글자 색상 */
    padding: 8px 16px; /* 버튼 내부의 여백 */
    border-radius: 4px; /* 모서리 둥글기 */
    font-family: "Playfair Display", serif; /* 폰트 */
    text-align: center; /* 텍스트 중앙 정렬 */
    cursor: pointer; /* 마우스 오버 시 커서 변경 */
    display: inline-block; /* 인라인 블록 요소로 표시 */
    text-decoration: none; /* 밑줄 없애기 */
    transition: background-color 0.3s; /* 배경색 변경 애니메이션 */
}
    .button:hover {
        background-color: #ccc;
    }
    
    .profile-image {
    width: 200px;
    height: 200px;
    border-radius: 5px;
	}

        .future_tabe td {
        	align: center;
        }
        img {
    max-width: 100%; /* 이미지가 셀 너비를 초과하지 않도록 함 */
    height: auto; /* 이미지 높이를 자동으로 조절하여 비율 유지 */
}
    </style>
</head>
<body>
	<h1>개인정보</h1>
	<div class="container">
		<div class="box">
			<table>
                <tr>
                    <td colspan="2">
                        <img src="<c:url value='${member.profile}' />" alt="Profile Image" class="profile-image">
                    </td>
                </tr>
				<tr>
					<th>이름</th>
					<td>${member.name}</td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td>${member.phone}</td>
				</tr>
			</table>
			<table>
				<tr>
            		<td>
				        <!-- 수정하기 버튼 -->
				        <form action="/ms/update" method="get" style="display: inline;">
				            <input type="hidden" name="userId" value="${member.userId}">
				            <button type="submit" class="button">수정하기</button>
				        </form>
				        <!-- 처음으로 버튼 -->
				        <button type="button" onclick="window.location.href='/'" class="button">처음으로</button>
				        <!-- 로그아웃 버튼 -->
                        <form action="/ms/logout" method="get" style="display: inline;">
                            <button type="submit" class="button">로그아웃</button>
                        </form>
				    </td>
				</tr>
			</table>

		</div>


		<div class="box">
	      <div class="box">
				<h2>${user.name}회원님의 예약내역</h2>
	      <table>
	        <tr>
	          <th style="border:1px solid #bcbcbc">스터디장</th>
	          <th style="border:1px solid #bcbcbc">스터디 시작</th>
	          <th style="border:1px solid #bcbcbc">스터디 종료</th>
	          <th style="border:1px solid #bcbcbc">스터디 주제</th>
	          <th style="border:1px solid #bcbcbc">스터디장<p>P.H</th>
	        </tr>
	          <c:forEach var="future" items="${fshList}">
	            <tr>
	              <td>${future.captainName}</td>
	              <td>${future.startDate}<p>${future.startTime}</td>
	              <td>${future.endDate}<p>${future.endTime}</td>
	              <td>
	              	<img src="${future.studyTitle}" style="width:40%"/>
	           	  </td>
	              <td><span class="phone">${future.captainPhone}</span>
	              <td>
	              	<a href="reserve_detail?reserveid=${future.reserveId}" class="button">자세히</a>
	              	<form action="./delete" method="post">
	              		<input type="hidden" name="userId" value="${future.userId}">
	              		<input type="hidden" name="reserveId" value="${future.reserveId}">
						<input type="submit" value="삭제" class="button">
	              	</form>
	              </td>
	            </tr>
	          </c:forEach>
	        </table> 
	      </div>
			<div class="box">
				<h2>${user.name}회원님의 공부내역</h2>
				<table>
        			<tr>
						<th style="border:1px solid #bcbcbc">스터디장</th>
						<th style="border:1px solid #bcbcbc">스터디 시작</th>
						<th style="border:1px solid #bcbcbc">스터디 종료</th>
						<th style="border:1px solid #bcbcbc">스터디 주제</th>
						<th style="border:1px solid #bcbcbc">스터디장<p>P.H</th>
					</tr>
					<c:forEach var="past" items="${pshList}">
						<tr>
							<td>${past.captainName}</td>
							<td>${past.startDate}<p>${past.startTime}</td>
							<td>${past.endDate}<p>${past.endTime}</td>
							<td>
								<img src="${past.studyTitle}" style="width:40%"/>
							</td>
							<td><span class="phone">${past.captainPhone}</span></td>
							<td>
								<a href="study_detail?reserveid=${past.reserveId}" class="button">자세히</a>
				              	<form action="./delete" method="post">
				              		<input type="hidden" name="userId" value="${past.userId}">
				              		<input type="hidden" name="reserveId" value="${past.reserveId}">
									<input type="submit" value="삭제" class="button">
				              	</form>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
<script>
	// 번호 뒷 4자리만 보이게
	window.onload = function() {
	    var phone = document.querySelectorAll('.phone'); // 모든 phoneNumber 클래스를 가진 요소 선택
	    // 각 요소에 대해 루프 실행
	    phone.forEach(function(element) {
	        var phone = element.innerText;
	        
	        
	            var maskedNumber =phone.substring(phoneNumber.length - 4);
	            element.innerText = maskedNumber; // 변형된 형식 설정
	        
	    });
	}
</script>
</html>