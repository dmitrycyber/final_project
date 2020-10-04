package by.epamtc.utilities.service;

import by.epamtc.utilities.entity.AuthData;
import by.epamtc.utilities.entity.RegData;
import by.epamtc.utilities.entity.User;
import by.epamtc.utilities.service.exception.ServiceException;
import by.epamtc.utilities.util.Wrapper;

public interface UserService {
	User login(AuthData authData) throws ServiceException;
	
	Wrapper<Object> registration(RegData regData) throws ServiceException;

    User getUserProfile(long id) throws ServiceException;
}
