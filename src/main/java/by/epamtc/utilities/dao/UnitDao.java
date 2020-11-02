package by.epamtc.utilities.dao;

import by.epamtc.utilities.dao.exception.DaoException;
import by.epamtc.utilities.entity.Unit;

import java.util.List;

public interface UnitDao {
    String findUnitTitleById(long unitId) throws DaoException;

    List<Unit> findAllUnits() throws DaoException;
}
