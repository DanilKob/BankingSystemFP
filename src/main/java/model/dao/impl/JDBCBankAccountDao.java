package model.dao.impl;

import model.dao.BankAccountDao;
import model.entity.BankAccount;

import java.sql.Connection;
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
