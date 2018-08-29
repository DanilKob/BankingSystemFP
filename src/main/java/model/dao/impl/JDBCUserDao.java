package model.dao.impl;


import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import model.dao.UserDao;
import model.dao.extracter.Extracter;
import model.dao.statement.Statements;
import model.entity.User;
import model.exception.NotUniqueException;

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
    public void create(User entity) throws NotUniqueException {
        try {
            PreparedStatement preparedStatement = super.getConnection()
                    .prepareStatement(Statements.INSERT_USER_FIRST_LAST_MIDDLE_NAME_LOGIN_PASSWORD_ROLE_ID);
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getMiddleName());
            preparedStatement.setString(4, entity.getLogin());
            preparedStatement.setString(5, entity.getPassword());
            preparedStatement.setInt(6, entity.getRole().roleId);

            System.out.println("IN CREATE METHOD. USER NAME :: "+entity.getFirstName());
            System.out.println("IN CREATE METHOD. USER NAME :: "+entity.getMiddleName());
            System.out.println("IN CREATE METHOD. USER NAME :: "+entity.getLastName());
            System.out.println("IN CREATE METHOD. USER NAME :: "+entity.getLogin());
            System.out.println("IN CREATE METHOD. USER NAME :: "+entity.getPassword());


            System.out.println("In dao method");

            preparedStatement.execute();

        } catch (MySQLIntegrityConstraintViolationException e){
            e.printStackTrace();
            //todo remove sout
            System.out.println("Not unique login");
            throw new NotUniqueException(entity.getLogin());
        } catch (SQLException e) {
            e.printStackTrace();
            //
            // todo add logger
            throw new RuntimeException();
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
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Optional<User> findByLoginPassword(String login, String password){
        Optional<User> optionalUser = Optional.empty();
        try {
            PreparedStatement preparedStatement = super.getConnection()
                    .prepareStatement(Statements.LOGIN_BY_LOGIN_PASSWORD);
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            Extracter<User> userExtracter = new Extracter<>();
            if(resultSet.next()){
                optionalUser = Optional.of(userExtracter.extractEntityFromResultSet(resultSet, new User()));
            }
            // todo throw my exception
        } catch (SQLException e) {
            e.printStackTrace();
            //
            // todo add logger
            throw new RuntimeException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
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
    /*
    private User extractUserFullNameByAccountId(ResultSet resultSet) throws SQLException{
        User user = new User.Builder()
                .setId(resultSet.getInt(UserTable.USER_ID))
                .setFirstName(resultSet.getString(UserTable.USER_FIRST_NAME))
                .setLastName(resultSet.getString(UserTable.USER_LAST_NAME))
                .setMiddleName(resultSet.getString(UserTable.USER_MIDDLE_NAME))
                .build();
        return user;
    }
    */
}
