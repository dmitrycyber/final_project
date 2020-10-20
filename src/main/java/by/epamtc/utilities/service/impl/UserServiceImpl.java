package by.epamtc.utilities.service.impl;

import by.epamtc.utilities.dao.DaoFactory;
import by.epamtc.utilities.dao.UserDao;
import by.epamtc.utilities.dao.exception.DaoException;
import by.epamtc.utilities.entity.AuthData;
import by.epamtc.utilities.entity.RegistrationData;
import by.epamtc.utilities.entity.User;
import by.epamtc.utilities.entity.UserProfile;
import by.epamtc.utilities.service.UserService;
import by.epamtc.utilities.service.exception.ServiceException;
import by.epamtc.utilities.util.Wrapper;

public class UserServiceImpl implements UserService {
	private final DaoFactory dao = DaoFactory.getInstance();
	private final UserDao userDao = dao.getUserDAO();

	@Override
	public User login(AuthData authData) throws ServiceException {
		User user;

		try {
			user = userDao.auth(authData);
		}
		catch (DaoException e) {
			throw new ServiceException(e);
		}
		return user;
	}

	@Override
	public Wrapper<Object> registration(RegistrationData registrationData) throws ServiceException {
		Wrapper<Object> obWrapper;
		
		try {
			obWrapper = userDao.register(registrationData);
			return obWrapper;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public UserProfile getUserProfile(long userId) throws ServiceException {
		try {
			UserProfile userProfile = userDao.getUserProfile(userId);
 			return userProfile;

		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Wrapper<Object> editUserProfile(UserProfile userProfile) throws ServiceException {
		Wrapper<Object> obWrapper;

		try {
			obWrapper = userDao.editProfile(userProfile);
			return obWrapper;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}


}
