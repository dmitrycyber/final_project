package by.epamtc.utilities.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.utilities.controller.command.Command;
import org.apache.log4j.Logger;

public class GoToMainPageCommand implements Command {
	private static final Logger log = Logger.getLogger(GoToMainPageCommand.class);


	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp").forward(request, response);
	}

}
