package controller.command;

import controller.PagesName;
import controller.Parameters;
import controller.utility.IOHandler;
import controller.utility.Languages;
import controller.utility.RegexKeys;
import controller.utility.RolesUtility;
import model.entity.User;
import model.exception.LoginException;
import model.service.GuestService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

public class LogInCommand implements Command {
    // todo best place to keep this class
    GuestService guestService = new GuestService();
    //IOHandler IOHandler = new IOHandler();
    RolesUtility rolesUtility = new RolesUtility();

    @Override
    public String execute(HttpServletRequest request) {

        String login = request.getParameter(Parameters.LOGIN);
        String password = request.getParameter(Parameters.PASSWORD);

        // todo IllegalArgumentException
        Languages language = IOHandler.getLanguageFromRequest(request);


        boolean isLoginCorrect = IOHandler.checkInputByRegex(login,RegexKeys.LOGIN_REGEX,language);
        boolean isPasswordCorrect = IOHandler.checkInputByRegex(password,RegexKeys.PASSWORD_REGEX,language);

        System.out.println("login" + login);
        System.out.println("isLoginCorrect = " + isLoginCorrect);
        System.out.println("isPasswordCorrect =  " + isPasswordCorrect);

        if(isInputDataUncorrect(isLoginCorrect,isPasswordCorrect)){
            IOHandler.setLoginErrorMessagesToRequest(request,isLoginCorrect,isPasswordCorrect,language);
            return PagesName.LOGIN_PAGE;
        }

        // todo remove to filter
        if(RolesUtility.isUserAlreadyLogged(request,login)){
            // todo block postman
            //CommandManager.getInstance().getCommand(CommandConstants.LOGOUT_COMMAND).execute(request);
            return PagesName.LOGIN_PAGE;
        }

        try {
            // todo return object User. not just role
            User.ROLE role = guestService.login(new User(login,password));

            RolesUtility.addLoginInServletContext(request,login);
            RolesUtility.addRoleAndLoginInSession(request,role,login);

            // todo if statement has not already accepted, catch exception
            return CommandConstants.REDIRECT+ getHomePageByRole(role);
        } catch (LoginException e) {
            return PagesName.LOGIN_PAGE;
        }
    }

    private boolean isInputDataUncorrect(boolean isLoginCorrect, boolean isPasswordCorrect){
        // todo debug mode. Must be uncommented
        return !isLoginCorrect || !isPasswordCorrect;
    }


    private String getHomePageByRole(User.ROLE role){
        String page;
        switch (role){
            case ADMIN: page = PagesName.ADMIN_HOME_PAGE;
            break;
            case USER: page = PagesName.USER_HOME_PAGE;
            break;
            default: page = PagesName.LOGIN_PAGE;
        }
        return page;
    }
}
