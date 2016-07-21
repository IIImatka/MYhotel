package by.htp3.hotel.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp3.hotel.command.Command;
import by.htp3.hotel.service.RoomService;
import by.htp3.hotel.service.ServiceFactory;
import by.htp3.hotel.service.exception.ServiceDeleteRoomException;
import by.htp3.hotel.service.exception.ServiceException;

public class DeletRoom implements Command {

	private static final String ROOMID = "room number"; 
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
String roomID  = request.getParameter(ROOMID);
		
		RoomService roomService = ServiceFactory.getInstance().getRoomService();
		
		request.getSession(true).setAttribute("prev_page", "/WEB-INF/jsp/adminUser.jsp");
		
		
		
		try {
			
			roomService.deleteRoom(roomID);
			
			request.setAttribute("progressMessage3", "Room was deleted");
		
			request.getRequestDispatcher("/WEB-INF/jsp/adminUser.jsp").forward(request, response);
			
			
		} catch (ServiceDeleteRoomException e) {
			request.setAttribute("errorDeleteRoomMessage", "Error during deleting");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		} catch (ServiceException e) {
			request.setAttribute("errorDeleteRoomMessage", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/jsp/adminUser.jsp").forward(request, response);
			
		}
		
		
	}
	
}
