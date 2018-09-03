package controller.utility;

import controller.PagesName;
import controller.Parameters;
import model.entity.User;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class RolesUtility {
    public static void addRoleAndLoginInSession(HttpServletRequest request,
                                                User user) {
        HttpSession session = request.getSession();
        // todo add User. NOT JUST LOGIN
        // todo add first name to session scope
        session.setAttribute(Parameters.ROLE, user.getRole());
        session.setAttribute(Parameters.USER,user);
        System.out.println("USER HAS BEEN ADDED TO SESSION. LOGIN :: " + user.getLogin());
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static boolean isUserAlreadyLogged(HttpServletRequest request, String login){
        HashSet<String> loggedUsers = (HashSet<String>) request.getServletContext().getAttribute(Parameters.LOGGED_USERS);
        return loggedUsers.stream().anyMatch(login::equals);
    }

    public static void addLoginInServletContext(HttpServletRequest request, String login){
        HashSet<String> loggedUsers = (HashSet<String>) request.getServletContext().getAttribute(Parameters.LOGGED_USERS);
        loggedUsers.add(login);
        System.out.println("LOGIN HAS BEEN ADDED TO CONTEXT. LOGIN :: " + login);
        //request.getServletContext().setAttribute(Parameters.LOGGED_USERS,loggedUsers);
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void logoutUser(HttpServletRequest request){
        HttpSession session = request.getSession();

        User user = (User)session.getAttribute(Parameters.USER);
        if(user != null){
            String login = user.getLogin();

            HashSet<String> loggedUsers = (HashSet<String>) request.getServletContext().getAttribute(Parameters.LOGGED_USERS);
            loggedUsers.remove(login);
            session.removeAttribute(Parameters.USER);
            System.out.println("REMOVE FROM CONTEXT");
        }

        // todo try to remove this string with setAttribute method
        //request.getServletContext().setAttribute(Parameters.LOGGED_USERS,loggedUsers);

        request.getSession().setAttribute(Parameters.ROLE, User.ROLE.GUEST);
    }


    // todo refactor isUserAlreadyLogged
    /*
    public boolean isUserAlreadyLogged(HttpServletRequest request, String login){
        HashSet<String> loggedUsers = (HashSet<String>) request.getServletContext().getAttribute(Parameters.LOGGED_USERS);

        if(loggedUsers.stream().anyMatch(login::equals)){
            return true;
        }
        loggedUsers.add(login);
        request.getServletContext().setAttribute(Parameters.LOGGED_USERS,loggedUsers);
        return false;
    }
     */
    // todo remove method and default command
    public static String defineHomePageByRole(User.ROLE role){
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
