package by.epamtc.utilities.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epamtc.utilities.controller.command.impl.*;
import by.epamtc.utilities.controller.command.impl.employee.GoToEmployeePageCommand;
import by.epamtc.utilities.controller.command.impl.order.CreateOrderCommand;
import by.epamtc.utilities.controller.command.impl.order.GoToCreateOrderPageCommand;
import by.epamtc.utilities.controller.command.impl.order.GoToOrdersPageCommand;
import by.epamtc.utilities.controller.command.impl.plan.CreateNoteCommand;
import by.epamtc.utilities.controller.command.impl.plan.GoToWorkPlanListCommand;
import by.epamtc.utilities.controller.command.impl.plan.GoToWorkPlaneAddNoteCommand;
import by.epamtc.utilities.controller.command.impl.profile.ChangeProfileCommand;
import by.epamtc.utilities.controller.command.impl.profile.GoToProfileCommand;
import by.epamtc.utilities.controller.command.impl.registration.*;

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
		commandMap.put(CommandName.LOGOUT, new LogoutCommand());
		commandMap.put(CommandName.CHANGE_PROFILE, new ChangeProfileCommand());
		commandMap.put(CommandName.GO_TO_ADMIN_REGISTRATION, new GoToAdminRegistration());
		commandMap.put(CommandName.GO_TO_ADMIN_LIST_PAGE, new GoToAdminListPageCommand());
		commandMap.put(CommandName.GO_TO_ORDERS_PAGE, new GoToOrdersPageCommand());
		commandMap.put(CommandName.GO_TO_CREATE_ORDER_PAGE, new GoToCreateOrderPageCommand());
		commandMap.put(CommandName.CREATE_ORDER, new CreateOrderCommand());
		commandMap.put(CommandName.GO_TO_CREATE_NOTE_PAGE, new GoToWorkPlaneAddNoteCommand());
		commandMap.put(CommandName.CREATE_NOTE, new CreateNoteCommand());
		commandMap.put(CommandName.GO_TO_EMPLOYEES_PAGE, new GoToEmployeePageCommand());
		commandMap.put(CommandName.GO_TO_WORK_PLAN_LIST_PAGE, new GoToWorkPlanListCommand());
		commandMap.put(CommandName.ERROR_PAGE, new GoToErrorPageCommand());

	}
	
	public Command getCommand(String commandName) {
		return commandMap.get(CommandName.valueOf(commandName.toUpperCase()));
	}
	
	

}
