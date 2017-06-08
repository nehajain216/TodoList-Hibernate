<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New To-do List</title>
</head>
<body>
	<h3>New To-do List</h3>
	<form action="NewToDoListServlet" method="POST" >

			Title :	
			<input type="text" name="title"/><br>		
		
			Description :
			<input type="text" name="description"/>
			
			<input type="submit" value="submit">			
		
		</form>
</body>
</html>