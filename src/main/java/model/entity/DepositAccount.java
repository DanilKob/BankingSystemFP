package model.entity;

import model.dao.extracter.ExtractParam;
import model.dao.statement.tables.DepositTable;

public class DepositAccount extends BankAccount {
    @ExtractParam(columnName = DepositTable.DEPOSIT_NAME)
    protected String name;

    //protected int accountPropertiesId;
    private int depositId;

    private int depositRate;
    private int depositAmount;

    public int getDepositRate() {
        return depositRate;
    }

    public void setDepositRate(int depositRate) {
        this.depositRate = depositRate;
    }

    public int getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(int depositAmount) {
        this.depositAmount = depositAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDepositId() {
        return depositId;
    }

    public void setDepositId(int depositId) {
        this.depositId = depositId;
    }
}
