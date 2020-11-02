package by.epamtc.utilities.dao;

import by.epamtc.utilities.dao.exception.DaoException;
import by.epamtc.utilities.entity.Order;
import by.epamtc.utilities.entity.User;
import by.epamtc.utilities.util.Wrapper;
import org.apache.taglibs.standard.tag.el.core.WhenTag;

import java.util.List;

public interface OrderDao {
    List<Order> findAllOrders() throws DaoException;

    List<Order> findAllOrdersById(long userId) throws DaoException;

    Wrapper<Object> addNewOrder(Order order) throws DaoException;



}
