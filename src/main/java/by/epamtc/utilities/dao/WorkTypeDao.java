package by.epamtc.utilities.dao;

import by.epamtc.utilities.dao.exception.DaoException;

public interface WorkTypeDao {
    String workTypeById(long workTypeId) throws DaoException;
}
