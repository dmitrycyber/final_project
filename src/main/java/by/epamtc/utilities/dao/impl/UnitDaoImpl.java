package by.epamtc.utilities.dao.impl;

import by.epamtc.utilities.dao.UnitDao;
import by.epamtc.utilities.dao.exception.DaoException;
import by.epamtc.utilities.dao.source.ConnectionException;
import by.epamtc.utilities.dao.source.ConnectionPool;
import by.epamtc.utilities.entity.Unit;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnitDaoImpl implements UnitDao {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private final Logger log = Logger.getLogger(UnitDaoImpl.class);

    private static final String SELECT_SCALE_UNIT_BY_ID = "SELECT u.title "
            + "FROM units u "
            + "WHERE u.id=?";

    private static final String SELECT_ALL_UNITS = "SELECT * "
            + "FROM units u;";

    @Override
    public String findUnitTitleById(long unitId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String unitTitle;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_SCALE_UNIT_BY_ID);

            preparedStatement.setLong(1, unitId);

            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()){
                log.warn("unit not found");
                throw new DaoException("unit not found");
            }

            unitTitle = resultSet.getString("title");

            return unitTitle;

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

    @Override
    public List<Unit> findAllUnits() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Unit> units;

        try {
            units = new ArrayList<>();
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_UNITS);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                final Unit unit = new Unit.Builder()
                        .id(resultSet.getInt("id"))
                        .title(resultSet.getString("title")).build();

                units.add(unit);
            }
            return units;

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
