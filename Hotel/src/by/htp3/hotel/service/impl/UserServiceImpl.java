package by.htp3.hotel.service.impl;

import by.htp3.hotel.bean.User;
import by.htp3.hotel.dao.DAOFactory;
import by.htp3.hotel.dao.UserDAO;
import by.htp3.hotel.dao.exception.DAOException;
import by.htp3.hotel.service.UserService;
import by.htp3.hotel.service.exception.ServiceAuthException;
import by.htp3.hotel.service.exception.ServiceException;
import by.htp3.hotel.service.exception.ServiceRegistrException;

public class UserServiceImpl implements UserService  {

	@Override
	public User authorisation(String login, String password) throws ServiceException, ServiceAuthException {
		
		if(!Validation.validate(login, password)){
			throw new ServiceAuthException("Wrong parameters!");
		}
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO dao = daoFactory.getUserDAO();
		
		User user;
		try {
			user = dao.authorisation(login, password);
			if(user==null){
				throw new ServiceAuthException("Wrong login or password!");
			}
			
			
		} catch (DAOException e) {
			throw new ServiceException("Error in source!", e);
		
		}
		
		return user;
	}
	static class Validation {
		static boolean validate(String login, String password){
			if(login==null || login.isEmpty()){
				return false;
			}
			if(password == null||password.isEmpty()){
				return false;
			}
			return true;
		}
		static void validate(String name, String surname, String login, String password, String repassword) throws ServiceRegistrException {
			if (login == null || login.isEmpty()) {
				throw new ServiceRegistrException("Field \"login\" could't be empty. "
												   + "Please re-enter it");
			}

			if (password == null || password.isEmpty()) {
				throw new ServiceRegistrException("Field \"password\" could't be empty ");
			}
			
			
			if(!password.equals(repassword)){
				throw new ServiceRegistrException("Sorry, your passwords don't match. "
													+ "Please re-enter it");				
			}
		}
	}
	
	@Override
	public User registration(String name, String surname, String login, String password, String repassword) throws ServiceException, ServiceRegistrException {

		Validation.validate(name, surname, login, password, repassword);
		
		if (name.isEmpty()){
			name = "user-name";
		}
		if (surname.isEmpty()){
			surname = "user-surname";
		}
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO dao = daoFactory.getUserDAO();
		
		User user;
		try {

			user = dao.registration(name, surname, login, password);
			
			//user = dao.authorization(login, password);
			
			if (user == null) {
				throw new ServiceRegistrException("Registration isn't completed");
			}

		} catch (DAOException e) {
			throw new ServiceException("Error in source", e);
		}

		return user;
		
		
		// TODO Auto-generated method stub
		
	}
	
}
