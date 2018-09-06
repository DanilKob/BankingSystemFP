package model.dao.impl;

import model.dao.CreditDao;
import model.dao.extracter.Extracter;
import model.dao.statement.Statements;
import model.dao.statement.TableConstants;
import model.entity.CreditAccount;
import model.entity.CreditTariff;
import model.exception.BankAccountNotExistException;
import model.exception.TariffNotExistException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class JDBCCreditDao extends AbstractJDBCGenericDao<CreditAccount> implements CreditDao{

    public JDBCCreditDao(Connection connection) {
        super(connection);
    }

    @Override
    public void create(CreditAccount entity) {

    }

    @Override
    public void udpateCreditAccountBalanceByAccountId(int creditId, int indebtedness) {
        try {
            PreparedStatement preparedStatement = super.getConnection()
                    .prepareStatement(Statements.UPDATE_CREDIT_ACCOUNT_BALANCE_INDEBTEDNESS_BY_BANK_ACCOUNT_ID);
            preparedStatement.setInt(1,indebtedness);
            preparedStatement.setInt(2,indebtedness);
            preparedStatement.setInt(3,indebtedness);
            preparedStatement.setInt(4,TableConstants.ACCOUNT_TYPE_CREDIT);
            preparedStatement.setInt(5,creditId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registerCredit(CreditAccount creditAccount) throws TariffNotExistException {
        Connection connection = super.getConnection();
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            PreparedStatement isExistStatement = connection.prepareStatement("select exists (select * from credit where credit.id = ?)");
            isExistStatement.setInt(1,creditAccount.getCreditTariff().getId());
            ResultSet resultSet = isExistStatement.executeQuery();
            resultSet.next();
            if(!resultSet.getBoolean(1)){
                throw new TariffNotExistException();
            }

            PreparedStatement preparedStatement = connection
                    .prepareStatement(Statements.INSER_CREDIT_USER_ID_CREDIT_ID_TYPE_DATE);
            preparedStatement.setInt(1,creditAccount.getUserId());
            preparedStatement.setInt(2,creditAccount.getCreditTariff().getId());
            preparedStatement.setInt(3,creditAccount.getAccountType().type_id);

            preparedStatement.execute();

            connection.commit();
        } catch (SQLException e) {
            Logger.getLogger(JDBCBankAccountDao.class.getName()).error("register credit",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public CreditAccount findById(int id) throws BankAccountNotExistException {
        // todo add Optional
        CreditAccount creditAccount = null;
        try {
            PreparedStatement preparedStatement = super.getConnection()
                    .prepareStatement(Statements.SELECT_CREDIT_BY_BANK_ACCOUNT_ID);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Extracter<CreditAccount> creditAccountExtracter = new Extracter<>();
            Extracter<CreditTariff> creditTariffExtracter = new Extracter<>();
            if(resultSet.next()){
                creditAccount = creditAccountExtracter.extractEntityFromResultSet(resultSet, new CreditAccount());
                creditAccount.setCreditTariff(creditTariffExtracter.extractEntityFromResultSet(resultSet,new CreditTariff()));
                //creditAccount = creditMapper.extractEntityFromResultSet(resultSet);
            }else{
                throw new BankAccountNotExistException();
            }

        } catch (SQLException | IllegalAccessException e) {
            Logger.getLogger(JDBCBankAccountDao.class.getName()).error("find by id",e);
            throw new RuntimeException(e);
        }
        return creditAccount;
    }


    private List<CreditAccount> findCreditByStatement(int userId, String statement){
        List<CreditAccount> creditAccounts = new LinkedList<>();
        try{
            PreparedStatement preparedStatement = super.getConnection()
                    .prepareStatement(statement);
            preparedStatement.setInt(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Extracter<CreditAccount> creditExtracter = new Extracter<>();
            Extracter<CreditTariff> creditTariffExtracter = new Extracter<>();
            CreditAccount creditAccount;
            while(resultSet.next()){
                creditAccount = creditExtracter.extractEntityFromResultSet(resultSet,new CreditAccount());
                creditAccount.setCreditTariff(creditTariffExtracter.
                        extractEntityFromResultSet(resultSet, new CreditTariff()));
                creditAccounts.add(creditAccount);
            }
        } catch (SQLException | IllegalAccessException e) {
            Logger.getLogger(JDBCBankAccountDao.class.getName()).error("find credit by statement",e);
            throw new RuntimeException(e);
        }
        return creditAccounts;
    }

    @Override
    public List<CreditAccount> findAll() {
        return null;
    }

    @Override
    public List<CreditAccount> findAllUnconfirmedCredits() {
        List<CreditAccount> creditAccounts = new LinkedList<>();
        try{
            PreparedStatement preparedStatement = super.getConnection()
                    .prepareStatement(Statements.SELECT_ALL_UNCONFIRMED_CREDITS);
            ResultSet resultSet = preparedStatement.executeQuery();
            Extracter<CreditAccount> creditExtracter = new Extracter<>();
            Extracter<CreditTariff> creditTariffExtracter = new Extracter<>();
            CreditAccount creditAccount;
            while(resultSet.next()){
                creditAccount = creditExtracter.extractEntityFromResultSet(resultSet,new CreditAccount());
                creditAccount.setCreditTariff(creditTariffExtracter.
                        extractEntityFromResultSet(resultSet, new CreditTariff()));
                creditAccounts.add(creditAccount);
            }
        } catch (SQLException | IllegalAccessException e) {
            Logger.getLogger(JDBCBankAccountDao.class.getName()).error("find all unconfirmed credits",e);
            throw new RuntimeException(e);
        }
        return creditAccounts;
    }

    @Override
    public List<CreditAccount> findAllConfirmedByUserId(int userId) {
        return findCreditByStatement(userId,Statements.SELECT_ALL_CONFIRMED_CREDIT_BY_BANK_USER_ID);
    }

    @Override
    public List<CreditAccount> findAllUnconfirmedCreditsByUserId(int userId) {
        return findCreditByStatement(userId, Statements.SELECT_ALL_UNCONFIRMED_CREDIT_BY_BANK_USER_ID);
    }


    @Override
    public void update(CreditAccount entity) {

    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = super.getConnection()
                    .prepareStatement(Statements.DELETE_CREDIT_ACCOUNT_BY_ACCOUNT_ID);
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        } catch (SQLException e) {
            Logger.getLogger(JDBCBankAccountDao.class.getName()).error("delete credit account",e);
            throw new RuntimeException(e);
        }
    }
}
