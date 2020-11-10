package by.epamtc.utilities.dao;

import by.epamtc.utilities.dao.exception.DaoException;
import by.epamtc.utilities.entity.Note;
import by.epamtc.utilities.util.Wrapper;

import java.util.List;

public interface WorkPlanDao {

    Wrapper<Object> processOrderNote(Note note) throws DaoException;

    List<Note> findAllNotes() throws DaoException;

    List<Note> findAllNotesByEmployeeId(long employeeId) throws DaoException;
}
