package controller.listeners;


import controller.Parameters;
import model.entity.User;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;

public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setAttribute(Parameters.ROLE,User.ROLE.GUEST);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HashSet<String> loggedUsers = (HashSet<String>) httpSessionEvent
                .getSession().getServletContext()
                .getAttribute(Parameters.LOGGED_USERS);

        String login = (String) httpSessionEvent.getSession().getAttribute(Parameters.LOGIN);

        loggedUsers.remove(login);
        //httpSessionEvent.getSession().getServletContext().setAttribute(Parameters.LOGGED_USERS, loggedUsers);
        System.out.println("Session Destroyed");
    }
}