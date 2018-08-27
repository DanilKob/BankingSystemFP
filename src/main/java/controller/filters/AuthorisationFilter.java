package controller.filters;

import controller.PagesName;
import controller.Parameters;
import controller.utility.RolesUtility;
import model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

public class AuthorisationFilter implements Filter {
    private static final String SERVLET_PATH = "/servlet";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Set<String> logedUsers = (Set<String>)servletRequest.getServletContext().getAttribute(AuthorisationConstants.LOGINED_USER);
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;
        HttpSession session = httpServletRequest.getSession();
        String path = httpServletRequest.getRequestURL().toString();
        String pathNew = httpServletRequest.getContextPath();

        System.out.println("===================");
        System.out.println("====== AuthFilter======");
        System.out.println("Contex Path = " + pathNew);

        System.out.println("path = "+path);
        /*
        if(session.isNew()){
            // todo call model from filter!!!
            System.out.println("Session = "+session+" is new");
            session.setAttribute(Parameters.ROLE,User.ROLE.GUEST);
        }
        */
        /*
        if(isServletCall(path)){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        */
        User.ROLE roleFromPath = defineRoleFromPath(path);
        User.ROLE roleFromSession = defineRoleFromSession(session);

        System.out.println("Session = "+session);
        System.out.println("roleFromPath = "+roleFromPath);
        System.out.println("roleFromSession = "+roleFromSession);

        // admin = admin
        // user = user
        // guest = guest
        if(roleFromPath.equals(roleFromSession)){
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

        // admin goes to user

        System.out.println("Filter redirect to error");
        //httpServletResponse.sendRedirect(PagesName.ERROR);
        // filter path
        System.out.println("path = "+path);


        String pathRedirect = removeRoleDirectoryFromPath(path,roleFromPath)+PagesName.ERROR;

        System.out.println("redirect path = " + pathRedirect);
        httpServletResponse.sendRedirect( pathRedirect);

        System.out.println("!===================!");
    }

    @Override
    public void destroy() {

    }

    private User.ROLE defineRoleFromPath(String path){
        if(path.contains(PagesName.USER_DIRECTORY)) return User.ROLE.USER;
        if(path.contains(PagesName.ADMIN_DIRECTORY)) return User.ROLE.ADMIN;
        return User.ROLE.GUEST;
    }
    //todo refactor defineRoleFromSession
    private User.ROLE defineRoleFromSession(HttpSession session){
        // todo maybe can be null
        /*
        User.ROLE roleFromSession = (User.ROLE) session.getAttribute(Parameters.ROLE);
        return (roleFromSession==null)?User.ROLE.GUEST:roleFromSession;
        */
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
        String target = SERVLET_PATH;
        return  path.replaceAll(target,"");
    }
}

        /*

        System.out.println("Session = "+session);
        System.out.println("roleFromPath = "+roleFromPath);
        System.out.println("roleFromSession = "+roleFromSession);

        System.out.println(roleFromPath.equals(roleFromSession));


        if(checkAccess(roleFromPath,roleFromSession)){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            System.out.println("Filter redirect to error");
            //httpServletResponse.sendRedirect(PagesName.ERROR);
            // filter path
            System.out.println("path = "+path);
            String pathRedirect = removeRoleDirectoryFromPath(path,roleFromPath)+PagesName.ERROR;

            System.out.println("redirect path = " + pathRedirect);
            httpServletResponse.sendRedirect( pathRedirect);
        }
        */