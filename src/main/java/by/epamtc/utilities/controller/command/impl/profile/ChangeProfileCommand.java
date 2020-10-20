package by.epamtc.utilities.controller.command.impl.profile;

import by.epamtc.utilities.controller.command.Command;
import by.epamtc.utilities.entity.RegistrationData;
import by.epamtc.utilities.entity.UserProfile;
import by.epamtc.utilities.service.ServiceFactory;
import by.epamtc.utilities.service.UserService;
import by.epamtc.utilities.service.exception.ServiceException;
import by.epamtc.utilities.util.Status;
import by.epamtc.utilities.util.Wrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeProfileCommand implements Command {

    private final UserService userService = ServiceFactory.getInstance().getUserService();

    private final String PARAMETER_USER_ID = "userId";
    private final String PARAMETER_NAME = "name";
    private final String PARAMETER_SURNAME = "surname";
    private final String PARAMETER_LOGIN = "login";
    private final String PARAMETER_PHONE = "phoneNumber";
    private final String PARAMETER_STREET = "street";
    private final String PARAMETER_HOUSE = "house";
    private final String PARAMETER_BUILDING = "building";
    private final String PARAMETER_FLAT = "flat";

    private final static String USER_EXIST = "userExist";

    private final static String USER_PROFILE_PAGE = "MainController?command=profile";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        final UserProfile userProfile = new UserProfile.Builder()
                .userId(Long.parseLong(request.getParameter(PARAMETER_USER_ID)))
                .name(request.getParameter(PARAMETER_NAME))
                .surname(request.getParameter(PARAMETER_SURNAME))
                .login(request.getParameter(PARAMETER_LOGIN))
                .phoneNumber(request.getParameter(PARAMETER_PHONE))
                .street(request.getParameter(PARAMETER_STREET))
                .house(Integer.parseInt(request.getParameter(PARAMETER_HOUSE)))
                .flat(Integer.parseInt(request.getParameter(PARAMETER_FLAT)))
                .building(request.getParameter(PARAMETER_BUILDING)).build();

        try {
            final HttpSession session = request.getSession();

            final Wrapper<Object> objectWrapper = userService.editUserProfile(userProfile);
            session.removeAttribute(USER_EXIST);

            if (objectWrapper.getStatus().equals(Status.LOGIN_OCCUPIED)){
                session.setAttribute(USER_EXIST, true);
            }
            response.sendRedirect(USER_PROFILE_PAGE);


        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
