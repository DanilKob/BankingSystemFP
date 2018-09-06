package controller.command;

import controller.PagesName;

import javax.servlet.http.HttpServletRequest;

public class ServerExceptionCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return PagesName.SERVER_EXCEPTION_PAGE;
    }
}
