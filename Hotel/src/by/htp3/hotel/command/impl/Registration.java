package by.htp3.hotel.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp3.hotel.bean.User;
import by.htp3.hotel.command.Command;
import by.htp3.hotel.service.ServiceFactory;
import by.htp3.hotel.service.UserService;
import by.htp3.hotel.service.exception.ServiceException;
import by.htp3.hotel.service.exception.ServiceRegistrException;

public class Registration implements Command{

	private static final String NAME = "name"; 
	private static final String SURNAME = "surname"; 
	private static final String LOGIN = "login"; 
	private static final String PASSWORD = "password";
	private static final String REPASSWORD = "repassword";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String name  = request.getParameter(NAME);
		String surname  = request.getParameter(SURNAME);
		String login  = request.getParameter(LOGIN);
		String password  = request.getParameter(PASSWORD);
		String repassword  = request.getParameter(REPASSWORD);
		
		UserService userService = ServiceFactory.getInstance().getUserService();

		request.getSession(true).setAttribute("prev_page", "/WEB-INF/jsp/registration.jsp");
		
		
		try {
				
			User user = userService.registration(name, surname, login, password, repassword);
			request.setAttribute("user", user);
			
			
			request.getRequestDispatcher("/WEB-INF/jsp/user.jsp").forward(request, response);;
			
		} catch (ServiceRegistrException e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(request, response);
			
		} catch (ServiceException e) {
			request.getRequestDispatcher("eee.jsp").forward(request, response);
			
		}
		
	}

}
