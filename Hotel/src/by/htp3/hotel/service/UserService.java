package by.htp3.hotel.service;

import by.htp3.hotel.bean.User;
import by.htp3.hotel.service.exception.ServiceException;

public interface UserService {
	User authorisation(String login, String password) throws ServiceException;
	
	User registration(String name, String surname, String login, String password, String repassword) throws ServiceException;
}
