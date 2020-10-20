package by.epamtc.utilities.controller.command.impl.registration;

import by.epamtc.utilities.controller.command.Command;
import by.epamtc.utilities.dao.impl.AdminDaoImpl;
import by.epamtc.utilities.entity.User;
import by.epamtc.utilities.service.AdminService;
import by.epamtc.utilities.service.ServiceFactory;
import by.epamtc.utilities.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToAdminListPageCommand implements Command {
    private final AdminService adminService = ServiceFactory.getInstance().getAdminService();
    private final Logger log = Logger.getLogger(GoToAdminListPageCommand.class);

    private static final String ADMIN_ROLE = "admin";

    private final static String ATTRIBUTE_ADMIN_LIST = "adminList";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final List<User> admins = adminService.getAdmins(ADMIN_ROLE);

            request.setAttribute(ATTRIBUTE_ADMIN_LIST, admins);


        } catch (ServiceException e) {
            log.error(e);
        }

        request.getRequestDispatcher("/WEB-INF/jsp/adminListPage.jsp").forward(request, response);
    }
}
