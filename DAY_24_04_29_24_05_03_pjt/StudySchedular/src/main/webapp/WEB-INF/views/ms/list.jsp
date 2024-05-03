<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 목록</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Batang&display=swap" rel="stylesheet">
    
    <style>
    
    .gowun-batang-regular {
  font-family: "Gowun Batang", serif;
  font-weight: 400;
  font-style: normal;
}
    
    .input-container span {
  display: block;
  position: absolute;
  bottom: 0;
  left: 0;
  background-color: #666;
  width: 0;
  height: 2px;
  border-radius: 15px;
  transition: width 0.3s ease;
  border: 1px solid #000;
}

.input-container input:focus ~ span,
.input-container input:valid ~ span {
  width: 100%;
}
    
    .font-han {
  font-family: "Playfair Display", serif;
  font-optical-sizing: auto;
  font-weight: normal;
  font-style: normal;
}

.font-han2 {
  font-family: "Playfair Display", serif;
  font-optical-sizing: auto;
  font-weight: bold;
  font-style: normal;
  text-align: center;
  margin-top: 50px;
  font-size: 50px;
}
    .container {
       aling: center;
        max-width: 800px;
        margin: 0 auto;
        padding: 20px;
    }
    .search-form {
        text-align: center;
        margin-bottom: 20px;
    }
    .member-list {
        display: flex;
        flex-wrap: wrap;
        justify-content: flex-start;
    }
    .member-card {
        width: calc(20% - 24px);
          height: 170px;
        border-radius: 5px;
        padding: 10px;
        margin: 10px;
        box-sizing: border-box;
        border: 1px solid black;
        background-color: white;
    }

    .member-card img {
        width: 100%;
        height: 70%;
        border-radius: 5px;
        object-fit: fill;
        border: 1px solid #000;
    }

    /* 텍스트 중앙 정렬 */
    .member-card p {
        text-align: center;
        margin: 2px 0; /* 위아래 마진 조정 */
        font-color: black;
        
    }
    
    .member-card a {
    text-decoration: none; /* 하이퍼링크의 밑줄 제거 */
    margin: 2px 0;
    
   }
   
   body {
  background-image: url('/images/front/background4.jpg');
  background-size: cover; 
  background-repeat: no-repeat;
}

#searchInput {
    width: 300px; /* 또는 원하는 너비 값 */
    padding: 10px; /* 선택적으로 내부 여백 조정 */
    font-size: 16px; /* 선택적으로 글꼴 크기 조정 */
    border-radius: 10px;
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
   <script>
        window.onload = function() {
            const searchInput = document.getElementById('searchInput');
            const memberItems = document.querySelectorAll('.member-card');

            searchInput.addEventListener('input', function() {
                const searchKeyword = this.value.trim().toLowerCase();
                
                memberItems.forEach(function(item) {
                    const memberName = item.querySelector('.member-name').textContent.trim().toLowerCase();
                    
                    if (memberName.includes(searchKeyword)) {
                        item.style.display = 'flex';
                    } else {
                        item.style.display = 'none';
                    }
                });
            });
        };
    </script>

</head>
<body>
    <div class="container">
        <!-- <h1><a href="/schedular/ms/list">회원 목록</a></h1> -->
        <h1 class="font-han2"><a href="/ms/list" style="text-decoration: none; color: black;">User List</a></h1>
        <script src="/js/event.js" defer></script>
        
        <c:if test="${not empty sessionScope.errorMessage}">
            <div id="error-message">${sessionScope.errorMessage}</div>
        </c:if>
        
        <!-- 검색창과 제출 버튼 -->
        <form id="searchForm" class="search-form " action="/schedular/ms/search" method="get">
            <input type="text" id="searchInput" placeholder="이름으로 검색">
            <button type="button" onclick="window.location.href='/';">첫 페이지로</button>
        </form>

        <!-- 회원 목록 카드 형태 -->
        <div id="memberList" class="member-list">
            <!-- 여기에 검색 결과가 들어갈 것입니다 -->
            <c:forEach var="member" items="${memberList}">
                <div class="member-card">
                    <%-- <a href="/schedular/ms/${member.userId}"> --%>
                    <a href="/ms/${member.userId}">
                        <img src="<c:url value='${member.profile}' />" alt="Profile Image">
                        <p class="member-name" style= "color:black;">${member.name}</p>
                        <p> ${member.phone}</p>
                        <input type="hidden" value="${member.userId}"/>
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>