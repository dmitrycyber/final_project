package by.epamtc.utilities.dao;

import by.epamtc.utilities.dao.exception.DaoException;

public interface UnitDao {
    String findUnitTitleById(long unitId) throws DaoException;
}
