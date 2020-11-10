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

    UserProfile getUserProfile(long id) throws ServiceException;

    Wrapper<Object> editUserProfile(UserProfile userProfile) throws ServiceException;

    Map<String, List<UserProfile>> getEmployeesByPositions() throws ServiceException;

    List<UserProfile> getAllEmployees() throws ServiceException;

    List<UserProfile> getAllAdmins() throws ServiceException;
}
