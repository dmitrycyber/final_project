package by.epamtc.utilities.dao;

import by.epamtc.utilities.dao.impl.*;

public class DaoFactory {
	private static final DaoFactory instance = new DaoFactory();

    private static final UserDao userDao = new UserDaoImpl();

    private static final AdminDao adminDao = new AdminDaoImpl();

    private static final OrderDao orderDao = new OrderDaoImpl();

    private static final WorkTypeDao workTypeDao = new WorkTypeDaoImpl();

    private static final UnitDao unitDao = new UnitDaoImpl();

    public UnitDao getUnitDao() {
        return unitDao;
    }

    public WorkTypeDao getWorkTypeDao() {
        return workTypeDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

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
