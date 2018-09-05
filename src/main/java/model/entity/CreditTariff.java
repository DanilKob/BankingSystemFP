package model.entity;

import model.dao.extracter.ExtractParam;
import model.dao.mapper.MappingKey;
import model.dao.statement.tables.CreditTable;

public class CreditTariff implements Entity {

    @MappingKey
    @ExtractParam(columnName = CreditTable.CREDIT_ID)
    private int id;

    @ExtractParam(columnName = CreditTable.CREDIT_NAME)
    private String name;

    @ExtractParam(columnName = CreditTable.CREDIT_RATE)
    private int rate;

    @ExtractParam(columnName = CreditTable.CREDIT_PERIOD)
    private int accrualRate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getAccrualRate() {
        return accrualRate;
    }

    public void setAccrualRate(int accrualRate) {
        this.accrualRate = accrualRate;
    }
}
