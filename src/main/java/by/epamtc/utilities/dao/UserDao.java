package by.epamtc.utilities.dao;

import by.epamtc.utilities.dao.exception.DaoException;
import by.epamtc.utilities.entity.AuthData;
import by.epamtc.utilities.entity.RegData;
import by.epamtc.utilities.entity.User;
import by.epamtc.utilities.util.Wrapper;

public interface UserDao {
    User auth(AuthData authorizationData) throws DaoException;
    Wrapper<Object> registrate(RegData registrationData) throws DaoException;
    
}