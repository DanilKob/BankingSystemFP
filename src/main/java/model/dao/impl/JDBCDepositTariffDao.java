package model.dao.impl;

import model.dao.DepositTariffDao;
import model.dao.extracter.Extracter;
import model.dao.statement.Statements;
import model.entity.DepositTariff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class JDBCDepositTariffDao extends AbstractJDBCGenericDao<DepositTariff> implements DepositTariffDao {

    public JDBCDepositTariffDao(Connection connection) {
        super(connection);
    }

    @Override
    public void create(DepositTariff entity) {

    }

    @Override
    public DepositTariff findById(int id) {
        return null;
    }

    @Override
    public List<DepositTariff> findAll() {
        List<DepositTariff> depositTariffList = new LinkedList<>();
        try {
            PreparedStatement preparedStatement = super.getConnection()
                    .prepareStatement(Statements.SELECT_ALL_DEPOSIT_TARIFFS);
            ResultSet resultSet = preparedStatement.executeQuery();
            Extracter<DepositTariff> depositTariffExtracter = new Extracter<>();
            while(resultSet.next()){
                depositTariffList.add(depositTariffExtracter.extractEntityFromResultSet(resultSet, new DepositTariff()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return depositTariffList;
    }

    @Override
    public void update(DepositTariff entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }
}
