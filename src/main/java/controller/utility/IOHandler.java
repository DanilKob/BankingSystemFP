package controller.utility;


import controller.Parameters;

import javax.servlet.http.HttpServletRequest;

public class IOHandler {

    public static Languages getLanguageFromRequest(HttpServletRequest request){
        String language = ((String)request.getSession().getAttribute(Parameters.LANGUAGE)).toUpperCase();
        return Languages.valueOf(language);
    }

    public static boolean isInputEmpty(String input){
        return ((input == null) || (input.isEmpty()));
    }

    public static boolean checkInputByRegex(String inputWord, String regexPropertyKey, Languages language){
        if(isInputEmpty(inputWord)) {
            return false;
        }
        return inputWord.matches(language.getRegexProperties().getProperty(regexPropertyKey));
    }

    public static String getTextByKeyAndLanguage(String textPropertyKey, Languages language){
        return language.getTextProperties().getProperty(textPropertyKey);
    }



    public static void setLoginAlreadyExistMessageToRequest(HttpServletRequest request,Languages language){
        request.setAttribute(Parameters.LOGIN_ERROR, getTextByKeyAndLanguage(TextKeys.LOGIN_ALREADY_EXIST,language));
    }

    public static void setLoginAlreadyInSystemMessage(HttpServletRequest request, Languages language){
        request.setAttribute(Parameters.LOGIN_ERROR, getTextByKeyAndLanguage(TextKeys.LOGIN_ALREADY_IN_SYSTEM,language));
    }

    public static void setLoginPasswordMistakeMessage(HttpServletRequest request,Languages language){
        request.setAttribute(Parameters.LOGIN_ERROR, getTextByKeyAndLanguage(TextKeys.LOGIN_OR_PASSWORD_MISTAKE,language));
    }

    public static void setRegistrationErrorMassageToReguest(HttpServletRequest request,
                                                            boolean isFirstNameCorrect, boolean isLastNameCorrect,
                                                            boolean isMiddleNameCorrect, boolean isLoginCorrect,
                                                            boolean isPasswordCorrect,
                                                            Languages language){
        if(!isFirstNameCorrect){
            request.setAttribute(Parameters.FIRST_NAME_ERROR, getTextByKeyAndLanguage(TextKeys.BAD_FIRST_NAME,language));
        }
        if(!isLastNameCorrect){
            request.setAttribute(Parameters.LAST_NAME_ERROR, getTextByKeyAndLanguage(TextKeys.BAD_LAST_NAME,language));
        }
        if(!isMiddleNameCorrect){
            request.setAttribute(Parameters.MIDDLE_NAME_ERROR, getTextByKeyAndLanguage(TextKeys.BAD_MIDDLE_NAME,language));
        }
        if(!isLoginCorrect){
            request.setAttribute(Parameters.LOGIN_ERROR, getTextByKeyAndLanguage(TextKeys.BAD_LOGIN,language));
        }
        if(!isPasswordCorrect){
            request.setAttribute(Parameters.PASSWORD_ERROR, getTextByKeyAndLanguage(TextKeys.BAD_PASSWORD,language));
        }
    }

    public static void setLoginErrorMessagesToRequest(HttpServletRequest request,
                                                      boolean isLoginCorrect, boolean isPasswordCorrect,
                                                      Languages language){
        if(!isLoginCorrect){
            request.setAttribute(Parameters.LOGIN_ERROR, getTextByKeyAndLanguage(TextKeys.BAD_LOGIN,language));
        }
        if(!isPasswordCorrect){
            request.setAttribute(Parameters.PASSWORD_ERROR, getTextByKeyAndLanguage(TextKeys.BAD_PASSWORD,language));
        }
    }

}
