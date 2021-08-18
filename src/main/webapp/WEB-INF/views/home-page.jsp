<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Task management System</title>
</head>
<body>

<div class="container">

<div class="taskForm">
<form action="add" method="post">
<label for="name">task name</label>
<input type="text" name="name" />
<br/>
<label for="name">status</label>
<input type="text" name="status" />
<br/>

<input type="submit" value="Add task" />
</form>


</div>

<div class=".allTasks">
<ul>
<c:forEach var="task" items="${tasks}">
<li>${task.name}</li>
</c:forEach>
</ul>
</div>
</div>
 
</body>
</html>