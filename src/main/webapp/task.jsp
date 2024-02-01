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
	<section>
		<h2>Tasks</h2>
		 <table border="1">
		 	<tr>
		 		<th>タスク名</th>
		 		<th>期日</th>
		 		<th>重要度</th>
		 		<th>ステータス</th>
		 	</tr>
		 	<c:forEach var="task" items="${tasks}">
		 	<tr>
		 		<td>${task.description}</td>
		 		<td>${task.dueDate}</td>
		 		<td>${task.importance}</td>
		 		<td>${task.status}</td>
		 	</tr>
		 	</c:forEach>
		 </table>
		 <button type="button" onclick="location.href='/final/ChangeTaskServlet'"><span>変</span><span>更</span></button>
	</section>
	<section>
		<h2>Add New Task</h2>
		<form action="${pageContext.request.contextPath}/task" method="post" onsubmit="return validateTaskForm()">
			<label for="description">タスク名:</label>
			<input type="text" id="description" name="description">
			<label for="dueDate">期日:</label>
			<input type="date" id="dueDate" name="dueDate">
			<label for="importance">重要度:</label>
			<select name="importance" id="importance">
				<option value="高">高</option>
				<option value="中">中</option>
				<option value="低">低</option>
			</select>
			<button type="submit">タスク追加</button>
		</form>
	</section>
	<script>
		function validateTaskForm() {
			var description = document.getElementById('description').value;
			var dueDate = document.getElementById('dueDate').value;

			if (description.trim() === "" || dueDate.trim() === '') {
				alert('タスク名と期日は必須項目です。');
				return false;
				}
			return true;
			}
	</script>
</body>
</html>