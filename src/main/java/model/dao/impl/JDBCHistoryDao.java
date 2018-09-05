package model.dao.impl;

import model.dao.HistoryDao;
import model.dao.extracter.Extracter;
import model.dao.mapper.Mapper;
import model.dao.statement.Statements;
import model.entity.History;
import model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class JDBCHistoryDao extends AbstractJDBCGenericDao<History> implements HistoryDao {

    public JDBCHistoryDao(Connection connection) {
        super(connection);
    }

    @Override
    public void create(History entity) {

    }

    @Override
    public History findById(int id) {
        return null;
    }

    @Override
    public List<History> findAllbyBankAccountId(int bankAccountId, int userId) {
        List<History> historyList = new LinkedList<>();
        try {
            PreparedStatement preparedStatement = getConnection()
                    .prepareStatement(Statements.SELECT_ALL_HISTORY_BY_ACCOUNT_ID_USER_ID);
            // userID, accountId, userId, accountId, userId, userId, accountId, accountId
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, bankAccountId);
            preparedStatement.setInt(3, userId);
            preparedStatement.setInt(4,bankAccountId);
            preparedStatement.setInt(5, userId);
            preparedStatement.setInt(6, userId);
            preparedStatement.setInt(7, bankAccountId);
            preparedStatement.setInt(8, bankAccountId);
            /*
            preparedStatement.setInt(1,bankAccountId);
            preparedStatement.setInt(2,bankAccountId);
            preparedStatement.setInt(3,userId);
            */
            ResultSet resultSet = preparedStatement.executeQuery();
            Extracter<History> historyExtracter = new Extracter<>();
            Extracter<User> userExtracter = new Extracter<>();
            Mapper<User> userMapper = new Mapper<>();

            User user;
            History history;
            while(resultSet.next()){
                history = historyExtracter.extractEntityFromResultSet(resultSet,new History());
                user = userMapper.makeUnique(userExtracter.extractEntityFromResultSet(resultSet,new User()));
                history.setUser(user);
                historyList.add(history);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return historyList;
    }

    @Override
    public List<History> findAll() {
        return null;
    }

    @Override
    public void update(History entity) {

    }

    @Override
    public void delete(int id) {

    }
}
