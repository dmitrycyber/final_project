package by.epamtc.utilities.controller.command.impl.plan;

import by.epamtc.utilities.controller.command.Command;
import by.epamtc.utilities.dao.impl.WorkPlanDaoImpl;
import by.epamtc.utilities.entity.Note;
import by.epamtc.utilities.entity.User;
import by.epamtc.utilities.service.ServiceFactory;
import by.epamtc.utilities.service.WorkPlanService;
import by.epamtc.utilities.service.exception.ServiceException;
import by.epamtc.utilities.util.Status;
import by.epamtc.utilities.util.Wrapper;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CreateNoteCommand implements Command {
    private final WorkPlanService workPlanService = ServiceFactory.getInstance().getWorkPlanService();
    private final Logger log = Logger.getLogger(CreateNoteCommand.class);

    private static final String ELECTRICIANS = "electricians";
    private static final String PLUMBERS = "plumbers";
    private static final String FOREMANS = "foremans";
    private static final String ACCOUNTANTS = "accountants";
    private static final String DISPATCHERS = "dispatchers";
    private static final String SYSTEM_ADMINS = "systemAdmins";
    private static final String CLEANERS = "cleaners";

    private final static String START_DATE = "startDate";
    private final static String END_DATE = "endDate";

    private final static String ORDERS_PAGE_URL = "MainController?command=go_to_orders_page";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        final User user = (User) session.getAttribute("user");
        final long dispatcherId = user.getId();

        final List<Long> employees = formListSelectedEmployees(request);

        try {
            final int orderId = Integer.parseInt(request.getParameter("orderId"));
            Note note;

            if (request.getParameter("isDecline") != null){
                note = new Note.Builder()
                        .orderId(orderId)
                        .isDecline(true).build();
            }
            else {
                note = new Note.Builder()
                        .orderId(orderId)
                        .employeeIds(employees)
                        .dispatcherId(dispatcherId)
                        .workTypeId(Integer.parseInt(request.getParameter("workTypeId")))
                        .startDate(new Timestamp(new DateTime(request.getParameter(START_DATE)).getMillis()))
                        .endDate(new Timestamp(new DateTime(request.getParameter(END_DATE)).getMillis()))
                        .comment(request.getParameter("comment"))
                        .isDecline(false).build();
            }
            final Wrapper<Object> objectWrapper = workPlanService.addNewNote(note);

            if (objectWrapper.getStatus().equals(Status.SUCCESSFULL)){
                response.sendRedirect(ORDERS_PAGE_URL);
            }

        } catch (ServiceException e) {
            log.error(e);
        }
    }

    private List<Long> formListSelectedEmployees(HttpServletRequest request) {
        List<String[]> employees = new ArrayList<>();

        employees.add(request.getParameterValues(ELECTRICIANS));
        employees.add(request.getParameterValues(PLUMBERS));
        employees.add(request.getParameterValues(FOREMANS));
        employees.add(request.getParameterValues(ACCOUNTANTS));
        employees.add(request.getParameterValues(DISPATCHERS));
        employees.add(request.getParameterValues(SYSTEM_ADMINS));
        employees.add(request.getParameterValues(CLEANERS));
        List<Long> userIds = new ArrayList<>();

        for (String[] empByPosition : employees) {
            if (empByPosition != null) {
                for (String userIdString : empByPosition) {
                    userIds.add(Long.parseLong(userIdString));
                }
            }
        }
        return userIds;
    }
}
