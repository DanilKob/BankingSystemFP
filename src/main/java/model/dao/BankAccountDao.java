package model.dao;

import model.entity.BankAccount;

import java.util.List;

public interface BankAccountDao extends GenericDao<BankAccount> {
    public List<BankAccount> findAllBankAccountByUserId(int userId);
}
