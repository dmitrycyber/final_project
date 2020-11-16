package by.epamtc.utilities.dao;

import by.epamtc.utilities.dao.exception.DaoException;
import by.epamtc.utilities.entity.Note;
import by.epamtc.utilities.util.Wrapper;

import java.util.List;

public interface WorkPlanDao {

    /**
     * Save new order create work plan note
     * @param note
     * @return object with query status and data
     * @throws DaoException if something wos wrong in database activity
     */
    Wrapper<Object> saveOrderNote(Note note) throws DaoException;

    /**
     * Find all work plan notes
     * @return
     * @throws DaoException
     */
    List<Note> findAllNotes() throws DaoException;

    List<Note> findAllNotesByEmployeeId(long employeeId) throws DaoException;
}
