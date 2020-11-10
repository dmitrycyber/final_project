package by.epamtc.utilities.service.impl;

import by.epamtc.utilities.dao.DaoFactory;
import by.epamtc.utilities.dao.WorkPlanDao;
import by.epamtc.utilities.dao.exception.DaoException;
import by.epamtc.utilities.entity.Note;
import by.epamtc.utilities.service.WorkPlanService;
import by.epamtc.utilities.service.exception.ServiceException;
import by.epamtc.utilities.util.Wrapper;
import org.apache.log4j.Logger;

import java.util.List;

public class WorkPlanServiceImpl implements WorkPlanService {
    private final Logger log = Logger.getLogger(WorkTypeServiceImpl.class);
    private final WorkPlanDao workPlanDao = DaoFactory.getInstance().getWorkPlanDao();


    @Override
    public Wrapper<Object> addNewNote(Note note) throws ServiceException {
        try {
            return workPlanDao.processOrderNote(note);
        } catch (DaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Note> getAllNotes() throws ServiceException {
        try {
            return workPlanDao.findAllNotes();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Note> getNotesByEmployeeId(long employeeId) throws ServiceException {
        try {
            return workPlanDao.findAllNotesByEmployeeId(employeeId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
