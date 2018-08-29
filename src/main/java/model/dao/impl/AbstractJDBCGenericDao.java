package model.dao.impl;

import model.dao.GenericDao;
import model.entity.Entity;

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
            // todo remose sout
            System.out.println("Close connection");
            connection.close();
        } catch (SQLException e) {
            // todo add logger
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
