<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>
		<i><c:out	value="${user.getName()}" />  <c:out	value="${user.getSurname()}" /></i>
	</h1>
	 DELETE ROOM: 
	<form action="Controller" method="post">
	
		<input type="hidden" name="command" value="delet_room" /> <br />
		Room number:<br />
		<input type="text" name="room number" value="" /><br />  
		
		<i><c:out	value="${errorDeleteRoomMessage}" /></i>
		<i><c:out	value="${progressMessage3}" /></i>
		<br />
		<input type="submit" name="delete room" value="delete room" /> 
		
	</form>
	
	
</body>
</html>