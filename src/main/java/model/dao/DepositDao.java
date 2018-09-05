package model.dao;

import model.entity.DepositAccount;
import model.exception.TariffNotExistException;

import java.util.List;

public interface DepositDao extends GenericDao<DepositAccount>{
    void registerDeposit(DepositAccount depositAccount) throws TariffNotExistException;
    List<DepositAccount> findAllByUserId(int userId);
    List<DepositAccount> findAllByUserBankAccountId(int bankAccountId);
}
