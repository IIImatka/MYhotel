package by.htp3.hotel.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import by.htp3.hotel.bean.User;
import by.htp3.hotel.dao.UserDAO;
import by.htp3.hotel.dao.exception.DAOException;
import by.htp3.hotel.dao.impl.pool.ConnectionPool;
import by.htp3.hotel.dao.impl.pool.ConnectionPoolException;


public class SQLUserDAO implements UserDAO {

	

	@Override
	public User authorisation(String login, String password) throws DAOException{
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			//Class.forName("org.gjt.mm.mysql.Driver");
			con = ConnectionPool.getInstance().takeConnection();

			st = (PreparedStatement) con.prepareStatement("select * from new_table where login=? and password=?");
	
			st.setString(1, login);
			st.setString(2, password);
			rs = (ResultSet) st.executeQuery();
			
			
			if (!rs.next()) {
				return null;
			}

			User user = new User();
			user.setName(rs.getString("name"));
			user.setSurname(rs.getString("surname"));
				
			return user;
			
			
		}  catch (SQLException e) {
			throw new DAOException("Logination sql error..", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("pool exception", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// logging
				}
			}

			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					// logging
				}
			}
			
		try {
			ConnectionPool.getInstance().returnConnection(con);
		} catch (ConnectionPoolException e) {
			//login
		}	
}
	}
	

	@Override
	public User registration(String name, String surname, String login, String password) throws DAOException {
		
		Connection con2 = null;
		PreparedStatement st2 = null;
		ResultSet rs2 = null;

		try {
			//Class.forName("org.gjt.mm.mysql.Driver"); // регистрация драйвера
			con2 = ConnectionPool.getInstance().takeConnection();
			//con2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1/mydb2", "root", "12345");

st2 = (PreparedStatement)con2.prepareStatement("insert into new_table (name, surname, login, password)" + " values (?, ?, ?, ?)");
			
			st2.setString(1, name);
			st2.setString(2, surname);
			st2.setString(3, login);
			st2.setString(4, password);
			
			st2.execute();
			
			User user = new User();
			user.setName(name);
			user.setSurname(surname);
			
			return user;

		} catch (SQLException e) {
			throw new DAOException("Error in registration!", e);

		} catch (ConnectionPoolException e) {
			throw new DAOException("pool exception", e);
		}  finally {

			if (rs2 != null) {

				try {
					rs2.close();
				} catch (SQLException e) {
					// logging
				}
			}
			if (st2 != null) {

				try {
					st2.close();
				} catch (SQLException e) {
					// logging
				}
			}
			try {
				ConnectionPool.getInstance().returnConnection(con2);
			} catch (ConnectionPoolException e) {
				//loggin error
			}
			/*if (con2 != null) {

				try {
					con2.close();
				} catch (SQLException e) {
					// logging
				}
			}*/

		}

	}
}

