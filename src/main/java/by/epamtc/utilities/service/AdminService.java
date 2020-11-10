package by.epamtc.utilities.service;

import by.epamtc.utilities.entity.User;
import by.epamtc.utilities.service.exception.ServiceException;

import java.util.List;

public interface AdminService {
    List<User> findAdmins(String role) throws ServiceException;
}
