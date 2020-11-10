package by.epamtc.utilities.service.impl;

import by.epamtc.utilities.dao.AdminDao;
import by.epamtc.utilities.dao.DaoFactory;
import by.epamtc.utilities.dao.exception.DaoException;
import by.epamtc.utilities.entity.User;
import by.epamtc.utilities.service.AdminService;
import by.epamtc.utilities.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    private final AdminDao adminDao = DaoFactory.getInstance().getAdminDao();
    private final Logger log = Logger.getLogger(AdminServiceImpl.class);

    @Override
    public List<User> findAdmins(String role) throws ServiceException {
        try {
            return adminDao.findUserByRole(role);
        } catch (DaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }
}
