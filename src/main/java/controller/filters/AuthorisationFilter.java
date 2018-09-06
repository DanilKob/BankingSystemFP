package controller.filters;

import controller.PagesName;
import controller.Parameters;
import controller.command.CommandManager;
import controller.utility.RolesUtility;
import model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorisationFilter implements Filter {
    private static final String SERVLET_PATH = "/servlet";
    private static final String CSS_DIRECTORY = "css/";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //Set<String> logedUsers = (Set<String>)servletRequest.getServletContext().getAttribute(AuthorisationConstants.LOGINED_USER);
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;
        HttpSession session = httpServletRequest.getSession();
        String path = httpServletRequest.getRequestURL().toString();

        User.ROLE roleFromSession = defineRoleFromSession(session);
        String commandName = httpServletRequest.getParameter(Parameters.ACTION_PARAM);

        if(isServletCall(path)){
            if(checkCommandAccess(commandName,roleFromSession)){
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            }else{
                Logger.getLogger(AuthorisationFilter.class.getName()).debug("Access denied !");
                RolesUtility.logoutUser(httpServletRequest);
                httpServletResponse.sendRedirect(removeServletDirectoryFromPath(path));
                return;
            }
        }

        if(isCssRequest(path)){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        // user or admin goes to guest pages
        if(!User.ROLE.GUEST.equals(roleFromSession)){
            RolesUtility.logoutUser(httpServletRequest);
            httpServletResponse.sendRedirect(PagesName.INDEX_PAGE);
            return;
        }

        // guest go to some guest page
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }

    private boolean checkCommandAccess(String commandName, User.ROLE roleFromSession){
        return CommandManager.getInstance().getCommandNameSet(roleFromSession).contains(commandName);
    }

    private User.ROLE defineRoleFromPath(String path){
        if(path.contains(PagesName.USER_DIRECTORY)) return User.ROLE.USER;
        if(path.contains(PagesName.ADMIN_DIRECTORY)) return User.ROLE.ADMIN;
        return User.ROLE.GUEST;
    }

    private User.ROLE defineRoleFromSession(HttpSession session){
        return (User.ROLE) session.getAttribute(Parameters.ROLE);
    }

    private String removeServletDirectoryFromPath(String path){
        return  path.replaceAll(SERVLET_PATH,"");
    }

    private boolean isServletCall(String path){
        return path.contains(SERVLET_PATH);
    }

    private boolean isCssRequest(String path){
        return path.contains(CSS_DIRECTORY);
    }

}