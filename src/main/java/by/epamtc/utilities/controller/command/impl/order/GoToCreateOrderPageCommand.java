package by.epamtc.utilities.controller.command.impl.order;

import by.epamtc.utilities.controller.command.Command;
import by.epamtc.utilities.entity.Unit;
import by.epamtc.utilities.entity.WorkType;
import by.epamtc.utilities.service.ServiceFactory;
import by.epamtc.utilities.service.UnitService;
import by.epamtc.utilities.service.WorkTypeService;
import by.epamtc.utilities.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToCreateOrderPageCommand implements Command {
    private final UnitService unitService = ServiceFactory.getInstance().getUnitService();
    private final WorkTypeService workTypeService = ServiceFactory.getInstance().getWorkTypeService();
    private final Logger log = Logger.getLogger(GoToCreateOrderPageCommand.class);

    private static final String ATTRIBUTE_WORK_TYPES_LIST = "workTypes";
    private static final String ATTRIBUTE_UNITS_LIST = "units";



    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final List<Unit> units = unitService.getAllUnits();
            final List<WorkType> workTypes = workTypeService.allWorkTypes();

            request.setAttribute(ATTRIBUTE_UNITS_LIST, units);
            request.setAttribute(ATTRIBUTE_WORK_TYPES_LIST, workTypes);


        } catch (ServiceException e) {
            log.error(e);
        }


        request.getRequestDispatcher("/WEB-INF/jsp/addOrderPage.jsp").forward(request, response);
    }
}
