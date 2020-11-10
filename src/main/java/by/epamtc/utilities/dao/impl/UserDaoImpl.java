package by.epamtc.utilities.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epamtc.utilities.dao.UserDao;
import by.epamtc.utilities.dao.exception.DaoException;
import by.epamtc.utilities.dao.source.ConnectionException;
import by.epamtc.utilities.dao.source.ConnectionPool;
import by.epamtc.utilities.entity.AuthData;
import by.epamtc.utilities.entity.RegistrationData;
import by.epamtc.utilities.entity.User;
import by.epamtc.utilities.entity.UserProfile;
import by.epamtc.utilities.util.RoleConsts;
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

	private final static String EDIT_USER_PROFILE = "UPDATE users SET name=?,surname=?,login=?,phone_number=?,street=?,house=?,flat=?,building=?" +
			"WHERE id=?;";
	
	private final static String SELECT_USER_ID = "SELECT u.id "
			+ "FROM users u "
			+ "WHERE u.login=?";
	
	private final static String INSERT_USER_ROLE = "INSERT INTO user_roles (user_id,role_id) " +
            "VALUES(?,3);";

	private final static String SELECT_USER_BY_ID = "SELECT name,surname,login,phone_number,street,house,flat,building "
			+ "FROM users u "
			+ "WHERE u.id=?";

	private final static String SELECT_EMPLOYEES_BY_ROLE = "SELECT u.id, u.name, u.surname, p.position, u.phone_number, u.login, u.hiring_date " +
			"FROM users u " +
			"JOIN user_roles ur ON u.id = ur.user_id " +
			"JOIN roles r ON r.id = ur.role_id " +
			"JOIN positions p ON p.id = u.position_id " +
			"WHERE r.role = ?;";

	private final static String SELECT_ADMINS = "SELECT u.id, u.name, u.surname, u.phone_number, u.login, u.hiring_date " +
			"FROM users u, user_roles ur, roles r " +
			"WHERE u.id=ur.user_id and ur.role_id=r.id and r.role=?;";




	@Override
	public User auth(AuthData authData) throws DaoException {
		User user;
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

			user = new User.Builder()
					.id(Integer.parseInt(resultSet.getString("id")))
					.login(resultSet.getString("login"))
					.role(resultSet.getString("role")).build();

//			user = new User();
//			user.setId(Integer.parseInt(resultSet.getString("id")));
//			user.setLogin(resultSet.getString("login"));
//			user.setRole(resultSet.getString("role"));

			return user;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (ConnectionException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			connectionPool.closeConnection(connection, preparedStatement, resultSet);
		}


	}

	@Override
	public Wrapper<Object> register(RegistrationData registrationData) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			log.info("try to reg " + registrationData);
			connection = connectionPool.takeConnection();
			String login = registrationData.getLogin();
			
			if(!isLoginUnique(login, connection)) {
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
			return new Wrapper.Builder().status(Status.SUCCESSFULL).build();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (ConnectionException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			connectionPool.closeConnection(connection, preparedStatement);
		}

	}

	@Override
	public UserProfile getUserProfile(long userId) throws DaoException {
		UserProfile profile;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			log.info("try to get user profile " + userId);
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);

			preparedStatement.setLong(1, userId);

			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()){
				log.warn("user not found");
				throw new DaoException("User not found");
			}

			profile = new UserProfile.Builder()
					.name(resultSet.getString("name"))
					.surname(resultSet.getString("surname"))
					.login(resultSet.getString("login"))
					.phoneNumber(resultSet.getString("phone_number"))
					.street(resultSet.getString("street"))
					.house(resultSet.getInt("house"))
					.flat(resultSet.getInt("flat"))
					.building(resultSet.getString("building")).build();

			return profile;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (ConnectionException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			connectionPool.closeConnection(connection, preparedStatement, resultSet);
		}
	}

	@Override
	public Wrapper<Object> editProfile(UserProfile userProfile) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			log.info("try to edit profile " + userProfile);
			connection = connectionPool.takeConnection();
			String login = userProfile.getLogin();

			if (userProfile.getOldLogin() != null){
				if(!isLoginUnique(login, connection)) {
					log.warn("login is not unique");
					return new Wrapper.Builder().status(Status.LOGIN_OCCUPIED).build();
				}
			}

			preparedStatement = connection.prepareStatement(EDIT_USER_PROFILE);

			preparedStatement.setString(1, userProfile.getName());
			preparedStatement.setString(2, userProfile.getSurname());
			preparedStatement.setString(3, login);
			preparedStatement.setString(4, userProfile.getPhoneNumber());
			preparedStatement.setString(5, userProfile.getStreet());
			preparedStatement.setLong(6, userProfile.getHouse());
			preparedStatement.setLong(7, userProfile.getFlat());
			preparedStatement.setString(8, userProfile.getBuilding());
			preparedStatement.setLong(9, userProfile.getUserId());
			preparedStatement.executeUpdate();

			return new Wrapper.Builder().status(Status.SUCCESSFULL).build();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (ConnectionException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			connectionPool.closeConnection(connection, preparedStatement);
		}
	}

	@Override
	public List<UserProfile> findAllByRole(String role) throws DaoException {
		List<UserProfile> userProfiles;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			userProfiles = new ArrayList<>();
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(SELECT_EMPLOYEES_BY_ROLE);
			preparedStatement.setString(1, role);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				final UserProfile build = new UserProfile.Builder()
						.userId(resultSet.getLong("id"))
						.name(resultSet.getString("name"))
						.surname(resultSet.getString("surname"))
						.position(resultSet.getString("position"))
						.login(resultSet.getString("login"))
						.phoneNumber(resultSet.getString("phone_number"))
						.hiringDate(resultSet.getTimestamp("hiring_date")).build();

				userProfiles.add(build);

			}
			return userProfiles;


		} catch (SQLException e) {
			log.error(e);
			throw new DaoException(e);
		} catch (ConnectionException e) {
			log.error(e);
			throw new DaoException(e);
		} finally {
			connectionPool.closeConnection(connection, preparedStatement, resultSet);
		}
	}

	@Override
	public List<UserProfile> findAllAdmins() throws DaoException {
		List<UserProfile> userProfiles;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			userProfiles = new ArrayList<>();
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(SELECT_ADMINS);
			preparedStatement.setString(1, RoleConsts.ADMINS);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				final UserProfile build = new UserProfile.Builder()
						.userId(resultSet.getLong("id"))
						.name(resultSet.getString("name"))
						.surname(resultSet.getString("surname"))
						.login(resultSet.getString("login"))
						.phoneNumber(resultSet.getString("phone_number"))
						.hiringDate(resultSet.getTimestamp("hiring_date")).build();

				userProfiles.add(build);

			}
			return userProfiles;


		} catch (SQLException e) {
			log.error(e);
			throw new DaoException(e);
		} catch (ConnectionException e) {
			log.error(e);
			throw new DaoException(e);
		} finally {
			connectionPool.closeConnection(connection, preparedStatement, resultSet);
		}
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

	private boolean isLoginUnique(String login, Connection connection) throws DaoException {
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
