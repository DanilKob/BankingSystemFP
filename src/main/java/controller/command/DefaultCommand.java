package controller.command;

import controller.PagesName;
import controller.Parameters;
import controller.utility.RolesUtility;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command{
    private static final String SERVLET_PATH = "/servlet";
    @Override
    public String execute(HttpServletRequest request) {
        // todo get role and redirect to main page according to this role
        String path = request.getRequestURL().toString();
        return removeServletDirectoryFromPath(path)
                + CommandConstants.REDIRECT
                + RolesUtility.defineHomePageByRole((User.ROLE) request.getSession().getAttribute(Parameters.ROLE));
    }
    private String removeServletDirectoryFromPath(String path){
        String target = SERVLET_PATH;
        return  path.replaceAll(target,"");
    }


}
