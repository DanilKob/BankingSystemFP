package model.dao;

import model.entity.DepositAccount;

import java.util.List;

public interface DepositDao extends GenericDao<DepositAccount>{
    public List<DepositAccount> findAllByUserId(int userId);
}
