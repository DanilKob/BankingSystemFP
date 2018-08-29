package model.entity;

import model.dao.extracter.ExtractParam;
import model.dao.mapper.MappingKey;
import model.dao.statement.tables.CreditTable;

public class CreditAccount extends BankAccount {
    @MappingKey
    private int creditId;
    @ExtractParam(columnName = CreditTable.CREDIT_NAME)
    private String name;


    private int percent;
    private int limit;
    private int period;


    public void setCreditId(int creditId) {
        this.creditId = creditId;
    }

    public int getCreditId() {
        return creditId;
    }


    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
