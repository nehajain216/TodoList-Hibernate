<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html >
<html>
<head>
<title>Update To-do List</title>
</head>
<body>
<h3>Update To-do List</h3>
	<form action="UpdateToDoServlet" method="POST" >
			Title :	
			<input type="text" name="title" value="${EditTodoObject.title}"/><br>		
		
			Description :
			<input type="text" name="description" value="${EditTodoObject.description}"/>
			
			<input type="hidden" name="id" value="${EditTodoObject.id}"/>
			
			<input type="submit" value="submit">			
		
		</form>				
			
</body>
</html>