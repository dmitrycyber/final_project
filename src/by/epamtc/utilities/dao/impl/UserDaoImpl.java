package by.epamtc.utilities.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.epamtc.utilities.dao.CustomConnectionProvider;
import by.epamtc.utilities.dao.UserDao;
import by.epamtc.utilities.dao.exception.DaoException;
import by.epamtc.utilities.entity.AuthData;
import by.epamtc.utilities.entity.RegData;
import by.epamtc.utilities.entity.User;

public class UserDaoImpl implements UserDao {
    
    private static final String SELECT_USER_BY_LOGIN =
            "SELECT u.id, u.login " +
                    "FROM users u " +
                    "WHERE u.login=? and u.password=?;";
    
	
	@Override
	public User auth(AuthData authData) throws DaoException {
		User user = null;

        CustomConnectionProvider customConnectionProvider;
        Connection connection;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
        	System.out.println("try to auth " + authData);
            customConnectionProvider = CustomConnectionProvider.getInstance();
            connection = customConnectionProvider.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN);
           

            preparedStatement.setString(1, authData.getLogin());
            preparedStatement.setString(2, authData.getPassword());

            resultSet = preparedStatement.executeQuery();
            
            System.out.println("RESULT SET " + resultSet);

            if (resultSet.next()) {
                String id = resultSet.getString("id");
                String login = resultSet.getString("login");
                
                System.out.println("id = " + id);
                System.out.println("login = " + login);
                
//                String role = resultSet.getString("user_role");
//                String rating = resultSet.getString("user_rating");

//                user = new User();
                
//                user.setId(id);
//                user.setLogin(login);
//                user.setRole(role);
            } else {
            	System.out.println("USER NOT FOUND");
            }
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException throwable) {
                	e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                
                    preparedStatement.close();
                
            }
        }

        return user;
	}

	@Override
	public boolean registrate(RegData registrationData) throws DaoException {
		
		return false;
	}

}
