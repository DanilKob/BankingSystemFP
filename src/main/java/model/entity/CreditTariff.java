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
