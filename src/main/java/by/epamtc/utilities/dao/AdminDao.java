package by.epamtc.utilities.dao;

import by.epamtc.utilities.dao.exception.DaoException;
import by.epamtc.utilities.entity.User;
import by.epamtc.utilities.util.Wrapper;

import java.util.List;

public interface AdminDao {
    List<User> findUserByRole(String role) throws DaoException;
}
