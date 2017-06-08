<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Welcome to Home Page</title>
</head>
<body>
	<h3>To-do List</h3>
	<c:set value="prasad" var="myname"></c:set>
	${myname}
	<table border="1" width="600">
		<tr>
			<td>Title</td>
			<td>Description</td>
			<td colspan="2"><a href="NewToDoList.jsp">New To-Do-List</a></td>
		</tr>

		<c:if test="${toDoList.isEmpty()}">
			<tr>
				<td colspan="4">No Todo found</td>
			</tr>
		</c:if>
		<c:forEach var="todo" items="${toDoList}">
			<tr>
				<td><c:out value="${todo.title}" /></td>
				<td><c:out value="${todo.description}" /></td>
				<td><a href="UpdateToDoServlet?id=${todo.id}">Edit</a></td>
				<td><a href="DeleteToDoServlet?id=${todo.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>