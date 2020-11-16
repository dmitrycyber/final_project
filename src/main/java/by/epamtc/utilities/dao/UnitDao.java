package by.epamtc.utilities.dao;

import by.epamtc.utilities.dao.exception.DaoException;
import by.epamtc.utilities.entity.Unit;

import java.util.List;

public interface UnitDao {
    /**
     * find unit naming by unit id
     * @param unitId
     * @return Unit title if fount
     * @throws DaoException if something wos wrong in database activity
     */
    String findUnitTitleById(long unitId) throws DaoException;

    /**
     * find all system units
     * @return List with fount units
     * @throws DaoException if something wos wrong in database activity
     */
    List<Unit> findAllUnits() throws DaoException;
}
