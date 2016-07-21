package by.htp3.hotel.dao.impl.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;





public class ConnectionPool {
	
	
	
	private static final ConnectionPool instance = new ConnectionPool(); 
	
	private static final String DRIVER = "db.driver";
	private static final String JDBS_URL = "db.jdbc_url";
	private static final String DB_LOGIN = "db.login";
	private static final String DB_PASSWORD = "db.password";
	private static final String CONNECTION_COUNT = "db.connection_count";
	
	private static final String DB_PROPERTIES_FILE = "resurses.db";
	private static final int MINIMAL_CONNECTION_COUNT= 5;
	
	private BlockingQueue<Connection> freeConnections;
	private BlockingQueue<Connection> givenConnections;
	
	
	
	public ConnectionPool()  {	
	
	}
	
	public static ConnectionPool getInstance(){
		return instance;
	}
	
	public void init() throws ConnectionPoolException{
		ResourceBundle resourceBundle =  ResourceBundle.getBundle(DB_PROPERTIES_FILE);
		
		String driver =resourceBundle.getString(DRIVER);
		String jdbcUrl = resourceBundle.getString(JDBS_URL);
		String login = resourceBundle.getString(DB_LOGIN);
		String password = resourceBundle.getString(DB_PASSWORD);
		int connectionCount = 0;
		try{
			connectionCount = Integer.parseInt(resourceBundle.getString(CONNECTION_COUNT));
		}catch(NumberFormatException e){
			
			connectionCount = MINIMAL_CONNECTION_COUNT;
		}
		freeConnections = new ArrayBlockingQueue<>(connectionCount);
		givenConnections = new ArrayBlockingQueue<>(connectionCount);
		
		try {
			Class.forName(driver);
			
			Connection connection  = DriverManager.getConnection(jdbcUrl, login, password);
		
				freeConnections.put(connection);
			
			
		} catch (ClassNotFoundException e) {
			
			throw new ConnectionPoolException("db.properties error", e);
		} catch (SQLException e) {
			throw new ConnectionPoolException("connection error", e);
		} catch (InterruptedException e){
			throw new ConnectionPoolException("pool error", e);
		}
		
	}
	public Connection takeConnection() throws ConnectionPoolException{
		Connection connection;
		
		try {
			connection = freeConnections.take();
			givenConnections.put(connection);
		} catch (InterruptedException e) {
			throw new ConnectionPoolException("take connection error", e);
		}
		return connection;
		
	}
	public void returnConnection(Connection connection) throws ConnectionPoolException{
		if(connection == null){
			throw new ConnectionPoolException("tyty");
			
		}
		try {
			connection.setAutoCommit(true);
			givenConnections.remove(connection);
			freeConnections.put(connection);
		} catch (SQLException e) {
			throw new ConnectionPoolException("zzz", e);
		} catch (InterruptedException e) {
			throw new ConnectionPoolException("xxx", e);
		}
	}
	
	public void destroyPool(){
		//int closeConnectionCounter = 0;
		
		for(int i =0;i<freeConnections.size();i++){
			Connection con = freeConnections.poll();
			try {
				con.close();
			} catch (SQLException e) {
				
				
			}
		}
		for(int i =0;i<givenConnections.size();i++){
			Connection con = givenConnections.poll();
			try {
				con.close();
			} catch (SQLException e) {
				
				
			}
		}
		
		
	}
	public static void main(String[] args) throws ConnectionPoolException{
		ConnectionPool pool = new ConnectionPool();
	} 
	
}
