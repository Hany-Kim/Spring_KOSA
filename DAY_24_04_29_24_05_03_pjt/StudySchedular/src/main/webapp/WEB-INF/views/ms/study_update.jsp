<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Example</title>
<style>
	.tagCategory {
		text-align: center;
		valign: middle;
	}
	
	.study-schedule {
		text-align: center;
		valign: middle;
	}
	.study_td {
		align: center;
	}
	.study_img {
		width: 200px;
		height: 200px;
	}
</style>
</head>
<body>
	<h1>스터디 정보 수정 페이지</h1>


	<form id='' action="/ms/study_update" method="post">
		<input type="hidden" name="reserveId" 
		value="${past.reserveId}" readonly />
		<table border="3">
			<tr>
				<td class="tagCategory">
					스터디 장
				</td>
				<td>
					<input type="text" name="captainName" value="${past.captainName}" readonly />
				</td>
				<td class="tagCategory">
					스터디 P.H
				</td>
				<td>
					<input type="text" name="captainPhone" value="${past.captainPhone}" readonly />
				</td>
			</tr>
			<tr>
				<td class="study-schedule" rowspan="2">
					Study<p>START!
				</td>
				<td>
					<input
	                    type="hidden"
	                    id="start_time"
	                    name="start"
	                    value="${past.start}"
	                    min="2024-03-11T00:00"
	                    max="2024-08-31T00:00" />
					&ensp;${past.startDate}
				</td>
				<td class="study-schedule" rowspan="2">
					Study<p>END.
				</td>
				<td>
					<input
	                    type="hidden"
	                    id="end_time"
	                    name="end"
	                    value="${past.end}"
	                    min="2024-03-11T00:00"
	                    max="2024-08-31T00:00" />
					&ensp;${past.endDate}
				</td>
			</tr>
			<tr>
				<td>
					&ensp;${past.startTime}
				</td>
				<td>
					&ensp;${past.endTime}
				</td>
			</tr>
			<tr>
				<td class="study_td" colspan="2">
					<select name="studyId">
	                    <c:forEach var="study" items="${studyList}">
	                        <option value="${study.studyId}">${study.studyName}</option>
	                    </c:forEach>
	                </select>
	                <input type="hidden" name="studyId" value="${past.studyId}" readonly />
                	<label for="study-id">스터디 주제</label>
	                <span class="underLine"></span>
				</td>
				<td colspan="2">
					<input type="text" name="studyContent" value="${past.studyContent}" />
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="수정">
					<input type="reset" value="취소">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>