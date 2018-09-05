package controller.command;

public interface CommandConstants {
    String SERVLET_MAPPING = "/servlet";

    String REDIRECT = "redirect/";
    String SET_COMMAND = SERVLET_MAPPING + "?command=";


    String LOGIN_COMMAND = "logIn";
    String LOGOUT_COMMAND = "logOut";
    String REGISTRATION_COMMAND = "register";
    String DEFAULT_COMMAND = "default";

    String USER_HOME_PAGE_COMMAND = "userHomePage";
    String ADMIN_HOME_PAGE_COMMAND = "adminHomePage";



    String BANK_ACCOUNTS_COMMAND = "bankAccounts";
    String CREDITS_COMMAND = "credits";
    String USER_UNCONFIRMED_CREDITS_COMMAND = "unconfirmedCredits";
    String DEPOSITS_COMMAND = "deposits";
    String CREDIT_PAGE_COMMAND = "CREDIT";
    String DEPOSIT_PAGE_COMMAND = "DEPOSIT";

    String HISTORY_COMMAND = "history";
    String PAY_COMMAND = "pay";
    String PAYMENT_CONFIRMATION = "payConfirmation";

    String CREDIT_TARIFF_INFO = "creditTariffsInfo";
    String DEPOSIT_TARIFF_INFO = "depositTariffsInfo";

    String REGISTER_CREDIT_ACCOUNT = "registerCreditAccount";
    String REGISTER_DEPOSIT_ACCOUNT = "registerDepositAccount";


    String ALL_UNCONFIRMED_CREDITS = "allUnconfirmedCredits";
    String UNCONFIRMED_CREDIT_INFO = "unconfirmedCreditInfo";

    String CONFIRM_CREDIT = "confirmCredit";
    String CANCEL_CREDIT = "cancelCredit";
}
