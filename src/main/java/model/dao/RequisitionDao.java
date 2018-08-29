package model.dao;

import model.entity.Requsition;

import java.util.List;

public interface RequisitionDao extends GenericDao<Requsition> {
    public List<Requsition> findAllbyBankAccountId(int bankAccountId);
}
