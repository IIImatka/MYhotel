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
   
   <jsp:useBean id="errorMessage" class="java.lang.String" scope="request" />
                        
      <a href="Controller?command=change_Language&Language=ru">${ru}</a> 
      <a href="Controller?command=change_Language&Language=en">${en}</a>
<br/>
     Hello USER!!!!
      <form action="Controller" method="post">
        <input type="hidden" name="command" value="show_free_rooms"/> ${login}:
          <input type="submit" name="sing in" value="show free rooms">
      </form>
      <br/>
      
      <form action="Controller" method="post">
        <input type="hidden" name="command" value="add_new_room_form"/> ${login}:
          <input type="submit" name="sing in" value="add new room">
      </form>
     
     <form action="Controller" method="post">
        <input type="hidden" name="command" value="delet_room"/> ${login}:
          <input type="submit" name="sing in" value="delet room">
      </form>
     
     
     
     <h1>
     <%
     by.htp3.hotel.bean.User user = (by.htp3.hotel.bean.User) request.getAttribute("user");
     
     out.println(user.getName());
     
     
     %>
     <h2>
     <%
     out.println(user.getSurname());
     %>
     </h2>
     
     
     
     </h1>
</body>
</html>