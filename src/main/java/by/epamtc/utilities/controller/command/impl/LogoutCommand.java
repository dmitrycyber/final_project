package by.epamtc.utilities.controller.command.impl;

import by.epamtc.utilities.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutCommand implements Command {
    private final static String MAIN_PAGE = "MainController?command=go_to_main_page";
    private final static String SESSION_ATTRIBUTE_USER = "user";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute(SESSION_ATTRIBUTE_USER);
        response.sendRedirect(MAIN_PAGE);
    }
}
