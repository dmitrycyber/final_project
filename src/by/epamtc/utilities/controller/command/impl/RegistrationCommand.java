package by.epamtc.utilities.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.utilities.controller.command.Command;
import by.epamtc.utilities.entity.RegData;
import by.epamtc.utilities.service.ServiceFactory;
import by.epamtc.utilities.service.UserService;
import by.epamtc.utilities.service.exception.ServiceException;
import by.epamtc.utilities.util.Status;
import by.epamtc.utilities.util.Wrapper;

public class RegistrationCommand implements Command {
	private final static ServiceFactory factory = ServiceFactory.getInstance();
	private final static UserService service = factory.getUserService();

	private final static String NAME = "name";
	private final static String SURNAME = "surname";
	private final static String LOGIN = "login";
	private final static String PASSWORD = "password";
	private final static String STREET = "street";
	private final static String HOUSE = "house";
	private final static String FLAT = "flat";
	private final static String BUILDING = "building";

	private final static String ATTRIBUTE_USER_EXIST = "user_exist";
	private final static String GO_TO_LOGIN_PAGE = "Controller?command=to_login_page";
	private final static String GO_TO_REGISTRATION_PAGE = "Controller?command=to_registration_page";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session;
		
        session = request.getSession();

        RegData regData = new RegData.Builder()
        	.name(request.getParameter(NAME))
        	.surname(request.getParameter(SURNAME))
        	.login(request.getParameter(LOGIN))
        	.password(request.getParameter(PASSWORD))
        	.street(request.getParameter(STREET))
        	.house(Integer.parseInt(request.getParameter(HOUSE)))
        	.flat(Integer.parseInt(request.getParameter(FLAT)))
        	.building(request.getParameter(BUILDING)).build();
        
        
        try {
            Wrapper<Object> registration = service.registration(regData);
            
            if (registration.getStatus().equals(Status.LOGIN_OCCUPIED)) {
            	System.out.println("login occupied");
            	response.sendRedirect(GO_TO_REGISTRATION_PAGE);
                session.setAttribute(ATTRIBUTE_USER_EXIST, ATTRIBUTE_USER_EXIST);
            }
            
            session.removeAttribute(ATTRIBUTE_USER_EXIST);
            response.sendRedirect(GO_TO_LOGIN_PAGE);
        
        } catch (ServiceException e) {
            // error
        }
		
	}

}
