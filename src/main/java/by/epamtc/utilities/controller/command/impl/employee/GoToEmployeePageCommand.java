package by.epamtc.utilities.controller.command.impl.employee;

import by.epamtc.utilities.controller.command.Command;
import by.epamtc.utilities.dao.impl.UserDaoImpl;
import by.epamtc.utilities.entity.UserProfile;
import by.epamtc.utilities.service.ServiceFactory;
import by.epamtc.utilities.service.UserService;
import by.epamtc.utilities.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToEmployeePageCommand implements Command {
    private final UserService userService = ServiceFactory.getInstance().getUserService();
    private final Logger log = Logger.getLogger(GoToEmployeePageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            final List<UserProfile> allEmployees = userService.getAllEmployees();

            request.setAttribute("employeesList", allEmployees);
        } catch (ServiceException e) {
            log.error(e);
        }

        request.getRequestDispatcher("/WEB-INF/jsp/employeesListPage.jsp").forward(request, response);
    }
}
