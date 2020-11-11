package by.epamtc.utilities.dao.impl;

import by.epamtc.utilities.dao.exception.DaoException;
import by.epamtc.utilities.dao.source.ConnectionException;
import by.epamtc.utilities.dao.source.ConnectionPool;
import by.epamtc.utilities.dao.WorkTypeDao;
import by.epamtc.utilities.entity.Unit;
import by.epamtc.utilities.entity.WorkType;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkTypeDaoImpl implements WorkTypeDao {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private final Logger log = Logger.getLogger(WorkTypeDaoImpl.class);

    private static final String SELECT_WORK_TYPE_BY_ID = "SELECT wt.work_type "
            + "FROM work_types wt "
            + "WHERE wt.id=?";

    private static final String SELECT_ALL_WORK_TYPES = "SELECT * "
            + "FROM work_types wt;";

    @Override
    public String findWorkTypeById(long workTypeId) throws DaoException {
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
    public List<WorkType> findAllWorkTypes() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<WorkType> workTypes;

        try {
            workTypes = new ArrayList<>();
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_WORK_TYPES);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                final WorkType workType = new WorkType.Builder()
                        .id(resultSet.getInt("id"))
                        .workType(resultSet.getString("work_type")).build();

                workTypes.add(workType);
            }
            return workTypes;

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
