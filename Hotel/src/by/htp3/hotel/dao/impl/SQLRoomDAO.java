package by.htp3.hotel.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

import by.htp3.hotel.bean.User;
import by.htp3.hotel.dao.RoomDAO;
import by.htp3.hotel.dao.exception.DAOException;
import by.htp3.hotel.dao.impl.pool.ConnectionPool;
import by.htp3.hotel.dao.impl.pool.ConnectionPoolException;

public class SQLRoomDAO implements RoomDAO {

	@Override
	public void addNewRoom(String type, String price_a_day) throws DAOException {
		Connection con = null;
		PreparedStatement st = null;
		
		try {
			//Class.forName("org.gjt.mm.mysql.Driver");
			con = ConnectionPool.getInstance().takeConnection();

			st = (PreparedStatement) con.prepareStatement("INSERT INTO rooms (type, price_a_day) VALUES (?,?)");
	
			st.setString(1, type);
			st.setString(2, price_a_day);
			 st.executeUpdate();
			
		
			
		}  catch (SQLException e) {
			throw new DAOException("Logination sql error..", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("pool exception", e);
		} finally {
			

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
	public void deleteRoom(int roomID) throws DAOException {
		
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			con = ConnectionPool.getInstance().takeConnection();
			
			st = (PreparedStatement)con.prepareStatement("delete from rooms where room_ID = ?");
			st.setInt(1, roomID);
			
			st.execute();

			
			
		} catch (SQLException e) {
			throw new DAOException("Error in room deletting DAOException!", e);

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
				// loggin error
			}

		}
		
	
	}
}
