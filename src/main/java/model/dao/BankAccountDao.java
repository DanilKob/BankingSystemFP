package model.dao;

import model.entity.BankAccount;
import model.exception.BankAccountNotExistException;

import java.util.List;

public interface BankAccountDao extends GenericDao<BankAccount> {
    public List<BankAccount> findAllBankAccountByUserId(int userId);

    boolean pay(int fromAccountId, int fromUserId, int toAccountId, int price) throws BankAccountNotExistException;
}
