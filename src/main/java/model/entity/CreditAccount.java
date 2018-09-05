package model.entity;

import model.dao.extracter.ExtractParam;
import model.dao.mapper.MappingKey;
import model.dao.statement.tables.BankAccountTable;
import model.dao.statement.tables.CreditTable;

public class CreditAccount extends BankAccount {

    private CreditTariff creditTariff;

    @ExtractParam(columnName = BankAccountTable.BANK_ACCOUNT_CREDIT_INDEBTEDNESS)
    private int indebtedness;

    public CreditTariff getCreditTariff() {
        return creditTariff;
    }

    public void setCreditTariff(CreditTariff creditTariff) {
        this.creditTariff = creditTariff;
    }

    public int getIndebtedness() {
        return indebtedness;
    }

    public void setIndebtedness(int indebtedness) {
        this.indebtedness = indebtedness;
    }
}
