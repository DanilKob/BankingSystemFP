package model.dao;

import model.entity.CreditAccount;
import model.exception.TariffNotExistException;

import java.util.List;

public interface CreditDao extends GenericDao<CreditAccount> {

    void registerCredit(CreditAccount creditAccount) throws TariffNotExistException;

    List<CreditAccount> findAllConfirmedByUserId(int accountId);

    List<CreditAccount> findAllUnconfirmedCreditsByUserId(int accountId);

    List<CreditAccount> findAllUnconfirmedCredits();

    void udpateCreditAccountBalanceByAccountId(int creditId, int indebtedness);

}
