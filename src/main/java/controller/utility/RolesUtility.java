package controller.utility;

import controller.PagesName;
import controller.Parameters;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class RolesUtility {
    public static void addRoleAndLoginInSession(HttpServletRequest request,
                                                User.ROLE role, String login) {
        HttpSession session = request.getSession();
        session.setAttribute(Parameters.ROLE, role);
        // todo add User. NOT JUST LOGIN
        session.setAttribute(Parameters.LOGIN,login);
        // todo add first name to session scope
        System.out.println("LOGIN HAS BEEN ADDED TO SESSION. LOGIN :: " + login);
    }

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

    public static void removeRoleAndLogin(HttpServletRequest request){
        HttpSession session = request.getSession();
        String login = (String)session.getAttribute(Parameters.LOGIN);

        HashSet<String> loggedUsers = (HashSet<String>) request.getServletContext().getAttribute(Parameters.LOGGED_USERS);
        loggedUsers.remove(login);
        // todo try to remove this string with setAttribute method
        //request.getServletContext().setAttribute(Parameters.LOGGED_USERS,loggedUsers);


        session.setAttribute(Parameters.ROLE, User.ROLE.GUEST);
        session.removeAttribute(Parameters.LOGIN);
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

    public String defineHomePageByRole(User.ROLE role){
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
