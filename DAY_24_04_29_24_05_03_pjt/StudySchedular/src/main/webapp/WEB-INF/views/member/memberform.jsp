<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap" rel="stylesheet">

<style>
/** 스타일 버튼 */
.joinForm {
  display: flex;
  flex-direction: column;
  position: absolute;
  width: 300px;
  height: 500px; /*수정된 부분*/
  padding: 40px;
  background-color: #FFFFFF;
  text-align: center;
  top: 50%;
  left: 50%;
  transform: translate(-50%,-50%);
  border-radius: 15px;
  border: 1px solid #000;
  margin-top: 15px; /* 수정된 부분 */
  align-items: center;
  justify-content: space-between;
}

.button-container{
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 80%;
  transform: translate(0px, 580px);
  position: absolute;
}

.user-details {
  flex: 50%; /* 중앙 영역 */
  /* 필요한 스타일링 추가 */
}

.signup-button {
    /*transform: translate(-45%, 750%); */
    width: 100px;
}

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

.styled-button2 {
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

.styled-button2:hover {
    background-color: #ccc;
    color: #333;
}

.styled-button2:active {
    background-color: #aaa;
    border-color: #777;
    color: #222;
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
  font-weight: normal;
  font-style: normal;
  text-align: center;
  margin-top: 100px;
  font-size: 50px;
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

.textForm label {
  margin-top: 70px; /* 레이블을 아래로 이동시키는 부분 */
}

.input-container {
  position: relative;
  width: 100%;
  margin-bottom: 20px;
}

.input-container input {
  font-size: 16px;
  color: #222222;
  width: calc(100% - 20px); /* 수정된 부분 */
  border: none;
  padding-top: 30px;
  padding-bottom: 20px;
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
  font-size: 20px;
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
  margin-top: 20px; /* 수정된 부분 */
}

.sign-up-button {
  position: relative;
  bottom: -20px; /* 수정된 부분 */
}

.profile-upload {
  flex: 30%; /* 상단 30% */
  /* 필요한 스타일링 추가 */
}

#profile-preview {
  display: block;
  width: 100px;
  height: 100px;
  margin: 0 auto;   /* 이미지를 가운데 정렬 */
  object-fit: cover; /* 이미지가 div를 벗어나지 않고 비율을 유지하면서 채워짐 */
}

</style>
</head>
<body>
  <form action="#" method="POST" class="joinForm" enctype="multipart/form-data">
  <!-- 프로필 이미지 업로드 섹션 -->
     <div class="profile-upload">
       <img id="profile-preview" src="#" alt="Profile Image Preview" style="display:none; width: 150px; height: 150px;"/>
          <script>
                  function displayImage(input) {
                      if (input.files && input.files[0]) {
                          var reader = new FileReader();
                          
                          reader.onload = function (e) {
                              // 미리보기 이미지를 표시하고 src 속성 설정
                              document.getElementById('profile-preview').style.display = 'block';
                              document.getElementById('profile-preview').src = e.target.result;
                          };
                          
                          // 파일 읽기
                          reader.readAsDataURL(input.files[0]);
                      }
                  }
         </script>
                
            <input type="file" name="file" id="input-file" class="font-han" style="display:none" accept=".jpg, .jpeg, .png" onchange="displayImage(this)" />
            <label for="input-file" class="font-han styled-button sign-up-button profile-upload" id="profile-label">Profile Image</label>
     </div>
   
     <!-- 이름, 전화번호, 비밀번호 입력 섹션 -->
     <div class="user-details">
       <div class="input-container mt-3">
           <input name="name" type="text" class="font-han" required>
           <label class="font-han">Name</label>
           <span></span>
         </div>
       
         <!-- <div class="input-container mt-3">
           <input name="phone" type="text" class="font-han" required>
           <label class="font-han">Phone Number</label>
           <span></span>
         </div> -->
         
         <div class="input-container mt-3">
    		<input name="phone" type="text" class="font-han" pattern="010[0-9]{8}" required onchange="validatePhoneNumber(this)">
    		<label class="font-han">Phone Number</label>
    		<span></span>
		</div>
		<script type="text/javascript">
		function validatePhoneNumber(input) {
		    // 입력된 전화번호 형식이 유효한지 확인
		    var pattern = /^010[0-9]{8}$/;
		    if (!pattern.test(input.value)) {
		        alert("올바른 형식의 전화번호를 입력하세요 (010XXXXXXXX).");
		        input.value = ""; // 입력란 비우기
		        input.focus(); // 포커스 다시 설정
		    }
		}
		</script>
       
         <div class="input-container">
           <input name="password" type="password" class="font-han" required>
           <label class="font-han">Password</label>
           <span></span>
         </div>
     </div>
     
     <!-- Sign up 및 Cancel 버튼 섹션 -->
	<div class="button-container">
	  <!-- Sign up 버튼 -->
	  <button class="styled-button2 signup-button font-han" type="submit">Sign up</button>
	  
	  <!-- Cancel 버튼 -->
	  <input type="button" onclick="location.href='/';" class="styled-button2 cancel-button font-han" value="Cancel">
	</div>
     
   </form>
   <h1 class="font-han2"><b>Sign up</b></h1>
  </body>
</html>