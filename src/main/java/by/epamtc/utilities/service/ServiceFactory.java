package by.epamtc.utilities.service;

import by.epamtc.utilities.service.impl.*;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return instance;
    }

    private final UserService userService = new UserServiceImpl();

    private final AdminService adminService = new AdminServiceImpl();

    private final OrderService orderService = new OrderServiceImpl();

    private final UnitService unitService = new UnitServiceImpl();

    private final WorkTypeService workTypeService = new WorkTypeServiceImpl();

    private final WorkPlanService workPlanService = new WorkPlanServiceImpl();

    public WorkPlanService getWorkPlanService() {
        return workPlanService;
    }

    public WorkTypeService getWorkTypeService() {
        return workTypeService;
    }

    public UnitService getUnitService() {
        return unitService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public AdminService getAdminService() {
        return adminService;
    }

    private ServiceFactory() {
    }

    public UserService getUserService() {
        return userService;
    }
}
