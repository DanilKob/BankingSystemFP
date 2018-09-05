package controller.command;

import controller.PagesName;
import controller.Parameters;
import controller.dto.LoginDto;
import controller.utility.IOHandler;
import controller.utility.Languages;
import controller.utility.RegexKeys;
import controller.utility.RolesUtility;

import model.entity.User;
import model.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


public class LogInCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        String login = request.getParameter(Parameters.LOGIN);
        String password = request.getParameter(Parameters.PASSWORD);
        Languages language = null;
        try{
            language = IOHandler.getLanguageFromRequest(request);
        }catch (IllegalArgumentException e){
            Logger.getLogger(LoggerConstants.DANGER_USER).warn("DANGER USER");
            return PagesName.ERROR;
        }

        boolean isLoginCorrect = IOHandler.checkInputByRegex(login,RegexKeys.LOGIN_REGEX,Languages.ENG);
        boolean isPasswordCorrect = IOHandler.checkInputByRegex(password,RegexKeys.PASSWORD_REGEX,Languages.ENG);

        Logger.getLogger(LogInCommand.class.getName()).debug("login" + login);
        Logger.getLogger(LogInCommand.class.getName()).debug("isLoginCorrect = " + isLoginCorrect);
        Logger.getLogger(LogInCommand.class.getName()).debug("isPasswordCorrect =  " + isPasswordCorrect);


        if(isInputDataUncorrect(isLoginCorrect,isPasswordCorrect)){
            IOHandler.setLoginErrorMessagesToRequest(request,isLoginCorrect,isPasswordCorrect,language);
            return PagesName.LOGIN_PAGE;
        }

        // todo remove to filter
        if(RolesUtility.isUserAlreadyLogged(request,login)){
            RolesUtility.logoutUser(request);
            Logger.getLogger(LogInCommand.class.getName()).debug("User is already logged");
            IOHandler.setLoginAlreadyInSystemMessage(request,language);
            return PagesName.LOGIN_PAGE;
        }

        LoginDto loginDto = new LoginDto(login,password);

        Optional<User> optionalUser = UserService.login(loginDto);

        if(optionalUser.isPresent()){
            optionalUser.get().setLogin(login);
            RolesUtility.addLoginInServletContext(request,login);
            RolesUtility.addRoleAndLoginInSession(request,optionalUser.get());
            Logger.getLogger(LogInCommand.class.getName()).info(login + optionalUser.get().getRole());
            return CommandConstants.REDIRECT + CommandConstants.SET_COMMAND + getHomePageCommand(optionalUser.get().getRole());
        }else{
            Logger.getLogger(LogInCommand.class.getName()).debug("Login or password is not correct");
            IOHandler.setLoginPasswordMistakeMessage(request,language);
            return PagesName.LOGIN_PAGE;
        }
    }

    private boolean isInputDataUncorrect(boolean isLoginCorrect, boolean isPasswordCorrect){
        return !isLoginCorrect || !isPasswordCorrect;
    }


    private String getHomePageCommand(User.ROLE role){
        String page;
        switch (role){
            case USER: page = CommandConstants.USER_HOME_PAGE_COMMAND;
                break;
            case ADMIN: page = CommandConstants.ADMIN_HOME_PAGE_COMMAND;
                break;
            default: page = PagesName.LOGIN_PAGE;
        }
        return page;
    }
}
