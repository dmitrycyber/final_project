package by.epamtc.utilities.service;

import by.epamtc.utilities.entity.Order;
import by.epamtc.utilities.entity.User;
import by.epamtc.utilities.service.exception.ServiceException;
import by.epamtc.utilities.util.Wrapper;

public interface OrderService {
    Wrapper<Object> getOrderList(User user) throws ServiceException;

    Wrapper<Object> addOrder(Order order) throws ServiceException;

    Order getOrderById(long orderId) throws ServiceException;
}
