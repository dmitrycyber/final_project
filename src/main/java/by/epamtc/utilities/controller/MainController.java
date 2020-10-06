package by.epamtc.utilities.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.utilities.controller.command.Command;
import by.epamtc.utilities.controller.command.CommandProvider;


public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String PARAMETER_COMMAND = "command";
	private final CommandProvider commandProvider = new CommandProvider();
	
    public MainController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commandName = request.getParameter(PARAMETER_COMMAND);
        Command command = commandProvider.getCommand(commandName.toUpperCase());
        command.execute(request, response);
	}
}
