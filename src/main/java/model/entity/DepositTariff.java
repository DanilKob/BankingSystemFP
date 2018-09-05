package model.entity;

import model.dao.extracter.ExtractParam;
import model.dao.mapper.MappingKey;
import model.dao.statement.tables.DepositTable;

public class DepositTariff implements Entity {

    @MappingKey
    @ExtractParam(columnName = DepositTable.DEPOSIT_ID)
    private int id;

    @ExtractParam(columnName = DepositTable.DEPOSIT_NAME)
    private String name;

    @ExtractParam(columnName = DepositTable.DEPOSIT_RATE)
    private int rate;

    @ExtractParam(columnName = DepositTable.DEPOSIT_PERIOD)
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
