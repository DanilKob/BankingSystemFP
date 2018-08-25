package controller;

public interface PagesName {
    String INDEX_PAGE = "index.jsp";
    String REGISTRATION = "registration.jsp";
    String LOGIN_PAGE = "login.jsp";
    String ERROR = "error.jsp";

    String USER_DIRECTORY = "/user";
    String ADMIN_DIRECTORY = "/admin";
    String USER_HOME_PAGE = USER_DIRECTORY +"/"+"user.jsp";
    String ADMIN_HOME_PAGE = ADMIN_DIRECTORY+"/"+"admin.jsp";
}
