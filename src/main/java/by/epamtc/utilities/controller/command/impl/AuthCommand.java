package by.epamtc.utilities.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.utilities.controller.command.Command;
import by.epamtc.utilities.entity.AuthData;
import by.epamtc.utilities.entity.User;
import by.epamtc.utilities.service.ServiceFactory;
import by.epamtc.utilities.service.UserService;
import by.epamtc.utilities.service.exception.ServiceException;
import org.apache.log4j.Logger;

public class AuthCommand implements Command {
    private final static Logger log = Logger.getLogger(AuthCommand.class);

    private final static String LOGIN = "login";
    private final static String PASSWORD = "password";
    
    private final static String SESSION_ATTRIBUTE_USER = "user";

    private final static String INVALID_DATA = "invalidData";
    private final static String MAIN_PAGE_URL = "MainController?command=go_to_auth_page";
    private final static String CABINET_PAGE_URL = "MainController?command=go_to_cabinet_page";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        HttpSession session = request.getSession();
        User user;

        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        try {
        	AuthData authData = new AuthData();
        	authData.setLogin(login);
        	authData.setPassword(password);
            session.removeAttribute(INVALID_DATA);
            user = userService.login(authData);

            session.setAttribute(SESSION_ATTRIBUTE_USER, user);

            response.sendRedirect(CABINET_PAGE_URL);
        } catch (ServiceException e) {
            session.setAttribute(INVALID_DATA, true);
            response.sendRedirect(MAIN_PAGE_URL);
        }
		
	}
	

}
