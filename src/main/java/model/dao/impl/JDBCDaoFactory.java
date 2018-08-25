package model.dao.impl;

import model.dao.*;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends AbstractDaoFactory {
    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public BankAccountDao createBankAccountDao() {
        return new JDBCBankAccountDao(getConnection());
    }

    @Override
    public CreditDao createCreditDao() {
        return new JDBCCreditDao(getConnection());
    }

    @Override
    public DepositDao createDepositDao() {
        return new JDBCDepositDao(getConnection());
    }

    public Connection getConnection(){
        try {
            return ConnectionPoolHolder.getDataSource().getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            // todo add logger
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

}
