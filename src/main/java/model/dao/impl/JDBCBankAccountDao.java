package model.dao.impl;

import model.dao.BankAccountDao;
import model.dao.extracter.Extracter;
import model.dao.statement.Statements;
import model.entity.BankAccount;
import model.exception.BankAccountNotExistException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class JDBCBankAccountDao extends AbstractJDBCGenericDao<BankAccount> implements BankAccountDao {

    public JDBCBankAccountDao(Connection connection) {
        super(connection);
    }

    @Override
    public void create(BankAccount entity) {

    }

    @Override
    public BankAccount findById(int id) {
        return null;
    }

    @Override
    public List<BankAccount> findAllBankAccountByUserId(int userId) {
        List<BankAccount> bankAccountList = new LinkedList<>();
        try {
            PreparedStatement preparedStatement = super.getConnection()
                    .prepareStatement(Statements.SELECT_ALL_CONFIRMED_BANK_ACCOUNT_BY_USER_ID);
            preparedStatement.setInt(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Extracter<BankAccount> extracter = new Extracter<>();
            while(resultSet.next()){
                bankAccountList.add(extracter.extractEntityFromResultSet(resultSet,new BankAccount()));
            }
        } catch (SQLException | IllegalAccessException e) {
            Logger.getLogger(JDBCBankAccountDao.class.getName()).error("findAllBankAccount",e);
            throw new RuntimeException(e);
        }
        return bankAccountList;
    }

    @Override
    public boolean pay(int fromAccountId, int fromUserId, int toAccountId, int price) throws BankAccountNotExistException {
        Connection connection = super.getConnection();
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            PreparedStatement psTakeMoney = connection.prepareStatement(Statements.TAKE_MONEY_FROM_BANK_ACCOUNT);
            psTakeMoney.setInt(1,price);
            psTakeMoney.setInt(2,fromAccountId);
            psTakeMoney.setInt(3,price);

            PreparedStatement psAddMoney = connection.prepareStatement(Statements.ADD_MONEY_TO_BANK_ACCOUNT);
            psAddMoney.setInt(1,price);
            psAddMoney.setInt(2,toAccountId);

            if(psTakeMoney.executeUpdate() == 0){
                connection.rollback();
                return false;
            }

            if(psAddMoney.executeUpdate() == 0){
                connection.rollback();
                throw new BankAccountNotExistException();
            }
            //////////////////////////////////////////////////////////////////////////////////////////////
            PreparedStatement preparedStatement = connection
                    .prepareStatement(Statements.INSERT_INTO_HISTORY_FROM_ID_TO_ID_PRICE_DATE,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,fromAccountId);
            preparedStatement.setInt(2,toAccountId);
            preparedStatement.setInt(3,price);
            preparedStatement.setTimestamp(4,Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            int generatedKey = rs.getInt(1);

            ///////////////////////////////////////////////////////////////////////////////////////////
            PreparedStatement getUserIdByAccountIdStatement = connection
                    .prepareStatement(Statements.GET_USER_ID_BY_ACCOUNT_ID);
            getUserIdByAccountIdStatement.setInt(1,toAccountId);
            ResultSet resultSet = getUserIdByAccountIdStatement.executeQuery();
            if(!resultSet.next()){
                throw new BankAccountNotExistException();
            }
            int toUserId = resultSet.getInt(1);

            PreparedStatement addHistory = connection.prepareStatement(Statements.INSERT_INTO_HISTORY_USER_ID_HISTORY_ID);
            addHistory.setInt(1, fromUserId);
            addHistory.setInt(2,generatedKey);
            addHistory.addBatch();

            if(fromUserId != toUserId) {
                addHistory.setInt(1,toUserId);
                addHistory.setInt(2,generatedKey);
                addHistory.addBatch();
            }
            addHistory.executeBatch();
            //////////////////////////////////////////////////////////////////////////////////////////

            connection.commit();
            return true;

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                Logger.getLogger(JDBCBankAccountDao.class.getName()).error("payment",e1);
                throw new RuntimeException(e1);
            }
            Logger.getLogger(JDBCBankAccountDao.class.getName()).error("payment",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BankAccount> findAll() {
        return null;
    }

    @Override
    public void update(BankAccount entity) {

    }

    @Override
    public void delete(int id) {

    }
}
