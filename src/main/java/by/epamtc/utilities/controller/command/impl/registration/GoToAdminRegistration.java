package by.epamtc.utilities.controller.command.impl.registration;

import by.epamtc.utilities.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToAdminRegistration implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/adminRegistration.jsp").forward(request, response);
    }
}
