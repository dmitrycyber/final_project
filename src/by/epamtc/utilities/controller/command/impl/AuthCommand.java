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

public class AuthCommand implements Command {
    private final static String LOGIN = "login";
    private final static String PASSWORD = "password";
    
    private final static String ATTRIBUTE_USER = "user";
    private final static String ATTRIBUTE_WRONG_DATA = "Wrong_Data";

    private final static String MAIN_PAGE_URL = "Controller?command=to_main_page";
    private final static String LOGIN_PAGE_URL = "Controller?command=to_login_page";
	
	
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        HttpSession session = request.getSession();
        User user;

        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        
        System.out.println("login " + login);
        System.out.println("password " + password);

        try {
        	AuthData authData = new AuthData();
        	authData.setLogin(login);
        	authData.setPassword(password);
        	
            user = userService.login(authData);
            
            System.out.println("CONTROLLER USER " + user);
            
            
            session.setAttribute(ATTRIBUTE_USER, user);
            response.sendRedirect(MAIN_PAGE_URL);
//        } catch (ServiceUserNotFoundException e) {
//            log.info("Trying authorize with incorrect data");
//            session.setAttribute(ATTRIBUTE_WRONG_DATA, ATTRIBUTE_WRONG_DATA);
//            resp.sendRedirect(GO_TO_LOGIN_PAGE);
        } catch (ServiceException e) {
        	// redirect to error page
        }
		
	}
	

}