<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<H1>
 Please enter some information:
</H1>
 	<form action="Controller" method="post">
		<input type="hidden" name="command" value="registration" /> Name: <br />
		<input type="text" name="name" value="" /><br /> Surname: <br />
		<input type="text" name="surname" value="" /><br /> Login: <br />
		<input type="text" name="login" value="" /><br /> Password: <br />
		<input type="password" name="password" value="" /> <br /> Re-enter password: <br />
		<input type="password" name="repassword" value="" /> <br /> <i>  
		
		
		<c:out	value="${errorMessage}" />

		</i> <br /> <input type="submit" name="sign in" value="sign in" />

	</form>
 
 
 
</body>
</html>