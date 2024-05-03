<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>스터디 목록</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap" rel="stylesheet">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Batang&family=Nanum+Myeongjo&family=Noto+Serif+KR&family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap" rel="stylesheet">
    <style>
    /** 스타일 버튼 */
.styled-button {
    background-color: #f4f4f4;
    border: 2px solid #000;
    color: #333;
    padding: 10px 20px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 15px;
    margin: 1px 2px;
    cursor: pointer;
    border-radius: 10px;
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
  margin-top: 100px;
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
    .study-list {
        display: flex;
        flex-wrap: wrap;
        justify-content: flex-start;
    }
    .study-card {
        width: calc(20% - 24px);
       	height: 170px;
        border: 2px solid black;
        border-radius: 10px;
        padding: 10px;
        margin: 10px;
        box-sizing: border-box;
        font-size: 25px;
        font-family: 'font-han', serif;
        color: black;
    }

    .study-card img {
        width: 100%;
        height: 70%;
        border-radius: 5px;
        object-fit: fill;
    }

    /* 텍스트 중앙 정렬 */
    .study-card p {
        text-align: center;
        margin: 5px 0; /* 위아래 마진 조정 */
    }
    
    .study-card a {
    text-decoration: none; /* 하이퍼링크의 밑줄 제거 */
	}
	
	input {
	  font-size: 20px;
	  color: #222222;
	  width: 300px;
	  border: 2px solid;
	  border-radius: 10px;
	  padding-bottom: 10px;
	  padding-left: 10px;
	  position: relative;
	  background: none;
	  z-index: 5; 
	  font-align: center;
}
    
	</style>
	<script>
        window.onload = function() {
            const searchInput = document.getElementById('searchInput');
            const memberItems = document.querySelectorAll('.study-card');

            searchInput.addEventListener('input', function() {
                const searchKeyword = this.value.trim().toLowerCase();
                
                memberItems.forEach(function(item) {
                    const memberName = item.querySelector('.study-name').textContent.trim().toLowerCase();
                    
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
        <h1 class="noto-serif-kr-regular">Study List</h1>
        
        <!-- 검색창과 제출 버튼 -->
        <form id="searchForm" class="search-form" action="/schedular/ms/study/search" method="get">
    <div class="input-container">
        <input type="text" id="searchInput" class="font-han" placeholder="이름으로 검색">
        <span></span>
    </div>
    
</form>


        <!-- 회원 목록 카드 형태 -->
        <div id="studyList" class="study-list">
            <!-- 여기에 검색 결과가 들어갈 것입니다 -->
            <c:forEach var="study" items="${studyList}">
                <div class="study-card">
                    <a href="/schedular/study/${study.studyId}">
                        <img src="<c:url value='${study.studyProfile}' />" alt="Profile Image">
                        <p class="study-name">${study.studyName}</p>
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>