package by.epamtc.utilities.controller.command.impl.profile;

import by.epamtc.utilities.controller.command.Command;
import by.epamtc.utilities.dao.DaoFactory;
import by.epamtc.utilities.dao.UserDao;
import by.epamtc.utilities.dao.exception.DaoException;
import by.epamtc.utilities.entity.User;
import by.epamtc.utilities.entity.UserProfile;
import by.epamtc.utilities.service.ServiceFactory;
import by.epamtc.utilities.service.UserService;
import by.epamtc.utilities.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToProfileCommand implements Command {
    private final UserService userService = ServiceFactory.getInstance().getUserService();

    private final static String ATTRIBUTE_USER = "user";
    private final static String ATTRIBUTE_USER_PROFILE = "userProfile";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute(ATTRIBUTE_USER);


        try {
            UserProfile userProfile = userService.getUserProfile(user.getId());

            request.setAttribute(ATTRIBUTE_USER_PROFILE, userProfile);
        } catch (ServiceException e) {
            //TODO error
            e.printStackTrace();
        }

        request.getRequestDispatcher("/WEB-INF/jsp/userProfile.jsp").forward(request, response);
    }
}
