package by.epamtc.utilities.controller.command.impl.order;

import by.epamtc.utilities.controller.command.Command;
import by.epamtc.utilities.dao.impl.UserDaoImpl;
import by.epamtc.utilities.entity.Order;
import by.epamtc.utilities.entity.User;
import by.epamtc.utilities.service.OrderService;
import by.epamtc.utilities.service.ServiceFactory;
import by.epamtc.utilities.service.exception.ServiceException;
import by.epamtc.utilities.util.Status;
import by.epamtc.utilities.util.Wrapper;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToOrderPageCommand implements Command {
    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();
    private final Logger log = Logger.getLogger(GoToOrderPageCommand.class);

    private static final String ATTRIBUTE_USER = "user";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final User user = (User) request.getSession().getAttribute(ATTRIBUTE_USER);

        try {
            final Wrapper<Object> wrapperObject = orderService.getOrderList(user);
            if (!wrapperObject.getStatus().equals(Status.SUCCESSFULL)){
                //TODO redirect to error page
            }
            final List<Order> orders = (List<Order>) wrapperObject.getMessage();

            request.setAttribute("orderList", orders);

            log.info("ORDERS  " + orders);

        } catch (ServiceException e) {
            //TODO redirect to error page
            log.error(e);
        }


        request.getRequestDispatcher("/WEB-INF/jsp/ordersPage.jsp").forward(request, response);
    }
}
