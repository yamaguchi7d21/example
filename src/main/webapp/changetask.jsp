<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Task Manager</title>
<link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
	<h1>Task Manager</h1>
	
	<h2>Tasks</h2>
	<table border="1">
		<tr>
			<th>タスク名</th>
			<th>期日</th>
			<th>重要度</th>
			<th>ステータス</th>
			<th></th>
		</tr>
		<c:forEach var="task" items="${tasks}">
		<form action="${pageContext.request.contextPath}/ChangeTaskServlet" method="post">
		<tr>
			<td><input type="text" name="description" value="${task.description}"/></td>
			<td><input type="date" name="dueDate" value="${task.dueDate}"/></td>
			<td>
				<select name="importance">
					<option value="高" ${task.importance eq '高' ? 'selected' : ''}>高</option>
					<option value="中" ${task.importance eq '中' ? 'selected' : ''}>中</option>
					<option value="低" ${task.importance eq '低' ? 'selected' : ''}>低</option>
				</select>
			</td>
			<td>
				<select name="status">
					<option value="未着手" ${task.status eq '未着手' ? 'selected' : ''}>未着手</option>
					<option value="進行中" ${task.status eq '進行中' ? 'selected' : ''}>進行中</option>
					<option value="完了" ${task.status eq '完了' ? 'selected' : ''}>完了</option>
				</select>
			</td>
			<input type="hidden" name="id" value="${task.id}"/>
			<td>
			<input type="submit" name="change" value="変 更" id="btn"/>
			<input type="submit" name="delete" value="削 除" id="btn"/>
			</td>
		</tr>
		</form>
		</c:forEach>
	</table>
	<button type="button" onclick="location.href='/final/task'"><span>戻</span><span>る</span></button>
</body>
</html>