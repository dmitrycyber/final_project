package by.epamtc.utilities.service;

import by.epamtc.utilities.service.impl.AdminServiceImpl;
import by.epamtc.utilities.service.impl.UserServiceImpl;

public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return instance;
    }

    private final UserService userService = new UserServiceImpl();

    private final AdminService adminService = new AdminServiceImpl();

    public AdminService getAdminService() {
        return adminService;
    }

    private ServiceFactory() {
    }

    public UserService getUserService() {
        return userService;
    }
}
