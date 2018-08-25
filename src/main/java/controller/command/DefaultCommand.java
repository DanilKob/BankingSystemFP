package controller.command;

import javax.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        // todo get role and redirect to main page according to this role
        return "index.jsp";
    }
}
