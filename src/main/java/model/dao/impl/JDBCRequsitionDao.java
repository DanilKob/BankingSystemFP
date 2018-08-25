package model.dao.impl;

import model.dao.RequisitionDao;
import model.entity.Requsition;

import java.sql.Connection;
import java.util.List;

public class JDBCRequsitionDao extends AbstractJDBCGenericDao<Requsition> implements RequisitionDao {

    public JDBCRequsitionDao(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Requsition entity) {

    }

    @Override
    public Requsition findById(int id) {
        return null;
    }

    @Override
    public List<Requsition> findAllbyBankAccountId(int bankAccountId) {
        return null;
    }

    @Override
    public List<Requsition> findAll() {
        return null;
    }

    @Override
    public void update(Requsition entity) {

    }

    @Override
    public void delete(int id) {

    }
}
