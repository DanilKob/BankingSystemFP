package controller.command;

import controller.PagesName;
import controller.Parameters;
import controller.dto.RegistrationFormDto;
import controller.utility.IOHandler;
import controller.utility.Languages;
import controller.utility.RegexKeys;
import controller.utility.RolesUtility;


import model.exception.NotUniqueException;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command{

    UserService UserService = new UserService();
    //IOHandler IOHandler = new IOHandler();
    RolesUtility RolesUtility = new RolesUtility();

    @Override
    public String execute(HttpServletRequest request) {

        //todo ask about cast to String
        // todo IllegalArgumentException
        Languages language = IOHandler.getLanguageFromRequest(request);

        String firstName = request.getParameter(Parameters.FIRST_NAME);
        String lastName = request.getParameter(Parameters.LAST_NAME);
        String middleName = request.getParameter(Parameters.MIDDLE_NAME);
        String login = request.getParameter(Parameters.LOGIN);
        String password = request.getParameter(Parameters.PASSWORD);

        boolean isFirstNameCorrect = IOHandler.checkInputByRegex(firstName,RegexKeys.FIRST_NAME_REGEX,language);
        boolean isLastNameCorrect = IOHandler.checkInputByRegex(lastName,RegexKeys.LAST_NAME_REGEX,language);
        boolean isMiddleNameCorrect = IOHandler.checkInputByRegex(middleName,RegexKeys.MIDDLE_NAME_REGEX,language);

        boolean isLoginCorrect = IOHandler.checkInputByRegex(login,RegexKeys.LOGIN_REGEX,Languages.ENG);
        boolean isPasswordCorrect = IOHandler.checkInputByRegex(password,RegexKeys.PASSWORD_REGEX,Languages.ENG);

        System.out.println("isFirstNameCorrect = " + isFirstNameCorrect);
        System.out.println("isLastNameCorrect = " + isLastNameCorrect);
        System.out.println("isMiddleNameCorrect = " + isMiddleNameCorrect);
        System.out.println("isLoginCorrect = " + isLoginCorrect);
        System.out.println("isPasswordCorrect = " + isPasswordCorrect);


        if(isInputUncorrect(isFirstNameCorrect,isLastNameCorrect,isMiddleNameCorrect,isLoginCorrect,isPasswordCorrect)){
            System.out.println("Input is uncorrect");
            IOHandler.setRegistrationErrorMassageToReguest(request,isFirstNameCorrect,isLastNameCorrect,
                    isMiddleNameCorrect,isLoginCorrect,isPasswordCorrect,language);
            return PagesName.REGISTRATION;
        }

        RegistrationFormDto registrationFormDto = new RegistrationFormDto.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setMiddleName(middleName)
                .setLogin(login)
                .setPassword(password)
                .build();

        try {
            model.service.UserService.registerUser(registrationFormDto);
            return PagesName.LOGIN_PAGE;
        }catch (NotUniqueException e) {
            IOHandler.setLoginAlreadyExistMessageToRequest(request,language);
            return PagesName.REGISTRATION;
        }
    }

    private boolean isInputUncorrect(boolean isFirstNameCorrect, boolean isLastNameCorrect,
                                     boolean isMiddleNameCorrect, boolean isLoginCorrect, boolean isPasswordCorrect){
        // todo debug mode. Must be uncommented
        return !isFirstNameCorrect||!isLastNameCorrect||!isMiddleNameCorrect||!isLoginCorrect||!isPasswordCorrect;
        //return false;
    }
}
