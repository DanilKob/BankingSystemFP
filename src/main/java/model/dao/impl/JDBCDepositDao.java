package model.dao.impl;

import model.dao.DepositDao;
import model.dao.extracter.Extracter;
import model.dao.statement.Statements;
import model.entity.DepositAccount;
import model.entity.DepositTariff;
import model.exception.TariffNotExistException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class JDBCDepositDao extends AbstractJDBCGenericDao<DepositAccount> implements DepositDao {

    public JDBCDepositDao(Connection connection) {
        super(connection);
    }

    @Override
    public void create(DepositAccount entity) {
        try {
            PreparedStatement preparedStatement = super.getConnection()
                    .prepareStatement(Statements.INSERT_DEPOSIT_USER_ID_DEPOSIT_ID_TYPE_ID_DEPOSIT_AMOUNT);
            preparedStatement.setInt(1,entity.getUserId());
            preparedStatement.setInt(2,entity.getDepositTariff().getId());
            preparedStatement.setInt(3,entity.getAccountType().type_id);
            preparedStatement.setInt(4,entity.getBalance());

            preparedStatement.execute();
            // todo add exception
        } catch (SQLException e) {
            Logger.getLogger(JDBCBankAccountDao.class.getName()).error("create deposit",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void registerDeposit(DepositAccount depositAccount) throws TariffNotExistException{
        Connection connection = super.getConnection();
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            PreparedStatement isExistStatement = connection.prepareStatement("select exists (select * from deposit where deposit.id = ?)");
            isExistStatement.setInt(1,depositAccount.getDepositTariff().getId());

            ResultSet resultSet = isExistStatement.executeQuery();
            resultSet.next();
            if(!resultSet.getBoolean(1)){
                throw new TariffNotExistException();
            }

            PreparedStatement preparedStatement = connection
                    .prepareStatement(Statements.INSERT_DEPOSIT_USER_ID_DEPOSIT_ID_TYPE_ID_DEPOSIT_AMOUNT);
            preparedStatement.setInt(1,depositAccount.getUserId());
            preparedStatement.setInt(2,depositAccount.getDepositTariff().getId());
            preparedStatement.setInt(3,depositAccount.getAccountType().type_id);
            preparedStatement.setInt(4,depositAccount.getDepositAmount());

            preparedStatement.execute();

            connection.commit();
            // todo add exception
        } catch (SQLException e) {
            Logger.getLogger(JDBCBankAccountDao.class.getName()).error("register deposit",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public DepositAccount findById(int id) {
        // todo add optional
        DepositAccount depositAccount = null;
        try{
            PreparedStatement preparedStatement = super.getConnection()
                    .prepareStatement(Statements.SELECT_DEPOSIT_BY_BANK_ACCOUNT_ID);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(Statements.SELECT_DEPOSIT_BY_BANK_ACCOUNT_ID);
            Extracter<DepositAccount> depositExtracter = new Extracter<>();
            Extracter<DepositTariff> depositTariffExtracter = new Extracter<>();
            resultSet.next();
            depositAccount = depositExtracter.extractEntityFromResultSet(resultSet,new DepositAccount());
            depositAccount.setDepositTariff(depositTariffExtracter.extractEntityFromResultSet(resultSet, new DepositTariff()));

        } catch (SQLException | IllegalAccessException e) {
            Logger.getLogger(JDBCBankAccountDao.class.getName()).error("find deposit by id",e);
            throw new RuntimeException(e);
        }
        return depositAccount;
    }

    @Override
    public List<DepositAccount> findAll() {
        return null;
    }

    @Override
    public List<DepositAccount> findAllByUserBankAccountId(int bankAccountId) {
        List<DepositAccount> depositAccountList = new LinkedList<>();
        try {
            PreparedStatement preparedStatement = super.getConnection()
                    .prepareStatement(Statements.SELECT_ALL_DEPOSIT_BY_USER_BANK_ACCOUNT_ID);
            preparedStatement.setInt(1,bankAccountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Extracter<DepositAccount> depositAccountExtracter = new Extracter<>();
            Extracter<DepositTariff> depositTariffExtracter = new Extracter<>();
            DepositAccount depositAccount;
            while(resultSet.next()){
                depositAccount = depositAccountExtracter.extractEntityFromResultSet(resultSet, new DepositAccount());
                depositAccount.setDepositTariff(depositTariffExtracter.extractEntityFromResultSet(resultSet,new DepositTariff()));
                depositAccountList.add(depositAccount);
            }
        } catch (SQLException | IllegalAccessException e) {
            Logger.getLogger(JDBCBankAccountDao.class.getName()).error("find deposits by user bank account",e);
            throw new RuntimeException(e);
        }
        return depositAccountList;
    }

    @Override
    public List<DepositAccount> findAllByUserId(int userId) {
        List<DepositAccount> depositAccounts =  new LinkedList<>();
        try{
            PreparedStatement preparedStatement = super.getConnection()
                    .prepareStatement(Statements.SELECT_ALL_DEPOSIT_BY_BANK_USER_ID);
            preparedStatement.setInt(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Extracter<DepositAccount> depositExtracter = new Extracter<>();
            Extracter<DepositTariff> depositTarrifExtracter = new Extracter<>();
            DepositAccount depositAccount;
            while(resultSet.next()){
                depositAccount = depositExtracter.extractEntityFromResultSet(resultSet,new DepositAccount());
                depositAccount.setDepositTariff(depositTarrifExtracter
                        .extractEntityFromResultSet(resultSet, new DepositTariff()));
                depositAccounts.add(depositAccount);
            }
        } catch (SQLException | IllegalAccessException e) {
            Logger.getLogger(JDBCBankAccountDao.class.getName()).error("find deposits by user id",e);
            throw new RuntimeException(e);
        }
        return depositAccounts;
    }

    @Override
    public void update(DepositAccount entity) {

    }

    @Override
    public void delete(int id) {

    }
}
