package model.dao;

import model.entity.History;

import java.util.List;

public interface HistoryDao extends GenericDao<History> {
    public List<History> findAllbyBankAccountId(int bankAccountId, int userId);
}
