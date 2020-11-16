package by.epamtc.utilities.dao.impl;

import by.epamtc.utilities.dao.DaoFactory;
import by.epamtc.utilities.dao.UserDao;
import by.epamtc.utilities.dao.exception.DaoException;
import by.epamtc.utilities.dao.exception.DaoUserAuthException;
import by.epamtc.utilities.dao.impl.pool.TestConnectionPool;
import by.epamtc.utilities.dao.source.ConnectionException;
import by.epamtc.utilities.dao.source.ConnectionPool;
import by.epamtc.utilities.entity.AuthData;
import by.epamtc.utilities.entity.RegistrationData;
import by.epamtc.utilities.entity.User;
import by.epamtc.utilities.util.RoleConsts;
import by.epamtc.utilities.util.Status;
import by.epamtc.utilities.util.Wrapper;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserDaoTest {

    @Before
    public void init() throws ConnectionException, IOException {

//        final TestConnectionPool pool = TestConnectionPool.getInstance();

        final ConnectionPool pool = ConnectionPool.getInstance();
//
        final Connection connection = pool.takeConnection();

        final ScriptRunner scriptRunner = new ScriptRunner(connection);

        Reader reader = new BufferedReader(new FileReader(UserDaoTest.class.getClassLoader().getResource("db_v2.sql").getPath()));

        scriptRunner.runScript(reader);

        pool.closeConnection(connection);

    }

    @Test
    public void checkIfUserSuccessfullyAuth() throws DaoException, DaoUserAuthException {
        final UserDao userDAO = DaoFactory.getInstance().getUserDAO();

        AuthData authData = new AuthData();
        authData.setLogin("zulu");
        authData.setPassword("zulu");

        final User actualUser = userDAO.authorizeUser(authData);

        final User expectedUser = new User.Builder()
                .name("Светлана")
                .role(RoleConsts.EMPLOYEES)
                .login("zulu")
                .id(3).build();

        assertEquals(expectedUser, actualUser);
    }

    @Test(expected = DaoUserAuthException.class)
    public void checkIfUserAuthException() throws DaoException, DaoUserAuthException {
        final UserDao userDAO = DaoFactory.getInstance().getUserDAO();

        AuthData authData = new AuthData();
        authData.setLogin("zulu");
        authData.setPassword("zulu1");

        userDAO.authorizeUser(authData);
    }

    @Test
    public void userSuccessfulRegistrationTest() throws DaoException, ConnectionException, SQLException {
        final UserDao userDAO = DaoFactory.getInstance().getUserDAO();

        final String userLogin = "qweqwe1";
        final RegistrationData expectedUserData = new RegistrationData.Builder()
                .name("qwe")
                .surname("qwe")
                .login(userLogin)
                .password("qwe")
                .phoneNumber("+1233456")
                .street("street")
                .building("1")
                .flat(123)
                .house(5).build();

        RegistrationData actualUserData = null;


        final Wrapper<Object> objectWrapper = userDAO.saveUser(expectedUserData);

        assertEquals(Status.SUCCESSFULL, objectWrapper.getStatus());

        String SELECT_USER_BY_ID = "SELECT name,surname,login,password,phone_number,street,house,flat,building "
                + "FROM users u "
                + "WHERE u.login=?;";

        final Connection connection = TestConnectionPool.getInstance().takeConnection();

        final PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
        preparedStatement.setString(1, userLogin);

        final ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            actualUserData = new RegistrationData.Builder()
                    .name(resultSet.getString("name"))
                    .surname(resultSet.getString("surname"))
                    .login(resultSet.getString("login"))
                    .password(resultSet.getString("password"))
                    .phoneNumber(resultSet.getString("phone_number"))
                    .street(resultSet.getString("street"))
                    .house(resultSet.getInt("house"))
                    .flat(resultSet.getInt("flat"))
                    .building(resultSet.getString("building")).build();
        }

        assertNotNull(actualUserData);

        assertEquals(expectedUserData.getName(), actualUserData.getName());
        assertEquals(expectedUserData.getSurname(), actualUserData.getSurname());
        assertEquals(expectedUserData.getLogin(), actualUserData.getLogin());
        assertEquals(expectedUserData.getPassword(), actualUserData.getPassword());
        assertEquals(expectedUserData.getStreet(), actualUserData.getStreet());
        assertEquals(expectedUserData.getHouse(), actualUserData.getHouse());
        assertEquals(expectedUserData.getBuilding(), actualUserData.getBuilding());
        assertEquals(expectedUserData.getFlat(), actualUserData.getFlat());
    }

    @Test
    public void userRegistrationWithNotUniqueLoginTest() throws DaoException {
        final UserDao userDAO = DaoFactory.getInstance().getUserDAO();

        final String userLogin = "zulu";
        final RegistrationData expectedUserData = new RegistrationData.Builder()
                .name("qwe")
                .surname("qwe")
                .login(userLogin)
                .password("qwe")
                .phoneNumber("+1233456")
                .street("street")
                .building("1")
                .flat(123)
                .house(5).build();

        final Wrapper<Object> objectWrapper = userDAO.saveUser(expectedUserData);

        assertEquals(Status.LOGIN_OCCUPIED, objectWrapper.getStatus());
    }
}

