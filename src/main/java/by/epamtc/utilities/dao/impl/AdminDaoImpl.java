package by.epamtc.utilities.dao.impl;

import by.epamtc.utilities.dao.AdminDao;
import by.epamtc.utilities.dao.exception.DaoException;
import by.epamtc.utilities.dao.source.ConnectionException;
import by.epamtc.utilities.dao.source.ConnectionPool;
import by.epamtc.utilities.entity.User;
import by.epamtc.utilities.entity.UserProfile;
import by.epamtc.utilities.util.RoleConsts;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDao {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private final Logger log = Logger.getLogger(AdminDaoImpl.class);

    private static final String SELECT_ADMINS = "SELECT u.id, u.login, r.role "
            + "FROM users u, user_roles ur, roles r "
            + "WHERE u.id=ur.user_id and ur.role_id=r.id and r.role=?;";

    @Override
    public List<User> getUsersByRole(String role) throws DaoException {
        List<User> adminList;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            adminList = new ArrayList<>();
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ADMINS);
            preparedStatement.setString(1, "admin");

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final User admin = new User.Builder()
                        .id(Integer.parseInt(resultSet.getString("id")))
                        .login(resultSet.getString("login"))
                        .role(resultSet.getString("role")).build();

                adminList.add(admin);

            }
            return adminList;


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
}
