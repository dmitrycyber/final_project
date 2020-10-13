package by.epamtc.utilities.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epamtc.utilities.controller.command.impl.*;
import by.epamtc.utilities.controller.command.impl.profile.GoToProfileCommand;

public class CommandProvider {
	
	private final Map<CommandName, Command> commandMap = new HashMap<>();
	
	public CommandProvider() {
		commandMap.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPageCommand());
		commandMap.put(CommandName.GO_TO_AUTH_PAGE, new GoToAuthPageCommand());
		commandMap.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPageCommand());
		commandMap.put(CommandName.AUTH, new AuthCommand());
		commandMap.put(CommandName.GO_TO_CABINET_PAGE, new GoToCabinetPage());
		commandMap.put(CommandName.REGISTRATION, new RegistrationCommand());
		commandMap.put(CommandName.CHANGE_LANGUAGE, new ChangeLanguageCommand());
		commandMap.put(CommandName.PROFILE, new GoToProfileCommand());
	}
	
	public Command getCommand(String commandName) {
		return commandMap.get(CommandName.valueOf(commandName.toUpperCase()));
	}
	
	

}
