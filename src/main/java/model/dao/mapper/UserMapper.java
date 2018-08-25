package model.dao.mapper;

import model.dao.statement.tables.RoleTable;
import model.dao.statement.tables.UserTable;
import model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper extends GenericMapper<Integer,User> {
    @Override
    public User extractFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User.Builder()
                .setId(resultSet.getInt(UserTable.USER_ID))
                .setFirstName(resultSet.getString(UserTable.USER_FIRST_NAME))
                .setLastName(resultSet.getString(UserTable.USER_LAST_NAME))
                .setMiddleName(resultSet.getString(UserTable.USER_MIDDLE_NAME))
                .setLogin(resultSet.getString(UserTable.USER_LOGIN))
                .setRole(resultSet.getString(RoleTable.ROLE_NAME))
                .build();
        return user;
    }

    @Override
    public Integer getKey(User entity) {
        return entity.getId();
    }
}
