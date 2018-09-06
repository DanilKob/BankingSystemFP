package controller.filters;

import controller.PagesName;
import controller.Parameters;
import controller.command.CommandManager;
import controller.utility.RolesUtility;
import model.entity.User;

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

        System.out.println("===================");
        System.out.println("====== AuthFilter======");

        System.out.println("path = "+path);

        User.ROLE roleFromSession = defineRoleFromSession(session);
        String commandName = httpServletRequest.getParameter(Parameters.ACTION_PARAM);

        if(isServletCall(path)){
            if(checkCommandAccess(commandName,roleFromSession)){
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            }else{
                System.out.println("ACCESS IS NOT CORRECT!!!!!!!!!!!!");
                RolesUtility.logoutUser(httpServletRequest);
                httpServletResponse.sendRedirect(removeServletDirectoryFromPath(path));
                //httpServletResponse.sendRedirect(clearUrl(path) + PagesName.ERROR);
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

        /*
        User.ROLE roleFromPath = defineRoleFromPath(path);


        System.out.println("Session = "+session);
        System.out.println("roleFromPath = "+roleFromPath);
        System.out.println("roleFromSession = "+roleFromSession);

        // admin = admin
        // user = user
        // guest = guest
        if(roleFromPath.equals(roleFromSession)){
            System.out.println("ROLE FROM PATH AND SESSION ARE EQUAL");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        RolesUtility.logoutUser(httpServletRequest);

        path = removeServletDirectoryFromPath(path);

        // user or admin goes to guest
        if(roleFromPath.equals(User.ROLE.GUEST)){
            httpServletResponse.sendRedirect(PagesName.INDEX_PAGE);
            return;
        }
        System.out.println("ROLE FROM PATH AND SESSION ARE NOT EQUAL");
        // admin goes to user

        System.out.println("Filter redirect to error");
        //httpServletResponse.sendRedirect(PagesName.ERROR);
        // filter path
        System.out.println("path = "+path);


        String pathRedirect = removeRoleDirectoryFromPath(path,roleFromPath)+PagesName.ERROR;

        System.out.println("redirect path = " + pathRedirect);
        httpServletResponse.sendRedirect( pathRedirect);
        */

        System.out.println("!===================!");
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

    private boolean checkAccess(String path,HttpSession session){
        User.ROLE roleFromPath = defineRoleFromPath(path);
        User.ROLE roleFromSession = defineRoleFromSession(session);
        //todo finish return statement and make correct responde path
        return roleFromPath.equals(roleFromSession);
    }

    private boolean isRequestedPageForGuest(User.ROLE roleFromPath){
        return roleFromPath.equals(User.ROLE.GUEST);
    }


    private boolean checkAccess(User.ROLE roleFromPath,User.ROLE roleFromSession){
        return (roleFromPath.equals(User.ROLE.GUEST) || roleFromPath.equals(roleFromSession));
    }

    private String removeRoleDirectoryFromPath(String path, User.ROLE role){
        String target = role.name().toLowerCase()+"/*.*";
        return  path.replaceAll(target,"");
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

    private String clearUrl(String path){
        return path.replaceAll("/*","/");
    }

}