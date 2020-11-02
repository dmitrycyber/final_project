package by.epamtc.utilities.service.impl;

import by.epamtc.utilities.dao.*;
import by.epamtc.utilities.dao.exception.DaoException;
import by.epamtc.utilities.entity.Order;
import by.epamtc.utilities.entity.User;
import by.epamtc.utilities.service.OrderService;
import by.epamtc.utilities.service.exception.ServiceException;
import by.epamtc.utilities.util.RoleConsts;
import by.epamtc.utilities.util.Status;
import by.epamtc.utilities.util.Wrapper;
import org.apache.log4j.Logger;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
    private final Logger log = Logger.getLogger(OrderServiceImpl.class);


    @Override
    public Wrapper<Object> getOrderList(User user) throws ServiceException {
        final List<Order> orderList;

        try {
            if (user.getRole().equals(RoleConsts.USERS)){
                orderList = orderDao.findAllOrdersById(user.getId());
                return new Wrapper.Builder()
                        .status(Status.SUCCESSFULL)
                        .message(orderList).build();
            }

            orderList = orderDao.findAllOrders();


            return new Wrapper.Builder()
                    .status(Status.SUCCESSFULL)
                    .message(orderList).build();

        } catch (DaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }


    }
}
