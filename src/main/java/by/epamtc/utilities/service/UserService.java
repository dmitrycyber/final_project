package by.epamtc.utilities.service;

import by.epamtc.utilities.entity.AuthData;
import by.epamtc.utilities.entity.RegistrationData;
import by.epamtc.utilities.entity.User;
import by.epamtc.utilities.service.exception.ServiceException;
import by.epamtc.utilities.util.Wrapper;

public interface UserService {
	User login(AuthData authData) throws ServiceException;
	
	Wrapper<Object> registration(RegistrationData registrationData) throws ServiceException;

    User getUserProfile(long id) throws ServiceException;
}
