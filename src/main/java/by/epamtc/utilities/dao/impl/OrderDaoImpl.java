package by.epamtc.utilities.dao.impl;

import by.epamtc.utilities.dao.DaoFactory;
import by.epamtc.utilities.dao.OrderDao;
import by.epamtc.utilities.dao.UserDao;
import by.epamtc.utilities.dao.exception.DaoException;
import by.epamtc.utilities.dao.source.ConnectionException;
import by.epamtc.utilities.dao.source.ConnectionPool;
import by.epamtc.utilities.entity.Order;
import by.epamtc.utilities.entity.User;
import by.epamtc.utilities.entity.UserProfile;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private final Logger log = Logger.getLogger(OrderDaoImpl.class);

    private static final String SELECT_ALL_ORDERS =
            "SELECT o.id, o.work_type_id, o.user_id, o.scale_units, o.scale_value, o.date_start, o.date_finish, o.severity, o.description, o.status, wt.work_type, un.title, us.name, us.surname, us.street, us.house, us.flat, us.building "
            + "FROM orders o, work_types wt, units un, users us "
            + "WHERE o.user_id=us.id and o.work_type_id=wt.id and o.scale_units=un.id;";

    @Override
    public List<Order> getOrderList() throws DaoException {
        List<Order> orders;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            orders = new ArrayList<>();
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_ORDERS);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                final Order order = new Order.Builder()
                        .id(resultSet.getInt("id"))
                        .workTypeId(resultSet.getInt("work_type_id"))
                        .userId(resultSet.getInt("user_id"))
                        .scaleUnitsId(resultSet.getInt("scale_units"))
                        .scaleValue(resultSet.getDouble("scale_value"))
                        .startDate(resultSet.getTimestamp("date_start"))
                        .endDate(resultSet.getTimestamp("date_finish"))
                        .isSeveral(resultSet.getBoolean("severity"))
                        .description(resultSet.getString("description"))

                        .userName(resultSet.getString("name"))
                        .userSurname(resultSet.getString("surname"))
                        .scaleUnit(resultSet.getString("title"))
                        .workType(resultSet.getString("work_type"))
                        .status(resultSet.getString("status"))
                        .street(resultSet.getString("street"))
                        .house(resultSet.getInt("house"))
                        .building(resultSet.getString("building"))
                        .flat(resultSet.getInt("flat")).build();

                orders.add(order);
            }
            return orders;


        } catch (SQLException e) {
            log.error(e);
            throw new DaoException(e);
        } catch (ConnectionException e) {
            log.error(e);
            throw new DaoException(e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
    }


}
