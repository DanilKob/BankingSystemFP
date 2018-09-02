package controller;

public interface PagesName {
    String INDEX_PAGE = "index.jsp";
    String REGISTRATION = "registration.jsp";
    String LOGIN_PAGE = "login.jsp";
    String ERROR = "error.jsp";


    String USER_DIRECTORY = "/user";
    String ADMIN_DIRECTORY = "/admin";
    String USER_HOME_PAGE = USER_DIRECTORY + "/" + "user.jsp";
    String ADMIN_HOME_PAGE = ADMIN_DIRECTORY + "/" + "admin.jsp";
    /*
    String CREDIT_PAGE = USER_DIRECTORY + "/" + "credit.jsp";

    //String CREDITS = USER_DIRECTORY + "/" + "credit.jsp";
    String DEPOSIT_PAGE = USER_DIRECTORY + "/" + "deposit.jsp";
    */
    String BANK_ACCOUNT_PAGE = USER_DIRECTORY + "/" +"bankAccountInfo.jsp";
    String PAYMENT_PAGE = USER_DIRECTORY + "/" + "payment.jsp";

    String TARIFF_INFO_PAGE = USER_DIRECTORY + "/" + "tariffs.jsp";
}
