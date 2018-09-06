package model.dao.impl;

import model.dao.GenericDao;
import model.entity.Entity;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractJDBCGenericDao<T extends Entity> implements GenericDao<T> {
    private Connection connection;

    public AbstractJDBCGenericDao(Connection connection){
        this.connection = connection;
    }

    public Connection getConnection(){
        return connection;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            Logger.getLogger(AbstractJDBCGenericDao.class.getName()).error("Close connection",e);
            throw new RuntimeException(e);
        }
    }
}
