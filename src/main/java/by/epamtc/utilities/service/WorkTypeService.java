package by.epamtc.utilities.service;

import by.epamtc.utilities.entity.WorkType;
import by.epamtc.utilities.service.exception.ServiceException;

import java.util.List;

public interface WorkTypeService {
    List<WorkType> allWorkTypes() throws ServiceException;
}
