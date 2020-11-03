package by.epamtc.utilities.controller.command.impl.order;

import by.epamtc.utilities.controller.command.Command;
import by.epamtc.utilities.entity.Order;
import by.epamtc.utilities.entity.User;
import by.epamtc.utilities.service.OrderService;
import by.epamtc.utilities.service.ServiceFactory;
import by.epamtc.utilities.service.exception.ServiceException;
import by.epamtc.utilities.util.Status;
import by.epamtc.utilities.util.Wrapper;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

public class CreateOrderCommand implements Command {
    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();
    private final Logger log = Logger.getLogger(CreateOrderCommand.class);

    private final static String WORK_TYPE_ID = "workTypeId";
    private final static String SCALE_VALUE = "scaleValue";
    private final static String SCALE_UNIT_ID = "scaleUnitsId";
    private final static String START_DATE = "startDate";
    private final static String END_DATE = "endDate";
    private final static String IS_SEVERAL = "isSeveral";
    private final static String DESCRIPTION = "description";

    private final static String CREATE_ORDER_ERROR = "createOrderError";
    private final static String CREATE_ORDERS_PAGE_URL = "MainController?command=go_to_orders_page";

    private final static String ATTRIBUTE_USER = "user";



    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final User user = (User) request.getSession().getAttribute(ATTRIBUTE_USER);


        final Order order = new Order.Builder()
                .workTypeId(Integer.parseInt(request.getParameter(WORK_TYPE_ID)))
                .userId((int) user.getId())
                .scaleUnitsId(Integer.parseInt(request.getParameter(SCALE_UNIT_ID)))
                .scaleValue(Double.parseDouble(request.getParameter(SCALE_VALUE)))
                .startDate(new Timestamp(new DateTime(request.getParameter(START_DATE)).getMillis()))
                .endDate(new Timestamp(new DateTime(request.getParameter(END_DATE)).getMillis()))
                .isSeveral(request.getParameter(IS_SEVERAL) != null)
                .description(request.getParameter(DESCRIPTION)).build();

        try {
            final Wrapper<Object> objectWrapper = orderService.addOrder(order);
            if (objectWrapper.getStatus().equals(Status.SUCCESSFULL)){
                response.sendRedirect(CREATE_ORDERS_PAGE_URL);
            }

        } catch (ServiceException e) {
            log.error(e);
        }
    }
}
