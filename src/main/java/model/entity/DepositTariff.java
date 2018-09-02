package model.entity;

import model.dao.extracter.ExtractParam;
import model.dao.mapper.MappingKey;
import model.dao.statement.tables.DepositTable;

public class DepositTariff implements Entity {

    @MappingKey
    @ExtractParam(columnName = DepositTable.DEPOSIT_ID)
    private int id;

    @ExtractParam(columnName = DepositTable.DEPOSIT_NAME)
    protected String name;

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
}
