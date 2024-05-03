<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Study Scheduler</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Batang&family=Nanum+Myeongjo&family=Noto+Serif+KR&family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap" rel="stylesheet">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        body {
            background-image: url('/images/front/background4.jpg'); /* <<--이미지를 바꿔가며 사용하세요 */
            background-size: cover; /* 이미지를 화면에 꽉 채우도록 설정합니다 */
            background-repeat: no-repeat; /* 이미지 반복을 방지합니다 */
        }
        h1 {
            color: white;
            text-shadow: 3px 3px 0 #000,  
                 1px -1px 0 #000,
                -1px  1px 0 #000,
                 1px  1px 0 #000;
            font-size: 70px;
        }
        .big-box {
            width: 400px; /* 원하는 크기로 조절하세요 */
            height: 200px; /* 너비와 동일한 값을 사용합니다 */
            border: 1px solid black;
            border-radius: 20px;
            padding: 10px;
            text-align: center;
            margin: auto; /* 가운데 정렬을 위해 margin을 auto로 설정합니다 */
            margin-bottom: 30px;
            background-color: rgba(255, 255, 255, 0.8); /* 불투명한 흰색 배경 */
            display: flex; /* 텍스트를 가운데 정렬하기 위해 Flexbox를 사용합니다 */
            justify-content: center; /* 수평 가운데 정렬 */
            align-items: center; /* 수직 가운데 정렬 */
        }
        .big-box a {
            display: block;
        }
        .page-container {
            display: flex;
            flex-direction: column;
            justify-content: center;
            min-height: 100vh;
        }
        .big-box h2 {
            color: black; /* 텍스트 색상을 검정색으로 지정합니다 */
            font-size: 20px;
        }
        #font-han {
            font-family: "Playfair Display", serif;
            font-optical-sizing: auto;
            font-weight: <weight>;
            font-style: normal;
        }
        .noto-serif-kr-regular {
            font-family: "Noto Serif KR", serif;
            font-weight: 400;
            font-style: normal;
            font-size: 10px;
        }
        .logout-dot {
            font-size: 70px;
            cursor: pointer;
            color: white;
            text-shadow: 2px 2px 4px #000;
        }
    </style>
</head>
<body>
    <div class="page-container">
        <div class="container">
            <div style="padding: 50px;">
                <h1 class="text-center" id="font-han">Study Scheduler<span class="logout-dot" onclick="logoutUser()">.</span></h1>
            </div>
            <script>
		        function logoutUser() {
		            // Redirect to the logout page
		            window.location.href = '/ms/logout';
		        }
		    </script>
            <div class="row justify-content-center">
                <div class="col">
                    <div class="big-box">
                        <a href="/member/memberform">
                            <svg xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="black" class="bi bi-person-plus" viewBox="0 0 16 16">
                                <path d="M6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6m2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0m4 8c0 1-1 1-1 1H1s-1 0-1-1 1-4 6-4 6 3 6 4m-1-.004c-.001-.246-.154-.986-.832-1.664C9.516 10.68 8.289 10 6 10s-3.516.68-4.168 1.332c-.678.678-.83 1.418-.832 1.664z"/>
                                <path fill-rule="evenodd" d="M13.5 5a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5"/>
                            </svg>
                            <h2 class="noto-serif-kr-regular">신규 회원 등록</h2>
                        </a>
                    </div>
                </div>
                <div class="col">
                    <div class="big-box">
                        <a href="/reserve/reserveform">
                            <svg xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="black" class="bi bi-calendar-plus" viewBox="0 0 16 16">
                                <path d="M8 7a.5.5 0 0 1 .5.5V9H10a.5.5 0 0 1 0 1H8.5v1.5a.5.5 0 0 1-1 0V10H6a.5.5 0 0 1 0-1h1.5V7.5A.5.5 0 0 1 8 7"/>
                                <path d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5M1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4z"/>
                            </svg>
                            <h2 class="noto-serif-kr-regular">예약하기</h2>
                        </a>
                    </div>
                </div>
                <div class="col">
                    <div class="big-box">
                        <a href="/ms/list">
                            <svg xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="black" class="bi bi-search" viewBox="0 0 16 16">
                                <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                            </svg>
                            <h2 class="noto-serif-kr-regular">회원 정보 검색</h2>
                        </a>
                    </div>
                </div>
                
            </div>
        </div>
    </div>
</body>
</html>