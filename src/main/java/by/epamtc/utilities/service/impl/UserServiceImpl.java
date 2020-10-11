package by.epamtc.utilities.service.impl;

import by.epamtc.utilities.dao.DaoFactory;
import by.epamtc.utilities.dao.UserDao;
import by.epamtc.utilities.dao.exception.DaoException;
import by.epamtc.utilities.entity.AuthData;
import by.epamtc.utilities.entity.RegistrationData;
import by.epamtc.utilities.entity.User;
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
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
		return obWrapper;

	}

	@Override
	public User getUserProfile(long id) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
