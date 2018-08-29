package model.dao.impl;

import model.dao.DepositDao;
import model.dao.extracter.Extracter;
import model.dao.statement.Statements;
import model.entity.DepositAccount;

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
                    .prepareStatement(Statements.INSER_DEPOSIT_USER_ID_DEPOSIT_ID_TYPE_BALANCE_DATE);
            preparedStatement.setInt(1,entity.getUserId());
            preparedStatement.setInt(2,entity.getDepositId());
            preparedStatement.setInt(3,entity.getAccountType().type_id);
            preparedStatement.setInt(4,entity.getBalance());

            preparedStatement.execute();
            // todo add exception
        } catch (SQLException e) {
            e.printStackTrace();
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
            System.out.println("Search deposit");
            System.out.println(Statements.SELECT_DEPOSIT_BY_BANK_ACCOUNT_ID);
            Extracter<DepositAccount> depositExtracter = new Extracter<>();
            if(resultSet.next()){
                System.out.println("Result is not empty !!!");
                depositAccount = depositExtracter.extractEntityFromResultSet(resultSet,new DepositAccount());
            }
            /*
            DepositMapper depositMapper = new DepositMapper();

            if(resultSet.next()){
                depositAccount = depositMapper.extractEntityFromResultSet(resultSet);
            }else{
                System.out.println("No such account");
                // todo throw my Exception
            }
            */
            /*
            if(!resultSet.next()){
                System.out.println("No such account");
                // todo throw my Exception
            }
            */

            //depositAccount = depositMapper.extractEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return depositAccount;
    }

    @Override
    public List<DepositAccount> findAll() {
        return null;
    }

    @Override
    public List<DepositAccount> findAllByUserId(int userId) {
        List<DepositAccount> depositAccounts = null;
        try{
            PreparedStatement preparedStatement = super.getConnection()
                    .prepareStatement(Statements.SELECT_ALL_DEPOSIT_BY_BANK_USER_ID);
            preparedStatement.setInt(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Extracter<DepositAccount> depositExtracter = new Extracter<>();
            depositAccounts = new LinkedList<>();
            while(resultSet.next()){
                depositAccounts.add(depositExtracter.extractEntityFromResultSet(resultSet,new DepositAccount()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        // todo

        return depositAccounts;
    }

    @Override
    public void update(DepositAccount entity) {

    }

    @Override
    public void delete(int id) {

    }
}
