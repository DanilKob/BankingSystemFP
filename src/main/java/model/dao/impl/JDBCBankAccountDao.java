package model.dao.impl;

import model.dao.BankAccountDao;
import model.dao.extracter.Extracter;
import model.dao.statement.Statements;
import model.entity.BankAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class JDBCBankAccountDao extends AbstractJDBCGenericDao<BankAccount> implements BankAccountDao {

    public JDBCBankAccountDao(Connection connection) {
        super(connection);
    }

    @Override
    public void create(BankAccount entity) {
        /*
        try {
            PreparedStatement preparedStatement = super.getConnection()
                    .prepareStatement("");


            preparedStatement.execute();
        } catch (MySQLIntegrityConstraintViolationException e){
            e.printStackTrace();
            //todo remove sout
            System.out.println("Not unique login");
        } catch (SQLException e) {
            e.printStackTrace();
            //
            // todo add logger
            throw new RuntimeException();
        }
        */
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
                    .prepareStatement(Statements.SELECT_ALL_BANK_ACCOUNT_BY_USER_ID);
            preparedStatement.setInt(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Extracter<BankAccount> extracter = new Extracter<>();
            while(resultSet.next()){
                bankAccountList.add(extracter.extractEntityFromResultSet(resultSet,new BankAccount()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return bankAccountList;
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
