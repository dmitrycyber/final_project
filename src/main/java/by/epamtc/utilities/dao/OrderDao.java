package by.epamtc.utilities.dao;

import by.epamtc.utilities.dao.exception.DaoException;
import by.epamtc.utilities.entity.Order;
import by.epamtc.utilities.entity.User;

import java.util.List;

public interface OrderDao {
    List<Order> getOrderList() throws DaoException;


}
