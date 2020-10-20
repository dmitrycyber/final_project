package by.epamtc.utilities.dao;

import by.epamtc.utilities.dao.impl.AdminDaoImpl;
import by.epamtc.utilities.dao.impl.UserDaoImpl;

public class DaoFactory {
	private static final DaoFactory instance = new DaoFactory();

    private static final UserDao userDao = new UserDaoImpl();

    private static final AdminDao adminDao = new AdminDaoImpl();

    public AdminDao getAdminDao() {
        return adminDao;
    }

    public static DaoFactory getInstance() {
        return instance;
    }

    public UserDao getUserDAO() {
        return userDao;
    }
}
