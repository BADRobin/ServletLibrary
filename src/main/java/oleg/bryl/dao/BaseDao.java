package oleg.bryl.dao;

import oleg.bryl.entity.BaseEntity;

import java.sql.Connection;

public abstract class BaseDao<T extends BaseEntity> implements Dao<T> {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}
