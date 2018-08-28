package model.dao.statement.tables;

public interface HistoryTable {
    String HISTORY_TABLE = "payment_history";
    String ID = "history_id";
    String ACCOUNT_FROM = "bank_account_from";
    //String USER_FROM_ID = "";
    String ACCOUNT_TO = "bank_account_to";
    String PRICE = "price";
    String DATE = "date";
}
