package by.epamtc.utilities.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.epamtc.utilities.dao.CustomConnectionProvider;
import by.epamtc.utilities.dao.UserDao;
import by.epamtc.utilities.dao.exception.DaoException;
import by.epamtc.utilities.dao.source.ConnectionException;
import by.epamtc.utilities.dao.source.ConnectionPool;
import by.epamtc.utilities.entity.AuthData;
import by.epamtc.utilities.entity.RegData;
import by.epamtc.utilities.entity.User;
import by.epamtc.utilities.util.Status;
import by.epamtc.utilities.util.Wrapper;
import org.apache.log4j.Logger;

public class UserDaoImpl implements UserDao {
	private final ConnectionPool connectionPool = ConnectionPool.getInstance();
	private final Logger log = Logger.getLogger(UserDaoImpl.class);


	private static final String SELECT_USER_BY_LOGIN = "SELECT u.id, u.login, r.role "
			+ "FROM users u, user_roles ur, roles r "
			+ "WHERE u.login=? and u.password=? and u.id = ur.user_id and ur.role_id = r.id;";
	
	private final static String INSERT_USER = "INSERT INTO users (name,surname,login,password,phone_number,street,house,flat,building) " +
            "VALUES(?,?,?,?,?,?,?,?,?);";
	
	private final static String SELECT_USER_ID = "SELECT u.id "
			+ "FROM users u "
			+ "WHERE u.login=?";
	
	private final static String INSERT_USER_ROLE = "INSERT INTO user_roles (user_id,role_id) " +
            "VALUES(?,3);";

	@Override
	public User auth(AuthData authData) throws DaoException {
		User user = null;
		CustomConnectionProvider customConnectionProvider;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			log.info("try to auth " + authData);
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN);

			preparedStatement.setString(1, authData.getLogin());
			preparedStatement.setString(2, authData.getPassword());

			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()){
				log.warn("user not found");
				throw new DaoException("User not found");
			}
			user = new User();
			user.setId(Integer.parseInt(resultSet.getString("id")));
			user.setLogin(resultSet.getString("login"));
			user.setRole(resultSet.getString("role"));

			System.out.println(user);
				

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (ConnectionException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			connectionPool.closeConnection(connection, preparedStatement, resultSet);
		}

		return user;
	}

	@Override
	public Wrapper<Object> registrate(RegData registrationData) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			log.info("try to reg " + registrationData);
			connection = connectionPool.takeConnection();
			String login = registrationData.getLogin();
			
			if(!checkIfLoginUnique(login, connection)) {
				log.warn("login is not unique");
				return new Wrapper.Builder().status(Status.LOGIN_OCCUPIED).build();
			}
			
			preparedStatement = connection.prepareStatement(INSERT_USER);

			preparedStatement.setString(1, registrationData.getName());
			preparedStatement.setString(2, registrationData.getSurname());
			preparedStatement.setString(3, login);
			preparedStatement.setString(4, registrationData.getPassword());
			preparedStatement.setString(5, registrationData.getPhoneNumber());
			preparedStatement.setString(6, registrationData.getStreet());
			preparedStatement.setLong(7, registrationData.getHouse());
			preparedStatement.setLong(8, registrationData.getFlat());
			preparedStatement.setString(9, registrationData.getBuilding());

			preparedStatement.executeUpdate();
			// add note to user_roles
			addUserRole(connection, login);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (ConnectionException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			connectionPool.closeConnection(connection, preparedStatement);
		}
		return new Wrapper.Builder().status(Status.SUCCESSFULL).build();	
	}

	private void addUserRole(Connection connection, String login) throws DaoException {
		PreparedStatement prepareStatement = null;
		
		int userId = getUserId(login, connection);
		
		try {
			prepareStatement = connection.prepareStatement(INSERT_USER_ROLE);
			prepareStatement.setLong(1, userId);
			
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			log.error("SQL exception adding user role", e);
			throw new DaoException(e);
		}
		finally {
			connectionPool.closeConnection(prepareStatement);
		}
	}

	private boolean checkIfLoginUnique(String login, Connection connection) throws DaoException {
		int userId = getUserId(login, connection);
		return userId == 0;
	}
	
	private int getUserId(String login, Connection connection) throws DaoException {
		int userId = 0;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		
		try {
			prepareStatement = connection.prepareStatement(SELECT_USER_ID);
			prepareStatement.setString(1, login);
			resultSet = prepareStatement.executeQuery();
			
			if (!resultSet.next()) {
				log.warn("user not found by id");
				return userId;
			}
			userId = Integer.parseInt(resultSet.getString("id"));
			
		} catch (SQLException e) {
			log.error("SQL exception select user by id", e);
			throw new DaoException(e);
		}
		finally {
			connectionPool.closeConnection(prepareStatement, resultSet);
		}
		return userId;
	}
}
