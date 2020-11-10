package by.epamtc.utilities.controller.command.impl.plan;

import by.epamtc.utilities.controller.command.Command;
import by.epamtc.utilities.entity.Note;
import by.epamtc.utilities.entity.User;
import by.epamtc.utilities.service.ServiceFactory;
import by.epamtc.utilities.service.WorkPlanService;
import by.epamtc.utilities.service.exception.ServiceException;
import by.epamtc.utilities.util.RoleConsts;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GoToWorkPlanListCommand implements Command {
    private final Logger log = Logger.getLogger(GoToWorkPlaneAddNoteCommand.class);
    private final WorkPlanService workPlanService = ServiceFactory.getInstance().getWorkPlanService();

    private final static String ATTRIBUTE_NOTES = "noteList";
    private final static String ATTRIBUTE_USER = "user";
    private final static String ERROR_PAGE_URL = "MainController?command=error_page";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final List<Note> allNotes;
            final HttpSession session = request.getSession();
            final User user = (User) session.getAttribute(ATTRIBUTE_USER);

            final String role = user.getRole();

            if (role.equals(RoleConsts.EMPLOYEES)){
                allNotes = workPlanService.findNotesByEmployeeId(user.getId());
            }
            else {
                allNotes = workPlanService.findAllNotes();
            }

            request.setAttribute(ATTRIBUTE_NOTES, allNotes);

        } catch (ServiceException e) {
            log.error(e);
            response.sendRedirect(ERROR_PAGE_URL);
        }


        request.getRequestDispatcher("/WEB-INF/jsp/workPlanListPage.jsp").forward(request, response);
    }
}
