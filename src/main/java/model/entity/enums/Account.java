package model.entity.enums;

import model.dao.statement.TableConstants;

public enum Account {
    CREDIT(TableConstants.ACCOUNT_TYPE_CREDIT),
    DEPOSIT(TableConstants.ACCOUNT_TYPE_DEPOSIT),
    UNCONFIRMED_CREDIT(TableConstants.ACCOUNT_TYPE_UNCONFIRMED);

    public final int type_id;

    Account(int type_id) {
        this.type_id = type_id;
    }
}
