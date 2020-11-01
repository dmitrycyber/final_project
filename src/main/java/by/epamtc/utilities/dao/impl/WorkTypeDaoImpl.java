package by.epamtc.utilities.dao.impl;

import by.epamtc.utilities.dao.exception.DaoException;
import by.epamtc.utilities.dao.source.ConnectionException;
import by.epamtc.utilities.dao.source.ConnectionPool;
import by.epamtc.utilities.dao.WorkTypeDao;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkTypeDaoImpl implements WorkTypeDao {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private final Logger log = Logger.getLogger(WorkTypeDaoImpl.class);

    private static final String SELECT_WORK_TYPE_BY_ID = "SELECT wt.work_type "
            + "FROM work_types wt "
            + "WHERE wt.id=?";

    @Override
    public String workTypeById(long workTypeId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String workType;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_WORK_TYPE_BY_ID);

            preparedStatement.setLong(1, workTypeId);

            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()){
                log.warn("work type not found");
                throw new DaoException("work type not found");
            }

            workType = resultSet.getString("work_type");

            return workType;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        } catch (ConnectionException e) {
            log.error(e);
            throw new DaoException(e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
    }
}
