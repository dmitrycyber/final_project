package by.epamtc.utilities.service.impl;

import by.epamtc.utilities.dao.DaoFactory;
import by.epamtc.utilities.dao.UnitDao;
import by.epamtc.utilities.dao.exception.DaoException;
import by.epamtc.utilities.entity.Unit;
import by.epamtc.utilities.service.UnitService;
import by.epamtc.utilities.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;

public class UnitServiceImpl implements UnitService {
    private final UnitDao unitDao = DaoFactory.getInstance().getUnitDao();
    private final Logger log = Logger.getLogger(UserServiceImpl.class);

    @Override
    public List<Unit> findAllUnits() throws ServiceException {
        try {
            final List<Unit> allUnits = unitDao.findAllUnits();
            return allUnits;
        } catch (DaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }
}
