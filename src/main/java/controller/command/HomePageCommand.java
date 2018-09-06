package controller.command;

import controller.PagesName;
import controller.Parameters;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;

public class HomePageCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        return getHomePageByRole(getRoleFromSession(request));
    }

    private User.ROLE getRoleFromSession(HttpServletRequest request){
        return (User.ROLE) request.getSession().getAttribute(Parameters.ROLE);
    }

    private String getHomePageByRole(User.ROLE role){
        String page;
        switch (role){
            case USER: page = CommandConstants.REDIRECT
                    + CommandConstants.SET_COMMAND + CommandConstants.USER_HOME_PAGE_COMMAND;
                    break;
            case ADMIN: page =  CommandConstants.REDIRECT
                    + CommandConstants.SET_COMMAND + CommandConstants.ADMIN_HOME_PAGE_COMMAND;
                    break;
            default: page = PagesName.INDEX_PAGE;
        }
        return page;
    }
}
