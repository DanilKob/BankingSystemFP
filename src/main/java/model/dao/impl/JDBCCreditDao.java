package model.dao.impl;

import model.dao.CreditDao;
import model.dao.extracter.Extracter;
import model.dao.statement.Statements;
import model.entity.CreditAccount;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class JDBCCreditDao extends AbstractJDBCGenericDao<CreditAccount> implements CreditDao{

    public JDBCCreditDao(Connection connection) {
        super(connection);
    }

    @Override
    public void create(CreditAccount entity) {
        try {
            PreparedStatement preparedStatement = super.getConnection()
                    .prepareStatement(Statements.INSER_CREDIT_USER_ID_CREDIT_ID_TYPE_DATE);
            preparedStatement.setInt(1,entity.getUserId());
            //preparedStatement.setInt(2,entity.getBalance());
            preparedStatement.setInt(2,entity.getCreditId());
            preparedStatement.setInt(3,entity.getAccountType().type_id);

            preparedStatement.execute();
            // todo add exception
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CreditAccount findById(int id) {
        // todo add Optional
        CreditAccount creditAccount = null;
        try {
            PreparedStatement preparedStatement = super.getConnection()
                    .prepareStatement(Statements.SELECT_CREDIT_BY_BANK_ACCOUNT_ID);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Extracter<CreditAccount> creditAccountExtracter = new Extracter<>();
            if(resultSet.next()){
                creditAccount = creditAccountExtracter.extractEntityFromResultSet(resultSet, new CreditAccount());
                //creditAccount = creditMapper.extractEntityFromResultSet(resultSet);
            }else{
                // todo throw my exception
                System.out.println("Nothing found");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return creditAccount;
    }

    @Override
    public List<CreditAccount> findAll() {

        return null;
    }

    @Override
    public List<CreditAccount> findAllByUserId(int userId) {
        List<CreditAccount> creditAccounts = null;
        try{
            PreparedStatement preparedStatement = super.getConnection()
                    .prepareStatement(Statements.SELECT_ALL_CONFIRMED_CREDIT_BY_BANK_USER_ID);
            preparedStatement.setInt(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Extracter<CreditAccount> creditExtracter = new Extracter<>();
            creditAccounts = new LinkedList<>();
            while(resultSet.next()){
                creditAccounts.add(creditExtracter.extractEntityFromResultSet(resultSet,new CreditAccount()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        // todo

        return creditAccounts;
    }

    @Override
    public List<CreditAccount> findAllUnconfirmedByUserId(int accountId) {
        return null;
    }

    @Override
    public boolean pay(int fromAccountId, int fromUserId, int toAccountId, int toUserId, int price) {
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
                System.out.println("Insufficient funds! Refill your bank account");
                connection.rollback();
                return false;
            }

            if(psAddMoney.executeUpdate() == 0){
                connection.rollback();
                return false;
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
            int generatedKey = 0;
            if(rs.next()){
                generatedKey = rs.getInt(1);
            }

            System.out.println("generated key = " + generatedKey);
            ///////////////////////////////////////////////////////////////////////////////////////////


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
            System.out.println("Payment is successful");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            // todo refactor and add logger
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public void update(CreditAccount entity) {

    }

    @Override
    public void delete(int id) {

    }

    // todo refactor to another class

    /*
    private CreditAccount extractLazyFromResultSet(ResultSet resultSet) throws SQLException{
        CreditAccount creditAccount = null;
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        int columnCount = resultSetMetaData.getColumnCount();

        for(int index=1;index<=columnCount;++index){
            String columName = resultSetMetaData.getColumnName(index);
        }

        if(resultSet.next()){
            creditAccount.setId(resultSet.getInt(CreditTable.CREDIT_ID));
            creditAccount.setBalance(resultSet.getInt(BankAccountTable.BANK_ACCOUNT_BALANCE));
            creditAccount.setName(resultSet.getString(CreditTable.CREDIT_NAME));
        }
        return creditAccount;
    }
    */
}
