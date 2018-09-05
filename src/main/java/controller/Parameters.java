package controller;

public interface Parameters {
    String ACTION_PARAM = "command";
    String LANGUAGE = "language";
    String ROLE = "role";
    String LOGGED_USERS = "loggedUsers";
    String ERROR_MESSAGE = "error";

    String USER = "user";

    // Registration form
    // todo Fill fields
    String LAST_NAME = "lastName";
    String FIRST_NAME = "firstName";
    String MIDDLE_NAME = "middleName";
    String LOGIN = "login";
    // Login form
    String PASSWORD = "password";
    // Login form

    String LAST_NAME_ERROR = "lastNameError";
    String FIRST_NAME_ERROR = "firstNameError";
    String MIDDLE_NAME_ERROR = "middleNameError";
    String LOGIN_ERROR = "loginError";
    String PASSWORD_ERROR = "passwordError";
    // Registration form


    String FAKE_ID_FROM_PAGE = "fakeId";
    String COMPLIANCE_TABLE = "complianceTable";

    String PAY_TO_ACCOUNT_ID = "bankAccountTo";
    String PAY_TO_USER_ID = "userTo";
    String PAY_PRICE = "price";
    String BALANCE = "balance";

    String PAYMENT_SUCCESS = "paymentSuccess";

    String BANK_ACCOUNTS = "bankAccounts";
    String BANK_ACCOUNT_ID = "bankAccountId";
    String BANK_ACCOUNT_HISTORY = "bankAccountHistory";

    String CREDITS = "credits";
    String UNCONFIRMED_CREDITS = "unconfirmedCredits";
    String CREDIT_TARIFF_ID = "creditTariffId";
    String CREDIT_TARIFFS = "creditTariffs";
    String CREDIT_ID = "creditId";
    String CREDIT_ACCOUNT = "creditAccount";

    String CREDIT_COMPLIANCE_TABLE  = "creditComplianceTable";

    String DEPOSITS = "deposits";
    String DEPOSIT_ID = "depositId";
    String DEPOSIT_TARIFFS = "depositTariffs";
    String DEPOSIT_TARIFF_ID = "depositTariffId";
    String DEPOSIT_ACCOUNT = "depositAccount";

    String DEPOSIT_COMPLIANCE_TABLE  = "depositComplianceTable";

    String ALL_UNCONFIRMED_CREDITS = "allUnconfirmedCredits";
    String ALL_DEPOSITS_BY_USER_WITH_CREDIT = "allUserDeposits";

}
