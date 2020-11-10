package by.epamtc.utilities.dao;

import by.epamtc.utilities.dao.exception.DaoException;
import by.epamtc.utilities.entity.AuthData;
import by.epamtc.utilities.entity.RegistrationData;
import by.epamtc.utilities.entity.User;
import by.epamtc.utilities.entity.UserProfile;
import by.epamtc.utilities.util.Wrapper;

import java.util.List;

/**
 * User dao interface
 *
 */
public interface UserDao {


    /**
     * Auth user
     *
     * @param authorizationData    model with user login and password
     * @return User object of user
     * @throws DaoException if something wos wrong in database activity
     */
    User authorizeUser(AuthData authorizationData) throws DaoException;

    /**
     * Registration user
     *
     * @param registrationData  user registration data
     * @return object with query status and data
     * @throws DaoException if something wos wrong in database activity
     */
    Wrapper<Object> saveUser(RegistrationData registrationData) throws DaoException;

    /**
     * Get user profile
     *
     * @param userId userId
     * @return Userprofile model
     * @throws DaoException if something wos wrong in database activity
     */
    UserProfile findUserProfile(long userId) throws DaoException;

    /**
     * Edit user profile
     *
     * @param userProfile user profile with set data
     * @return object with query status and data
     * @throws DaoException if something wos wrong in database activity
     */
    Wrapper<Object> updateUserProfile(UserProfile userProfile) throws DaoException;

    /**
     * Find all users by role
     *
     * @param role role constants
     * @return list of find user profiles, if no profiles find - returned empty list
     * @throws DaoException if something wos wrong in database activity
     */
    List<UserProfile> findAllByRole(String role) throws DaoException;

    /**
     * Find all admins
     *
     * @return list of find admin profiles, if no profiles find - returned empty list
     * @throws DaoException if something wos wrong in database activity
     */
    List<UserProfile> findAllAdmins() throws DaoException;

}
