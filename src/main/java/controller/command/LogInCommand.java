package controller.command;

import controller.PagesName;
import controller.Parameters;
import controller.dto.LoginDto;
import controller.utility.IOHandler;
import controller.utility.Languages;
import controller.utility.RegexKeys;
import controller.utility.RolesUtility;

import model.entity.User;
import model.mock.service.GuestService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

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


        boolean isLoginCorrect = IOHandler.checkInputByRegex(login,RegexKeys.LOGIN_REGEX,Languages.ENG);
        boolean isPasswordCorrect = IOHandler.checkInputByRegex(password,RegexKeys.PASSWORD_REGEX,Languages.ENG);

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
            RolesUtility.logoutUser(request);
            System.out.println("USER HAS ALREADY REGISTERED!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            return PagesName.LOGIN_PAGE;
        }

        LoginDto loginDto = new LoginDto(login,password);

        Optional<User> optionalUser = model.service.UserService.login(loginDto);

        if(optionalUser.isPresent()){
            optionalUser.get().setLogin(login);
            RolesUtility.addLoginInServletContext(request,login);
            RolesUtility.addRoleAndLoginInSession(request,optionalUser.get());
            return CommandConstants.REDIRECT + RolesUtility.defineHomePageByRole(optionalUser.get().getRole());
        }else{
            System.out.println("LOGIN ISN'T EXIST");
            return PagesName.LOGIN_PAGE;
        }
        /*
        try {
            // todo return object User. not just role
            //User.ROLE role = guestService.login(new User());

            LoginDto loginDto = new LoginDto(login,password);

            Optional<User> optionalUser = model.service.UserService.login(loginDto);

            if(optionalUser.isPresent()){
                RolesUtility.addLoginInServletContext(request,login);
                RolesUtility.addRoleAndLoginInSession(request,optionalUser.get().getRole(),login);
            }else{
                return PagesName.LOGIN_PAGE;
            }


            // todo if statement has not already accepted, catch exception
            return CommandConstants.REDIRECT+ getHomePageByRole(optionalUser.get().getRole());
        } /*catch (LoginException e) {
            return PagesName.LOGIN_PAGE;
        }
        */
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
