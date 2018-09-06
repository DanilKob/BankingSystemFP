package model.dao.impl;

import model.dao.CreditTariffDao;
import model.dao.DepositTariffDao;
import model.dao.extracter.Extracter;
import model.dao.statement.Statements;
import model.entity.CreditTariff;
import model.entity.DepositTariff;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class JDBCCreditTariffDao extends AbstractJDBCGenericDao<CreditTariff> implements CreditTariffDao {


    public JDBCCreditTariffDao(Connection connection) {
        super(connection);
    }

    @Override
    public void create(CreditTariff entity) {

    }

    @Override
    public CreditTariff findById(int id) {
        return null;
    }

    @Override
    public List<CreditTariff> findAll() {
        List<CreditTariff> creditTariffList = new LinkedList<>();
        try {
            PreparedStatement preparedStatement = super.getConnection()
                    .prepareStatement(Statements.SELECT_ALL_CREDIT_TARIFFS);
            ResultSet resultSet = preparedStatement.executeQuery();
            Extracter<CreditTariff> creditTariffExtracter = new Extracter<>();
            while(resultSet.next()){
                creditTariffList.add(creditTariffExtracter.extractEntityFromResultSet(resultSet, new CreditTariff()));
            }
        }catch (SQLException | IllegalAccessException e) {
            Logger.getLogger(JDBCBankAccountDao.class.getName()).error("find all",e);
            throw new RuntimeException(e);
        }
        return creditTariffList;
    }

    @Override
    public void update(CreditTariff entity) {

    }

    @Override
    public void delete(int id) {

    }
}
