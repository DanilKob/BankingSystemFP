package controller.command;

import controller.PagesName;
import controller.utility.RolesUtility;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        RolesUtility.logoutUser(request);
        return CommandConstants.REDIRECT + PagesName.INDEX_PAGE;
    }
}
