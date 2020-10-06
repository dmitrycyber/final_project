package by.epamtc.utilities.dao;

import by.epamtc.utilities.dao.impl.UserDaoImpl;

public class DaoFactory {
	private static final DaoFactory instance = new DaoFactory();

    private static final UserDao sqlUserDAO = new UserDaoImpl();

    public static DaoFactory getInstance() {
        return instance;
    }

    public UserDao getUserDAO() {
        return sqlUserDAO;
    }
}
