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
      <fmt:setLocale value="${sessionScope.locale}" /><!-- locale = ru -->
   <fmt:setBundle basename="resurses.locale" var="loc" /><!-- locale_ru  -->
   
   <fmt:message bundle="${loc}" key="locale.change_language.ru" var="ru" />
   <fmt:message bundle="${loc}" key="locale.change_language.en" var="en" />
   <fmt:message bundle="${loc}" key="locale.index.login" var="login" />
    <fmt:message bundle="${loc}" key="locale.index.password" var="Password" />  
     <fmt:message bundle="${loc}" key="locale.index.sing_in" var="Sing in" /> 
     
      <jsp:useBean id="errorMessage" class="java.lang.String" scope="request"/>
      
      <a href="Controller?command=change_Language&Language=ru">${ru}</a> 
      <a href="Controller?command=change_Language&Language=en">${en}</a>
      
      <br/> 
      
      
      <form action="Controller" method="post">
        <input type="hidden" name="command" value="logination"/> ${login}:
        <br/>
         <input type="text" name="login" value=""><br/>${Password}:
         <br/>
          <input type="password" name="password" value="">
          
          <br/> <i> <c:out value="${errorMessage}"/></i>
          
         <br/>  <input type="submit" name="sing in" value="sing in">
      </form>
      <br/>
       <form action="Controller" method="post">
		<input type="hidden" name="command" value="registration_form" /> 
		 <input type="submit" name="registration" value="Registration" />
	</form>
</body>
</html>