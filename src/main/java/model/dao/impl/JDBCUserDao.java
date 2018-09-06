package model.dao.impl;


import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import model.dao.UserDao;
import model.dao.extracter.Extracter;
import model.dao.statement.Statements;
import model.entity.User;
import model.exception.NotUniqueException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JDBCUserDao extends AbstractJDBCGenericDao<User> implements UserDao {


    public JDBCUserDao(Connection connection) {
        super(connection);
    }

    @Override
    public void create(User entity){

    }

    @Override
    public void registerUser(User user) throws NotUniqueException{
        try {
            PreparedStatement preparedStatement = super.getConnection()
                    .prepareStatement(Statements.INSERT_USER_FIRST_LAST_MIDDLE_NAME_LOGIN_PASSWORD_ROLE_ID);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getMiddleName());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setInt(6, user.getRole().roleId);

            preparedStatement.execute();

        } catch (MySQLIntegrityConstraintViolationException e){
            Logger.getLogger(JDBCBankAccountDao.class.getName()).debug("not unique",e);
            throw new NotUniqueException(user.getLogin());
        } catch (SQLException e) {
            Logger.getLogger(JDBCBankAccountDao.class.getName()).error("register user",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findById(int id) {
        return null;

    }

    @Override
    public User findByBankAccount(int bankAccountId) {
        User user = null;
        try{
            PreparedStatement preparedStatement = super.getConnection()
                    .prepareStatement(Statements.SELECT_USER_BY_BANK_ACCOUNT_ID);
            preparedStatement.setInt(1,bankAccountId);

            ResultSet resultSet = preparedStatement.executeQuery();
            Extracter<User> userExtracter = new Extracter<>();
            if(resultSet.next()){
                user = userExtracter.extractEntityFromResultSet(resultSet, new User());
            }
        } catch (SQLException | IllegalAccessException e) {
            Logger.getLogger(JDBCBankAccountDao.class.getName()).error("find user by bank account id",e);
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public Optional<User> findByLoginPassword(String login, String password){
        Optional<User> optionalUser = Optional.empty();
        try {
            PreparedStatement preparedStatement = super.getConnection()
                    .prepareStatement(Statements.SELECT_USER_BY_LOGIN_PASSWORD);
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            Extracter<User> userExtracter = new Extracter<>();
            if(resultSet.next()){
                optionalUser = Optional.of(userExtracter.extractEntityFromResultSet(resultSet, new User()));
            }
        } catch (SQLException | IllegalAccessException e) {
            Logger.getLogger(JDBCBankAccountDao.class.getName()).error("find user by login and password",e);
            throw new RuntimeException(e);
        }
        return optionalUser;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(int id) {

    }
}
