package by.epamtc.utilities.dao;

import by.epamtc.utilities.dao.exception.DaoException;
import by.epamtc.utilities.entity.AuthData;
import by.epamtc.utilities.entity.RegistrationData;
import by.epamtc.utilities.entity.User;
import by.epamtc.utilities.entity.UserProfile;
import by.epamtc.utilities.util.Wrapper;

public interface UserDao {
    User auth(AuthData authorizationData) throws DaoException;
    Wrapper<Object> register(RegistrationData registrationData) throws DaoException;
    UserProfile getUserProfile(long userId) throws DaoException;
    
}
