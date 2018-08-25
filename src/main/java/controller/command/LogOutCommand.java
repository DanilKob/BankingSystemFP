package controller.command;

import controller.PagesName;
import controller.utility.RolesUtility;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {
    RolesUtility rolesUtility = new RolesUtility();

    @Override
    public String execute(HttpServletRequest request) {
        RolesUtility.removeRoleAndLogin(request);
        return CommandConstants.REDIRECT + PagesName.INDEX_PAGE;
    }
}
