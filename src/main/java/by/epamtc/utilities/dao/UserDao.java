package by.epamtc.utilities.dao;

import by.epamtc.utilities.dao.exception.DaoException;
import by.epamtc.utilities.entity.AuthData;
import by.epamtc.utilities.entity.RegistrationData;
import by.epamtc.utilities.entity.User;
import by.epamtc.utilities.entity.UserProfile;
import by.epamtc.utilities.util.Wrapper;

import java.util.List;

public interface UserDao {
    User auth(AuthData authorizationData) throws DaoException;
    Wrapper<Object> register(RegistrationData registrationData) throws DaoException;
    UserProfile getUserProfile(long userId) throws DaoException;
    Wrapper<Object> editProfile(UserProfile userProfile) throws DaoException;

    List<UserProfile> findAllByRole(String role) throws DaoException;

    List<UserProfile> findAllAdmins() throws DaoException;

}
