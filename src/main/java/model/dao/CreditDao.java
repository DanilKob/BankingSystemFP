package model.dao;

import model.entity.CreditAccount;

import java.util.List;

public interface CreditDao extends GenericDao<CreditAccount> {
    public boolean pay(int fromAccountId, int fromUserId, int toAccountId, int toUserId, int price);

    public List<CreditAccount> findAllByUserId(int accountId);

    public List<CreditAccount> findAllUnconfirmedByUserId(int accountId);

}
