package by.epamtc.utilities.controller.command.impl.registration;

import by.epamtc.utilities.controller.command.Command;
import by.epamtc.utilities.entity.UserProfile;
import by.epamtc.utilities.service.AdminService;
import by.epamtc.utilities.service.ServiceFactory;
import by.epamtc.utilities.service.UserService;
import by.epamtc.utilities.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToAdminListPageCommand implements Command {
    private final AdminService adminService = ServiceFactory.getInstance().getAdminService();
    private final UserService userService = ServiceFactory.getInstance().getUserService();
    private final Logger log = Logger.getLogger(GoToAdminListPageCommand.class);

    private static final String ADMIN_ROLE = "admin";

    private final static String ATTRIBUTE_ADMIN_LIST = "adminList";

    private final static String ERROR_PAGE_URL = "MainController?command=error_page";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final List<UserProfile> allAdmins = userService.findAllAdmins();

            request.setAttribute(ATTRIBUTE_ADMIN_LIST, allAdmins);

        } catch (ServiceException e) {
            log.error(e);
            response.sendRedirect(ERROR_PAGE_URL);
        }

        request.getRequestDispatcher("/WEB-INF/jsp/adminListPage.jsp").forward(request, response);
    }
}
