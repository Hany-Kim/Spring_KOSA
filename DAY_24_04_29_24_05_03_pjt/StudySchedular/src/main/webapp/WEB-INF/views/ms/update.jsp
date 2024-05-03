<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Update</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: "Gowun Batang", serif;
            background-image: url('/images/front/background4.jpg');
            background-size: cover;
            background-repeat: no-repeat;
            width: 100%;
            height: 100vh;
        }
        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            max-width: 800px;
            margin: 50px auto;
            padding: 20px;
            background-color: white;
            border-radius: 15px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.2);
  			border: 1px solid #000;
        }
        h1 {
            font-family: "Playfair Display", serif;
            text-align: center;
            margin-top: 20px;
            font-size: 50px;
        }
        table {
            width: 100%;
            margin-top: 20px;
        }
        th, td {
            padding: 10px;
        }
        input[type="text"], input[type="file"] {
            width: 100%;
            padding: 8px;
            margin: 5px 0;
            box-sizing: border-box;
        }
        input[type="submit"], input[type="button"] {
            background-color: #f4f4f4;
            border: none;
            padding: 10px;
            margin-top: 10px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        input[type="submit"]:hover, input[type="button"]:hover {
            background-color: #ccc;
        }
        
        .profile-image{
        	width: 150px;
        	height: 150px;
        	border-radius:5px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>User Update</h1>
    <form action="./update" method="post" enctype="multipart/form-data">
        <input type="hidden" name="userId" value="${member.userId}">
        <input type="hidden" name="password" value="${member.password}">
        <table>
            <tr>
                <th>회원 이름</th>
                <td><input type="text" name="name" value="${member.name}" required></td>
            </tr>
            <tr>
                <th>핸드폰 번호</th>
                <td><input type="text" name="phone" value="${member.phone}" required></td>
            </tr>
            <tr>
                <td colspan="2">
                    <img id="profile-preview" src="<c:url value='${member.profile}'/>" alt="Profile Image Preview" class="profile-image"/>
                    <input type="file" name="file" id="input-file" style="display:none" accept=".jpg, .jpeg, .png" onchange="displayImage(this)"/>
                    <label for="input-file" style="display: inline-block; cursor: pointer;">Profile Image</label>
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>
                    <input type="submit" value="수정">
                    <input type="button" value="취소" onclick="window.history.back();">
                </td>
            </tr>
        </table>
    </form>
</div>
<script>
    function displayImage(input) {
        var profilePreview = document.getElementById('profile-preview');
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                profilePreview.src = e.target.result;
            };
            reader.readAsDataURL(input.files[0]);
        } else {
            profilePreview.src = "<c:url value='${member.profile}'/>";
        }
    }
</script>
</body>
</html>
