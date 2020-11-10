package by.epamtc.utilities.controller.command.impl.plan;

import by.epamtc.utilities.controller.command.Command;
import by.epamtc.utilities.entity.Order;
import by.epamtc.utilities.entity.UserProfile;
import by.epamtc.utilities.service.OrderService;
import by.epamtc.utilities.service.ServiceFactory;
import by.epamtc.utilities.service.UserService;
import by.epamtc.utilities.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class GoToWorkPlaneAddNoteCommand implements Command {
    private final Logger log = Logger.getLogger(GoToWorkPlaneAddNoteCommand.class);
    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();
    private final UserService userService = ServiceFactory.getInstance().getUserService();

    private final static String PARAMETER_ORDER_ID = "orderId";
    private final static String ATTRIBUTE_ORDER = "order";
    private final static String ATTRIBUTE_EMPLOYEES_BY_POSITIONS = "employeeMap";
    private final static String ERROR_PAGE_URL = "MainController?command=error_page";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final long orderId = Long.parseLong(request.getParameter(PARAMETER_ORDER_ID));

        try {
            final Order orderById = orderService.findOrderById(orderId);

            final Map<String, List<UserProfile>> employeesByPositions = userService.findEmployeesByPositions();

            request.setAttribute(ATTRIBUTE_ORDER, orderById);

            request.setAttribute(ATTRIBUTE_EMPLOYEES_BY_POSITIONS, employeesByPositions);

        } catch (ServiceException e) {
            log.error(e);
            response.sendRedirect(ERROR_PAGE_URL);
        }

        request.getRequestDispatcher("/WEB-INF/jsp/createNotePage.jsp").forward(request, response);
    }
}
