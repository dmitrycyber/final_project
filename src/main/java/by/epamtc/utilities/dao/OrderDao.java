package by.epamtc.utilities.dao;

import by.epamtc.utilities.dao.exception.DaoException;
import by.epamtc.utilities.entity.Order;
import by.epamtc.utilities.entity.User;
import by.epamtc.utilities.util.Wrapper;
import org.apache.taglibs.standard.tag.el.core.WhenTag;

import java.util.List;

public interface OrderDao {

    /**
     * find all orders
     * @return list of finded users
     * @throws DaoException if something wos wrong in database activity
     */
    List<Order> findAllOrders() throws DaoException;

    /**
     * Find all user orders
     * @param userId
     * @return list of fount user orders
     * @throws DaoException if something wos wrong in database activity
     */
    List<Order> findAllOrdersByUserId(long userId) throws DaoException;

    /**
     * find order by order id
     * @param orderId
     * @return order if it fount
     * @throws DaoException if something wos wrong in database activity
     */

    Order findOrderById(long orderId) throws DaoException;

    /**
     * save created order
     * @param order
     * @return Wrapper with status
     * @throws DaoException if something wos wrong in database activity
     */
    Wrapper<Object> saveNewOrder(Order order) throws DaoException;



}
