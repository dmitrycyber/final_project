package by.epamtc.utilities.service;

import by.epamtc.utilities.entity.AuthData;
import by.epamtc.utilities.entity.RegistrationData;
import by.epamtc.utilities.entity.User;
import by.epamtc.utilities.entity.UserProfile;
import by.epamtc.utilities.service.exception.ServiceException;
import by.epamtc.utilities.util.Wrapper;

import java.util.List;
import java.util.Map;

public interface UserService {
	User login(AuthData authData) throws ServiceException;
	
	Wrapper<Object> registration(RegistrationData registrationData) throws ServiceException;

    UserProfile findUserProfile(long id) throws ServiceException;

    Wrapper<Object> editUserProfile(UserProfile userProfile) throws ServiceException;

    Map<String, List<UserProfile>> findEmployeesByPositions() throws ServiceException;

    List<UserProfile> findAllEmployees() throws ServiceException;

    List<UserProfile> findAllAdmins() throws ServiceException;
}
