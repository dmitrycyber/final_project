package by.epamtc.utilities.controller.command.impl.registration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.utilities.controller.command.Command;
import by.epamtc.utilities.entity.RegistrationData;
import by.epamtc.utilities.service.ServiceFactory;
import by.epamtc.utilities.service.UserService;
import by.epamtc.utilities.service.exception.ServiceException;
import by.epamtc.utilities.util.Status;
import by.epamtc.utilities.util.Wrapper;
import org.apache.log4j.Logger;

public class RegistrationCommand implements Command {
    private final static ServiceFactory factory = ServiceFactory.getInstance();
    private final static UserService service = factory.getUserService();
    private final Logger log = Logger.getLogger(RegistrationCommand.class);

    private final static String NAME = "name";
    private final static String SURNAME = "surname";
    private final static String LOGIN = "login";
    private final static String PASSWORD = "password";
    private final static String PHONE_NUMBER = "phoneNumber";
    private final static String STREET = "street";
    private final static String HOUSE = "house";
    private final static String FLAT = "flat";
    private final static String BUILDING = "building";

    private final static String USER_EXIST = "userExist";
    private final static String LOGIN_PAGE_URL = "MainController?command=go_to_auth_page";
    private final static String REGISTRATION_PAGE_URL = "MainController?command=go_to_registration_page";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session;

        session = request.getSession();

        RegistrationData registrationData = new RegistrationData.Builder()
                .name(request.getParameter(NAME))
                .surname(request.getParameter(SURNAME))
                .login(request.getParameter(LOGIN))
                .password(request.getParameter(PASSWORD))
                .phoneNumber(request.getParameter(PHONE_NUMBER))
                .street(request.getParameter(STREET))
                .house(Integer.parseInt(request.getParameter(HOUSE)))
                .flat(Integer.parseInt(request.getParameter(FLAT)))
                .building(request.getParameter(BUILDING)).build();

        try {
            Wrapper<Object> registration = service.registration(registrationData);
            session.removeAttribute(USER_EXIST);

            if (registration.getStatus().equals(Status.LOGIN_OCCUPIED)) {
                session.setAttribute(USER_EXIST, true);
                response.sendRedirect(REGISTRATION_PAGE_URL);
            }
            else{
                response.sendRedirect(LOGIN_PAGE_URL);
            }


        } catch (ServiceException e) {
            log.error("Something wrong", e);
        }

    }

}
