package controller.command;

import controller.PagesName;

import javax.servlet.http.HttpServletRequest;

public class HistoryInfoCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return PagesName.USER_HOME_PAGE;
    }
}
