package by.epamtc.utilities.service.impl;

import by.epamtc.utilities.dao.*;
import by.epamtc.utilities.dao.exception.DaoException;
import by.epamtc.utilities.entity.Order;
import by.epamtc.utilities.entity.User;
import by.epamtc.utilities.entity.UserProfile;
import by.epamtc.utilities.service.OrderService;
import by.epamtc.utilities.service.exception.ServiceException;
import by.epamtc.utilities.util.Status;
import by.epamtc.utilities.util.Wrapper;
import org.apache.log4j.Logger;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
    private final UserDao userDao = DaoFactory.getInstance().getUserDAO();
    private final WorkTypeDao workTypeDao = DaoFactory.getInstance().getWorkTypeDao();
    private final UnitDao unitDao = DaoFactory.getInstance().getUnitDao();
    private final Logger log = Logger.getLogger(OrderServiceImpl.class);


    @Override
    public Wrapper<Object> getOrderList(User user) throws ServiceException {
        final List<Order> orderList;

        try {
            orderList = orderDao.getOrderList();


            return new Wrapper.Builder()
                    .status(Status.SUCCESSFULL)
                    .message(orderList).build();

        } catch (DaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }


    }
}
