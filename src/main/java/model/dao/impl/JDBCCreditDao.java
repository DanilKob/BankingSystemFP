package model.dao.impl;

import model.dao.CreditDao;
import model.dao.extracter.Extracter;
import model.dao.statement.Statements;
import model.entity.CreditAccount;
import model.entity.CreditTariff;
import model.exception.TariffNotExistException;

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
            //preparedStatement.setInt(2,entity.getBalance());
            preparedStatement.setInt(2,creditAccount.getCreditTariff().getId());
            preparedStatement.setInt(3,creditAccount.getAccountType().type_id);
            //preparedStatement.setInt(4,creditAccount.getBalance());

            preparedStatement.execute();

            connection.commit();
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
            Extracter<CreditTariff> creditTariffExtracter = new Extracter<>();
            if(resultSet.next()){
                creditAccount = creditAccountExtracter.extractEntityFromResultSet(resultSet, new CreditAccount());
                creditAccount.setCreditTariff(creditTariffExtracter.extractEntityFromResultSet(resultSet,new CreditTariff()));
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
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        // todo
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
