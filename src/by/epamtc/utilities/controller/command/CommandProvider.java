package by.epamtc.utilities.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epamtc.utilities.controller.command.impl.GoToAuthPageCommand;
import by.epamtc.utilities.controller.command.impl.GoToMainPageCommand;
import by.epamtc.utilities.controller.command.impl.GoToRegistrationPageCommand;

public class CommandProvider {
	
	private final Map<CommandName, Command> commandMap = new HashMap<>();
	
	public CommandProvider() {
		commandMap.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPageCommand());
		commandMap.put(CommandName.GO_TO_AUTH_PAGE, new GoToAuthPageCommand());
		commandMap.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPageCommand());
	}
	
	public Command getCommand(String commandName) {
		return commandMap.get(CommandName.valueOf(commandName.toUpperCase()));
	}
	
	

}
