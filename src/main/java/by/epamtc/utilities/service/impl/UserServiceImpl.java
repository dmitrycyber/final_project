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
import by.epamtc.utilities.util.RoleConsts;
import by.epamtc.utilities.util.Wrapper;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
	private final Logger log = Logger.getLogger(UserServiceImpl.class);
	private final DaoFactory dao = DaoFactory.getInstance();
	private final UserDao userDao = dao.getUserDAO();
//	private static final String EMPLOYEE_ROLE = "employee";
//	private static final String EMPLOYEE_ROLE = "employee";

	@Override
	public User login(AuthData authData) throws ServiceException {
		User user;

		try {
			user = userDao.authorizeUser(authData);
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
			obWrapper = userDao.saveUser(registrationData);
			return obWrapper;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public UserProfile findUserProfile(long userId) throws ServiceException {
		try {
			UserProfile userProfile = userDao.findUserProfile(userId);
 			return userProfile;

		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Wrapper<Object> editUserProfile(UserProfile userProfile) throws ServiceException {
		Wrapper<Object> obWrapper;

		try {
			obWrapper = userDao.updateUserProfile(userProfile);
			return obWrapper;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

    @Override
    public Map<String, List<UserProfile>> findEmployeesByPositions() throws ServiceException {

		try {
			final List<UserProfile> allByRole = userDao.findAllByRole(RoleConsts.EMPLOYEES);

			return allByRole.stream()
					.collect(Collectors.groupingBy(UserProfile::getPosition));

		} catch (DaoException e) {
			log.error(e);
			throw new ServiceException(e);
		}
    }

	@Override
	public List<UserProfile> findAllEmployees() throws ServiceException {
		try {
			return userDao.findAllByRole(RoleConsts.EMPLOYEES);

		} catch (DaoException e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<UserProfile> findAllAdmins() throws ServiceException {
		try {
			return userDao.findAllAdmins();

		} catch (DaoException e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}


}
