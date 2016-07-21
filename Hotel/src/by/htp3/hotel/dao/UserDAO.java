package by.htp3.hotel.dao;

import by.htp3.hotel.bean.User;
import by.htp3.hotel.dao.exception.DAOException;

public interface UserDAO {
	User authorisation(String login, String password)throws DAOException;


User registration(String name, String surname, String login, String password) throws DAOException;

}
