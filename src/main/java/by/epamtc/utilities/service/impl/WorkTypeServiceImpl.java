package by.epamtc.utilities.service.impl;

import by.epamtc.utilities.dao.DaoFactory;
import by.epamtc.utilities.dao.WorkTypeDao;
import by.epamtc.utilities.dao.exception.DaoException;
import by.epamtc.utilities.entity.WorkType;
import by.epamtc.utilities.service.WorkTypeService;
import by.epamtc.utilities.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;

public class WorkTypeServiceImpl implements WorkTypeService {
    private final WorkTypeDao workTypeDao = DaoFactory.getInstance().getWorkTypeDao();
    private final Logger log = Logger.getLogger(WorkTypeServiceImpl.class);

    @Override
    public List<WorkType> allWorkTypes() throws ServiceException {

        try {
            final List<WorkType> allWorkTypes = workTypeDao.findAllWorkTypes();
            return allWorkTypes;
        } catch (DaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }
}
