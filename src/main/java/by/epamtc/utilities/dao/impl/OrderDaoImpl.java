package by.epamtc.utilities.dao.impl;

import by.epamtc.utilities.dao.OrderDao;
import by.epamtc.utilities.dao.exception.DaoException;
import by.epamtc.utilities.dao.source.ConnectionException;
import by.epamtc.utilities.dao.source.ConnectionPool;
import by.epamtc.utilities.entity.Order;
import by.epamtc.utilities.util.Status;
import by.epamtc.utilities.util.Wrapper;
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
            "SELECT o.id, o.work_type_id, o.user_id, o.scale_units, o.scale_value, o.date_start, o.date_finish, o.severity, o.description, o.status, o.dispatcher_comment, wt.work_type, un.title, us.name, us.surname, us.street, us.house, us.flat, us.building "
            + "FROM orders o, work_types wt, units un, users us "
            + "WHERE o.user_id=us.id and o.work_type_id=wt.id and o.scale_units=un.id;";

    private static final String SELECT_ORDERS_BY_USER_ID =
            "SELECT o.id, o.work_type_id, o.user_id, o.scale_units, o.scale_value, o.date_start, o.date_finish, o.severity, o.description, o.status, o.dispatcher_comment, wt.work_type, un.title, us.name, us.surname, us.street, us.house, us.flat, us.building "
                    + "FROM orders o, work_types wt, units un, users us "
                    + "WHERE o.user_id=? and o.user_id=us.id and o.work_type_id=wt.id and o.scale_units=un.id;";

    private final static String INSERT_ORDER = "INSERT INTO orders (work_type_id,user_id,scale_units,scale_value,date_start,date_finish,severity,description,status) " +
            "VALUES(?,?,?,?,?,?,?,?,?);";

    private static final String SELECT_ORDER_BY_ID =
            "SELECT o.id, o.work_type_id, o.user_id, o.scale_units, o.scale_value, o.date_start, o.date_finish, o.severity, o.description, o.status, wt.work_type, un.title, us.name, us.surname, us.street, us.house, us.flat, us.building "
                    + "FROM orders o, work_types wt, units un, users us "
                    + "WHERE o.id = ? and o.user_id=us.id and o.work_type_id=wt.id and o.scale_units=un.id;";

    @Override
    public List<Order> findAllOrders() throws DaoException {
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
                        .flat(resultSet.getInt("flat"))
                        .dispatcherComment(resultSet.getString("dispatcher_comment")).build();

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

    @Override
    public List<Order> findAllOrdersByUserId(long userId) throws DaoException {
        List<Order> orders;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            orders = new ArrayList<>();
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ORDERS_BY_USER_ID);

            preparedStatement.setLong(1, userId);

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
                        .flat(resultSet.getInt("flat"))
                        .dispatcherComment(resultSet.getString("dispatcher_comment")).build();

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

    @Override
    public Order findOrderById(long orderId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_ID);

            preparedStatement.setLong(1, orderId);

            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()){
                log.warn("order not found");
                throw new DaoException("Order not found");
            }

            return new Order.Builder()
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

    @Override
    public Wrapper<Object> addNewOrder(Order order) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            log.info("try to add order " + order);

            connection = connectionPool.takeConnection();

            preparedStatement = connection.prepareStatement(INSERT_ORDER);

            preparedStatement.setLong(1, order.getWorkTypeId());
            preparedStatement.setLong(2, order.getUserId());
            preparedStatement.setLong(3, order.getScaleUnitsId());
            preparedStatement.setDouble(4, order.getScaleValue());
            preparedStatement.setTimestamp(5, order.getStartDate());
            preparedStatement.setTimestamp(6, order.getEndDate());
            preparedStatement.setBoolean(7, order.isSeveral());
            preparedStatement.setString(8, order.getDescription());
            preparedStatement.setString(9, order.getStatus());

            preparedStatement.executeUpdate();

            return new Wrapper.Builder().status(Status.SUCCESSFULL).build();

        } catch (SQLException e) {
            log.error(e);
            throw new DaoException(e);
        } catch (ConnectionException e) {
            log.error(e);
            throw new DaoException(e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }


}
