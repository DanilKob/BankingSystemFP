package model.dao;

import model.entity.Entity;
import model.exception.BankAccountNotExistException;

import java.util.List;

public interface GenericDao<T extends Entity> extends AutoCloseable {
    void create(T entity);
    T findById(int id) throws BankAccountNotExistException;
    List<T> findAll();
    void update(T entity);
    void delete(int id);
    void close();
}
