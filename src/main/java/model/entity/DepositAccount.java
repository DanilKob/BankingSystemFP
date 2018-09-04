package model.entity;

import model.dao.extracter.ExtractParam;
import model.dao.statement.tables.BankAccountTable;
import model.dao.statement.tables.DepositTable;

public class DepositAccount extends BankAccount {

    private DepositTariff depositTariff;

    @ExtractParam(columnName = BankAccountTable.BANK_ACCOUNT_DEPOSIT_AMMOUNT)
    private int depositAmount;

    public DepositTariff getDepositTariff() {
        return depositTariff;
    }

    public void setDepositTariff(DepositTariff depositTariff) {
        this.depositTariff = depositTariff;
    }

    public int getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(int depositAmount) {
        this.depositAmount = depositAmount;
    }

}
