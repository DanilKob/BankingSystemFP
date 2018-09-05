package controller.command;

import controller.PagesName;

import javax.servlet.http.HttpServletRequest;

public class AdminHomePageCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        return PagesName.ADMIN_HOME_PAGE;
    }
}
