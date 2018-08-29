package model.dao;

import model.entity.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User>{
    public Optional<User> findByLoginPassword(String login, String password);
    public User findByBankAccount(int bankAccountId);
}
