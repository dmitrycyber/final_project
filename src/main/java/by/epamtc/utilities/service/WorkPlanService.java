package by.epamtc.utilities.service;

import by.epamtc.utilities.entity.Note;
import by.epamtc.utilities.service.exception.ServiceException;
import by.epamtc.utilities.util.Wrapper;

import java.util.List;

public interface WorkPlanService {


    Wrapper<Object> addNewNote(Note note) throws ServiceException;

    List<Note> getAllNotes() throws ServiceException;

    List<Note> getNotesByEmployeeId(long employeeId) throws ServiceException;
}
