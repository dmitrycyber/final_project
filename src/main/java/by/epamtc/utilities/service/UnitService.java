package by.epamtc.utilities.service;

import by.epamtc.utilities.entity.Unit;
import by.epamtc.utilities.service.exception.ServiceException;

import java.util.List;

public interface UnitService {
    List<Unit> findAllUnits() throws ServiceException;
}
