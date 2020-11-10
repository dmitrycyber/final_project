package by.epamtc.utilities.controller.command.impl.profile;

import by.epamtc.utilities.controller.command.Command;
import by.epamtc.utilities.entity.User;
import by.epamtc.utilities.entity.UserProfile;
import by.epamtc.utilities.service.ServiceFactory;
import by.epamtc.utilities.service.UserService;
import by.epamtc.utilities.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToProfileCommand implements Command {
    private final UserService userService = ServiceFactory.getInstance().getUserService();
    private final Logger log = Logger.getLogger(GoToProfileCommand.class);

    private final static String ATTRIBUTE_USER = "user";

    private final static String ATTRIBUTE_USER_PROFILE = "userProfile";

    private final static String PARAMETER_ADMIN_ID = "adminId";

    private final static String ERROR_PAGE_URL = "MainController?command=error_page";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute(ATTRIBUTE_USER);
        long userId = user.getId();

        String stringAdminId = request.getParameter(PARAMETER_ADMIN_ID);

        if (stringAdminId != null){
            userId = Long.parseLong(stringAdminId);
        }

        try {
            UserProfile userProfile = userService.findUserProfile(userId);

            request.setAttribute(ATTRIBUTE_USER_PROFILE, userProfile);
        } catch (ServiceException e) {
            log.error(e);
            response.sendRedirect(ERROR_PAGE_URL);
        }

        request.getRequestDispatcher("/WEB-INF/jsp/userProfile.jsp").forward(request, response);
    }
}
