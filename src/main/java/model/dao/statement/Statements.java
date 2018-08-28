package model.dao.statement;

import model.dao.statement.tables.*;
import model.entity.User;

public interface Statements {
    String AND = " AND ";
    String INSERT_USER_FIRST_LAST_MIDDLE_NAME_LOGIN_PASSWORD_ROLE = "INSERT INTO " + UserTable.USER_TABLE
            + "( "
            + UserTable.USER_FIRST_NAME + ","
            + UserTable.USER_LAST_NAME + ","
            + UserTable.USER_MIDDLE_NAME + ","
            + UserTable.USER_LOGIN + ","
            + UserTable.USER_PASSWORD + ","
            + UserTable.USER_ROLE
            + ")"
            +" VALUES (?,?,?,?,?,?)";

    String INSERT_USER_FIRST_LAST_MIDDLE_NAME_LOGIN_PASSWORD_ROLE_ID = "INSERT INTO " + UserTable.USER_TABLE
            + "( "
            + UserTable.USER_FIRST_NAME + ","
            + UserTable.USER_LAST_NAME + ","
            + UserTable.USER_MIDDLE_NAME + ","
            + UserTable.USER_LOGIN + ","
            + UserTable.USER_PASSWORD + ","
            + UserTable.USER_ROLE_ID
            + ")"
            +" VALUES (?,?,?,?,?,?)";

    String SELECT_USER_BY_ID = "";

    /*
    String LOGIN_BY_LOGIN_PASSWORD = "SELECT * FROM " + TableConstants.USER_TABLE
            + " WHERE "
            + TableConstants.USER_LOGIN + " = ? "
            + AND
            + TableConstants.USER_PASSWORD + " = ?";
    */


    String LOGIN_BY_LOGIN_PASSWORD = "SELECT "
            + UserTable.USER_TABLE + "." + UserTable.USER_ID + ","
            + UserTable.USER_FIRST_NAME + ","
            + UserTable.USER_MIDDLE_NAME + ","
            + UserTable.USER_LAST_NAME + ","
            + RoleTable.ROLE_NAME
            + " FROM " + UserTable.USER_TABLE
            + " INNER JOIN " + RoleTable.ROLE_TABLE
            + " ON "
                + RoleTable.ROLE_TABLE + "." + RoleTable.ROLE_ID
                + " = "
                + UserTable.USER_TABLE + "." + UserTable.USER_ROLE_ID +
            " WHERE "
            + UserTable.USER_LOGIN + " = ? AND " + UserTable.USER_PASSWORD + " = ?";

    // todo add other fields

    String INSER_CREDIT_USER_ID_CREDIT_ID_TYPE_DATE = "INSERT INTO " + BankAccountTable.BANK_ACCOUNT_TABLE
            + "( "
                + BankAccountTable.BANK_ACCOUNT_USER_ID + ","
                //+ TableConstants.BANK_ACCOUNT_BALANCE+ ","
                + BankAccountTable.BANK_ACCOUNT_CREDIT_ID  + ","
                + BankAccountTable.BANK_ACCOUNT_TYPE_ID //+ ","
                //+ TableConstants.BANK_ACCOUNT_DATE
            + ")"
            +" VALUES (?,?,?)";

    String INSER_DEPOSIT_USER_ID_DEPOSIT_ID_TYPE_BALANCE_DATE = "INSERT INTO " + BankAccountTable.BANK_ACCOUNT_TABLE
            + "( "
                + BankAccountTable.BANK_ACCOUNT_USER_ID + ","
                //+ TableConstants.BANK_ACCOUNT_BALANCE+ ","
                + BankAccountTable.BANK_ACCOUNT_DEPOSIT_ID + ","
                + BankAccountTable.BANK_ACCOUNT_TYPE_ID + ","
                + BankAccountTable.BANK_ACCOUNT_BALANCE //+ ","
                //+ TableConstants.BANK_ACCOUNT_DATE
            + ")"
            +" VALUES (?,?,?,?)";

    String SELECT_CREDIT_BY_BANK_ACCOUNT_ID = "SELECT * FROM " + BankAccountTable.BANK_ACCOUNT_TABLE
            + " INNER JOIN " + CreditTable.CREDIT_TABLE
            + " ON " + BankAccountTable.BANK_ACCOUNT_TABLE + "." + BankAccountTable.BANK_ACCOUNT_CREDIT_ID
                     + " = "
                     + CreditTable.CREDIT_TABLE + "." + CreditTable.CREDIT_ID
            + " WHERE " + BankAccountTable.BANK_ACCOUNT_ID + " = ?";

    String SELECT_DEPOSIT_BY_BANK_ACCOUNT_ID = "SELECT * FROM " + BankAccountTable.BANK_ACCOUNT_TABLE
            + " INNER JOIN " + DepositTable.DEPOSIT_TABLE
            + " ON " + BankAccountTable.BANK_ACCOUNT_TABLE + "." + BankAccountTable.BANK_ACCOUNT_DEPOSIT_ID
                + " =  "
                + DepositTable.DEPOSIT_TABLE + "." + DepositTable.DEPOSIT_ID
            + " WHERE " + BankAccountTable.BANK_ACCOUNT_ID + " = ?";


    String SELECT_ALL_CREDIT_BY_BANK_USER_ID = "SELECT * FROM " + BankAccountTable.BANK_ACCOUNT_TABLE
            + " INNER JOIN " + CreditTable.CREDIT_TABLE
            + " ON " + BankAccountTable.BANK_ACCOUNT_TABLE + "." + BankAccountTable.BANK_ACCOUNT_CREDIT_ID
            + " = "
            + CreditTable.CREDIT_TABLE + "." + CreditTable.CREDIT_ID
            + " WHERE " + BankAccountTable.BANK_ACCOUNT_USER_ID + " = ?";

    String SELECT_ALL_DEPOSIT_BY_BANK_USER_ID = "SELECT * FROM " + BankAccountTable.BANK_ACCOUNT_TABLE
            + " INNER JOIN " + DepositTable.DEPOSIT_TABLE
            + " ON " + BankAccountTable.BANK_ACCOUNT_TABLE + "." + BankAccountTable.BANK_ACCOUNT_DEPOSIT_ID
            + " = "
            + DepositTable.DEPOSIT_TABLE+ "." + DepositTable.DEPOSIT_ID
            + " WHERE " + BankAccountTable.BANK_ACCOUNT_USER_ID + " = ?";





    String COUNT_ROW_PSEYDONYM = "countrow";


    String ACCOUNT_IS_CONFIRMED = BankAccountTable.BANK_ACCOUNT_ID + " <> " + TableConstants.ACCOUNT_TYPE_UNCONFIRMED;

    String COUNT_ABLE_TO_PAY_ACCOUNTS_WITH_ID_1_ID_2 = "SELECT COUNT(*) AS " + COUNT_ROW_PSEYDONYM
                        + "FROM BANK_ACCOUNT "
                        + "WHERE ("
                                + "( "
                                    + BankAccountTable.BANK_ACCOUNT_ID + " = ? "
                                    + "AND "
                                    + ACCOUNT_IS_CONFIRMED
                                + ")"
                                + "OR "
                                + "( "
                                    + BankAccountTable.BANK_ACCOUNT_ID + " = ? "
                                    + "AND "
                                    + ACCOUNT_IS_CONFIRMED
                                + ")"
                        + ")";

    String ADD_MONEY_TO_BANK_ACCOUNT = "UPDATE " + BankAccountTable.BANK_ACCOUNT_TABLE
            + " SET " + BankAccountTable.BANK_ACCOUNT_BALANCE + " = " + BankAccountTable.BANK_ACCOUNT_BALANCE + " + ? "
            + " WHERE "
            + BankAccountTable.BANK_ACCOUNT_ID + " = ? "
            + "AND "
            + ACCOUNT_IS_CONFIRMED;

    String TAKE_MONEY_FROM_BANK_ACCOUNT = "UPDATE " + BankAccountTable.BANK_ACCOUNT_TABLE
            + " SET " + BankAccountTable.BANK_ACCOUNT_BALANCE + " = " + BankAccountTable.BANK_ACCOUNT_BALANCE + " - ? "
            + " WHERE "
            + BankAccountTable.BANK_ACCOUNT_ID + " = ? "
            + "AND "
            + BankAccountTable.BANK_ACCOUNT_BALANCE + " >= ?";

    // update bank_account set balance = balance ?<OPERATION> ?<AMOUNT> where bank_account.account_id = ?<ACCOUNT_ID>;
    String MAKE_OPERATION_WITH_BANK_ACCOUNT_BALANCE =  "UPDATE " + BankAccountTable.BANK_ACCOUNT_TABLE
            + " SET " + BankAccountTable.BANK_ACCOUNT_BALANCE + " = " + BankAccountTable.BANK_ACCOUNT_BALANCE + " ? ? "
            + " WHERE " + BankAccountTable.BANK_ACCOUNT_ID + " = ? ";

    String INSERT_INTO_HISTORY_USER_ID_HISTORY_ID = "insert into user_has_payment_history " +
            "(user_id, payment_history_id ) values (?,?)";

    String INSERT_INTO_HISTORY_FROM_ID_TO_ID_PRICE_DATE = "insert into payment_history (bank_account_from, bank_account_to, price, date) values (?,?,?,?)";

    String SELECT_USER_BY_BANK_ACCOUNT_ID = "SELECT "
            + UserTable.USER_ID + "," +UserTable.USER_FIRST_NAME + ","
            + UserTable.USER_LAST_NAME + "," + UserTable.USER_MIDDLE_NAME
            + " FROM " + BankAccountTable.BANK_ACCOUNT_TABLE
            + " INNER JOIN " + UserTable.USER_TABLE
            + " ON "
                    + BankAccountTable.BANK_ACCOUNT_TABLE + "." + BankAccountTable.BANK_ACCOUNT_USER_ID
                    + " = "
                    + UserTable.USER_TABLE + "." + UserTable.USER_ID
            + " WHERE "
            + BankAccountTable.BANK_ACCOUNT_TABLE + "." + BankAccountTable.BANK_ACCOUNT_ID + " = ?";

    String SELECT_CREDIT_BY_ACCOUNT_ID = "SELECT * FROM " + BankAccountTable.BANK_ACCOUNT_TABLE
            +" WHERE " + BankAccountTable.BANK_ACCOUNT_TYPE_ID + " = " + TableConstants.ACCOUNT_TYPE_CREDIT;

    String SELECT_DEPOSIT_BY_ACCOUNT_ID = "SELECT * FROM " + BankAccountTable.BANK_ACCOUNT_TABLE
            +" WHERE " + BankAccountTable.BANK_ACCOUNT_TYPE_ID + " = " + TableConstants.ACCOUNT_TYPE_DEPOSIT;

    String SELECT_UNCONFIRMED_CREDIT_BY_ACCOUNT_ID = "SELECT * FROM " + BankAccountTable.BANK_ACCOUNT_TABLE
            +" WHERE " + BankAccountTable.BANK_ACCOUNT_TYPE_ID + " = " + TableConstants.ACCOUNT_TYPE_UNCONFIRMED;

    /*
   SELECT BANK_ACCOUNT_FROM,BANK_ACCOUNT_TO,PRICE,DATE,FIRST_NAME,MIDDLE_NAME,LAST_NAME FROM PAYMENT_HISTORY
   INNER JOIN USER_HAS_PAYMENT_HISTORY ON USER_HAS_PAYMENT_HISTORY.PAYMENT_HISTORY_ID = PAYMENT_HISTORY.HISTORY_ID
   INNER JOIN USER ON USER.ID = USER_HAS_PAYMENT_HISTORY.USER_ID
   WHERE (PAYMENT_HISTORY.BANK_ACCOUNT_TO = 10 OR PAYMENT_HISTORY.BANK_ACCOUNT_FROM = 10) AND USER.ID<>8;
    */
    String SELECT_HISTORY_BY_ACCOUNT_ID_AND_USER_ID = "SELECT "
            + HistoryTable.ACCOUNT_FROM +","
            + HistoryTable.ACCOUNT_TO +","
            + HistoryTable.PRICE +","
            + HistoryTable.DATE +","
            + UserTable.USER_ID +","
            + UserTable.USER_FIRST_NAME +","
            + UserTable.USER_MIDDLE_NAME+","
            + UserTable.USER_LAST_NAME
            + " FROM " + HistoryTable.HISTORY_TABLE
            + " INNER JOIN "
            + UserHasPaymentHistoryTable.USER_HAS_PAYMENT_HISTORY + " ON "
            + UserHasPaymentHistoryTable.USER_HAS_PAYMENT_HISTORY + "." +UserHasPaymentHistoryTable.PAYMENT_HISTORY_ID
            + " = " + HistoryTable.HISTORY_TABLE + "." +HistoryTable.ID
            + " INNER JOIN "
            + UserTable.USER_TABLE +" ON "
            + UserTable.USER_ID + " = " + UserHasPaymentHistoryTable.USER_ID
            + " WHERE ( "
            + HistoryTable.ACCOUNT_FROM + " = ? OR " + HistoryTable.ACCOUNT_TO + " = ? "
            + " ) "
            + " AND "
            + UserTable.USER_ID + "<>?";
}
