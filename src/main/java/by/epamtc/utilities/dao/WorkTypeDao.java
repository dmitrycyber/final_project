package by.epamtc.utilities.dao;

import by.epamtc.utilities.dao.exception.DaoException;
import by.epamtc.utilities.entity.WorkType;

import java.util.List;

public interface WorkTypeDao {

    /**
     * Method to find work type by work type id
     * @param workTypeId
     * @return String work type
     * @throws DaoException if something wos wrong in database activity
     */
    String findWorkTypeById(long workTypeId) throws DaoException;


    /**
     * Find all work types
     * @return List of work types
     * @throws DaoException if something wos wrong in database activity
     */
    List<WorkType> findAllWorkTypes() throws DaoException;
}
