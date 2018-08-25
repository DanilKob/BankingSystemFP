package model.dao;

import model.entity.Entity;
import model.exception.NotUniqueException;

import java.util.List;

public interface GenericDao<T extends Entity> extends AutoCloseable {
    void create(T entity) throws NotUniqueException;
    T findById(int id);
    List<T> findAll();
    void update(T entity);
    void delete(int id);
    void close();
}
