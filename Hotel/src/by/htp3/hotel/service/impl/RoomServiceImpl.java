package by.htp3.hotel.service.impl;

import by.htp3.hotel.bean.User;
import by.htp3.hotel.dao.DAOFactory;
import by.htp3.hotel.dao.RoomDAO;
import by.htp3.hotel.dao.UserDAO;
import by.htp3.hotel.dao.exception.DAOException;
import by.htp3.hotel.service.RoomService;
import by.htp3.hotel.service.exception.ServiceAuthException;
import by.htp3.hotel.service.exception.ServiceException;
import by.htp3.hotel.service.impl.UserServiceImpl.Validation;

public class RoomServiceImpl implements RoomService {

	@Override
	public void addNewRoom(String type, String price_a_day) throws ServiceException {
		if(type == null || type.isEmpty()){
			throw new ServiceException("add new room exception");
		}
		if(price_a_day == null || price_a_day.isEmpty()){
			throw new ServiceException("add new room exception");
		}
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		RoomDAO dao = daoFactory.getRoomDAO();
		
		
		try {
			dao.addNewRoom(type, price_a_day);
			
		} catch (DAOException e) {
			throw new ServiceException("Error in source!", e);
		
		}
		
	}
	
	static class Validation {

		static boolean validate(String roomIDstring, String typeName) {
			if (roomIDstring == null || roomIDstring.isEmpty()) {
				return false;
			}

			if (typeName == null || typeName.isEmpty()) {
				return false;
			}

			return true; // stub
		}

		static boolean validate(String roomIDstring) {
			if (roomIDstring == null || roomIDstring.isEmpty()) {
				return false;
			}

			return true; // stub
		}
	}
	@Override
	public void deleteRoom(String roomIDstring) throws ServiceException {

		if (!Validation.validate(roomIDstring)) {
			throw new ServiceException("Room number couldn't be empty");
		}

		int roomID = Integer.parseInt(roomIDstring);

		DAOFactory daoFactory = DAOFactory.getInstance();
		RoomDAO dao = daoFactory.getRoomDAO();

		try {
			dao.deleteRoom(roomID);

		} catch (DAOException e) {
			throw new ServiceException("Error in source", e);
		}

	}
}
