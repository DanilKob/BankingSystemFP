package model.dao;

import model.entity.User;
import model.exception.NotUniqueException;

import java.util.Optional;

public interface UserDao extends GenericDao<User>{
    void registerUser(User user) throws NotUniqueException;
    Optional<User> findByLoginPassword(String login, String password);
    User findByBankAccount(int bankAccountId);
}
