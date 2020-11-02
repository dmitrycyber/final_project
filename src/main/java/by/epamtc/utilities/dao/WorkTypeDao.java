package by.epamtc.utilities.dao;

import by.epamtc.utilities.dao.exception.DaoException;
import by.epamtc.utilities.entity.WorkType;

import java.util.List;

public interface WorkTypeDao {
    String findWorkTypeById(long workTypeId) throws DaoException;

    List<WorkType> findAllWorkTypes() throws DaoException;
}
